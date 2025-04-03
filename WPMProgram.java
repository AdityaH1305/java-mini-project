import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class WPMProgram {
    // ANSI color codes
    static final String RESET = "\u001B[0m";
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String BLUE = "\u001B[34m";
    static final String PURPLE = "\u001B[35m";
    static final String CYAN = "\u001B[36m";

    static String[] words = {
        "apple", "banana", "cherry", "dog", "elephant", "forest", "guitar", "house", "island", "jungle",
        "kite", "lemon", "mountain", "notebook", "ocean", "pencil", "queen", "river", "sunset", "tiger",
        "umbrella", "volcano", "whale", "xylophone", "yellow", "zebra", "avocado", "bridge", "castle",
        "diamond", "engine", "flamingo", "garden", "horizon", "iceberg", "jigsaw", "kangaroo", "lantern",
        "moonlight", "nightingale", "octopus", "parrot", "quicksand", "rainbow", "sapphire", "tornado",
        "unicorn", "violet", "windmill", "yacht", "zeppelin"
    };

    static int highestWPM = 0;
    static List<Integer> wpmScores = new ArrayList<>();
    static List<Integer> accuracyScores = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println(CYAN + "╔════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + "║   Welcome to the WPM Typing Test   ║" + RESET);
        System.out.println(CYAN + "╚════════════════════════════════════╝" + RESET + "\n");

        System.out.println(YELLOW + "Choose difficulty:" + RESET);
        System.out.println("  1. Easy (5 words)");
        System.out.println("  2. Medium (10 words)");
        System.out.println("  3. Hard (15 words)");
        System.out.print(GREEN + "Enter your choice: " + RESET);
        int choice = scan.nextInt();
        int wordCount = choice == 1 ? 5 : (choice == 2 ? 10 : 15);
        scan.nextLine();

        while (true) {
            System.out.println("\n" + PURPLE + "Get ready!" + RESET);
            for (int i = 3; i > 0; i--) {
                System.out.println(YELLOW + i + RESET);
                TimeUnit.SECONDS.sleep(1);
            }
            
            System.out.println("\n" + BLUE + "Type the following words:" + RESET);
            Random rand = new Random();
            StringBuilder generatedText = new StringBuilder();
            String[] selectedWords = new String[wordCount];
            for (int i = 0; i < wordCount; i++) {
                selectedWords[i] = words[rand.nextInt(words.length)];
                System.out.print(CYAN + selectedWords[i] + " " + RESET);
                generatedText.append(selectedWords[i]).append(" ");
            }
            System.out.println("\n\n" + GREEN + "Start typing below (press Enter when done):" + RESET + "\n");

            double start = LocalTime.now().toNanoOfDay();
            String typed = scan.nextLine().trim();
            double end = LocalTime.now().toNanoOfDay();
            
            double actualTime = end - start;
            double sec = actualTime / 1_000_000_000.0;
            int numc = typed.length();
            int wpm = (int) (((double) numc / 5) / sec * 60);

            System.out.println("\n" + PURPLE + "╔════════════════════════════════════╗" + RESET);
            System.out.println(PURPLE + "║        Typing Test Results         ║" + RESET);
            System.out.println(PURPLE + "╚════════════════════════════════════╝" + RESET);

            int accuracy = calculateWordAccuracy(typed, selectedWords);
            System.out.printf(GREEN + " Your WPM:      %-3d%n" + RESET, wpm);
            System.out.printf(YELLOW + " Accuracy:      %-3d%%%n" + RESET, accuracy);
            System.out.printf(CYAN + " Time taken:    %.2f seconds%n" + RESET, sec);
            System.out.println();

            wpmScores.add(wpm);
            accuracyScores.add(accuracy);

            if (wpm > highestWPM) {
                highestWPM = wpm;
                System.out.println(GREEN + "  New High Score! " + RESET + "\n");
            } else {
                System.out.println(YELLOW + " Highest WPM so far: " + highestWPM + RESET + "\n");
            }
            
            System.out.println(BLUE + "Would you like to play again? (yes/no)" + RESET);
            if (!scan.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("\n" + CYAN + "What would you like to do?" + RESET);
                System.out.println("  1. Exit");
                System.out.println("  2. Show results");
                System.out.print(GREEN + "Enter your choice: " + RESET);
                int finalChoice = scan.nextInt();
                scan.nextLine();
                
                if (finalChoice == 2) {
                    showGraph();
                }
                
                System.out.println("\n" + PURPLE + "Thanks for playing! Goodbye!" + RESET + "\n");
                break;
            }
        }
    }

    public static int calculateWordAccuracy(String typed, String[] originalWords) {
        String[] typedWords = typed.trim().split("\\s+");
        int correctWords = 0;
        int totalWords = originalWords.length;

        for (int i = 0; i < Math.min(typedWords.length, totalWords); i++) {
            if (typedWords[i].equals(originalWords[i])) {
                correctWords++;
            }
        }
        return (int) (((double) correctWords / totalWords) * 100);
    }

    public static void showGraph() {
        System.out.println("\n" + BLUE + "╔════════════════════════════════════╗" + RESET);
        System.out.println(BLUE + "║        Typing Speed Progress       ║" + RESET);
        System.out.println(BLUE + "╚════════════════════════════════════╝" + RESET);
        for (int i = 0; i < wpmScores.size(); i++) {
            System.out.printf(GREEN + " Attempt %-2d: " + RESET, i + 1);
            int bars = wpmScores.get(i) / 2;
            System.out.print(YELLOW);
            for (int j = 0; j < bars; j++) {
                System.out.print("█");
            }
            System.out.println(RESET + " " + wpmScores.get(i) + " WPM");
        }
        
        System.out.println("\n" + BLUE + "╔════════════════════════════════════╗" + RESET);
        System.out.println(BLUE + "║      Typing Accuracy Progress      ║" + RESET);
        System.out.println(BLUE + "╚════════════════════════════════════╝" + RESET);
        for (int i = 0; i < accuracyScores.size(); i++) {
            System.out.printf(GREEN + " Attempt %-2d: " + RESET, i + 1);
            int bars = accuracyScores.get(i) / 2;
            System.out.print(CYAN);
            for (int j = 0; j < bars; j++) {
                System.out.print("*");
            }
            System.out.println(RESET + " " + accuracyScores.get(i) + "% Accuracy");
        }
    }
}
