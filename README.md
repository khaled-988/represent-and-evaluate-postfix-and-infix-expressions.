# represent-and-evaluate-postfix-and-infix-expressions.
We will be using queues and stacks to represent and evaluate postfix and infix expressions.

# # Implement the interface PostFixExp using the class PostFixExpImp.
This interface represents a postfix expression involving non-negative integers. Expressions can include any of the
following binary operators : *,/,%,+,-,<,>,>=,<=,==,!=.
# # # # Remark 1.
• "/" represents integer division and "%" represents the modulo operation on integers.
• The tokens of the expression are separated by a space, for example: 5 3 - 4 2 + *.
• The comparison operators should return the integer values 0 and 1 to represent false
and true.
# # # # A postfix expression is considered to be valid if and only if:
(a) Each operator is preceded by two postfix expressions. The smallest postfix expression
is a singular integer.
(b) The expression contains no division by or modulo 0.

# # Implement the interface InFixExp using the class InFixExpImp. 
This interface represents an infix expression on non-negative integers. Expressions can include any of the following
operators: *,/,%,+,-,<,>,<=,>=,==,!= in addition to the parentheses: (,).
# # # # Remark 2.
• "/" represents integer division and "%" represents the modulo operation on integers.
• The tokens of the expression are separated by a space, for example: 6 / 2 * ( 5 - 2 ).
• The comparison operators should return the integer values 0 and 1 to represent false and true.
# # # # An infix expression is considered to be valid if and only if:
(a) Each operator is preceded by a valid infix expression and succeeded by a valid infix expression. The smallest infix expression is a singular number.
(b) The expression contains no division by or modulo 0.
(c) The parentheses in the expression are in a valid configuration.
