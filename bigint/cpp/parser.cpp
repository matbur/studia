#include "parser.h"

void Parser::make_calculation() {
	verbose = false;

	std::cout << "\n"
			"      _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n"
			"    (                                                                 )\n"
			"    (          INTERACTIVE INTERGER CALTULATOR by @matbur             )\n"
			"    (                                                                 )\n"
			"    (    Feel free to type a really big numbers                       )\n"
			"    (    You can use following operators: + - * / % ( )               )\n"
			"    (    Verbose mode is default 'off', to turn 'on' type: 0 [Enter]  )\n"
			"    (    Example: ( 12 + 2 ) * -3 [Enter]                             )\n"
			"    (    To exit just write: end [Enter]                              )\n"
			"    ( _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ )\n"
			"\n";

	int i = 0;
	while (++i) {
		std::cout << i << ")> ";
		std::getline(std::cin, raw_expression);

		if (raw_expression == "") {
			--i;
			continue;
		}
		else if (isalpha(raw_expression[0])) {
			std::cout << "\nThank you\n\n";
			break;
		}
		else if (raw_expression[0] == '0') {
			verbose = !verbose;
			std::cout << "verbose: " << (verbose ? "on" : "off") << std::endl;
		}

		if (verbose)
			std::cout << "raw: |" << raw_expression << "|" << std::endl;
		parse_raw_expression();

		if (verbose)
			std::cout << "exp: |" << expression << "|" << std::endl;
		parse_expression();

		if (verbose)
			std::cout << "RPn: |" << get_RPn() << "|" << std::endl;
		parse_RPn();

		std::cout << "\t= " << value << std::endl;
	}
}

void Parser::parse_raw_expression() {
	expression.clear();

	for (auto i = raw_expression.begin(); i != raw_expression.end(); ++i) {
		if (*i != ' ')
			expression += *i;
	}
	if (expression[0] == '-')
		expression.insert(0, "0");

	size_t n = 0, len = expression.size();
	for (auto i = expression.begin(); i < expression.end() && n < 2 * len; ++i, ++n) {
		if (is_in(*i, "+-*/%") && *(i + 1) == '-') {
			if (isdigit(*(i + 2))) {
				if (expression.find_first_not_of("0123456789", n + 2) == std::string::npos)
					expression.push_back(')');
				else
					expression.insert(expression.find_first_not_of("0123456789", n + 2), ")");
			}
			else if (*(i + 2) == '(')
				expression.insert(expression.find(')', n + 2), ")");
			expression.insert(n + 1, "(0");
		}
	}
}

void Parser::parse_expression() {
	RPn.clear();
	std::vector<char> op_stack;

	// Shunting-yard algorithm
	for (auto i = expression.begin(); i != expression.end(); ++i) {
		if (isdigit(*i)) {
			if (RPn.size() && isdigit(*(i - 1)))
				(RPn.end() - 1)->push_back(*i);
			else
				RPn.push_back(t2s(*i));
		}
		else if (is_in(*i, "+-")) {
			if (op_stack.size() and op_stack.back() != '(') {
				RPn.push_back(t2s(op_stack.back()));
				op_stack.pop_back();
			}
			op_stack.push_back(*i);
		}
		else if (is_in(*i, "*/%")) {
			if (op_stack.size() and is_in(op_stack.back(), "*/%")) {
				RPn.push_back(t2s(op_stack.back()));
				op_stack.pop_back();
			}
			op_stack.push_back(*i);
		}
		else if (*i == '(') {
			op_stack.push_back(*i);
		}
		else if (*i == ')') {
			if (op_stack.back() != '(') {
				RPn.push_back(t2s(op_stack.back()));
				op_stack.pop_back();
			}
			if (op_stack.back() == '(') {
				op_stack.pop_back();
			}
		}


	}
	while (op_stack.size()) {
		RPn.push_back(t2s(op_stack.back()));
		op_stack.pop_back();
	}
}

void Parser::parse_RPn() {
	std::vector<Integer> vec;

	for (auto i: RPn) {
		if (isdigit(i.back())) {
			vec.push_back(i);
		}
		else {
			Integer last = vec.back();
			vec.pop_back();
			Integer *to_change = &vec.back();

			switch (i[0]) {
				case '+':
					*to_change += last;
			        break;
				case '-':
					*to_change -= last;
			        break;
				case '*':
					*to_change *= last;
			        break;
				case '/':
					*to_change /= last;
			        break;
				case '%':
					*to_change %= last;
			        break;
			}
		}
	}
	value = vec[0];
}

std::string Parser::get_RPn() {
	std::string res;
	for (auto i: RPn) {
		res += i;
		res += ' ';
	}
	return res;
}

bool Parser::is_in(char c, const char *str) {
	for (size_t i = 0; i < strlen(str); ++i)
		if (c == str[i])
			return true;
	return false;
}
