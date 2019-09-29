package com.demofish.demo.model;


import com.demofish.demo.Bean.MultipleChoice;
import com.demofish.demo.Bean.SingleChoice;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class ResultMap extends HashMap<String, Object> {
    public ResultMap() {
    }

    public ResultMap SingleChoices(List<SingleChoice> singleChoices) {
        this.put("SingleChoice", singleChoices);
        return this;
    }

    public ResultMap MultipleChoices(List<MultipleChoice> multipleChoices) {
        this.put("MultipleChoice", multipleChoices);
        return this;
    }
}
