package u6pp;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;

    //Creates a class with the String name and ArrayList cards. 
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Card>();
    }

    //Get methods. 
    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    public void callUno() {
        if (hand.size() == 1) {
            System.out.println(name + " says: \"Uno!\"");
        }
    }

    public void drawCards(CardStack stack, int numCards) {
        for (int i = 0; i < numCards; i++) {
            Card card = stack.pop();
            if (card != null) {
                hand.add(card);
            } else {
                System.out.println("No more cards in the stack!");
                break;
            }
        }
    }
}
