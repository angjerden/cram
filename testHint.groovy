/*String answer = "telemark"
String userInput = "telfnakreee"*/
def answers = ["jostedalsbreen", "rennefart", "jus"]
//String userInput = "jusnutalbtreit"
String userInput = "rennedalsbreen"

String userInputTmp = userInput
def hints = []
def graceCharacters = []
def matches = []
def highestIndex = 0
answers.eachWithIndex{ String answer, Integer i->
    println "Checking string: $answer"
    graceCharacters.add(((int)answer.size()/4))
    matches[i] = 0
    hints[i] = ""
    if(userInputTmp.size() < answer.size()){
        for(int k in userInputTmp.size()..answer.size())
        userInputTmp += "*"
    }
    println "userInputTmp: $userInputTmp"
    answer.toCharArray().eachWithIndex {Character c, Integer j ->
        print "\tChecking char: $c"
        if(c == userInputTmp.charAt(j)){
            print "\twhich matches ${userInputTmp.charAt(j)},"
            matches[i] = matches[i] + 1
            hints[i] += c
            print "\tmatches is now $matches"
        }
        else if(graceCharacters[i] > 0){
            hints[i] += c
            graceCharacters[i]--
        }
        else {
            hints[i] += "*"
        }
        println ""
    }
    if(matches[highestIndex] < matches[i]){
        highestIndex = i
    }

}

println ""
println "graceCharacters $graceCharacters"
println "matches $matches"
println "hint $hints"

//def highestIndex = 0
/*for(int k in 0..matches.size()-1){
    println "Comparing: ${matches[highestIndex]} with ${matches[k]}"
    if(matches[highestIndex] < matches[k]){
        highestIndex = k
    }
}*/

println "highestIndex: $highestIndex"
println graceCharacters[highestIndex]
println matches[highestIndex]
println hints[highestIndex]
/*


println userInput

answer.toCharArray().eachWithIndex { Character c, Integer i ->
    if(c == userInput.charAt(i)){
        hint += c
    }    
    else if(graceCharacters > 0){
        hint += c
        graceCharacters--
    }
    else {
        hint += "*"
    }
}

println hint
*/
