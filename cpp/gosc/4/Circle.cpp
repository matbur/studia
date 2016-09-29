#include "Circle.h"

Circle::Circle()
: radius(1)
{
    cout << "Konstruktor Circle()" << endl;
}

Circle::Circle(int val) : center(val)
{
	if(val >= 0)
		radius = val;
	else
		radius = 1;

	cout << "Kontruktor Circle(int)" << endl;
}

Circle::Circle(int num, int den, float rad)
: center(num, den), radius(rad)
{
    cout << "Konstruktor Circle(int, int, float)" << endl;
}

Circle::Circle(Fraction f)
: center(f.get_nominator(), f.get_denominator())
{
    cout << "Konstruktor Circle(Fraction)" << endl;
}

Circle::Circle(Circle &c)
: center(c.get_center().get_nominator(), c.get_center().get_denominator()), radius(c.get_radius())
{
    cout << "Konstruktor Circle(Circle&)" << endl;
}

Circle::~Circle()
{
	cout << "Destruktor ~Circle()" << endl;
}

float Circle::get_radius()
{
	return radius;
}

Fraction Circle::get_center()
{
	return center;
}

void Circle::set_radius(float radius)
{
	 this->radius = radius;
}

void Circle::set_center(Fraction center)
{
	this->center = center;
}

void Circle::reset_fraction_access_count()
{
	Fraction::reset_access_count();
}
