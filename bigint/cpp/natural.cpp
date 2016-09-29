#include "natural.h"

Natural::Natural(int arg) : vec(new std::vector<int>) {
	if (arg)
		for (size_t i = 0; std::pow(float(10), float(i)) <= arg; ++i)
			push_back(arg / int(std::pow(float(10), float(i))) % 10);
	else
		push_back(0);
}

Natural::Natural(const char arg) : vec(new std::vector<int>) {
	push_back(arg - '0');
}

Natural::Natural(const Natural &arg) : vec(new std::vector<int>) {
	for (size_t i = 0; i < arg.size(); ++i)
		push_back(arg[i]);
}

Natural::~Natural() {
	delete vec;
	vec = nullptr;
}

Natural &Natural::operator=(const Natural &arg) {
	if (this == &arg)
		return *this;

	vec->clear();
	for (size_t i = 0; i < arg.size(); ++i)
		push_back(arg[i]);

	return *this;
}

Natural Natural::operator++(int) {
	Natural res = *this;
	*this += 1;
	return res;
}

Natural Natural::operator++() {
	*this += 1;
	return *this;
}

Natural::operator std::string() const {
	std::stringstream ss;
	std::string res;
	ss << *this;
	ss >> res;
	return res;
}

Natural::operator const char *() const {
	std::string s = *this;
	return s.c_str();
}

std::ostream &operator<<(std::ostream &os, const Natural &arg) {
	for (size_t i = 1; i <= arg.size(); ++i)
		os << arg[-i];
	return os;
}

std::istream &operator>>(std::istream &is, Natural &arg) {
	std::string temp;
	is >> temp;
	arg = temp;
	return is;
}

int &Natural::operator[](int arg) const {
	// Python way :)
	if (arg >= 0)
		return *(vec->begin() + arg);
	return *(vec->end() + arg);
}

