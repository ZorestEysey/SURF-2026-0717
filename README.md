# this is a title

Codes for calculating CSPF(Closest-Spot Parking Function)-related questions.
## About CSPF
Closest-Spot Parking Function(CSPF) is a variation of Parking Function. CSPF obeys three rules:

1. Every car has a preferred spot to park. All the cars manage to park their car at their preferred spot.
2. If a car find its preferred spot is occupied by another car, it will find the closest empty spot to park.
3. If the preferred spot is occupied, and there are two nearest empty spots with the same distance between each of them and the preferred spot, the car will choose the one closer to the entrance.


## Codes
- `CSPF.py` is designed to calculate and print several CSPF-related results. By setting the variables `N`, `Index`, `car_num`, `Disp`, `left_displacement` and `right_displacement`, it can be used to calculate and print different CSPF-related results. The value of `n` should be an positive integer.
    - `print_all_cspf(n)` By setting the value of `n`, this command will print all the CSPFs with length equals to `n`. The index of each CSPF is also presented, which is useful for following functions.
    - `get_one_cspf(n, index)` By setting the value of `n` and `index`, this command will print just one CSPF depending on the value of `n` and `index`.
    - `get_disp(n, index, car_num)` This method will return an integer `disp` depending on the value of `n`, `index` and `carNum`. `disp` is the displacement of the car with number `carNum` in the CSPF with length `n` and index `index`.
    - `print_unit_interval_cspf(n)` This method will print all the n-lengthed unit-interval CSPFs, whose cars all move for no more than 1 unit. 
    - `count_unit_interval_cspf(n)` This method returns an integer that shows how many unit-interval CSPFs for the given `n`.
    - `get_t_disp(n)` This method returns an integer that shows how many n-lengthed CSPFs have the total displacement `disp`.
    - `get_t_(disp, n)` This method returns an array that shows how many CSPFs have share the same displacement which equals to the index of the array.
    - `print_cspf_by_disp(n, disp)` This method prints all the CSPFs with length `n` and displacement `disp`.
    - `is_a_strict_cspf(n, index)` A strict CSPF has no draws in it. Therefore, a non-strict CSPF has at least one draw. This method returns if the given CSPF is a strict CSPF.
    - `print_all_non_strict_cspf(n)` This method prints all the CSPFs with length `n`.
    - `get_total_left_displacement(n)` This method returns the total left displacement of all CSPFs with length `n`.
    - `get_total_right_displacement(n)` This method returns the total right displacement of all CSPFs with length `n`.
    - `get_total_net_displacement(n)` This method returns the total net displacement (right displacement - left displacement) of all CSPFs with length `n`.
    - `get_sum(n)` This method returns the total displacement (right displacement + left displacement) of all CSPFs with length `n`.
    - `print_lr_distribution(n)` This method prints the frequency of every possible pair of total left displacement and total right displacement across all preference sequences of length `n`.
    - `print_cspf_with_lr_distribution(n, l, r)` This method prints all preference sequences with length `n` whose total left displacement equals `l` and total right displacement equals `r`.
    - `print_non_strict_lr_distribution(n)` Prints the frequency of each (left‑displacement, right‑displacement) pair among all non‑strict CSPFs (i.e., those that experienced at least one tie during the parking process).
    - `print_non_strict_cspf_with_lr_distribution(n, l, r)` Prints all preference sequences that are non‑strict and have exactly `l` units of left displacement and `r` units of right displacement.
    - `print(count_total_lucky_cars(n))` Prints the total number of lucky cars (cars that park exactly at their preferred spot) summed over all length‑`n` CSPFs.
- `analyze.py` provides two utility functions for analyzing numerical sequences:
    - `is_log_concave(lst)` Checks whether a given list of non‑negative numbers satisfies the log‑concavity condition: `lst[i] ** 2 ≥ lst[i‑1]·lst[i+1]` for all interior indices `i`. Returns True for lists with fewer than 3 elements (trivially log‑concave).
    - `max_at(lst)` Returns the index of the first occurrence of the maximum value in the list. If the list is empty, returns `None`.
    - The `__main__` block contains a commented‑out test list and examples; it is not executed by default.
- `prime_divider.py` performs prime factorization of a positive integer. When run as a standalone program, it prompts the user for a number and outputs its prime factorization.
    - `factorize(n)` Factorizes `n` into its prime factors and prints the result in a human‑readable format (e.g., `360 = 2^3 * 3^2 * 5`). It handles the special case `n = 1` by printing `1`. Raises ValueError for non‑positive inputs.
    - `print_factor(base, exp, first)` Internal helper that prints a single prime factor with its exponent, correctly managing multiplication signs.