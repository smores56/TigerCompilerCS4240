#!/bin/bash

# Runs antlr on grammar in ./grammar/Tiger.g4 and places is into ./parser/
cd grammar
java -Xmx500M -cp "/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool -o ../parser/ ./Tiger.g4 -visitor -no-listener

# Compiles all java files in ./parser/
cd ..
javac ./parser/*.java
