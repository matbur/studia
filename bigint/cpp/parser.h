#pragma once

#include <iostream>
#include <vector>
#include <string>
#include <sstream>

#include "integer.h"

class Parser {
public:
	void make_calculation();

	void parse_raw_expression();

	void parse_expression();

	void parse_RPn();


	std::string get_raw_expression() { return raw_expression; }

	std::string get_expression() { return expression; }

	std::string get_RPn();

	Integer get_value() { return value; }

	void set_raw_expression(std::string arg) { raw_expression = arg; }

private:
	std::string raw_expression;
	std::string expression;
	std::vector<std::string> RPn;
	Integer value;
	bool verbose;

	template<class T>
	static std::string t2s(T);

	static bool is_in(char c, const char *str);
};

template<class T>
std::string Parser::t2s(T t_arg) {
	// t2s == type to string
	std::stringstream ss;
	std::string s;
	ss << t_arg;
	ss >> s;
	return s;
}
