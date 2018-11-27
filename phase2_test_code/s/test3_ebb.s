.data
fp_0: .float 0.0
fp_1: .float 0.0
fp_2: .float 1.0

.text

print:
sub $sp, $sp, 20
sw $ra, 16($sp)
sw $a0, 20($sp)
lw $s2, 20($sp)
sw $s2, 20($sp)
lw $a0, 20($sp)
li $v0, 1
syscall
lw $ra, 16($sp)
addi $sp, $sp, 20
jr $ra

main:
sub $sp, $sp, 40
sw $ra, 16($sp)
l.s $f6, fp_0
l.s $f6, fp_1
s.s $f6, 36($sp)
li $a0, 5
jal print
l.s $f6, 36($sp)
l.s $f6, fp_2
lw $ra, 16($sp)
addi $sp, $sp, 40
jr $ra
