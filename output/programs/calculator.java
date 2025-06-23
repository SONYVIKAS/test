import re

def calculate(expression):
    expression = re.sub(r'\s+', '', expression)
    operators = {'*': [], '/': [], '+': [], '-': []}
    for i, char in enumerate(expression):
        if char in operators:
            operators[char].append(i)
    while operators['*'] or operators['/']:
        if operators['*']:
            op_index = operators['*'].pop()
        else:
            op_index = operators['/'].pop()
        prev = expression[op_index - 1]
        nxt = expression[op_index + 1]
        if expression[op_index] == '*':
            total = int(prev) * int(nxt)
        else:
            total = int(prev) / int(nxt)
        expression = expression[:op_index - 1] + str(total) + expression[op_index + 2:]
        for op, indices in operators.items():
            for i, index in enumerate(indices):
                if index > op_index:
                    operators[op][i] -= 2
    while operators['+'] or operators['-']:
        if operators['+']:
            op_index = operators['+'].pop()
        else:
            op_index = operators['-'].pop()
        prev = expression[op_index - 1]
        nxt = expression[op_index + 1]
        if expression[op_index] == '+':
            total = int(prev) + int(nxt)
        else:
            total = int(prev) - int(nxt)
        expression = expression[:op_index - 1] + str(total) + expression[op_index + 2:]
        for op, indices in operators.items():
            for i, index in enumerate(indices):
                if index > op_index:
                    operators[op][i] -= 2
