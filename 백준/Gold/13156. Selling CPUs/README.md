# [Gold V] Selling CPUs - 13156 

[문제 링크](https://www.acmicpc.net/problem/13156) 

### 성능 요약

메모리: 15316 KB, 시간: 156 ms

### 분류

다이나믹 프로그래밍, 배낭 문제

### 제출 일자

2025년 2월 15일 09:12:25

### 문제 설명

<p>You are very happy, that you got a job at ACME Corporation’s CPU factory. After a hard month of labor, your boss has given you c identical CPUs as payment. Apparently, ACME Corporation is low on cash at the moment.</p>

<p>Since you can’t live on CPUs alone, you want to sell them on the market and buy living essentials from the money you make. Unfortunately, the market is very restrictive in the way you are allowed to conduct business. You are only allowed to enter the market once, you can only trade with each merchant once, and you have to visit the traders in a specific order. The market organizers have marked each merchant with a number from 1 to m (the number of merchants) and you must visit them in this order. Each trader has his own price for every amount of CPUs to buy.</p>

### 입력 

 <p>The input consists of:</p>

<ul>
	<li>one line with two integers c and m (1 ≤ c, m ≤ 100), where c is the number of CPUs and m is the number of merchants;</li>
	<li>m lines describing the merchants. Each merchant is described by:</li>
	<li>one line with c integers p<sub>1</sub>, . . . , p<sub>c</sub> (1 ≤ p<sub>i</sub> ≤ 10<sup>5</sup> for all 1 ≤ i ≤ c), where pi is the amount of money the merchant will give you for i CPUs.</li>
</ul>

### 출력 

 <p>Output the maximum amount of money you can make by selling the CPUs you have.</p>

<p>Note that you don’t have to sell all CPUs.</p>

