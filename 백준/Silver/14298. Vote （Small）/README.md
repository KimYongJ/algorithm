# [Silver III] Vote (Small) - 14298 

[문제 링크](https://www.acmicpc.net/problem/14298) 

### 성능 요약

메모리: 13040 KB, 시간: 100 ms

### 분류

브루트포스 알고리즘, 깊이 우선 탐색, 그래프 이론, 그래프 탐색, 수학, 확률론

### 제출 일자

2025년 12월 27일 23:07:28

### 문제 설명

<p>A and B are the only two candidates competing in a certain election. We know from polls that exactly N voters support A, and exactly M voters support B. We also know that N is greater than M, so A will win.</p>

<p>Voters will show up at the polling place one at a time, in an order chosen uniformly at random from all possible (N + M)! orders. After each voter casts their vote, the polling place worker will update the results and note which candidate (if any) is winning so far. (If the votes are tied, neither candidate is considered to be winning.)</p>

<p>What is the probability that A stays in the lead the entire time -- that is, that A will always be winning after every vote?</p>

### 입력 

 <p>The input starts with one line containing one integer T, which is the number of test cases. Each test case consists of one line with two integers N and M: the numbers of voters supporting A and B, respectively.</p>

<h3>Limits</h3>

<ul>
	<li>1 ≤ T ≤ 100.</li>
	<li>0 ≤ M < N ≤ 10.</li>
</ul>

### 출력 

 <p>For each test case, output one line containing <code>Case #x: y</code>, where <code>x</code> is the test case number (starting from 1) and <code>y</code> is the probability that A will always be winning after every vote.</p>

<p><code>y</code> will be considered correct if <code>y</code> is within an absolute or relative error of 10<sup>-6</sup> of the correct answer. </p>

