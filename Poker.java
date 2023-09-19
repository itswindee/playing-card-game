import java.util.*;

public class Poker {

    private PlayingCards deck;

    ArrayList<String> hand1 = new ArrayList<String>();
    ArrayList<String> hand2 = new ArrayList<String>();



    public void dealHands(){
        for(int i =0; i < 5; i++){
            hand1.add(deck.getNextCard());
            hand2.add(deck.getNextCard());
        }
    }


    //constructor that takes no parameter
    public Poker(){
        deck = new PlayingCards();
        deck.Shuffle();
        dealHands();
    }


    //constructor for the two ArrayLists
    public Poker(ArrayList<String> hand1, ArrayList<String> hand2) {
        deck = new PlayingCards();
        this.hand1 = hand1;
        this.hand2 = hand2;
    }



    public void showHand(int integer){
        if(integer == 1){
            System.out.println("Player 1's hand:");
            for(int i = 0; i < hand1.size(); i++){
                System.out.print(hand1.get(i) + ", ");
            }
            System.out.println();
        }
        else if(integer == 2){
            System.out.println("Player 2's hand:");
            for(int i = 0; i < hand2.size(); i++){
                System.out.print(hand2.get(i) + ",");
            }
            System.out.println();
        }
    }


    public static int[] countSuite(ArrayList<String> hand){
        int[] count = new int[14];
        for(int i = 0; i < hand.size(); i++){
            String suite = hand.get(i).split(" ")[2].substring(0,1);
            if(suite.equals("C")){
                count[0]++;
            } else if(suite.equals("D")){
                count[1]++;
            } else if(suite.equals("H")){
                count[2]++;
            } else if(suite.equals("S")){
                count[3]++;
            }
        }
        return count;
    }


    public int[] countValues(ArrayList<String> hand) {
        int[] counts = new int[14];
        for (String card : hand) {
            String value = card.split(" ")[0];
            switch(value){
                case "A": counts[0]++; break;
                case "2": counts[1]++; break;
                case "3": counts[2]++; break;
                case "4": counts[3]++; break;
                case "5": counts[4]++; break;
                case "6": counts[5]++; break;
                case "7": counts[6]++; break;
                case "8": counts[7]++; break;
                case "9": counts[8]++; break;
                case "10": counts[9]++; break;
                case "J": counts[10]++; break;
                case "Q": counts[11]++; break;
                case "K": counts[12]++; break;
            }
        }
        return counts;
    }


    public static int numPairs(int[] counts) {
        int numPairs = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 2) {
                numPairs++;
            }
        }
        return numPairs;
    }



    public static int threeOfAKind(int[] counts){
        int numThreeOfAKind = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 3) {
                numThreeOfAKind++;
            }
        }
        return numThreeOfAKind;
    }


    public static int fourOfAKind(int[] counts){
        int numFourOfAKind = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 4) {
                numFourOfAKind++;
            }
        }
        return numFourOfAKind;
    }


    public static boolean fullHouse(int[] counts){
        return threeOfAKind(counts) != 0 && numPairs(counts) != 0;
    }


    public boolean straight(int[] counts){
        for (int i = 0; i < 10; i++){
            if (counts[i] == 1 && counts[i+1] == 1 && counts[i+2] == 1 && counts[i+3] == 1 && counts[i+4] == 1){
                return true;
            }
            if (counts[10] == 1 && counts[11] == 1 && counts[12] == 1 && counts[13] == 1 && counts[1] == 1){
                return true;
            }
        }
        return false;
    }


    public boolean flush(int[] suites){
        for (int i = 0; i < suites.length; i++){
            if (suites[i] == 5){
                return true;
            }
        }
        return false;
    }

    public boolean straightFlush(int[] counts, int[] suites){
        if(straight(counts) && flush(suites)){
            return true;
        }
        return false;
    }


    public boolean royalFlush(int[] counts, int[] suites){
        if(flush(suites)){
            if (counts[10] == 1 && counts[11] == 1 && counts[12] == 1 && counts[13] == 1 && counts[1] == 1){
                return true;
            }
        }
        return false;
    }

    public String scoreHand(int integer){
        int[] values = new int[14];
        int[] counts = new int[14];
        if(integer == 1){
            values = countValues(hand1);
            counts = countSuite(hand1);
        }
        else{
            values = countValues(hand2);
            counts = countSuite(hand2);
        }
        if(royalFlush(counts, values)){
            return "Royal Flush";
        }
        if(straightFlush(counts, values)){
            return "Straight Flush";
        }
        if(fourOfAKind(values) != 0){
            return "Four of a Kind";
        }
        if(fullHouse(values)){
            return "Full House";
        }
        if(flush(counts)){
            return "Flush";
        }
        if(straight(values)){
            return "Straight";
        }
        if(threeOfAKind(values)!= 0){
            return "Three of a Kind";
        }
        if(numPairs(values) > 1){
            return "Two pairs";
        }
        if(numPairs(values) == 1){
            return "One pair";
        }
        return "High Card";
    }
}
