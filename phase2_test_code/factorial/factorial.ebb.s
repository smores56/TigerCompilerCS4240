.data
r_st_1_0: .word 1
.text
fact_st_1_0:
addiu $sp, $sp, -72
sw $ra, 16($sp)
li $t8, 0
move $t6, $a0
li $s3, 1
li $s2, 1
bne $t6, $s3, cond_0_stz_stf_fact_2_0
j cond_1_after_stf_fact_2_0
cond_0_stz_stf_fact_2_0:
li $s2, 0
cond_1_after_stf_fact_2_0:
beq $s2, $t8, if_after2__stf_fact_2_0
li $t0, 1
sw $t0, 36($sp)
lw $v0, 36($sp)
lw $ra, 16($sp)
addiu $sp, $sp, 72
jr $ra
if_after2__stf_fact_2_0:
move $s2, $a0
li $t8, 1
sub $t0, $s2, $t8
sw $t0, 68($sp)
sw $a0, 72($sp)
lw $a0, 68($sp)
jal fact_st_1_0
lw $a0, 72($sp)
sw $v0, 24($sp)
lw $t0, 24($sp)
sw $t0, r_st_1_0
move $s2, $a0
lw $t8, r_st_1_0
mulo $t0, $s2, $t8
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
