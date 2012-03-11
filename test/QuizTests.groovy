
/**
 * Created by IntelliJ IDEA.
 * User: b543840
 * Date: 2/17/12
 * Time: 9:40 PM
 * To change this template use File | Settings | File Templates.
 */
class QuizTests extends GroovyTestCase{

    Quiz quiz
    File file
    public void setUp(){
        file = new File("quiz_testing.txt")
        file.write("Question;Luxembourg|Luxury|Mucus|Strasbourg\n")
        file.append("Q2;A2\n")
        file.append("Q3;A3\n")
        file.append("Q4;A4\n")
        file.append("Q5;A5\n")
        file.append("Q6;A6\n")
        file.append("Q7;A7\n")
        file.append("Q8;A8\n")
        file.append("Q9;A9\n")
        file.append("Q10;A10\n")
        quiz = new Quiz(file, false, false, false, false)
    }

    public void tearDown(){

    }
    
    public void test_instantiate_quiz(){
        assertNotNull quiz
        assertNotNull quiz.cardList
        assertFalse quiz.flashcard
        assertFalse quiz.hint
        assertEquals "Question", quiz.cardList.cards[0].question
        assertEquals 4, quiz.cardList.cards[0].answers.size()
        assertEquals "Luxury", quiz.cardList.cards[0].answers[1]
        assertEquals "Strasbourg", quiz.cardList.cards[0].answers[3]
    }

    public void test_get_hint(){
        Card card = quiz.cardList.next()
        String hint = quiz.getHint(card, "Walshbourg")
        assertEquals "Lu***bourg", hint

        hint = quiz.getHint(card, "Lux")
        assertEquals "Luxem*****", hint
        
        hint = quiz.getHint(card, "Muxus")
        assertEquals "Mucus", hint

    }
    
}
