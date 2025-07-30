# [Platinum V] 헤븐스 키친 - 14574 

[문제 링크](https://www.acmicpc.net/problem/14574) 

### 성능 요약

메모리: 34732 KB, 시간: 232 ms

### 분류

그래프 이론, 그래프 탐색, 깊이 우선 탐색, 최소 스패닝 트리

### 제출 일자

2025년 7월 30일 17:54:11

### 문제 설명

<p>남규는 요즘 군입대를 기다리며 하루 종일 유튜브를 본다. 남규가 가장 좋아하는 채널은 ‘Heaven`s kichen’이다. 이 프로그램에서는 N명의 요리사가 매일 둘씩 요리 대결을 펼치고, 승리한 요리사 한 명이 천국으로 떠난다.</p>

<p>만일 한 명의 요리사가 남는다면 해당 요리사는 지옥으로 보내진다.</p>

<p>이 프로그램에 출연하는 N명의 요리사는 각각 요리 실력 P<sub>i</sub>와 인기도 C<sub>i</sub>를 갖고 있다.</p>

<p>이 프로그램의 시청률은 그 날 요리 대결을 펼치는 두 요리사의 요리 실력과 인기도에 의해 결정된다.</p>

<p>만일 요리사 A와 요리사 B가 대결을 펼친다면, 그 날의 시청률은 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D453 TEX-I"></mjx-c></mjx-mi><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D459 TEX-I"></mjx-c></mjx-mi><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D45C TEX-I"></mjx-c></mjx-mi><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D45C TEX-I"></mjx-c></mjx-mi><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D45F TEX-I"></mjx-c></mjx-mi><mjx-mrow space="2"><mjx-mo class="mjx-lop"><mjx-c class="mjx-c28 TEX-S2"></mjx-c></mjx-mo><mjx-mfrac><mjx-frac><mjx-num><mjx-nstrut></mjx-nstrut><mjx-mrow size="s"><mjx-msub><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D436 TEX-I"></mjx-c></mjx-mi><mjx-script style="vertical-align: -0.153em; margin-left: -0.045em;"><mjx-mi class="mjx-i" size="s"><mjx-c class="mjx-c1D434 TEX-I"></mjx-c></mjx-mi></mjx-script></mjx-msub><mjx-mo class="mjx-n"><mjx-c class="mjx-c2B"></mjx-c></mjx-mo><mjx-msub><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D436 TEX-I"></mjx-c></mjx-mi><mjx-script style="vertical-align: -0.15em; margin-left: -0.045em;"><mjx-mi class="mjx-i" size="s"><mjx-c class="mjx-c1D435 TEX-I"></mjx-c></mjx-mi></mjx-script></mjx-msub></mjx-mrow></mjx-num><mjx-dbox><mjx-dtable><mjx-line></mjx-line><mjx-row><mjx-den><mjx-dstrut></mjx-dstrut><mjx-mrow size="s"><mjx-mo class="mjx-n"><mjx-c class="mjx-c7C"></mjx-c></mjx-mo><mjx-msub><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D443 TEX-I"></mjx-c></mjx-mi><mjx-script style="vertical-align: -0.153em; margin-left: -0.109em;"><mjx-mi class="mjx-i" size="s"><mjx-c class="mjx-c1D434 TEX-I"></mjx-c></mjx-mi></mjx-script></mjx-msub><mjx-mo class="mjx-n"><mjx-c class="mjx-c2212"></mjx-c></mjx-mo><mjx-msub><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D443 TEX-I"></mjx-c></mjx-mi><mjx-script style="vertical-align: -0.15em; margin-left: -0.109em;"><mjx-mi class="mjx-i" size="s"><mjx-c class="mjx-c1D435 TEX-I"></mjx-c></mjx-mi></mjx-script></mjx-msub><mjx-mo class="mjx-n"><mjx-c class="mjx-c7C"></mjx-c></mjx-mo></mjx-mrow></mjx-den></mjx-row></mjx-dtable></mjx-dbox></mjx-frac></mjx-mfrac><mjx-mo class="mjx-lop"><mjx-c class="mjx-c29 TEX-S2"></mjx-c></mjx-mo></mjx-mrow></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>f</mi><mi>l</mi><mi>o</mi><mi>o</mi><mi>r</mi><mrow data-mjx-texclass="INNER"><mo data-mjx-texclass="OPEN">(</mo><mfrac><mrow><msub><mi>C</mi><mi>A</mi></msub><mo>+</mo><msub><mi>C</mi><mi>B</mi></msub></mrow><mrow><mo stretchy="false">|</mo><msub><mi>P</mi><mi>A</mi></msub><mo>−</mo><msub><mi>P</mi><mi>B</mi></msub><mo stretchy="false">|</mo></mrow></mfrac><mo data-mjx-texclass="CLOSE">)</mo></mrow></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$ floor \left( \frac{C_A + C_B}{|P_A - P_B|} \right) $</span></mjx-container>로 결정된다. 어떤 두 요리사의 요리 실력이 같은 경우는 없다.</p>

<p>(floor(x)는 x보다 작거나 같은 가장 큰 정수)</p>

<p>대결의 승패는 요리 실력이나 인기도와는 관계없이 결정될 수 있다.</p>

<p>남규는 이 프로그램을 시청하던 중, 요리사들의 대결 순서와 승패에 따라 프로그램의 시청률이 크게 달라질 수도 있다는 사실을 발견했다.</p>

<p>남규는 총 N-1번의 경기 동안, 경기 순서와 승패를 잘 정해서 시청률의 총합을 최대화한다면 어느 정도까지 시청률의 합이 커질 수 있는지가 궁금해졌다.</p>

<p>경기의 순서와 승패를 잘 정해, 시청률의 합의 최댓값을 구해 보고, 경기가 어떻게 진행되어야 하는지 정해 보자.</p>

### 입력 

 <p>첫째 줄에 참가하는 요리사의 수 N이 주어진다. (2 ≤ N ≤ 1000)</p>

<p>이어 N줄에 걸쳐, 각 요리사의 요리 실력 P<sub>i</sub>와 인기도 C<sub>i</sub>가 주어진다. (0 ≤ P<sub>i</sub>, C<sub>i</sub> ≤ 10<sup>9</sup>, P<sub>i</sub> ≠ P<sub>j</sub> if i ≠ j)</p>

### 출력 

 <p>첫째 줄에 가능한 시청률의 합의 최댓값을 출력한다.</p>

<p>둘째 줄부터 N번째 줄까지, 대결하는 두 요리사의 번호를 “패자 승자” 의 형식으로 대결한 순서대로 출력한다.</p>

<p>승자가 천국으로 떠나고, 패자는 계속 남아 대결을 진행하게 되는 것임에 주의한다.</p>

<p>만일 시청률의 합이 최대가 되는 대진표가 여러 가지 있다면 그 중 임의의 하나를 출력해도 정답으로 인정된다.</p>

