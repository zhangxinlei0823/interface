package com.autotest.api.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;

public class ExcelUtil {
    //根据流水线代码封装属性和方法
    //封装属性
    String filePath;
    private Workbook wb = null;
    private InputStream inputStream = null;

    //把路径做成一个构造方法，实例化对象实进行传参
    public ExcelUtil(String filePath){
        this.filePath = filePath;
    }

    //封装一个得到wb对象的方法
/**
 * create by: zhangshao
 * @description: 用于得到Workbook类型的对象，方便后边使用
 * create time: 2019-08-12 14:33
 * @params none
 * @return Workbook
 */
    public Workbook getWb(){
        try {
            //流（输入流与输出流，java代码把数据写到磁盘是输出流，java代码把磁盘上的数据读取是输入流）
            inputStream = new FileInputStream(filePath);
            //判断excel的类型
            if (filePath.endsWith(".xlsx")) {
                wb = new XSSFWorkbook(inputStream);
            } else {
                wb = new HSSFWorkbook(inputStream);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return wb;
    }
/**
 * create by: 17600
 * description: TODO
 * create time: 2019-08-12 14:13
 * @params int index
 * @return Sheet
 */
    //封装一个方法为了得到sheet
    public Sheet getSheet(int index){
        Sheet sheet = null;
        try{
            wb = getWb();
            sheet = wb.getSheetAt(index);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sheet;
    }

    //封装一个方法得到cell
    /*
    public Object  getCellValue(int index,int rownum,int cellnum){
        Object result = null;
        try{
            Sheet sheet = getSheet(index);
            Row row = sheet.getRow(rownum);
            Cell cell = row.getCell(cellnum);
            result = getCellTypeGetCellValue(cell);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }*/
/**
 * create by: 17600
 * description: TODO
 * create time: 2019-08-12 14:14
 * @params int index,int rownum,int cellnum
 * @return Object
 */
    public Object getCellValue(int index,int rownum,int cellnum){
        //初始化返回值
        Object result = null;
        try{
            //依据sheet,行,列下标,得到单元格值
            Row row = getSheet(index).getRow(rownum);
            Cell cell = row.getCell(cellnum);
            result = fromCellTypeGetCellValue(cell);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * create by: 17600
     * description: TODO
     * create time: 2019-08-12 14:14
     * @params Cell cell
     * @return Object
     */
    //封装一个方法根据单元格类型得到单元格的值
    public  Object fromCellTypeGetCellValue(Cell cell){

        //初始化返回值
        Object value = null;

        try{
            //整个代码块依据单元格类型取对应的值
            if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
                value = "";

            }else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                value = cell.getNumericCellValue();

            }else if(cell.getCellType()==Cell.CELL_TYPE_STRING){
                value = cell.getStringCellValue().trim();

            }else if(cell.getCellType()==Cell.CELL_TYPE_FORMULA){
                value = cell.getCellFormula();

            }else if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN){
                value = cell.getBooleanCellValue();

            } else if(cell.getCellType()==Cell.CELL_TYPE_ERROR){
                value = "";
            } else {
                value = cell.getDateCellValue();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return value;
    }

    //分支语句要使用else if,而不是单纯的使用if做判断，一下为错误示例
    /*public Object getCellTypeGetCellValue(Cell cell){
        Object value = null;
        try{
            if(cell.getCellType() == Cell.CELL_TYPE_BLANK){
                value = "";
            }if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                value = cell.getStringCellValue().trim();
            }if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){
                value = cell.getCellFormula();
            }if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                value = cell.getNumericCellValue();
            }if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
                value = cell.getBooleanCellValue();
            }if(cell.getCellType() == Cell.CELL_TYPE_ERROR){
                value = cell.getErrorCellValue();
            }else{
                value = cell.getDateCellValue();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return value;
    }*/
/**
 * create by: 17600
 * description: TODO
 * create time: 2019-08-12 14:15
 * @params int index
 * @return Object[][]
 */
    //封装一个方法，将excel中的数据存放在二维数组中
    public Object[][] ExcelDataToArray(int index){
        Object[][] testCaseData = null;
        try{
            Sheet sheet = getSheet(index);
            int lastRowNum = sheet.getLastRowNum();
            testCaseData = new Object[lastRowNum][10];
            for(int rowIndex = 1;rowIndex <=lastRowNum;rowIndex++){
                Row row = sheet.getRow(rowIndex);
                if(row == null){
                    continue;
                }
                for(int cellIndex = 0; cellIndex < row.getLastCellNum();cellIndex++){
                    Cell cell = row.getCell(cellIndex);
                    if(cell == null){
                        testCaseData[rowIndex -1 ][cellIndex] = null;
                    }else {
                        testCaseData[rowIndex -1 ][cellIndex] = fromCellTypeGetCellValue(cell);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return testCaseData;
    }

    public static void main(String[] args){
//        String filePath = "E:\\autotest\\app\\app_testcase.xlsx";
//        ExcelUtil excelUtil = new ExcelUtil(filePath);
//        Object result = excelUtil.getCellValue(0,1,2);
//        System.out.println(result.toString());

        //测试将excel中的数据存放在二维数组中的方法是否可用；
        String filePath = "E:\\autotest\\app\\app_testcase.xlsx";
        ExcelUtil excelUtil = new ExcelUtil(filePath);
        Object[][] object = excelUtil.ExcelDataToArray(0);
        System.out.println(object[1][1]);
        //指定一个excel路径
       /*String filePath = "E:\\autotest\\app\\app_testcase.xlsx";
       Workbook wb = null;
       InputStream inputStream = null;
       //打开指定路径的excel
       try {
           //流（输入流与输出流，java代码把数据写到磁盘是输出流，java代码把磁盘上的数据读取是输入流）
           inputStream = new FileInputStream(filePath);
           //判断excel的类型
           if(filePath.endsWith(".xlsx")){
               wb = new XSSFWorkbook(inputStream);
           }else{
               wb = new HSSFWorkbook(inputStream);
           }
           //指定一个sheet
           Sheet sheet = wb.getSheetAt(0);

           //指定一个row
           Row row = sheet.getRow(0);

           //指定一个cell
           Cell cell = row.getCell(0);

           //假设cell单元格类型是String
           String str = cell.getStringCellValue();

           System.out.println(str);
       } catch (Exception e) {
           e.printStackTrace();
       }*/
    }
}
