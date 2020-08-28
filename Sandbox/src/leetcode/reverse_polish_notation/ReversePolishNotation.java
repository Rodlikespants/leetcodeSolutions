package leetcode.reverse_polish_notation;

import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReversePolishNotation {
    static final Set<String> OPERS = Stream.of("*", "+", "/", "-").collect(Collectors.toSet());

    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();

        String currOper = null;
        Integer currOperand = null;

        for (int i = 0; i < tokens.length; ++i) {
            String token = tokens[i];
            if (OPERS.contains(token)) {
                int num1 = Integer.valueOf(stack.pop());
                int num2 = Integer.valueOf(stack.pop());
                int result = processExpr(token, num2, num1);
                stack.push(String.valueOf(result));
            } else {
                // assume integer
                int operand = Integer.valueOf(token);
                stack.push(String.valueOf(operand));
            }
        }

        // if stack is non empty we have a problem

        return Integer.valueOf(stack.pop());
    }

    public Integer processExpr(String oper, int operand1, int operand2) {
        if (oper == null) {
            throw new RuntimeException("didn't expect null operator"); // error handling?
        }

        if (oper.equals("*")) {
            return operand1 * operand2;
        } else if (oper.equals("/")) {
            return operand1 / operand2;
        } else if (oper.equals("+")) {
            return operand1 + operand2;
        } else if (oper.equals("-")) {
            return operand1 - operand2;
        } else {
            throw new RuntimeException("unexpected expression");
        }
    }
}