/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdel
 */
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
public class Hand implements Comparable<Hand>{
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }
    
    public void add(Card card) {
        this.cards.add(card);
    }
    public void print() {
        for (Card card : this.cards) {
            System.out.println(card);
        }
    }
     public void sort() {
        this.cards.sort(null);
    }
    public void sortBySuit() {
        this.cards.sort(new SortBySuit());
    }

    @Override
    public int compareTo(Hand other) {
        int thisHandValue = this.cards.stream().mapToInt(Card::getValue).sum();
        int otherHandValue = other.cards.stream().mapToInt(Card::getValue).sum();
        return Integer.compare(thisHandValue, otherHandValue);
    }
    
}
