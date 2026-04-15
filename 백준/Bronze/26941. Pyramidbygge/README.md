# [Bronze III] Pyramidbygge - 26941 

[문제 링크](https://www.acmicpc.net/problem/26941) 

### 성능 요약

메모리: 14072 KB, 시간: 108 ms

### 분류

수학, 구현, 브루트포스 알고리즘, 사칙연산, 시뮬레이션

### 제출 일자

2026년 4월 15일 19:24:05

### 문제 설명

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/99987025-e52d-4f98-a15f-87a0caa1c4dd/-/preview/" style="width: 200px; height: 123px;"></p>

<p style="text-align: center;">Figure 1: Ett exempel på en pyramid av höjd 3 med 35 block.</p>

<p>När man ska inleda ett större projekt, exempelvis bygga en pyramid, är det bäst att tänka efter en gång extra. Du ska skriva ett program som beräknar hur hög pyramid man kan bygga om man har tillgång till ett visst antal stenblock.</p>

<p>Vi antar att pyramiden är kompakt, d.v.s. det finns inga hålrum inuti. Vidare byggs den enligt principen i figure 1. Varje lager är alltså kvadratiskt med en sidlängd som är två block mindre än det underliggande lagrets. Det översta lagret består alltid av ett ensamt block.</p>

<p>Det gör ingenting om det blir block över, men det får inte saknas ett enda block.</p>

### 입력 

 <p>Indata består av ett enda heltal <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container> (<mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c31"></mjx-c></mjx-mn><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c2264"></mjx-c></mjx-mo><mjx-mi class="mjx-i" space="4"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c2264"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="4"><mjx-c class="mjx-c31"></mjx-c><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c></mjx-mn><mjx-mstyle><mjx-mspace style="width: 0.167em;"></mjx-mspace></mjx-mstyle><mjx-mn class="mjx-n"><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c></mjx-mn><mjx-mstyle><mjx-mspace style="width: 0.167em;"></mjx-mspace></mjx-mstyle><mjx-mn class="mjx-n"><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>1</mn><mo>≤</mo><mi>N</mi><mo>≤</mo><mn>100</mn><mstyle scriptlevel="0"><mspace width="0.167em"></mspace></mstyle><mn>000</mn><mstyle scriptlevel="0"><mspace width="0.167em"></mspace></mstyle><mn>000</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$1 \le N \le 100\,000\,000$</span></mjx-container>): antal tillgängliga block.</p>

### 출력 

 <p>Programmet ska skriva ut en rad med ett heltal: höjden för den största pyramid som kan byggas med som högst <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container> block.</p>

