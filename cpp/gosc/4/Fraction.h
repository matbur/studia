#pragma once

#include <iostream>
using namespace std;

class Fraction
{
	int nominator;
	int denominator;

	static int created_count;
	static int exist_count;
	static int access_count;

public:

    Fraction();
	Fraction(int);
	Fraction(int, int);
	Fraction(Fraction&);
	~Fraction();

	int get_nominator();
	int get_denominator();

	void set_nominator(int);
	void set_denominator(int);

	char get_sign();

	static int get_created_count();
	static int get_exist_count();
	static int get_access_count();
	static void reset_access_count();
};
