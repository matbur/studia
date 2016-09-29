#include <iostream>
using namespace std;
#include <ctime>


void swap(float &x, float &y);
time_t bubble(float tab[], int n);


int main()
{
	int n;
	float *tab;

	cout << "n = ";
	cin >> n;

	tab = new float[n];

	srand((int)time(nullptr));
	for (int i = 0; i < n; ++i)
		tab[i] = float(rand() / 1000. + rand());


	for (int i = 0; i < n; ++i)
		cout << tab[i] << endl;

	cout << "Sortowanie zajelo: " << bubble(tab, n) << " ms" << endl;

	for (int i = 0; i < n; ++i)
		cout << tab[i] << endl;

	delete[] tab;
	system("pause");
	return 0;
}

inline void swap(float &x, float &y)
{
	float temp = x;
	x = y;
	y = temp;
}

time_t bubble(float tab[], int n)
{
	time_t t0 = clock();

	for (int i = 0; i < n - 1; ++i)
		for (int j = 0; j < n - 1; ++j)
			if (tab[j] > tab[j + 1])
				swap(tab[j], tab[j + 1]);

	return clock() - t0;
}