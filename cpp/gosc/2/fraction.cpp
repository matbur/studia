#include "fraction.h"
/*
Fraction::Fraction(int num, int den)
    : _numerator(num),
      _denominator(den)
{ }
*/
char Fraction::sign()
{
    int sign = get_numerator() * get_denominator();

    if (sign == 0)
        return ' ';
    else if (sign > 0)
        return '+';
    else
        return '-';
}

template <typename T>
string type2string(T t)
{
    stringstream ss;
    string s;
    ss << t;
    ss >> s;
    return s;
}

string Fraction::repr()
{
    string result = "";
    result += type2string(get_numerator());
    result += "/";
    result += type2string(get_denominator());
    return result;
}
