package com.rule.engine.ast.helper;

import com.rule.engine.ast.model.Node;

public class RuleParser {

    public static Node parseRuleString(String ruleString) {
        ruleString = ruleString.trim();
//        if (ruleString.startsWith("(") && ruleString.endsWith(")")) {
//            ruleString = removeOutermostParentheses(ruleString);
//        }

        if (ruleString.startsWith("(") && ruleString.endsWith(")")) {
            ruleString = ruleString.substring(1, ruleString.length() - 1).trim();
        }

        int splitPos = findSplitPosition(ruleString);

        if (splitPos != -1) {
            String leftPart = ruleString.substring(0, splitPos).trim();
            String rightPart = ruleString.substring(splitPos + 3).trim(); // +3 for AND/OR length
            String operator = ruleString.substring(splitPos, splitPos + 3).trim();

            Node leftNode = parseRuleString(leftPart);
            Node rightNode = parseRuleString(rightPart);

            return new Node("operator", leftNode, rightNode, operator);
        } else {
            return parseCondition(ruleString);
        }
    }

    private static String removeOutermostParentheses(String ruleString) {
        int depth = 0;
        for (int i = 0; i < ruleString.length(); i++) {
            if (ruleString.charAt(i) == '(') depth++;
            else if (ruleString.charAt(i) == ')') depth--;

            if (depth == 0 && i < ruleString.length() - 1) {
                return ruleString;
            }
        }
        return ruleString.substring(1, ruleString.length() - 1);
    }

    private static int findSplitPosition(String ruleString) {
        int parenthesisDepth = 0;
        for (int i = 0; i < ruleString.length() - 3; i++) {
            String sub = ruleString.substring(i, i + 3);
            String sub2 = ruleString.substring(i, i + 2);
            if (ruleString.charAt(i) == '(') {
                parenthesisDepth++;
            } else if (ruleString.charAt(i) == ')') {
                parenthesisDepth--;
            }
            if (parenthesisDepth == 0 && (sub.equalsIgnoreCase("AND") || sub2.equalsIgnoreCase("OR"))) {
                return i;
            }
        }
        return -1;
    }

    private static Node parseCondition(String conditionString) {
        conditionString = conditionString.trim();

        if (conditionString.contains(">")) {
            String[] parts = conditionString.split(">");
            return new Node("operand", null, null, parts[0].trim() + " > " + parts[1].trim());
        } else if (conditionString.contains("<")) {
            String[] parts = conditionString.split("<");
            return new Node("operand", null, null, parts[0].trim() + " < " + parts[1].trim());
        } else if (conditionString.contains("=")) {
            String[] parts = conditionString.split("=");
            return new Node("operand", null, null, parts[0].trim() + " = " + parts[1].trim().replace("'", ""));
        } else {
            throw new IllegalArgumentException("Invalid condition: " + conditionString);
        }
    }
}
