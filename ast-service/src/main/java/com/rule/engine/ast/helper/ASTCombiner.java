package com.rule.engine.ast.helper;

import com.rule.engine.ast.model.Node;

import java.util.List;

public class ASTCombiner {

    public static Node combineRules(List<Node> astList, List<String> ruleStrings) {
        if (astList == null || astList.isEmpty()) {
            throw new IllegalArgumentException("AST list cannot be null or empty");
        }

        if (astList.size() == 1) {
            return astList.get(0);
        }

        String mostFrequentOperator = findMostFrequentOperator(ruleStrings);

        Node combinedAST = astList.get(0);

        for (int i = 1; i < astList.size(); i++) {
            combinedAST = new Node("operator", combinedAST, astList.get(i), mostFrequentOperator);
        }

        return combinedAST;
    }

    private static String findMostFrequentOperator(List<String> ruleStrings) {
        int andCount = 0;
        int orCount = 0;

        for (String rule : ruleStrings) {
            andCount += countOccurrences(rule, "AND");
            orCount += countOccurrences(rule, "OR");
        }

        return (andCount >= orCount) ? "AND" : "OR";
    }

    private static int countOccurrences(String rule, String operator) {
        int count = 0;
        int index = 0;

        while ((index = rule.indexOf(operator, index)) != -1) {
            count++;
            index += operator.length();
        }

        return count;
    }
}

