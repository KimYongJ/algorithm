#include <iostream>
#include <vector>
#include <string>
#include <cstring>
#include <algorithm>

using namespace std;

string text;
int N, tlen;
vector<int> len;
vector<vector<bool>> match;
vector<int> DP;

// KMP 실패 함수 생성
vector<int> getFail(const string& pattern) {
    int plen = pattern.length();
    vector<int> fail(plen, 0);

    for (int i = 1, j = 0; i < plen; i++) {
        while (j > 0 && pattern[i] != pattern[j])
            j = fail[j - 1];
        if (pattern[i] == pattern[j])
            fail[i] = ++j;
    }

    return fail;
}

// DP 계산
int solve(int idx) {
    if (idx == tlen) return 0;
    if (DP[idx] != -1) return DP[idx];

    DP[idx] = solve(idx + 1); // 현재 위치를 건너뛴 경우

    for (int i = 0; i < N; i++) {
        if (match[i][idx]) {
            DP[idx] = max(DP[idx], len[i] + solve(idx + len[i]));
        }
    }

    return DP[idx];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> text;
    tlen = text.length();
    cin >> N;

    len.resize(N);
    match.assign(N, vector<bool>(tlen, false));
    DP.assign(tlen, -1);

    for (int idx = 0; idx < N; idx++) {
        string pattern;
        cin >> pattern;
        len[idx] = pattern.length();

        vector<int> fail = getFail(pattern);

        // KMP 매칭
        for (int i = 0, j = 0; i < tlen; i++) {
            while (j > 0 && text[i] != pattern[j])
                j = fail[j - 1];
            if (text[i] == pattern[j]) {
                if (j == len[idx] - 1) {
                    match[idx][i - j] = true;
                    j = fail[j];
                } else {
                    j++;
                }
            }
        }
    }

    cout << solve(0) << '\n';

    return 0;
}
