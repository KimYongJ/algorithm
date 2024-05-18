# [Gold IV] Tree - 13244 

[문제 링크](https://www.acmicpc.net/problem/13244) 

### 성능 요약

메모리: 17904 KB, 시간: 156 ms

### 분류

자료 구조, 깊이 우선 탐색, 분리 집합, 그래프 이론, 그래프 탐색, 트리

### 제출 일자

2024년 5월 18일 18:19:42

### 문제 설명

<p>One of the most important data structures in computer science is the tree. You already dealt with binary trees in the qualification round. This problem is about general trees.</p>

<p>Trees are the subset of graphs that have the following 3 properties:</p>

<ol>
	<li>It is connected: for every node you can reach every other node following edges.</li>
	<li>If an edge is removed, the graph is no longer connected. That is, some nodes cannot be reached anymore.</li>
	<li>When an edge is added between two existing nodes A and B, a cycle is created. There is a cycle if there is more than one way to go from A to B.</li>
</ol>

<p>Your task is to decide if a given graph is a tree or not.</p>

### 입력 

 <p>The first line will contain an integer T representing the number of graphs to check. There will be at most 10 graphs in each test case.</p>

<p>Each of the graph will be represented as follows:</p>

<p>The first line will contain an integer N with the number of nodes in the graph. The number of nodes will be between 1 and 1,000. The identifier of each node will be an integer from 1 to N. </p>

<p>The next line will contain an integer M with the number of edges in the graph. There will be at most 10<sup>6</sup> edges.</p>

<p>The next M lines will contain 2 integers A and B each. These are the two nodes connected by an edge.</p>

<p>The total sum of M in all test cases is at most 10<sup>6</sup>.</p>

### 출력 

 <p>For each graph, a single line with “tree” if the graph represents a tree or “graph“ otherwise.</p>

