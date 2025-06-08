# [Diamond IV] Minimum Spanning Tree - 10637 

[문제 링크](https://www.acmicpc.net/problem/10637) 

### 성능 요약

메모리: 110480 KB, 시간: 1480 ms

### 분류

자료 구조, 분리 집합, 그래프 이론, Heavy-light 분할, 느리게 갱신되는 세그먼트 트리, 최소 공통 조상, 최소 스패닝 트리, 세그먼트 트리, 트리

### 제출 일자

2025년 6월 8일 19:18:33

### 문제 설명

<p>You are given an undirected weighted graph G with n nodes and m edges. Each edge is numbered from 1 to m.</p>

<p>Let G<sub>i</sub> be an graph that is made by erasing i-th edge from G. Your task is to compute the cost of minimum spanning tree in G<sub>i</sub> for each i.</p>

### 입력 

 <p>The dataset is formatted as follows.</p>

<pre>n m
a<sub>1</sub> b<sub>1</sub> w<sub>1</sub>
...
a<sub>m</sub> b<sub>m</sub> w<sub>m</sub>
</pre>

<p>The first line of the input contains two integers n (2 ≤ n ≤ 100,000) and m (1 ≤ m ≤ 200,000). n is the number of nodes and m is the number of edges in the graph. Then m lines follow, each of which contains a<sub>i</sub> (1 ≤ a<sub>i</sub> ≤ n), b<sub>i</sub> (1 ≤ b<sub>i</sub> ≤ n) and w<sub>i</sub> (0 ≤ w<sub>i</sub> ≤ 1,000,000). This means that there is an edge between node a<sub>i</sub> and node b<sub>i</sub> and its cost is w<sub>i</sub>. It is guaranteed that the given graph is simple: That is, for any pair of nodes, there is at most one edge that connects them, and a<sub>i</sub> ≠ b<sub>i</sub> for all i.</p>

### 출력 

 <p>Print the cost of minimum spanning tree in G<sub>i</sub> for each i, in m line. If there is no spanning trees in G<sub>i</sub>, print "-1" (quotes for clarity) instead.</p>

