package funcioneshash;

import java.io.File;

public class FuncionesHash {
 
    public static void main(String[] args)  {
        int matricula[] = new int[100];
        int index[] = new int[100];
        File nfile = new File("C:/Users/alber/Escritorio/Proyectos/FuncionesHash/Matriculas_TransClaves.xls");
        if (nfile.exists()) {
            leerExcel obj = new leerExcel(nfile);
            matricula = obj.getMatriculas();
            
            //Función
            Plegamiento indi = new Plegamiento(matricula);
            //Truncamiento indi = new Truncamiento(matricula);   
            //Cuadrado indi = new Cuadrado(matricula);
            
            index=indi.getIndices();
            
            //Colisión
            ArreglosAnidados arreglo = new ArreglosAnidados(index,matricula);
            //Encadenamiento arreglo = new Encadenamiento(index,matricula);
            //DobleHash arreglo = new DobleHash(index,matricula);
            
            arreglo.imprimir();
            arreglo.eliminar(211146);
            arreglo.imprimir();
        }
    }
}
