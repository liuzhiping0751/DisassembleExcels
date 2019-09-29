package com.demofish.demo.Bean;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SingleChoice implements Serializable {

    //题目
    String singleTopic;


    String singleChoiceA;

    String singleChoiceB;

    String singleChoiceC;

    String singleChoiceD;

    String rightAnswers;


    @Override
    public String toString() {
        return "SingleChoice{" +
                "题目：'" + singleTopic + '\'' +
                ", A:'" + singleChoiceA + '\'' +
                ", B:'" + singleChoiceB + '\'' +
                ", C:'" + singleChoiceC + '\'' +
                ", D:'" + singleChoiceD + '\'' +
                ", 正确答案:'" + rightAnswers + '\'' +
                '}';
    }
}
