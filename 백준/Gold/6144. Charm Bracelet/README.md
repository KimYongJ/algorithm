# [Gold V] Charm Bracelet - 6144 

[문제 링크](https://www.acmicpc.net/problem/6144) 

### 성능 요약

메모리: 14436 KB, 시간: 168 ms

### 분류

다이나믹 프로그래밍, 배낭 문제

### 제출 일자

2025년 2월 12일 09:22:16

### 문제 설명

<p>Bessie has gone to the mall's jewelry store and spies a charm bracelet. Of course, she'd like to fill it with the best charms possible from the N (1 <= N <= 3,402) available charms. Each charm i in the supplied list has a weight W_i (1 <= W_i <= 400), a 'desirability' factor D_i (1 <= D_i <= 100), and can be used at most once.  Bessie can only support a charm bracelet whose weight is no more than M (1 <= M <= 12,880).</p>

<p>Given that weight limit as a constraint and a list of the charms with their weights and desirability rating, deduce the maximum possible sum of ratings.</p>

### 입력 

 <ul>
	<li>Line 1: Two space-separated integers: N and M</li>
	<li>Lines 2..N+1: Line i+1 describes charm i with two space-separated integers: W_i and D_i</li>
</ul>

<p> </p>

### 출력 

 <ul>
	<li>Line 1: A single integer that is the greatest sum of charm desirabilities that can be achieved given the weight constraints</li>
</ul>

<p> </p>

