package funcioneshash;

public class Plegamiento {
    int indices[] = new int[100];

    public Plegamiento(int[] datos) {
        for (int i = 0; i < datos.length; i++) {
            indices[i] = Digitos_menos_significativos(datos[i])+1;
        }
    }
    public static int Digitos_menos_significativos(int k) {
        int a1,a2,a3;
        a1=k%100;
        k /= 100;
        a2=k%100;
        k /= 100;
        a3=k;
        return (a1+a2+a3)%100;
    }

    public int[] getIndices() {
        return indices;
    }
    
}
