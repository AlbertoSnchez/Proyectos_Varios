import java.util.ArrayList;

public class JefeDeGrupo extends Tecnico{
    //Declaración de funciones
    String nombreEquipo;
    ArrayList <Tecnico> equipo = new ArrayList<Tecnico>(); //ArrayList Equipo que contiene la subclase de técnico

    public JefeDeGrupo(String nombre, String telefono, Fecha fechanacimiento, String DNI, CuentaBancaria cuenta,
            Contrato contratopersona,double IRPF, String nombreEquipo, ArrayList<Tecnico> equipo) {
        super(nombre, telefono, fechanacimiento, DNI, cuenta, contratopersona, IRPF);
        this.nombreEquipo = nombreEquipo;
        this.equipo = equipo;
    }

    //Métodos getter de nombre de equipo y de equipo
    public String getNombreEquipo() {
        return this.nombreEquipo;
    }

    public ArrayList<Tecnico> getEquipo() {
        return this.equipo;
    }
    

    @Override
    public double calculoNomina(int mes, int anno) {//Cálculo de la nómina
        System.out.println(this.nombre + " " + this.DNI);
        System.out.println("Cuenta: " + this.cuenta.IBAN);
        System.out.println("Puesto: " + this.contratopersona.puesto);
        //s valor del trienio
        double s = this.contratopersona.salarioBase/12; //se divide el salario base entre 12 meses
        System.out.println("Salario mes: " + s);
        this.contratopersona.annadeComplementos(this.contratopersona.fechaAlta.diferenciaFechaTrienios(this.contratopersona.fechaBaja), mes, anno);
        this.contratopersona.annadeDeducciones(mes, anno, this.contratopersona.d.calculaDiasDeBaja(mes, anno));
        s += ((double) this.contratopersona.c.getTrienios() * 20);
        System.out.println("+Por trienios: " + this.contratopersona.c.getTrienios() * 20);
        s += (double) 15 * this.contratopersona.c.calculaHorasExtra(mes, anno); //se añade el valor de las horas extra
        System.out.println("+Horas extra: " + (double) 15 * this.contratopersona.c.calculaHorasExtra(mes, anno));
        s += this.contratopersona.salarioBase * 0.02 * ((double) equipo.size()-1); //Se añade el 2% adicional al salario base
        System.out.println("+Por jefe: " + this.contratopersona.salarioBase * 0.02 * ((double) equipo.size()-1)); 

        s-= (double) this.contratopersona.salarioBase*this.contratopersona.d.calculaDiasDeBaja(mes, anno)/30;
        //12% de IRPF indefinido //16% de IRPF definido


        System.out.println("-IRPF: " + s * this.IRPF);
        s*=(1-IRPF);
    
        System.out.println("-Desempleo: " + s*0.016);
        s *= 0.984; // Aportación desempleo

        System.out.println("-Contingencias: " + s*0.047);
        s *= 0.953; // Aportación por contingencias comunes

        System.out.println("A percibir: " + s);

        return s;
    }


    

}
