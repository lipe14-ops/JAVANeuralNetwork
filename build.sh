#!/bin/bash

set -e

javac -cp ./src ./src/NeuralNet.java -d ./dist/
java  -cp ./dist NeuralNet
