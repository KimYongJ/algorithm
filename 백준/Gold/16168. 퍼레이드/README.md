# [Gold IV] 퍼레이드 - 16168 

[문제 링크](https://www.acmicpc.net/problem/16168) 

### 성능 요약

메모리: 14060 KB, 시간: 108 ms

### 분류

그래프 이론, 자료 구조, 분리 집합, 오일러 경로

### 제출 일자

2025년 6월 7일 17:24:28

### 문제 설명

<p>종우는 18학번을 대표하여 중앙대학교 개교 100주년 기념 퍼레이드의 경로 선정 위원으로 선정되었다. 퍼레이드의 경로는 일정한 지점들과 두 지점을 연결하는 연결 구간으로 이루어져 있다. 종우는 모든 지점을 지나면서 모든 연결 구간들을 지나고 싶어한다.</p>

<p>하지만 같은 연결 구간을 두 번 이상 지날 경우 그 구간의 주민들이 민원을 제기하게 된다. 단, 같은 지점은 두 번 이상 지나도 된다.</p>

<p>민원을 받지 않으면서 모든 구간을 지나도록 퍼레이드를 만들고 싶은 종우를 위한 프로그램을 작성해보도록 하자.</p>

### 입력 

 <p>첫 번째 줄에 지점의 개수 V, 연결 구간의 개수 E가 주어진다. (1 <span style="font-size:10.0pt"><span style="font-family:나눔고딕"><span style="color:black"><span style="language:en-US">≤ </span></span></span></span>V <span style="font-size:10.0pt"><span style="font-family:나눔고딕"><span style="color:black"><span style="language:en-US">≤ </span></span></span></span>E <span style="font-size:10.0pt"><span style="font-family:나눔고딕"><span style="color:black"><span style="language:en-US">≤ </span></span></span></span>3000) 이후 E개의 줄에 걸쳐 각 연결 구간이 연결하는 두 지점의 번호 V<sub>a</sub>, V<sub>b</sub>가 공백을 사이에 두고 주어진다. (1 <span style="font-size:10.0pt"><span style="font-family:나눔고딕"><span style="color:black"><span style="language:en-US">≤ </span></span></span></span>V<sub>a</sub>, V<sub>b</sub> <span style="font-size:10.0pt"><span style="font-family:나눔고딕"><span style="color:black"><span style="language:en-US">≤ </span></span></span></span>V, V<sub>a</sub> <span style="font-size:10pt"><span style="line-height:115%"><span style="font-family:"맑은 고딕"">≠</span></span></span> V<sub>b</sub>)</p>

<p>서로 다른 두 연결 구간 (V<sub>a1</sub>, V<sub>b1</sub>), (V<sub>a2</sub>, V<sub>b2</sub>) 에서 V<sub>a1</sub> = V<sub>a2</sub> & V<sub>b1</sub> = V<sub>b2</sub> 인 경우는 존재하지 않으며, 임의의 지점에 적어도 하나의 연결 구간이 연결되어 있음이 보장된다.</p>

### 출력 

 <p>종우가 원하는 노선을 만들 수 있다면 YES, 아니면 NO를 출력한다.</p>

