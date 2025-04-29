# [Platinum I] Tree - 13038 

[문제 링크](https://www.acmicpc.net/problem/13038) 

### 성능 요약

메모리: 87260 KB, 시간: 944 ms

### 분류

자료 구조, 오일러 경로 테크닉, Heavy-light 분할, 최소 공통 조상, 세그먼트 트리, 트리

### 제출 일자

2025년 4월 29일 09:52:49

### 문제 설명

<p>Nick has got a rooted tree with n vertices as his birthday present.</p>

<p>Recall that a rooted tree is a connected undirected graph, one vertex is chosen as a root. For each vertex the closest to the root neighbor is called the parent of this vertex, other neighbors are its children. The root has no parent. Nick's tree has vertices numbered from 1 to n, vertex with number 1 is the tree root.</p>

<p>Nick has decided to play with his tree. He chooses some vertex u and removes it, connecting the vertices that were its children to the former parent vertex of u. The root is never removed. But it is not interesting to just remove the vertices, so sometimes he asks for the length of the path from one vertex to another in the current tree. Answer to Nick's questions.</p>

### 입력 

 <p>The first line of the input contains integer n (1 ≤ n ≤ 100 000) — the number of vertices in the initial tree. The second line contains n - 1 integers p<sub>i</sub> (1 ≤ p<sub>i</sub> ≤ n) — parents of vertices 2, 3, ..., n.</p>

<p>The third line contains integer q (1 ≤ q ≤ 100 000) — the number of queries. Each of the following q lines contains queries. Each query starts with a query type — an integer equal either to 1 or to 2. If the query type is 1, it is followed by a and b (1 ≤ a, b ≤ n) — the numbers of vertices the distance between which must be found. If the query type is 2, one integer v (1 ≤ v ≤ n) follows — the number of the vertex to remove.</p>

<p>It is guaranteed that the initial tree is given correctly, the vertices in queries are not removed yet, and the root is never removed.</p>

### 출력 

 <p>For each query of type 2 print the distance between the corresponding vertices, one per line.</p>

<p> </p>

