#include <iostream>

using namespace std;

int main()
{
char tc[6];
int ti[6];
double td[6];
float tf[6];

int *wi;
double *wd;
char *wc;
float *wf;



wi= &ti[0];
wd= &td[0];
wc= &tc[0];
wf= &tf[0];

cout << "Adresy typu char, int oraz double;\n";
for (int i=0; i<6; i++, wi++, wd++, wc++)
{

cout<< "i= "<<i<<"|";
cout<< " CHAR = ";
cout<< reinterpret_cast<int>(wc);
cout<< " INT = ";
cout<< reinterpret_cast<int>(wi);
cout<< " DOUBLE = ";
cout<< reinterpret_cast<int>(wd);
cout<< " FLOAT = ";
cout<< reinterpret_cast<int>(wf)<<endl;
cout<<"******************************************************"<<endl;
}


return 0;
}