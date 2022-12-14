public class HrsExtra {
    int mes, anno, numero;


    public HrsExtra(int mes, int anno, int numero) {
        this.mes = mes;
        this.anno = anno;
        this.numero = numero;
    }

   public int getMes() {
    return this.mes;
}


   public int getAnno() {
        return this.anno;
    }

    public int getNumero() {
        return this.numero;
    }
    public void setMes(int mes) {
        if(mes>=1 && mes <=12){
            this.mes = mes;
        }

    }
    public void setAnno(int anno) {
        this.anno = anno;
    }






}
