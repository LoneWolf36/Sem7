#include "cuda_runtime.h"
#include "device_launch_parameters.h"
#include <cstdlib>
#include <iostream>
#include <numeric>
#include <time.h>
#include <math.h>
#include <stdio.h>
using namespace std;

__global__ void sum(double* input)
{
	const int tid = threadIdx.x;
	int step_size = 1;
	int number_of_threads = blockDim.x;

	while (number_of_threads > 0)
	{
		if (tid < number_of_threads)
		{
			const int fst = tid * step_size * 2;
			const int snd = fst + step_size;
			input[fst] += input[snd];
		}
		step_size <<= 1;
		number_of_threads >>= 1;
	}
}

__global__ void variance(int* n, double *x, double *mean)
{
	int index = threadIdx.x;
	int stride = blockDim.x;

	for (int i = index; i < *n; i+= stride) {
		x[i] = (x[i] - *mean) ;
		x[i] = x[i] * x[i];
	}
}

double calculateMean(int count , double *h) {

	const int size = count * sizeof(double);
	double* d;

	//mean
	cudaMalloc(&d, size);
	cudaMemcpy(d, h, size, cudaMemcpyHostToDevice);
	sum<<<1, count / 2 >>> (d);
	double result;
	cudaMemcpy(&result, d, sizeof(double), cudaMemcpyDeviceToHost);
	result /= count;
	// cout << "\nAverage is " << result << endl;
	cudaFree(d);
	return result;
}

double* calculateVariance(int *count , double *h , double *mean) {

	const int size = (*count) * sizeof(double);
	double* d ;
	int* n;
	double *tempMean;

	cudaMalloc(&d, size);
	cudaMalloc(&n, sizeof(int));
	cudaMalloc(&tempMean, sizeof(double));
	cudaMemcpy(d, h, size, cudaMemcpyHostToDevice);
	cudaMemcpy(n, count, sizeof(int), cudaMemcpyHostToDevice);
	cudaMemcpy(tempMean, mean, sizeof(double), cudaMemcpyHostToDevice);
	variance<<<1, (*count)/2 >>> (n , d  , tempMean);
	cudaMemcpy(h, d, size, cudaMemcpyDeviceToHost);
	cudaFree(d);
	cudaFree(n);
	cudaFree(tempMean);
	return h;
}

int main()
{
	const int count = 8;      // size of array
	srand(2);
    double h[count];
    cout << "Elements of array are : " << endl;
	for ( int i = 0 ; i < count ; i ++ ) {
		h[i] = rand()%10;
		cout << h[i] << "\t" ;
    }
	int tempCount = count ;
	double mean = calculateMean(count , h);
	cout << "\nMean is : " << mean << endl;
	double *res = calculateVariance(&tempCount , h , &mean);
	// for ( int i = 0 ; i < count ; i ++ ) {
	//
    //         cout << res[i] << "\t" ;
	//
    // }
	double variance = calculateMean(count , res);
	cout << "Variance is : " << variance << endl;
	cout << "Standard Deviation is : " << sqrt(variance) << endl;
	delete[] &h;
	return 0;
}
