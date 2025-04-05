# [Gold IV] Cow Roller Coaster - 6208 

[문제 링크](https://www.acmicpc.net/problem/6208) 

### 성능 요약

메모리: 26288 KB, 시간: 240 ms

### 분류

다이나믹 프로그래밍, 배낭 문제

### 제출 일자

2025년 4월 5일 22:29:27

### 문제 설명

<p>The cows are building a roller coaster! They want your help to design as fun a roller coaster as possible, while keeping to the budget.</p>

<p>The roller coaster will be built on a long linear stretch of land of length L (1 <= L <= 1,000). The roller coaster comprises a collection of some of the N (1 <= N <= 10,000) different interchangable components. Each component i has a fixed length Wi (1 <= Wi <= L). Due to varying terrain, each component i can be only built starting at location Xi (0 <= Xi <= L-Wi). The cows want to string together various roller coaster components starting at 0 and ending at L so that the end of each component (except the last) is the start of the next component.</p>

<p>Each component i has a "fun rating" Fi (1 <= Fi <= 1,000,000) and a cost Ci (1 <= Ci <= 1000). The total fun of the roller coster is the sum of the fun from each component used; the total cost is likewise the sum of the costs of each component used. The cows' total budget is B (1 <= B <= 1000). Help the cows determine the most fun roller coaster that they can build with their budget.</p>

### 입력 

 <ul>
	<li>Line 1: Three space-separated integers: L, N and B.</li>
	<li>Lines 2..N+1: Line i+1 contains four space-separated integers, respectively: Xi, Wi, Fi, and Ci.</li>
</ul>

<p> </p>

### 출력 

 <ul>
	<li>Line 1: A single integer that is the maximum fun value that a roller-coaster can have while staying within the budget and meeting all the other constraints. If it is not possible to build a roller-coaster within budget, output -1.</li>
</ul>

