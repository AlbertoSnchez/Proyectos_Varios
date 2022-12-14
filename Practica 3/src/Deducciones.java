import java.util.ArrayList;

public class Deducciones{
    //Declaración de funciones
    double irpf;
    ArrayList<DiasDeBaja> DDB;


    //Métodos getter y setter del IRPF
    public void setIrpf(double irpf) {
        this.irpf = irpf;
    }

    public double getIrpf() {
        return this.irpf;
    }

    //Métodos para añadir y calcular los días de baja
    void annadirDiasDeBaja(int mes, int anno, int numero){
        DiasDeBaja ddb = new DiasDeBaja(mes, anno, numero);
        DDB.add(ddb);
    }

    int calculaDiasDeBaja (int mes, int anno){
        //Devuelve la suma de todas los días de baja de ese mes y año.
        int num = 0;
        for(DiasDeBaja d : DDB){
            if(d.getMes() == mes && d.getAnno() == anno){
                num = d.getNumero();
            }
        }
        return num;
    }



}


