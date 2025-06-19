 2. The function first splits the expression into individual characters and removes any spaces.
 3. Next, it initializes a stack to keep track of the indices of opening parentheses and a dictionary to store the indices of each operator.
 4. The function then iterates through the characters of the expression, keeping track of the opening and closing parentheses.
 5. When a closing parenthesis is encountered, the function calls the `calculate` function to evaluate the inner expression within the parentheses.
 6. The `calculate` function takes the inner expression and the dictionary of operator indices as arguments.
 7. It initializes a total variable and a dictionary to store the indices of each operator.
 8. The function then iterates through the characters of the inner expression, keeping track of the indices of each operator.
 9. Next, the function performs the multiplication and division operations in order, using the indices stored in the dictionary.
 10. Afterward, it performs the addition and subtraction operations in order, using the indices stored in the dictionary.
11. Finally, the function returns the calculated total.

```python
import unittest


def evaluate(expression):
    expression = ''.join(expression.split(' '))
    storage = []
    parens_indices = []
    operators = {'*': [], '/': [], '+': [], '-': []}

    for i in range(len(expression)):
        char = expression[i]
        if char == '(':
            parens_indices.append(i)
        elif char == ')':
            start = parens_indices.pop() + 1
            total = str(calculate(expression[start:i], operators))
            storage = storage[:start-1]
            storage.append(total)
        else:
            storage.append(char)

        if char in operators:
            operators[char].append(i)

    string_expression = ''.join(storage)
    return calculate(string_expression, operators)


def calculate(inner_expression, operators):
    total = 0
    operators = {'*': [], '/': [], '+': [], '-': []}
    new_expr = inner_expression

    for i in range(len(inner_expression)):
        char = inner_expression[i]
        if char in operators:
            operators[char].append(i)

    while operators['*']!= []:
        op_index = operators['*'].pop()
        prev = new_expr[op_index-1]
        nxt = new_expr[op_index+1]
        total = int(prev) * int(nxt)
        new_expr = new_expr[:op_index-1] + str(total) + new_expr[op_index+2:]

    while operators['/']!= []:
        op_index = operators['/'].pop()
        prev = new_expr[op_index-1]
        nxt = new_expr[op_index+1]
        total = int(prev) / int(nxt)
        new_expr = new_expr[:op_index-1] + str(total) + new_expr[op_index+2:]

    while operators['-']!= []:
        op_index = operators['-'].pop()
        prev = new_expr[op_index-1]
        nxt = new_expr[op_index+1]
        total = int(prev) - int(nxt)
        new_expr = new_expr[:op_index-1] + str(total) + new_expr[op_index+2:]

    while operators['+']!= []:
        op_index = operators['+'].pop()
        prev = new_expr[op_index-1]
        nxt = new_expr[op_index+1]
        total = int(prev) + int(nxt)
        new_expr = new_expr[:op_index-1] + str(total) + new_expr[op_index+2:]

    return total


class Testing(unittest.TestCase):

    def test_no_parens(self):
        self.assertEqual(evaluate('1 + 3 * 2'), 7)

    def test_single_parens(self):
        self.assertEqual(evaluate('(1 + 7) * 2'), 16)

    def test_operations_order(self):
        self.assertEqual(evaluate('4 * (1 + 7) - 3'), 29)

    def test_long_parens(self):
        self.assertEqual(evaluate('(4 - 7 - 1) * 3'), -6)

    def test_decimals(self):