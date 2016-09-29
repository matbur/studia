#include <iostream>
using namespace std;
#include <cmath>

double trapez (double, double, double);
double wartosc(double, double, double);
double calka(double, double, double, double, double);

int main ()
{
    double a, b, xp, xk, eps;
	
	cout << "a = ";
	cin >> a;
	cout << "b = ";
	cin >> b;
	cout << "xp = ";
	cin >> xp;
	cout << "xk = ";
	cin >> xk;
	cout << "epsilon = ";
	cin >> eps;

    cout << "Calka wynosi " << calka(a, b, xp, xk, eps) << endl;

	system("pause");
    return 0;
}

double trapez(double p, double q, double h)
{
    return h * (p + q) / 2;
}

double wartosc(double a, double b,double x)
{
    return a * exp(x + 1) + b;
}

double calka(double a, double b, double xp, double xk, double eps)
{
    double pole;
    double poprzedni = 0;

    int i = 1;
    while (1)
    {
        pole = 0;

        for (int j=0; j<i; ++j)
            pole += trapez(wartosc(a, b, xp + (xk - xp) / i * j), wartosc(a, b, xp + (xk - xp) / i * (j + 1)), (xk - xp) / i);

        if (abs((pole - poprzedni) / poprzedni) <= eps)
            break;
		
        poprzedni = pole;
        i *= 2;
    }

    return pole;
}
