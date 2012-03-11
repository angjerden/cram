
/**
 * Created by IntelliJ IDEA.
 * User: b543840
 * Date: 1/28/12
 * Time: 8:23 PM
 * To change this template use File | Settings | File Templates.
 */
class FileParserTests extends GroovyTestCase{
    
    File file
    
    public void setUp(){
        file = new File("example.txt")
        file.write("A question;An answer|An alternative answer\n")
        file.append("Yes?;Yes|No|Maybe\n")
        file.append("To be or not to be, that is the (...);Question\n")
    } 
    
    public void tearDown(){
        
    }
    
    public void test_parse_file_correctly(){
        file.eachLine{ String line ->
            println line
        }
        List cards = FileParser.parseFile(file)
        println cards
        assertNotNull cards
        assertEquals 3, cards.size()
        assertEquals "A question", cards[0].question
        assertEquals "An answer", cards[0].answers[0]
        assertEquals "An alternative answer", cards[0].answers[1]
    }

    public void test_with_serbian_characters_latin(){
        file = new File("example_serbian_latin.txt")
        file.write("Some serbian characters;čČšŠđĐćĆžŽ\n")
        List cards = FileParser.parseFile(file)
        println cards
        assertNotNull cards
        assertEquals "čČšŠđĐćĆžŽ", cards[0].answers[0]
    }

    public void test_with_serbian_characters_cyrillic(){
        file = new File("example_serbian_cyrillic.txt")
        file.write("Some serbian characters;љњертзуиопшђасдфгхјклчћжжџцвбнм\n")
        List cards = FileParser.parseFile(file)
        println cards
        assertNotNull cards
        assertEquals "љњертзуиопшђасдфгхјклчћжжџцвбнм", cards[0].answers[0]
    }

    public void test_with_greek_characters(){
        file = new File("example_greek.txt")
        file.write("Some greek characters;ςερτυθιοπασδφγηξκλζχψωβνμ\n")
        List cards = FileParser.parseFile(file)
        println cards
        assertNotNull cards
        assertEquals "ςερτυθιοπασδφγηξκλζχψωβνμ", cards[0].answers[0]
    }
}
