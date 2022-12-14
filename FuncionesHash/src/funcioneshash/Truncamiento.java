package funcioneshash;

public class Truncamiento {
    int indices[] = new int[100];

    public Truncamiento(int[] datos) {
        for (int i = 0; i < datos.length; i++) {
            this.indices[i] = Elegir_digitos(datos[i])+1;
        }
    }
    
    public static int Elegir_digitos(int dig){
        int aux1, aux2;
        aux1 = ((dig/100)%10)*10;
        aux2 = dig%10;
        return (aux1 +aux2);
    }

    public int[] getIndices() {
        return indices;
    }
    
}
