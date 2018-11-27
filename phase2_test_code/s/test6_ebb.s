.data
fp_0: .float 3.14

.text

main:
sub $sp, $sp, 28
sw $ra, 16($sp)
li $s2, 42
l.s $f6, fp_0
sw $s2, 20($sp)
lw $a0, 20($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 28
jr $ra
