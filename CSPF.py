# CSPF.py

def check_n(n):
    if n <= 0:
        raise Exception('n must be a positive integer')
    return

def check_index(index):
    max_index = N ** N
    if index < 1 or index > max_index:
        raise Exception ('Index must be between 1 and {}'.format(max_index))
    return

def check_car_num(carNum):
    if carNum < 1 or carNum > N:
        raise Exception ('Car number must be between 1 and {}'.format(N))
    return

def check_disp(disp):
    max_disp= (N*(N-1))//2
    if disp < 0 or disp > max_disp:
        raise Exception ('Disp must be between 0 and {}'.format(max_disp))
    return

def print_all_cspf(n):
    check_n(n)

    total = n ** n
    for i in range(total):
        print(f"{i+1}. {get_one_cspf(n, i+1)}")
    return

def get_one_cspf(n, index):
    check_n(n)
    check_index(index)

    return into_list(index-1)

def into_list(index):
    list = [0] * N
    for i in range(N):
        list[N - 1 - i] = ( index // N**i ) % N + 1
    return list

def get_disp(n, index, car_num):
    check_n(n)
    check_index(index)
    check_car_num(car_num)

    pref = into_list(index-1)
    final_position, _, _, _= simulate(pref)
    return final_position[car_num-1]-(pref[car_num-1]-1)

def simulate(pref):
    size = len(pref)
    occupied = [False] * size
    final_pos = [0] * size
    left_total = 0
    right_total = 0
    has_tie = 0

    for i in range(size):
        p = pref[i] - 1
        if not occupied[p]:
            final_pos[i] = p
            occupied[p] = True
            continue

        left = p - 1
        right = p + 1
        found = False
        while not found:
            left_ok = left >= 0 and not occupied[left]
            right_ok = right < size and not occupied[right]

            if left_ok and right_ok:
                has_tie += 1
                best_pos = left
                occupied[left] = True
                disp = left - p
                if disp < 0:
                    left_total += -disp
                found = True
            elif left_ok:
                best_pos = left
                occupied[left] = True
                disp = left - p
                if disp < 0:
                    left_total += -disp
                found = True
            elif right_ok:
                best_pos = right
                occupied[right] = True
                disp = right - p
                if disp > 0:
                    right_total += disp
                found = True
            else:
                left -= 1
                right += 1
                if left < 0 and right >= size:
                    raise RuntimeError("No empty spot available")

        final_pos[i] = best_pos

    return final_pos, left_total, right_total, has_tie

def print_unit_interval_cspf(n):
    check_n(n)

    total = n ** n
    for i in range(total):
        if is_unit_interval_cspf(n, i+1):
            print(f"{i+1}. {into_list(i)}")
    return

def is_unit_interval_cspf(n, index):
    check_n(n)
    check_index(index)

    for k in range(n):
        if abs(get_disp(n, index, k+1))>1:
            return False
    return True

def count_unit_interval_cspf(n):
    check_n(n)

    count = 0
    total = n ** n
    for i in range(total):
        if is_unit_interval_cspf(n, i+1):
            count += 1
    return count

def get_t_disp(n):
    check_n(n)
    total_configues = n ** n
    max_total_disp = n*(n-1)//2
    freq = [0] * (max_total_disp+1)

    for i in range(total_configues):
        pref = into_list(i)
        total_disp = calc_total_disp(pref)
        freq[total_disp]+=1
    return freq

def calc_total_disp(pref):
    final_position, _, _, _ = simulate(pref)
    total = 0
    for i in range(N):
        total += abs(final_position[i]-(pref[i]-1))
    return total

def get_t(disp, n):
    check_disp(disp)
    t = get_t_disp(n)
    return t[disp]

def print_cspf_by_disp(n, disp):
    check_disp(disp)

    total_configues = n**n
    for index in range(total_configues):
        pref = into_list(index)
        total_disp = calc_total_disp(pref)
        if total_disp == disp:
            print(f"{index+1}. {pref}")
    return

def print_different_disp(n):
    check_n(n)
    totals = compute_left_right_totals(n)
    print("Left total  = "+str(totals[0]))
    print("Right total = "+str(totals[1]))
    print("Net (R-L)   = "+str(totals[1]-totals[0]))
    print("Sum(n)      = "+str(totals[1]+totals[0]))
    return

def compute_left_right_totals(n):
    check_n(n)
    total_left = 0
    total_right = 0
    total_configues = n ** n
    for i in range(total_configues):
        pref = into_list(i)
        lr = calc_left_right(pref)
        total_left+=lr[0]
        total_right+=lr[1]
    return [total_left, total_right]

def calc_left_right(pref):
    final_position, _, _, _ = simulate(pref)
    left = 0
    right = 0
    for i in range(N):
        diff = final_position[i] - (pref[i]-1)
        if diff<0:
            left += -diff
        elif diff>0:
            right += diff
    return [left, right]

def is_a_strict_cspf(n, index):
    check_n(n)
    check_index(index)

    pref = get_one_cspf(n, index)
    _, _, _, has_tie = simulate(pref)
    return has_tie == 0

def print_all_non_strict_cspf(n):
    check_n(n)
    total = n ** n
    for i in range(total):
        if not is_a_strict_cspf(n, i+1):
            print(f"{i+1}. {get_one_cspf(n, i+1)}")
    return

def print_all_strict_cspf(n):
    check_n(n)
    total = n ** n
    for i in range(total):
        if is_a_strict_cspf(n, i + 1):
            print(f"{i + 1}. {get_one_cspf(n, i + 1)}")
    return

def get_left_displacement_for_one(n, index):
    check_n(n)
    check_index(index)
    pref = get_one_cspf(n, index)
    _, left_displacement, _, _ = simulate(pref)
    return left_displacement

def get_right_displacement_for_one(n, index):
    check_n(n)
    check_index(index)
    pref = get_one_cspf(n, index)
    _, _, right_displacement, _ = simulate(pref)
    return right_displacement

def get_total_left_displacement(n):
    check_n(n)
    total = n ** n
    total_left_displacement = 0
    for i in range(total):
        pref = get_one_cspf(n, i+1)
        _, left_displacement, _, _ = simulate(pref)
        total_left_displacement += left_displacement
    return total_left_displacement

def get_total_right_displacement(n):
    check_n(n)

    total = n ** n
    total_right_displacement = 0
    for i in range(total):
        pref = get_one_cspf(n, i+1)
        _, _, right_displacement, _ = simulate(pref)
        total_right_displacement += right_displacement
    return total_right_displacement

def get_sum(n):
    check_n(n)
    return get_total_left_displacement(n)+get_total_right_displacement(n)

def get_net_displacement_for_one(n, index):
    check_n(n)
    check_index(index)
    return get_right_displacement_for_one(n, index)-get_left_displacement_for_one(n, index)

def get_total_net_displacement(n):
    check_n(n)

    total = n ** n
    total_net_displacement = 0
    for i in range(total):
        total_net_displacement += get_net_displacement_for_one(n, i)
    return total_net_displacement

def print_lr_distribution(n):
    check_n(n)
    from collections import defaultdict
    import itertools

    dist = defaultdict(int)
    for pref_tuple in itertools.product(range(1, n + 1), repeat=n):
        pref = list(pref_tuple)
        _, left, right, _ = simulate(pref)
        dist[(left, right)] += 1

    for (l, r), count in sorted(dist.items()):
        print(f"L={l}, R={r}: {count}")


def print_cspf_with_lr_distribution(n, l, r):
    check_n(n)
    max_disp = n * (n - 1) // 2
    if l < 0 or l > max_disp:
        raise ValueError(f"l must be between 0 and {max_disp}")
    if r < 0 or r > max_disp:
        raise ValueError(f"r must be between 0 and {max_disp}")
    if l + r > max_disp:
        raise ValueError(f"l+r cannot exceed {max_disp}")
    import itertools

    found = False
    count = 0
    for pref_tuple in itertools.product(range(1, n + 1), repeat=n):
        pref = list(pref_tuple)
        _, left, right, _ = simulate(pref)
        if left == l and right == r:
            print(pref)
            found = True
            count += 1

    if not found:
        print("none")
    else:
        print(f"Total: {count}")

def print_non_strict_lr_distribution(n):
    check_n(n)
    from collections import defaultdict
    import itertools

    dist = defaultdict(int)
    for pref_tuple in itertools.product(range(1, n + 1), repeat=n):
        pref = list(pref_tuple)
        _, left, right, has_tie = simulate(pref)
        if has_tie > 0:
            dist[(left, right)] += 1

    for (l, r), count in sorted(dist.items()):
        print(f"L={l}, R={r}: {count}")


def print_non_strict_cspf_with_lr_distribution(n, l, r):
    check_n(n)

    max_disp = n * (n - 1) // 2
    if l < 0 or l > max_disp:
        raise ValueError(f"l must be between 0 and {max_disp}")
    if r < 0 or r > max_disp:
        raise ValueError(f"r must be between 0 and {max_disp}")
    if l + r > max_disp:
        raise ValueError(f"l+r cannot exceed {max_disp}")

    import itertools

    found = False
    count = 0
    for pref_tuple in itertools.product(range(1, n + 1), repeat=n):
        pref = list(pref_tuple)
        _, left, right, has_tie = simulate(pref)
        if left == l and right == r and has_tie > 0:
            print(pref)
            found = True
            count += 1

    if not found:
        print("none")
    else:
        print(f"Total: {count}")
    return

def count_lucky_cars(n, index):
    check_n(n)
    check_index(index)

    pref = get_one_cspf(n, index)
    final_pos, _, _, _ = simulate(pref)

    lucky_count = 0
    for i in range(n):
        if final_pos[i] == pref[i] - 1:
            lucky_count += 1
    return lucky_count

def count_total_lucky_cars(n):
    check_n(n)
    total = n ** n
    total_lucky = 0

    for idx in range(1, total + 1):
        total_lucky += count_lucky_cars(n, idx)

    return total_lucky

if __name__ == '__main__':
    N = 3
    Index = 7
    Car_num = 3
    Disp = 2
    left_displacement = 0
    right_displacement = 0

    # print_all_cspf(N)

    # print(get_one_cspf(N, Index))

    # print(get_disp(N, Index, Car_num))

    # print_unit_interval_cspf(N)

    # print(count_unit_interval_cspf(N))

    # print(get_t_disp(N))

    # print(get_t(Disp, N))

    # print(is_a_strict_cspf(N, Index))
    
    # print_all_non_strict_cspf(N)
    
    # print(get_total_left_displacement(N))
    
    # print(get_total_right_displacement(N))
    
    # print(get_total_net_displacement(N))
    
    # print(get_sum(N))
    
    # print_lr_distribution(N)
    
    # print_cspf_with_lr_distribution(N, left_displacement, right_displacement)
    
    # print_non_strict_lr_distribution(N)
    
    # print_non_strict_cspf_with_lr_distribution(N, left_displacement, right_displacement)
    
    # print(count_total_lucky_cars(N))