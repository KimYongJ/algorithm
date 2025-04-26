# [Gold IV] 그리드 네트워크 - 18769 

[문제 링크](https://www.acmicpc.net/problem/18769) 

### 성능 요약

메모리: 323072 KB, 시간: 2212 ms

### 분류

그래프 이론, 최소 스패닝 트리

### 제출 일자

2025년 4월 26일 09:32:50

### 문제 설명

<p>재현이는 그리드 네트워크 컨설팅 회사를 운영하고 있다. 어떤 회사의 데이터 서버가 격자 형태의 그래프로 주어졌을 때, 최소의 비용을 들여서 전용 통신망을 설치하려고 한다. 이때, 전용 통신망에 있는 임의의 두 서버는 통신을 할 수 있어야 한다. 두 서버가 직접 연결되어 있지 않아도 다른 서버를 통해서 통신을 할 수 있어도 된다.</p>

<p>서버는 행의 개수가 R개, 열의 개수가 C개인 격자로 나타낼 수 있다. 상하좌우로 인접한 서버 사이에는 직접 통신망을 설치할 수 있다. 직접 통신망을 설치하는 비용은 1, 2, 3, 4중 하나이다. 직접 통신망의 비용에는 흥미로운 성질이 하나 있는데, 한 서버 A와 인접한 B, C가 있을 때, A와 B 사이에 통신망을 설치하는 비용과 A와 C 사이에 설치하는 비용이 같은 경우는 없다는 것이다.</p>

<table class="table table-bordered" style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 50%; vertical-align: middle; text-align: center"><img alt="" src="https://upload.acmicpc.net/60cb8704-1ffe-4767-b9bc-91d3dcb379d6/-/preview/" style="width: 385px; height: 223px;"></td>
			<td style="width: 50%; vertical-align: middle; text-align: center"><img alt="" src="https://upload.acmicpc.net/c9272c2b-4fad-431a-a2e2-9e7f3eb41a61/-/preview/" style="width: 386px; height: 223px;"></td>
		</tr>
		<tr>
			<td style="width: 50%; vertical-align: middle; text-align: center">(a)</td>
			<td style="width: 50%; vertical-align: middle; text-align: center">(b)</td>
		</tr>
		<tr>
			<td style="width: 50%; vertical-align: middle; text-align: center"><img alt="" src="https://upload.acmicpc.net/93b2a9ec-b17c-4f30-aa4d-25f748219d38/-/preview/" style="width: 386px; height: 223px;"></td>
			<td style="width: 50%; vertical-align: middle; text-align: center"><img alt="" src="https://upload.acmicpc.net/bf20c4c4-c71d-4df6-bed8-bbb270b65dd0/-/preview/" style="width: 383px; height: 223px;"></td>
		</tr>
		<tr>
			<td style="width: 50%; vertical-align: middle; text-align: center">(c)</td>
			<td style="width: 50%; vertical-align: middle; text-align: center">(d)</td>
		</tr>
	</tbody>
</table>

<p>그림 (a)는 총 여섯 대의 서버가 있고, 각 서버 사이에 설치할 수 있는 통신망의 비용이 나와있는 그림이다. 그림 (b)는 (1, 2)와 (2, 2)에 있는 서버에서 비용이 같은 통신망이 두 개 있기 때문에, 입력으로 주어지지 않는 형태이다.</p>

<p>그림 (a)와 같은 그리드 네트워크의 경우 다섯 개의 통신망을 설치해서 임의의 두 서버가 통신할 수 있게 만들 수 있고, 최소 비용은 9이다. 최소가 되게 설치하는 방법은 총 두 가지가 있고, 그림 (c)와 그림 (d)에 빨간색으로 표시된 통신망을 설치하면 된다.</p>

<p>그리드 네트워크와 통신망의 설치 비용이 주어졌을 때, 임의의 두 서버가 통신할 수 있도록 하기 위한 최소 비용을 구해보자.</p>

### 입력 

 <p>첫째 줄에 테스트 케이스의 수 T가 주어진다.</p>

<p>각 테스트 케이스의 첫째 줄에는 R과 C가 공백으로 구분되어 주어진다. 다음 R개의 줄에 걸쳐서 각 줄에 C-1개의 정수가 주어지는데, 이 정수는 각 행에 놓인 C개의 서버에서 좌우로 연결하는 통신망을 설치하는 비용을 나타낸다. 다음 R-1개의 줄에 걸쳐서 각 줄에 C개의 정수가 주어지는데, 이 정수는 i번째 행과 i+1번째 행에 놓인 C개의 서버를 상하로 연결하는 통신망을 설치하는 비용을 나타낸다.</p>

### 출력 

 <p>각 테스트 케이스마다 최소 비용을 한 줄에 하나씩 출력한다.</p>

