def find_grant_cap(grantsArray, newBudget):
    grantsArray = sorted(grantsArray, reverse=True)
    i = len(grantsArray) - 1
    flag = False
    cap = float(newBudget)/len(grantsArray)
    newBudget = float(newBudget)

    while not flag:

        while cap > grantsArray[i]:
            newBudget -= grantsArray[i]
            i -= 1

        new_cap = newBudget/(i + 1)

        if cap == new_cap:
            flag = True

        cap = new_cap

    return cap