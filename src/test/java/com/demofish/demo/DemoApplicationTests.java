package com.demofish.demo;

import com.demofish.demo.Bean.MultipleChoice;
import com.demofish.demo.Bean.SingleChoice;
import com.demofish.demo.Service.ExcelService;
import com.demofish.demo.model.ResultMap;
import com.demofish.demo.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


    @Autowired
    ExcelService excelService;

    @Test

    public void contextLoads() {
    }

    //读取单个单元格
    @Test
    public void testRead() throws Exception {
        //Excel文件
        //ResourceUtils.getFile("classpath:web-info.xls")

        File file = new File("C:\\Users\\Administrator\\Desktop\\test.xlsx");
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(file);
        } catch (Exception e) {
            wb = new HSSFWorkbook(new FileInputStream(file));
        }

        //Excel工作表
        Sheet sheet = wb.getSheetAt(0);

        //表头那一行
        Row titleRow = sheet.getRow(0);

        //表头那个单元格
        Cell titleCell = titleRow.getCell(0);

        String title = titleCell.getStringCellValue();

        System.out.println("标题是：" + title);
    }


    @Test
    public void testReadList() {


        List<MultipleChoice> multipleChoices = new ArrayList<>();
        List<SingleChoice> singleChoices = new ArrayList<>();


        File file = new File("C:\\Users\\Administrator\\Desktop\\XX部-姓名-技术委员会考试题目.xlsx");

        try {
            excelService.ExeclSingle(file);



            //singleChoices.addAll((List<SingleChoice>) resultMap.get("SingleChoice"));
            //multipleChoices.addAll((List<MultipleChoice>) resultMap.get("MultipleChoice"));

        } catch (Exception e) {


        }

        System.out.println("------------------->>>>>>>>>单选题一共" + singleChoices.size() + "：\n");
        for (SingleChoice singleChoice : singleChoices) {
            System.out.println(singleChoice.toString() + "\n");
        }


        System.out.println("------------------->>>>>>>>>多选题一共" + multipleChoices.size() + "：\n");
        for (MultipleChoice multipleChoice : multipleChoices) {
            System.out.println(multipleChoice.toString() + "\n");
        }
    }



    @Test
    public void test3() {
        File file = new File("C:\\Users\\Administrator\\Documents\\Tencent Files\\2452740632\\FileRecv\\Execldemo\\ExcelModel\\test.xlsx");
        List<List<String>> textExcel = new ArrayList<>();

        try {
            textExcel = ExcelUtil.excelToStr(file, 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (List<String> strings : textExcel) {
            if (strings.get(0).equals("d") && strings.get(0) != null) {
                SingleChoice singleChoice =
                        new SingleChoice(strings.get(1), strings.get(2), strings.get(3), strings.get(4), strings.get(5), strings.get(8));
                System.out.println(singleChoice.toString());
            } else {
                System.out.println(strings);
            }
        }


    }



}
