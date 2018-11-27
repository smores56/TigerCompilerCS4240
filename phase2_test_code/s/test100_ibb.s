
.text

main:
sub $sp, $sp, 76
sw $ra, 16($sp)
li $s2, 0
li $s1, 0
li $s0, 0
li $t2, 0
li $t3, 0
j main0
main0:
li $s3, 0
add $t4, $t3, $s3
move $t3, $t4
li $s3, 1
add $t5, $t2, $s3
move $t2, $t5
li $s3, 2
add $t6, $s0, $s3
move $s0, $t6
li $s3, 3
add $t7, $s1, $s3
move $s1, $t7
li $s3, 4
add $t8, $s2, $s3
move $s2, $t8
li $t9, 0
li $s3, 0
beq $t2, $s3, if_label0
li $t9, 1
if_label0:
li $s4, 0
beq $t9, $s4, if_label1
li $t3, 0
li $t2, 1
li $s0, 2
j if_label2
if_label1:
li $s1, 3
li $s2, 4
if_label2:
sw $t3, 20($sp)
lw $a0, 20($sp)
li $v0, 1
syscall
sw $t2, 24($sp)
lw $a0, 24($sp)
li $v0, 1
syscall
sw $s0, 28($sp)
lw $a0, 28($sp)
li $v0, 1
syscall
sw $s1, 32($sp)
lw $a0, 32($sp)
li $v0, 1
syscall
sw $s2, 36($sp)
lw $a0, 36($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 76
jr $ra
