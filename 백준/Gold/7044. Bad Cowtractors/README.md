# [Gold IV] Bad Cowtractors - 7044 

[문제 링크](https://www.acmicpc.net/problem/7044) 

### 성능 요약

메모리: 15472 KB, 시간: 152 ms

### 분류

그래프 이론, 최소 스패닝 트리

### 제출 일자

2025년 6월 21일 08:58:56

### 문제 설명

<p>Bessie has been hired to build a cheap internet network among Farmer John's N (2 <= N <= 1,000) barns that are conveniently numbered 1..N. FJ has already done some surveying, and found M (1 <= M <= 20,000) possible connection routes between pairs of barns. Each possible connection route has an associated cost C (1 <= C <= 100,000). Farmer John wants to spend the least amount on connecting the network; he doesn't even want to pay Bessie. </p>

<p>Realizing Farmer John will not pay her, Bessie decides to do the worst job possible. She must decide on a set of connections to install so that (i) the total cost of these connections is as large as possible, (ii) all the barns are connected together (so that it is possible to reach any barn from any other barn via a path of installed connections), and (iii) so that there are no cycles among the connections (which Farmer John would easily be able to detect). Conditions (ii) and (iii) ensure that the final set of connections will look like a "tree".</p>

### 입력 

 <ul>
	<li>Line 1: Two space-separated integers: N and M </li>
	<li>Lines 2..M+1: Each line contains three space-separated integers A, B, and C that describe a connection route between barns A and B of cost C.</li>
</ul>

### 출력 

 <ul>
	<li>Line 1: A single integer, containing the price of the most expensive tree connecting all the barns. If it is not possible to connect all the barns, output -1.</li>
</ul>

