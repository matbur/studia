#pragma once

#include <iostream>
#include <cmath>
#include <string>
#include <cstring>
#include <cstdlib>
#include <sstream>
#include <vector>


class Natural {
public:
	Natural(int = 0);

	Natural(const char);

	template<class T>
	Natural(const T &);

	Natural(const Natural &);

	virtual ~Natural();

	Natural &operator=(const Natural &);
////////////////////////////////////////////////////////////////////////////////

	Natural operator+() const { return *this; }

	template<class T>
	Natural operator+(const T &) const;

	template<class T>
	friend Natural operator+(const T &t_arg, const Natural &arg) { return arg.operator+(t_arg); }

	template<class T>
	Natural &operator+=(const T &);

	Natural operator++();

	Natural operator++(int);
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	Natural operator*(const T &) const;

	template<class T>
	friend Natural operator*(const T &t_arg, const Natural &arg) { return arg.operator*(t_arg); }

	template<class T>
	Natural &operator*=(const T &);
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	bool operator==(const T &) const;

	template<class T>
	friend bool operator==(const T &t_arg, const Natural &arg) { return arg.operator==(t_arg); }
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	bool operator>(const T &) const;

	template<class T>
	friend bool operator>(const T &t_arg, const Natural &arg) { return arg.operator<(t_arg); }
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	bool operator>=(const T &t_arg) const { return (*this).operator==(t_arg) || (*this).operator>(t_arg); }

	template<class T>
	friend bool operator>=(const T &t_arg, const Natural &arg) { return arg.operator<=(t_arg); }
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	bool operator<(const T &t_arg) const { return !(*this).operator>=(t_arg); }

	template<class T>
	friend bool operator<(const T &t_arg, const Natural &arg) { return arg.operator>(t_arg); }
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	bool operator<=(const T &t_arg) const { return !(*this).operator>(t_arg); }

	template<class T>
	friend bool operator<=(const T &t_arg, const Natural &arg) { return arg.operator>=(t_arg); }
////////////////////////////////////////////////////////////////////////////////

	template<class T>
	bool operator!=(const T &t_arg) const { return !(*this).operator==(t_arg); }

	template<class T>
	friend bool operator!=(const T &t_arg, const Natural &arg) { return arg.operator!=(t_arg); }
////////////////////////////////////////////////////////////////////////////////

	virtual operator bool() const { return !(*this == 0); }

	virtual bool operator!() const { return !bool(*this); }
////////////////////////////////////////////////////////////////////////////////

	virtual operator std::string() const;

	virtual operator const char *() const;
////////////////////////////////////////////////////////////////////////////////

	friend std::ostream &operator<<(std::ostream &, const Natural &);

	friend std::istream &operator>>(std::istream &, Natural &);
////////////////////////////////////////////////////////////////////////////////

	size_t size() const { return vec->size(); }

protected:
	std::vector<int> *vec;

	void push_back(int arg) { vec->push_back(arg); }

	int &operator[](int arg) const;
};


template<class T>
Natural::Natural(const T &t_arg) : vec(new std::vector<int>) {
	std::string s = t_arg;
	for (size_t i = s.size(); i; --i)
		if (isdigit(s[i - 1]))
			push_back(s[i - 1] - '0');
}

template<class T>
Natural Natural::operator+(const T &t_arg) const {
	Natural res, arg = t_arg;

	do res.push_back(0);
	while (res.size() <= std::max(size(), arg.size()));

	for (size_t i = 0; i < res.size(); ++i) {
		if (i < size())
			res[i] += (*this)[i];
		if (i < arg.size())
			res[i] += arg[i];
		if (res[i] > 9) {
			res[i] -= 10;
			res[i + 1]++;
		}
	}

	if (!res[-1] && res.size() > 1)
		res.vec->pop_back();

	return res;
}

template<class T>
Natural &Natural::operator+=(const T &t_arg) {
	*this = *this + t_arg;
	return *this;
}

template<class T>
Natural Natural::operator*(const T &t_arg) const {
	Natural res, arg = t_arg;

	do res.push_back(0);
	while (res.size() < size() + arg.size());

	for (size_t i = 0; i < size(); ++i)
		for (size_t j = 0; j < arg.size(); ++j) {
			res[i + j] += (*this)[i] * arg[j];

			while (res[i + j] > 9) {
				res[i + j] -= 10;
				res[i + j + 1]++;
			}
		}

	while (!res[-1] && res.size() > 1)
		res.vec->pop_back();

	return res;
}

template<class T>
Natural &Natural::operator*=(const T &t_arg) {
	*this = *this * t_arg;
	return *this;
}

template<class T>
bool Natural::operator==(const T &t_arg) const {
	Natural arg = t_arg;
	if (size() != arg.size())
		return false;

	for (size_t i = 0; i < size(); ++i)
		if ((*this)[i] != arg[i])
			return false;

	return true;
}

template<class T>
bool Natural::operator>(const T &t_arg) const {
	Natural arg = t_arg;
	if (size() > arg.size())
		return true;

	if (size() < arg.size() || *this == arg)
		return false;

	for (size_t i = 1; i <= size(); ++i)
		if ((*this)[-i] == arg[-i]) continue;
		else return (*this)[-i] > arg[-i];

	return false;
}

