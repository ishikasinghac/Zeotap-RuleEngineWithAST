package com.rule.engine.ast.model;

import java.util.Map;

public class EvaluateRuleRequest {
    private Node ast;
    private Map<String, Object> data;

    public EvaluateRuleRequest(Node ast, Map<String, Object> data) {
        this.ast = ast;
        this.data = data;
    }

    public EvaluateRuleRequest() {
    }

    public Node getAst() {
        return ast;
    }

    public void setAst(Node ast) {
        this.ast = ast;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
