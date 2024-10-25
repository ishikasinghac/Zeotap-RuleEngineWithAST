package com.rule.engine.ast.repository;

import com.rule.engine.ast.model.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RuleRepository extends MongoRepository<Rule, String> {
}
