#include <iostream>
using namespace std;

void sum(int, int);
void sum(float, float);

int main()
{
	sum(1, 2);
	sum(1., 2.);
	
	return 0;
}

void sum(int a, int b)
{
	cout << a + b << endl;
}
void sum(float a, float b)
{
	cout << a + b << endl;
}
