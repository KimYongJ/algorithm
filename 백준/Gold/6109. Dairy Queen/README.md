# [Gold V] Dairy Queen - 6109 

[문제 링크](https://www.acmicpc.net/problem/6109) 

### 성능 요약

메모리: 14128 KB, 시간: 104 ms

### 분류

다이나믹 프로그래밍, 배낭 문제

### 제출 일자

2025년 2월 15일 09:34:18

### 문제 설명

<p>Bessie, always in need of an income, has decided to leverage her dairy skills by taking a part-time job at the local Dairy Queen restaurant. She is running a special cash register (since she has hooves instead of fingers and thus requires special accommodation). Her job is making change for customers as they make a purchase.</p>

<p>As she was counting out 83 cents in change, she wondered: "How many ways can I count out 83 cents? I can use three quarters and eight pennies, seven dimes and three pennies, 83 pennies... there must be a zillion ways!"</p>

<p>How many different ways can one make change for N (1 <= N <= 300) cents using coins from a set of C (1 <= C <= 8) coins of supplied values C_i (1 <= C_i <= 200)?  "Different" means differing counts of coins.</p>

<p>Thus, 8 cents can be made, in American currency, with 1 five-cent piece + 3 one-cent pieces and also with 8 one-cent pieces. Using 3 one-cent pieces + 1 five-cent piece is the same as 1 five-cent piece + 3 one-cent pieces, so one can create eight cents in just two different ways. Note that some coin systems are poor at making change and result in an answer of '0'.</p>

<p>Coin values in the input file are listed in descending order from largest to smallest. All coin values will be distinct.</p>

<p>HINT: Consider recursion as a solution technique.</p>

### 입력 

 <ul>
	<li>Line 1: Two space-separated integers: N and C</li>
	<li>Lines 2..C+1: Line i+1 contains a single integer: C_i</li>
</ul>

<p> </p>

### 출력 

 <ul>
	<li>Line 1: A single line with a single integer that is the number of ways to create N cents of change using the supplied coins. The answer is guaranteed to fit into a signed 32-bit integer.</li>
</ul>

<p> </p>

