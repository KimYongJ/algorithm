# [Bronze II] 4-LSB - 32685 

[문제 링크](https://www.acmicpc.net/problem/32685) 

### 성능 요약

메모리: 11516 KB, 시간: 68 ms

### 분류

수학, 구현, 비트마스킹

### 제출 일자

2025년 12월 13일 10:05:10

### 문제 설명

<p>스테가노그래피(Steganography)는 데이터 은폐 기술 중 하나로, 특정 데이터를 다른 데이터에 삽입하여 감추는 기술을 말한다. 스테가노그래피에서 일반적으로 쓰는 기법 중 하나인 k-Least Significant Bit(k-LSB)는 데이터의 최하위 k 비트의 값을 의미하며, 평문 데이터의 최하위 k 비트를 전달하려는 데이터로 대체하여 데이터를 은닉한다. 예를 들어, 7(이진수: 111)이라는 데이터를 100(이진수: 1100100)이라는 데이터에 3-LSB를 사용하여 숨긴다면, 하위 3개의 비트가 7(이진수: 111)에 해당하는 값으로 대체되어 103(이진수: 1100<strong>111</strong>)이 된다.</p>

<p>화장실이 급한 은규는 화장실 비밀번호를 몰라 척이에게 물어봤지만, 척이는 은규를 골탕먹이기 위해 4-LSB를 이용하여 화장실 비밀번호를 은폐하여 전달하기로 했다. 척이는 은규에게 세 십진수를 알려준다. 이 세 십진수의 4-LSB를 이진수로 표현하여 순서대로 이어붙인 수를 다시 십진수로 변환한 값이 화장실의 비밀번호가 된다. 비밀번호는 항상 4자리이며, 은폐 데이터로 도출한 비밀번호가 4자리보다 작다면 앞에 0을 1개 이상 붙여 4자리로 만든다.</p>

<p>화장실이 급한 은규를 위해 척이가 준 세 십진수에서 비밀번호를 도출하자.</p>

### 입력 

 <p>첫째 줄부터 셋째 줄까지 척이가 준 세 십진수가 순서대로 주어진다. 각 수는 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c30"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>0</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$0$</span></mjx-container> 이상 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-msup><mjx-mn class="mjx-n"><mjx-c class="mjx-c31"></mjx-c><mjx-c class="mjx-c30"></mjx-c></mjx-mn><mjx-script style="vertical-align: 0.393em;"><mjx-mn class="mjx-n" size="s"><mjx-c class="mjx-c39"></mjx-c></mjx-mn></mjx-script></mjx-msup></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><msup><mn>10</mn><mn>9</mn></msup></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$10^9$</span></mjx-container> 이하의 정수이다.</p>

### 출력 

 <p>첫째 줄에 화장실의 비밀번호 네 자리를 출력한다.</p>

