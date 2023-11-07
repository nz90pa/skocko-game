
import java.util.Scanner;
import java.util.Random;


public class Skocko {


    private static final int MAX_ATTEMPTS = 7;
    private static final int MAX_SYMBOLS = 4;
    private static final int SYMBOLS_COUNT = 6;
    private static final int POINTS_FOR_WIN = 30;
    private static final int POINTS_FOR_WIN2 = 20;
    private static final int POINTS_FOR_LOSS = 0;

    private static int totalPoints = 0;


    private static final char[] SYMBOLS = {'1', '2', '3', '4', '5', '6'};

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    private static int[] generateRandomCombination() {
        int[] combination = new int[MAX_SYMBOLS];
        for (int i = 0; i < MAX_SYMBOLS; i++) {
            combination[i] = random.nextInt(SYMBOLS_COUNT) + 1;
        }
        return combination;
    }

    private static void printAttempt(int correctCount, int correctPositions) {
        System.out.print("Correct: " + correctCount + ", Correct positions: " + correctPositions + "\n");
    }

    private static boolean isWinner(int correctPositions) {
        return correctPositions == MAX_SYMBOLS;
    }

    private static void playGame() {
        int[] combination = generateRandomCombination();
        int attemptCount = 0;
        int points = 0;
        System.out.println("Welcome to Skocko!");
        System.out.println("Guess the combination of " + MAX_SYMBOLS + " symbols, each between 1 and " + SYMBOLS_COUNT + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts. Good luck!");

        while (attemptCount < MAX_ATTEMPTS) {
            System.out.print("Attempt #" + (attemptCount+1) + ": ");

            int[] attempt = new int[MAX_SYMBOLS];
            for (int i = 0; i < MAX_SYMBOLS; i++) {
                attempt[i] = scanner.nextInt();
            }

            int correctCount = 0;
            int correctPositions = 0;
            
            for (int i = 0; i < MAX_SYMBOLS; i++) {
                    if (attempt[i]==combination[i]){
                        correctPositions++;
                    }
            }    
            for (int i = 0; i < MAX_SYMBOLS; i++){
                for(int j = 0 ; j< MAX_SYMBOLS; j++){
                    if(combination[i]==attempt[j]){
                        correctCount++;
                        attempt[j]=0;
                        break;
                    }
                }
            }
            
                /*if (attempt[i] == combination[i]) {
                    correctPositions++;
                    correctCount++;
                    System.out.println(attempt[i]+"   " + combination[i]) ;
                } 
                else {
                    for (int j = i+1; j < MAX_SYMBOLS; j++) {
                        if (i!=j && attempt[i] == combination[j]) {
                            correctCount++;
                            break;
                        }
                    }
                }
                
            }*/
            

            printAttempt(correctCount, correctPositions);
            
            if (attemptCount == MAX_ATTEMPTS-1){
                if (isWinner(correctPositions)) {
                System.out.println("Congratulations, you won!");
                points += POINTS_FOR_WIN2;
                break;
                }
            }
            
            if (isWinner(correctPositions)) {
                System.out.println("Congratulations, you won!");
                points += POINTS_FOR_WIN;
                break;
            }

            attemptCount++;
        }

        if (attemptCount == MAX_ATTEMPTS) {
            System.out.println("Sorry, you lost. The correct combination was:");
            for (int i = 0; i < MAX_SYMBOLS; i++) {
                System.out.print(combination[i] + " ");
            }
            points += POINTS_FOR_LOSS;
        }

        System.out.println("You scored " + points + " points.");
        totalPoints += points;
    }

    public static void main(String[] args) {
        int gameCount = 0;
        do {
            playGame();
            gameCount++;

            System.out.print("Play again? (Y/N): ");
            String playAgain = scanner.next();

            if (playAgain.equalsIgnoreCase("N")) {
                break;
            }

        } while (true);

        System.out.println("Thanks for playing Skocko!");
        System.out.println("You played " + gameCount + " games and scored " + totalPoints + " points in total.");
    }

	public static char[] getSymbols() {
		return SYMBOLS;
	}
}


