package u6pp;

import java.util.ArrayList;

import java.util.Random;

public class CardStack {
    private ArrayList<Card> stack;

    public CardStack() {
        stack = new ArrayList<Card>();
    }

    public Card peek() {
        if (isEmpty() == true) {
            return null;
        } else {
            return stack.get(stack.size() - 1);
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int getSize() {
        return stack.size();
    }

    public void push(Card card) {
        stack.add(card);
    }

    public Card pop() {
        if (isEmpty() == true) {
            return null;
        } else {
            return stack.remove(stack.size() - 1);
        }
    }

    public void clear() {
        stack.clear();
    }

    public void addAll(CardStack other) {
        Card[] cards = new Card[other.getSize()];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = other.pop();
        }
        for (int i = cards.length - 1; i >= 0; i--) {
            push(cards[i]);
        }
    }

    //Uses the Gates algorithm becuase it does, basically swaps positions
    public void shuffle() {
        int n = stack.size();
        Random rand = new Random();
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = stack.get(j);
            stack.set(j, stack.get(i));
            stack.set(i, temp);
        }
    }
}
