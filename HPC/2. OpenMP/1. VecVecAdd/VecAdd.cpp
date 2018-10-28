#include<iostream>
#include<omp.h>
#include <time.h>
using namespace std;

int main() {
    double total_time_serial, total_time_parallel;
    int size = 7000;
    int vec1[size];
    int vec2[size];
    int result[size];
    clock_t start, end;
    
    // Setting values
    for(int i = 0; i < size; i++)
    {
        vec1[i] = vec2[i] = i;
        result[i] = 0;
    }
    
    // Serial addition
    start = clock();
    for(int i = 0; i < size; i++)
    {
        result[i] = vec1[i] + vec2[i];
    }
    end = clock();
    
    double elapsed_ser = ((double)end-start)/CLOCKS_PER_SEC;
    cout<<"Elapsed time for serial calculation is "<<elapsed_ser<<endl;

    // Setting result to 0 again
	for(int i=0;i<size;i++) {
		result[i] = 0;
	}
    
    // Parallel addition
    double t1 = omp_get_wtime();
    #pragma omp parallel for default(none) shared (vec1, vec2, result, size)
    
    for(int i = 0; i < size; i++)
    {
        result[i] = vec1[i] + vec2[i];
    }
    double t2 = omp_get_wtime();	 
    cout<<"Elapsed time for parallel calculation is "<<t2-t1; 
    return 0;
}
