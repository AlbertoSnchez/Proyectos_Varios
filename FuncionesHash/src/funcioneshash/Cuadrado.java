package funcioneshash;

public class Cuadrado {
    int indices[] = new int[100];

    public Cuadrado(int[] datos) {
        for (int i = 0; i < datos.length; i++) {
            this.indices[i] = Digitos_Centrales(datos[i]*datos[i])%100+1;
        }
    }
    
    public static int Digitos_Centrales(int dig){
        int aux1, aux2, aux3, res;
        aux1 = ((dig/1000000)%10)*100;
        aux2 = ((dig/100000)%10)*10;
        aux3 = ((dig/10000)%10);
        res = aux1 +aux2 + aux3;
        if(res<0)
            res *= -1;
        return (res);
    }

    public int[] getIndices() {
        return indices;
    }
}
