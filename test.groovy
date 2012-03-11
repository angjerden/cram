File f = new File("dat/testfile.dat")
def lines = []
f.eachLine{ String line ->
    lines << line        
}
def end = lines.size()
def start = end - 5
for(int i = start; i < end; i++){
    println lines[i]
}