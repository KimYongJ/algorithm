# [Platinum I] Jumping Path - 17997 

[문제 링크](https://www.acmicpc.net/problem/17997) 

### 성능 요약

메모리: 716812 KB, 시간: 6648 ms

### 분류

자료 구조, Heavy-light 분할, 가장 긴 증가하는 부분 수열 문제, 세그먼트 트리, 트리

### 제출 일자

2025년 5월 26일 13:54:58

### 문제 설명

<p>You are given a rooted tree where each vertex is labeled with a non-negative integer.</p>

<p>Define a <em>Jumping Path</em> of vertices to be a sequence of vertices <em>v</em><sub>1</sub>, <em>v</em><sub>2</sub>, ..., <em>v<sub>k</sub></em> where <em>v<sub>i</sub></em> is an ancestor of <em>v<sub>j</sub></em> for all <em>i</em> < <em>j</em>. Note that <em>v<sub>i</sub></em> is an ancestor of <em>v</em><sub><em>i</em>+1</sub>, but not necessarily the parent of <em>v</em><sub><em>i</em>+1</sub> (hence the <em>jumping part</em> of a <em>jumping path</em>).</p>

<p>Compute two quantities:</p>

<ul>
	<li>The length (number of vertices) of the longest <em>jumping path</em> where the labels of the vertices are nondecreasing.</li>
	<li>The number of <em>jumping paths</em> of that length where the labels of the vertices are nondecreasing.</li>
</ul>

### 입력 

 <p>The first line of input contains an integer <em>n</em> (1 ≤ <em>n</em> ≤ 10<sup>6</sup>), which is the number of vertices in the tree. Vertices are numbered from 1 to <em>n</em>, with vertex 1 being the tree root.</p>

<p>Each of the next <em>n</em> lines contains an integer <em>x</em> (0 ≤ <em>x</em> ≤ 10<sup>6</sup>), which are the labels of the vertices, in order.</p>

<p>Each of the next <em>n</em> − 1 lines contains an integer <em>p</em> (1 ≤ <em>p</em> ≤ <em>n</em>), which are the parents of nodes 2 through <em>n</em>, in order.</p>

<p>It is guaranteed that the vertices form a single tree, i.e., they are connected and acyclic.</p>

### 출력 

 <p>Output a single line with two integers separated by a space.</p>

<p>The first integer is length of the longest <em>jumping path</em> where the labels of the vertices are nondecreasing. The second integer is the number of <em>jumping paths</em> of that length where the labels of the vertices are nondecreasing. As the second integer may be large, give its value modulo 11092019.</p>

