import java.util.ArrayList;

public class Complementos{
    int trienios;
    ArrayList<HrsExtra> HE;


    public Complementos(){
        trienios = 0;
        HE = new ArrayList<>();
    }

    void annadirHorasExtra(int mes, int anno, int numero){
        HrsExtra he = new HrsExtra(mes, anno, numero);
        HE.add(he);
    }
    
    int calculaHorasExtra (int mes, int anno){
        //Devuelve la suma de todas las horas extra de ese mes y año.
        int num = 0;
        for(HrsExtra h : HE){
            if(h.getMes() == mes && h.getAnno() == anno){
                num = h.getNumero();
            }
        }
        return num;
    }


    public int getTrienios() {
        return this.trienios;
    }

    public void setTrienios(int trienios) {
        this.trienios = trienios;
    }



    
}
