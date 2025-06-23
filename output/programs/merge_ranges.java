def merge_ranges(lst):
    """ Merges overlapping meeting time ranges and returns a list of condensed ranges.

    Args:
        lst (list): A list of tuples representing meeting time ranges. Each tuple has two elements: start time and end time.

    Returns:
        list: A list of condensed meeting time ranges.
    """
    meeting_times = sorted(lst)

    merged_range = [meeting_times[0]]

    for start, end in meeting_times[1:]:
        last_start, last_end = merged_range[-1]

        if last_end >= start:
            merged_range[-1] = (last_start, max(last_end, end))
        else:
            merged_range.append((start, end))
