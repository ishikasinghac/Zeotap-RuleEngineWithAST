package com.rule.engine.ast.controller;

import com.rule.engine.ast.helper.ASTCombiner;
import com.rule.engine.ast.helper.ASTEvaluator;
import com.rule.engine.ast.helper.RuleParser;
import com.rule.engine.ast.model.*;
import com.rule.engine.ast.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rules")
public class RuleController {

    @Autowired
    private RuleRepository ruleRepository;

    @PostMapping("/create")
    public ResponseEntity<Node> createRule(@RequestBody CreateRuleRequest createRuleRequest) {
        Node ast = RuleParser.parseRuleString(createRuleRequest.getRuleString());
        Rule rule = new Rule(ast, createRuleRequest.getRuleString());
        ruleRepository.save(rule);
        return ResponseEntity.ok(ast);
    }

    @PostMapping("/combine")
    public ResponseEntity<Node> combineRules(@RequestBody CombineRulesRequest combineRulesRequest) {

        List<Node> astList = combineRulesRequest.getRuleStrings().stream()
                .map(RuleParser::parseRuleString)
                .collect(Collectors.toList());

        Node node = ASTCombiner.combineRules(astList, combineRulesRequest.getRuleStrings());

        return ResponseEntity.ok(node);
    }

    @PostMapping("/evaluate")
    public ResponseEntity<Boolean> evaluateRule(@RequestBody EvaluateRuleRequest evaluateRuleRequest) {

        Node ast = evaluateRuleRequest.getAst();
        Map<String, Object> data = evaluateRuleRequest.getData();

        boolean result = ASTEvaluator.evaluateRule(ast, data);
        return ResponseEntity.ok(result);
    }

}
