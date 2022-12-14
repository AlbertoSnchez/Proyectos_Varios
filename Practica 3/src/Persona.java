import java.io.Serializable;

public abstract class Persona implements Serializable{ 
    //Declaración de funciones de la clase abstracta
    String nombre;
    String telefono;
    Fecha fechanacimiento;
    String DNI;
    CuentaBancaria cuenta;
    Contrato contratopersona;
    double IRPF;

    public Persona(String nombre, String telefono, Fecha fechanacimiento, String DNI, CuentaBancaria cuenta, Contrato contratopersona, double IRPF) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechanacimiento = fechanacimiento;
        this.DNI = DNI;
        this.cuenta = cuenta;
        this.contratopersona = contratopersona;
        this.IRPF = IRPF;
    }


    //Función abstracta del cálculo de las nóminas de los trabajadores
    abstract public double calculoNomina(int mes, int anno);



}
