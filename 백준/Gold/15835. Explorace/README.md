# [Gold IV] Explorace - 15835 

[문제 링크](https://www.acmicpc.net/problem/15835) 

### 성능 요약

메모리: 14096 KB, 시간: 104 ms

### 분류

자료 구조, 분리 집합, 그래프 이론, 최소 스패닝 트리

### 제출 일자

2025년 7월 14일 20:03:03

### 문제 설명

<p>CodingSchool is conducting an explorace to welcome new students. It is compulsory for each team to visit all check points (not necessarily following the sequence). At each check point, the team will have to complete a specific activity. Each team can plan a strategy on the sequence of check points to visit. The distance of each path is no more than 500 meters.</p>

<p>Because they don’t want the new student to wander away and get lost, CodingSchool wants to put their committee on the paths and only allow the student to use path that have a committee. But CodingSchool only have a limited number of committee, so they don’t want to use all path. Shorter path is preferred because it use less committee. While at the same time, they must make sure that there exists a way to travel between every two checkpoints. Help CodingSchool by determining the minimum total distance of path that they must cover.</p>

### 입력 

 <p>First line of input is integer T (1 ≤ T ≤ 10) that represents the number of test cases. Each test case starts with a line with two integers N (1 ≤ N ≤ 20) and M (1 ≤ M ≤ N*(N-1)), that represents the number of check points and the number of paths to consider respectively. In the following M lines, there are 3 integers a, b (1 ≤ a, b ≤ N) and d (1 ≤ d ≤ 500) that represent the start check points (a), the end check points (b) and the distance of the path (d) that connects check points a and b.</p>

### 출력 

 <p>For each test case, output the minimum distance as shown in the sample output.</p>

