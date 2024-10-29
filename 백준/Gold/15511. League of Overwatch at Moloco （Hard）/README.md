# [Gold III] League of Overwatch at Moloco (Hard) - 15511 

[문제 링크](https://www.acmicpc.net/problem/15511) 

### 성능 요약

메모리: 277372 KB, 시간: 884 ms

### 분류

이분 그래프, 깊이 우선 탐색, 그래프 이론, 그래프 탐색

### 제출 일자

2024년 10월 29일 11:46:27

### 문제 설명

<p>Once in a while, Moloco employees partition themselves into two groups, and engage in a best-of-five series of the League of Overwatch. Since some pairs of employees are hardcore gamers and they have played together as a duo in the past, the company decided to split them for this friendly company-wide event to make the event less competitive but more enjoyable for newbies.</p>

<p>Formally, there are <em>n</em> employees at Moloco who are numbered as 1 through <em>n</em>. </p>

<p>There are <em>m</em> known pairs of employees (<em>f<sub>i</sub></em>, <em>s<sub>i</sub></em>) such that the employees <em>f<sub>i</sub></em> and <em>s<sub>i</sub></em> have played a duo game in the past -- they must belong to different groups for this event.</p>

<p>Given this information, determine whether it is possible to partition <em>n</em> employees to two non-empty groups such that each employee belongs to exactly one group and there is no pair (<em>f<sub>i</sub></em>, <em>s<sub>i</sub></em>) such that both <em>f<sub>i</sub></em> and and <em>s<sub>i</sub></em> belong to the same group.</p>

### 입력 

 <p>First line contains two integers, <em>n</em> and <em>m</em> where 1 ≤ <em>n</em> ≤ 1,000,000 and 1 ≤ <em>m</em> ≤ 1,000,000.</p>

<p>The following <em>m</em> lines contain two integers where <em>i</em>+1th line describes <em>f<sub>i</sub></em> and <em>s<sub>i</sub></em> where 1 ≤ <em>f<sub>i</sub></em>, <em>s<sub>i</sub></em> ≤ <em>n</em>. It is guaranteed that <em>f<sub>i</sub></em> ≠ <em>s<sub>i</sub></em> for all <em>i</em>.</p>

### 출력 

 <p>Your output should be a single line that contains a string "POSSIBLE" or "IMPOSSIBLE" (quotes for clarity only). </p>

