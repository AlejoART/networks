#!/bin/bash

# This script is intended to be executed on the docker container
# It's purpose is to execute the tests then copy the reporting
# files to a mounted volume for persistence.

CMD="mvn -o -e clean verify"

# Read input arguments that are passed with -D add turn them into environment variables.
while [[ ${1} ]]; do
    if [[ "${1}" =~ ^-D.+=.+ ]]; then
        CMD="$CMD ${1}"
    fi
    shift
done

echo "$CMD"
eval $CMD

TEST_RESULT=$?

cp -rf /app/target/cucumber/data/. /cucumber/

exit ${TEST_RESULT}