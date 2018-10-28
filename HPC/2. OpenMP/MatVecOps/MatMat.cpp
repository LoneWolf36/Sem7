#include<iostream>
#include<omp.h>
#include<time.h>
using namespace std;

int main()
{
    int size = 500;
    int mat1[size][size];
    int mat2[size][size];
    int result[size][size];
    clock_t start, end;

    // Initializing arrays
    for(int i = 0; i < size; i++)
    {
        for(int j = 0; j < size; j++)
        {
            result[i][j] = 0;
            mat1[i][j] = mat2[i][j] = j * 2;
        } 
    }
    
    // Serial multiplication
    start = clock();
    for(int i = 0; i < size; i++)
    {
        for(int j = 0; j < size; j++)
        {
            for(int k = 0; k < size; k++)
            {
                result[i][j] += mat1[i][k] * mat2[k][j];
            }
        }
    }
    end = clock();
    double serial_time = ((double)end-start) / CLOCKS_PER_SEC;
    cout<<"Elapsed time for serial: "<<serial_time<<endl;

    double t1 = omp_get_wtime();
    #pragma omp parallel for shared(mat1 , mat2, result) schedule(static) num_threads(4)
    for(int i = 0; i < size; i++)
    {
        for(int j = 0; j < size; j++)
        {
            for(int k = 0; k < size; k++)
            {
                result[i][j] += mat1[i][k] * mat2[k][j];
            }
        }
    }
    double t2 = omp_get_wtime();
    cout<<"Elapsed time for parallel: "<<t2-t1;
    return 0;
}
