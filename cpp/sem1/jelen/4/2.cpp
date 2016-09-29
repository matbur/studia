#include <iostream>
using namespace std;


int fib_iter(int);
int fib_rec(int);


int main()
{
    int n;
    cout << "n = ";
    cin >> n;

    cout << "fib(" << n << ") = " << fib_iter(n) << endl;

    return 0;
}


int fib_iter(int n)
{
    int a = 0, b = 1, c;

    for (int i=0; i<n; ++i)
    {
        c = b;
        b = a;
        a = b + c;
    }

    return a;
}

int fib_rec(int n)
{
    if (n > 1)
        return fib_rec(n-1)+ fib_rec(n-2);
    else if (n == 1)
        return 1;
    else
        return 0;
}