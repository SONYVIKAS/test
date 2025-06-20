import random

quotes = ['hi', 'hello', 'goodbye', 'dog', 'cat', 'banana']


class QuoteGenerator:
    def __init__(self, quotes):
        self.last_rand_num = -1
        self.quotes = quotes

    def generate_quote(self):
        random_num = random.randint(0, len(self.quotes) - 1)

        if random_num!= self.last_rand_num:
            self.last_rand_num = random_num
            return self.quotes[random_num]
        else:
            return self.generate_quote()


generator1 = QuoteGenerator(quotes)
print(generator1.generate_quote())
print(generator1.generate_quote())
print(generator1.generate_quote())
print(generator1.generate_quote())
print(generator1.generate_quote())
print(generator1.generate_quote())
print(generator1.generate_quote())
print(generator1.generate_quote())
print(generator1.generate_quote())