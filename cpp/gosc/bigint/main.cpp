#include "natural.h"

//typedef Integer INT;
typedef Natural NAT;

void String2Int(const std::string& str, int& result)
{
    result = std::atoi(str.c_str());
}

int main()
{
    char c[] = "123456789";
    string s = "123457689";

    NAT
        b1(c),
        b2(s),
        b3(b1),
        b4 = 123456789;

	cout << "char*\t" << b1 << endl;
	cout << "string\t" << b2 << endl;
	cout << "int\t" << b4 << endl;

	cout << strcat(c,c) << endl;

    return 0;
}
