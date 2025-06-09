# [Diamond IV] 두 번째로 작은 스패닝 트리 - 1626 

[문제 링크](https://www.acmicpc.net/problem/1626) 

### 성능 요약

메모리: 100824 KB, 시간: 1200 ms

### 분류

그래프 이론, 자료 구조, 트리, 최소 스패닝 트리, 최소 공통 조상, 희소 배열

### 제출 일자

2025년 6월 9일 21:39:35

### 문제 설명

<p>방향성이 없는 그래프 G가 주어진다. 문제는 G의 최소 스패닝 트리보다는 크면서 가장 작은 스패닝 트리인 'The second minimum spanning tree'를 구하는 것이다.</p>

<p style="text-align:center"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/upload/201004/smst.PNG" style="height:128px; width:273px"></p>

<p style="text-align:center">MST와 second MST의 모습</p>

### 입력 

 <p>첫째 줄에 그래프의 정점의 수 V(1 ≤ V ≤ 50,000)와 간선의 수 E(1 ≤ E ≤ 200,000)가 들어온다. 둘째 줄부터 E+1번째 줄까지 한 간선으로 연결된 두 정점과 그 간선의 가중치가 주어진다. 가중치는 100,000보다 작거나 같은 자연수 또는 0이고, 답은 2<sup>31</sup>-1을 넘지 않는다.</p>

<p>정점 번호는 1보다 크거나 같고, V보다 작거나 같은 자연수이다.</p>

### 출력 

 <p>두 번째로 작은 스패닝 트리의 값을 출력한다. 만약 스패닝 트리나 두 번째로 작은 스패닝 트리가 존재하지 않는다면 -1을 출력한다.</p>

