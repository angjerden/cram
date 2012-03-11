
/**
 * Created by IntelliJ IDEA.
 * User: b543840
 * Date: 1/28/12
 * Time: 7:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Card {

    String question
    List<String> answers  //one or more possible answers
    Integer id
    private Boolean answeredCorrectly //used for statistical purposes, can only get set once

    public Card(String question, List<String> answers){
        this.question = question
        this.answers = answers
    }
    
    public Card(String question, List<String> answers, Integer id){
        this(question, answers)
        this.id = id
    }

    public String toString(){
        String idStr = ""
        if(id != null) idStr = " " + id
        String returnString = "Question$idStr: ${question}, Answer"
        if(answers.size() > 1) returnString += "s"
        returnString += ": ${answers}"

        return returnString
    }
    
    public String printCorrectAnswersWithLabel(){
        String returnString = "Correct answer"
        if(answers.size() > 1) returnString += "s"
        returnString += ": ${answers}"
        return returnString
    }
    
    public void flip(){
        def tempquestion = question
        def index = ((int)(Math.random() * answers.size())) //select a random answer to use as the question
        question = answers.asList()[index]
        answers = new ArrayList<String>()
        answers.add(tempquestion)
    }

    public Boolean getAnsweredCorrectly(){
        return this.answeredCorrectly
    }

    public void setAnsweredCorrectly(Boolean answeredCorrectly){
        if(this.answeredCorrectly == null){
            this.answeredCorrectly = answeredCorrectly
        }
    }
}