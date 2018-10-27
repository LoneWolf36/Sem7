#include "cuda_runtime.h"
#include "device_launch_parameters.h"
#include <cstdlib>
#include <iostream>
#include <numeric>
#include <time.h>
#include <math.h>
using namespace std;

__global__ void max(double* input)
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
			input[fst] = input[fst] >= input[snd] ? input[fst] : input[snd] ;
		}
		step_size <<= 1;
		number_of_threads >>= 1;
	}
}

__global__ void min(double* input)
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
			input[fst] = input[fst] <= input[snd] ? input[fst] : input[snd] ;
		}
		step_size <<= 1;
		number_of_threads >>= 1;
	}
}


int main()
{
	const int count = 10;      // size of array
	srand(2);
    double h[count];
    for ( int i = 0 ; i < count ; i ++ ) {
        h[i] = rand();
        cout << h[i] << "\t" ;
    }

    const int size = count * sizeof(double);
	double* d;

	// Mean
	cudaMalloc(&d, size);
	cudaMemcpy(d, h, size, cudaMemcpyHostToDevice);

	max<<<1, count / 2 >>> (d);

	double result;
	cudaMemcpy(&result, d, sizeof(double), cudaMemcpyDeviceToHost);

	cudaDeviceSynchronize();
	cout << "\nMax is " << result << endl;
	cudaFree(d);
    cudaMalloc(&d, size);
	cudaMemcpy(d, h, size, cudaMemcpyHostToDevice);
	min<<<1, count / 2 >>> (d);
	cudaMemcpy(&result, d, sizeof(double), cudaMemcpyDeviceToHost);
	cudaDeviceSynchronize();
	cout << "Min is " << result << endl;
	cudaFree(d);
/*
	// variance
	cudaMalloc(&d, size);
	cudaMemcpy(d, h, size, cudaMemcpyHostToDevice);

	double* temp ;
	cudaMalloc(&temp, sizeof(double));
	cudaMemcpy(temp, &res, sizeof(double), cudaMemcpyHostToDevice);

	var <<<1, count / 2 >>>(d, temp);

	cudaMemcpy(&res, d, sizeof(double), cudaMemcpyDeviceToHost);

	// res = res / count  ;

	cout << "Variance is " << res << endl;

	cudaFree(d);

	*/

	delete[] &h;
	return 0;
}
