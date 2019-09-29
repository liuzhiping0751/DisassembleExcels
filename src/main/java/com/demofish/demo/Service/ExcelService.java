package com.demofish.demo.Service;


import com.demofish.demo.Bean.MultipleChoice;
import com.demofish.demo.Bean.SingleChoice;
import com.demofish.demo.model.ResultMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExcelService {


    /**
     * 解析Excel文件 20190828 old
     * @param file
     * @return
     * @throws Exception
     */
    public ResultMap testReadList(File file) throws Exception {
        List<MultipleChoice> multipleChoices = new ArrayList<>();
        List<SingleChoice> singleChoices = new ArrayList<>();

        Workbook wb;
        try {
            wb = new XSSFWorkbook(file);
        } catch (Exception e) {
            wb = new HSSFWorkbook(new FileInputStream(file));
        }
        Sheet sheet = wb.getSheetAt(0);

        getTitle(sheet);

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            try {
                String Types = row.getCell(0).getStringCellValue();
                if ("d".equals(Types)) {

                    String[] strings = getStrings(row);
                    singleChoices.add(new SingleChoice(strings[0], strings[1], strings[2], strings[3], strings[4], strings[7]));

                } else if ("f".equals(Types)) {

                    String[] strings = getStrings(row);
                    multipleChoices.add(new MultipleChoice(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5], strings[6], strings[7]));
                } else {
                    System.out.println("------------------->>>>>>>>>未知题目");
                }
            } catch (Exception e) {
                System.out.println("------------------->>>>>>>>>未知题目");
            }

        }

        ResultMap resultMap = new ResultMap();
        resultMap.SingleChoices(singleChoices);
        resultMap.MultipleChoices(multipleChoices);

        return resultMap;
    }


    public List<SingleChoice> ExeclSingle(File file) throws Exception {

        List<SingleChoice> singleChoices = new ArrayList<>();


        Workbook workbook;

        try {
            workbook = new XSSFWorkbook(file);
        } catch (Exception e) {
            workbook = new HSSFWorkbook(new FileInputStream(file));
        }

        Sheet sheets = workbook.getSheetAt(0);


        for (int i = 1; i < sheets.getPhysicalNumberOfRows(); i++) {
            Row row = sheets.getRow(i);



            try {

                String[] strings = getStrings(row);
                System.out.println(strings);

            } catch (Exception e) {
                System.out.println("------------------->>>>>>>>>未知题目");
            }

        }

        return singleChoices;
    }


    private void getTitle(Sheet sheet) {
        //表头那一行
        Row titleRow = sheet.getRow(0);

        //表头那个单元格
        Cell titleCell = titleRow.getCell(0);

        Tieles = titleCell.getStringCellValue();
    }
    public String Tieles;


    /**
     *
     * @param row
     * @return 格式化单元格
     */
    private String[] getStrings(Row row) {
        String[] strings = new String[10];
        for (int j = 0; j < strings.length; j++) {
            row.getCell(j + 1).setCellType(CellType.STRING);
            strings[j] = row.getCell(j + 1).getStringCellValue();
        }
        return strings;
    }




    /**
     * 生成临时文件
     * @param multipartFile
     * @return
     * @throws Exception
     */
    public File linshi(MultipartFile multipartFile) throws Exception {
        // 用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()), Prefix(multipartFile));
        // MultipartFile to File
        multipartFile.transferTo(excelFile);
        return excelFile;
    }

    /**
     * 获取文件后缀
     * @param multipartFile
     * @return
     */
    public String Prefix(MultipartFile multipartFile){
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        return prefix;
    }

    /**
     * 删除临时文件
     * @param files
     */
    public void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }


}



