# analyze.py

def is_log_concave(lst):
    if len(lst) < 3:
        return True
    return all(lst[i]**2 >= lst[i-1]*lst[i+1] for i in range(1, len(lst)-1))

def max_at(lst):
    if not lst:
        return None
    return max(range(len(lst)), key=lst.__getitem__)

if __name__ == '__main__':
    test_list = [
        0
    ]

    # print(is_log_concave(test_list))
    # print(max_at(test_list))