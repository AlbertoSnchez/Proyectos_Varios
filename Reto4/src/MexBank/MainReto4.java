package MexBank;

import java.util.Scanner;

public class MainReto4 {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Bienvenido a MexBank");
        System.out.println("Por favor, seleccione el tipo de cuenta que desea abrir con nosotros");
        System.out.println("1)Tarjeta de Crédito\n2)Tarjeta de Débito\n3)Cuenta de Ahorro");
        int opc = leer.nextInt();
        System.out.println("\nIngrese el monto de apertura de su cuenta");
        double apertura = leer.nextDouble();
        switch (opc) {
            case 1:
                TarjetaCredito Credito = new TarjetaCredito(apertura);
                tCredito(Credito);
                break;
                
            case 2:
                TarjetaDebito Debito = new TarjetaDebito(apertura);
                tDebito(Debito);
                break;
                
            case 3:
                CuentaAhorro Ahorro = new CuentaAhorro(apertura);
                cAhorro(Ahorro);
                break;
            
            default:
                throw new AssertionError();
        }
        System.out.println("Gracias por elegirnos\n¡Vuelta pronto!");
    }
    
    public static double deposito(){
        Scanner depo = new Scanner(System.in);
        System.out.print("Ingrese la cantidad que desea depositar: $");
        double dep = depo.nextDouble();
        return dep;
    }
    
    public static void tCredito(TarjetaCredito tarCred){
        int opera;
        do {
            opera = menu("Sumar Interes",tarCred.getMontoActual());
            if (opera == 1) {
                tarCred.sumaInteres();
            } else if (opera == 2) {
                tarCred.depositar(deposito());
            }
        } while (opera == 1 || opera == 2);
        
    }
    
    public static  void tDebito(TarjetaDebito tarDeb){
        Scanner efectivo = new Scanner(System.in);
        int opera;
        do {
            opera = menu("Retirar",tarDeb.getMontoActual());
            if (opera == 1) {
                System.out.println("¿Cuánto desea retirar? ");
                tarDeb.retirar(efectivo.nextDouble());
            } else if (opera == 2) {
                tarDeb.depositar(deposito());
            }
        } while (opera == 1 || opera == 2);
        
    }
    
    public static  void cAhorro(CuentaAhorro cueAho){
        int opera;
        do {
            opera = menu("Invertir",cueAho.getMontoActual());
            if (opera == 1) {
               cueAho.invertir();
            } else if (opera == 2) {
                cueAho.depositar(deposito());
            }
        } while (opera == 1 || opera == 2);
        
    }
    
    public static int menu(String opc1, double monto){
        Scanner opcion = new Scanner(System.in);
        System.out.println("Monto actual: $"+monto);
        System.out.println("Seleccione la operación que desea realizar");
        System.out.println("1)"+opc1+"\n2)Depositar\n3)Salir");
        int opc=opcion.nextInt();
        System.out.println("");
        return opc;
    }
    
}
