package com.invocker.customrecyclerview_math.activities.modelgame;

public class Word {
    private String operator;
    private String result;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Word(String operator, String result) {
        this.operator = operator;
        this.result = result;
    }
}
