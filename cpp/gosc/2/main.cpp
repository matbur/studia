#include "section.h"

int main()
{
    Fraction f;

    f.set_numerator(1);
    f.set_denominator(1);
    //cout << f.get_numerator() << '/' << f.get_denominator() << endl;
    cout << f.repr() << endl;
    cout << f.sign() << endl;

    f.set_numerator(-1);
    f.set_denominator(2);
    //cout << f.get_numerator() << '/' << f.get_denominator() << endl;
    cout << f.repr() << endl;
    cout << f.sign() << endl;

    Section s;

    s.set_begin_numerator(3);
    s.set_begin_denominator(4);
    cout << s.get_begin() << endl;

    s.set_end_numerator(5);
    s.set_end_denominator(6);
    cout << s.get_end() << endl;

    cout << s.repr() << endl;

    s.set_begin_numerator(31);
    s.set_begin_denominator(41);
    cout << s.get_begin() << endl;

    s.set_end_numerator(51);
    s.set_end_denominator(61);
    cout << s.get_end() << endl;

    cout << s.repr() << endl;

    return 0;
}
