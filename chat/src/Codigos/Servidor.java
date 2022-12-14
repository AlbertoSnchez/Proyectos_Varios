package Codigos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class Servidor {
	
	private static JFrame frame;
	private static VistaServidor showoff;
	private static ControladorServidor manager;
	public static ServerSocket server;
	private static ListaClientes client;
	public static void main(String[] args) {
		
		configurarVentana();
        show();
        
        try {
		    start();		    
		    do {
	    		handleClient();
		    }while(!server.isClosed());
        } catch (BindException e) {
			showoff.addText("Ya tienes una instancia del servidor abierta");
		} catch(IOException e) {
        	showoff.addText("FATAL ERROR: No fue posible iniciar el servidor.");
        }
        
        while(true) {}
    }
    
	public static void display(String message) {
		showoff.addText(message);
	}
	
	public static void displayall(String message) {
		display(message);
		client.emit(message);
	}
	
	public static ListaClientes getClientes() {
		return client;
	}
		
	private static void configurarVentana() {

        frame = new JFrame("Servidor");
        showoff = new VistaServidor();
        manager = new ControladorServidor(showoff);

        frame.setContentPane(showoff);
	}
	
    private static void show(){
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private static void start() throws IOException{
		server = new ServerSocket(42455);
		client = new ListaClientes();
		manager.setServidor(server);
		server.getInetAddress();
		showoff.addText("(Servidor) Servidor iniciado en "+InetAddress.getLocalHost().getHostAddress());
    }
    private static void handleClient(){
    	try {
	    	Socket cliente = server.accept();
	    	HiloServidor thread = new HiloServidor(showoff, cliente);
		thread.start();
		thread.setTCP("aceptado");
    	}catch(IOException e) { }
    }
    
    public static void addclient(HiloServidor thread) {
    	client.add(thread.getname(), thread);
    	client.updateon();
    	showoff.setClientesConectados(client.getClientsOn());
    }
    
    public static void deleteclient(String nombre) {
    	client.remove(nombre);
    	client.updateon();
    	showoff.setClientesConectados(client.getClientsOn());
    }
}

class ControladorServidor implements ActionListener {

	private VistaServidor showoff;
	private ServerSocket server;
	
	public ControladorServidor(VistaServidor showoff) {
		this.showoff=showoff;
	}
	
	public void setServidor(ServerSocket server) {
		this.server= server;
	}
	
	@Override
	public void actionPerformed(ActionEvent action_event) {
		switch(action_event.getActionCommand()){
			case "apagar":
				try {
					do {
						Servidor.getClientes().logout();
						server.close();
						showoff.addText("(Servidor) el servidor se ha apagado.");
						showoff.apagar();
					}while(!server.isClosed());
				} catch (IOException e) {showoff.addText("FATAL ERROR: el servidor ya estaba apagado"); }
			break;
			default:
				
			break;
		}
	}
}

class HiloServidor extends Thread {

	private Socket client;
	private VistaServidor showoff;
	
	private BufferedReader buff;
	private PrintWriter exit_;	
	
	private String name;
	
	public HiloServidor(VistaServidor showoff, Socket client) throws IOException {
		this.showoff =showoff;
		this.client = client;
		this.client.setSoTimeout(5000);
		name = "";
		buff = new BufferedReader(new InputStreamReader(client.getInputStream()));
		exit_ = new PrintWriter(client.getOutputStream(), true);
	}
	
	public void run() {
		String aid;
		sign();
		try {
			do {
				aid=getTCP();
				messageHandler(aid.trim());
			}while(!aid.trim().equals("/exit"));
			
			buff.close();
			exit_.close();
			client.close();
		} catch(SocketTimeoutException e) { 
			Servidor.displayall("(Servidor) "+name+" se ha caído.");
			Servidor.deleteclient(name);
		} catch(IOException e) { Servidor.displayall(
                        "(Servidor) "+name+" desconectado."); 
                }
		showoff.setClientesConectados(Servidor.getClientes().getClientsOn());
	}
	
	public String getname() {
		return name;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	
	public void close() {
		setTCP("/exit");
	}
	private void messageHandler(String message) {
		switch(message.trim()) {
			case "/exit":
				
				Servidor.displayall("(Servidor) "+ name+ " abandonó el chat.");
				Servidor.deleteclient(name);
				
			break;
			case "/write":
				
				setTCP("(Servidor) CLIENTES CONECTADOS: " + new String(Servidor.getClientes().getClientList()));
				
			break;
			default:
				
				Servidor.displayall(name+": "+ message);
				
			break;
		}
	}

	private void sign() {
    	name = check(getTCP());
		
		Servidor.addclient(this);
		Servidor.getClientes().updateon();
		Servidor.displayall("(Servidor) "+ name + " se ha unido al chat.");
	}
	private String check(String before) {
    	String after = before; int i = 1;
    	while(Servidor.getClientes().alreadyin(after)) { 
    		after=before.concat(Integer.toString(i));
    		i++; 
    	}
    	return after;
	}
	
	public String getTCP() {
		String cadenaRecibida = null;
		do {
			try {
				cadenaRecibida = buff.readLine();
			} catch (IOException e) { cadenaRecibida = null; }
		} while(cadenaRecibida == null);
			
		return cadenaRecibida;
	}
	
	public void setTCP(String message) {
			exit_.println(message);
	}
	
}

class ListaClientes {

	private static HashMap<String, HiloServidor> hashmap;
	
	public ListaClientes(){
		hashmap = new HashMap<String, HiloServidor>();
	}
	
	public int getClientsOn() {
		return hashmap.size();
	}
	
	public void add(String name, HiloServidor client) {
		hashmap.put(name, client);
	}
	
	public void remove(String name) {
		hashmap.remove(name);
	}
	
	public boolean alreadyin(String name) {
		return hashmap.containsKey(name);
	}
	
	
	public String getClientList() {
		StringBuilder client= new StringBuilder(250);
		Set<String> keys= hashmap.keySet();
		for (String key :keys) {
		   client.append(key + ", ");
		}
		
		client.setLength(client.length()-2);
		client.append(".");
				
		return client.toString().trim();
	}
	
	public void updateon() {
    	emit("/update");
    	emit(getClientsOn() + "");
    }
	
	public void logout() {
		Set<Map.Entry<String, HiloServidor>> set = hashmap.entrySet();
		for (@SuppressWarnings("rawtypes") Map.Entry entry : set) {
			((HiloServidor) entry.getValue()).close();
		}
	}
	
	public void emit(String msg) {
		Set<Map.Entry<String, HiloServidor>> set = hashmap.entrySet();
		for (@SuppressWarnings("rawtypes") Map.Entry entry : set) {
		   ((HiloServidor) entry.getValue()).setTCP(msg);
		}
	}
}

class VistaServidor extends JPanel {

	private static final long serialVersionUID = 1L;
	
	@Deprecated
	private int clientesConectados;
	
	private JLabel labelConexiones;
	private JTextArea texto;
	
	DefaultCaret caret;
	
	public VistaServidor() {
		setLayout(new BorderLayout());
		JPanel panelNorte = new JPanel(new GridLayout(1, 2));
		JPanel panelSur = new JPanel(new GridLayout(1, 2));
	
		clientesConectados = 0;
		labelConexiones = new JLabel("Clientes conectados: 0");
		texto = new JTextArea();
		texto.setEditable(false);
		JScrollPane scroll = new JScrollPane(texto);
	
		panelNorte.add(labelConexiones);
		this.add(panelNorte, BorderLayout.NORTH);
		this.add(panelSur, BorderLayout.SOUTH);
		add(scroll, BorderLayout.CENTER);
		
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		caret = (DefaultCaret)texto.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		texto.setLineWrap(true);
		
		setPreferredSize(new Dimension(480, 360));
	}
	
	public void setClientesConectados(int clientesConectados) {
		labelConexiones.setText("Clientes conectados: " + clientesConectados);
	}
	
	public void addText(String linea) {
		texto.append(linea+ "\n");
	}
	
	public void apagar() {
		texto.setEnabled(false);
		labelConexiones.setText("Servidor apagado.");
	}
	
	@Deprecated
	public void sumarCliente() {
		clientesConectados++;
		labelConexiones.setText("Clientes conectados: " + clientesConectados);
	}
	
	@Deprecated
	public void restarCliente() {
		clientesConectados--;
		labelConexiones.setText("Clientes conectados: " + clientesConectados);
	}
}
