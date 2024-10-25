package com.rule.engine.ast.model;

import java.util.List;

public class CombineRulesRequest {
    private List<String> ruleStrings;

    public CombineRulesRequest(List<String> ruleStrings) {
        this.ruleStrings = ruleStrings;
    }

    public CombineRulesRequest() {
    }

    public List<String> getRuleStrings() {
        return ruleStrings;
    }

    public void setRuleStrings(List<String> ruleStrings) {
        this.ruleStrings = ruleStrings;
    }
}
