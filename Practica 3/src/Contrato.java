public class Contrato {
    //Declaración de funciones
    protected String puesto; 
    protected double salarioBase;
    protected Fecha fechaAlta;
    protected Fecha fechaBaja; 
    Complementos c = new Complementos();
    Deducciones d = new Deducciones();

    //Contrato indefinido
    public Contrato(String puesto, double salarioBase, Fecha fechaAlta) {
        this.puesto = puesto;
        this.salarioBase = salarioBase;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = null;
    }
    //Contrato definido
    public Contrato(String puesto, double salarioBase, Fecha fechaAlta, Fecha fechaBaja) {
        this.puesto = puesto;
        this.salarioBase = salarioBase;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
    }

    //Método para añadir complementos: trienios y horas extra
    public void annadeComplementos(int trienios, int mes, int anno){
        c.setTrienios(trienios);
        c.annadirHorasExtra(mes, anno,c.calculaHorasExtra(mes, anno));
    }

    //Método para eliminar los complementos: trienios y horas extra
    public void eliminaComplementos(){ 
        c = null;
    }

    //Método para añadir 
    public void annadeDeducciones(int mes, int anno, int numero){
        d.annadirDiasDeBaja(mes, anno, numero);
    }

    public void eliminaDeducciones(){
        d = null;
    }

    public String getPuesto() {
        return this.puesto;
    }


    public double getSalarioBase() {
        return this.salarioBase;
    }


    public Fecha getFechaAlta() {
        return this.fechaAlta;
    }


    public Fecha getFechaBaja() {
        return this.fechaBaja;
    }
    
}