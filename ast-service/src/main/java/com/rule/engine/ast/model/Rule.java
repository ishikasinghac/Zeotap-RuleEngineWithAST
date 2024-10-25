package com.rule.engine.ast.model;

import org.springframework.data.annotation.Id;

public class Rule {
    @Id
    private String id;
    private Node ast;  // Root node of the AST
    private String ruleString;

    public Rule(Node ast, String ruleString) {
        this.ast = ast;
        this.ruleString = ruleString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Node getAst() {
        return ast;
    }

    public void setAst(Node ast) {
        this.ast = ast;
    }

    public String getRuleString() {
        return ruleString;
    }

    public void setRuleString(String ruleString) {
        this.ruleString = ruleString;
    }
}
