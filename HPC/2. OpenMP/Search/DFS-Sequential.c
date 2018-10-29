#include<stdio.h>

#define MAX 10

void DFS(int);
int G[MAX+1][MAX+1],visited[MAX+1],n;    //n is no of vertices and graph is sorted in array G[10][10]

void main()
{
    int i,j;
    printf("Enter number of vertices:");

    scanf("%d",&n);

    //read the adjecency matrix
    printf("\nEnter adjecency matrix of the graph:\n");

    for(i=1;i<=n;i++)
       for(j=1;j<=n;j++)
            scanf("%d",&G[i][j]);

    //visited is initialized to zero
   for(i=1;i<=n;i++)
        visited[i]=0;

   int startNode;

   fflush(stdin);       // needed because the below scanf was getting skipped..so buffer is flushed
   printf("\nEnter start node :");
   scanf("%d" ,&startNode);

    DFS(startNode);
}

void DFS(int i)
{
    int j;
    printf("\n%d",i);
    visited[i]=1;

    for(j=1;j<=n;j++)
       if(!visited[j]&&G[i][j]==1)
            DFS(j);
}
/*
10

0 1 1 1 0 0 0 0 0 0
1 0 0 0 1 1 0 0 0 0
1 0 0 0 0 0 1 0 0 0
1 0 0 0 0 0 0 1 0 0
0 1 0 0 0 0 0 0 1 1
0 1 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0

1

*/
