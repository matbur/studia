#include "section.h"

string Section::repr()
{
    string result = "[";
    result += get_begin();
    result += ", ";
    result += get_end();
    result += "]";
    return result;
}
