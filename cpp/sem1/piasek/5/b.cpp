#include <iostream>
using namespace std;

char * foo(char *);

int main()
{
    const int N = 80;
    char tab[N];
    int len;

    cout << "Wpisz cus:" << endl;
    cin.getline(tab, N);

    cout << endl << '|' << foo(tab) << '|' << endl;

    return 0;
}


char * foo(char * arg)
{
    char * tab;
    int pocz, koniec, delta, len;

    for (len=0; arg[len]; ++len);

    for (pocz=0; arg[pocz]==' '; ++pocz);
    for (koniec=len-2; arg[koniec]==' '; --koniec);

    delta = koniec - pocz + 1;
    tab = new char[delta + 1];

    for (int i=0; i<delta; ++i)
        tab[i] = arg[i + pocz];

    tab[delta] = '\0';

    return tab;
}
