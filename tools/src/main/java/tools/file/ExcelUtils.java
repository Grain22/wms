package tools.file;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author laowu
 */
public class ExcelUtils extends FileUtils {
    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    /**
     * read excel
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String[]> readExcel(String path) throws IOException {
        return readExcel(getFile(path));
    }

    /**
     * read excel
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static List<String[]> readExcel(File file) throws IOException {
        checkFileIsExcel(file);
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    int firstCellNum = row.getFirstCellNum();
                    /*为空列获取*/
                    int lastCellNum = row.getLastCellNum();
                    /*为空列不获取*/
                    /*int lastCellNum = row.getPhysicalNumberOfCells();*/
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    /*String[] cells = new String[row.getLastCellNum()];*/
                    //循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
        }
        return list;
    }

    public static void checkFileIsExcel(File file) throws IOException {
        //判断文件是否存在
        if (null == file) {
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getName();
        //判断文件是否是excel文件
        if (!fileName.endsWith(XLS) && !fileName.endsWith(XLSX)) {
            throw new IOException(fileName + "不是excel文件");
        }
    }

    public static Workbook getWorkBook(File file) {
        String fileName = file.getName();
        Workbook workbook = null;
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            if (fileName.endsWith(XLS)) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith(XLSX)) {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                /*工式*/
                /*cellValue = String.valueOf(cell.getCellFormula());*/
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BLANK:
                cellValue = "";
                break;
            case ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}