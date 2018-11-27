
.text

fact_st_1_0:
sub $sp, $sp, 80
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
li $s1, 0
cond_1_after_stf_fact_2_0:
beq $s1, $s2, if_after2__stf_fact_2_0
li $s1, 1
move $v0, $s1
lw $ra, 16($sp)
addi $sp, $sp, 80
jr $ra
if_after2__stf_fact_2_0:
move $s1, $s0
li $s2, 1
sub $t2, $s1, $s2
sw $t2, 64($sp)
sw $s0, 72($sp)
sw $s1, 76($sp)
sw $t2, 80($sp)
lw $a0, 64($sp)
jal fact_st_1_0
lw $s0, 72($sp)
lw $s1, 76($sp)
lw $t2, 80($sp)
sw $v0, 20($sp)
lw $s1, 20($sp)
move $t2, $s1
move $s1, $s0
move $s0, $t2
mulo $t2, $s1, $s0
move $v0, $t2
lw $ra, 16($sp)
addi $sp, $sp, 80
jr $ra

main:
sub $sp, $sp, 36
sw $ra, 16($sp)
li $t2, 1
li $t2, 5
sw $t2, 20($sp)
sw $t2, 36($sp)
lw $a0, 20($sp)
jal fact_st_1_0
lw $t2, 36($sp)
sw $v0, 24($sp)
lw $s1, 24($sp)
move $t2, $s1
move $s1, $t2
sw $s1, 28($sp)
lw $a0, 28($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 36
jr $ra
