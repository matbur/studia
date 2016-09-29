//#include "integer.h"
//
//Integer::Integer(int arg) : Natural(abs(arg)), sign(arg >= 0) {}
//
//Integer::Integer(const char arg) : Natural(arg) {}
//
//Integer::Integer(const char* arg) : Natural(arg), sign(*arg != '-') {}
//
//Integer::Integer(const string& arg) : Natural(arg), sign(arg[0] != '-') {}
//
//Integer::Integer(const Integer& arg) : Natural(arg), sign(arg.sign) {}
//
//Integer::Integer(const Natural& arg) : Natural(arg), sign(1) {}
//
//void Integer::correct() {
//    // przeniesienie jedynki w prawo, gdy trzeba
//    for (vii i = begin(); i != end(); ++i)
//        while (*i < 0) {
//            *i++ += 10;
//            *i-- -= 1;
//        }
//
//    // usuniecie zer na poczatku
//    while (!(*this)[-1] and size() > 1)
//        pop_back();
//}
//
//Integer Integer::operator+() const { return *this; }
//
//Integer Integer::operator-() const {
//    Integer res = *this;
//    res.sign = !sign;
//    return res;
//}
//
//Natural abs(const Integer& arg) {
//    Natural res = arg;
//    return res;
//}
//
//Integer Integer::operator+(const Integer& arg) const {
//    if (sign == arg.sign) {
//        Integer res = abs(*this) + abs(arg);
//        res.sign = sign;
//        return res;
//    } else if (abs(*this) >= abs(arg)) {
//        Integer res = abs(*this);
//
//        for (size_t i = 0; i < arg.size(); ++i)
//            res[i] -= arg[i];
//
//        res.sign = sign;
//        res.correct();
//        return res;
//    } else {
//        Integer res = abs(arg);
//
//        for (size_t i = 0; i < size(); ++i)
//            res[i] -= (*this)[i];
//
//        res.sign = arg.sign;
//        res.correct();
//        return res;
//    }
//}
//
//Integer Integer::operator-(const Integer& arg) const { return *this + -arg; }
//
//Integer Integer::operator*(const Integer& arg) const {
//    Integer res = abs(*this);
//    res *= abs(arg);
//    res.sign = (sign == arg.sign);
//    return res;
//}
//
//Integer Integer::operator/(const Integer& arg) const {
//    if (!arg)
//        return 0;
//
//    Integer res;
//    while (*this >= arg * ++res)
//        ;
//    res--;
//    res.sign = (sign == arg.sign);
//    return res;
//}
//
//Integer Integer::operator%(const Integer& arg) const {
//    Integer res = *this / arg;
//    return *this - arg * res;
//}
//
//Integer Integer::operator++(int) {
//    Integer res = *this;
//    *this -= -Integer(1);
//    return res;
//}
//
//Integer Integer::operator++() {
//    *this -= -Integer(1);
//    return *this;
//}
//
//Integer Integer::operator--(int) {
//    Integer res = *this;
//    *this -= Integer(1);
//    return res;
//}
//
//Integer Integer::operator--() {
//    *this -= Integer(1);
//    return *this;
//}
//
//Integer& Integer::operator-=(const Integer& arg) {
//    *this = *this - arg;
//    return *this;
//}
//
//Integer& Integer::operator/=(const Integer& arg) {
//    *this = *this / arg;
//    return *this;
//}
//
//Integer& Integer::operator%=(const Integer& arg) {
//    *this = *this % arg;
//    return *this;
//}
//
//bool Integer::operator==(const Integer& arg) const {
//    if (sign == arg.sign and abs(*this) == abs(arg))
//        return true;
//    return false;
//}
//
//bool Integer::operator>(const Integer& arg) const {
//    if (sign)
//        if (!arg.sign)
//            return true;
//        else
//            return abs(*this) > abs(arg);
//
//    else if (arg.sign)
//        return false;
//    else
//        return abs(*this) > abs(arg);
//}
//
//Integer::operator string() const {
//    string res = (sign ? "+" : "-");
//    res += string(abs(*this));
//    return res;
//}
//
//ostream& operator<<(ostream& os, const Integer& arg) {
//    os << "|" << (arg.sign ? '+' : '-') << Natural(arg);
//    return os;
//}
//
//istream& operator>>(istream& is, Integer& arg) {
//    string temp;
//    is >> temp;
//    arg = Integer(temp);
//    return is;
//}
