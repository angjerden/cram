import Quiz
/**
 * Created by IntelliJ IDEA.
 * User: b543840
 * Date: 2/10/12
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */

/*def i = 0
args.each{String arg ->
    println("Argument $i: $arg")
    i++
}*/

def cli = new CliBuilder(usage: 'cram -[cfhir] [filename]')
cli.with {
    h longOpt: 'help', 'Show usage information'
    r longOpt: 'rand', 'Randomize the order of the questions'
    f longOpt: 'flip', 'Flip question and answer (show answer as the question)'
    c longOpt: 'card', 'Flashcard mode'
    i longOpt: 'hint', "Show hint when wrong answer"
}

Boolean rand = false
Boolean flip = false
Boolean flashcard = false
Boolean hint = false

def options = cli.parse(args)

def showUsage(cli){
    cli.usage()
    println "\nCram v.0.01 by Anders Gjerde"
}

if(!options || options.arguments().size() == 0){
    showUsage(cli)
    return
}

if (options.h || options.arguments().contains("-h") || options.arguments().contains("--help")) {
    showUsage(cli)
}

if(options.r || options.arguments().contains("-r") || options.arguments().contains("--rand")){
    rand = true
    println "Randomizing on"
} 

if(options.f || options.arguments().contains("-f") || options.arguments().contains("--flip")){
    flip = true
    println "Flipped cards on"
} 

if(options.c || options.arguments().contains("-c") || options.arguments().contains("--card")){
    flashcard = true
    println "Flashcard mode on"
}

if(options.i || options.arguments().contains("-i") || options.arguments().contains("--hint")){
    hint = true
    println "Hint mode on"
}

def filename = options.arguments().asList()[0]

if(filename){
    File f = new File("txt/${filename}")
    Quiz q = new Quiz(f, flashcard, flip, hint, rand)
    q.startQuiz()
}

System.exit(0);

