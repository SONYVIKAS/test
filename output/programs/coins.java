def coins(num_coins):
    """Find change from combinations of `num_coins` of dimes and pennies.
    This should return a set of the unique amounts of change possible.
    """
    combos = set()
    coins = [1, 10]

    def _coins(coins_left, combos, total):
        if not coins_left:
            combos.add(total)
            return
        for coin in coins:
            _coins(coins_left - 1, combos, total + coin)

    _coins(num_coins, combos, 0)

    return combos

def coins_2(num_coins):
    """Find change from combinations of `num_coins` of dimes and pennies.
    This should return a set of the unique amounts of change possible.
    """
    combos = set()
    dimes = 10
    pennies = 1

    def _coins_2(coins_left, combos, total):
        if not coins_left:
            combos.add(total)
            return
        _coins_2(coins_left - 1, combos, total + dimes)
        _coins_2(coins_left - 1, combos, total + pennies)

    _coins_2(num_coins, combos, 0)
