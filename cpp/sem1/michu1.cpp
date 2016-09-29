#include <iostream>
using namespace std;

int main()
{
    double maxx, temp;

    cout << "podaj liczbe nr 1: ";
    cin >> maxx;

    for (int i=2; i<=1000; ++i)
    {
        cout << "podaj liczbe nr " << i << ": ";
        cin >> temp;
        if (temp > maxx)
            maxx = temp;
    }
    cout << endl << "najwieksza liczba to " << maxx << endl;

    return 0;
}
