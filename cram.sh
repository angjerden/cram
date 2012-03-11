#!/bin/sh
dir=$(pwd $0)
echo "dir er: $dir"
echo `ls $dir/out/production/cram_groovy/cramj`
java -classpath $GROOVY_HOME/embeddable/groovy-all-1.8.4.jar:$dir/out/production/cram_groovy/ $dir/out/production/cram_groovy/cramj $dir

