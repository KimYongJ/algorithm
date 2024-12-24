# [Gold IV] 시간표 만들기 - 31837 

[문제 링크](https://www.acmicpc.net/problem/31837) 

### 성능 요약

메모리: 15164 KB, 시간: 108 ms

### 분류

백트래킹, 브루트포스 알고리즘, 파싱, 문자열

### 제출 일자

2024년 12월 24일 17:13:18

### 문제 설명

<p>찬솔이는 이번 학기에 22학점을 들을 계획이다. 시간표를 만들기 위해 찬솔이는 다음과 같이 들을 예정인 과목들을 그룹으로 정리했다.</p>

<ul>
	<li>그룹 A : <code>[전공종합설계1(가), 전공종합설계2(나), 컴퓨터공학특강1]</code></li>
	<li>그룹 B : <code>[네트워크프로그래밍(가), 네트워크프로그래밍(나)]</code></li>
	<li>그룹 C : <code>[멀티미디어응용]</code></li>
	<li>그룹 D : <code>[SW융합세미나1, 정보기술세미나1]</code></li>
	<li>그룹 E : <code>[파일처리(가), 파일처리(나)]</code></li>
	<li>그룹 F : <code>[디지털공학(나), 디지털공학(다)]</code></li>
	<li>그룹 G : <code>[문제해결]</code></li>
	<li>그룹 H : <code>[프로그래밍언어(가)]</code></li>
</ul>

<p>그룹마다 그룹에 속한 강의 중 <strong>최대 하나의 강의를 선택</strong>해서 시간표를 구성한다. 각 강의에는 강의가 진행되는 요일, 강의 시작 시각, 강의 종료 시각이 있고, 선택한 강의끼리 진행 시간이 겹치면 안 된다. 요일이 다르거나 강의가 끝나는 동시에 다른 강의가 시작하는 것은 시간이 겹치는 것이 아니다.</p>

<p>꼭 모든 그룹에서 강의를 하나씩 선택해야 하는 것이 아니며, 하나의 그룹에 학점이 다른 강의가 있을 수도 있음에 유의하라. 또한, 모든 강의는 일주일에 한 번만 진행된다. 즉, 입력으로 주어지는 강의는 모두 서로 다른 강의이다.</p>

<p>찬솔이는 들을 예정인 과목들을 위와 같이 그룹으로 정리했을 때, 선택한 강의의 학점 합이 정확히 <strong>22</strong>가 되도록 시간표를 만들 수 있는 경우의 수가 궁금해졌다.</p>

### 입력 

 <p>첫째 줄에 그룹의 개수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>이 주어진다.</p>

<p>둘째 줄부터 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>개의 그룹과 각 그룹에 포함된 과목의 정보가 주어진다. 그룹에 포함된 과목의 개수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-msub><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D434 TEX-I"></mjx-c></mjx-mi><mjx-script style="vertical-align: -0.15em;"><mjx-mi class="mjx-i" size="s"><mjx-c class="mjx-c1D456 TEX-I"></mjx-c></mjx-mi></mjx-script></mjx-msub></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><msub><mi>A</mi><mi>i</mi></msub></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$A_i$</span></mjx-container>가 먼저 주어진다. 이어서 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-msub><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D434 TEX-I"></mjx-c></mjx-mi><mjx-script style="vertical-align: -0.15em;"><mjx-mi class="mjx-i" size="s"><mjx-c class="mjx-c1D456 TEX-I"></mjx-c></mjx-mi></mjx-script></mjx-msub></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><msub><mi>A</mi><mi>i</mi></msub></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$A_i$</span></mjx-container>개의 줄에 걸쳐 각 과목의 학점 수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D436 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>C</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$C$</span></mjx-container>, 요일 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D437 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>D</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$D$</span></mjx-container>, 강의 시작 시각 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D446 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>S</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$S$</span></mjx-container>, 강의 종료 시각 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D438 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>E</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$E$</span></mjx-container>가 공백으로 구분되어 주어진다.</p>

<p>강의 시작 시각 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D446 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>S</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$S$</span></mjx-container>와 종료 시각 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D438 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>E</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$E$</span></mjx-container>는 <code>HH</code>시 <code>MM</code>분이 <code>HH:MM</code> 형식으로 주어진다.</p>

### 출력 

 <p>주어진 입력으로 조건을 만족하며 만들 수 있는 22학점 시간표의 개수를 출력한다.</p>

