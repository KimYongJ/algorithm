# [Gold III] Xayahh-Rakann at Moloco (Hard) - 15509 

[문제 링크](https://www.acmicpc.net/problem/15509) 

### 성능 요약

메모리: 208016 KB, 시간: 552 ms

### 분류

자료 구조, 분리 집합, 다이나믹 프로그래밍, 배낭 문제

### 제출 일자

2025년 3월 16일 11:45:36

### 문제 설명

<p>Although Moloco is an IT company, some hideous employees have conducted forbidden experiments that involve two kinds of unstable dark matters, which they named 'Xayahh-Rakann' based on the old legend of an inseparable couple.</p>

<p>These employees have produced <em>n</em> units of unstable dark matters, and each unit is contained in a jar that is labeled as 1, 2, ..., <em>n</em>.</p>

<p>After a number of experiments, it was found that certain pairs of Xayahh-Rakann should not be placed too far away from each other or they would become so unstable that they explode and wipe out the entire planet. Such pairs of jars are called inseparable pairs, and the employees have identified <em>m</em> such pairs of such jars (other pairs of jars can be separated far away, safely).</p>

<p>Unfortunately, a renovation is scheduled for the building where the jars are currently stored. The employees decided to keep exactly <em>k</em> jars at the basement of the current building which is not part of the renovation plan, while keeping the other <em>n</em>-<em>k</em> jars at a new office that is far from the current building. One tricky issue is that, if an inseparable pair of jars are not put in the same building, they will explode and nuke the planet.</p>

<p>These employees need your help to decide whether it is possible to separate the jars into two groups (of size <em>k</em> and <em>n</em>-<em>k</em>) in a safe manner. </p>

### 입력 

 <p>The first line contains three integers <em>n</em>, <em>m</em>, and <em>k</em> where 1 ≤ <em>n</em>, <em>k</em>, ≤ 1,000 and 1 ≤ <em>m</em> ≤ 1,000,000.</p>

<p>The following <em>m</em> lines contain two integers per line where each line describes an inseparable pair of jars (the two integers in the same line are distinct and between 1 and <em>n</em>, inclusive).</p>

<p>The (<em>i</em>+1)th line describes the <em>i</em>th pair of inseparable jars.</p>

### 출력 

 <p>Your output should consist of a single line that contains either "SAFE" or "DOOMED".</p>

<p>Output "SAFE" if it is possible to safely separate k jars and n-k jars; otherwise, output "DOOMED".</p>

<p>Quotes are for clarity only.</p>

