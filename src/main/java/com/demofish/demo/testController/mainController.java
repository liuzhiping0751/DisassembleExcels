package com.demofish.demo.testController;


import com.demofish.demo.Bean.MultipleChoice;
import com.demofish.demo.Bean.SingleChoice;
import com.demofish.demo.Service.ExcelService;
import com.demofish.demo.model.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class mainController {


    @Autowired
    ExcelService excelService;

    List<MultipleChoice> multipleChoices;
    List<SingleChoice> singleChoices;

    @RequestMapping({"/", "/page"})
    public String page() {

        return "/daoru";

    }


    @ResponseBody
    @RequestMapping("/upload.do")
    public String upload(@RequestParam("file_data") MultipartFile[] myfiles, HttpServletRequest request, @RequestParam("file_data") MultipartFile multipartFile) throws IOException {


        multipleChoices = new ArrayList<>();
        singleChoices = new ArrayList<>();


        try {

            if (excelService.Prefix(multipartFile).equals(".xlsx") || excelService.Prefix(multipartFile).equals(".xls")) {

                File excelFile = excelService.linshi(multipartFile);
                ResultMap resultMap = excelService.testReadList(excelFile);
                singleChoices.addAll((List<SingleChoice>) resultMap.get("SingleChoice"));
                multipleChoices.addAll((List<MultipleChoice>) resultMap.get("MultipleChoice"));

                excelService.deleteFile(excelFile);


                System.out.println("------------------->>>>>>>>>单选题一共" + singleChoices.size() + "：\n");
                for (SingleChoice singleChoice : singleChoices) {
                    System.out.println(singleChoice.toString() + "\n");
                }


                System.out.println("------------------->>>>>>>>>多选题一共" + multipleChoices.size() + "：\n");
                for (MultipleChoice multipleChoice : multipleChoices) {
                    System.out.println(multipleChoice.toString() + "\n");
                }

            } else {
                System.out.println("------------------------->>>>>>格式不对，请上传正确格式！！");
            }


        } catch (Exception e) {
            e.fillInStackTrace();
        }


        return "{\"Data\": \"ok\"}";
    }


    @RequestMapping("/preview")
    public String preview(ModelMap modelMap) {

        try {

            modelMap.put("Timu", excelService.Tieles);
            modelMap.put("singleChoices", singleChoices);
            modelMap.put("multipleChoices", multipleChoices);
        } catch (Exception e) {
            e.fillInStackTrace();
        }


        return "/Text_paper";
    }


    @RequestMapping("/two_list")
    public String twoList() {


        return "/daoru成功";
    }


}
