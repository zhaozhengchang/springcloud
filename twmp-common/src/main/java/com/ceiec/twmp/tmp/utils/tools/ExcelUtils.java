package com.ceiec.twmp.tmp.utils.tools;

import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: operate excel utils
 * @create 2019-04-04 14:31
 **/
public class ExcelUtils {

    /*************************************************************************************************************************************
     ** @Description  add info into excel title
     ** @param: titleName
     ** @param: necessaryCondition
     ** @param: prompt
     ** @Return java.util.List<java.lang.String>
     ** @Author Ding
     ** @Date 2019/4/4 14:33
     **
     ************************************************************************************************************************************/
    public static List<String> setTitle(String titleName[], Integer necessaryCondition[], String prompt[]){
        List<String> fieldList = new ArrayList<>();
        for (int i = 0; i < titleName.length; i++) {
            fieldList.add(titleName[i]);
        }
        for(int i=0; i<fieldList.size(); i++){


            if(necessaryCondition.length>=i+1 && necessaryCondition[i]!=null && necessaryCondition[i] == 1){
                fieldList.set(i, "*" + fieldList.get(i));
            }
            if(prompt!=null && prompt.length>=i+1 && !StringUtils.isNullOrEmpty(prompt[i])){
                fieldList.set(i, fieldList.get(i)+ "("+prompt[i]+")");
            }
        }
        return fieldList;
    }


    /*************************************************************************************************************************************
     ** @Description build excel
     ** @param: worksheet
     ** @param: fieldList
     ** @param: workbook
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/4 14:42
     **
     ************************************************************************************************************************************/
    public static void buildTitle(HSSFSheet worksheet, List<String> fieldList) {
        /**设置单元格格式为文本格式*/
        HSSFCellStyle textStyle = worksheet.getWorkbook().createCellStyle();
        HSSFDataFormat format = worksheet.getWorkbook().createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));


        for(int i = 0; i < fieldList.size(); i++){
            //设置列宽
            worksheet.setColumnWidth(i, 5000);
            worksheet.setDefaultColumnStyle(i, textStyle);
        }

        buildHeaders(worksheet, fieldList);

    }


    /*************************************************************************************************************************************
     ** @Description build excel headers
     ** @param: worksheet
     ** @param: fieldList
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/4 14:41
     **
     ************************************************************************************************************************************/
    private static void buildHeaders(HSSFSheet worksheet, List<String> fieldList) {
        // Header字体
        Font font = worksheet.getWorkbook().createFont();

        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 单元格样�?
        HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
        headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
        headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        headerCellStyle.setFont((HSSFFont) font);
        headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);

        // 创建字段标题
        HSSFRow rowHeader = worksheet.createRow(0);
        rowHeader.setHeight((short) 500);

        for(int i = 0; i < fieldList.size(); i++){
            HSSFCell cell = rowHeader.createCell(0 + i);
            cell.setCellValue(fieldList.get(i));
        }
    }

    /*************************************************************************************************************************************
     ** @Description build excel content
     ** @param: worksheet
     ** @param: dateString
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/4 16:54
     **
     ************************************************************************************************************************************/
    public static void buildContent(HSSFSheet worksheet, String[][] dateString){
        if(dateString!=null && dateString.length>0){
            for(int rowIndex = 0; rowIndex < dateString.length ;rowIndex++){
                // 表头为第一行，故rowIndex+1
                Row dataRow = worksheet.createRow(rowIndex + 1);
                for(int cellIndex =0; cellIndex < dateString[rowIndex].length; cellIndex++){
                    Cell cell = dataRow.createCell(cellIndex);
                    cell.setCellValue(dateString[rowIndex][cellIndex]);
                }
            }
        }
    }

    /*************************************************************************************************************************************
     ** @Description write excel sheet into reponse
     ** @param: worksheet
     ** @param: fileName
     ** @param: response
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/4 16:45
     **
     ************************************************************************************************************************************/
    public static void writeExcel(HSSFSheet worksheet, String fileName, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xls");
        response.setContentType("application/x-msdownload");

        ServletOutputStream outputStream = response.getOutputStream();
        worksheet.getWorkbook().write(outputStream);
        outputStream.flush();
    }





    /**
     *
     * @description:  获取excel中的数据，统一转化为String类型，表格转换为List<List<String>>类型
     * @param: fileName
     * @param: inputStream
     * @return: java.util.List<java.util.List<java.lang.String>>
     * @author: shihsh
     * @date: 2019/3/27
     */
    public static List<List<String>> getListByExcel(String fileName, InputStream inputStream) {
        List<List<String>> list = new ArrayList<>();
        Workbook workbook = getWorkBook(fileName, inputStream);
        if (workbook == null) {
            // 为空表示文件有误
            return list;
        }

        // 第一个sheet为数据内容
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        int colNum = sheet.getRow(0).getLastCellNum();
        Row row;
        Cell cell;
        for (int i = 0; i <= rowNum; i++) {
            List<String> objList = new ArrayList<>();
            row = sheet.getRow(i);
            if (i == 0) {
                continue;
            } else if (row == null) {
                list.add(objList);
            } else {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    String obj = getCellValue(cell);
                    objList.add(obj);
                }
                for (int j = row.getLastCellNum(); j < colNum; j++) {
                    objList.add("");
                }
                list.add(objList);
            }
        }
        return list;
    }

    /**
     *
     * @description:  获取输入流转化为excel对象workbook，.xls为2003，.xlsx为2003以上版本
     * @param: fileName
     * @param: inputStream
     * @return: org.apache.poi.ss.usermodel.Workbook
     * @author: shihsh
     * @date: 2019/3/27
     */
    public static Workbook getWorkBook(String fileName, InputStream inputStream) {
        Workbook workbook = null;
        if (fileName != null && fileName.endsWith(".xls")) {
            try {
                workbook = new HSSFWorkbook(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                workbook = null;
            }
        } else if (fileName != null && fileName.endsWith(".xlsx")) {
            try {
                workbook = new XSSFWorkbook(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                workbook = null;
            }
        }
        return workbook;
    }



    /**
     *
     * @description:  获取cell中的数据，都转化为String类型
     * @param: cell
     * @return: java.lang.String
     * @author: shihsh
     * @date: 2019/3/27
     */
    public static String getCellValue(Cell cell) {
        String value = "";
        if (cell == null) {
            return value;
        }
        DecimalFormat dfDouble = new DecimalFormat("#.#######");
        CellType type = cell.getCellTypeEnum();
        if (type == CellType.STRING) {
            value = String.valueOf(cell.getStringCellValue());
        } else if (type == CellType.NUMERIC) {
            String dataFormat = cell.getCellStyle().getDataFormatString();
            System.out.println(dataFormat);
            if ("General".equals(dataFormat)) {
                value = dfDouble.format(cell.getNumericCellValue());
            } else if ("m/d/yy".equals(dataFormat)) {
                cell.getDateCellValue();
                Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                //采用统一的日期格式 yyyy-MM-dd 与DateFormatUtils保持一致
                value = DateFormatUtils.dateToString(date);
            }
        } else if(type == CellType.BOOLEAN){
            value =  cell.getBooleanCellValue() ? "true" : "false";
        }
        // 其他类型数据默认为:""
        return value;
    }

}
