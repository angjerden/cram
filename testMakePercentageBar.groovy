def percent = 50
def block = "▊"
def maximumBlocks = 25
def blocksToWrite = (int)(maximumBlocks * (percent/100))
def blankSpace = maximumBlocks - blocksToWrite
def percentBarString = ""
println blocksToWrite
println blankSpace
percentBarString += "["
for(int i = 0; i < blocksToWrite; i++){
    percentBarString += block
}
for(int i = 0; i < blankSpace; i++){
    percentBarString += " "
}

percentBarString += "]"

println percentBarString