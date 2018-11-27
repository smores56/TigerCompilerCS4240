
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
add $t4, $t2, $s3
move $t2, $t4
li $s3, 2
add $t4, $s0, $s3
move $s0, $t4
li $s3, 3
add $t4, $s1, $s3
move $s1, $t4
li $s3, 4
add $s1, $s2, $s3
move $s2, $s1
li $s2, 0
li $s3, 0
beq $t2, $s3, if_label0
li $t2, 1
if_label0:
li $s4, 0
beq $t2, $s4, if_label1
li $t2, 0
li $s2, 1
li $s1, 2
j if_label2
if_label1:
li $t4, 3
li $s0, 4
if_label2:
sw $t2, 20($sp)
lw $a0, 20($sp)
li $v0, 1
syscall
sw $s2, 24($sp)
lw $a0, 24($sp)
li $v0, 1
syscall
sw $s1, 28($sp)
lw $a0, 28($sp)
li $v0, 1
syscall
sw $t4, 32($sp)
lw $a0, 32($sp)
li $v0, 1
syscall
sw $s0, 36($sp)
lw $a0, 36($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 76
jr $ra
