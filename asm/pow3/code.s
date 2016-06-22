    .global pow3
    .type   pow3, @function         # deklaracja funkcji
pow3:
    push    %ebp                    # prolog
    movl    %esp, %ebp

    movl    8(%ebp), %ebx           # ebx := 1. arg
    movl    $0, %ecx                # licznik

p:
    incl    %ecx
    movl    %ecx, %eax
    leal    -1(%eax), %edx          # edx := eax - 1
    mull    %edx                    # eax := eax * (eax - 1)
    leal    (%eax, %eax, 2), %eax   # eax := 3 * eax
    incl    %eax
    
    subl    %eax, %ebx              # odejmowanie kolejnych składników
    cmpl    $0, %ebx                # sprawdzanie czy koniec
    jge	    p
    
    leal    -1(%ecx), %eax          # eax := ecx - 1
    
    movl    %ebp, %esp              # epilog
    popl    %ebp
    ret
