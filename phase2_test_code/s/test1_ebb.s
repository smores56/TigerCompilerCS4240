.data
Y: .word 10:100
X: .word 10:100

.text

main:
sub $sp, $sp, 40
sw $ra, 16($sp)
li $s2, 0
li $s1, 0
li $s1, 0
loop_label0:
li $s3, 99
bgt $s1, $s3, loop_label1
mulo $t0, $s1, 4
la $t1, X
add $t1, $t1, $t0
lw $s0, ($t1)
mulo $t0, $s1, 4
la $t1, Y
add $t1, $t1, $t0
lw $t2, ($t1)
mulo $t3, $s0, $t2
add $t2, $s2, $t3
move $s2, $t2
li $s3, 1
add $s1, $s1, $s3
j loop_label0
loop_label1:
sw $s2, 36($sp)
lw $a0, 36($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 40
jr $ra
