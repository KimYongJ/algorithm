# [Gold IV] 팀 빌딩 - 22945 

[문제 링크](https://www.acmicpc.net/problem/22945) 

### 성능 요약

메모리: 14772 KB, 시간: 128 ms

### 분류

두 포인터

### 제출 일자

2025년 1월 20일 10:23:48

### 문제 설명

<p>개발자 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>명이 팀 빌딩을 위해 한 줄로 서있다.</p>

<p>하나의 팀을 만들기 위해서는 개발자 2명이 반드시 모여야 한다.</p>

<p>개발자 A와 개발자 B가 팀을 만들 때 팀의 능력치는 아래와 같이 계산이 된다.</p>

<ul>
	<li>(개발자 A와 개발자 B 사이에 존재하는 다른 개발자 수) × min(개발자 A의 능력치, 개발자 B의 능력치)</li>
</ul>

<p>예를 들어, 4명의 개발자가 존재할 때, 각 개발자의 능력치를 <code><span style="background-color:#dddddd;">1 4 2 5</span></code>라고 하자. 이때 능력치가 1인 개발자와 능력치가 5인 개발자가 한 팀을 이뤘다고 가정하자. 그러면 이 팀의 능력치는 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c32"></mjx-c></mjx-mn><mjx-mi class="mjx-i"><mjx-c class="mjx-cD7"></mjx-c></mjx-mi><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D45A TEX-I"></mjx-c></mjx-mi><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D456 TEX-I"></mjx-c></mjx-mi><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D45B TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n"><mjx-c class="mjx-c28"></mjx-c></mjx-mo><mjx-mn class="mjx-n"><mjx-c class="mjx-c31"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="2"><mjx-c class="mjx-c35"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c29"></mjx-c></mjx-mo><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c3D"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="4"><mjx-c class="mjx-c32"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>2</mn><mi>×</mi><mi>m</mi><mi>i</mi><mi>n</mi><mo stretchy="false">(</mo><mn>1</mn><mo>,</mo><mn>5</mn><mo stretchy="false">)</mo><mo>=</mo><mn>2</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$2×min(1, 5) = 2$</span></mjx-container>가 된다.</p>

<p>팀 빌딩에서 나올 수 있는 팀 중 능력치의 최대값을 구해보자.</p>

### 입력 

 <p>첫 번째 줄에 개발자의 수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>이 주어진다.</p>

<p>두 번째 줄에는 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>의 개발자의 각 능력치 <mjx-container class="MathJax" jax="CHTML" style="font-size: 108.8%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-msub><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D465 TEX-I"></mjx-c></mjx-mi><mjx-script style="vertical-align: -0.15em;"><mjx-texatom size="s" texclass="ORD"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D456 TEX-I"></mjx-c></mjx-mi></mjx-texatom></mjx-script></mjx-msub></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><msub><mi>x</mi><mrow data-mjx-texclass="ORD"><mi>i</mi></mrow></msub></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$x_{i}$</span></mjx-container>가 공백으로 구분되어 주어진다.</p>

### 출력 

 <p>팀의 능력치 최댓값을 출력한다.</p>

