# [Gold V] Moocast - 14172 

[문제 링크](https://www.acmicpc.net/problem/14172) 

### 성능 요약

메모리: 14344 KB, 시간: 112 ms

### 분류

너비 우선 탐색, 깊이 우선 탐색, 그래프 이론, 그래프 탐색

### 제출 일자

2024년 11월 4일 17:40:05

### 문제 설명

<p>Farmer John's N cows (1≤N≤200) want to organize an emergency "moo-cast" system for broadcasting important messages among themselves.</p>

<p>Instead of mooing at each-other over long distances, the cows decide to equip themselves with walkie-talkies, one for each cow. These walkie-talkies each have a limited transmission radius -- a walkie-talkie of power P can only transmit to other cows up to a distance of P away (note that cow A might be able to transmit to cow B even if cow B cannot transmit back, due to cow A's power being larger than that of cow B). Fortunately, cows can relay messages to one-another along a path consisting of several hops, so it is not necessary for every cow to be able to transmit directly to every other cow.</p>

<p>Due to the asymmetrical nature of the walkie-talkie transmission, broadcasts from some cows may be more effective than from other cows in their ability to reach large numbers of recipients (taking relaying into account). Please help the cows determine the maximum number of cows that can be reached by a broadcast originating from a single cow.</p>

### 입력 

 <p>The first line of input contains N.</p>

<p>The next N lines each contain the x and y coordinates of a single cow ( integers in the range 0…25,000) followed by p, the power of the walkie-talkie held by this cow.</p>

### 출력 

 <p>Write a single line of output containing the maximum number of cows a broadcast from a single cow can reach. The originating cow is included in this number.</p>

