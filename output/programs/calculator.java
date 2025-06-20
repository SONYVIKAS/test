def evaluate(expression):
    storage = []
    parens_indices = []
    operators = {'*': [], '/': [], '+': [],'-': []}

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
    operators = {'*': [], '/': [], '+': [],'-': []}
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
