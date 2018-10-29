#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <omp.h>

#define NUM_THREADS 4

struct node
{
	int num_neighbours;
	int *next; // array containing neighbours' node number
};

int visit_check(int n, bool *visited)
{
	for (int i=0; i<n; i++)
	{
		if (visited[i]==false)
		{
			return i;
		}
	}
	return -1;
}

void parallel_bfs(int n, struct node *nodes, int start, bool *visited)
{

	int *level = (int*)malloc(n*sizeof(int));
	visited[start] = true;
	level[start] = 0;
	int *q[2];
	q[0] = (int*)malloc(n*sizeof(int));
	q[1] = (int*)malloc(n*sizeof(int));
	q[0][0] = start;
	int l[2];
	l[0] = 1;
	int next_queue, current_queue = 0;
	int current_node;
	while(1)
	{
		current_node = q[current_queue][0];
		printf("\nLevel %d : ", level[current_node]);
		printf("%d\t", current_node);
		omp_set_num_threads(NUM_THREADS);
		#pragma omp parallel for
		for (int i=1; i<l[current_queue]; i++)
		{
			printf("%d\t",q[current_queue][i]);
		}
		next_queue = (current_queue + 1)%2;
		l[next_queue] = 0;
		omp_set_dynamic(0);
		#pragma omp parallel for private(current_node)
		for (int i=0; i<l[current_queue]; i++)
		{
			current_node = q[current_queue][i];
			for (int j=0; j<nodes[current_node].num_neighbours; j++)
			{
				if (!visited[nodes[current_node].next[j]])
				{
					visited[nodes[current_node].next[j]] = true;
					level[nodes[current_node].next[j]] = level[current_node]+1;
					#pragma omp critical
					q[next_queue][l[next_queue]++] = nodes[current_node].next[j];
				}
			}
		}
		if (l[next_queue]==0)
			break;
		else
			current_queue = next_queue;
	}
	free(level);
	free(q[0]);
	free(q[1]);
}

void main()
{
	int n;

	printf("\nTotal number of nodes : ");
	scanf("%d", &n);
	struct node *nodes = (struct node*)malloc(n*sizeof(struct node));
	for (int i=0; i<n; i++)
	{
		printf("No. of neighbours of node %d : ",i);
		scanf("%d", &nodes[i].num_neighbours);
		nodes[i].next = (int*)malloc(nodes[i].num_neighbours*sizeof(int));
		for (int j=0; j<nodes[i].num_neighbours; j++)
		{
			//printf("Neighbour %d : ",j+1);
			scanf("%d", &nodes[i].next[j]);
		}
	}
        printf("\n");

	double start_time = omp_get_wtime();
	int num_component = 1;
	bool *visited = (bool*)malloc(n);
	int start = visit_check(n, visited);
	//printf("Start node : ");
	//scanf("%d", &start);
	while (start!=-1)
	{
		// printf("\nComponent No.==> %d", num_component);
		parallel_bfs(n, nodes, start, visited);
		start = visit_check(n, visited);
		num_component++;
	}
	double end = omp_get_wtime();
	double time = end - start_time;
	free(visited);
	printf("\n%f\n", time);
	// for (int i=0; i<n; i++)
	// 	free(nodes[i].next);
	free(nodes);
}

/*

10
3
1 2 3
2
4 5
1
6
1
7
2
8 9
0
0
0
0
0

*/
