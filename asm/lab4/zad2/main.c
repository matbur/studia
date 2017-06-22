#include "stdio.h"

int get_status();
int get_control();
void exception_div0();
void exception_over();

void bin(int n) {
    if (n == 0) return;
    int r = n%2;
    bin(n/2);
    printf("%d", r);
}

int main() {
    int sw, cw;

    sw = get_status();
    printf("sw ");
    bin(sw);
    printf("\n");

    cw = get_control();
    printf("cw ");
    bin(cw);
    printf("\n");

    exception_div0();
    printf("\nafter div0\n");

    sw = get_status();
    printf("sw ");
    bin(sw);
    printf("\n");

    cw = get_control();
    printf("cw ");
    bin(cw);
    printf("\n");

    exception_over();
    printf("\nafter over\n");

    sw = get_status();
    printf("sw ");
    bin(sw);
    printf("\n");

    cw = get_control();
    printf("cw ");
    bin(cw);
    printf("\n");


    return 0;
}
