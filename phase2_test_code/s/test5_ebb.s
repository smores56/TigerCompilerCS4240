
.text

main:
sub $sp, $sp, 32
sw $ra, 16($sp)
li $s2, 0
li $s1, 0
li $s0, 0
bne $s1, $s2, if_label0
li $s1, 1
if_label0:
li $s3, 0
beq $s1, $s3, if_label1
li $s3, 2
add $s1, $s2, $s3
move $s2, $s1
j if_label2
if_label1:
li $s2, 2
if_label2:
sw $s2, 24($sp)
lw $a0, 24($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 32
jr $ra
