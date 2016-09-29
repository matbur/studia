#include "box.h"

int Box::counter_boxes = 0;

int asc(const void* a, const void* b)
{
	if (*(double*)a <  *(double*)b) return -1;
	if (*(double*)a == *(double*)b) return 0;
	if (*(double*)a >  *(double*)b) return 1;
}

int dsc(const void* a, const void* b)
{
    return -asc(a, b);
}

double minn(double a, double b)
{
    return (a ? a < b : b);
}

int Box::get_len()
{
    return len;
}

void Box::create_box(int n)
{
    len = n;
    tab = new double[n];
    counter_boxes++;
}

void Box::delete_box()
{
    delete[] tab;
    tab = NULL;
    counter_boxes--;
}

void Box::nullify_box()
{
    for (int i = 0; i<len; i++)
        *(tab + i) = 0;
}

void Box::write_box()
{
    for (int i = 0; i<len; i++)
    {
        cout << "Podaj wartosc na pozycji " << i << ": ";
        cin >> *(tab + i);
    }
}

void Box::read_box()
{
    for (int i = 0; i<len; i++)
        cout << "tab " << i << ": " << *(tab + i) << ", ";
    cout << endl;
}

void Box::rand_box(int n)
{
    for (int i = 0; i<len; i++)
        *(tab + i) = rand() % n;
}

void Box::sort_asc()
{
    qsort((void*)tab, len, sizeof(double), asc);
}

void Box::sort_desc()
{
    qsort((void*)tab, len, sizeof(double), dsc);
}

Box Box::operator+ (Box b)
{
    Box c;
    int clen = min(this->len, b.len);
    c.create_box(clen);

    for (int k=0; k<clen; k++)
        *(c.tab + k) = *(this->tab + k) + *(b.tab + k);

    //len--;
    return c;
}

Box Box::operator- (Box b)
{
    Box c;
    int clen = min(this->len, b.len);
    c.create_box(clen);

    for (int k=0; k<clen; k++)
        *(c.tab + k) = *(this->tab + k) - *(b.tab + k);

    //len--;
    return c;
}

Box Box::operator* (Box b)
{
    Box c;
    int clen = min(this->len, b.len);
    c.create_box(clen);

    for (int k=0; k<clen; k++)
        *(c.tab + k) = *(this->tab + k) * *(b.tab + k);

    //len--;
    return c;
}

Box Box::operator/ (Box b)
{
    Box c;
    int clen = min(this->len, b.len);
    c.create_box(clen);

    for (int k=0; k<clen; k++)
        *(c.tab + k) = *(this->tab + k) / *(b.tab + k);

    //len--;
    return c;
}

int Box::get_counter_boxes()
{
    return counter_boxes;
}
