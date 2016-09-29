#include <iostream>
using namespace std;

char roznica(char, int, char, int);
void szyfr(char a[], int N, char b[], int n, char c[]);

int main()
{
    const int MAX = 120;
    int N=0;
    int n=0;
    char s1[MAX] = {0};
    char s2[MAX] = {0};
    char s[MAX] = {0};
    char w[MAX] = {0};

    cout << "s1 = ";
    cin.getline(s1, MAX);
    cout << "s2 = ";
    cin.getline(s2, MAX);

    for (int i=0; i!=MAX; ++i)
    {
        if (s1[i])
            ++N;
        if (s2[i])
            ++n;
    }

    if (n > N)
    {
        cout << "Zbyt dlugi s2" << endl;
        return 0;
    }

    //szyfrowanie
    szyfr(s1, N, s2, n, s);
    
    //deszyfrowanie
    szyfr(s, N, s2, n, w);


    for (auto i: s)
        cout << i;
    cout << endl;
    for (auto i: w)
        cout << i;
    cout << endl;

    return 0;
}

char roznica(char a, char b)
{
    return a^b;
}

void szyfr(char a[], int N, char b[], int n, char c[])
{
    for (int i=0; i!=N; ++i)
        c[i] = roznica(a[i], b[i%n]);
}
