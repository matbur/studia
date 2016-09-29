#pragma once

#include "natural.h"

class Integer : public Natural {
public:

    Integer(int = 0);
    Integer(const char);
    Integer(const char*);
    Integer(const string&);
    Integer(const Integer&);
    Integer(const Natural&);

    Integer operator+() const;
    Integer operator-() const;
    friend Natural abs(const Integer&);

    Integer operator+(const Integer&) const;
    Integer operator-(const Integer&) const;
    Integer operator*(const Integer&) const;
    Integer operator/(const Integer&) const; // dorobic funkcje zaprzyjaznione
    Integer operator%(const Integer&) const;

    Integer operator++(int);
    Integer operator++();
    Integer operator--(int);
    Integer operator--();

    Integer& operator-=(const Integer&);
    Integer& operator/=(const Integer&);
    Integer& operator%=(const Integer&);

    bool operator==(const Integer&) const;
    bool operator>(const Integer&) const;

    operator string() const;

    friend ostream& operator<<(ostream&, const Integer&);
    friend istream& operator>>(istream&, Integer&);

private:
    bool sign; // if >= 0: 1 else 0

    void correct();
};
