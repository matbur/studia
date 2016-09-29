#include "integer.h"

Integer Integer::add_if_same_sign(const Integer &arg) const {
	Integer res = abs(*this) + abs(arg);
	res.sign = sign;
	return res;
}

Integer Integer::add_if_different_sign(const Integer &arg) const {
	if (abs(*this) == abs(arg))
		return 0;

	Integer res;

	// atgaa -> abs (of) this (is) greater (than) abs (of) arg: 1 or -1
	int atgaa = (abs(*this) > abs(arg)) * 2 - 1;

	do res.push_back(0);
	while (res.size() < std::max(size(), arg.size()));

	for (size_t i = 0; i < res.size(); ++i) {
		if (i < size())
			res[i] += (*this)[i] * atgaa;

		if (i < arg.size())
			res[i] += arg[i] * -atgaa;

		if (res[i] < 0) {
			res[i] += 10;
			res[i + 1]--;
		}
	}

	res.sign = (atgaa == 1 ? sign : arg.sign);

	while (!res[-1])
		res.vec->pop_back();

	return res;
}

Integer Integer::operator-() const {
	Integer res = *this;
	res.sign = !sign;
	return res;
}


Integer Integer::operator++(int) {
	Integer res = *this;
	*this += 1;
	return res;
}

Integer Integer::operator++() {
	*this += 1;
	return *this;
}

Integer Integer::operator--(int) {
	Integer res = *this;
	*this -= 1;
	return res;
}

Integer Integer::operator--() {
	*this -= 1;
	return *this;
}

Integer::operator std::string() const {
	std::stringstream ss;
	std::string res;
	ss << *this;
	ss >> res;
	return res;
}

Integer::operator const char *() const {
	std::string s = *this;
	return s.c_str();
}

std::ostream &operator<<(std::ostream &os, const Integer &arg) {
	if (!arg.sign)
		os << "-";
	for (size_t i = 1; i <= arg.size(); ++i)
		os << arg[-i];
	return os;
}

std::istream &operator>>(std::istream &is, Integer &arg) {
	std::string temp;
	is >> temp;
	arg = temp;
	return is;
}
