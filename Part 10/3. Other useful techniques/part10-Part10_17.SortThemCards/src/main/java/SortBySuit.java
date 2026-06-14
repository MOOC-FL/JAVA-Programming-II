/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdel
 */
import java.util.Comparator;
public class SortBySuit implements Comparable<Card>, Comparator<Card> {
    public int compare(Card c1, Card c2) {
        if (c1.getSuit() != c2.getSuit()) {
            return c1.getSuit().compareTo(c2.getSuit());
        }
        return Integer.compare(c1.getValue(), c2.getValue());
    }

    @Override
    public int compareTo(Card o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}
