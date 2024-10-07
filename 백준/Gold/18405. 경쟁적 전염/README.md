# [Gold V] 경쟁적 전염 - 18405 

[문제 링크](https://www.acmicpc.net/problem/18405) 

### 성능 요약

메모리: 21072 KB, 시간: 212 ms

### 분류

너비 우선 탐색, 그래프 이론, 그래프 탐색, 구현

### 제출 일자

2024년 10월 7일 19:55:39

### 문제 설명

<p><em>N</em>x<em>N</em> 크기의 시험관이 있다. 시험관은 1x1 크기의 칸으로 나누어지며, 특정한 위치에는 바이러스가 존재할 수 있다. 모든 바이러스는 1번부터 <em>K</em>번까지의 바이러스 종류 중 하나에 속한다.</p>

<p>시험관에 존재하는 모든 바이러스는 1초마다 상, 하, 좌, 우의 방향으로 증식해 나간다. 단, 매 초마다 번호가 낮은 종류의 바이러스부터 먼저 증식한다. 또한 증식 과정에서 특정한 칸에 이미 어떠한 바이러스가 존재한다면, 그 곳에는 다른 바이러스가 들어갈 수 없다.</p>

<p>시험관의 크기와 바이러스의 위치 정보가 주어졌을 때, <em>S</em>초가 지난 후에 (X,Y)에 존재하는 바이러스의 종류를 출력하는 프로그램을 작성하시오. 만약 <em>S</em>초가 지난 후에 해당 위치에 바이러스가 존재하지 않는다면, 0을 출력한다. 이 때 X<font face="sans-serif, Arial, Verdana, Trebuchet MS, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol">와 </font>Y는 각각 행과 열의 위치를 의미하며, 시험관의 가장 왼쪽 위에 해당하는 곳은 (1,1)에 해당한다.</p>

<p>예를 들어 다음과 같이 3x3 크기의 시험관이 있다고 하자. 서로 다른 1번, 2번, 3번 바이러스가 각각 (1,1), (1,3), (3,1)에 위치해 있다. 이 때 2초가 지난 뒤에 (3,2)에 존재하는 바이러스의 종류를 계산해보자.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/02958527-debb-46e3-a56d-79b87ad46d03/-/preview/" style="height: 148px; width: 250px;"></p>

<p style="text-align: justify;">1초가 지난 후에 시험관의 상태는 다음과 같다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/9ab631e7-1f1d-42fe-b8b8-95ded9bce52d/-/preview/" style="height: 148px; width: 250px;"></p>

<p>2초가 지난 후에 시험관의 상태는 다음과 같다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/35ea13c6-9ee3-40e1-8b21-c37beaf46c59/-/preview/" style="height: 148px; width: 250px;"></p>

<p style="text-align: justify;">결과적으로 2초가 지난 뒤에 (3,2)에 존재하는 바이러스의 종류는 3번 바이러스다. 따라서 3을 출력하면 정답이다.</p>

### 입력 

 <p>첫째 줄에 자연수 <em>N</em>, <em>K</em>가 공백을 기준으로 구분되어 주어진다. (1 ≤ <em>N</em> ≤ 200, 1 ≤ <em>K</em> ≤ 1,000) 둘째 줄부터 <em>N</em>개의 줄에 걸쳐서 시험관의 정보가 주어진다. 각 행은 <em>N</em>개의 원소로 구성되며, 해당 위치에 존재하는 바이러스의 번호가 공백을 기준으로 구분되어 주어진다. 단, 해당 위치에 바이러스가 존재하지 않는 경우 0이 주어진다. 또한 모든 바이러스의 번호는 <em>K</em>이하의 자연수로만 주어진다. <em>N</em>+2번째 줄에는 <em>S</em>, <em>X</em>, <em>Y</em>가 공백을 기준으로 구분되어 주어진다. (0 ≤ <em>S</em> ≤ 10,000, 1 ≤ <em>X</em>, <em>Y</em> ≤ <em>N</em>)</p>

### 출력 

 <p><em>S</em>초 뒤에 (X,Y)에 존재하는 바이러스의 종류를 출력한다. 만약 <em>S</em>초 뒤에 해당 위치에 바이러스가 존재하지 않는다면, 0을 출력한다.</p>

