
/**
 * Created by IntelliJ IDEA.
 * User: b543840
 * Date: 1/28/12
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */
class CardTests extends GroovyTestCase{

    def q
    def a
    def answers
    
    public void setUp(){
        q = "What is the air speed velocity of an unladen swallow?"
        a = "An African swallow?|A European swallow?|Blue!|No, yellow!"
        answers = FileParser.parseAnswers(a)
    }

    public void tearDown(){

    }

    public void test_instantiate_card(){
        Card c = new Card(q, answers)
        assertNotNull c
        assertEquals q, c.question
        assertEquals answers, c.answers
        assertEquals answers.size(), c.answers.size()
    }

    public void test_instantiate_card_with_id(){
        Card c = new Card(q, answers, 1)
        assertNotNull c
        assertNotNull c.id
        assertEquals 1, c.id
    }
    
    public void test_to_string(){
        Card c = new Card(q, answers)
        assertEquals "Question: ${q}, Answers: ${answers}", c.toString()
        Card c2 = new Card(q, answers, 1)
        assertEquals "Question 1: ${q}, Answers: ${answers}", c2.toString()
    }
    
    public void test_print_correct_answer_with_label(){
        Card c = new Card(q, answers)
        assertEquals "Correct answers: ${answers}", c.printCorrectAnswersWithLabel()
        
        String anAnswer = "Answer"
        def answerArray = FileParser.parseAnswers(anAnswer)
        Card c2 = new Card("Question", answerArray)
        assertEquals "Correct answer: $answerArray", c2.printCorrectAnswersWithLabel()
    }
    
    public void test_flip(){
        Card c = new Card(q, answers)
        c.flip()
        assertEquals q, c.answers.asList()[0]
        Boolean isInAnswers = false
        answers.each{String answer ->
            if(answer == c.question) isInAnswers = true
        }
        assertTrue isInAnswers
    }
    
    public void test_set_answered_correctly(){
        Card c = new Card(q, answers)
        assertNull c.answeredCorrectly
        c.answeredCorrectly = true
        assertTrue c.answeredCorrectly
        c.setAnsweredCorrectly(false)
        assertTrue c.answeredCorrectly
    }
}
