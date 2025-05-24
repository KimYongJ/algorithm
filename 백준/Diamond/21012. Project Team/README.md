# [Diamond V] Project Team - 21012 

[문제 링크](https://www.acmicpc.net/problem/21012) 

### 성능 요약

메모리: 495000 KB, 시간: 6740 ms

### 분류

이분 탐색, 자료 구조, 그리디 알고리즘, mo's, 오프라인 쿼리, 누적 합, 퍼시스턴트 세그먼트 트리, 세그먼트 트리

### 제출 일자

2025년 5월 24일 21:56:08

### 문제 설명

<p>There are N (software) engineers in PT Untung Pasti Bahagia (UPB) whose numbers are from 1 to N. As their manager, Andi knows those engineers very well and has assigned a potential score to each of them where P<sub>i</sub> represents the i<sup>th</sup> engineer’s potential.</p>

<p>Once in a while, a project offer comes to UPB. As a manager, Andi evaluates the project proposal and determines that he will need a team of at least one engineer that has an average potential score of at least S. To avoid any issue due to instability of the potential scores, Andi wants each engineer in the selected team to have a potential score between A and B, inclusive. Andi also (naively) believes that the more engineers he has in a team, the better the project will run.</p>

<p>Due to a weird company policy, the project can only be run by a team of engineers whose number is between L and R, inclusive. In other words, Andi has to select as many engineers as possible whose numbers are between L and R (inclusive) and whose potential scores are between A and B (inclusive) such that the average potential score of the selected engineers is at least S.</p>

<p>There are Q incoming projects, each having their own L, R, A, B, and S values. For each project, help Andi to determine the maximum number of engineers that can join the team for the project, or determine if there is no solution.</p>

### 입력 

 <p>Input begins with a line containing an integer: N (1 ≤ N ≤ 200 000) representing the number of engineers in UPB. The next line contains N integers: Pi (1 ≤ Pi ≤ 200 000) representing the potential score of the engineers. The next line contains an integer: Q (1 ≤ Q ≤ 200 000) representing the number of incoming projects. The next Q lines, each contains five integers: L R A B S (1 ≤ L ≤ R ≤ N; 1 ≤ A ≤ B ≤ 200 000; 1 ≤ S ≤ 200 000) representing the number range and the potential score range in which Andi can select the engineers from and the minimum average potential score for the selected team, respectively.</p>

### 출력 

 <p>For each incoming project in the same order as input, output in a line an integer representing the maximum number of engineers that can be selected for the respective project, or output 0 if there is no solution.</p>

