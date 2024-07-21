#include <stdio.h>
#include <string.h>
#include <vector>
#define NMAX 250010
#define lint long long int
using namespace std;
 
int N, Q, a, b;
int M, K, t;
int inp[NMAX];
 
int treeParent[NMAX];
vector< int > graph[NMAX];
 
int p, pp;
int used[NMAX], check[NMAX], parent[NMAX];
 
lint ret;
lint cnt[NMAX];
 
int find(int p) {
    if(parent[p] == p) return p;
    else return parent[p] = find(parent[p]);
}
 
void make_tree(int idx) {
    check[idx] = 0;
    for(int child:graph[idx]) {
        if(!check[child]) continue;
        make_tree(child);
        treeParent[child] = idx;
    }
}
 
int main() {
    // input
    scanf("%d", &N);
    for(int i=1;i<N;i++) {
        scanf("%d %d", &a, &b);
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    
    // make tree
    memset(check, -1, sizeof(check));
    make_tree(1);
    
    scanf("%d", &M);
    for(int i=1;i<=M;i++) {
        // init
        ret = 0;
        
        // query
        scanf("%d", &K);
        for(int j=0;j<K;j++) {
            scanf("%d", &inp[j]);
            
            // init
            used[inp[j]] = i;
            cnt[inp[j]] = 1;
            parent[inp[j]] = inp[j];
        }
        
        // UF(자식과 부모 모두 S에 포함된 경우만 합치기)
        for(int j=0;j<K;j++) {
            if(used[inp[j]] == i and used[treeParent[inp[j]]] == i) {
                p = find(inp[j]);
                pp = find(treeParent[inp[j]]);
                
                cnt[pp] += cnt[p];
                parent[p] = pp;
            }
        }
        
        // check
        for(int j=0;j<K;j++) {
            if(parent[inp[j]] == inp[j]) {
                ret += cnt[inp[j]]*(cnt[inp[j]]-1)/2;
            }
        }
        
        // print
        printf("%lld\n", ret);
    }
}