# [Platinum II] 비숍2 - 2570 

[문제 링크](https://www.acmicpc.net/problem/2570) 

### 성능 요약

메모리: 14116 KB, 시간: 108 ms

### 분류

이분 매칭

### 제출 일자

2025년 6월 13일 23:06:28

### 문제 설명

<p>서양장기인 체스에는 대각선 방향으로 움직일 수 있는 비숍(bishop)이 있다. < 그림 1 >과 같이 크기가 5인 정사각형 체스판 위에 B라고 표시된 곳에 비숍이 있을 때 비숍은 대각선 방향으로 움직여 O로 표시된 칸에 있는 다른 말을 잡을 수 있다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/35843c11-738b-4146-bb36-dd8d763c0e50/-/preview/" style="width: 211px; height: 211px;"></p>

<p style="text-align: center;">< 그림 1 ><br>
 </p>

<p>그런데 체스판 위에는 비숍이 지나갈 수 없는 장애물이 놓일 수 있다. 예를 들어 < 그림 2 >와 같이 체스판 중앙에 비숍이 지나갈 수 없는 장애물이 놓이면 비숍은 장애물 오른쪽 아래 대각선 방향으로는 움직일 수 없다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/43a8950f-ff40-4a3b-805e-318685da068d/-/preview/" style="width: 211px; height: 194px;"></p>

<p style="text-align: center;">< 그림 2 ></p>

<p>정사각형 체스판의 한 변에 놓인 칸의 개수를 체스판의 크기라고 한다. 체스판의 크기와 체스판에 놓인 장애물들의 위치가 주어질 때, 체스판 위에 서로가 서로를 잡을 수 없도록 하면서 최대 몇 개의 비숍을 놓을 수 있는지를 구하는 프로그램을 작성하시오.</p>

<p>예를 들어 < 그림 2 >와 같이 체스판이 주어지면 < 그림 3 >과 같이 서로가 서로를 잡지 못하도록 하면서 최대 10개의 비숍을 놓을 수 있다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/e8ff9dc7-94db-4baa-bccc-2b2c1f16412f/-/preview/" style="width: 177px; height: 174px;"></p>

<p style="text-align: center;">< 그림 3 ></p>

### 입력 

 <p>첫째 줄에 정사각형 체스판의 크기 N이 주어진다. 둘째 줄에는 체스판 위에 놓인 장애물의 개수 M이 주어진다. N은 100이하의 자연수이고 M은 음이 아닌 정수이다. 이어 셋째 줄부터 한 줄에 하나씩 장애물의 위치가 주어진다. 장애물이 놓인 칸의 위치는 위에서부터 몇 번째 행인지와 왼쪽부터 몇 번째 열인지를 나타내는 두 개의 자연수가 차례로 주어진다.</p>

### 출력 

 <p>첫째 줄에 주어진 체스판 위에 놓을 수 있는 비숍의 최대 개수를 출력한다.</p>

