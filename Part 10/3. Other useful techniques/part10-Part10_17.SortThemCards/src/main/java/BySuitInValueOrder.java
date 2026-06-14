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
public class BySuitInValueOrder implements Comparator<Card> {

    @Override
    public int compare(Card arg0, Card arg1) {
        if (arg0.getSuit() != arg1.getSuit()) {
            return arg0.getSuit().compareTo(arg1.getSuit());
        }   
        return Integer.compare(arg0.getValue(), arg1.getValue());
    }
}
