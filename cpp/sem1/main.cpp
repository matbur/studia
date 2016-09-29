#include <ctime>
#include <cstdlib>
#include <string>
#include <conio.h>
#include <iomanip>
#include <iostream>
using namespace std;

void a(), b(), c(), d(), e();

int main()
{
	char menu;
	cout << "\t\tZABAWY Z TABLICAMI" << endl;
	cout << "Ktory podpunkt chcesz wywolac(a, b, c, d, e): ";
	cin >> menu;
	switch (menu)
	{
	case 'a': a(); break;
	case 'b': b(); break;
	case 'c': c(); break;
	case 'd': d(); break;
	case 'e': e(); break;
	default: cout << "Nieznana komenda" << endl;
	}

	system("pause");
	return 0;
}


void a()
{
    cout << "Po podaniu rozmiaru tablicy, program wypelni ja liczbami losowymi z przedzialu [-30, 50]" << endl;
    int N;
    int *tab;

    cout << "Podaj rozmiar tablicy: N = ";
    cin >> N;

    tab = new int[N];

    srand(time(NULL));

    for (int i=0; i!=N; ++i)
        cout << (i ? ", " : "") << (tab[i] = rand() % 81 - 30);

    delete[] tab;

    cout << endl << "Odpowiedz brzmi: TAK" << endl;
}


void b()
{
    cout << "Po wypelnieniu tablicy program okresli jej monotonicznosc" << endl;
    static int N = 10;
    double tab[N];
    int constant = 0,
        ascending = 0,
        decreasing = 0;

    cout << "Wypelnij tablice 10-elementowa:" << endl;
    for (int i=0; i!=N; ++i)
    {

        cout << "tab[" << i << "] = ";
        cin >> tab[i];
    }

    for (int i=1; i!=N; ++i)
    {
        if (tab[i-1] == tab[i])
            constant++;
        if (tab[i-1] <= tab[i])
            ascending++;
        if (tab[i-1] >= tab[i])
            decreasing++;
    }

    if (constant == N - 1)
        cout << "stala";
    else if (ascending == N - 1)
        cout << "rosnaca";
    else if (decreasing == N - 1)
        cout << "malejaca";
    else
        cout << "niemonotoniczna";

    cout << endl;
}


void c()
{
    cout << "Po podaniu rozmiaru tablicy i wyoelnieniu jej program zamieni kolejnosc elementow o 1" << endl;
    int N;
    double *tab;
    double temp;

    cout << "Podaj rozmiar tablicy: N = ";
    cin >> N;

    tab = new double[N];


    cout << endl << "Wypelnij tablice N-elementowa:" << endl;
    for (int i=0; i!=N; ++i)
    {
        cout << "tab[" << i << "] = ";
        cin >> tab[i];
    }

    cout << endl << "Tablica przed zmiana:" << endl;
    for (int i=0; i!=N; ++i)
        cout << (i ? ", " : " ") << tab[i];

    //ALGORYTM!!!
    temp = tab[N-1];
    for (int i=N-1; i; --i)
        tab[i] = tab[i-1];
    tab[0] = temp;


    cout << endl << "Tablica po zmianie:" << endl;
    for (int i=0; i!=N; ++i)
        cout << (i ? ", " : " ") << tab[i];

    cout << endl << endl;

    delete[] tab;
}


void d()
{
    cout << "Program tworzy ranking wynikow w kolejnosci malejacej" << endl;
    int N = 100;
    double *tab = new double[N];
    double counter = 0;

    for (int i=0; i!=N; ++i)
        tab[i] = 0;

    cout << "Dodanie zerowego czasu zakonczy program" << endl;
    for (int i=0; i!=N && tab[i-1]; ++i)
    {
        cout << "Dodaj czas: ";
        cin >> tab[i];
        if (tab[i])
            ++counter;

        for (int j=i; j && i; --j)
        {
            if (tab[j] > tab[j-1])
            {
                double temp = tab[j];
                tab[j] = tab[j-1];
                tab[j-1] = temp;
            }
        }

        cout << "Aktualny ranking:";
        for (int j=0; j!=counter; ++j)
        {
            cout << (j ? ", " : "") << tab[j];
        }
        cout << endl << endl;
    }

    delete[] tab;
}


void e()
{
    cout << "Program zliczy ile razy wpisywane byly konkretne litery" << endl;
    static int N = 26;
    double tab[N];
    char letter;

    for (int i=0; i!=N; ++i)
        tab[i] = 0;

    cout << "Klikniecie ESC zakonczy program" << endl;
    do
    {
        letter = getche();
        if (letter >= 'A' && letter <= 'Z')
            ++tab[letter - 'A'];
        if (letter >= 'a' && letter <= 'z')
            ++tab[letter - 'a'];
    }
    while (letter != 27);

    cout << endl;
    for (int i=0; i!=N; ++i)
        cout << char(i+'A') << " " << tab[i] << " " << setfill('#') << setw(tab[i]) << "" << endl;
}
