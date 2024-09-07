# [Platinum V] 자물쇠 - 2478 

[문제 링크](https://www.acmicpc.net/problem/2478) 

### 성능 요약

메모리: 19224 KB, 시간: 236 ms

### 분류

구현

### 제출 일자

2024년 9월 7일 22:12:53

### 문제 설명

<p>띠 모양의 자물쇠가 있다. 이 자물쇠는 한 줄로 늘어선 N개의 칸으로 이루어져 있고, 각 칸에는 1부터 N까지의 숫자가 하나씩 들어 있다. 맨 처음에는 1번째 칸부터 N번째 칸까지 1부터 N까지 숫자가 순서대로 하나씩 들어 있다. 아래 그림 1은 10개의 칸으로 이루어진 자물쇠의 맨 처음 모양을 보여주고 있다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/50c10caf-437f-4e2e-bd9a-d62fa2299a63/-/preview/" style="width: 337px; height: 43px;"></p>

<p style="text-align: center;">그림 1</p>

<p>이 자물쇠를 잠그기 위해서는 다음과 같은 3회의 동작을 연속적으로 수행해야 한다.</p>

<ol>
	<li>왼쪽으로 밀기</li>
	<li>구간 뒤집기</li>
	<li>왼쪽으로 밀기</li>
</ol>

<p>첫 번째 동작은 왼쪽으로 밀기이다. 칸 밖으로 밀려나간 번호는 다시 오른쪽으로 돌아온다. 그림 1의 자물쇠를 왼쪽으로 3칸 밀고 나면 그림 2와 같게 된다. 이렇게 왼쪽으로 k칸 밀기 동작을 k-<strong>왼쪽밀기</strong>라고 부른다. 이때 1 ≤ k < N이다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/d6370f4b-2405-40ea-83e9-f952505bb876/-/preview/" style="width: 337px; height: 39px;"></p>

<p style="text-align: center;">그림 2</p>

<p>그 다음 동작은 정해진 구간의 숫자를 뒤집는 것이다. 예를 들어 그림 2의 자물쇠에서 7번째 칸에서부터 9번째 칸까지 숫자 <10,1,2>를 뒤집으면 다음 그림 3과 같게 된다. p번째 칸부터 q번째 칸까지 숫자들을 뒤집는 동작을 (p,q)-<strong>구간뒤집기</strong>라고 한다. 이때 항상 p<q이다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/9e843ea9-0df6-4c14-8f92-4cfdb9afaa81/-/preview/" style="width: 337px; height: 39px;"></p>

<p style="text-align: center;">그림 3</p>

<p>이 상황에서 다시 5-왼쪽밀기 동작을 수행하였다면 자물쇠 모양은 아래 그림 4와 같게 된다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/1daeb024-6484-4825-bad8-2c4a073ee2c1/-/preview/" style="width: 337px; height: 39px;"></p>

<p style="text-align: center;">그림 4</p>

<p>위에서 3-왼쪽밀기, (7,9)-구간뒤집기, 다시 5-왼쪽밀기의 동작을 차례로 수행하여 자물쇠를 잠궜다.</p>

<p>잠긴 자물쇠의 마지막 상태를 입력으로 받아서 그렇게 만든 3회의 동작을 찾아내는 프로그램을 작성하시오. 예를 들어 자물쇠 모양이 그림 4와 같다면 그 답은 3-왼쪽밀기, (7,9)-구간뒤집기, 5-왼쪽밀기이다.</p>

### 입력 

 <p>첫째 줄에 자물쇠에 있는 칸의 수를 나타내는 정수 N이 주어진다. N은 10 이상 500 이하이다. 둘째 줄에는 잠겨 있는 자물쇠의 1번째 칸부터 N번째 칸까지 들어 있는 숫자들이 순서대로 빈칸을 사이에 두고 입력된다.</p>

### 출력 

 <p>처음 k-왼쪽밀기의 k를 첫째 줄에, (p,q)-구간뒤집기의 p와 q를 빈칸을 사이에 두고 둘째 줄에, 그리고 마지막 k-왼쪽밀기의 k를 셋째 줄에 출력한다. 만일 답이 여럿일 경우에는 그 중 하나만 출력하면 된다.</p>

