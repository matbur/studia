#include "natural.h"
#include "integer.h"
#include "parser.h"

using namespace std;

typedef Natural NAT;
typedef Integer INT;


int main() {

	Parser *p = new Parser;
	p->make_calculation();
	delete p;

	return 0;
}

