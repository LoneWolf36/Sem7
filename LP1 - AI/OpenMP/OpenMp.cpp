#include<iostream>
#include<omp.h>
using namespace std;
int main() {
	int th_id;
	#pragma omp parallel
	{
		th_id = omp_get_max_threads();
		cout<<th_id; 
	} 
	return 0;
}
