#!/bin/bash
BASEDIR=$(dirname $(realpath "$0"))
java -javaagent:$BASEDIR/jolokia-agent-jvm-2.0.3-javaagent.jar -jar $BASEDIR/target/userCredentialsAPI-0.0.1-SNAPSHOT.jar
