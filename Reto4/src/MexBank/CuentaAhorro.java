package MexBank;

public class CuentaAhorro extends CuentaBase{
    
    public CuentaAhorro(double apertura) {
        super(apertura);
    }
    public void invertir(){
        double rendimiento, nuevoMonto;
        rendimiento = super.getMontoActual()*0.1;
        System.out.println("Usted ha generado un rendimiento del 10% de: $"+rendimiento);
        nuevoMonto = super.getMontoActual() + rendimiento;
        super.setMontoActual(nuevoMonto);
    }
}
