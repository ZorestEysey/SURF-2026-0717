# prime_divider.py

def factorize(n):
    if n <= 0:
        raise ValueError("n must be positive")
    if n == 1:
        print(n)
        return

    first = True

    count = 0
    while n % 2 == 0:
        n //= 2
        count += 1
    if count:
        print_factor(2, count, first)
        first = False

    i = 3
    while i * i <= n:
        count = 0
        while n % i == 0:
            n //= i
            count += 1
        if count:
            print_factor(i, count, first)
            first = False
        i += 2

    if n > 1:
        print_factor(n, 1, first)

def print_factor(base, exp, first):
    if not first:
        print(" * ", end="")
    print(base, end="")
    if exp > 1:
        print(f"^{exp}", end="")

if __name__ == '__main__':
    n = int(input("Give me a number: "))
    print(f"{n} = ", end="")
    factorize(n)
    print()