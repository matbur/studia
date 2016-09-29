#include <iostream>
using namespace std;

int counter (char*, int);

int main()
{
    int n = 100;
    char *tab = new char[n];

    for (int i=0; i<n; ++i)
    {
        cout << "wpisz znak nr " << i+1 << ": ";
        cin >> tab[i];
    }

    cout << endl << "w podanych znakach ilosc cyfr wyniosla " << counter(tab, n) << endl;

    delete[] tab;
    return 0;
}

int counter (char *tab, int n)
{
    int temp = 0;

    for (int i=0; i<n; ++i)
        if (tab[i] >= '0' and tab[i] <= '9')
            temp++;

    return temp;
}
