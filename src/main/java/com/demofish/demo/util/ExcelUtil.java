package com.demofish.demo.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ExcelUtil 类
 *
 * @author lzp
 * @version 1.0
 * @date 2020/5/17 15:16
 */
public class ExcelUtil {


    public static List<List<String>> excelToStr(File file, int sheetIndex) throws IOException {
        List<List<String>> mapList = new ArrayList<>();
        Workbook wb;

        try {
            wb = new XSSFWorkbook(file);
        } catch (Exception e) {
            wb = new HSSFWorkbook(new FileInputStream(file));
        }

        Sheet sheet = wb.getSheetAt(sheetIndex);

        for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            List<String> list = new ArrayList<>();
            if (row != null) {
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        list.add(cell.getStringCellValue());
                    } else {
                        list.add(String.valueOf(cell));
                    }
                }
                mapList.add(list);
            }
        }
        return mapList;
    }



    /**
     * 生成临时文件
     * @param multipartFile 文件类型
     * @return 返回存储的文件
     */
    public static File tempFile(MultipartFile multipartFile) throws IOException {
        // 用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()), Prefix(multipartFile));
        // MultipartFile to File
        multipartFile.transferTo(excelFile);
        return excelFile;
    }

    /**
     * 获取文件后缀
     * @param multipartFile 文件类型
     * @return 文件后缀
     */
    public static String Prefix(MultipartFile multipartFile){
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        return prefix;
    }

    /**
     * 删除临时文件
     * @param files 文件
     */
    public static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
