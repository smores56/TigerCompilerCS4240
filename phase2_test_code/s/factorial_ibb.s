
.text

fact_st_1_0:
sub $sp, $sp, 72
sw $ra, 16($sp)
sw $a0, 68($sp)
li $s2, 0
lw $s0, 68($sp)
move $s1, $s0
li $t2, 1
li $t3, 1
bne $s1, $t2, cond_0_stz_stf_fact_2_0
j cond_1_after_stf_fact_2_0
cond_0_stz_stf_fact_2_0:
li $t3, 0
cond_1_after_stf_fact_2_0:
beq $t3, $s2, if_after2__stf_fact_2_0
li $t4, 1
move $v0, $t4
lw $ra, 16($sp)
addi $sp, $sp, 72
jr $ra
if_after2__stf_fact_2_0:
move $t5, $s0
li $t6, 1
sub $t7, $t5, $t6
sw $t7, 64($sp)
sw $s0, 72($sp)
lw $a0, 64($sp)
jal fact_st_1_0
lw $s0, 72($sp)
sw $v0, 20($sp)
lw $t9, 20($sp)
move $t8, $t9
move $s5, $s0
sw $s5, 24($sp)
move $s6, $t8
sw $s6, 28($sp)
lw $s5, 24($sp)
lw $s6, 28($sp)
mulo $s7, $s5, $s6
sw $s7, 52($sp)
lw $s7, 52($sp)
move $v0, $s7
lw $ra, 16($sp)
addi $sp, $sp, 72
jr $ra
sw $s7, 52($sp)

main:
sub $sp, $sp, 44
sw $ra, 16($sp)
li $s5, 1
sw $s5, 32($sp)
li $s6, 5
sw $s6, 20($sp)
lw $s7, 20($sp)
sw $s7, 20($sp)
sw $s5, 36($sp)
sw $s6, 40($sp)
sw $s7, 44($sp)
lw $a0, 20($sp)
jal fact_st_1_0
lw $s5, 36($sp)
lw $s6, 40($sp)
lw $s7, 44($sp)
sw $v0, 24($sp)
lw $s6, 24($sp)
move $s5, $s6
sw $s5, 32($sp)
lw $s5, 32($sp)
move $s7, $s5
sw $s7, 28($sp)
lw $s6, 28($sp)
sw $s6, 28($sp)
lw $a0, 28($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 44
jr $ra
