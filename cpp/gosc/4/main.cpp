#include <iostream>

#include "Fraction.h"
#include "Circle.h"

using namespace std;

int main()
{
    cout << "Przyklad 1.) Circle red;" << endl;
    Circle red;
    cout << endl;
    cout << "Przyklad 2.) Circle orange(1,2,3);" << endl;
    Circle orange(1,2,3);
    cout << endl;
    cout << "Przyklad 3.) Circle();" << endl;
    Circle();
    cout << endl;
    cout << "Przyklad 4.) Circle green = orange;" << endl;
    Circle green = orange;
    cout << endl;
    cout << "Przyklad 5.) red = Circle(1,2,4);" << endl;
    red = Circle(1,2,4);
    cout << endl;
    cout << "Przyklad 6.) for(int i=0; i<3; i++) Circle();" << endl;
    for(int i=0; i<3; i++) Circle();
    cout << endl;

}
