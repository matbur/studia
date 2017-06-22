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


    .global set_control
set_control:
    pushl %ebp
    movl %esp, %ebp

    addl $8, %esp
    popl %eax

    movl %eax, scw
    fldcw scw
    subl $8, %esp

    leave
    ret


#    .global set_status
#set_status:
#    pushl %ebp
#    movl %esp, %ebp
#
#    addl $8, %esp
#    popl %eax
#
#    mov %eax, ssw
#
#    subl $8, %esp
#
#    leave
#    ret
