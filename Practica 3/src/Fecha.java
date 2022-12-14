public class Fecha {
    //Declaración de funciones
    private int dia;
    private int mes;
    private int anno;

    public Fecha(int dia, int mes, int anno) throws Exception {
        if (! esFechaValida(dia, mes, anno)) {
            throw new Exception("La fecha no es válida.");
        }

        this.dia = dia;
        this.mes = mes;
        this.anno = anno;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) throws Exception {
        if (! esFechaValida(this.dia, this.mes, anno)) {
            throw new Exception("El valor de año no es válido.");
        }

        this.anno = anno;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) throws Exception {
        if (! esFechaValida(dia, this.mes, this.anno)) {
            throw new Exception("El valor de día no es válido.");
        }
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }


    public int diferenciaFechaTrienios(Fecha otra) {
        return (Math.abs(this.anno-otra.anno)/3);
    }

    public void setMes(int mes) throws Exception {
        if (! esFechaValida(this.dia, mes, this.anno)) {
            throw new Exception("El valor de mes no es válido.");
        }
        this.mes = mes;
    }

    public boolean esBisiesto(int anno) {
        if (anno % 4 == 0 && anno % 100 != 0 || anno % 400 == 0) {
            return true;
        }
        return false;
    }

    private boolean esFechaValida(int dia, int mes, int anno) {
        if (dia < 1 || dia > 31) {
            return false;
        }
        if (mes < 1 || mes > 12) {
            return false;
        }
        if (anno < 1582) {
            return false;
        }
        return true;
    }

    public int diasMes(int mes, int anno) {
        int dias;
        switch (mes) {
            case 1, 3, 5, 7, 8, 10, 12:
                dias = 30;
                break;
            case 2:
                if (esBisiesto(this.anno)) {
                    dias = 29;
                } else {
                    dias = 28;
                }
                break;
            default:
                dias = 30;
        }
        return dias;
    }

    public boolean esAnterior(Fecha otra) {
        if (this.anno < otra.anno) {
            return true;
        }
        if (this.anno == otra.anno && this.mes < otra.mes) {
            return true;
        }
        if (this.anno == otra.anno && this.mes == otra.mes && 
                this.dia < otra.dia) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + dia + "/" + mes + "/" + anno;
    }

    
}
