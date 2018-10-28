#include <iostream>
#include <omp.h>
#include <time.h>
using namespace std;

int main() {
	double total_time_serial, total_time_parallel;
	int size = 1000;
	int matrix[size][size], vector[size], result[size];
	int i, j;
	clock_t start, end;
	
	// Setting values
	for(i=0;i<size;i++) {
		for(j=0;j<size;j++) {
			matrix[i][j] = j*2;
		}
		vector[i] = i*2;
		result[i] = 0;
	}
	
	// Serial matrix multiplication
	start = clock();
	for(i=0;i<size;i++) {
		for(j=0;j<size;j++) {
			result[i] += matrix[i][j] * vector[j];
		}
	}
	end = clock();
	double elapsed_ser = ((double)end-start)/CLOCKS_PER_SEC;
	cout<<"Elapsed time for serial: "<<elapsed_ser;
	
	// Setting result to 0 again
	for(i=0;i<size;i++) {
		result[i] = 0;
	}
	
	// Parallel matrix multiplication
	double t1 = omp_get_wtime();
	#pragma omp parallel for shared (matrix, vector, result) schedule(static)
	for(i=0;i<size;i++) {
		for(j=0;j<size;j++) {
			result[i] += matrix[i][j] * vector[j];
		}
	}
	double t2 = omp_get_wtime();
	double elapsed_par = t2 - t1;
	cout<<"Elapsed time for parallel: "<<elapsed_par;
	return 0;
}
