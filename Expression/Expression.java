package axl173530;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * author : Ashish Lamichhane
 * version : 1.0
 * Class : CS 3345.004 Fall '18
 */

/**
 * Class to store a node of expression tree
 * For each internal node, element contains a binary operator
 * List of operators: +|*|-|/|%|^
 * Other tokens: (|)
 * Each leaf node contains an operand (long integer)
 */

// Starter code for Project 1
public class Expression {
    Token element;
    Expression left, right;

    private Expression() {
        element = null;
    }

    private Expression(Token oper, Expression left, Expression right) {
        this.element = oper;
        this.left = left;
        this.right = right;
    }

    private Expression(Token num) {
        this.element = num;
        this.left = null;
        this.right = null;
    }

    // Create token corresponding to a string
    // tok is "+" | "*" | "-" | "/" | "%" | "^" | "(" | ")"| NUMBER
    // NUMBER is either "0" or "[-]?[1-9][0-9]*
    static Token getToken(String tok) {
        Token result;
        switch (tok) {
            case "+":
                result = new Token(TokenType.PLUS, 2, tok);  // priority of + is 2.
                break;
            case "*":
                result = new Token(TokenType.TIMES, 3, tok);  // priority of * is 1.
                break;
            case "-":
                result = new Token(TokenType.MINUS, 2, tok); // priority of - is 2.
                break;
            case "/":
                result = new Token(TokenType.DIV, 3, tok); // priority of / is 1.
                break;
            case "%":
                result = new Token(TokenType.MOD, 3, tok); // priority of % is 1.
                break;
            case "^":
                result = new Token(TokenType.POWER, 4, tok); // priority of ^ is 0.
                break;
            case "(":
                result = new Token(TokenType.OPEN, 1, tok); // priority of "(" is 3.
                break;
            case ")":
                result = new Token(TokenType.CLOSE, 1, tok); // priority of ")" is 3.
                break;
            case " ":
                result = new Token(TokenType.NIL, 0, tok); // priority of nill is 4.
                break;
            default:
                result = new Token(tok);
                break;
        }
        return result;
    }

    /**
     * opstack : stack to store the operations.
     * expressionstack : stack to store expressions.
     * temptoken: stores next token to be compared.
     * while there are tokens in the list
     * if token is number store in expressionstack.
     * if token is operator store in opstack.
     * Based on operator's type and priority pop enough numbers ( or expressions) from expressionstack
     * create new expression including operator and poped numbers (or expressions) and push the expression into the stack.
     *
     * @param exp : Take list of tokens as parameters are convert into expression tree.
     * @return the top of the expression stack which is the expression tree of the given list of tokens.
     */

    public static Expression infixToExpression(List<Token> exp) {
        Iterator<Token> iterator = exp.iterator();
        Stack<Token> opstack = new Stack<>();
        opstack.push(getToken(" "));
        Stack<Expression> expressionstack = new Stack<>();
        Token temptoken;

        while (iterator.hasNext()) {                // as long as there are any tokens left
            temptoken = iterator.next();
            if (!temptoken.isOperand()) {                // if token is arithmetic operator.
                if (opstack.peek().token != TokenType.NIL) {        // if opstacks already has some tokens in it.
                    if (temptoken.token == TokenType.CLOSE) {        // if token is close then pop from opstack and add to output until open is found.
                        while (opstack.peek().token != TokenType.OPEN) {
                            expressionAdder(opstack, expressionstack);
                        }
                        opstack.pop();         // pops the open from opstack.
                    } else {                      // if token is not close pop from opstacks and add to output while token in stack has priority >= temptoken's priority.
                        while (opstack.peek().priority >= temptoken.priority) {
                            expressionAdder(opstack, expressionstack);
                        }
                        opstack.push(temptoken);           // finally push the temptoken after popping enough operators.
                    }
                } else {
                    opstack.push(temptoken);
                }         // if opstack is empty then push the first operator into the stack.
            } else {
                expressionstack.add(new Expression(temptoken));
            }               // if token is number then add to output.
        }
        // form new expressions unless there a single expression left in the stack.
        while (expressionstack.size() > 1) {
            expressionAdder(opstack, expressionstack);
        }
        return expressionstack.pop();
    }

    /**
     * Helper method to add expression to an expressionstack.
     *
     * @param opstack  : stack of operators.
     * @param expstack : stack of expressions.
     */
    private static void expressionAdder(Stack<Token> opstack, Stack<Expression> expstack) {
        Token operator = opstack.pop();
        Expression right = expstack.pop();
        Expression left = expstack.pop();
        expstack.add(new Expression(operator, left, right));
    }

    /**
     * opstack : stack to store the operations.
     * output : linked list to store the postfix expression.
     * temptoken: stores next token to be compared.
     * if the token is number store in output list.
     * if the token is operator add in opstacks.
     * Based on token's type and priority pop enough numbers from output and form the postfix expression and
     * push the result back to the output list.
     *
     * @param exp : list of tokens to be converted to postfix expression.
     * @return : return converted postfix expression as a list.
     */

    public static List<Token> infixToPostfix(List<Token> exp) {
        Stack<Token> opstacks = new Stack<>();
        opstacks.push(getToken(" "));               // indicates end of the stack.
        List<Token> output = new LinkedList<>();
        Iterator<Token> tokenIterator = exp.iterator();
        Token tempToken;
        while (tokenIterator.hasNext()) {                // as long as there are any tokens left
            tempToken = tokenIterator.next();
            if (!tempToken.isOperand()) {                // if token is arithmetic operator.
                if (opstacks.peek().token != TokenType.NIL) {        // if opstacks already has some tokens in it.
                    if (tempToken.token == TokenType.CLOSE) {        // if token is close then pop from opstack and add to output until open is found.
                        while (opstacks.peek().token != TokenType.OPEN) {
                            output.add(opstacks.pop());
                        }
                        opstacks.pop();         // pops the open from opstack.
                    } else {                      // if token is not close pop from opstacks and add to output while token in stack has priority >= temptoken's priority.
                        while (opstacks.peek().priority >= tempToken.priority) {
                            output.add(opstacks.pop());
                        }
                        opstacks.push(tempToken);           // finally push the temptoken after popping enough operators.
                    }
                } else {
                    opstacks.push(tempToken);
                }         // if opstack is empty then push the first operator into the stack.
            } else {
                output.add(tempToken);
            }               // if token is number then add to output.
        }
        // Adding remaining tokens in opstack to output.
        while (opstacks.peek().token != TokenType.NIL) {
            if (opstacks.peek().token != TokenType.OPEN || opstacks.peek().token != TokenType.CLOSE) {
                output.add(opstacks.pop());
            }
        }
        return output;
    }

    /**
     * operands : stack to store the numbers.
     * tempToken: stores the token to be compared.
     * if the token is number store in operands.
     * if the token is operator pop enough numbers from operands and
     * perform the arithmetic operation and push the result into operands.
     *
     * @param exp : list of tokens to be evaluated.
     * @return : return the final answer.
     */

    public static long evaluatePostfix(List<Token> exp) {
        Stack<Token> operands = new Stack<>();
        Iterator<Token> expiterator = exp.iterator();
        Token tempToken;
        Long result = 0L;
        while (expiterator.hasNext()) {
            tempToken = expiterator.next();
            if (tempToken.isOperand()) {
                operands.push(tempToken);
            } else {
                Long right = operands.pop().getValue();
                Long left = operands.pop().getValue();
                result = perform(left, right, tempToken);
                operands.push(new Token(String.valueOf(result)));
            }
        }
        return result;
    }

    /**
     * if leaf node ( i.e. number) then store it in result.
     * if inner nodes then calculate the result using helper function : perform*left, right, operator).
     *
     * @param tree : expression tree to be evaluated.
     * @return : return the final answer.
     */


    public static long evaluateExpression(Expression tree) {
        Long result;
        Long left, right;
        if (tree.element.isOperand()) {
            result = tree.element.getValue();
        } else {
            left = evaluateExpression(tree.left);
            right = evaluateExpression(tree.right);
            result = perform(left, right, tree.element);
        }
        return result;
    }

    /**
     * Helper function to check the arithmetic operation type and performs the operation on two operators.
     *
     * @param o1    : goes to the left of operator.
     * @param token : arithmetic operator.
     * @param o2    : goes to the right of operator.
     */

    private static long perform(Long o1, Long o2, Token token) {
        Long result = 0L;
        String c = token.toString();
        switch (c) {
            case "+":
                result = o1 + o2;
                break;
            case "*":
                result = o1 * o2;
                break;
            case "-":
                result = o1 - o2;
                break;
            case "/":
                result = o1 / o2;
                break;
            case "%":
                result = o1 % o2;
                break;
            case "^":
                result = (long) Math.pow(o1, o2);
                break;
            default:
                break;
        }
        return result;
    }

    // sample main program for testing
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;

        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }

        int count = 0;
        while (in.hasNext()) {
            String s = in.nextLine();
            List<Token> infix = new LinkedList<>();
            Scanner sscan = new Scanner(s);
            int len = 0;
            while (sscan.hasNext()) {
                infix.add(getToken(sscan.next()));
                len++;
            }
            if (len > 0) {
                count++;
                System.out.println("Expression number: " + count);
                System.out.println("Infix expression: " + infix);
                Expression exp = infixToExpression(infix);
                List<Token> post = infixToPostfix(infix);
                System.out.println("Postfix expression: " + post);
                long pval = evaluatePostfix(post);
                long eval = evaluateExpression(exp);
                System.out.println("Postfix eval: " + pval + " Exp eval: " + eval + "\n");
            }
        }
    }


    public enum TokenType {  // NIL is a special token that can be used to mark bottom of stack
        PLUS, TIMES, MINUS, DIV, MOD, POWER, OPEN, CLOSE, NIL, NUMBER
    }

    public static class Token {
        TokenType token;
        int priority; // for precedence of operator
        Long number;  // used to store number of token = NUMBER
        String string;

        Token(TokenType op, int pri, String tok) {
            token = op;
            priority = pri;
            number = null;
            string = tok;
        }

        // Constructor for number.  To be called when other options have been exhausted.
        Token(String tok) {
            token = TokenType.NUMBER;
            number = Long.parseLong(tok);
            string = tok;
        }

        boolean isOperand() {
            return token == TokenType.NUMBER;
        }

        public long getValue() {
            return isOperand() ? number : 0;
        }

        public String toString() {
            return string;
        }
    }
}

