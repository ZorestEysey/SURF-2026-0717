# Problem Overview

In the classical parking problem, we consider $n$ cars parking into $n$ spots. Each car has a preferred spot, and it will park as close to its preference as possible.

For example, in the $n=3$ parking function (i.e., 3 cars and 3 spots), we use a vector $\vec{s}=(0,0,0)$ to represent a road with three spots, where cars enter from the left side and $0$ denotes an empty spot. The vector $\vec{p}=(3,1,2)$ represents the preferred spots of the three cars: the first car (denoted by $C_1$) prefers the third position (denoted by $s_3$, where $s_3$ is the third component of $\vec{s}$) because $p_1=3$. Similarly, $C_2$ prefers $s_1$, and $C_3$ prefers $s_2$. The final parking arrangement is then given by $\vec{s}=(2,3,1)$, where $C_1$ parks at $s_3$, $C_2$ at $s_1$, and $C_3$ at $s_2$; all cars get their preferred spots.

The parking order follows the car indices: $C_1$ parks first, $C_2$ second, and so on.

However, if a car finds its preferred spot already occupied, for example, in an $n=4$ CSPF instance with $\vec{s}=(0,1,2,0)$ and $C_3$ is about to park with $p_3=3$, but $s_3=2$, so $C_3$ must find another empty spot. Here $s_1=s_4=0$, both are candidates. In the Closest‑Spot Parking Function (CSPF), the rule is: if a car's preferred spot is taken, it parks in the nearest empty spot to its preference. Thus $C_3$ would park at $s_4$, because the distance from $s_4$ to its preferred spot $s_3$ is smaller than from $s_1$ (distance between adjacent spots is 1).

Similarly, if $\vec{s}=(0,1,2,0)$ and $p_3=2$, then $C_3$ would park at $s_1$.

In CSPF, if there are two empty spots equally distant from the preferred spot and no other empty spot is closer, the car chooses the left one, i.e., the smaller index. For example, with $n=3$, $\vec{s}=(0,1,0)$ and $p_2=2$, then $C_2$ needs to park at either $s_1$ or $s_3$; they are equally distant from $s_2$. By the rule, $C_2$ parks at $s_1$ because it has the smaller index.

# Definitions and Problems

This section introduces definitions related to CSPF and the problems to be studied.

Related definitions:
- Define the vector $\vec{r}$ as the final parking position of each car. For instance, when $\vec{s}=(2,3,1)$, we have $\vec{r}=(3,1,2)$, meaning $r_1=3$ means $C_1$ ends at $s_3$, $r_2=1$ means $C_2$ ends at $s_1$, etc.

- Define $d_k(\vec{p}) = |r_k - p_k|$ as the displacement of $C_k$ (for a fixed preference sequence $\vec{p}$). $D(\vec{p})$ is the total displacement of the preference sequence $\vec{p}$, given by $\displaystyle \sum_{i=1}^n d_i(\vec{p})$.

- Define $t_m(n)$ as the number of preference sequences $\vec{p}$ of length $n$ such that $D(\vec{p})=m$. For example, $t_0(3)=6$. Let $\vec{t}(n) = (t_0(n), t_1(n), t_2(n), \dots)$.

- Define a unit‑interval CSPF as one where $\forall k \in [1,n]$, $|r_k - p_k| \le 1$.

- Define $D_L(\vec{p})$ as the total leftward movement of cars from their preferred positions, and $D_R(\vec{p})$ as the total rightward movement.

- Define $\text{Sum}(n)=\displaystyle\sum_{i=0}^\frac{n(n-1)}{2}i\cdot t_i(n)$ as the sum of the absolute value of all CSPFs whose length equals to $n$. $\text{Sum}(n)==\displaystyle\sum_{\vec{p}\in\text{CSPF}}D(\vec{p})=\displaystyle\sum_{\vec{p}\in\text{CSPF}}D_L(\vec{p})+D_R(\vec{p})=\displaystyle\sum_{\vec{p}\in\text{CSPF}}\sum_{i=1}^n d_i(\vec{p})$
- Define $\delta(\vec{p})$ as the net displacement of $\vec{p}$, $\Delta(n)$ as the total net displacement of all $\vec{p}$ whose length equals to $n$, $\Delta(n)=\displaystyle\sum_{\vec{p}\in\text{CSPF}}D_R(\vec{p})-D_L(\vec{p})=\displaystyle\sum_{\vec{p}\in\text{CSPF}}\delta(\vec{p})$.

The following are problems around CSPF. Problems marked "Solved" have been solved.

- Problem 1: For car $C_k$, with preferred spot $p_k$ and final spot $r_k$, let there be $\alpha_k$ occupied spots immediately to the left of $r_k$, and $\beta_k$ occupied spots immediately to the right. Determine the possible range of $p_k$. [Solved](#problem1)

- Problem 2: Prove that $t_0(n)=n!$. [Solved](#problem2)

- Problem 3: Find an expression for $t_1(n)$. [Solved](#problem3)

- Problem 4: Prove that $t_{\frac{n(n-1)}{2}}(n)=3$ for $n\ge 3$. [Solved](#problem4)

- Problem 5: Find an expression for $t_m(n)$ for $m \le \frac{n(n-1)}{2}$.

- Problem 6: Investigate unit‑interval CSPF.

- Problem 7: Investigate the polynomial $\displaystyle \sum_{\vec{p}\in\text{CSPF}} x^{D_L(\vec{p})} y^{D_R(\vec{p})}$.

- Problem 8: A sequence is called (non‑strict) unimodal if there exists a point (or a consecutive interval) such that the sequence is non‑decreasing before that point and non‑increasing after. Investigate whether $t_m(n)$ is unimodal. (This may have been studied in classical parking functions; literature search needed.)

- Problem 9: A finite sequence of non‑negative real numbers $a_0,a_1,\dots,a_n$ is called log‑concave if for all $1\le i\le n-1$, $a_i^2 \ge a_{i-1} a_{i+1}$. Investigate whether $t_m(n)$ is log‑concave. If $t_m(n)$ is log‑concave, it is automatically unimodal. (Literature search may be needed.)

- Problem 10: Attempt to prove or disprove $t_m(n) \le t_{n-1}(n)$.

- Problem 11: Find an expression for $t_{n-1}(n)$.

- Problem 12: Prove that for $n\ge 4$, $t_{\frac{n(n-1)}{2}-1}(n)=3n$. [Solved](#problem12)

- Problem 13: Investigate properties related to $\text{Sum}(n)$.

- Problem 14: Prove that $\Delta(n)=0$.

# Solutions and Conjectures

This section contains solutions and conjectures for the above problems. If a problem is not yet solved, some related information may be given.

## Problem 1

The range of possible $p_k$ is

- $p_k \in [p_{k,\min}, p_{k,\max}]$
- $p_{k,\min}=1$ if $r_k-\alpha_k=1$, otherwise $p_{k,\min}=r_k-\alpha_k/2$
- $p_{k,\max}=n$ if $r_k+\beta_k=n$, otherwise $p_{k,\max}=r_k+(\beta_k+1)/2$

All divisions are integer divisions.

## Problem 2

$D(\vec{p})=0$ means that every car parks at its preferred spot. This is equivalent to assigning the $n$ spots bijectively to the $n$ cars, giving $n!$ possible preference sequences. Hence $t_0(n)=n!$.

## Problem 3

$$
t_1(n)=\frac{n!(5n-4)}{6}, \quad n\ge 2.
$$

We define a vector $\vec{q}$ where $q_i$ is the number of cars that choose spot $s_i$ as their preferred spot. For example, if $\vec{p}=(1,2,1)$, then $\vec{q}=(2,1,0)$: $q_1=2$ means two cars ($C_1$ and $C_3$) prefer $s_1$, $q_2=1$ means one car ($C_2$) prefers $s_2$, and $q_3=0$ means no car prefers $s_3$.

When $D(\vec{p})=1$, exactly one car has displacement 1. Since $C_1$ always finds all spots empty, $d_1(\vec{p})=0$. Thus the displaced car cannot be $C_1$.

The possible $\vec{q}$ that could satisfy $D(\vec{p})=1$ fall into two types:

- Type 1
	- $(0, 2, 1, 1, \dots, 1)$
	- $(1, 0, 2, 1, \dots, 1)$
	- ...
	- $(1, 1,\dots, 0, 2, 1)$
	- $(1, 1,\dots, 1, 0, 2)$

- Type 2
	- $(2, 0, 1, 1, \dots, 1)$
	- $(1, 2, 0, 1, \dots, 1)$
	- ...
	- $(1, 1,\dots, 2, 0, 1)$
	- $(1, 1,\dots, 1, 2, 0)$

Each type has $n-1$ permutations, giving $2(n-1)$ vectors $\vec{q}$ in total.

For each such $\vec{q}$, we distribute the $n$ spots among the $n$ cars according to these counts. The two cars that select the same spot (where $q_i=2$) are unordered, so each $\vec{q}$ corresponds to $\frac{n!}{2!}$ preference sequences. Thus the total from these $\vec{q}$ would be $\frac{n!}{2!}\cdot 2(n-1) = n!(n-1)$.

However, not all these $\vec{q}$ actually yield $D(\vec{p})=1$. For instance, $\vec{p}=(1,5,3,3,2)$ gives $\vec{q}=(1,1,2,0,1)$, but $D(\vec{p})=d_4+d_5=1+2=3\neq 1$. Hence we must subtract the number of invalid sequences, denote this by $b$. So $t_1(n)=n!(n-1)-b$.

For Type 1, the two cars that prefer the same spot will cause the later one to move to the spot immediately to the left (regardless of whether the right side is available), with displacement 1, and all other cars stay put. Thus all Type 1 $\vec{q}$ are valid. Therefore $b$ comes entirely from Type 2.

Among Type 2, the special case $\vec{q}=(2, 0, 1, 1, \dots, 1)$ is valid: one of the two cars that prefer $s_1$ will move to $s_2$, all others have displacement 0. Hence this one is not counted in $b$. So $b$ comes from the remaining Type 2 vectors.

Now consider a general Type 2 vector $\vec{q}=(1\dots, 2, 0\dots)$ with $q_i=2$, $q_{i+1}=0$, and all other entries 1. Suppose we have cars with preferences: $p_x=i$, $p_y=i$ (with $x<y$), and $p_z=i-1$. If $D(\vec{p})=1$, then $d_y(\vec{p})=1$ (the later of the two cars preferring $i$ moves right by one). This means spot $s_{i-1}$ is already occupied. Since $D(\vec{p})=d_y=1$, we must have $d_z=0$, so $C_z$ parks at $s_{i-1}$, and $C_z$ must have parked before $C_y$ (i.e., $y>z$). Thus if the indices satisfy $x<y<z$, then $D(\vec{p})\neq 1$.

There are $n-2$ such $\vec{q}$ (all Type 2 except the first). For each such $\vec{q}$, we choose 3 cars: let the largest index among them prefer $s_{i-1}$, and the other two prefer $s_i$. The remaining $n-3$ cars are assigned to the remaining $n-3$ spots arbitrarily. Hence
$$
b = \binom{n}{3} (n-2) (n-3)!.
$$

Therefore
$$
t_1(n)=n!(n-1)-\binom{n}{3}(n-2)(n-3)!
= n!(n-1)-\frac{n!}{6}(n-2)
= \frac{n!(5n-4)}{6}.
$$

## Problem 4

If all cars achieve their individual maximum displacement, then $D(\vec{p})$ attains its global maximum.

For car $C_i$, the theoretical maximum displacement is $i-1$, i.e., it passes all previously parked cars. For example, to have $d_3(\vec{p})=2$, $C_3$ must pass both $C_1$ and $C_2$. If $C_1$ and $C_2$ are not parked in consecutive spots, then $d_3(\vec{p})\le 1$. Also, if both $s_1$ and $s_n$ are empty, then $d_3(\vec{p})\le 1$.

More generally, for $i\ge 3$, if there exist $x<y\le n$ such that either $s_{x+1}=0$ or $s_{y-1}=0$, then $d_i(\vec{p})\le i-2 < i-1$. If both $s_1=s_n=0$, then $d_i(\vec{p})\le i/2 \le i-1$, with equality only when $i=2$.

Thus, to achieve $d_i(\vec{p})=i-1$ for $i\ge 3$, the already parked $i-1$ cars must occupy $i-1$ consecutive spots, and either $s_1\neq 0$ or $s_n\neq 0$. Moreover, it cannot happen that both $s_1\neq 0$ and $s_n\neq 0$ at the moment $C_i$ parks, because that would mean all spots are occupied while at least one car is still to park, contradiction. Therefore, for maximum total displacement, we require either $s_1\neq 0$ or $s_n\neq 0$.

We now discuss cases under the condition that the first $i-1$ parked cars occupy consecutive spots.

- Case $s_1\neq 0$:
	- Then for every $i\ge 3$, we must have $p_i=1$ to force $C_i$ to pass all $i-1$ cars. Also, $d_1=0$ always. For $C_2$, the maximum displacement is 1, which can occur in two ways: either $s_1=s_2=1$ or $s_1=s_2\ge 2$. To have $C_3$ pass both $C_1$ and $C_2$ with $p_3=1$, we get exactly two possibilities: either $s_1=1, s_2=2$ or $s_1=2, s_2=1$, corresponding to $\vec{p}=(1, 1, 1, \dots)$ or $\vec{p}=(2, 2, 1, 1, 1, \dots)$.

- Case $s_n\neq 0$:
	- Then we must have $p_i=n$ for all $i\ge 3$. Also, for $C_2$, we need $s_1=s_2=n$ to allow $C_3$ to pass them. This yields $\vec{p}=(n, n, n, \dots)$.

Hence, for $n\ge 3$, the maximum total displacement is
$$
\sum_{i=1}^n (i-1) = \frac{n(n-1)}{2},
$$
and the three preference sequences achieving it are
$$
(1, 1, 1, \dots),\quad (2, 2, 1, 1, 1, \dots),\quad (n, n, n, \dots).
$$
Thus $t_{\frac{n(n-1)}{2}}=3$.

## Problem 5

Perhaps it is ineffective to merely derive from the sequence or to enumerate various possible cases. I have a new idea:

The final result is very likely of the form  
$$
t_m(n)=f(m,n)-g(m,n)-h(m,n),
$$
where  
$$
f(m,n)=f_1(m,n)+f_2(m,n).
$$
Here $f_1(m,n)$ counts the extensions from the $t_{m-1}(n)$ cases in which the number of spots with $q_i=0$ remains unchanged, while $f_2(m,n)$ counts those in which that number increases by one. The term $g(m,n)$ accounts for overlaps between $f_1$ and $f_2$, and $h(m,n)$ accounts for invalid cases that may arise in $f(m,n)$.

I boldly conjecture that for all $n$,
$$
\frac{\partial g}{\partial m} \ge 0.
$$
Moreover, if $n$ is sufficiently large, then
$$
\frac{\partial f}{\partial m} \ge n.
$$

## Problem 6
unit‑interval CSPF counts:  
`[1, 4, 20, 135, 1136, 11488, 135547]`

## Problem 7

## Problem 8
Related to Problem 5.
## Problem 9
Related to Problem 5.
## Problem 10
Related to Problem 5. The conjecture might be false. Starting from $n\ge 10$, the maximum of $t_m(n)$ equals $t_{n-1}(n)$, which differs from our previous guess. However, the data for $n\ge 10$ were generated by AI‑written code, which may contain errors.
## Problem 11

Related to Problem 5. It is not yet determined whether this problem needs to be solved. The conjecture in Problem 10 might be false.

## Problem 12

When $n\ge4$, $D_{\max}(\vec{p})=\dfrac{n(n-1)}{2}$, so we have $D(\vec{p})=\dfrac{n(n-1)}{2}-1$ if and only if exactly one car $C_i$ (with $i\neq 1$) satisfies $d_i(\vec{p})=d_{i,\max}(\vec{p})-1=i-2$, and all other cars $C_j$ (with $j\neq i$) satisfy $d_j(\vec{p})=d_{j,\max}(\vec{p})=j-1$.

That is, to obtain $D(\vec{p})=\dfrac{n(n-1)}{2}-1$, we take the three extremal vectors and modify one car's preference in each.

We examine the three extremal cases:

- $\vec{p}=(1, 1, 1, 1, \dots)$
	- In this case, $r_k=k$. Each new car $C_k$ passes the previous $k-1$ cars and parks at the first empty spot $s_k$. For $C_4$, we need $p_4=2$ to reduce its displacement by 1. In general, for any $i\ge 4$, the modification is $p_i=2$ and $p_j=1$ for all $j\neq i$. Let $\vec{p}_1$ denote such a vector with $p_i=2$ ($i\neq 1$) and $p_j=1$ for all $j\neq i$; there are $n-1$ such vectors.

- $\vec{p}=(n, n, n, n, \dots)$
	- Similarly, for $i\ge 4$, we set $p_i=n-1$ and $p_j=n$ for $j\neq i$. Let $\vec{p}_2$ satisfy $p_i=n-1$ ($i\neq 1$) and $p_j=n$ for all $j\neq i$; there are $n-1$ such vectors.

- $\vec{p}=(2,2,1,1,1,1\dots)$
	- Similarly, for $i\ge 4$, we set $p_1=p_2=p_i=2$ and $p_j=1$ for all $j\neq i$ with $j\ge 3$. Let $\vec{p}_3$ satisfy $p_1=p_2=p_i=2$ ($i\ge 3$) and $p_j=1$ for all $j\neq i$, or $\vec{p}_3=(2, 1, 1, 1, 1, 1,\dots)$; there are $n-1$ such vectors.

Note that to have $d_4(\vec{p})\ge 2$, the first three cars must occupy three consecutive spots, and exactly one of them must be at $s_1$ or $s_n$. The same condition applies for $k\ge 4$.

Thus, for $i\ge 4$, there are exactly 3 vectors satisfying $d_i(\vec{p})=d_{i,\max}(\vec{p})-1$. Also, $i\neq 1$ since $C_1$ always has displacement 0.

If we naïvely apply the same reasoning to $C_2$ and $C_3$, we would get $3(n-1)$ total sequences, but this does not match the computed data. We must examine the boundary cases.

First consider $C_3$. To have $d_3(\vec{p})=1$, the three "standard" patterns are:
- $\vec{p}_1=(1, 1, 2, 1, \dots)$
- $\vec{p}_2=(n, n, n-1, n, \dots)$
- $\vec{p}_3=(2, 2, 2, 1, 1, 1,\dots)$

In addition, there are two special cases:
- $\vec{p}_4=(3, 3, 2, 1, 1, 1, 1,\dots)$
- $\vec{p}_5=(n-1, n-1, n-1, n, n, n, n,\dots)$

Now consider $C_2$. There is one special case where $d_2(\vec{p})=0$ instead of its maximum 1:
- $\vec{p}_6=(n-1, n, n, n, n,\dots)$

Hence, for $n\ge 4$, the total count is
$$
3(n-1) + 1 + 2 = 3n.
$$

For $n=3$, the above special cases collapse: $\vec{p}_2=(3, 3, 2)$ and $\vec{p}_4=(3, 3, 2)$ coincide, and $\vec{p}_3=(2, 2, 2)$ and $\vec{p}_5=(2, 2, 2)$ coincide. Thus two special cases are already covered by the standard ones, so the count becomes
$$
3(n-1)+1 = 3n-2.
$$

For $n=2$, the sequences $\vec{p}_4$ and $\vec{p}_5$ do not exist (index overflow or insufficient cars), and $\vec{p}_6=(1, 2)$ coincides with $\vec{p}_1=(1, 2)$. Also, the $\vec{p}_3$ family requires $n\ge 3$, so all those $n-1$ possibilities vanish. Therefore
$$
t_{\frac{n(n-1)}{2}-1}(2)=3(n-1)-(n-1)=2n-2.
$$

For $n=1$, the expression $\frac{n(n-1)}{2}-1=-1$ is not in the domain of definition (since $D_{\max}(\vec{p})\ge 0$), so $t_{-1}(1)$ is undefined.

In summary,
$$
t_{\frac{n(n-1)}{2}-1}(n)=
\begin{cases}
2(n-1)=2n-2 & \text{if } n=2,\\
3(n-1)+1=3n-2 & \text{if } n=3,\\
3(n-1)+1+2=3n & \text{if } n\ge 4.
\end{cases}
$$
## Problem 13

$\text{Sum}(n)$ appears to be even.
## Problem 14
# Data

Here we list some important data.

## $t_m(n)$ sequences

```
n=1: [1]
sum = 0
lucky_car = 1

n=2: [2, 2]
sum = 2
lucky_car = 6

n=3: [6, 11, 7, 3]
sum = 34
lucky_car = 54

n=4: [24, 64, 70, 52, 31, 12, 3]
sum = 562
lucky_car = 640

n=5: [120, 420, 637, 637, 526, 378, 229, 112, 48, 15, 3]
sum = 10306
lucky_car = 9375

n=6: [720, 3120, 6044, 7592, 7604, 6705, 5388, 3920, 2544, 1511, 832, 411, 178, 66, 18, 3]
sum = 211776
lucky_car = 163296


n=7: [5040, 26040, 61362, 93097, 109245, 110787, 102599, 88573, 71531, 53841, 37998, 25574, 16494, 10053, 5708, 3041, 1508, 676, 265, 87, 21, 3]
sum = 4869356
lucky_car = 3294172

n=8: [40320, 241920, 671328, 1197120, 1620394, 1851530, 1905920, 1828004, 1658043, 1430855, 1175434, 921087, 694677, 508867, 362710, 250341, 166268, 106145, 65241, 38449, 21506, 11330, 5601, 2560, 1052, 376, 111, 24, 3]
sum = 124217536
lucky_car = 75497472

n=9: [362880, 2479680, 7917840, 16249656, 25025013, 31939007, 36090435, 37635471, 37067260, 34878807, 31529945, 27446686, 23049733, 18757971, 14887647, 11584136, 8849786, 6621676, 4836720, 3444507, 2393020, 1622653, 1072283, 687801, 426548, 255268, 147114, 81201, 42587, 21057, 9727, 4126, 1566, 514, 138, 27, 3]
sum = 3490099468
lucky_car = 

n=10: [3628800, 27820800, 100457280, 233397120, 404070588, 572202448, 706720634, 795256853, 838717290, 843223551, 815655100, 762901636, 691945121, 609781764, 523491582, 439568309, 362637839, 294920443, 236740766, 187411521, 146023777, 111838346, 84203724, 62356355, 45417994, 32506466, 22822564, 15690995, 10552637, 6937684, 4453458, 2784810, 1691089, 994334, 564540, 308300, 160973, 79745, 37158, 16101, 6374, 2248, 682, 168, 30, 3]
sum = 107171657344
lucky_car = 

n=11: [39916800, 339292800, 1366817760, 3547967280, 6832956174, 10664174901, 14343954001, 17369660460, 19521117840, 20783950132, 21239440165, 20993564892, 20159256217, 18858736654, 17221049549, 15380497452, 13470662650, 11606716696, 9869488429, 8301735076, 6914999429, 5702305863, 4650518570, 3747424947, 2982707910, 2345568293, 1822979788, 1400200611, 1062315483, 795442565, 587310678, 427308732, 306235566, 216088775, 150026072, 102372463, 68564094, 45013840, 28935858, 18189157, 11160622, 6668152, 3868364, 2172303, 1176619, 611812, 303482, 142509, 62764, 25606, 9505, 3131, 883, 201, 33, 3]
sum = 3573968348686
lucky_car = 

```

The sequences for $n\ge 10$ were generated by a program written with AI assistance. The sequences for larger $n$ are currently being computed and may be added later.