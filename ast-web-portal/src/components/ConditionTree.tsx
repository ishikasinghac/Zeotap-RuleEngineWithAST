"use client";

import React from 'react';
import { ConditionNode, Operator, Operand } from '@/types/types';

interface ConditionTreeProps {
  node: ConditionNode;
}

const ConditionTree: React.FC<ConditionTreeProps> = ({ node }) => {
  // Check if the node is an operand (base case)
  if (node.type === 'operand') {
    return <span>{node.value}</span>;
  }

  // If the node is an operator, render it recursively
  return (
    <div style={{ marginLeft: '20px', marginTop: '10px' }}>
      <div>
        <strong>{node.value}</strong>
      </div>
      <div style={{ display: 'flex', gap: '10px' }}>
        <ConditionTree node={node.left} />
        <ConditionTree node={node.right} />
      </div>
    </div>
  );
};

export default ConditionTree;
