#include <stdio.h>

int pow3(int);

int main() {
    int j;
    for (int i=0; i<10; i++) {
        j = i*i*i-1;
        printf("%d -> %d\n", j, pow3(j));
        j++;
        printf("%d -> %d\n", j, pow3(j));
        j++;
        printf("%d -> %d\n", j, pow3(j));
        printf("\n");
    }
    return 0;
}
