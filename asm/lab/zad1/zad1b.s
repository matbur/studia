    .align 32
    .data
tmpl: .asciz "%d"
buff: .space 1000

    .text
    .global main
main:
    pushl %ebp
    movl %esp, %ebp

    push $buff
    push $tmpl
    call scanf
    sub $8, %esp

    push buff
    push $tmpl
    call printf
    sub $8, %esp

    mov $0, %eax
    leave
    ret
