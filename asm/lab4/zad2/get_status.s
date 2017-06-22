    .bss
cw: .space 4
sw: .space 4
scw: .space 4
ssw: .space 4

    .text

    .global get_control
get_control:
    pushl %ebp
    movl %esp, %ebp

    fstcw cw
    mov cw, %eax

    leave
    ret


    .global get_status
get_status:
    pushl %ebp
    movl %esp, %ebp

    xor %eax, %eax
    fstsw %ax

    leave
    ret


    .data
num: .space 100

    .text

    .global exception_div0
exception_div0:
    pushl %ebp
    movl %esp, %ebp

    fnclex

    fldz
    fld1

    fdivp
    
    fstp num

    leave
    ret


    .data
num2: .float 3.0

    .text

    .global exception_over
exception_over:
    pushl %ebp
    movl %esp, %ebp

    fnclex

    fld num2
    fld1

    fdivp
    
    fstp num2

    leave
    ret
