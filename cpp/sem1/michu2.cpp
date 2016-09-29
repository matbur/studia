#include <iostream>
using namespace std;

int main()
{
    int n = 20;
    double *tab = new double[n];
    double sum = 0;
    int counter = 0;
    double avg = 0;

    for (int i=0; i<n; ++i)
    {
        cout << "podaj liczbe nr " << i+1 << ": ";
        cin >> tab[i];

        if (tab[i] > 0)
        {
            sum += tab[i];
            counter++;
        }
    }

    avg = sum / counter;
    cout << endl << "srednia dodatnich liczb wynosi " << avg << endl;

    delete[] tab;
    return 0;
}
