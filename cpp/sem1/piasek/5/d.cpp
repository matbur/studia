#include <iostream>
using namespace std;
#include <cstdlib>
#include <cstring>


int foo(char *);


int main()
{
    char* liczby = "12   -3    64    2     78";

    cout << endl << foo(liczby) << endl;

    return 0;
}


int foo(char * arg)
{
    int suma = 0;

    for (int i=0; i<(int)strlen(arg); ++i)
    {
        while (arg[0] == ' ')
            arg++;

        suma += atoi(arg);

        while ((arg[0] >= '0' && arg[0] <= '9') || arg[0] == '-')
            arg++;
    }

    return suma;
}
