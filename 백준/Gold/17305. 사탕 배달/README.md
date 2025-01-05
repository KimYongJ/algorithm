# [Gold IV] 사탕 배달 - 17305 

[문제 링크](https://www.acmicpc.net/problem/17305) 

### 성능 요약

메모리: 29248 KB, 시간: 456 ms

### 분류

그리디 알고리즘, 누적 합, 정렬

### 제출 일자

2025년 1월 5일 16:11:17

### 문제 설명

<p>사탕을 좋아하는 아기 석환은, 집에 <em>N</em>개의 사탕이 들어있는 자루를 들여놓았다. 자루에는 두 가지 종류의 사탕이 있는데, 작은 사탕은 3g의 무게를 가지고, 큰 사탕은 5g의 무게를 가진다. 똑똑한 아기 석환은 자루에 있는 모든 사탕에 대해서, 그 사탕의 당도 <em>s<sub>i</sub></em> 를 계산해 놓았다. <em>s<sub>i </sub></em>는 양의 정수로, <em>s<sub>i</sub></em> 가 클수록 사탕은 달콤하다.</p>

<p>shake! 2019 대회에 참가하기 위해 짐을 싸고 있는 아기 석환은, 달콤한 사탕을 최대한 많이 담아가서 대회 도중 당분을 보충하려고 한다. 하지만, 연약한 아기 석환은 가방에 최대 <em>w</em>g (<em>w</em>그램) 의 사탕만을 담을 수 있다. 아기 석환이 이 조건을 만족하면서 사탕을 담았을 때, 담아간 사탕의 당도의 합은 최대 얼마가 될 수 있을까?</p>

### 입력 

 <p>첫 번째 줄에 사탕의 개수 <em>N</em>(1 ≤ <em>N</em> ≤ 250,000), 무게 제한 <em>w</em>(0 ≤ <em>w</em> ≤ 5<em>N</em>)가 주어진다.</p>

<p>이후 <em>N</em>개의 줄에 각 사탕의 종류 <em>t<sub>i</sub></em>,  당도 <em>s<sub>i</sub></em>가 주어진다. (<mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-msub><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D461 TEX-I"></mjx-c></mjx-mi><mjx-script style="vertical-align: -0.15em;"><mjx-mi class="mjx-i" size="s"><mjx-c class="mjx-c1D456 TEX-I"></mjx-c></mjx-mi></mjx-script></mjx-msub><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c2208"></mjx-c></mjx-mo><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c7B"></mjx-c></mjx-mo><mjx-mn class="mjx-n"><mjx-c class="mjx-c33"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="2"><mjx-c class="mjx-c35"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c7D"></mjx-c></mjx-mo></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><msub><mi>t</mi><mi>i</mi></msub><mo>∈</mo><mo fence="false" stretchy="false">{</mo><mn>3</mn><mo>,</mo><mn>5</mn><mo fence="false" stretchy="false">}</mo></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$t_i \in \{3, 5\}$</span></mjx-container>, 1 ≤ <em>s<sub>i</sub></em> ≤ 10<sup>9</sup>)</p>

### 출력 

 <p>아기 석환이 조건을 만족하면서 담아갈 수 있는 사탕의 당도의 최대 합을 출력하라.</p>

