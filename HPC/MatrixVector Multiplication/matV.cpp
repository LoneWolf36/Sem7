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
		cout<<result[i]<<endl;
	}
	double end = omp_get_wtime();
	double elapsed_ser = end - start;
	cout<<"Elapsed time for serial: "<<elapsed_ser;
	
	#pragma omp parallel
	{
		
	}
	return 0;
}
