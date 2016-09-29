#include "Natural.h"

Natural::Natural(int arg) : vec(new vector<int>) {
    if (arg)
        for (size_t i = 0; pow(10000, i) <= arg; ++i)
            push_back(int(arg / pow(10000, i)) % 10000);
    else
        push_back(0);
}

Natural::Natural(const char arg) : vec(new vector<int>) {
    push_back(arg - '0');
}

Natural::Natural(const char* arg) : vec(new vector<int>) {
    for (size_t i = strlen(arg); i; --i)
        if (isdigit(arg[i - 1]))
            push_back(arg[i - 1] - '0');
}

Natural::Natural(const string& arg) : vec(new vector<int>(arg.size()/4+1)) {
    for (size_t i = arg.size(); i; --i);
	for (size_t i = 0; i < arg.size() / 4; ++i)
		(*this)[i] = atoi((arg.substr(arg.size()-(4*(i+1), 4)).c_str()));
//        if (isdigit(arg[i - 1]))
//            push_back((arg.substr(arg.size()-(i*4))).stoi());
}

Natural::Natural(const Natural& arg) : vec(new vector<int>) {
    for (size_t i = 0; i < arg.size(); ++i)
        push_back(arg[i]);
}

Natural::~Natural() {
    delete vec;
    vec = NULL;
}

Natural& Natural::operator=(const Natural& arg) {
    if (this == &arg)
        return *this;

    vec->clear();
    for (size_t i = 0; i < arg.size(); ++i)
        push_back(arg[i]);

    return *this;
}

Natural Natural::operator+() const { return *this; }

Natural Natural::operator++(int) {
    Natural res = *this;
    *this += 1;
    return res;
}

Natural Natural::operator++() {
    *this += 1;
    return *this;
}

//bool Natural::operator==(const Natural& arg) const {
//    if (size() != arg.size())
//        return false;
//
//    for (size_t i = 0; i < size(); ++i)
//        if ((*this)[i] != arg[i])
//            return false;
//
//    return true;
//}
//
//bool Natural::operator>(const Natural& arg) const {
//    //        cout << size() << endl;
//    if (size() > arg.size())
//        return true;
//
//    if (size() < arg.size() or *this == arg)
//        return false;
//
//    for (size_t i = 1; i <= size(); ++i)
//        if ((*this)[-i] > arg[-i])
//            return true;
//        else if ((*this)[-i] < arg[-i])
//            return false;
//
//    return false;
//}
//
//bool Natural::operator>=(const Natural& arg) const {
//    return (*this == arg or *this > arg);
//}
//
//bool Natural::operator<(const Natural& arg) const { return !(*this >= arg); }
//
//bool Natural::operator<=(const Natural& arg) const { return !(*this > arg); }
//
//bool Natural::operator!=(const Natural& arg) const { return !(*this == arg); }

Natural::operator bool() const {
    if (*this == Natural(0))
        return false;
    return true;
}

bool Natural::operator!() const { return !bool(*this); }

Natural::operator string() const {
    string res;
    for (size_t i = 1; i <= size(); ++i)
        res += (*this)[-i] + '0';
    return res;
}

ostream& operator<<(ostream& os, const Natural& arg) {
    for (size_t i = 1; i <= arg.size(); ++i)
        os << "|" << arg[-i];
    return os;
}

istream& operator>>(istream& is, Natural& arg) {
    string temp;
    is >> temp;
    arg = Natural(temp);
    return is;
}

size_t Natural::size() const { return vec->size(); }

void Natural::push_back(int arg) { vec->push_back(arg); }

int& Natural::operator[](int arg) const {
    // Python way :)
    if (arg >= 0)
        return *(vec->begin() + arg);
    return *(vec->end() + arg);
}

void Natural::to_correct() {
    // przeniesienie jedynki w lewo, gdy trzeba
	for (size_t i = 0; i < size(); ++i)
		while ((*this)[i] > 9) {
			(*this)[i] -= 10;
			(*this)[i+1]++;
		}

    // usuniecie zer na poczatku
    while (!(*this)[-1] and size() > 1)
        vec->pop_back();
}
