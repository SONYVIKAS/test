  import sys

  def find_int(arr):
      arr_set = set(arr)
      for i in range(sys.maxint):
          if i not in arr_set:
              return i
      return None