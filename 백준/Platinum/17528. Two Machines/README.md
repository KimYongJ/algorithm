# [Platinum V] Two Machines - 17528 

[문제 링크](https://www.acmicpc.net/problem/17528) 

### 성능 요약

메모리: 14972 KB, 시간: 144 ms

### 분류

다이나믹 프로그래밍, 배낭 문제

### 제출 일자

2025년 4월 9일 14:11:23

### 문제 설명

<p>스케줄링 최적화 회사인 SOPT 에 완료해야 할 <em>n</em>개의 작업 <em>t</em><sub>1</sub>, <em>t</em><sub>2</sub>, ..., <em>t<sub>n</sub></em> 이 있다. SOPT 회사는 두 대의 머신 <em>A</em> 와 <em>B</em> 를 보유하고 있다. 각 작업 <em>t<sub>i</sub></em>를 완료하기 위해 SOPT 는 머신 <em>A</em> 와 <em>B</em> 둘 중에 오직 하나를 선택할 수 있다. 작업 <em>t<sub>i</sub></em>를 완료하기 위해 머신 <em>A</em>를 선택하면 <em>a<sub>i</sub></em>시간이 걸리고 머신 <em>B</em>를 선택하면 <em>b<sub>i</sub></em> 시간이 걸린다. 각 머신은 어느 순간에 최대 하나의 작업만 수행할 수 있으며, 한 작업이 시작되면 그 작업을 완료하기 전까지 다른 작업을 그 머신에서 수행할 수 없다. SOPT 는 모든 작업을 완료하기 위한 최소의 완료 시간을 구하고자 한다.</p>

<p>예를 들어, 세 개의 작업이 <em>t</em><sub>1</sub>, <em>t</em><sub>2</sub>, <em>t</em><sub>3</sub>가 주어져 있고 <em>a</em><sub>1</sub> = 2, <em>b</em><sub>1</sub> = 3, <em>a</em><sub>2</sub> = 5, <em>b</em><sub>2</sub> = 3, <em>a</em><sub>3</sub> = 2, <em>b</em><sub>3</sub> = 7라고 하자. 완료 시간을 최소화하기 위해서는 작업 <em>t</em><sub>1</sub>, <em>t</em><sub>3</sub>는 머신 <em>A</em>에, 작업 <em>t</em><sub>2</sub>는 머신 <em>B</em>에 할당한다. 머신 <em>A</em>는 작업 <em>t</em><sub>1</sub>, <em>t</em><sub>3</sub>를 완료하는데 2 + 2 = 4 시간이 걸리고 머신 <em>B</em>는 작업 <em>t</em><sub>2</sub>를 완료하는데 3 시간이 걸린다. 따라서 최소 완료 시간은 4 시간이 된다. <em>n</em>개의 작업 <em>t</em><sub>1</sub>, <em>t</em><sub>2</sub>, ..., <em>t<sub>n</sub></em> 과 각 머신에서 각 작업들을 수행하는 데 걸리는 시간들이 주어질 때, 모든 작업들을 완료하기 위해 걸리는 시간의 최솟값을 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>입력은 표준입력을 사용한다. 첫 번째 줄에 작업의 개수를 나타내는 양의 정수 <em>n</em> (1 ≤ <em>n</em> ≤ 250)이 주어진다. 다음 <em>n</em>개의 줄에서 <em>i</em>번째 줄에는 두 개의 정수 <em>a<sub>i</sub></em>, <em>b<sub>i</sub></em> (1 ≤ <em>a<sub>i</sub></em>, <em>b<sub>i</sub></em> ≤ 250)가 주어진다. 여기서 <em>a<sub>i</sub></em>와 <em>b<sub>i</sub></em>는 각각 작업 <em>t<sub>i</sub></em>를 머신 <em>A</em>와 <em>B</em>에서 완료하는데 걸리는 시간이다.</p>

### 출력 

 <p>출력은 표준출력을 사용한다. 모든 작업 <em>t</em><sub>1</sub>, <em>t</em><sub>2</sub>, ..., <em>t<sub>n</sub></em>을 완료하기 위한 최소의 완료시간을 한 줄에 출력한다.</p>

