package com.demofish.demo.Bean;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MultipleChoice implements Serializable {

    //题目
    String MultipleTopic;
    String MultipleChoiceA;
    String MultipleChoiceB;
    String MultipleChoiceC;
    String MultipleChoiceD;
    String MultipleChoiceE;
    String MultipleChoiceF;
    String rightAnswers;

    @Override
    public String toString() {
        return "MultipleChoice{" +
                "题目：'" + MultipleTopic + '\'' +
                ", A:'" + MultipleChoiceA + '\'' +
                ", B:'" + MultipleChoiceB + '\'' +
                ", C:'" + MultipleChoiceC + '\'' +
                ", D:'" + MultipleChoiceD + '\'' +
                ", E:'" + MultipleChoiceE + '\'' +
                ", F:'" + MultipleChoiceF + '\'' +
                ", 正确答案：'" + rightAnswers + '\'' +
                '}';
    }
}
