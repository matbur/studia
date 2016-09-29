#include <iostream>
using namespace std;


int sum(int, int);				//prototyp

int main()
{
	cout << sum(1, 2);			//wywolanie

	return 0;
}

int sum(int a, int b)				//definicja
{
	cout << a + b << endl;
}

