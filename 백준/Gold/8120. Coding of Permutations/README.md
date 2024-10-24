# [Gold III] Coding of Permutations - 8120 

[문제 링크](https://www.acmicpc.net/problem/8120) 

### 성능 요약

메모리: 20472 KB, 시간: 272 ms

### 분류

이분 탐색, 자료 구조, 구현, 세그먼트 트리

### 제출 일자

2024년 10월 24일 21:04:16

### 문제 설명

<p>Every permutation A = (a<sub>1</sub>, ..., a<sub>n</sub>) of numbers 1, ..., n can be coded by a sequence B = (b<sub>1</sub>, ..., b<sub>n</sub>) in which b<sub>i</sub> equals the number of all a<sub>j</sub> such that (j < i & a<sub>j</sub> > a<sub>i</sub>), for i = 1, ..., n.</p>

<p>The sequence B = (0, 0, 1, 0, 2, 0, 4) is the code of the permutation A = (1, 5, 2, 6, 4, 7, 3).</p>

<p>Write a program that:</p>

<ul>
	<li>reads from the standard input the length n and successive elements of the sequence B,</li>
	<li>examines whether it is a code of some permutation of numbers 1, ..., n,</li>
	<li>if so, it finds that permutation and writes it in the standard output,</li>
	<li>otherwise it writes in the standard output one word <code>NIE</code> ("<i>no</i>").</li>
</ul>

### 입력 

 <ul>
	<li>In the first line of the standard input there is a positive integer n ≤ 30,000. It is the number of elements of the sequence B.</li>
	<li>In each of the following n lines there is one nonnegative integer not greater than 30,000. It is the successive element of the sequence B.</li>
</ul>

### 출력 

 <p>The standard output should contain:</p>

<ul>
	<li>in each of n consecutive lines - one element of the permutation A, whose code is the sequence B written in the standard input,</li>
	<li>one word <code>NIE</code>, if the sequence B is not a code of any permutation.</li>
</ul>

