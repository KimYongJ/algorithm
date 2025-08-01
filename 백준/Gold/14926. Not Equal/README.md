# [Gold V] Not Equal - 14926 

[문제 링크](https://www.acmicpc.net/problem/14926) 

### 성능 요약

메모리: 22348 KB, 시간: 212 ms

### 분류

그래프 이론, 그리디 알고리즘, 그래프 탐색, 깊이 우선 탐색, 오일러 경로

### 제출 일자

2025년 7월 31일 17:27:47

### 문제 설명

<p>주어진 N개의 수가 모두 서로 다르다는 것은 기호 "!="를 통해 하나의 식으로 표현할 수 있다. 예를 들어 A, B, C가 모두 서로 다르다는 것은 논리식으로 (A != B) && (B != C) && (C != A) 로 쓸 수 있고, 이를 다음과 같이 한 줄로 표현하는 것을 A, B, C에 대한 "한 줄 표기법"이라고 부르기로 하자.</p>

<p><strong>A != B != C != A</strong></p>

<p>하지만 5개의 수 A, B, C, D, E가 모두 서로 다르다는 것을 다음처럼 표현하는 것은 올바른 한 줄 표기법이 아니다.</p>

<p><strong>A != B != C != D != E</strong></p>

<p>왜냐하면 5개의 수가 서로 다름을 나타내기 위해서는 10개의 쌍에 대해 서로 다름을 표현해야 하고, 이는 적어도 10개의 "!="를 필요로 하기 때문이다. 일반적으로 주어진 N개의 수가 모두 다름을 한 줄 표기법으로 표현하기 위해서는 적어도 C(N, 2)개의 "!="이 필요함이 알려져 있다(C(N, 2) : N개의 서로 다른 대상 중 2개를 뽑는 가짓수).</p>

<p>홀수 자연수 N이 주어졌을 때, N개의 수 a<sub>1</sub>, a<sub>2</sub>, …, a<sub>N</sub>에 대해 가능한 한 줄 표기법 중 가장 짧으면서 사전순으로 가장 앞에 오는 한 줄 표기법을 출력하는 프로그램을 작성하라. 단 이때 "!="은 공백으로 대신하기로 한다. 예를 들어 N = 3이 주어졌을 때 "a<sub>1</sub> a<sub>2</sub> a<sub>3</sub> a<sub>1</sub>"는 정답으로 인정되지만, "a<sub>3</sub> a<sub>1</sub> a<sub>2</sub> a<sub>3</sub>"는 사전순으로 앞의 표기법보다 뒤에 오기 때문에 올바른 한 줄 표기법이라도 정답으로 인정되지 않는다.</p>

<p>Hint : 한 줄 표기법에 최소로 필요한 "!="의 개수인 C(N, 2)는 Vertex가 N개인 완전 그래프의 Edge의 개수와 동일함을 고려해 보라.</p>

### 입력 

 <p>첫째 줄에 자연수 N이 주어진다. N은 1보다 크고 500보다 작은 홀수이다.</p>

### 출력 

 <p>첫째 줄에 가능한 한 줄 표기법 중 가장 짧으면서 사전순으로 가장 앞에 오는 것을 출력한다.</p>

