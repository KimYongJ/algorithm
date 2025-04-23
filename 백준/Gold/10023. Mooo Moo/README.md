# [Gold IV] Mooo Moo - 10023 

[문제 링크](https://www.acmicpc.net/problem/10023) 

### 성능 요약

메모리: 14852 KB, 시간: 128 ms

### 분류

다이나믹 프로그래밍, 배낭 문제

### 제출 일자

2025년 4월 23일 20:28:50

### 문제 설명

<p>Farmer John has completely forgotten how many cows he owns!  He is too embarrassed to go to his fields to count the cows, since he doesn't want the cows to realize his mental lapse.  Instead, he decides to count his cows secretly by planting microphones in the fields in which his cows tend to gather, figuring that he can determine the number of cows from the total volume of all the mooing he hears.</p><p>FJ's N fields (1 <= N <= 100) are all arranged in a line along a long straight road.  Each field might contain several types of cows; FJ owns cows that come from B different breeds (1 <= B <= 20), and a cow of breed i moos at a volume of V(i) (1 <= V(i) <= 100).  Moreover, there is a strong wind blowing down the road, which carries the sound of mooing in one direction from left to right: if the volume of mooing in some field is X, then in the next field this will contribute X-1 to the total mooing volume (and X-2 in the field after that, etc.). Otherwise stated, the mooing volume in a field is the sum of the contribution due to cows in that field, plus X-1, where X is the total mooing volume in the preceding field.</p><p>Given the volume of mooing that FJ records in each field, please compute the minimum possible number of cows FJ might own.</p><p>The volume FJ records in any field is at most 100,000.</p>

### 입력 

 <ul><li>Line 1: The integers N and B.</li><li>Lines 2..1+B: Line i+1 contains the integer V(i).</li><li>Lines 2+B..1+B+N: Line 1+B+i contains the total volume of all mooing in field i.</li></ul>

### 출력 

 <ul><li>Line 1: The minimum number of cows owned by FJ, or -1 if there is no configuration of cows consistent with the input.</li></ul>

