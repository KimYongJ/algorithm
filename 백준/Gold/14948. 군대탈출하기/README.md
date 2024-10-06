# [Gold II] 군대탈출하기 - 14948 

[문제 링크](https://www.acmicpc.net/problem/14948) 

### 성능 요약

메모리: 31832 KB, 시간: 244 ms

### 분류

너비 우선 탐색, 이분 탐색, 그래프 이론, 그래프 탐색

### 제출 일자

2024년 10월 6일 21:58:21

### 문제 설명

<p>기윤이는 군대 탈출 게임을 좋아한다. 이 게임을 완료하기 위해서는 병영을 통과해 탈출해야 한다. 병영의 모습은 군기를 위해 항상 n x m 직사각형 모양이다.</p>

<p>블록(0,0)에서 출발하여 병영 밖으로 나가지 않고 상, 하, 좌, 우 4방향으로만 이동하여 블록(n-1,m-1)에 도착해야 병영을 탈출 한 것 이다. 즉, 반드시 블록(0,0)과 블록(n-1,m-1)을 밟아야 한다.</p>

<p>각 블록은 레벨 제한이 있다. 만약 블록의 숫자가 3이라면 최소한 레벨 3이 되어야 그 블록을 지나갈 수 있다는 뜻이다.</p>

<p style="text-align:center"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/14948/1.png" style="height:117px; width:123px"></p>

<p style="text-align:center"><strong>4x3 </strong><strong>병영</strong> <strong>타일</strong> <strong>번호</strong></p>

<p style="text-align:center"><strong><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/14948/2.png" style="height:120px; width:126px"></strong></p>

<p style="text-align:center"><strong>타일</strong> <strong>레벨</strong> <strong>제한</strong></p>

<p>위와 같은 병영이 주어졌을 때 병영을 탈출 하기 위해 필요한 레벨은 4이다.</p>

<p>(2-3-4-1-3-2 : 최댓값 4)</p>

<p>그러나 기윤이는 공군의 특수장비를 사용하여 단 한번 타일을 무시하고 건너뛰어 다음 타일로 갈 수 있다.</p>

<p>특수장비의 조건은 다음과 같다.</p>

<ol>
	<li>타일을 뛰어넘는 도중에 방향을 바꿀 수 없다.</li>
	<li>병영 밖으로는 넘어갈 수 없다.</li>
</ol>

<p>그러므로, 기윤이가 특수장비를 사용한 경우, 위의 예시에서 필요한 레벨의 최소 값은 3이다.</p>

<p style="text-align:center"> <img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/14948/3.png" style="height:118px; width:126px"></p>

<p style="text-align:center">(2-3-(12)-1-3-2 : 최댓값 3)</p>

<p>기윤이가 병영을 탈출하기 위해 달성해야 하는 최소한의 레벨을 알려주자!</p>

### 입력 

 <p>첫 줄에 각 병영의 세로 길이 n, 가로 길이 m 이 주어진다. (1 ≤ n, m ≤ 100)</p>

<p>다음 줄부터 차례대로 병영의 블록별 레벨 제한 k가 주어진다. (0 ≤ k ≤ 10<sup>9</sup>).</p>

### 출력 

 <p>기윤이가 병영을 탈출하기 위해 달성해야 하는 최소한의 레벨을 출력한다.</p>

