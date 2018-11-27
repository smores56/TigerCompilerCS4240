
.text

main:
sub $sp, $sp, 32
sw $ra, 16($sp)
li $s2, 0
sw $s2, 20($sp)
li $s2, 0
sw $s2, 24($sp)
li $s2, 0
sw $s2, 28($sp)
lw $s2, 24($sp)
lw $s1, 20($sp)
bne $s2, $s1, if_label0
li $s2, 1
sw $s2, 28($sp)
if_label0:
lw $s2, 28($sp)
li $s3, 0
beq $s2, $s3, if_label1
li $s3, 2
lw $s2, 20($sp)
add $s1, $s2, $s3
sw $s1, 32($sp)
lw $s2, 32($sp)
sw $s2, 24($sp)
j if_label2
if_label1:
li $s2, 2
sw $s2, 24($sp)
if_label2:
lw $a0, 24($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 32
jr $ra
