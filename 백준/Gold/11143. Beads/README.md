# [Gold I] Beads - 11143 

[문제 링크](https://www.acmicpc.net/problem/11143) 

### 성능 요약

메모리: 27876 KB, 시간: 296 ms

### 분류

자료 구조, 세그먼트 트리

### 제출 일자

2024년 10월 22일 22:05:38

### 문제 설명

<p>A game consists of putting beads in boxes. The rules of the game are too complex to describe here, but all you need to know is that keeping track of the number of beans in adjacent boxes are very important to the outcome of the game.</p>

<p>You are asked by a friend to write a program to help him win the game every time. At the start of a game, all boxes are empty.</p>

### 입력 

 <p>The first line of the input consists of a single number T, the number of games played. Each game start with a line describing B, P and Q, the number of boxes, put requests and query requests, respectively.</p>

<p>Then follows P + Q lines with either <code>P i a</code>, saying <code>a</code> beads are put in box number <code>i</code>, or <code>Q i j</code>, a query request for the number of beads in boxes <code>i</code> through <code>j</code>, inclusive.</p>

<ul>
	<li>0 < T ≤ 100</li>
	<li>0 < B ≤ 100000</li>
	<li>0 < P ≤ 30000</li>
	<li>0 < Q ≤ 30000</li>
	<li>0 ≤ a ≤ 100</li>
	<li>0 < i ≤ j ≤ B</li>
	<li>Note that boxes are 1-indexed.</li>
	<li>This is an I/O-heavy problem. For Java programmers, this means that you should use <code>BufferedReader</code> for input reading (not <code>Scanner</code>). It is also beneficial to build all output in a <code>StringBuilder</code> before printing in a single print statement.</li>
</ul>

### 출력 

 <p>For each query request, output the number of beads in boxes <code>a</code> through <code>b</code>, inclusive, that are in the boxes at this point of the game.</p>

