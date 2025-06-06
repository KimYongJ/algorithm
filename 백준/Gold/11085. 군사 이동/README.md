# [Gold III] 군사 이동 - 11085 

[문제 링크](https://www.acmicpc.net/problem/11085) 

### 성능 요약

메모리: 32112 KB, 시간: 300 ms

### 분류

그래프 이론, 자료 구조, 그래프 탐색, 분리 집합

### 제출 일자

2025년 6월 6일 19:39:09

### 문제 설명

<p>전쟁 당시 Baekjoon World의 국왕은 Cube World를 공격할 작전을 세운 적이 있습니다. Baekjoon World와 Cube World는 p개의 지점과 w개의 길로 표현됩니다. 모든 길은 양방향이며, 각 길마다 너비가 존재하여 이에 비례하는 수의 군사가 지나갈 수 있습니다.</p>

<p>Baekjoon World의 국왕은 군사들이 뭉치는 것이 유리하다고 생각해서, 미리 Cube World로 가는 경로를 정해 두고 그 경로로만 모든 군사를 보냈습니다. Baekjoon World의 국왕은 총명해서, 경로 상에 있는 길 중 너비가 가장 좁은 길의 너비를 최대화하는 경로를 택했습니다.</p>

<p>그런데 전쟁 때문에 어느 길로 보냈는지에 대한 기록이 불타 없어져 버렸습니다. 전쟁사를 완성하려면 이 기록이 꼭 필요합니다. 위대한 과학자인 당신이 다시 복구해 주세요.</p>

### 입력 

 <p>첫 줄에 p와 w가 공백을 사이에 두고 주어집니다. (2 ≤ p ≤ 1 000; 1 ≤ w ≤ 50 000)</p>

<p>다음 줄에 Baekjoon World의 수도 c와 Cube World의 수도 v가 공백을 사이에 두고 주어집니다. (0 ≤ c, v < p; c ≠ v)</p>

<p>다음 w줄에 길이 연결하는 두 지점 w<sub>start</sub>, w<sub>end</sub>,와 길의 너비 w<sub>width</sub>가 공백을 사이에 두고 주어집니다. (0 ≤ w<sub>start</sub>, w<sub>end</sub> < p; w<sub>start</sub> ≠ w<sub>end</sub>; 1 ≤ w<sub>width</sub> ≤ 1 000)</p>

### 출력 

 <p>첫 줄에 Baekjoon World의 국왕이 정한 경로 상에 있는 길 중 너비가 가장 좁은 길의 너비를 출력합니다.</p>

