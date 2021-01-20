#!/bin/bash

g++ -c $1 -o tmp__0___1.o -fPIC
g++ -shared tmp__0___1.o -o build/$2.so 
rm tmp__0___1.o
