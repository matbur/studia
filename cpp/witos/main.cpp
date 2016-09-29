#include "box.h"

int main()
{
	int input; //Uzywane przy pozniejszym wczytywaniu
	Box b1, b2, b3, b4;

	cout << "Obecna liczba pudelek to " << Box::get_counter_boxes() << endl;

	cout << "Wporwadz rozmiar pierwszego pudelka: ";
	cin >> input;
    b1.create_box(input);

	cout << "Obecna liczba pudelek to " << b1.get_counter_boxes() << endl;
	cout << "Rozmiar pierwszego pudelka to " << b1.get_counter_boxes() << endl;
	b1.write_box();
	b1.read_box();
	cout << "Po wywolaniu funckji zerujacej: " << endl;
	b1.nullify_box();
	b1.read_box();
	cout << "Wporwadz rozmiar drugiego pudelka: ";
	cin >> input;
	b2.create_box(input);
	cout << "Podaj zakres losowania zawartosci pudelka (od 0):";
	cin >> input;
	b2.rand_box(input);
	cout << "Wylosowana zawartosc to: " << endl;
	b2.read_box();
	b2.sort_asc();
	cout << "Po posortowaniu rosnaco: " << endl;
	b2.read_box();
	b2.sort_desc();
	cout << "Po posortowaniu malejaco: " << endl;
	b2.read_box();
	cout << "Wporwadz rozmiar trzeciego pudelka: ";
	cin >> input;
	b3.create_box(input);
	b3.nullify_box();
	cout << "Zawartosc trzeciego pudelka po wyzerowaniu: " << endl;
	b3.read_box();
	cout << "Obecna liczba pudelek to " << Box::get_counter_boxes() << endl;
	b1 + b2;
	cout << "Obecna liczba pudelek to " << Box::get_counter_boxes() << endl;
	b4 = b3 + b2;
	cout << "Wynik dodania dwoch pierwszych pudelek to: ";
	b4.read_box();
	b4.delete_box();
	b1.delete_box();
	b2.delete_box();
	b3.delete_box();
	cout << "Po usunieciu liczba pudelek wynosi " << Box::get_counter_boxes();
	return 0;
}
