
/**
 * Created by IntelliJ IDEA.
 * User: b543840
 * Date: 2/11/12
 * Time: 9:45 PM
 * To change this template use File | Settings | File Templates.
 */
class CardListTests extends GroovyTestCase {

    CardList cl
    List<String> q
    List<String> a
    Card c1
    Card c2
    Card c3
    Card c4
    Card c5
    Card c6
    Card c7
    Card c8
    List<String> answers1
    List<String> answers2
    List<String> answers3
    List<String> answers4
    List<String> answers5
    List<String> answers6
    List<String> answers7
    List<String> answers8
    List<Card> cards
    
    File settingsFile
    public void setUp(){
        settingsFile  = new File("settings")
        settingsFile.write("flashcard.bumpbackspace=6")
        q = ["Q1", "Q2", "Q3", "Q4", "Q5", "Q6", "Q7", "Q8"]
        a = ["A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8"]
        def arr = a*.split(/\|/)
        answers1 = arr[0].toList()
        answers2 = arr[1].toList()
        answers3 = arr[2].toList()
        answers4 = arr[3].toList()
        answers5 = arr[4].toList()
        answers6 = arr[5].toList()
        answers7 = arr[6].toList()
        answers8 = arr[7].toList()
        c1 = new Card(q[0], answers1)
        c2 = new Card(q[1], answers2)
        c3 = new Card(q[2], answers3)
        c4 = new Card(q[3], answers4)
        c5 = new Card(q[4], answers5)
        c6 = new Card(q[5], answers6)
        c7 = new Card(q[6], answers7)
        c8 = new Card(q[7], answers8)
        cards = [c1, c2, c3, c4, c5, c6, c7, c8]
    }

    public void tearDown(){

    }
    
    public void test_instantiate_cardlist(){
        cl = new CardList(cards, false, false)
        assertNotNull cl
        assertEquals 8, cl.cards.size()
        assertEquals "A2", cl.cards[1].answers[0]
        assertEquals 0, cl.index
        assertEquals 6, cl.space
    }
    
    public void test_next_and_hasnext(){
        cl = new CardList(cards, false, false)
        assertEquals c1, cl.next()
        assertTrue cl.hasNext()
        assertEquals c2, cl.next()
        assertTrue cl.hasNext()
        assertEquals c3, cl.next()
        assertTrue cl.hasNext()
        assertEquals c4, cl.next()
        assertTrue cl.hasNext()
        assertEquals c5, cl.next()
        assertTrue cl.hasNext()
        assertEquals c6, cl.next()
        assertTrue cl.hasNext()
        assertEquals c7, cl.next()
        assertTrue cl.hasNext()
        assertEquals c8, cl.next()
        assertFalse cl.hasNext()
        assertEquals null, cl.next()
        assertFalse cl.hasNext()
        assertEquals null, cl.next()
    }
    
    public void test_bump_incorrect(){
        cl = new CardList(cards, false, false)
        println "Before bumping:  ${cl.cards}"
        Card card = cl.cards[0]
        println "Bumping ${card}"
        cl.bumpIncorrect(card)
        assertEquals card, cl.cards[6]
        println "After bumping: ${cl.cards}"
        println "Bumping ${card}"
        cl.bumpIncorrect(card)
        assertEquals card, cl.cards[7]
        println "After bumping: ${cl.cards}"
    }
}
