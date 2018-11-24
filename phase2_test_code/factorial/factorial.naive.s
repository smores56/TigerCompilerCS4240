.data
r_st_1_0: .word 1
.text
fact_st_1_0:
addiu $sp, $sp, -72
sw $ra, 16($sp)
li $t0, 0
sw $t0, 56($sp)
sw $a0, 40($sp)
li $t0, 1
sw $t0, 44($sp)
li $t0, 1
sw $t0, 48($sp)
lw $t0, 40($sp)
lw $t1, 44($sp)
bne $t0, $t1, cond_0_stz_stf_fact_2_0
j cond_1_after_stf_fact_2_0
cond_0_stz_stf_fact_2_0:
li $t0, 0
sw $t0, 48($sp)
cond_1_after_stf_fact_2_0:
lw $t0, 48($sp)
lw $t1, 56($sp)
beq $t0, $t1, if_after2__stf_fact_2_0
li $t0, 1
sw $t0, 36($sp)
lw $v0, 36($sp)
lw $ra, 16($sp)
addiu $sp, $sp, 72
jr $ra
if_after2__stf_fact_2_0:
sw $a0, 60($sp)
li $t0, 1
sw $t0, 64($sp)
lw $t1, 60($sp)
lw $t2, 64($sp)
sub $t0, $t1, $t2
sw $t0, 68($sp)
sw $a0, 72($sp)
lw $a0, 68($sp)
jal fact_st_1_0
lw $a0, 72($sp)
sw $v0, 24($sp)
lw $t0, 24($sp)
sw $t0, r_st_1_0
sw $a0, 28($sp)
lw $t0, r_st_1_0
sw $t0, 32($sp)
lw $t1, 28($sp)
lw $t2, 32($sp)
mulo $t0, $t1, $t2
sw $t0, 52($sp)
lw $v0, 52($sp)
lw $ra, 16($sp)
addiu $sp, $sp, 72
jr $ra
main:
addiu $sp, $sp, -40
sw $ra, 16($sp)
li $t0, 5
sw $t0, 24($sp)
lw $a0, 24($sp)
jal fact_st_1_0
sw $v0, 28($sp)
lw $t0, 28($sp)
sw $t0, r_st_1_0
lw $t0, r_st_1_0
sw $t0, 32($sp)
lw $a0, 32($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addiu $sp, $sp, 40
jr $ra
