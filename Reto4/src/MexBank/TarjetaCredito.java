package MexBank;

public class TarjetaCredito extends CuentaBase {
    
    public TarjetaCredito(double apertura) {
        super(-1*apertura);
    }
    public void sumaInteres(){
        double interes, nuevoMonto;
        interes = super.getMontoActual()*0.15;
        System.out.println("Su interes del 15% es de: $"+(-1*interes));
        nuevoMonto = super.getMontoActual() + interes;
        super.setMontoActual(nuevoMonto);
        System.out.println("Su nuevo saldo es de: $"+nuevoMonto);
    }
}
