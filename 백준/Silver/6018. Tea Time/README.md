# [Silver I] Tea Time - 6018 

[문제 링크](https://www.acmicpc.net/problem/6018) 

### 성능 요약

메모리: 14416 KB, 시간: 120 ms

### 분류

자료 구조, 분리 집합, 그래프 이론, 그래프 탐색

### 제출 일자

2025년 7월 5일 23:18:28

### 문제 설명

<p>N (1 <= N <= 1000) cows, conveniently numbered 1..N all attend a tea time every day. M (1 <= M <= 2,000) unique pairs of those cows have already met before the first tea time. Pair i of these cows who have met is specified by two differing integers A_i and B_i (1 <= A_i <= N; 1 <= B_i <= N). The input never indicates that cows have met each other more than once.</p>

<p>At tea time, any cow i and cow j who have met a mutual friend cow k will meet sometime during that tea time and thus expand their circle of known cows.</p>

<p>Determine whether Q (1 <= Q <= 100) pairs of cows have met after tea times are held for long enough that no new cow meetings are occurring. Query j consists of a pair of different cows X_j and Y_j (1 <= X_j <= N; 1 <= Y_j <= N).</p>

<p>For example, suppose that out of cows 1 through 5, we know that 2 has met 5, 2 has met 3, and 4 has met 5; see (a) below.</p>

<pre>   2---3           2---3            2---3
    \              |\  |            |\ /|
1    \     -->  1  | \ |    -->  1  | X |
      \            |  \|            |/ \|
   4---5           4---5            4---5
    (a)             (b)              (c)</pre>

<p>In the first tea time, cow 2 meets cow 4, and cow 3 meets cow 5; see (b) above. In the second tea time, cow 3 meets cow 4; see (c) above.</p>

### 입력 

 <ul>
	<li>Line 1: Three space-separated integers: N, M, and Q</li>
	<li>Lines 2..M+1: Line i+1 contains two space-separated integers: A_i and B_i</li>
	<li>Lines M+2..M+Q+1: Line j+M+1 contains query j as two space-separated integers: X_j and Y_j</li>
</ul>

<p> </p>

### 출력 

 <ul>
	<li>Lines 1..Q: Line j should be "Y" if the cows in the jth query have met and "N" if they have not met.</li>
</ul>

<p> </p>

