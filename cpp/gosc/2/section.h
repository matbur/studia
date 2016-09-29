#pragma once

#include "fraction.h"

class Section
{
public:
    int get_begin_numerator() { return _begin.get_numerator(); }
    int get_begin_denominator() { return _begin.get_denominator(); }
    string get_begin() { return _begin.repr(); }

    int get_end_numerator() { return _end.get_numerator(); }
    int get_end_denominator() { return _end.get_denominator(); }
    string get_end() { return _end.repr(); }

    void set_begin_numerator(int num) { _begin.set_numerator(num); }
    void set_begin_denominator(int den) { _begin.set_denominator(den); }

    void set_end_numerator(int num) { _end.set_numerator(num); }
    void set_end_denominator(int den) { _end.set_denominator(den); }

    string repr();

private:
    Fraction _begin;
    Fraction _end;
};
