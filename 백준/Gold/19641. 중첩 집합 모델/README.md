# [Gold V] 중첩 집합 모델 - 19641 

[문제 링크](https://www.acmicpc.net/problem/19641) 

### 성능 요약

메모리: 66900 KB, 시간: 624 ms

### 분류

깊이 우선 탐색, 오일러 경로 테크닉, 그래프 이론, 그래프 탐색, 트리

### 제출 일자

2025년 7월 29일 22:21:18

### 문제 설명

<p>SQL은 릴레이션으로 나타낼 수 있는 데이터를 나타내기에는 훌륭한 표현력을 갖고 있지만, 계층적인 구조를 표현하기에는 한계가 있다. 이러한 한계를 보완하고자 나온 모델 중 하나가 중첩 집합 모델이다.</p>

<p>중첩 집합 모델은 구간의 포함관계를 이용해서 계층적인 구조를 나타내는 방식이며, 각각의 데이터는 서로 겹치지 않거나 한 데이터가 다른 데이터를 포함하는 관계를 가진다. 중첩 집합 모델은 트리 구조로 구성된 각 노드의 <span style="color:#e74c3c;"><code>left</code></span>, <span style="color:#e74c3c;"><code>right</code></span> 필드에 <strong>트리의 방문 순서</strong>로 번호를 매겨서 <span style="color:#e74c3c;"><code>left</code></span>, <span style="color:#e74c3c;"><code>right</code></span> 필드가 구간을 나타내도록 한다. 이 때, 각 노드의 <span style="color:#e74c3c;"><code>left</code></span> 필드와 <span style="color:#e74c3c;"><code>right</code></span> 필드는 <span style="color:#e74c3c;"><code> left < right</code></span>임이 보장된다.</p>

<p>중첩 집합 모델을 구성하는 A 노드와 B 노드가 있을 때, A.left < B.left이고 B.right < A.right이면 <strong>A는 B를 포함한다</strong>고 할 수 있다. A 노드가 포함하는 모든 노드들을 불러올 때는 SQL로는 <span style="color:#e74c3c;"><code><strong>SELECT</strong> * <strong>FROM</strong> tree <strong>WHERE</strong> left > A.left <strong>AND</strong> right < A.right;</code></span> 명령을 수행하면 된다.</p>

<p>예를 들면, 한 회사의 조직도를 나타낸다고 가정해 보자. HI-ARC라는 회사에는 여러 개의 부서가 있으며, 각 부서는 여러 개의 팀을 가질 수 있다. 그리고 각 팀은 여러 명의 사원을 포함할 수 있다.</p>

<p>아래와 같이 조직이 구성되어 있다고 생각해 보자.</p>

<blockquote>
<p><strong>1레벨</strong></p>

<ul>
	<li>HI-ARC - (경영지원실, 개발부)</li>
</ul>

<p><strong>2레벨</strong></p>

<ul>
	<li>경영지원실 - (사업전략팀, 법무팀)</li>
	<li>개발부 - (개발1팀, 개발2팀, 운영팀)</li>
</ul>

<p><strong>3레벨</strong></p>

<ul>
	<li>사업전략팀 - 사원1, 사원2</li>
	<li>법무팀 - 사원3</li>
	<li>개발1팀 - 사원4, 사원5</li>
	<li>개발2팀 - 사원6, 사원7, 사원8</li>
	<li>운영팀 - 사원9</li>
</ul>
</blockquote>

<p>그렇다면, 위와 같은 조직 구성을 중첩 집합 모델로 아래와 같이 나타낼 수 있다.</p>

<table class="table table-bordered table-center-30">
	<tbody>
		<tr>
			<td><strong>부서명/이름</strong></td>
			<td><strong>left</strong></td>
			<td><strong>right</strong></td>
			<td><strong>level</strong></td>
		</tr>
		<tr>
			<td>HI-ARC</td>
			<td>1</td>
			<td>34</td>
			<td>1</td>
		</tr>
		<tr>
			<td>경영지원실</td>
			<td>2</td>
			<td>13</td>
			<td>2</td>
		</tr>
		<tr>
			<td>사업전략팀</td>
			<td>3</td>
			<td>8</td>
			<td>3</td>
		</tr>
		<tr>
			<td>사원1</td>
			<td>4</td>
			<td>5</td>
			<td>4</td>
		</tr>
		<tr>
			<td>사원2</td>
			<td>6</td>
			<td>7</td>
			<td>4</td>
		</tr>
		<tr>
			<td>법무팀</td>
			<td>9</td>
			<td>12</td>
			<td>3</td>
		</tr>
		<tr>
			<td>사원3</td>
			<td>10</td>
			<td>11</td>
			<td>4</td>
		</tr>
		<tr>
			<td>개발부</td>
			<td>14</td>
			<td>33</td>
			<td>2</td>
		</tr>
		<tr>
			<td>개발1팀</td>
			<td>15</td>
			<td>20</td>
			<td>3</td>
		</tr>
		<tr>
			<td>사원4</td>
			<td>16</td>
			<td>17</td>
			<td>4</td>
		</tr>
		<tr>
			<td>사원5</td>
			<td>18</td>
			<td>19</td>
			<td>4</td>
		</tr>
		<tr>
			<td>개발2팀</td>
			<td>21</td>
			<td>28</td>
			<td>3</td>
		</tr>
		<tr>
			<td>사원6</td>
			<td>22</td>
			<td>23</td>
			<td>4</td>
		</tr>
		<tr>
			<td>사원7</td>
			<td>24</td>
			<td>25</td>
			<td>4</td>
		</tr>
		<tr>
			<td>사원8</td>
			<td>26</td>
			<td>27</td>
			<td>4</td>
		</tr>
		<tr>
			<td>운영팀</td>
			<td>29</td>
			<td>32</td>
			<td>3</td>
		</tr>
		<tr>
			<td>사원9</td>
			<td>30</td>
			<td>31</td>
			<td>4</td>
		</tr>
	</tbody>
</table>

<p>사이클이 없는 임의의 그래프가 주어졌을 때, 한 노드를 루트 노드로 하여 구성된 트리를 중첩 집합으로 나타냈을 때 각 노드의 <span style="color:#e74c3c;"><code>left</code></span> 필드, <span style="color:#e74c3c;"><code>right</code></span> 필드를 출력한다.</p>

### 입력 

 <p>첫 번째 줄에는 트리를 구성하는 정점의 개수 <em>N</em> (2 ≤ <em>N</em> ≤ 10<sup>5</sup>)가 주어진다.</p>

<p>두 번째 줄부터 <em>N </em>+ 1번째 줄까지는 정점에 연결된 간선에 대한 정보가 주어진다. 각 줄의 처음에는 간선이 연결될 정점의 번호 <em>V<sub>i</sub></em> (1 ≤ <em>V<sub>i</sub></em> ≤ <em>N</em>)가 주어지며, -1을 입력받기 전까지는 해당 노드에서 연결된 모든 노드에 대한 정보가 주어진다.</p>

<p><em>N </em>+ 2 번째 줄에는 루트 노드의 역할을 하게 될 정점의 번호 <em>S</em>가 주어진다.</p>

<p>이 때, 트리의 구성하는 간선은 단방향 그래프의 간선처럼 입력받지만, 양방향 그래프의 입력임이 보장된다. 2번 노드에서 3번 노드로의 간선을 입력받으면, 3번 노드에 연결된 노드의 정보를 입력받을 때는 3번 노드에서 2번 노드로의 간선이 입력으로 들어온다.</p>

<p>트리를 구성하는 양방향 간선의 정보를 입력받기 때문에, 간선의 개수의 총합은 <strong>2 × (<em>N</em> - 1) </strong>개이며, 모든 정점들은 간선을 통해 서로 직/간접적으로 연결되있다.</p>

### 출력 

 <p><em>S</em>번 노드가 루트 노드일 때, <strong>번호가 가장 낮은 노드부터 오름차순으로 방문</strong>해서 중첩 집합을 구성했을 때, 각 노드의 번호 <span style="color:#e74c3c;"><code>left</code></span> 필드와 <span style="color:#e74c3c;"><code>right</code></span> 필드를 출력한다.</p>

<p>총 <em>N</em>개의 줄에 걸쳐 <em>i</em>번째 줄에 <em>i</em>번 노드의 번호와 <span style="color:#e74c3c;"><code>left </code></span>/ <span style="color:#e74c3c;"><code>right</code></span> 필드를 출력한다.</p>

<p>이 때, 출력되는 모든 <span style="color:#e74c3c;"><code>left</code></span> 필드와 <span style="color:#e74c3c;"><code>right</code></span> 필드는 <strong>1 이상</strong> <strong>2 × <em>N</em> 이하</strong>의 서로 다른 자연수이다.</p>

