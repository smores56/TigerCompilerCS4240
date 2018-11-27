
.text

main:
sub $sp, $sp, 32
sw $ra, 16($sp)
li $s2, 0
li $s1, 0
li $s0, 0
bne $s1, $s2, if_label0
li $s0, 1
if_label0:
li $s3, 0
beq $s0, $s3, if_label1
li $s3, 2
add $t2, $s2, $s3
move $s1, $t2
j if_label2
if_label1:
li $s1, 2
if_label2:
sw $s1, 24($sp)
lw $a0, 24($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 32
jr $ra
