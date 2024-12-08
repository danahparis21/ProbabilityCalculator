
package probabilitycalculator;

/**
 *
 * @author 63945
 */


import java.util.HashSet;
import java.util.Set;

public class CardMapper {
    private static final String[] RANKS = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};

   
    public static Set<String> getCardsByRank(String rank) {
        Set<String> cards = new HashSet<>();
        for (String suit : SUITS) {
            cards.add(rank + " of " + suit);
        }
        return cards;
    }

   
    public static Set<String> getCardsBySuit(String suit) {
        Set<String> cards = new HashSet<>();
        for (String rank : RANKS) {
            cards.add(rank + " of " + suit);
        }
        return cards;
    }

    // Get all cards 
    public static Set<String> getAllCards() {
        Set<String> cards = new HashSet<>();
        for (String rank : RANKS) {
            for (String suit : SUITS) {
                cards.add(rank + " of " + suit);
            }
        }
        return cards;
    }
}
