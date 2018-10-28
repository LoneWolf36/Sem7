#include <iostream>
#include <omp.h>
#include <time.h>
using namespace std;

int main() {
    int size = 100;
    int arr_s[size];
    int arr_p[size];
    int temp;
    clock_t start, end;
    
    srand(5);
    // Fill random values
    for(int i = 0; i < size; i++)
    {
        arr_s[i] = arr_p[i] = rand()%100;
    }
    
    // Serial bubble sort algorithm
    start = clock();
    for(int i = 0; i < size; i++)
    {
        for(int j = 0; j < size - 1; j++)
        {
            if (arr_s[j] > arr_s[j+1]) {
                temp = arr_s[j];
                arr_s[j] = arr_s[j+1];
                arr_s[j+1] = temp;
            }     
        }
    }
    end = clock();
    double serial_time = ((double)end-start) / CLOCKS_PER_SEC;
    cout<<"Elapsed time for serial: "<<serial_time<<endl;
    cout<<"Sorted values are: \n";
    for(int i = 0; i < size; i++)
    {
        cout<<arr_s[i]<<"\t";
    }

    double t1 = omp_get_wtime();
    // Parallel odd even sort algorithm
    for(int i = 0; i < size; i++)
    {
        if (i%2 == 0) {
            #pragma omp parallel for shared(arr_p)
            for(int j = 0; j < size - 1; j+=2)
            {
                if(arr_p[j] > arr_p[j+1])
                {
                    temp = arr_p[j];
                    arr_p[j] = arr_p[j+1];
                    arr_p[j+1] = temp;
                }
            }
        }
        else {
            #pragma omp parallel for shared(arr_p)
            for(int j = 1; j < size - 1; j+=2)
            {
                if(arr_p[j] > arr_p[j+1])
                {
                    temp = arr_p[j];
                    arr_p[j] = arr_p[j+1];
                    arr_p[j+1] = temp;
                }
            }
        }
    }
    double t2 = omp_get_wtime();
    cout<<"Elapsed time for parallel algorithm: "<<t2-t1<<endl;
    
    for(int i = 0; i < size; i++)
    {
        cout<<arr_p[i]<<"\t";
    }
    return 0;
}
