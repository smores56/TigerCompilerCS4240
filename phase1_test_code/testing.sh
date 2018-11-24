#!/bin/bash

# Runs antlr on grammar in ./grammar/Tiger.g4 and places is into ./parser/
cd grammar
java -Xmx500M -cp "/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool -o ../parser/ ./Tiger.g4 -visitor -no-listener

# Compiles all java files in ./parser/
cd ..
javac ./parser/*.java

# Runs all scripts that end with *.tiger.txt using the Tiger.class file we have
cd project1_test_code
for filename in ./*.tiger.txt
do
    cd ../parser/
    parsedFileName=${filename:1:${#filename}}
    parsedIrName=${parsedFileName/tiger/ir}

    # Puts the output of our IR gen into /project1_test_code/output/*.tiger.txt.out
    java Tiger ../project1_test_code/$filename > ../project1_test_code/output${filename:1:${#filename}}.out

    # Puts the output of the difference between our IR and the provided IR into /project1_test_code/output/*.tiger.txt.diff.out
    diff ../project1_test_code/$parsedIrName ../project1_test_code/output${filename:1:${#filename}}.out > ../project1_test_code/output${filename:1:${#filename}}.diff.out
    cd ../project1_test_code
done