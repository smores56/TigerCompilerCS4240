.data
r_st_1_0: .word 1
.text
fact_st_1_0:
addiu $sp, $sp, -72
sw $ra, 16($sp)
li $t6, 0
move $t3, $a0
li $t4, 1
li $t5, 1
sw $t5, 48($sp)
sw $t6, 56($sp)
bne $t3, $t4, cond_0_stz_stf_fact_2_0
lw $t3, 48($sp)
lw $t4, 56($sp)
sw $t3, 48($sp)
sw $t4, 56($sp)
j cond_1_after_stf_fact_2_0
cond_0_stz_stf_fact_2_0:
lw $t3, 48($sp)
lw $t4, 56($sp)
li $t3, 0
sw $t3, 48($sp)
sw $t4, 56($sp)
cond_1_after_stf_fact_2_0:
lw $t3, 48($sp)
lw $t4, 56($sp)
beq $t3, $t4, if_after2__stf_fact_2_0
li $t3, 1
move $v0, $t3
lw $ra, 16($sp)
addiu $sp, $sp, 72
jr $ra
if_after2__stf_fact_2_0:
move $t7, $a0
li $t8, 1
sub $t9, $t7, $t8
sw $a0, 72($sp)
sw $t9, 68($sp)
move $a0, $t9
jal fact_st_1_0
lw $a0, 72($sp)
lw $t3, 24($sp)
move $t3, $v0
sw $t3, r_st_1_0
move $t4, $a0
lw $t5, r_st_1_0
mulo $t6, $t4, $t5
move $v0, $t6
lw $ra, 16($sp)
addiu $sp, $sp, 72
jr $ra
main:
addiu $sp, $sp, -40
sw $ra, 16($sp)
li $t3, 5
sw $t3, 24($sp)
move $a0, $t3
jal fact_st_1_0
lw $t4, 28($sp)
move $t4, $v0
sw $t4, r_st_1_0
lw $t5, r_st_1_0
sw $t5, 32($sp)
move $a0, $t5
li $v0, 1
syscall
lw $ra, 16($sp)
addiu $sp, $sp, 40
jr $ra
