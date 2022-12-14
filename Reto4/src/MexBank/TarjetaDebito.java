package MexBank;

public class TarjetaDebito extends CuentaBase {
    
    public TarjetaDebito(double apertura) {
        super(apertura);
    }
    public void retirar(double cantidad){
        if (cantidad > super.getMontoActual()) {
            System.out.println("No se puede retirar una cantidad mayor a la disponible en la cuenta");
        } else {
            System.out.println("Usted ha retirado la cantidad de: $"+cantidad);
            double nuevoMonto = super.getMontoActual()-cantidad;
            super.setMontoActual(nuevoMonto);
        }
    }
}
