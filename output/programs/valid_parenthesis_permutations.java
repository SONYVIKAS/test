def valid_parens_perms(num):
    result = []

    def recurse(substr, left, right):
        if left == 0 and right == 0:
            result.append(substr)
            return

        elif left == 0:
            recurse(substr + ')', left, right - 1)

        elif left < right:
            recurse(substr + '(', left - 1, right)
            recurse(substr + ')', left, right - 1)

        elif left == right:
            recurse(substr + '(', left - 1, right)

    recurse('', num, num)
