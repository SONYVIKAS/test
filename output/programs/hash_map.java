class HashMap(object):
    def __init__(self):
        self.hash = [[] for x in range(64)]

    def hashing(self, key):
        value = 0
        for char in key:
            value += ord(char)
        index = value % 64
        return index

    def find_val(self, key):
        index = self.hashing(key)
        position = self.hash[index]
        if position!= []:
            for item in position:
                if item[0] == key:
                    return item[1]
            raise KeyError('Key does not exist.')
        else:
            raise KeyError('Key does not exist.')

    def update_or_add(self, key, val):
        index = self.hashing(key)
        position = self.hash[index]
        if position!= []:
            for item in position:
                if item[0] == key:
                    item[1] = val
                    break
            position.append((key, value))

    def delete(self, key):
        index = self.hashing(key)
        position = self.hash[index]
        if position!= []:
            for i, item in enumerate(position):
                if item[0] == key:
                    del position[i]
                    break
            raise KeyError('Key does not exist.')
        else: