#pragma once

#include <iostream>
#include <string>
#include <sstream>

using namespace std;

class Fraction
{
public:
    int get_numerator() { return _numerator; }
    void set_numerator(int num) { _numerator = num; }

    int get_denominator() { return _denominator; }
    void set_denominator(int den) { _denominator = den; }

    char sign();
    string repr();

private:
    int _numerator;
    int _denominator;
};
