#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

double* allocate_and_fill(int);


int main(int argc, char**argv)
{
    srand(time(NULL));

    int volume = atoi(argv[1]);
    double *table = allocate_and_fill(volume);

    if (table)
        for (int i=0; i<volume; ++i)
            cout << table[i] << endl;

    delete[] table;
    return 0;
}


double* allocate_and_fill(int n)
{
    if (n < 0)
        return NULL;

    double *result = new double[n];

    for (int i=0; i<n; ++i)
        result[i] = rand() % 10001 / 1000. - 5;

    return result;
}
