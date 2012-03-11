
/**
 * Created by IntelliJ IDEA.
 * User: b543840
 * Date: 2/11/12
 * Time: 6:08 PM
 * To change this template use File | Settings | File Templates.
 */
class CardList {
    
    Integer space //how far back in the stack a card goes after getting it wrong
    List<Card> cards
    Integer index
    def config = new ConfigSlurper().parse(new File("settings").toURL())

    public CardList(List<Card> cards, Boolean flip, Boolean rand){
        this.cards = cards
        if(flip){
            this.cards.each{Card card ->
                card.flip()
            }
        }
        if(rand) Collections.shuffle(cards)
        this.space = config.flashcard.bumpbackspace
        this.index = 0
    }
    
    public Boolean hasNext(){
        return index < cards.size()
    }
    
    public Card next(){
        if(index < cards.size()){
            return (Card) cards[index++]
        }
        else return null
    }

    public void bumpIncorrect(Card card){
        def cardIndex = cards.indexOf(card)
        if(cardIndex+space < cards.size()){
            cards.remove(card)
            cards.add(cardIndex+space, card)
        }
        else if(cardIndex+space >= cards.size()){
            cards.remove(card)
            cards.add(cards.size(), card)
        }
        index-- //moving a card back nudges all the other cards forward one spot
    }
    
}
