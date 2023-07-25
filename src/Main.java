import java.util.*;

public class Main {

    static List<Integer> cards = new ArrayList<>(Arrays.asList(11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10));
    static Random rand = new Random();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean gameContinue = true;
        while (gameContinue){
            List<Integer> userCards = new ArrayList<>();
            List<Integer> computerCards = new ArrayList<>();
            userCards.add(dealCards());
            userCards.add(dealCards());
            computerCards.add(dealCards());
            computerCards.add(dealCards());
            System.out.println("Computer cards:["+ computerCards.get(0) +",_]");
            while (gameContinue){
                System.out.println("Your Cards:" + userCards);
                int userScore = calculateScore(userCards);
                int computerScore = calculateScore(computerCards);
                System.out.println("Draw another card 'y' or 'n':");
                if(userScore == 0 || userScore > 21 ){
                    gameContinue = false;
                } else if (computerScore == 0) {
                    gameContinue = false;
                } else if (scanner.nextLine().equals("y")) {
                    userCards.add(dealCards());
                } else gameContinue = false;
            }
            if(calculateScore(userCards) > 21) System.out.println("You Lose");
            else {
                while (calculateScore(computerCards) < 17) computerCards.add(dealCards());
                System.out.println("Computer cards:" + computerCards);
                compare(userCards,computerCards);
            }
            System.out.println("Restart game?:(y/n)");
            String nextGame = scanner.nextLine();
            if(nextGame.equals("y")){
                gameContinue = true;
            } else gameContinue = false;
        }
        }

    public static int dealCards(){
        return cards.get(rand.nextInt(cards.size()));
    }

    public static int calculateScore(List<Integer> player){
        int sumOfCards = player.stream().mapToInt(Integer::intValue).sum();
        if(sumOfCards == 21){
            return 0;
        }

        if(player.contains(11) && sumOfCards > 21){
            player.remove((Integer) 11);
            player.add(1);
        }
        return sumOfCards;
    }

    public static void compare(List<Integer> player, List<Integer> computer){
        if(calculateScore(player) == calculateScore(computer)){
            System.out.println("Draw");
        } else if (calculateScore(computer)== 0) {
            System.out.println("You Lose");
        } else if (calculateScore(player) == 0) {
            System.out.println("You Win");
        } else if (calculateScore(player) > 21) {
            System.out.println("You Lose");
        } else if (calculateScore(computer) > 21) {
            System.out.println("You Win");
        } else if (calculateScore(player) > calculateScore(computer)) {
            System.out.println("You Win");
        } else if (calculateScore(player) < calculateScore(computer)) {
            System.out.println("You Lose");
        }
    }
}

