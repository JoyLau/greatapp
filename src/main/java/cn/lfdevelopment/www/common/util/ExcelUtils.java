package cn.lfdevelopment.www.common.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by LiuFa on 2016/10/29.
 */
public class ExcelUtils {

    /**
     * 验证EXCEL文件
     * @param filePath
     * @return
     */
    private boolean validateExcel(String filePath){
        return !(filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath)));
    }


    /**
     * 是否是97-2003的excel
     * @param fileName
     * @return
     */
    private static boolean isExcel2003(String fileName) {
        return fileName.matches("^.+\\.(?i)(xls)$");
    }


    /**
     * 是否是2007之后的excel
     * @param fileName
     * @return
     */
    private static boolean isExcel2007(String fileName) {
        return fileName.matches("^.+\\.(?i)(xlsx)$");
    }


    /**
     *  根据excel 的类型返回Workbook
     * @param filePath 文件路径名
     * @param fileName 文件名，要求带后缀名
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(String filePath, String fileName) throws IOException {
        InputStream is = new FileInputStream(filePath + fileName);
        //当excel是2003时
        if(isExcel2003(fileName)){
            return new HSSFWorkbook(is);
        }
        else{//当excel是2007时
            return new XSSFWorkbook(is);
        }

    }
}
