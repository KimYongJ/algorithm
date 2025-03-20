# [Gold III] Circuit Counting - 11089 

[문제 링크](https://www.acmicpc.net/problem/11089) 

### 성능 요약

메모리: 221100 KB, 시간: 460 ms

### 분류

다이나믹 프로그래밍, 배낭 문제

### 제출 일자

2025년 3월 20일 10:47:45

### 문제 설명

<p>Suppose you are given a sequence of N integer-valued vectors in the plane (x<sub>i</sub>, y<sub>i</sub>), i = 1, . . . , N. Beginning at the origin, we can generate a path by regarding each vector as a displacement from the previous location. For instance, the vectors (1, 2), (2, 3), (−3, −5) form the path (0, 0),(1, 2),(3, 5),(0, 0). We define a path that ends at the origin as a circuit. The example just given is a circuit. We could form a path using any nonempty subset of the N vectors, while the result (circuit or not) doesn’t depend on the ordering of the subset. How many nonempty subsets of the vectors form circuits?</p>

<p>For instance, consider the vectors {(1, 2),(−1, −2),(1, 1),(−2, −3),(−1, −1)} From these vectors we can construct 4 possible subset circuits using</p>

<p style="text-align:center">{(1, 2), (−1, −2)}<br>
{(1, 1), (−1, −1)}<br>
{(1, 2), (1, 1), (−2, −3)}<br>
{(1, 2), (−1, −2), (1, 1), (−1, −1)}</p>

### 입력 

 <p>Input begins with an integer N ≤ 40 on the first line. The next N lines each contain two integer values x and y forming the vector (x, y), where |x|, |y| ≤ 10 and (x, y) ≠ (0, 0). Since the given vectors are a set, all vectors are unique.</p>

### 출력 

 <p>Output the number of nonempty subsets of the given vectors that produce circuits. It’s guaranteed that the answer is less than 10<sup>10</sup>.</p>

