# [Platinum V] #15164번_제보 - 16163 

[문제 링크](https://www.acmicpc.net/problem/16163) 

### 성능 요약

메모리: 52128 KB, 시간: 292 ms

### 분류

매내처, 문자열

### 제출 일자

2024년 12월 17일 23:54:10

### 문제 설명

<p>안녕 백준대숲 사람들, 억울한 일이 있어요.</p>

<p>악랄한 <img alt=":jh05013:" src="https://upload.acmicpc.net/74a7737c-aed4-45ab-ba02-baefc80ce8b4/-/preview/" style="width: 1.5em; height: 1.5em; transform: translateY(-0.15em); display: inline;">이 <img alt="*" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;"><a href="https://www.acmicpc.net/problem/15927"><strong>회문은 회문아니야!!</strong></a><img alt="*" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;"> 이런 문제를 냈어요. 근데 푼 사람이 너무 많은 거예요!</p>

<blockquote><img alt=">" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;">내가 이걸 안 풀 순 없지...</blockquote>

<p>생각하면서 고통받으면서 <img alt="_" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;"><em>M</em><img alt="_" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;"> 어쩌구랑 <img alt="_" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;"><em>offline BIT</em><img alt="_" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;">‍를 짰다?</p>

<p>근데 <img alt=":wookje:" src="https://upload.acmicpc.net/bfe223ec-2a96-4096-affa-25169a4c0d3f/-/preview/" style="width: 1.5em; height: 1.5em;  transform: translateY(-0.15em); display: inline;"> 솔루션을 보니까 내가 멍청한 거예요ㅠㅠ 백주뉴ㅠㅠ 그들이 절 기만했어요ㅠㅠㅠ</p>

<p>그래서 다시는 낚이지 않겠다고 생각했어요.</p>

<p>근데 얼마 전에 대회를 치면서 <img alt="*" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;"><a href="https://www.acmicpc.net/problem/16161"><strong>가장 긴 증가하는 팰린드롬 부분수열</strong></a><img alt="*" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;"> 문제를 봤어요.</p>

<p>열심히 생각했는데 <img alt="_" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;"><em>M</em><img alt="_" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;"> 어쩌구 말고는 생각이 안 나서</p>

<blockquote><img alt=">" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px;">와 이번엔 진짜 <img alt="_" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;"><em>M</em><img alt="_" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;"> 어쩌구다</blockquote>

<p>생각하면서 열심히 <img alt="_" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;"><em>M</em><img alt="_" src="https://upload.acmicpc.net/150f1ab6-a14f-4c6a-955f-0b7cf531558e/-/preview/" style="width: 0px; display: inline;"> 어쩌구를 짰다?</p>

<p>근데 이번에도 낚인 거예요ㅠㅠㅠㅠ 사람들 코드 길이가 다 너무 짧아ㅠㅠㅠㅠㅠㅠ 키파 너무 멍청해요ㅠㅠㅠ</p>

<p>이제 키파는 기만자들에게 복수의 철퇴를 날릴 거예요. 그러니까 여러분이 부분 문자열 중 회문인 것의 개수를 구하는 프로그램을 작성해 주세요! 😈</p>

### 입력 

 <p>길이가 1 이상 200만 이하인 문자열을 드릴게요. 알파벳 대문자로만 이루어져 있어요!</p>

### 출력 

 <p>주어진 문자열의 부분 문자열 중 회문인 것의 개수를 출력해 주세요!</p>

