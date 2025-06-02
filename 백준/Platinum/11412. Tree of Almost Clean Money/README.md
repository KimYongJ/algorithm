# [Platinum I] Tree of Almost Clean Money - 11412 

[문제 링크](https://www.acmicpc.net/problem/11412) 

### 성능 요약

메모리: 300228 KB, 시간: 7024 ms

### 분류

자료 구조, Heavy-light 분할, 세그먼트 트리, 트리

### 제출 일자

2025년 6월 2일 15:44:23

### 문제 설명

<p>The tree of Almost Clean Money (or ACM Tree, for short) consists of N (1≤N≤500000) vertices in which, well, (almost clean) money is growing (contrary to the old saying that money doesn’t grow on trees). The vertices are numbered from 0 to N-1, with vertex 0 being the root of the tree. Every vertex i except vertex 0 has a parent p(i) in the tree, such that p(i)<i. Initially, every vertex contains v(i) (0≤v(i)<1000000007) monetary units. Due to its special properties, the tree has attracted the attention of a large money laundering organization, who wants to use the tree for its money “cleansing” business. This organization wants to execute Q (1≤Q≤50000) operations on the tree. Each operation consists of two steps:</p>

<ol>
	<li>In step 1, K (1≤K≤1000) vertices from the tree are chosen: x(1), …, x(K) (0≤x(i)≤N-1) – the same vertex may be selected multiple times here. In each of these vertices, an amount of monetary units is added (thus increasing the amount of monetary units in them). More exactly, y(i) (0≤y(i)<1000000007) monetary units are added to the selected vertex x(i) (1≤i≤K).</li>
	<li>In step 2, two vertices u and v (0≤u,v≤N-1) are chosen and the organization wants to know the total amount of money found in the vertices located on the unique path in the tree between the vertices u and v (with u and v inclusive).</li>
</ol>

<p>The organization hired you to find the answer for step 2 of each of the Q operations and promised you a hefty amount of money if you succeed.</p>

### 입력 

 <p>The first line of input contains the number of tree vertices N. The next N-1 lines contain two space-separated integers, p(i) and i, each describing an edge of the tree. The next line contains N space-separated values: the initial amount of monetary units in each vertex, v(0), …, v(N-1). The next line contains the number of operations Q. Each of the next Q lines describes an operation. Each operation is described by 9 space-separated integers, in this order: K, x(1), y(1), A, B, C, D, u, v (0≤A,B,C,D<1000000007). The values x(2≤i≤K) and y(2≤i≤K) are generated as follows:</p>

<ul>
	<li>x(i) = (A*x(i-1) + B) modulo N</li>
	<li>y(i) = (C*y(i-1) + D) modulo 1000000007</li>
</ul>

### 출력 

 <p>For each of the Q operations print a line containing the answer to step 2 of the operation. When computing the answer for an operation, the effects of steps 1 from previous operations need to be considered, too (i.e. after adding y(i) monetary units to a vertex x(i), these units remain added to the vertex when executing subsequent operations, too).</p>

