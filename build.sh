#!/bin/bash
# Set JAVA_HOME to a valid JDK path
export JAVA_HOME="/c/Program Files/Common Files/Oracle/Java/javapath"
export PATH="$JAVA_HOME:$PATH"

# Run Maven
cd "$(dirname "$0")"
mvn "$@"



