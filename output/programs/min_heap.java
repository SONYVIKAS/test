  class MinHeap:
      def __init__(self):
          self.storage = []

      def swap(self, a, b):
          self.storage[a], self.storage[b] = self.storage[b], self.storage[a]

      def size(self):
          return len(self.storage)

      def peak(self):
          return self.storage[0]

      def insert(self, value):
          self.storage.append(value)
          index = self.size() - 1
          self.bubbleUp(index)

      def get_parent(self, child):
          if child % 2 == 0:
              return (child - 2) // 2
          else:
              return (child - 1) // 2

      def bubbleUp(self, child):
          parent = self.get_parent(child)

          while child > 0 and parent >= 0 and self.storage[child] < self.storage[parent]:
              self.swap(child, parent)
              child = parent
              parent = self.get_parent(child)

      def remove_peak(self):
          self.swap(0, self.size() - 1)
          min_elem = self.storage.pop()
          self.bubbleDown(0)

          return min_elem

      def get_child(self, parent):
          child1 = 2 * parent + 1
          child2 = 2 * parent + 2

          if child1 >= self.size():
              return
          elif child2 >= self.size():
              return child1
          elif self.storage[child1] < self.storage[child2]:
              return child1
          else:
              return child2

      def bubbleDown(self, parent):
          child = self.get_child(parent)

          while child is not None and self.storage[parent] > self.storage[child]:
              self.swap(child, parent)
              parent = child
              child = self.get_child(parent)

      def remove(self, item):
          last_index = self.size() - 1
          swap_index = 0

          for i in range(len(self.storage)):
              if item == self.storage[i]:
                  swap_index = i
                  self.storage[i], self.storage[last_index] = self.storage[last_index], self.storage[i]

          self.bubbleUp(swap_index)
          self.bubbleDown(swap_index)

          removed_item = self.storage.pop()

          return removed_item