# [Gold III] A Bug’s Life - 7535 

[문제 링크](https://www.acmicpc.net/problem/7535) 

### 성능 요약

메모리: 74708 KB, 시간: 500 ms

### 분류

깊이 우선 탐색, 그래프 이론, 그래프 탐색

### 제출 일자

2024년 10월 28일 10:52:10

### 문제 설명

<p>Professor Hopper is researching the sexual behavior of a rare species of bugs. He assumes that they feature two different genders and that they only interact with bugs of the opposite gender. In his experiment, individual bugs and their interactions were easy to identify, because numbers were printed on their backs.</p>

<p>Given a list of bug interactions, decide whether the experiment supports his assumption of two genders with no homosexual bugs or if it contains some bug interactions that falsify it.</p>

### 입력 

 <p>The first line of the input contains the number of scenarios. Each scenario starts with one line giving the number of bugs (at least one, and up to 2000) and the number of interactions (up to 1000000) separated by a single space. In the following lines, each interaction is given in the form of two distinct bug numbers separated by a single space. Bugs are numbered consecutively starting from one.</p>

### 출력 

 <p>The output for every scenario is a line containing “Scenario #i:”, where i is the number of the scenario starting at 1, followed by one line saying either “No suspicious bugs found!” if the experiment is consistent with his assumption about the bugs’ sexual behavior, or ”Suspicious bugs found!” if Professor Hopper’s assumption is definitely wrong.</p>

