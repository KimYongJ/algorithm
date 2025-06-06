# [Gold IV] 로봇 조립 - 18116 

[문제 링크](https://www.acmicpc.net/problem/18116) 

### 성능 요약

메모리: 43568 KB, 시간: 552 ms

### 분류

자료 구조, 분리 집합

### 제출 일자

2025년 6월 6일 19:17:35

### 문제 설명

<p>성규는 로봇을 조립해야 한다. 상자 안에는 여러 로봇의 부품들이 섞여 있다. 그런데 어떤 부품이 어느 로봇의 부품인지 표시가 되어있지 않다. 호재는 전자과라서 두 부품을 보면 같은 로봇의 부품인지 알 수 있다. 그래서 성규는 호재의 지시에 따라 부품들을 정리하기로 하였다.</p>

<p>부품들은 1부터 10<sup>6</sup>까지의 정수로 표현된다. 그리고 부품 <em>i</em>가 속한 로봇은 robot(<em>i</em>)라고도 표현한다. 예를 들어, 부품 11과 부품 22가 로봇 A의 부품이라고 알고 있는 경우, robot(11)은 로봇 A를 의미하고, robot(22)도 로봇 A를 의미한다.</p>

<p>서로 다른 로봇은 공통 부품을 가지지 않는다. 즉 어떤 부품이 로봇 A의 부품이라면, 로봇 B의 부품은 될 수 없다.</p>

<p>호재는 2가지 지시를 한다.</p>

<ul>
	<li>서로 다른 부품 2개를 말해주며, 두 부품은 같은 로봇의 부품이라는 정보를 알려준다.</li>
	<li>부품 <em>i</em>에 대해서, 지금까지 알아낸 robot(<em>i</em>)의 부품이 몇 개냐고 물어본다.</li>
</ul>

<p>초기에는 부품에 대한 정보가 존재하지 않는다.</p>

### 입력 

 <p>첫 번째 줄에 호재의 지시 횟수 <em>N</em>이 들어온다. (1 ≤ <em>N</em> ≤ 10<sup>6</sup>)</p>

<p>다음 줄부터 <em>N</em>개의 지시가 들어온다.</p>

<p>부품 2개가 같은 로봇의 부품인지 알려줄 때에는 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D43C TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>I</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">\(I\)</span> </mjx-container><em> a b</em> 의 형태로 들어온다. 부품 <em>a</em>와 부품 <em>b</em>는 같은 로봇의 부품이라는 의미이다. (1 ≤<strong> </strong><em>a</em>, <em>b</em> ≤ 10<sup>6</sup>, <em>a</em> ≠ <em>b, a</em>, <em>b</em>는 정수)</p>

<p>어떤 로봇의 부품이 몇 개인지 물어볼 때에는 <em>Q c</em> 의 형태로 들어온다. 지금까지 알아낸 robot(<em>c</em>)의 부품이 몇 개냐는 의미이다. (1 ≤ <em>c</em> ≤ 10<sup>6</sup>, <em>c</em>는 정수)</p>

<p>입력으로 <em>Q c</em>의 형태가 적어도 한 번 들어온다.</p>

### 출력 

 <p><em>Q</em>로 시작하는 입력에 대해서 한 줄에 하나씩, 지금까지 알아낸 해당 로봇의 부품 개수를 출력한다.</p>

