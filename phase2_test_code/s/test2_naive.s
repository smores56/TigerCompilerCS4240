
.text

print:
sub $sp, $sp, 20
sw $ra, 16($sp)
sw $a0, 20($sp)
lw $a0, 20($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 20
jr $ra

main:
sub $sp, $sp, 16
sw $ra, 16($sp)
li $a0, 5
jal print
lw $ra, 16($sp)
addi $sp, $sp, 16
jr $ra
