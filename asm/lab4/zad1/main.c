#include "stdio.h"

int get_status();
int get_control();
//void set_status(int);
void set_control(int);

int main() {
    int sw, cw, scw;

    sw = get_status();
    cw = get_control();

    printf("sw %d\n", sw);
    printf("cw %d\n", cw);

    scw = 324;

    set_control(scw);
    //set_status(ssw);

    sw = get_status();
    cw = get_control();

    printf("sw %d\n", sw);
    printf("cw %d\n", cw);

    return 0;
}
