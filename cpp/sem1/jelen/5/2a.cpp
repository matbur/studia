#include <iostream>
using namespace std;
#include <string>


string* wczytaj_stringi (int);
void sortuj_stringi (string*, int);
void wypisz_stringi (string*, int);


const int N = 40;
int n;

int
main() {
	string *tab = wczytaj_stringi(N);

    sortuj_stringi(tab, n);

    cout << endl << "po posortowaniu:" << endl;
    wypisz_stringi(tab, n);

 	delete[] tab;
	return 0;
}

string* wczytaj_stringi(int len) {

    string *tab = new string[len];

    for (n=0; n<len; ++n) {

        getline(cin, tab[n]);

        if (tab[n] == "")
            break;

    }

    return tab;
}


void sortuj_stringi(string *tab, int len) {
    bool changed;

    do {
        changed = false;

        for (int i=0; i<len-1; ++i)
            if (tab[i].compare(tab[i+1]) > 0) {
                swap(tab[i], tab[i+1]);
                changed = true;
            }
    } while (changed);
}


void wypisz_stringi(string *tab, int len) {
    cout << endl;
    for (int i=0; i<len; ++i)
        cout << tab[i] << endl;
}
