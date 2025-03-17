# [Gold III] Dividing the Gold - 5954 

[문제 링크](https://www.acmicpc.net/problem/5954) 

### 성능 요약

메모리: 15932 KB, 시간: 260 ms

### 분류

다이나믹 프로그래밍, 배낭 문제

### 제출 일자

2025년 3월 17일 13:57:32

### 문제 설명

<p>Bessie and Canmuu found a sack of N (1 <= N <= 250) gold coins that they wish to divide as evenly as possible. Coin i has value v_i (1 <= V_i <= 2,000). The cows would like to split the pile as evenly as they can, but that is not always possible. What is the smallest difference between the values of the two piles?</p>

<p>In addition, the Bessie and Canmuu have found that there might be multiple ways to split the piles with that minimum difference. They would also like to know the number of ways to split the coins as fairly as possible. If it isn't possible to split the piles evenly, Bessie will get the higher-valued pile.</p>

<p>By way of example, consider a sack of five coins of values: 2, 1, 8, 4, and 16. Bessie and Canmuu split the coins into two piles, one pile with one coin worth 16, and the other pile with the remaining coins worth 1+2+4+8=15. Therefore the difference is 16-15 = 1. This is the only way for them to split the coins this way, so the number of ways to split it evenly is just 1.</p>

<p>Note that same-valued coins can be switched among the piles to increase the number of ways to perform an optimal split. Thus, the set of coins {1, 1, 1, 1} has six different ways to split into two optimal partitions, each with two coins.</p>

### 입력 

 <ul>
	<li>Line 1: A single integer: N</li>
	<li>Lines 2..N+1: Line i+1 contains a single integer: V_i</li>
</ul>

<p> </p>

### 출력 

 <ul>
	<li>Line 1: A single integer that is the smallest difference of two partitions.</li>
	<li>Line 2: A single integer that is the number of ways to split the coins with the minimum difference printed in line 1. Since this number can get quite large, print the remainder when divided by 1,000,000.</li>
</ul>

<p> </p>

