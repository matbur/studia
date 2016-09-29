#pragma once

#include <iostream>
#include <cstdlib>
#include <stdlib.h>

using namespace std;


class Box {
	double* tab;
    int len;        //length of box
    static int counter_boxes;
public:
    int get_len();

	void create_box(int); //create - tworzy
	void delete_box();  //delete - usuwa
	void nullify_box(); //nullify - zeruje
	void write_box(); //write - wpisuje
	void read_box(); //read - wyczytuje i wypisuje
	void rand_box(int); //losuje
	void sort_asc();
	void sort_desc();

	Box operator+ (Box); //Operator w wyniku daje tablice rozmiaru najmniejszego elementu
	Box operator- (Box);
	Box operator* (Box);
	Box operator/ (Box);

	static int get_counter_boxes();
};
