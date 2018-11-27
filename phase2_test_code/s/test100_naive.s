
.text

main:
sub $sp, $sp, 60
sw $ra, 16($sp)
li $s2, 0
sw $s2, 36($sp)
li $s2, 0
sw $s2, 32($sp)
li $s2, 0
sw $s2, 28($sp)
li $s2, 0
sw $s2, 24($sp)
li $s2, 0
sw $s2, 20($sp)
j main0
main0:
li $s3, 0
lw $s2, 20($sp)
add $s1, $s2, $s3
sw $s1, 40($sp)
lw $s1, 40($sp)
sw $s1, 20($sp)
li $s3, 1
lw $s1, 24($sp)
add $s2, $s1, $s3
sw $s2, 44($sp)
lw $s2, 44($sp)
sw $s2, 24($sp)
li $s3, 2
lw $s2, 28($sp)
add $s1, $s2, $s3
sw $s1, 48($sp)
lw $s1, 48($sp)
sw $s1, 28($sp)
li $s3, 3
lw $s1, 32($sp)
add $s2, $s1, $s3
sw $s2, 52($sp)
lw $s1, 52($sp)
sw $s1, 32($sp)
li $s3, 4
lw $s1, 36($sp)
add $s2, $s1, $s3
sw $s2, 56($sp)
lw $s1, 56($sp)
sw $s1, 36($sp)
li $s1, 0
sw $s1, 60($sp)
lw $s1, 24($sp)
li $s3, 0
beq $s1, $s3, if_label0
li $s1, 1
sw $s1, 60($sp)
if_label0:
lw $s1, 60($sp)
li $s3, 0
beq $s1, $s3, if_label1
li $s1, 0
sw $s1, 20($sp)
li $s1, 1
sw $s1, 24($sp)
li $s1, 2
sw $s1, 28($sp)
j if_label2
if_label1:
li $s1, 3
sw $s1, 32($sp)
li $s1, 4
sw $s1, 36($sp)
if_label2:
lw $a0, 20($sp)
li $v0, 1
syscall
lw $a0, 24($sp)
li $v0, 1
syscall
lw $a0, 28($sp)
li $v0, 1
syscall
lw $a0, 32($sp)
li $v0, 1
syscall
lw $a0, 36($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 60
jr $ra
