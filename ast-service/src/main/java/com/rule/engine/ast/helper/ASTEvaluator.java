package com.rule.engine.ast.helper;

import com.rule.engine.ast.model.Node;

import java.util.Map;

public class ASTEvaluator {

    public static boolean evaluateRule(Node ast, Map<String, Object> data) {
        if (ast == null) {
            throw new IllegalArgumentException("AST cannot be null");
        }

        if ("operator".equals(ast.getType())) {
            boolean leftResult = evaluateRule(ast.getLeft(), data);
            boolean rightResult = evaluateRule(ast.getRight(), data);

            if ("AND".equals(ast.getValue())) {
                return leftResult && rightResult;
            } else if ("OR".equals(ast.getValue())) {
                return leftResult || rightResult;
            } else {
                throw new IllegalArgumentException("Unsupported operator: " + ast.getValue());
            }
        }

        if ("operand".equals(ast.getType())) {
            return evaluateCondition(ast.getValue(), data);
        }

        throw new IllegalArgumentException("Unknown node type: " + ast.getType());
    }

    private static boolean evaluateCondition(String condition, Map<String, Object> data) {
        String[] parts = condition.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid condition format: " + condition);
        }

        String attribute = parts[0];
        String operator = parts[1];
        String value = parts[2];

        Object attributeValue = data.get(attribute);
        if (attributeValue == null) {
            throw new IllegalArgumentException("Attribute " + attribute + " not found in data");
        }

        switch (operator) {
            case ">":
                return compareValues(attributeValue, value) > 0;
            case "<":
                return compareValues(attributeValue, value) < 0;
            case "=":
                return compareValues(attributeValue, value) == 0;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

    private static int compareValues(Object attributeValue, String value) {
        if (attributeValue instanceof Number) {
            return Double.compare(((Number) attributeValue).doubleValue(), Double.parseDouble(value));
        } else if (attributeValue instanceof String) {
            return ((String) attributeValue).compareTo(value);
        } else {
            throw new IllegalArgumentException("Unsupported attribute type: " + attributeValue.getClass().getSimpleName());
        }
    }
}
