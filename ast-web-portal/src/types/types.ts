
export interface Operand {
    type: 'operand';
    left: null;
    right: null;
    value: string;
  }
  
  export interface Operator {
    type: 'operator';
    left: Operand | Operator;
    right: Operand | Operator;
    value: string;
  }
  
  export type ConditionNode = Operand | Operator;
  