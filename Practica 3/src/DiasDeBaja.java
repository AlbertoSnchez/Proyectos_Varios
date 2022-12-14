public class DiasDeBaja{
    protected int mes;
    protected int anno;
    protected int numero;


    public DiasDeBaja(int mes, int anno, int numero) {
        this.mes = mes;
        this.anno = anno;
        this.setNumero(numero);
    }


    public  void setNumero(int numero) {
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


    
}
