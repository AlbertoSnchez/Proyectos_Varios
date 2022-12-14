package Codigos;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

public class Cliente {
	
	private static JFrame frame;
	private static VistaCliente showoff;
	private static ControladorCliente manager;
	private static Socket client;
	private static UtilidadesCliente functions;

	public static void main(String[] args) {
		
		configurarVentana();
		
		try {
			
			clientStart();
			
			while(!client.isClosed()) {
				functions.handleMessage();
			}
			
			while(true) {}
		} catch (SocketTimeoutException e) {
			frame.setEnabled(false);
			JOptionPane.showMessageDialog(frame, "Conexión perdida", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (SocketException e) {
			frame.setEnabled(false);
			JOptionPane.showMessageDialog(frame, "Servidor no localizado", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} 
		
	}

	private static void configurarVentana() {
	
        frame= new JFrame("Cliente");
        showoff= new VistaCliente(frame);
        manager= new ControladorCliente(showoff);
        
        frame.setContentPane(showoff);
        showoff.setControlador(manager);
        frame.pack();
        frame.setResizable(false);
	}
    
    private static void clientStart() throws NumberFormatException, IOException {
    	String host = JOptionPane.showInputDialog(frame, "Introduce la ip del servidor", "IP", JOptionPane.INFORMATION_MESSAGE);
    	String puerto = "42455";
    	String nickname = JOptionPane.showInputDialog(frame, "Introduce el nombre de usuario", "Nombre de usuarop", JOptionPane.INFORMATION_MESSAGE);
    	
    	if(host.equals(""))
    		host = "localhost";
		
    	try {
    		if(nickname.equals(""))
    			throw new IOException("Nombre de usuario no válido.");
    		client= new Socket();
    		client.connect(new InetSocketAddress(host, Integer.parseInt(puerto)), 5000);
    		functions= new UtilidadesCliente(client, showoff, manager);
    	
    		if(functions.getTCP().trim().equals("aceptado")) {
    			start(nickname);
    		}else {
    			functions= null;
    			throw new IOException("Servidor lleno");
    		}
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(frame, "Ha ocurrido un error" + e.getStackTrace().toString(), "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
    private static void start(String ign) throws IOException {
    	frame.setVisible(true);
		showoff.setEnabled(true);
		manager.setCliente(functions);
		functions.enviarTCP(ign);
    }
    
    
}

class ControladorCliente implements ActionListener {

	private UtilidadesCliente client;
	private VistaCliente showoff;
	
	public ControladorCliente(VistaCliente showoff) {
		this.showoff=showoff;
	}

	public void setCliente(UtilidadesCliente client) {
		this.client=client;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()) {
			case "salir":
				exit();
			break;
			case "enviar":
				client.enviarTCP(showoff.getTextoCampo());
				showoff.vaciarTextoCampo();
			break;
			case "listado":
				client.enviarTCP("/write");
			break;
			case "limpiar":
				showoff.limpiarChat();
			break;
			default:
			break;
		}
	}
	
	public int exit() {
		client.enviarTCP("/exit");
		client.close();
		showoff.setClientes("Unknown");
		showoff.addText("(Cliente) Sesión terminada.");
		showoff.setEnabled(false);
		return 0;
	}
}

class UtilidadesCliente {

	private VistaCliente showoff;
	private ControladorCliente manager;
	private Socket client;
	
	private BufferedReader buff;
	private PrintWriter out;
	
	public UtilidadesCliente(Socket client, VistaCliente showoff, ControladorCliente manager) throws IOException {
		this.client = client;
		this.showoff = showoff;
		this.manager = manager;
		buff = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(), true);
	}
	
	public String getTCP() throws IOException {
		String aid= null;
		do {
				aid= buff.readLine();
		} while(aid==null);
			
		return aid;
	}
	
	public void enviarTCP(String string) {
			out.println(string);
	}
	
	
	public void close() {
		try {
			buff.close();
			out.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
    public void handleMessage() {
		try {
			String message=getTCP();
		
			switch(message.trim()){
				case "/exit":
					
					manager.exit();
					showoff.addText("(Cliente) El servidor se ha apagado");
					
				break;
				case "/update":
					showoff.setClientes(getTCP());
				break;
				default: 
					showoff.addText(message);
						
				break;
			}
	    	
		} catch (IOException e) {
			manager.exit();
			showoff.addText("(Cliente) Servidor desconectado.");
		}
    }
}

class VistaCliente extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame ventana;
	WindowListener exitListener;
	
	private JLabel labelClientes;
	private JTextArea chat;
	private JTextField campo;
	private JButton botonEnviar, botonLimpiar, botonListado;
	DefaultCaret caret;
	
	public VistaCliente(JFrame ventana) {
		this.ventana = ventana;
		setLayout(new BorderLayout());
		JPanel panelNorte = new JPanel(new GridLayout(1,3));
		JPanel panelSur = new JPanel(new GridLayout(1,3));
                JPanel contenedor = new JPanel(new GridLayout(1,2));
	
                panelSur.setLayout(new BoxLayout(panelSur, BoxLayout.Y_AXIS));
                
		labelClientes = new JLabel("Clientes en el chat: 0");
		chat = new JTextArea();
		campo = new JTextField();
		botonListado = new JButton("Listado de clientes");
		botonEnviar = new JButton("Enviar");
		botonLimpiar = new JButton("Limpiar chat");
		JScrollPane scroll = new JScrollPane(chat);
                
                Border line = new LineBorder(Color.WHITE);
                Border margin = new EmptyBorder(5, 15, 5, 15);
                Border compound = new CompoundBorder(line, margin);
                
                botonListado.setForeground(Color.WHITE);
                botonListado.setBackground(Color.GRAY);
                botonListado.setBorder(compound);
                
                botonEnviar.setForeground(Color.WHITE);
                botonEnviar.setBackground(Color.GRAY);
                botonEnviar.setBorder(compound);
                
                
                botonEnviar.setForeground(Color.WHITE);
                botonEnviar.setBackground(Color.GRAY);
                botonEnviar.setBorder(compound);
                
                botonLimpiar.setForeground(Color.WHITE);
                botonLimpiar.setBackground(Color.GRAY);
                botonLimpiar.setBorder(compound);
	
		panelNorte.add(labelClientes);
		panelNorte.add(botonListado);
		panelSur.add(campo);
		contenedor.add(botonLimpiar);
		contenedor.add(botonEnviar);
                panelSur.add(contenedor);
		
		add(panelNorte, BorderLayout.NORTH);
		add(panelSur, BorderLayout.SOUTH);
		add(scroll, BorderLayout.CENTER);
	
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		caret = (DefaultCaret)chat.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		setPreferredSize(new Dimension(480, 360));
		chat.setLineWrap(true);
		
		chat.setEditable(false);
		setEnabled(false);
	}
	
	public String getTextoCampo() {
		return campo.getText().toString();
	}
	
	public void vaciarTextoCampo() {
		campo.setText("");
	}
	
	public void setClientes(String clientes) {
		labelClientes.setText("Clientes en el chat: "+ clientes);
	}
	
	public void addText(String linea) {
		chat.append(linea+"\n");
	}
	
	public void limpiarChat() {
		chat.setText("");
	}
	
	public void setEnabled(boolean activado) {
		campo.setEnabled(activado);
		chat.setEnabled(activado);
		botonEnviar.setEnabled(activado);
		botonLimpiar.setEnabled(activado);
		botonListado.setEnabled(activado);
	}
	
	public void setControlador(final ControladorCliente l) {
		botonEnviar.setActionCommand("enviar");
		campo.setActionCommand("enviar");
		botonLimpiar.setActionCommand("limpiar");
		botonListado.setActionCommand("listado");
		
		botonEnviar.addActionListener(l);
		campo.addActionListener(l);
		botonLimpiar.addActionListener(l);
		botonListado.addActionListener(l);
		
		exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
		        l.exit();
		        System.exit(0);
		    }
		};
		ventana.addWindowListener(exitListener);
	}	
}
