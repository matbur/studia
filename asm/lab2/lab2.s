	.align 32

STDIN     = 0
STDOUT    = 1
SUCCESS   = 0
SYSCALL32 = 0x80
SYSEXIT   = 1
SYSREAD   = 3
SYSWRITE  = 4

	.macro write str, str_size, dest
		MOV $SYSWRITE, %eax
		MOV \dest, %ebx
		MOV \str, %ecx
		MOV \str_size, %edx
		INT $SYSCALL32
	.endm

	.macro read buff, buff_size, src
		MOV $SYSREAD, %eax
		MOV \src, %ebx
		MOV \buff, %ecx
		MOV \buff_size, %edx
		INT $SYSCALL32
	.endm

	.data

#new_line: .ascii "\n"
#
#buff: .ascii "9   " "0   " "5   " "0   " "3   " "0   " "7   " "0   "
#len_buff = . - buff

	.bss

buff: .space 16000
len_buff = . - buff

len: .space 16						# bufor na ilosc elementow do wyswietlenia
short_len: .space 16				# bufor na ilosc elementow do porownania

	.text
	.global _start

_start:
	#write $buff, $len_buff, $STDOUT
	read $buff, $len_buff, $STDIN
	MOV %eax, len
	SHR $3, %eax
	DEC %eax
	MOV %eax, short_len
#	MOV short_len, %esi
#	ADD $0x30, %esi
#	MOV %esi, short_len
#	write $new_line, $1, $STDOUT
#	write $short_len, $2, $STDOUT
#	write $new_line, $1, $STDOUT

	MOV $0, %edx

l2:
	PUSH %rdx

	MOV $0, %ecx
	MOV $buff, %edi

l1:
	PUSH %rcx

	MOV $0, %esi
	MOV (%edi, %esi, 4), %eax
	INC %esi
	MOV (%edi, %esi, 4), %ebx
	INC %esi
	MOV (%edi, %esi, 4), %ecx
	INC %esi
	MOV (%edi, %esi, 4), %edx

	BSWAP %eax
	BSWAP %ebx
	BSWAP %ecx
	BSWAP %edx

	CMP %eax, %ecx
	JA continue
	JB change

	CMP %ebx, %edx
	JA continue

change:
	XCHG %ebx, %edx
	XCHG %eax, %ecx

	BSWAP %eax
	BSWAP %ebx
	BSWAP %ecx
	BSWAP %edx

	MOV $0, %esi
	MOV %eax, (%edi, %esi, 4)
	INC %esi
	MOV %ebx, (%edi, %esi, 4)
	INC %esi
	MOV %ecx, (%edi, %esi, 4)
	INC %esi
	MOV %edx, (%edi, %esi, 4)

continue:
	ADD $8, %edi

	POP %rcx
	INC %ecx
	CMP short_len, %ecx
	JL l1

	POP %rdx
	INC %edx
	CMP short_len, %edx
	JL l2

	write $buff, len, $STDOUT

	CALL exit

exit:
	MOV $SYSEXIT, %eax
	MOV $SUCCESS, %ebx
	INT $SYSCALL32
