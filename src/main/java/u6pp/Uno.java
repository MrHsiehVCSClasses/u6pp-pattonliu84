package u6pp;

import java.util.ArrayList;

public class Uno {
    private ArrayList<Player> players;
    private CardStack deck;
    private CardStack discard;
    private int currentPlayerIndex;
    private boolean reversed;

    public Uno(ArrayList<Player> players, CardStack deck, CardStack discard, int startingPlayerIndex, boolean reversed) {
        this.players = players;
        this.deck = deck;
        this.discard = discard;
        this.currentPlayerIndex = startingPlayerIndex;
        this.reversed = reversed;
    }

    public Uno(int numPlayers) {
        players = new ArrayList<Player>(numPlayers);
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(null));
        }
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public Player getNextPlayer() {
        int nextPlayerIndex;
        if (reversed) {
            nextPlayerIndex = currentPlayerIndex - 1;
            if (nextPlayerIndex < 0) {
                nextPlayerIndex = players.size() - 1;
            }
        } else {
            nextPlayerIndex = currentPlayerIndex + 1;
            if (nextPlayerIndex >= players.size()) {
                nextPlayerIndex = 0;
            }
        }
        return players.get(nextPlayerIndex);
    }

    public Card getTopDiscard() {
        return discard.peek();
    }

    public Player getWinner() {
        for (Player player : players) {
            if (player.getHand().size() == 0) {
                return player;
            }
        }
        return null;
    }

    public boolean playCard(Card card, String color) {
        Player currentPlayer = getCurrentPlayer();
        if (!currentPlayer.getHand().contains(card)) {
            return false;
        }

        discard.push(card);
        currentPlayer.getHand().remove(card);

        if (card.getValue() == Card.REVERSE) {
            reversed = !reversed;
        }

        if (card.getValue() == Card.SKIP) {
            getNextPlayer();
        }

        if (card.getValue() == Card.WILD || card.getValue() == Card.WILD_DRAW_4) {
            discard.pop();
            discard.push(new Card(card.getColor(), card.getValue()));
        }

        if (card.getValue() == Card.WILD_DRAW_4) {
            Player nextPlayer = getNextPlayer();
            nextPlayer.drawCards(deck, 4);
        }

        if (currentPlayer.getHand().size() == 1) {
            currentPlayer.callUno();
        }

        if (currentPlayer.getHand().size() == 0) {
            return true;
        }

        if (card.getValue() == Card.WILD || card.getValue() == Card.WILD_DRAW_4) {
            return true;
        }

        if (card.getValue() == Card.REVERSE) {
            return true;
        }

        if (card.getValue() == Card.SKIP) {
            return true;
        }

        for (Card playerCard : currentPlayer.getHand()) {
            if (playerCard.getColor() == card.getColor()) {
                return true;
            }
        }

        for (Card playerCard : currentPlayer.getHand()) {
            if (playerCard.getValue() == card.getValue()) {
                return true;
            }
        }

        return false;
    }
}
