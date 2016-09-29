#include <cmath>
#include <iostream>
using namespace std;

bool is_prime(int);
int how_much_primes(int, int);


int main()
{
    int n, k;
    cout << "n = ";
    cin >> n;
    cout << "k = ";
    cin >> k;

    cout << how_much_primes(n, k) << endl;

    return 0;
}


bool is_prime(int n)
{
    if (n < 2)
        return false;
    if (n == 2)
        return true;

    for (int i=2; i<=ceil(sqrt(n)); ++i)
        if (n % i == 0)
            return false;

    return true;
}


int how_much_primes(int n, int k)
{
    int ile = 0;

    for (int i=n; i<=k; ++i)
        if (is_prime(i))
            ++ile;

    return ile;
}
