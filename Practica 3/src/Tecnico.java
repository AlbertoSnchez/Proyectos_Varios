

public class Tecnico extends Persona {

public Tecnico(String nombre, String telefono, Fecha fechanacimiento, String DNI, CuentaBancaria cuenta,
            Contrato contratopersona, double IRPF) {
        super(nombre, telefono, fechanacimiento, DNI, cuenta, contratopersona, IRPF);

    }




    @Override
    public double calculoNomina(int mes, int anno) { // Cálculo de la nómina
        System.out.println(this.nombre + " " + this.DNI);
        System.out.println("Cuenta: " + this.cuenta.IBAN);
        System.out.println("Puesto: " + this.contratopersona.puesto);
        double s = this.contratopersona.salarioBase / 12; // se divide el salario base entre 12 meses
        System.out.println("Salario mes: " + s);
        this.contratopersona.annadeComplementos(
                this.contratopersona.fechaAlta.diferenciaFechaTrienios(this.contratopersona.fechaBaja), mes, anno);

        s += ((double) this.contratopersona.c.getTrienios() * 20);
        System.out.println("+Por trienios: " + (double) this.contratopersona.c.getTrienios() * 20);
        s += (double) 15 * this.contratopersona.c.calculaHorasExtra(mes, anno); //se añade el valor de las horas extra
        System.out.println("+Horas extra: " + (double) 15 * this.contratopersona.c.calculaHorasExtra(mes, anno));

        s -= this.contratopersona.salarioBase * this.contratopersona.d.calculaDiasDeBaja(mes, anno) / 30;

        // 5% de IRPF indefinido, 7% definido
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
