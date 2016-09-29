#include <iostream>
using namespace std;
#include <cstring>

char * foo(char *);

int main()
{
    const int N = 80;
    char tab[N];

    cout << "Wpisz cus:" << endl;
    cin.getline(tab, N);

    cout << endl << '|' << foo(tab) << '|' << endl;

    return 0;
}


char * foo(char * arg)
{
    char * tab;
    int pocz, koniec, delta, licznik=0, len;

    for (len=0; arg[len]; ++len);

    for (pocz=0; arg[pocz]==' '; ++pocz);
    for (koniec=len-2; arg[koniec]==' '; --koniec);
    for (int i=pocz; i<koniec; ++i)
        if (arg[i] == ' ' and arg[i+ 1] == ' ')
            ++licznik;

    delta = koniec - pocz - licznik + 1;
    tab = new char[delta + 1];

    for (int i=0; i<delta; ++i)
    {
        while (arg[i + pocz] == ' ' and arg[i + pocz + 1] == ' ')
            pocz++;
        tab[i] = arg[i + pocz];
    }

    tab[delta] = '\0';

    return tab;
}
