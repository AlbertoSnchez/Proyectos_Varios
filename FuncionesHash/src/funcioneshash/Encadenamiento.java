package funcioneshash;

import java.util.LinkedList;


public class Encadenamiento {
    LinkedList<Integer>[] hash = new LinkedList[100];

    public Encadenamiento(int[] indice, int[] matricula) {
        for (int i = 0; i < 100; i++) {
            hash[i] = new LinkedList<Integer>();
        }
        for (int i = 0; i < 100; i++) {
                hash[indice[i]].add(matricula[i]);
        }
    }
    
    public void imprimir(){
        for (int i = 0; i < hash.length; i++) {
            System.out.print("\n" + i + "\t");
            for (int j = 0; j < hash[i].size(); j++) {
                System.out.print("[" + hash[i].get(j) + "]->");
            }
        }
    }
    
    public void eliminar(int matri){
        int i=-1,j = 0;
        boolean salir = true;
        while(salir) {
            i++;
            for ( j = 0; j < hash[i].size(); j++) {
                if (matri == hash[i].get(j)){
                    salir = false;
                    break;
                }
            }
        }
        hash[i].remove(j);
    }
}
