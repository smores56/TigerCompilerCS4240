.data
Y: .word 10:100
X: .word 10:100

.text

main:
sub $sp, $sp, 40
sw $ra, 16($sp)
li $s2, 0
sw $s2, 36($sp)
li $s2, 0
sw $s2, 20($sp)
li $s2, 0
sw $s2, 20($sp)
loop_label0:
lw $s2, 20($sp)
li $s3, 99
bgt $s2, $s3, loop_label1
lw $s1, 20($sp)
mulo $t0, $s1, 4
la $t1, X
add $t1, $t1, $t0
lw $s2, ($t1)
sw $s2, 24($sp)
lw $s2, 20($sp)
mulo $t0, $s2, 4
la $t1, Y
add $t1, $t1, $t0
lw $s1, ($t1)
sw $s1, 28($sp)
lw $s2, 24($sp)
lw $s1, 28($sp)
mulo $s0, $s2, $s1
sw $s0, 32($sp)
lw $s1, 36($sp)
lw $s2, 32($sp)
add $s0, $s1, $s2
sw $s0, 40($sp)
lw $s1, 40($sp)
sw $s1, 36($sp)
li $s3, 1
lw $s1, 20($sp)
add $s1, $s1, $s3
sw $s1, 20($sp)
j loop_label0
loop_label1:
lw $a0, 36($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 40
jr $ra
