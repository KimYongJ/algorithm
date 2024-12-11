# [Platinum V] 앞뒤가 맞는 수열 - 16570 

[문제 링크](https://www.acmicpc.net/problem/16570) 

### 성능 요약

메모리: 22612 KB, 시간: 288 ms

### 분류

KMP, 문자열

### 제출 일자

2024년 12월 11일 11:14:45

### 문제 설명

<p>수열 (a<sub>1</sub>, a<sub>2</sub>, ⋯, a<sub>N</sub>) 이 다음의 성질을 가지면 그 수열은 <em>k-앞뒤수열 </em>이라고 한다.</p>

<p style="text-align: center;">(a<sub>1</sub>, a<sub>2</sub>, ⋯, a<sub><span style="font-size: 10.8333px;">k</span></sub>) = (a<sub>N-k+1</sub>, a<sub>N-k+2</sub>, ⋯ , a<sub>N</sub>), 1 ≤ k < N</p>

<p>어떤 수열이 k-앞뒤수열일 때, k의 최댓값 k<sup>*</sup>를 그 수열의 <em>앞뒤계수</em>라고 한다.</p>

<p>우리는 수열의 앞뒤가 맞게 만들기 위해 수열의 연속된 앞부분을 자를 수 있다.</p>

<p>예를 들어 (a<sub>1</sub>, a<sub>2</sub>, ⋯, a<sub>N</sub>) 에서 (a<sub>1</sub>, a<sub>2</sub>) 을 제거하면 (a<sub>3</sub>, a<sub>4</sub>, ⋯ , a<sub>N</sub>) 가 된다.</p>

<p>주어진 수열  (A<sub>1</sub>,A<sub>2</sub>, ⋯ , A<sub>N</sub>)의 앞부분을 얼마나 잘라야 <strong>앞뒤계수를 최대</strong>로 만들 수 있을까? 단, 그러한 방법은 2가지 이상일 수 있다. 그리고 자르는 방법에는 "아무것도 자르지 않는 것" 도 포함한다.</p>

### 입력 

 <p dir="ltr">첫 번째 줄에 N이 주어진다. (2 ≤ N ≤ 1,000,000)</p>

<p dir="ltr">두번째 줄에 N개의 정수 A<sub>1</sub>,A<sub>2</sub>, ⋯ , A<sub>N</sub>이 공백으로 구분되어 주어진다. (-2<sup>31</sup> ≤ A<sub>i</sub> ≤ 2<sup>31</sup>-1)</p>

### 출력 

 <p>앞부분을 잘라서 앞뒤수열로 만들 수 있다면, 그렇게 자른 후 수열의 앞뒤계수 최댓값과 그렇게 자르는 방법의 수를 공백으로 구분하여 출력한다. 어떻게 잘라도 앞뒤계수가 존재하지 않으면 -1 을 출력한다.</p>

