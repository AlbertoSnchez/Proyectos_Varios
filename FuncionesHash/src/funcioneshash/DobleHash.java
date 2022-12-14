package funcioneshash;

public class DobleHash {
    static int hash[] = new int[100];
    
    public DobleHash(int[] indice, int[] matricula){
        int i = 0;
        do {
            if(hash[indice[i]] == 0){
                hash[indice[i]] = matricula[i];
            }else{
                int ind=(indice[i]%100)+1;
                if(hash[ind] == 0){
                    hash[ind] = matricula[i];
                    indice[i] = ind;
                }else{
                    do{
                        ind=(ind%100)+1;
                        if(ind >= 100)
                            ind = 0;
                        if(hash[ind] == 0)
                            break;
                    }while(true);
                    indice[i] = ind;
                    hash[ind] = matricula[i];
                }
            }
            i++;
        }while(i<100);
    }    
    public void imprimir(){
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " " + hash[i]);
        }
    }
}
