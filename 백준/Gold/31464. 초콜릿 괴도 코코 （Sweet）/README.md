# [Gold II] 초콜릿 괴도 코코 (Sweet) - 31464 

[문제 링크](https://www.acmicpc.net/problem/31464) 

### 성능 요약

메모리: 17128 KB, 시간: 128 ms

### 분류

그래프 이론, 브루트포스 알고리즘, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색

### 제출 일자

2025년 8월 13일 17:30:11

### 문제 설명

<p>추리 소설 “초콜릿 괴도 레나”를 감명 깊게 읽은 코코는 이 소설의 명장면을 따라 해 보기로 했다. 구체적인 방법은 다음과 같다.</p>

<ul>
	<li>1단계: 먼저 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n" space="3"><mjx-c class="mjx-cD7"></mjx-c></mjx-mo><mjx-mi class="mjx-i" space="3"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi><mo>×</mo><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N\times N$</span></mjx-container> 크기의 사각 격자 형태의 초콜릿을 준비한다. 이 초콜릿은 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c31"></mjx-c></mjx-mn><mjx-mo class="mjx-n" space="3"><mjx-c class="mjx-cD7"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="3"><mjx-c class="mjx-c31"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>1</mn><mo>×</mo><mn>1</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$1\times 1$</span></mjx-container> 단위로 원하는 곳에서 떼어낼 수 있게 되어 있으며, <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c30"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>0</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$0$</span></mjx-container>개 이상의 단위 초콜릿이 제거된 상태이다. 이때, 남아있는 단위 초콜릿은 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c34"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>4</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$4$</span></mjx-container>개 이상이며, 한 조각을 이루어야 한다. 상하좌우로 이웃한 두 단위 초콜릿은 서로 연결되어 있으며, 서로 연결된 단위 초콜릿들의 집합을 하나의 조각이라고 한다.</li>
	<li>2단계: 이 초콜릿에서 다음의 조건을 충족하도록 하나의 단위 초콜릿을 떼어 먹는다.
	<ul>
		<li>이 단위 초콜릿을 떼어낸 후에도 남아있는 초콜릿은 하나의 조각을 이루지만, 그 이후에 서로 이웃한 임의의 두 단위 초콜릿 사이를 자르면 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c32"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>2</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$2$</span></mjx-container>개의 조각으로 나누어진다.</li>
	</ul>
	</li>
	<li>3단계: 레나의 명대사를 외친다. “이번엔 봐줬지만, 다음에는 반드시 초콜릿을 조각내 줄 거야.”</li>
</ul>

<p>코코는 1단계의 조건을 충족하는 초콜릿을 준비해 놓았지만, 2단계의 조건을 충족하려면 어느 지점에 있는 단위 초콜릿을 떼어내야 할지 고민에 빠졌다. 코코를 도와주자.</p>

### 입력 

 <p>첫 번째 줄에는 초콜릿의 크기 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>이 주어진다. <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mo class="mjx-n"><mjx-c class="mjx-c28"></mjx-c></mjx-mo><mjx-mn class="mjx-n"><mjx-c class="mjx-c32"></mjx-c></mjx-mn><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c2264"></mjx-c></mjx-mo><mjx-mi class="mjx-i" space="4"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c2264"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="4"><mjx-c class="mjx-c34"></mjx-c><mjx-c class="mjx-c30"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c29"></mjx-c></mjx-mo></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mo stretchy="false">(</mo><mn>2</mn><mo>≤</mo><mi>N</mi><mo>≤</mo><mn>40</mn><mo stretchy="false">)</mo></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$(2\le N\le 40)$</span> </mjx-container></p>

<p>두 번째 줄부터 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>줄에 걸쳐서 초콜릿의 상태가 주어진다. 각 줄에는 각 칸에 단위 초콜릿이 있는지를 나타내는 문자 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>개가 공백 없이 주어진다. 각 문자는 모두 <span style="color:#e74c3c;"><code>#</code></span> 또는 <span style="color:#e74c3c;"><code>.</code></span>이며, 초콜릿의 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D45F TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>r</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$r$</span></mjx-container>번째 줄의 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D450 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>c</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$c$</span></mjx-container>번째 글자가 <span style="color:#e74c3c;"><code>#</code></span>이면 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D45F TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>r</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$r$</span></mjx-container>행 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D450 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>c</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$c$</span></mjx-container>열에 단위 초콜릿이 있음을, <span style="color:#e74c3c;"><code>.</code></span>이면 없음을 뜻한다. 초콜릿의 맨 왼쪽 위 칸은 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c31"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>1</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$1$</span></mjx-container>행 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c31"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>1</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$1$</span></mjx-container>열이다.</p>

<p>모든 입력은 1단계의 조건을 충족한다.</p>

### 출력 

 <p>첫 번째 줄에는 2단계의 조건을 충족하도록 떼어낼 수 있는 서로 다른 단위 초콜릿의 개수를 출력한다.</p>

<p>다음 줄부터는 그러한 단위 초콜릿의 행 번호와 열 번호를 한 줄에 하나씩 출력한다. 이때, 행 번호가 작은 것부터, 행 번호가 같은 것들 중에서는 열 번호가 작은 것부터 출력한다.</p>

