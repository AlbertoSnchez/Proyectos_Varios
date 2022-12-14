package MexBank;

public class CuentaBase {
    private double montoActual;

    protected CuentaBase(double apertura) {
        this.montoActual = apertura;
        System.out.println("Su cuenta se abrió correctamente con un monto de: $"+apertura+"\n");
    }
    
    public void depositar(double cantidad){
        this.montoActual += cantidad;
    }

    public double getMontoActual() {
        return montoActual;
    }

    public void setMontoActual(double montoActual) {
        this.montoActual = montoActual;
    }
    
    
}
