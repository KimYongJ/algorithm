# [Gold III] Cow Lineup - 5926 

[문제 링크](https://www.acmicpc.net/problem/5926) 

### 성능 요약

메모리: 41776 KB, 시간: 500 ms

### 분류

값 / 좌표 압축, 정렬, 두 포인터

### 제출 일자

2025년 1월 26일 18:15:54

### 문제 설명

<p>Farmer John has hired a professional photographer to take a picture of some of his cows.  Since FJ's cows represent a variety of different breeds, he would like the photo to contain at least one cow from each distinct breed present in his herd.</p><p>FJ's N cows are all standing at various positions along a line, each described by an integer position (i.e., its x coordinate) as well as an integer breed ID.  FJ plans to take a photograph of a contiguous range of cows along the line.  The cost of this photograph is equal its size -- that is, the difference between the maximum and minimum x coordinates of the cows in the range of the photograph.</p><p>Please help FJ by computing the minimum cost of a photograph in which there is at least one cow of each distinct breed appearing in FJ's herd.</p>

### 입력 

 <ul><li>Line 1: The number of cows, N (1 <= N <= 50,000).</li><li>Lines 2..1+N: Each line contains two space-separated positive integers specifying the x coordinate and breed ID of a single cow.  Both numbers are at most 1 billion.</li></ul>

### 출력 

 <ul><li>Line 1: The smallest cost of a photograph containing each distinct breed ID.</li></ul>

