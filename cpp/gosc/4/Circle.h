#pragma once

#include "Fraction.h"

//#include <iostream>
//using namespace std;

class Circle
{
	Fraction center;
	float radius;

public:

    Circle();
	Circle(int);
	Circle(int, int, float);
	Circle(Fraction);
	Circle(Circle&);
	~Circle();

	float get_radius();
	Fraction get_center();

	void set_radius(float);
	void set_center(Fraction);

	static void reset_fraction_access_count();
};
