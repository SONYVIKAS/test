import unittest


def evaluate(expression):
    storage = []
    parens_indices = []

    for i in range(len(expression)):
        char = expression[i]
        if char == '(':
            parens_indices.append(i)
        elif char == ')':
            start = parens_indices.pop() + 1  # parens + 1 position
            total = str(eval(expression[start:i]))
            storage = storage[:start-1]
            storage.append(total)
        else:
            storage.append(char)

    string_expression = ''.join(storage)
    return eval(string_expression)


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
        self.assertEqual(evaluate('(1.2 + 1.3) * 2.5'), 6.25)


if __name__ == '__main__':