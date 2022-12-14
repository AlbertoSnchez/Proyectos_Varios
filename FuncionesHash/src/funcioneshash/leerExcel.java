package funcioneshash;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class leerExcel {
    
    private int matriculas[] = new int[100];
    
    public leerExcel(File nombre){
        List datos = new ArrayList();
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(nombre));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows();
            
            
            int cols = 0; // No of columns
            int tmp = 0;

            // This trick ensures that we get the data properly even if it doesn't start from first few rows
            for(int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if(row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if(tmp > cols) cols = tmp;
                }
            }

            for(int r = 0; r < rows; r++) {
                row = sheet.getRow(r);
                if(row != null) {
                    for(int c = 0; c < cols; c++) {
                        cell = row.getCell((short)c);
                        if(cell != null) {
                            if(r<100){
                                matriculas[r]=Integer.parseInt(cell.toString());
                            }
                        }
                    }
                }
            }
        } catch(Exception ioe) {
            ioe.printStackTrace();
        }
    }//Fin del método
    
    public int[] getMatriculas(){
        return matriculas;
    }//Fin del método
    
    private void get(List listaDatos){
        for (int i = 0; i < listaDatos.size(); i++) {
            List celTempList = (List) listaDatos.get(i);
            for (int j = 0; j < celTempList.size(); j++) {
                XSSFCell xssfCell = (XSSFCell) celTempList.get(j);
                String valor = xssfCell.toString();
                System.out.println(valor + " ");
            }
        }
    }//Fin del método
}//Fin de la clase
