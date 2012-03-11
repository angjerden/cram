
/**
 * Created by IntelliJ IDEA.
 * User: b543840
 * Date: 1/28/12
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
class FileParser {

    private static final questionAnswerDelimiter = ";"
    private static final answersDelimiter = /\|/
    
    public static List <Card> parseFile(File file){
        if(!file.exists()) return null

        List <Card> cards = new ArrayList<Card>();
        def counter = 1
        file.eachLine{ String line ->
            def el = line.split(questionAnswerDelimiter)
            Card c = new Card(el[0], parseAnswers(el[1]), counter)
            cards.add(c)
            counter++
        }
        return cards
    }
    
    public static List<String> parseAnswers(String answerString){
        return answerString.split(answersDelimiter).toList()
    }
}
