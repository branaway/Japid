#!/bin/sh
echo ~
echo \~ this command is for use in none-Play! environment.
echo \~ use \"play japid:$1\" for Play applications.
echo ~
dir=$(dirname $(readlink /proc/$$/fd/255))
java -classpath "${dir}/../lib/*" cn.bran.japid.compiler.JapidRender $*