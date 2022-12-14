import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;
//Grupo Scrum 3

//Se trata de un programa multifuncional de gestión de las nóminas de los empleados de una empresa.


public class GestionNominas implements Serializable{
	public static void main(String[] args) throws Exception{
		// Declaración de variables, objetos
		ArrayList<Persona> miEmpresa = new ArrayList<>();

		//Creamos a varias personas
		JefeDeGrupo j1 = new JefeDeGrupo("C", "637384729", new Fecha(25, 07, 2003), "34124134S",
				new CuentaBancaria("BBVA", "ES9121000418450200051332"),
				new Contrato("JEFE", 340000, new Fecha(23, 04, 2020)),12, "Avengers",
				new ArrayList<Tecnico>());
		Tecnico t1 = new Tecnico("G", "637387228", new Fecha(2, 05, 2003), "341343234E",
				new CuentaBancaria("Santander", "ES912100041845021671251332"),
				new Contrato("tecnico", 34000, new Fecha(23, 04, 2021)),5);
		Administracion admin1 = new Administracion("P", "677512456", new Fecha(12, 03, 2003), "123214415D",
				new CuentaBancaria("EVO", "ES1231082480801841048140"),
				new Contrato("admin", 25000, new Fecha(12, 04, 2015)),25);
		JefeDeGrupo j2 = new JefeDeGrupo("K", "657899910", new Fecha(23, 12, 2001), "3471213981Z",
				new CuentaBancaria("CaixaBank", "ES1231913782480801841048140"),
				new Contrato("jefe", 3500000, new Fecha(12, 01, 2019)),12,  "Justice League",
				new ArrayList<Tecnico>());
		Tecnico t2 = new Tecnico("F", "6378987228", new Fecha(2, 05, 2000), "34123472234K",
				new CuentaBancaria("Santander", "ES9121847183441845021671251332"),
				new Contrato("tecnico", 23000, new Fecha(23, 04, 2021)),5);

		//Añadimos unos empleados		
		miEmpresa.add(j1);
		miEmpresa.add(t1);
		j1.equipo.add(t1);
		miEmpresa.add(admin1);
		miEmpresa.add(j2);
		miEmpresa.add(t2);
		j2.equipo.add(t2);

		int numeroIntroducido = -1;
		Scanner teclado = new Scanner(System.in);
		while (numeroIntroducido != 0){
			// Se imprime por pantalla las opciones disponibles de menú
			System.out.println("Elige una de las siguientes opciones:");
			System.out.println("1-Dar de alta un contrato\n" + "2-Dar de baja un contrato \n" + "3-Añadir técnico\n"
					+ "4-Quitar técnico\n" + "5-Añadir horas extra\n" + "6-Añadir días de baja\n"
					+ "7-Listado de empleados actuales\n" + "8-Listado empleados dados de baja\n"
					+ "9-Listado de empleados de administración\n" + "10-Listado de equipos\n" + "11-Generar nómina\n"
					+ "12-Generar nóminas\n" + "13-Escribir la información de todos los empleados en un archivo de datos\n" +  "14-Leer la información del archivo de datos\n" +  "15-Escribir nómina de un empleado en un archivo\n" +  "16/17-Escribir nómina de todos los empleados en un archivo\n" + "0-Salir\n");

			// Se le pide al ususario introducir un número acorde con las opciones
			numeroIntroducido = teclado.nextInt();

			switch (numeroIntroducido) {
				case 1:
					darAlta(miEmpresa);//Al seleccionar le número 1, se llama a la función "dar de alta"
					break;
				case 2:
					darBaja(miEmpresa);//Al seleccionar le número 2, se llama a la función "dar de baja"
					break;
				case 3:
					annadirTecnico(miEmpresa);//Al seleccionar le número 3, se llama a la función "añadir técnico"
					break;
				case 4:
					quitarTecnico(miEmpresa);//Al seleccionar le número 4, se llama a la función "quitar técnico"
					break;
				case 5:
					annadirHorasExtra(miEmpresa);//Al seleccionar le número 5, se llama a la función "añadir horas extra"
					break;
				case 6:
					annadirDiasBaja(miEmpresa);//Al seleccionar le número 6, se llama a la función "añadir días de baja"
					break;
				case 7:
					listadoEmpleados(miEmpresa);//Al seleccionar le número 7, se llama a la función "listado de empleados"
					break;
				case 8:
					listadoEmpleadosBaja(miEmpresa);//Al seleccionar le número 8, se llama a la función "listado de empleados de baja"
					break;
				case 9:
					listadoAdministracion(miEmpresa);//Al seleccionar le número 9, se llama a la función "listado de administración"
					break;
				case 10:
					listadoEquipos(miEmpresa);//Al seleccionar le número 10, se llama a la función "listado de equipos"
					break;
				case 11:
					generarNomina(miEmpresa);//Al seleccionar le número 11, se llama a la función "generar nómina"
					break;
				case 12:
					gNominas(miEmpresa);//Al seleccionar le número 12, se llama a la función "gestión de nóminas"
					break;

				case 13:
					escribirInfo(miEmpresa);//Al seleccionar le número 13, se llama a la función "escribir información"
					break;
				
				case 14: 
					leerInfo(miEmpresa);//Al seleccionar le número 14, se llama a la función "leer información"
					break;

				case 15:
					nomina(miEmpresa);//Al seleccionar le número 15, se llama a la función "nómina"
					break;

				case 16:
					nominas(miEmpresa);//Al seleccionar le número 16, se llama a la función "nóminas"
					break;
				case 17:
					NominaTextoArchivo(miEmpresa);//Al seleccionar le número 17, se llama a la función "NominaTextoArchivo"
					break;
				case 0: //Al seleccionar le número 0, se pregunta si se quiere salir del programa o volver a poder seleccionar una opción"
					System.out.println("Desea salir?");
					Scanner scanner = new Scanner(System.in);
					scanner.nextLine();
					String resp = scanner.nextLine();
					if(resp=="NO"||resp=="no"){
						numeroIntroducido = -50;
					}
					break;
			}
		}
		teclado.close();

	}
	
	//Constructor que da de baja a una persona del ArrayList
	public static void darAlta(ArrayList<Persona> Empresa) throws Exception {
		// Declaración de funciones
		double salarioBase, IRPF;
		String puesto, nombre, telefono, DNI, resp, entidad, IBAN;
		String equipo = "";
		Fecha fechanacimiento, fechaAlta, fechaBaja;
		CuentaBancaria cuenta;
		int dia, mes, anno;
		Contrato contratopersona;

		try {
			// Se pide al usuario los datos personales de la persona que quiere darse de
			// alta (nombre, DNI, teléfono y fecha de nacimiento)

			System.out.println("-----------");
			System.out.println("DAR DE ALTA");
			System.out.println("-----------");

			Scanner scanner = new Scanner(System.in);
			System.out.println("Introduzca el nombre de la persona a la que se quiere dar de alta: ");
			nombre = scanner.nextLine();

			System.out.println("Introduzca el DNI: ");
			DNI = scanner.nextLine();

			System.out.println("Introduzca el número de teléfono ");
			telefono = scanner.nextLine();

			System.out.println("Introduzca la fecha de nacimiento: ");
			System.out.println("Día: ");
			dia = scanner.nextInt();
			System.out.println("Mes: ");
			mes = scanner.nextInt();
			System.out.println("Año: ");
			anno = scanner.nextInt();
			fechanacimiento = new Fecha(dia, mes, anno);

			System.out.println("Procesando información. Por favor, espere...");

			scanner.nextLine();

			// Se pide al usuario el puesto de trabajo, el salario base establecido en el
			// contrato, las fechas en las que se da de alta y de baja
			System.out.println();
			System.out.println("Introduzca el puesto de trabajo: ");
			puesto = scanner.nextLine();
			if(puesto== "JEFE" || puesto == "jefe" || puesto == "BOSS" || puesto == "boss"){
				System.out.println("Introduzca el nombre de su equipo: ");
				equipo = scanner.nextLine();
			}

			System.out.println("Introduzca el salario base: ");
			salarioBase = scanner.nextDouble();

			System.out.println("Introduzca la fecha de alta: ");
			System.out.println("Día: ");
			dia = scanner.nextInt();
			System.out.println("Mes: ");
			mes = scanner.nextInt();
			System.out.println("Año: ");
			anno = scanner.nextInt();
			fechaAlta = new Fecha(dia, mes, anno);

			scanner.nextLine();
			System.out.println("Procesando información. Por favor, espere...");

			// Se le pregunta al usuario si tiene o no fecha de baja
			System.out.println("¿Tiene fecha de baja? Conteste con Si o No.");
			resp = scanner.nextLine();
			if (resp == "Si" || resp == "si" || resp == "SI" || resp == "sí" || resp == "Sí") {
				// En caso afirmativo, se pide la fecha de baja
				System.out.println("Introduzca la fecha de baja: ");
				System.out.println("Día: ");
				dia = scanner.nextInt();
				System.out.println("Mes: ");
				mes = scanner.nextInt();
				System.out.println("Año: ");
				anno = scanner.nextInt();
				fechaBaja = new Fecha(dia, mes, anno);
				contratopersona = new Contrato(puesto, salarioBase, fechaAlta, fechaBaja);
				System.out.println("Procesando información. Por favor, espere...");
				System.out.println("Datos de la cuenta bancaria: ");
				System.out.println("Introduzca su entidad bancaria: ");
				entidad = scanner.nextLine();
				System.out.println("Introduzca el IBAN: ");
				IBAN = scanner.nextLine();
				cuenta = new CuentaBancaria(entidad, IBAN);
				System.out.println("Introduzca el IRPF: ");
				IRPF = scanner.nextDouble();
				if(puesto == "Técnico" ||puesto == "TECNICO"||puesto == "tecnico" || puesto == "técnico"){
					Tecnico t = new Tecnico(nombre, telefono, fechanacimiento, DNI, cuenta, contratopersona,IRPF);
					Empresa.add(t);
				}else if(puesto == "Administración" || puesto == "ADMINISTRACION" || puesto == "admin" || puesto == "ADMIN"){
					Administracion a = new Administracion(nombre, telefono, fechanacimiento, DNI, cuenta, contratopersona,IRPF);
					Empresa.add(a);
				}else if(puesto== "JEFE" || puesto == "jefe" || puesto == "BOSS" || puesto == "boss"){
					JefeDeGrupo j = new JefeDeGrupo(nombre, telefono, fechanacimiento, DNI, cuenta, contratopersona, IRPF, equipo, new ArrayList<Tecnico>());
					Empresa.add(j);
				}

			} else if (resp == "No" || resp == "no" || resp == "NO") {
				// En caso negativo, la fecha de baja no tendrá valor
				contratopersona = new Contrato(puesto, salarioBase, fechaAlta);
				System.out.println("Procesando información. Por favor, espere...");
				// Se le pide al ususario los datos de la cuenta bancaria (entidad e IBAN)
				System.out.println("Datos de la cuenta bancaria: ");
				System.out.println("Introduzca su entidad bancaria: ");
				entidad = scanner.nextLine();
				System.out.println("Introduzca el IBAN: ");
				IBAN = scanner.nextLine();
				System.out.println("Introduzca el IRPF: ");
				IRPF = scanner.nextDouble();
				cuenta = new CuentaBancaria(entidad, IBAN);
				if(puesto == "Técnico" ||puesto == "TECNICO"||puesto == "tecnico" || puesto == "técnico"){
					Tecnico t = new Tecnico(nombre, telefono, fechanacimiento, DNI, cuenta, contratopersona,IRPF);
					Empresa.add(t);
				}else if(puesto == "Administración" || puesto == "ADMINISTRACION" || puesto == "admin" || puesto == "ADMIN"){
					Administracion a = new Administracion(nombre, telefono, fechanacimiento, DNI, cuenta, contratopersona,IRPF);
					Empresa.add(a);
				}else if(puesto== "JEFE" || puesto == "jefe" || puesto == "BOSS" || puesto == "boss"){
					JefeDeGrupo j = new JefeDeGrupo(nombre, telefono, fechanacimiento, DNI, cuenta, contratopersona,IRPF, equipo, new ArrayList<Tecnico>());
					Empresa.add(j);
				}
			}
			scanner.close();

		} catch (Exception e) {
			System.out.println("Los datos introducidos no son válidos.");
		}

	}

	//Constructor que da de baja a una persona del ArrayList
	public static void darBaja(ArrayList<Persona> miEmpresa) throws Exception {
		// Declaración de funciones
		String DNIbaja;
		Fecha Fechabaja;
		int dia, mes, anno;

		System.out.println("-----------");
		System.out.println("DAR DE BAJA");
		System.out.println("-----------");

		// Se le pide al usuario el DNI de la persona que quiere darse de baja y la
		// fecha correspondiente a dicha baja
		System.out.println("Introduce el DNI de la persona que se quiere dar de baja: ");
		Scanner scanner = new Scanner(System.in);

		try {
			DNIbaja = scanner.nextLine();

			System.out.println("Introduce la fecha de la baja: ");
			System.out.println("Día: ");
			dia = scanner.nextInt();
			System.out.println("Mes: ");
			mes = scanner.nextInt();
			System.out.println("Año: ");
			anno = scanner.nextInt();
			Fechabaja = new Fecha(dia, mes, anno);

			for (Persona p : miEmpresa) { // Se busca en el ArrayList el DNI introducido
				if (p.DNI == DNIbaja) { // Una vez encontrado, se cambia la fecha de baja a la actual y el salario base
										// se iguala a 0
					p.contratopersona.fechaBaja = Fechabaja;
					p.contratopersona.salarioBase = 0;
				}
			}

		} catch (Exception e) {
			System.out.print("Los datos introducidos no son válidos");
		}
		scanner.close();
	}
	
	//Constructor que añade a un técnico a un equipo
	public static void annadirTecnico(ArrayList<Persona> miEmpresa) {
		// Declaración de funciones
		String nEquipo;
		String DNItecnico;

		System.out.println("--------------");
		System.out.println("AÑADIR TÉCNICO");
		System.out.println("--------------");

		// Se pide al usuario el nombre del equipo al que se quiere añadir y el DNI de
		// la persona
		System.out.println("Introduce el nombre del equipo: ");
		Scanner teclado = new Scanner(System.in);
		nEquipo = teclado.nextLine();

		System.out.println("Introduce el DNI de la persona que se quiere añadir al equipo: ");
		DNItecnico = teclado.nextLine();

		JefeDeGrupo j = null;
		for (Persona p : miEmpresa) { // Se busca en el ArrayList el nombre de equipo y el DNI introducido
			if (p instanceof JefeDeGrupo) {
				j = (JefeDeGrupo) p;
				if (j.nombreEquipo == nEquipo) {
					for (Persona e : miEmpresa) {
						if (e.DNI == DNItecnico) { // Si todo coincide, se añade al equipo
							j.equipo.add((Tecnico) e);
						}
					}
				}

			}
		}
		teclado.close();

	}
	
	//Constructor que quitar un técnico de un equipo 
	public static void quitarTecnico(ArrayList<Persona> miEmpresa) {
		// Declaración de funciones
		String nEquipo;
		String DNItecnico;

		System.out.println("--------------");
		System.out.println("QUITAR TÉCNICO");
		System.out.println("--------------");

		// Se le pide al usuario el nombre del equipo del que se quiere quitar y el DNI
		// de la persona
		System.out.println("Introduce el nombre del equipo: ");
		Scanner teclado = new Scanner(System.in);
		nEquipo = teclado.nextLine();
		;

		System.out.println("Introduce el DNI del tecnico a quitar: ");
		DNItecnico = teclado.nextLine();

		JefeDeGrupo j = null;
		for (Persona p : miEmpresa) { // Se busca en el ArrayList el nombre de equipo y el DNI introducido
			if (p instanceof JefeDeGrupo) {
				j = (JefeDeGrupo) p;
				if (j.nombreEquipo == nEquipo) {
					for (Persona e : miEmpresa) {
						if (e.DNI == DNItecnico) {// Si conincide todo, se elimina del equipo
							j.equipo.remove((Tecnico) e);
						}
					}
				}

			}
		}
		teclado.close();

	}

	//Constructor que añade horas extras a una persona del ArrayList
	public static void annadirHorasExtra(ArrayList<Persona> miEmpresa) {
		// Declaración de variables
		String DNI;
		int mes, anno, numero;

		Scanner scanner = new Scanner(System.in);

		System.out.println("------------------");
		System.out.println("AÑADIR HORAS EXTRA");
		System.out.println("------------------");

		// Se le pide al usuario introducir los datos (DNI, mes y año de las horas extra
		// y el número de estas)
		System.out.println("Introduce el DNI de la persona a la que quieres aÃ±adir las horas extra: ");
		DNI = scanner.nextLine();
		System.out.println("Introduce el mes: ");
		mes = scanner.nextInt();
		System.out.println("Introduce el año: ");
		anno = scanner.nextInt();
		System.out.println("Introduce el numero de horas extra: ");
		numero = scanner.nextInt();

		//Se recorre el ArrayList buscando el dni introducido por el usuario y se ajustan los datos pedidos en el contrato de la persona seleccionada
		for (Persona i : miEmpresa) {
			if (i.DNI == DNI) {
				i.contratopersona.c.annadirHorasExtra(mes, anno, numero);
			}
		}
		scanner.close();

	}
	
	//Constructor que añade días de baja de una persona del ArrayList
	public static void annadirDiasBaja(ArrayList<Persona> miEmpresa) {
		// Declaración de funciones
		String DNI;
		int numero, mes, anno;

		System.out.println("-------------------");
		System.out.println("AÑADIR DÍAS DE BAJA");
		System.out.println("-------------------");

		// Se le pide al ususario el DNI de la persona a la que se le añaden los días de
		// baja
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce el DNI de la persona a la que quieres añadir días de baja");
		DNI = scanner.nextLine();
		for (Persona i : miEmpresa) {
			if (DNI == i.DNI) {
				// Se almacena el número de dí­as, el mes y el año que quiere añadir el usuario
				System.out.println("Introduzca el mes:");
				mes = scanner.nextInt();
				System.out.println("Introduzca el año:");
				anno = scanner.nextInt();
				System.out.println("Introduce los días de baja que quieres añadir");
				numero = scanner.nextInt();

				i.contratopersona.d.annadirDiasDeBaja(mes, anno, numero);

				// Se suma a los dí­as de baja los dí­as que introduzca el usuario.

			}

		}
		scanner.close();
	}

	//Constructor que imprime por pantalla los datos de los empleados que están dados de alta
	public static void listadoEmpleados(ArrayList<Persona> miEmpresa) {

		System.out.println("----------------------------------");
		System.out.println("LISTADO DE EMPLEADOS DADOS DE ALTA");
		System.out.println("----------------------------------");

		for (Persona i : miEmpresa) { //Se recorre el ArrayList buscando a los empleados que están dados de alta
			if (i.contratopersona.salarioBase != 0 || i.contratopersona.fechaBaja == null
				|| (i.contratopersona.fechaBaja.getMes() >= 5 && i.contratopersona.fechaBaja.getAnno() >= 2022)) {
					
				//Se imprime por pantañña el nombre, puesto y la fecha de alta
				System.out.println("Nombre: " + i.nombre);
				System.out.println("Puesto: " + i.contratopersona.puesto);
				if (i instanceof Administracion) {
				System.out.println("Tipo de trabajo: Administración");
			} else if (i instanceof Tecnico) {
				if (i instanceof JefeDeGrupo) {
					System.out.println("Tipo de trabajo: Jefe de grupo");
				} else {
					System.out.println("Tipo de trabajo: Técnico");
				}

				System.out.println("Fecha de Alta: " + i.contratopersona.fechaAlta);
			}

		}

	}
	}

	//Constructor que imprime por pantalla los datos de los empleados que están dados de baja
	public static void listadoEmpleadosBaja(ArrayList<Persona> miEmpresa) {

		System.out.println("----------------------------------");
		System.out.println("LISTADO DE EMPLEADOS DADOS DE BAJA");
		System.out.println("----------------------------------");

		for (Persona i : miEmpresa) { //Se recorre el ArrayList buscando a los empleados que están dados de baja
			if (i.contratopersona.salarioBase != 0
				|| (i.contratopersona.fechaBaja.getMes() < 5 && i.contratopersona.fechaBaja.getAnno() == 2022)
				|| i.contratopersona.fechaBaja.getAnno() < 2022) {
				
				//Se imprime por pantañña el nombre, puesto y la fecha de baja
				System.out.println("Nombre:" + i.nombre);
				System.out.println("Puesto: " + i.contratopersona.puesto);
				if (i instanceof Administracion) {
					System.out.println("Tipo de trabajo: Administración");
				} else if (i instanceof Tecnico) {
					if (i instanceof JefeDeGrupo) {
						System.out.println("Tipo de trabajo: Jefe de grupo");
					} else {
						System.out.println("Tipo de trabajo: Técnico");
					}
				}
				System.out.println("Fecha de Baja: " + i.contratopersona.fechaAlta);

			}

		}

	}

	//Constructor que imprime por pantalla un listado con los datos de los empleados de administración
	public static void listadoAdministracion(ArrayList<Persona> miEmpresa) {

		System.out.println("--------------------------------------");
		System.out.println("LISTADO DE EMPLEADOS DE ADMINISTRACIÓN");
		System.out.println("--------------------------------------");

		Administracion admin;
		for (Persona i : miEmpresa) { //Se recorre el ArrayList hasa encontrar personas que sean de la administración
			if (i instanceof Administracion) {
				admin = (Administracion) i;
				//Se imprime por pantalla el nombre y su puesto
				System.out.println("Nombre:" + admin.nombre);
				System.out.println("Puesto: " + admin.contratopersona.puesto);
			}

		}

	}

	//Constructor que imprime por pantalla un listado con los datos de los empleados pertencecientes a un equipo
	public static void listadoEquipos(ArrayList<Persona> miEmpresa) {

		System.out.println("------------------");
		System.out.println("LISTADO DE EQUIPOS");
		System.out.println("------------------");

		for (Persona i : miEmpresa) {//Se recorre el ArrayList
			int k = 0;
			if (i instanceof JefeDeGrupo) {//Se buscan a los jefes de grupo para encontrar el nombre de equipo
				JefeDeGrupo jefe;
				jefe = (JefeDeGrupo) i;
				System.out.println("Nombre del equipo: " + jefe.nombreEquipo);
				for (Tecnico j : jefe.equipo) { //Se buscan a los técnicos pertenecientes a ese equipo
					k++;
					//Se imprimen por pantalla los nombres de los técnicos que pertenecen a ese equipo
					System.out.println("Técnico " + k + ": " + j.nombre); 
				}
			}
		}
	}

	//Constructor que genera la nómina de una persona del ArrayList
	public static void generarNomina(ArrayList<Persona> miEmpresa) {

		System.out.println("---------------");
		System.out.println("GENERAR NÓMINA");
		System.out.println("---------------");

		//Se pide el dni de la persona a la que se le quiere generar la nómina
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduzca el DNI del empleado:");
		String DNI = teclado.nextLine();

		for (Persona i : miEmpresa) {//Se recorre el ArrayList buscando el dni introducido
			if (DNI == i.DNI) {
				//Una vez encontrado se pide el mes y el año
				System.out.println("Introduzca el mes:");
				int mes = teclado.nextInt();
				System.out.println("Introduzca el año");
				int anno = teclado.nextInt();
				//se genera la nómina si el contrato está en vigor en el mes indicado
				i.calculoNomina(mes,anno);

			}

		}
		teclado.close();

	}

	//Constructor que genera las nóminas de todos los empleados que estén dados de alta en un mes y año determinados
	public static void gNominas(ArrayList<Persona> miEmpresa) {

		System.out.println("------------------");
		System.out.println("GESTIÓN DE NÓMINAS");
		System.out.println("-------------------");

		//Se pide introducir el mes y el año para calcular las nóminas de esa fecha
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduzca el mes:");
		int mes = teclado.nextInt();
		System.out.println("Introduzca el año");
		int anno = teclado.nextInt();
		for (Persona i: miEmpresa){ 
			i.calculoNomina(mes,anno); 
		//Se generan las nóminas de los empleados que estén dados de alta en dicho mes y año
		}
		teclado.close();

	}

	//Método que crea el archivo y escribe en él los datos del ArrayList
	public static void escribirInfo(ArrayList<Persona> miEmpresa) {
		try(ObjectOutputStream oos = //función que escribe los datos del ArrayList en un archivo
                new ObjectOutputStream(new FileOutputStream("info.out"))){
            oos.writeObject(miEmpresa);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionNominas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionNominas.class.getName()).log(Level.SEVERE, null, ex);
        }

	}

	//Método que lee los datos del archivo (los muestra por pantalla)
	public static void leerInfo(ArrayList<Persona> miEmpresa) throws ClassNotFoundException  {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("info.out"))) {
            Object o = ois.readObject();//función para leer el archivo ya creado
            if(o instanceof ArrayList){
                ArrayList<Persona> n = (ArrayList<Persona>)o;
                System.out.println();
                for (Persona p:n) {//Se recorre el ArrayList y se imprime por pantalla los datos de todos los empleados de la empresa
					System.out.println("Estos son los empleados de la empresa:");
                    System.out.println(p);
                }
            }
        }catch(FileNotFoundException ex){//lanza una excepción si el archivo no se encuentra
            System.out.println("No encontrado");
        }catch(IOException e){//lanza una excepción si el archivo no se puede abrir
            System.out.println("No se puede abrir");
        }
	}

	public static void nomina(ArrayList<Persona> miEmpresa) {
		Scanner teclado = new Scanner(System.in);

		System.out.println("Introduce el nombre: ");
		String name = teclado.nextLine();
		System.out.println("Introduce el año:");
		int year = teclado.nextInt();
		System.out.println("Introduce el mes:");
		int month = teclado.nextInt();

		Persona personaBuscada = null;
		for (int i = 0; i < miEmpresa.size(); i++) {
			if (miEmpresa.get(i).nombre.equals(name)) {
				personaBuscada = miEmpresa.get(i);
			}
			System.out.println(miEmpresa.get(i).nombre);
		}

		double valorNomina = personaBuscada.calculoNomina(month, year);

		teclado.close();

		String nombreArchivo = year + "_" + month + "_" + name + ".nomina.txt";

		try {
			FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
			DataOutputStream salida = new DataOutputStream(fileOut);
			salida.writeDouble(valorNomina);
			salida.close();
		} catch (IOException e) {
			System.out.println("Imposible abrir el archivo para escribir");
		}


	}

	public static void nominas(ArrayList<Persona> miEmpresa) throws FileNotFoundException {
		/* ArrayList<Contrato> misContratos = new ArrayList<>();
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduzca el mes:");
		int mes = teclado.nextInt();
		System.out.println("Introduzca el año");
		int anno = teclado.nextInt();
		for (Persona i : miEmpresa){
			misContratos.add(i.contratopersona);
		}
		for (Persona i: miEmpresa){
			i.calculoNomina(mes,anno);
			/**try ( PrintWriter pw = new PrintWriter("contrato1.txt")) {
				// Escribo en un archivo los datos de un contrato.
				NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es","es");
				Contrato c1 = i.contratopersona;
				pw.println("Contrato de ejemplo");
				pw.println("---------------------------------------");
				pw.println("| Puesto: " + c1.puesto);
				pw.println("---------------------------------------");
				pw.println("| Salario base: " + nf.format(c1.salarioBase));
				pw.println("|");
				pw.println("----------------------------------------");
				pw.println("|            Horas extra               |");
				pw.println("----------------------------------------");
				for (HrsExtra he: c1.c.HE) {
					pw.println("| Mes: " + he.getMes() + " Año: " + he.getAnno()
							+ " Cantidad: " + he.getNumero());
				}
				pw.println("----------------------------------------");
	
			} catch (FileNotFoundException ex) {
				System.out.println("No se pudo crear el archivo.");
			}	**/
			
		Scanner teclado = new Scanner(System.in);

		System.out.println("Introduce el mes: ");
		int month = teclado.nextInt();
		System.out.println("Introduce el año: ");
		int year = teclado.nextInt();

		Persona personaBuscada = null;
		for (int i=0; i<miEmpresa.size(); i++){
			personaBuscada = miEmpresa.get(i);
			double valorNomina = personaBuscada.calculoNomina(month, year);
			String nombreArchivo = year + "_" + month + "_" + personaBuscada.nombre + ".nomina.txt";

		try {
			FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
			DataOutputStream salida = new DataOutputStream(fileOut);
			salida.writeDouble(valorNomina);
			salida.close();
		} catch (IOException e) {
			System.out.println("Imposible abrir el archivo para escribir");
		}
		}

		teclado.close();

		}
		//teclado.close();
		

	public boolean alta (ArrayList<Persona> miEmpresa){
				
		return false;

	}
	

public static void NominaTextoArchivo(ArrayList<Persona> miEmpresa){


		System.out.println("------------------");
		System.out.println("IMPRESIÓN DE TEXTO A ARCHIVO.");
		System.out.println("-------------------");


System.out.println("Introduzca el nombre de la persona de la que desea guardar la nómina.");	

      for(Persona i: miEmpresa){//Para cualquier persona incluida en el array
		Calendar FechaActual = new GregorianCalendar();
    	try {
		
		
	
        File Nomina = new File (FechaActual.get(Calendar.YEAR) +"_" +FechaActual.get(Calendar.MONTH)+"_"+i.nombre+".nomina.txt"); //buscamos la persona con el año, el mes y su nombre
              //Creamos un archivo nombrado como la fecha actual y el nombre de la persona de la que se pide la nómina.   
         if (Nomina.createNewFile()) {
        System.out.println("Archivo creado: " + Nomina.getName());//Imprimimos por pantalla que está creado (y su nombre)
      } else {
        System.out.println("Este archivo ya existe."); //Si ya existe, imprimimos que ya existe.
      }
    } catch (IOException e) {
      System.out.println("Ha ocurrido un error");
    }
      

   try ( ObjectOutputStream oos
                = new ObjectOutputStream(new FileOutputStream(FechaActual.get(Calendar.YEAR) +"_" +FechaActual.get(Calendar.MONTH)+"_"+i.nombre+".nomina.txt"))) {
            oos.writeObject(i.contratopersona);
//Seleccionamos como archivo en el que escribir la nómina el que acabamos de crear y escribimos todo lo obtenido con gNóminas.
        } catch (FileNotFoundException ex) {
           System.out.println("No se ha encontrado el archivo."); // se lanza una excepción en caso de que no se encuentre el archivo.
        } catch (IOException ex) {
           System.out.println("No se puede abrir el archivo."); //Se lanza otra excepción en caso de que no se pueda abrir.
        }

	  }
    





            }
			


}