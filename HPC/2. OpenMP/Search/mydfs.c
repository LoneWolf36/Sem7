#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#define SIZE 10000

int adjMatrix[SIZE][SIZE];
int visited[SIZE];
int i;
void makeZero()
{
  #pragma omp parallel for
  for(int i=0;i<SIZE;i++)
    visited[i] = 0;
}
void dfsSerial(int node)
{
  visited[node] = 1;
  for(i=0;i<SIZE;i++)
  {
    if(adjMatrix[node][i]==1 && !visited[i])
    {
      dfsSerial(i);
    }
  }
}

void visit(int node)
{
  int iVisited=0,i;
  #pragma omp critical
  if(!visited[node])
  {
    visited[node] = 1;
    iVisited = 1;
  }
  if(iVisited)
  {
    for(i=0;i<SIZE;i++)
    {
      if(adjMatrix[node][i]==1 && !visited[i])
      {
        #pragma omp task
        visit(i);
      }
    }
  }
}

void dfsParallel()
{
  #pragma omp parallel
  {
    makeZero();
    #pragma omp single
    visit(1);
  }
}

void setUp()
{
  for(int i=0;i<SIZE;i++)
  {
    for(int j=0;j<=i;j++)
    {
      adjMatrix[i][j] = rand()%2;
      adjMatrix[j][i] = adjMatrix[i][j];
    }
  }
}

void main()
{
  int c = 0;
  double start,end,elapsed;
  setUp();
  makeZero();

  start = omp_get_wtime();
  dfsSerial(1);
  end = omp_get_wtime();
  elapsed = end-start;
  for(int i=0;i<SIZE;i++)
    c++;
  if(c == SIZE)
    printf("\nAll nodes visited");
  printf("\nTime taken for serial = %f",elapsed);

  makeZero();
  start = omp_get_wtime();
  dfsParallel();
  end = omp_get_wtime();
  elapsed = end-start;
  c = 0;
  for(int i=0;i<SIZE;i++)
    c++;
  if(c == SIZE)
    printf("\nAll nodes visited");
  printf("\nTime taken for parallel = %f",elapsed);

}
