#include <iostream>
#include <string>
#include <sstream>
using namespace std;

double* przyjmij_wspolczynniki (int);
string drukuj_wielomian (int, double*);
double wartosc_wielomianu (int, double*, double);

int main ()
{
    int n;
    double x, *a;

    cout << "stopien wielomianu = ";
    cin >> n;

    cout << endl;
    a = przyjmij_wspolczynniki(n);
    if (a == nullptr)
        return 0;

    cout << endl << "x = ";
    cin >> x;

    cout << endl << endl << "Wartosc wielomianu"
         << endl << endl << "\tW(x) = " << drukuj_wielomian(n, a)
         << endl << endl << "dla"
         << endl << endl << "\tx = " << x
         << endl << endl << "wynosi"
         << endl << endl << "\tW(" << x << ") = " << wartosc_wielomianu(n, a, x)
         << endl;

    delete[] a;
    return 0;
}

double* przyjmij_wspolczynniki (int n)
{
    double *a = new double[n+1];

    cout << "a" << n << "= ";
    cin >> a[0];
    if (!a[0])
    {
        cout << "Wspolczynnik przy najwyzszej potedze nie moze byc zerem!";
        return nullptr;
    }

    for (int i=1; i<n+1; ++i)
    {
        cout << "a" << n-i << "= ";
        cin >> a[i];
    }

    return a;
}

template <typename T>
string t2s(T t)                 //  t2s == type to string
{
    stringstream ss;
    string s;
    ss << t;
    ss >> s;
    return s;
}

string drukuj_wielomian (int n, double *a)
{
    string s;

    for (int i=0; i<n-1; ++i)
        s += "(";

    if (a[0] != 1)
        s += t2s(a[0]);

    for (int i=1; i<n+1; ++i)
        s += "x + " + t2s(a[i]) + (i != n ? ")" : "");

    return s;
}

double wartosc_wielomianu (int n, double *a, double x)
{
    double wartosc = a[0];
    for (int i=1; i<n+1; ++i)
    {
        wartosc *= x;
        wartosc += a[i];
    }

    return wartosc;
}
