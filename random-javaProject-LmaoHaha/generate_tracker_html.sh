#!/bin/bash

# Print the input provided as arguments
echo "test"
echo "$@"
# Loop through each argument passed to the script
for arg in "$@"; do
    # Print each argument
    echo "$arg"
done