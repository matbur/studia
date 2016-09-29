#pragma once

#include <iostream>
#include <cmath>
#include <string>
#include <cstring>
#include <cstdlib>
//#include <cstddef>
#include <vector>

using namespace std;


class Natural {
public:
    Natural(int = 0);
    Natural(const char);
    Natural(const char*);
    Natural(const string&);
    Natural(const Natural&);
    virtual ~Natural();

    Natural& operator=(const Natural&);

    Natural operator+() const;
    template <class T> Natural operator+(const T&) const;
    template <class T> friend Natural operator+(const T&, const Natural&);
    template <class T> Natural& operator+=(const T&);

    template <class T> Natural operator*(const T&) const;
    template <class T> friend Natural operator*(const T&, const Natural&);
    template <class T> Natural& operator*=(const T&);

    Natural operator++();
    Natural operator++(int);

//
//    template <class T> bool operator==(const T&) const;
//    template <class T> bool operator>(const T&) const;
//    template <class T> bool operator>=(const T&) const;
//    template <class T> bool operator<(const T&) const;
//    template <class T> bool operator<=(const T&) const;
//    template <class T> bool operator!=(const T&) const;
    operator bool() const;
    bool operator!() const;

    virtual operator string() const;
//    virtual operator char*() const;

    friend ostream& operator<<(ostream&, const Natural&);
    friend istream& operator>>(istream&, Natural&);

protected:
    vector<int>* vec;

    size_t size() const;
    void push_back(int);
    int& operator[](int arg) const;
    void to_correct();
};



template <class T> Natural Natural::operator+(const T& arg) const {
    Natural res = arg;

    // dodanie zer na pocz az dlugosc wyniesie max(size, arg.size)+1
    do
        res.push_back(0);
    while (res.size() <= size());

    // dodanie odpowiadajacych cyfr
    for (size_t i = 0; i < size(); ++i)
        res[i] += (*this)[i];

    res.to_correct();
    return res;
}

template <class T> Natural operator+(const T& targ, const Natural& arg) { return arg + targ; }

template <class T> Natural Natural::operator*(const T& targ) const {
    Natural res, arg = targ;

    do
        res.push_back(0);
    while (res.size() < size() + arg.size());

    for (size_t i = 0; i < size(); ++i)
        for (size_t j = 0; j < arg.size(); ++j)
            res[i + j] += (*this)[i] * arg[j];

    res.to_correct();
    return res;
}

template <class T> Natural operator*(const T& targ, const Natural& arg) { return arg * targ; }

template <class T> Natural& Natural::operator+=(const T& arg) {
    *this = *this + arg;
    return *this;
}

template <class T> Natural& Natural::operator*=(const T& arg) {
    *this = *this * arg;
    return *this;
}

