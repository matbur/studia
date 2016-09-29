#include "Fraction.h"

int Fraction::created_count = 0;
int Fraction::exist_count = 0;
int Fraction::access_count = 0;

Fraction::Fraction()
: nominator(1), denominator(3)
{
	created_count++;
	exist_count++;
    cout << "Konstruktor Fraction()" << endl;
}
Fraction::Fraction(int val)
{
	if(val != 0)
		nominator = denominator = val;
	else
	{
		nominator = val;
		denominator = 1;
	}

	created_count++;
	exist_count++;
	cout << "Kontruktor Fraction(int)" << endl;
}

Fraction::Fraction(int num, int den)
: nominator(num), denominator(den)
{
	created_count++;
	exist_count++;
    cout << "Konstruktor Fraction(int, int)" << endl;
}

Fraction::Fraction(Fraction &f)
: nominator(f.get_nominator()), denominator(f.get_denominator())
{
	created_count++;
	exist_count++;
    cout << "Konstruktor Fraction(Fraction&)" << endl;
}

Fraction::~Fraction()
{
	exist_count--;
	cout << "Destruktor ~Fraction()" << endl;
}

int Fraction::get_nominator()
{
	access_count++;
	return nominator;
}

int Fraction::get_denominator()
{
	access_count++;
	return denominator;
}

void Fraction::set_nominator(int val)
{
	access_count++;
	nominator = val;
}

void Fraction::set_denominator(int val)
{
	access_count++;
	denominator = val;
}

char Fraction::get_sign()
{
	if(nominator * denominator < 0)
		return '-';
	else if(nominator == 0)
		return '0';
	else
		return '+';
}

int Fraction::get_created_count()
{
	return created_count;
}

int Fraction::get_exist_count()
{
	return exist_count;
}

int Fraction::get_access_count()
{
	return access_count;
}

void Fraction::reset_access_count()
{
	access_count = 0;
}
