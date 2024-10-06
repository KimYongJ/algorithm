# [Gold II] 줄임말 - 20191 

[문제 링크](https://www.acmicpc.net/problem/20191) 

### 성능 요약

메모리: 25380 KB, 시간: 388 ms

### 분류

이분 탐색

### 제출 일자

2024년 10월 6일 17:21:13

### 문제 설명

<p>문자열 A가 문자열 B의 <strong>줄임말</strong>이라는 것은 B의 순서를 바꾸지 않고 0 또는 그 이상 개수의 문자를 지워 A를 만들 수 있다는 뜻이다. 정의에 의해서 B는 자기 자신의 줄임말임에 유의하라. 예를 들어, <code>ac</code>, <code>ab</code>, <code>aa</code>, <code>aabc</code>는 <code>aabc</code>의 줄임말이고, <code>d</code>, <code>aaa</code>, <code>ba</code>는 <code>aabc</code>의 줄임말이 아니다.</p>

<p>영문 알파벳 소문자로만 이루어진 두 문자열 S와 T가 주어진다. T를 자연수 n번 반복해서 이어쓴 문자열을 T n이라고 하자. S가 T n의 줄임말이 되는 최소의 n을 구하라.</p>

<p>예를 들어, T = <code>ac</code>, S = <code>caa</code>라고 하면, T<sup>1</sup> = T = <code>ac</code>, T<sup>2</sup> = <code>acac</code>, T<sup>3</sup> = <code>acacac</code>이고 n = 3일 때 처음으로 S가 T<sup>n</sup>의 줄임말이 된다.</p>

### 입력 

 <p>첫째 줄에 문자열 S가 주어진다.</p>

<p>둘째 줄에 문자열 T가 주어진다.</p>

### 출력 

 <p>S가 T<sup>n</sup>의 줄임말이 되는 최소의 n을 출력한다. 단, 어떤 n에 대해서도 T<sup>n</sup>이 S의 줄임말이 되지 못할 때에는, -1을 출력한다.</p>

