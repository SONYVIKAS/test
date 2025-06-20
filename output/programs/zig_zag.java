def zigzag(a):
    longest = 1
    curr_length = 1

    for i in range(len(a)-2):
        prev = a[i]
        curr = a[i+1]
        nxt = a[i+2]

        if (prev < curr and curr > nxt) or (prev > curr and curr < nxt):
            curr_length += 1
            longest = max(longest, curr_length)
        else:
            curr_length = 1
