.data
r_st_1_0:  .space 4

.text

fact_st_1_0:
sub $sp, $sp, 80
sw $ra, 16($sp)
sw $a0, 68($sp)
li $s2, 0
sw $s2, 48($sp)
lw $s2, 68($sp)
sw $s2, 36($sp)
li $s2, 1
sw $s2, 40($sp)
li $s2, 1
sw $s2, 44($sp)
lw $s2, 36($sp)
lw $s1, 40($sp)
bne $s2, $s1, cond_0_stz_stf_fact_2_0
j cond_1_after_stf_fact_2_0
cond_0_stz_stf_fact_2_0:
li $s2, 0
sw $s2, 44($sp)
cond_1_after_stf_fact_2_0:
lw $s2, 44($sp)
lw $s1, 48($sp)
beq $s2, $s1, if_after2__stf_fact_2_0
li $s2, 1
sw $s2, 32($sp)
lw $s2, 32($sp)
move $v0, $s2
lw $ra, 16($sp)
addi $sp, $sp, 80
jr $ra
if_after2__stf_fact_2_0:
lw $s2, 68($sp)
sw $s2, 56($sp)
li $s2, 1
sw $s2, 60($sp)
lw $s2, 56($sp)
lw $s1, 60($sp)
sub $s0, $s2, $s1
sw $s0, 64($sp)
sw $s0, 72($sp)
sw $s1, 76($sp)
sw $s2, 80($sp)
lw $a0, 64($sp)
jal fact_st_1_0
lw $s0, 72($sp)
lw $s1, 76($sp)
lw $s2, 80($sp)
sw $v0, 20($sp)
lw $s2, 20($sp)
sw $s2, r_st_1_0
lw $s2, 68($sp)
sw $s2, 24($sp)
lw $s2, r_st_1_0
sw $s2, 28($sp)
lw $s2, 24($sp)
lw $s1, 28($sp)
mulo $s0, $s2, $s1
sw $s0, 52($sp)
lw $s0, 52($sp)
move $v0, $s0
lw $ra, 16($sp)
addi $sp, $sp, 80
jr $ra

main:
sub $sp, $sp, 36
sw $ra, 16($sp)
li $s0, 1
sw $s0, r_st_1_0
li $s0, 5
sw $s0, 20($sp)
sw $s0, 36($sp)
lw $a0, 20($sp)
jal fact_st_1_0
lw $s0, 36($sp)
sw $v0, 24($sp)
lw $s0, 24($sp)
sw $s0, r_st_1_0
lw $s0, r_st_1_0
sw $s0, 28($sp)
lw $a0, 28($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 36
jr $ra
