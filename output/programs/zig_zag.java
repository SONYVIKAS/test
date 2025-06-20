  def zigzag(a):
      longest = 1
      curr_length = 1

      if len(a) == 2 and a[0]!= a[1]:
          return len(a)

      for i in range(len(a)-2):
          prev = a[i]
          curr = a[i+1]
          nxt = a[i+2]

          if (prev < curr and curr > nxt) or (prev > curr and curr < nxt):
              if nxt == a[-1]:
                  curr_length += 2
              else:
                  curr_length += 1

              longest = max(longest, curr_length)

          else:
              curr_length += 1
              longest = max(longest, curr_length)
              curr_length = 1

      return longest