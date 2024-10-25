package com.rule.engine.ast.model;

public class CreateRuleRequest {
    private String ruleString;

    public CreateRuleRequest(String ruleString) {
        this.ruleString = ruleString;
    }

    public CreateRuleRequest() {
    }

    public String getRuleString() {
        return ruleString;
    }

    public void setRuleString(String ruleString) {
        this.ruleString = ruleString;
    }
}
