.align 32

STDIN 		= 0
STDOUT 		= 1
SUCCESS		= 0
SYSCALL32	= 0x80
SYSEXIT 	= 1
SYSREAD 	= 3
SYSWRITE 	= 4

.data
tmpl: .ascii "000 "			# szablon dla kazdego bajtu
tmpllen = . - tmpl

.bss
stin: .space 1				# bufor dla przychodzacego bajtu
inlen = . - stin

stout: .space 4				# bufor dla wychodzacego bajtu
outlen = . - stout

.text
.global _start
_start:
	MOV $stin, %ecx			# czytanie bajtu z stdin
	MOV $inlen, %edx
	CALL read

	CMP $0, %eax			# jesli nic nie ma to koniec
	JE exit

	MOV tmpl, %eax			# kopiowanie szablonu do bufora
	MOV %eax, stout

	MOV $0, %eax
	MOVB stin, %al
	MOV $10, %bl			# na system dziesietny
	MOV $2, %esi

	DIV %bl					# reszta trafia do %ah
	ADDB %ah, stout(%esi)	# wiec dodawana jest do bufora
	MOV $0, %ah

	DEC %esi
	DIV %bl
	ADDB %ah, stout(%esi)
	MOV $0, %ah

	DEC %esi
	DIV %bl
	ADDB %ah, stout(%esi)
	MOV $0, %ah

	MOV $stout, %ecx		# pisanie 4 bajtow na stdout
	MOV $outlen, %edx
	CALL print

	JMP _start

# Used registers:
# ecx - address 1st byte
# edx - length of string
print:
	MOV $SYSWRITE, %eax
	MOV $STDOUT, %ebx
	INT $SYSCALL32
	RET

read:
	MOV $SYSREAD, %eax
	MOV $STDIN, %ebx
	INT $SYSCALL32
	RET

exit:
	MOV $SYSEXIT, %eax
	MOV $SUCCESS, %ebx
	INT $SYSCALL32
