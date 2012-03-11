
/**
 * Created by IntelliJ IDEA.
 * User: b543840
 * Date: 1/28/12
 * Time: 11:16 PM
 * To change this template use File | Settings | File Templates.
 */
class Quiz {
    File file //file containing Cards with Questions/Answers
    BufferedReader br
    CardList cardList
    Boolean flashcard
    Boolean hint
    StatsMaker statsMaker

    public Quiz(File file, Boolean flashcard, Boolean flip, Boolean hint, Boolean rand){
        this.flashcard = flashcard
        this.hint = hint
        if(file.exists()){
            br = new BufferedReader(new InputStreamReader(System.in))
            this.file = file
            List<Card> cards = FileParser.parseFile(file)
            cardList = new CardList(cards, flip, rand)
        }
        else{
            println "Could not find: ${file.toString()}"
        }
    }

    public void startQuiz(){
        println "***Cramming ${file.name}***"
        while(cardList.hasNext()){
            Card card = cardList.next()
            Boolean answeredCorrectly = ask(card)
            if(!answeredCorrectly && flashcard){
                cardList.bumpIncorrect(card)
            }
        }
        println "Finished cramming ${file.name}"
        doStatsPresentation()
    }

    public void doStatsPresentation(){
        Integer numCorrect = cardList.cards.findAll{Card card -> card.answeredCorrectly}.size()
        Integer total = cardList.cards.size()
        statsMaker = new StatsMaker(file.name, numCorrect, total)
        String recentStats = statsMaker.retrieveRecentStats()
        if(recentStats != ""){
            println "\nLast ${statsMaker.numRecentStatsToRetrieve} sessions:\n${statsMaker.retrieveRecentStats()}"
        }
        String statsEntry = statsMaker.makeStatisticEntry()
        println "This session:"
        println statsEntry
        statsMaker.writeStatisticsToFile(statsEntry)
    }

    private Boolean ask(Card card){
        Boolean correct = false
        print "(Question ${card.id}/${cardList.cards.size()}) Remaining: ${(cardList.cards.size()+1)-cardList.index} \n${card.question}:"
        String input = br.readLine()
        if(card.answers*.toLowerCase().contains(input.toLowerCase())){
            println "\tCorrect"
            correct = true
            card.answeredCorrectly = true
        }
        else{
            if (hint){
                print "Hint: ${getHint(card, input)}:"
                input = br.readLine()
                if(card.answers*.toLowerCase().contains(input.toLowerCase())){
                    println "\tCorrect"
                    correct = true
                    card.answeredCorrectly = true
                }
            }
            if(!card.answeredCorrectly){
                println "\tNo... ${card.printCorrectAnswersWithLabel()}"
                card.answeredCorrectly = false
            }

        }
            
        return correct
    }

    public String getHint(Card card, String userInput){
        String userInputTmp = userInput
        def hints = []
        def graceCharacters = []
        def matches = []
        def highestIndex = 0
        card.answers.eachWithIndex{ String answer, Integer i->
            graceCharacters.add(((int)answer.size()/4))
            matches[i] = 0
            hints[i] = ""
            if(userInputTmp.size() < answer.size()){
                for(int k in userInputTmp.size()..answer.size())
                    userInputTmp += "*"
            }
            answer.toCharArray().eachWithIndex {Character c, Integer j ->
                if(c == userInputTmp.charAt(j)){
                    matches[i] = matches[i] + 1
                    hints[i] += c
                }
                else if(graceCharacters[i] > 0){
                    hints[i] += c
                    graceCharacters[i]--
                }
                else {
                    hints[i] += "*"
                }
            }
            if(matches[highestIndex] < matches[i]){
                highestIndex = i
            }

        }
        return hints[highestIndex]
    }
}
