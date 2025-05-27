# [Diamond V] Parity Constraint Minimum Spanning Tree - 20132 

[문제 링크](https://www.acmicpc.net/problem/20132) 

### 성능 요약

메모리: 60848 KB, 시간: 1540 ms

### 분류

자료 구조, 그래프 이론, Heavy-light 분할, 최소 공통 조상, 최소 스패닝 트리, 트리

### 제출 일자

2025년 5월 27일 22:57:44

### 문제 설명

<p>2019년에 연세대학교 최적화 연구실에서는 갑자기 특정 문제에 Parity Constraint(홀짝 제약)을 넣은 상태로 문제를 푸는 것이 유행한 적이 있었고, 졸업하신 김강산 선배도 Parity Constraint 관련 논문을 작성하셨다. 이를 기념하기 위해서 국렬이는 연세대학교 프로그래밍 경진대회에 Parity Constraint 문제를 내기로 하였다.</p>

<p>정점이 <em>N</em>개, 비용이 있는 무방향 간선이 <em>M</em>개 있는 그래프가 주어진다. 모든 정점에는 1부터 <em>N</em>까지 번호가 매겨져 있다. 임의의 정점을 선택했을 때 다른 정점으로 가는 경로는 항상 존재하며, 서로 다른 두 정점 사이에는 최대 한 개의 간선이 존재한다.</p>

<p>스패닝 트리는 주어진 그래프의 부분 그래프들 중 트리인 것을 의미한다. 스패닝 트리의 가능한 비용의 합 중 홀수인 최솟값, 짝수인 최솟값을 구하여라.</p>

### 입력 

 <p>다음과 같이 입력이 주어진다.</p>

<div style="background:#eeeeee;border:1px solid #cccccc;padding:5px 10px;"><em>N</em> <em>M</em><br>
<i>u<sub>1</sub></i> <i>v<sub>1</sub></i> <i>w<sub>1</sub></i><br>
. . .<br>
<i>u<sub>M</sub></i> <i>v<sub>M</sub></i> <i>w<sub>M</sub></i></div>

### 출력 

 <p>스패닝 트리의 가능한 비용의 합 중 홀수인 최솟값, 짝수인 최솟값을 구하여라. 만약에 해당하는 트리가 존재하지 않는 경우 <span style="color:#e74c3c;"><code>-1</code></span>을 출력한다.</p>

