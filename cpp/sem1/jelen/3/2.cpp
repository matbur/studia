#include <iostream>
using namespace std;

void foo(int, int);

int main()
{
    int k, n;
    cout << "k = ";
    cin >> k;
    cout << "n = ";
    cin >> n;

    foo(k, n);

    cout << endl;
    return 0;
}

void foo(int k, int n)
{
    if (n < 2 || n > 36)
    {
        cout << "n poza zakresem";
        return;
    }

    int r = k % n;

    if (k >= n)
        foo(k/n, n);

    if (r < 10)
        cout << r;
    else
        cout << char(r - 10 + 'a');
}
