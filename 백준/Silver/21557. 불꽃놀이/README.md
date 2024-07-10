# [Silver IV] 불꽃놀이 - 21557 

[문제 링크](https://www.acmicpc.net/problem/21557) 

### 성능 요약

메모리: 35436 KB, 시간: 344 ms

### 분류

그리디 알고리즘

### 제출 일자

2024년 7월 10일 14:45:11

### 문제 설명

<p>폴리매스 왕국의 사람들은 불의 돌을 이용해 불꽃놀이를 합니다. 오늘의 불꽃놀이는 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>개의 폭죽 더미를 이용할 예정입니다.</p>

<p>당신은 아래 작업을 정확히 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n" space="3"><mjx-c class="mjx-c2212"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="3"><mjx-c class="mjx-c32"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi><mo>−</mo><mn>2</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N-2$</span></mjx-container>번 반복해서 폭죽을 터뜨리려고 합니다.</p>

<ul>
	<li>양 끝 폭죽 더미를 제외한 폭죽 더미를 하나 고릅니다.</li>
	<li>해당 폭죽 더미의 폭죽을 모두 터뜨립니다.</li>
	<li>폭발한 폭죽 더미는 사라지고, 양 옆으로 가장 가까운 폭죽 더미의 높이가 1씩 감소합니다.</li>
</ul>

<p>불꽃놀이가 끝나고 나면 두 개의 폭죽 더미만이 남습니다. 한 번 불꽃놀이에 사용한 폭죽 더미는 재사용이 불가능하기 때문에, 남은 두 폭죽 더미의 높이 중 더 큰 값을 최소화하려고 합니다. 이 값을 찾는 프로그램을 작성해 봅시다.</p>

### 입력 

 <p>첫 줄에는 폭죽 더미의 개수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>이 주어집니다. 다음 줄에는 각 폭죽 더미의 높이 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-msub><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D434 TEX-I"></mjx-c></mjx-mi><mjx-script style="vertical-align: -0.15em;"><mjx-mn class="mjx-n" size="s"><mjx-c class="mjx-c31"></mjx-c></mjx-mn></mjx-script></mjx-msub><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-msub space="2"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D434 TEX-I"></mjx-c></mjx-mi><mjx-script style="vertical-align: -0.15em;"><mjx-mn class="mjx-n" size="s"><mjx-c class="mjx-c32"></mjx-c></mjx-mn></mjx-script></mjx-msub><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mo class="mjx-n" space="2"><mjx-c class="mjx-c22EF"></mjx-c></mjx-mo><mjx-mo class="mjx-n" space="2"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-msub space="2"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D434 TEX-I"></mjx-c></mjx-mi><mjx-script style="vertical-align: -0.15em;"><mjx-mi class="mjx-i" size="s"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-script></mjx-msub></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><msub><mi>A</mi><mn>1</mn></msub><mo>,</mo><msub><mi>A</mi><mn>2</mn></msub><mo>,</mo><mo>⋯</mo><mo>,</mo><msub><mi>A</mi><mi>N</mi></msub></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$A_1, A_2, \cdots, A_N$</span></mjx-container>이 주어어집니다.</p>

### 출력 

 <p>마지막 두 폭죽 더미 중 더 높은 것의 높이의 최솟값을 출력합니다.</p>

