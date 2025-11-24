# [Bronze I] 바둑돌 게임 - 25592 

[문제 링크](https://www.acmicpc.net/problem/25592) 

### 성능 요약

메모리: 14308 KB, 시간: 100 ms

### 분류

수학, 구현, 시뮬레이션

### 제출 일자

2025년 11월 24일 21:26:35

### 문제 설명

<p>게임을 좋아하는 푸앙이는 요즘 "바둑돌 게임"이라는 게임을 즐겨한다.</p>

<p>바둑돌 게임은 두 명이 번갈아 가며 한 개의 바둑돌 무더기에서 바둑돌을 정해진 개수만큼 가져가는 게임이다. 처음 시작한 사람은 바둑돌을 1개를 가져가야 하고, 그다음 차례인 사람이 지난 차례에 가져갔던 바둑돌보다 한 개 더 많은 2개를 가져가야 한다. 그다음 차례에는 이전 차례보다 한 개 더 많은 3개를 가져가야 한다. 이런 식으로 차례를 반복해서 자신의 차례에 정해진만큼의 바둑돌을 못 가져간 사람이 게임에서 지게 된다.</p>

<p>게임의 규칙을 완벽하게 이해하고 있던 푸앙이는 같이 바둑돌 게임을 하기로 한 친구를 이기기 위해, 바둑돌 무더기에 바둑돌을 추가하기로 결심했다. 이때, 바둑돌을 너무 많이 추가하면, 친구가 수상해 할 수 있으니, 가능한 한 적은 양의 바둑돌을 추가하기로 했다.</p>

<p>푸앙이가 먼저 게임을 시작한다고 했을 때, 푸앙이가 게임에서 이기기 위해 추가해야 하는 최소한의 바둑돌의 개수를 구해주는 프로그램을 만들어주자. 단, 바둑돌을 추가하지 않아도 된다.</p>

### 입력 

 <p>첫 번째 줄에 바둑돌 무더기에 있는 바둑돌의 개수를 의미하는 정수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container> <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mo class="mjx-n"><mjx-c class="mjx-c28"></mjx-c></mjx-mo><mjx-mn class="mjx-n"><mjx-c class="mjx-c31"></mjx-c></mjx-mn><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c2264"></mjx-c></mjx-mo><mjx-mi class="mjx-i" space="4"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c2264"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="4"><mjx-c class="mjx-c31"></mjx-c><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c></mjx-mn><mjx-mstyle><mjx-mspace style="width: 0.167em;"></mjx-mspace></mjx-mstyle><mjx-mn class="mjx-n"><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c29"></mjx-c></mjx-mo></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mo stretchy="false">(</mo><mn>1</mn><mo>≤</mo><mi>N</mi><mo>≤</mo><mn>100</mn><mstyle scriptlevel="0"><mspace width="0.167em"></mspace></mstyle><mn>000</mn><mo stretchy="false">)</mo></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$(1 \leq N \leq 100\,000)$</span></mjx-container>이 입력된다.</p>

### 출력 

 <p>푸앙이가 게임에서 이기기 위해 바둑돌 무더기에 추가해야 할 최소의 바둑돌의 개수를 출력한다.</p>

