#include <iostream>
using namespace std;
#include <cstring>


char** wczytaj_stringi (int);
void sortuj_stringi (char **, int);
void wypisz_stringi (char **, int);


const int N = 40;
int n;

int
main() {
	char **tab = wczytaj_stringi(N);

    sortuj_stringi(tab, n);

    cout << endl << "po posortowaniu:" << endl;
    wypisz_stringi(tab, n);


    for (int i=0; i<n; ++i)
        delete[] tab[i];
	delete[] tab;
	return 0;
}

char** wczytaj_stringi(int len) {

    char **tab = new char*[len];

    int i;
    for (i=0; i<len; ++i) {

        tab[i] = new char[N];
        cin.getline(tab[i], N);
        tab[i][strlen(tab[i])] = '\0';

        if (tab[i][0]=='\0')
            break;
    }

    n = i;

    return tab;
}


void sortuj_stringi(char **tab, int len) {
    bool changed;

    do {
        changed = false;

        for (int i=0; i<len-1; ++i)
            if (strcmp(tab[i], tab[i+1]) > 0) {
                swap(tab[i], tab[i+1]);
                changed = true;
            }
    } while (changed);
}


void wypisz_stringi(char **tab, int len) {
    cout << endl;
    for (int i=0; i<len; ++i)
        cout << tab[i] << endl;
}
