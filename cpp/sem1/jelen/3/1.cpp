#include <iomanip>
#include <cmath>
#include <iostream>
using namespace std;

int main()
{
    double xp, xk, dx;
    cout << "xp = ";
    cin >> xp;
    cout << "xk = ";
    cin >> xk;
    cout << "dx = ";
    cin >> dx;

    int w = 10;

    cout << setw(w) << "x"
         << setw(w) << "sin(x)"
         << setw(w) << "x/(x^2+1)"
         << setw(w) << "e^x"
         << setw(w) << "1/e^x"
         << endl << fixed;
    for (double i=xp; i<=xk; i+=dx)
        cout << setw(w) << setprecision(2) << i
             << setw(w) << setprecision(3) << sin(i)
             << setw(w) << setprecision(4) << i/(i*i+1)
             << setw(w) << setprecision(4) << exp(i)
             << setw(w) << setprecision(4) << exp(-i)
             << endl;

    return 0;
}
