# [Gold V] Subset Sums - 27134 

[문제 링크](https://www.acmicpc.net/problem/27134) 

### 성능 요약

메모리: 14192 KB, 시간: 104 ms

### 분류

다이나믹 프로그래밍, 배낭 문제

### 제출 일자

2025년 2월 15일 10:03:40

### 문제 설명

<p>For many sets of consecutive integers from 1 through N (1 ≤ N ≤ 39), one can partition the set into two sets whose sums are identical.</p>

<p>For example, if N=3, one can partition the set {1, 2, 3} in one way so that the sums of both subsets are identical:</p>

<ul>
	<li>{3} and {1,2}</li>
</ul>

<p>This counts as a single partitioning (i.e., reversing the order counts as the same partitioning and thus does not increase the count of partitions).</p>

<p>If N=7, there are 4 ways to partition the set {1, 2, 3, ... 7} so that each partition has the same sum:</p>

<ul>
	<li>{1,6,7} and {2,3,4,5}</li>
	<li>{2,5,7} and {1,3,4,6}</li>
	<li>{3,4,7} and {1,2,5,6}</li>
	<li>{1,2,4,7} and {3,5,6}</li>
</ul>

<p>Given N, your program should print the number of ways a set containing the integers from 1 through N can be partitioned into two sets whose sums are identical. Print 0 if there are no such ways.</p>

<p>Your program must calculate the answer, not look it up from a table.</p>

### 입력 

 <p>The input file contains a single line with a single integer representing N, as above.</p>

### 출력 

 <p>The output file contains a single line with a single integer that tells how many same-sum partitions can be made from the set {1, 2, ..., N}. The output file should contain 0 if there are no ways to make a same-sum partition.</p>

