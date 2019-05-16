## Expression : Implementation of Stack and List, Postfix, Infix.

Expression is either number or combination of number and operator.
Expression class has enum token type to determine the types of token that can be used in the program. Every token has its priority if it is not the number.
Expression has five public methods including main and two private helper methods.

I. Public methods:
a. infixToExpression: converts infix expression to an expression tree. This method takes list of tokens as parameter and returns Expression object.
b. infixToPostfix: converts infix expression to postfix expression. This method takes list of tokens as parameter and returns list of tokens in post fix format.
c. evaluatePostfix: evaluates the value of postfix expression. This method takes list of tokens that are in the post fix format, calculates the value of the expression and returns the result as a long data type.
d. evaluateExpression: evaluates the value of expression tree. This method takes expression as a parameter and returns the result as a long data type.

II. Private Methods:
a. expressionAdder: adds expression to the expression stack. This method takes
two stacks (operator stack and expression stack) as parameter and this is the
void type.
b. perform: peforms the arithmetic operation based on operator type. This method
takes two number and a token (arithmetic operator) as parameters and return the result as long data type.
