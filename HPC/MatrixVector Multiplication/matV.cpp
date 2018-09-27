#include <iostream>
#include <omp.h>
using namespace std;

int main() {
	double total_time_serial, total_time_parallel;
	int size = 1000;
	int matrix[size][size], vector[size], result[size];
	int i, j;
	
	// Setting values
	for(i=0;i<size;i++) {
		for(j=0;j<size;j++) {
			matrix[i][j] = j*2;
		}
		vector[i] = i*2;
		result[i] = 0;
	}
	
	// Serial matrix multiplication
	double start = omp_get_wtime();
	for(i=0;i<size;i++) {
		for(j=0;j<size;j++) {
			result[i] += matrix[i][j] * vector[j];
		}
	}
	double end = omp_get_wtime();
	double elapsed_ser = end - start;
	cout<<"Elapsed time for serial: "<<elapsed_ser;
	
	// Setting result to 0 again
	for(i=0;i<size;i++) {
		result[i] = 0;
	}
	
	// Parallel matrix multiplication
	start = omp_get_wtime();
	#pragma omp parallel shared (matrix, vector, result) private (i, j)
	{
		#pragma omp for schedule(static)
		for(i=0;i<size;i++) {
			for(j=0;j<size;j++) {
				result[i] += matrix[i][j] * vector[j];
			}
		}
	}
	end = omp_get_wtime();
	double elapsed_par = end - start;
	cout<<"Elapsed time for parallel: "<<elapsed_par;
	return 0;
}
