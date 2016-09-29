#pragma once

#include "natural.h"


class Integer : public Natural {
public:
	Integer(int arg = 0) : Natural(std::abs(arg)), sign(arg >= 0) { }

	Integer(const char arg) : Natural(arg), sign(1) { }

	template<class T>
	Integer(const T &t_arg) : Natural(t_arg), sign(t_arg[0] != '-') { }

	Integer(const Natural &arg) : Natural(arg), sign(1) { }
////////////////////////////////////////////////////////////////////////////////

	friend Natural abs(const Integer &arg) { return arg; }
////////////////////////////////////////////////////////////////////////////////

	Integer add_if_same_sign(const Integer &) const;

	Integer add_if_different_sign(const Integer &) const;

	Integer operator+() const { return *this; }

	template<class T>
	Integer operator+(const T &) const;

	template<class T>
	friend Integer operator+(const T &t_arg, const Integer &arg) { return arg.operator+(t_arg); }

	template<class T>
	Integer &operator+=(const T &);

	Integer operator++();

	Integer operator++(int);
////////////////////////////////////////////////////////////////////////////////

	Integer operator-() const;

	template<class T>
	Integer operator-(const T &t_arg) const { return (*this).operator+(-Integer(t_arg)); }

	template<class T>
	friend Integer operator-(const T &t_arg, const Integer &arg) { return (-arg).operator+(t_arg); }

	template<class T>
	Integer &operator-=(const T &);

	Integer operator--();

	Integer operator--(int);
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	Integer operator*(const T &) const;

	template<class T>
	friend Integer operator*(const T &t_arg, const Integer &arg) { return arg.operator*(t_arg); }

	template<class T>
	Integer &operator*=(const T &);
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	Integer operator/(const T &) const;

	template<class T>
	friend Integer operator/(const T &t_arg, const Integer &arg) { return Integer(t_arg).operator/(arg); }

	template<class T>
	Integer &operator/=(const T &);
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	Integer operator%(const T &) const;

	template<class T>
	friend Integer operator%(const T &t_arg, const Integer &arg) { return Integer(t_arg).operator%(arg); }

	template<class T>
	Integer &operator%=(const T &);
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	bool operator==(const T &) const;

	template<class T>
	friend bool operator==(const T &t_arg, const Integer &arg) { return arg.operator==(t_arg); }
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	bool operator>(const T &) const;

	template<class T>
	friend bool operator>(const T &t_arg, const Integer &arg) { return arg.operator<(t_arg); }
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	bool operator>=(const T &t_arg) const { return (*this).operator==(t_arg) || (*this).operator>(t_arg); }

	template<class T>
	friend bool operator>=(const T &t_arg, const Integer &arg) { return arg.operator<=(t_arg); }
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	bool operator<(const T &t_arg) const { return !(*this).operator>=(t_arg); }

	template<class T>
	friend bool operator<(const T &t_arg, const Integer &arg) { return arg.operator>(t_arg); }
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	bool operator<=(const T &t_arg) const { return !(*this).operator>(t_arg); }

	template<class T>
	friend bool operator<=(const T &t_arg, const Integer &arg) { return arg.operator>=(t_arg); }
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	bool operator!=(const T &t_arg) const { return !(*this).operator==(t_arg); }

	template<class T>
	friend bool operator!=(const T &t_arg, const Integer &arg) { return arg.operator!=(t_arg); }
////////////////////////////////////////////////////////////////////////////////

	operator bool() const { return !(*this == 0); }

	bool operator!() const { return !bool(*this); }
////////////////////////////////////////////////////////////////////////////////

	operator std::string() const;

	operator const char *() const;

	friend std::ostream &operator<<(std::ostream &, const Integer &);

	friend std::istream &operator>>(std::istream &, Integer &);

private:
	bool sign; // if *this >= 0 ? 1 : 0
};


template<class T>
Integer Integer::operator+(const T &t_arg) const {
	Integer arg = t_arg;

	if (sign == arg.sign)
		return add_if_same_sign(arg);

	else
		return add_if_different_sign(arg);
}

template<class T>
Integer &Integer::operator+=(const T &t_arg) {
	*this = *this + t_arg;
	return *this;
}

template<class T>
Integer &Integer::operator-=(const T &t_arg) {
	*this = *this - t_arg;
	return *this;
}

template<class T>
Integer Integer::operator*(const T &t_arg) const {
	Integer arg = t_arg;
	Integer res = abs(*this) * abs(arg);
	res.sign = (sign == arg.sign);
	return res;
}

template<class T>
Integer &Integer::operator*=(const T &t_arg) {
	*this = *this * t_arg;
	return *this;
}

template<class T>
Integer Integer::operator/(const T &t_arg) const {
	Integer arg = t_arg;
	if (!arg)
		return 0;

	Natural
			temp = 1,
			prev,
			athis = abs(*this),
			aarg = abs(arg);

	if (athis < aarg)
		return 0;

	for (int i = 2000000000; i > 1; i /= 2) {
		while (athis >= aarg * temp) {
			prev = temp;
			temp *= i;
		}
		temp = prev;
	}

	for (Integer i = std::string(athis.size(), '1'); i.size(); i.vec->pop_back()) {
		while (athis >= aarg * temp) {
			prev = temp;
			temp += i;
		}
		temp = prev;
	}

	if (temp == 0)
		return 0;

	Integer res = temp;
	res.sign = (sign == arg.sign);
	return res;
}

template<class T>
Integer &Integer::operator/=(const T &t_arg) {
	*this = *this / t_arg;
	return *this;
}

template<class T>
Integer Integer::operator%(const T &t_arg) const {
	Integer temp = *this / t_arg;
	if (*this == t_arg * temp)
		return 0;
	return *this - t_arg * temp;
}

template<class T>
Integer &Integer::operator%=(const T &t_arg) {
	*this = *this % t_arg;
	return *this;
}

template<class T>
bool Integer::operator==(const T &t_arg) const {
	Integer arg = t_arg;
	return sign == arg.sign && abs(*this) == abs(arg);
}

template<class T>
bool Integer::operator>(const T &t_arg) const {
	Integer arg = t_arg;

	if (sign) if (!arg.sign)
		return true;
	else
		return abs(*this) > abs(arg);

	else if (arg.sign)
		return false;
	else
		return abs(*this) < abs(arg);
}

