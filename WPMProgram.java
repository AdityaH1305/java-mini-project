import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class WPMProgram {
    static String[] words = {
        "apple", "banana", "cherry", "dog", "elephant", "forest", "guitar", "house", "island", "jungle",
        "kite", "lemon", "mountain", "notebook", "ocean", "pencil", "queen", "river", "sunset", "tiger",
        "umbrella", "volcano", "whale", "xylophone", "yellow", "zebra", "avocado", "bridge", "castle",
        "diamond", "engine", "flamingo", "garden", "horizon", "iceberg", "jigsaw", "kangaroo", "lantern",
        "moonlight", "nightingale", "octopus", "parrot", "quicksand", "rainbow", "sapphire", "tornado",
        "unicorn", "violet", "windmill", "yacht", "zeppelin"
    };

    static int highestWPM = 0;

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose difficulty: 1. Easy (5 words), 2. Medium (10 words), 3. Hard (15 words)");
        int choice = scan.nextInt();
        int wordCount = choice == 1 ? 5 : (choice == 2 ? 10 : 15);
        scan.nextLine();

        while (true) {
            System.out.println("Get ready!");
            for (int i = 3; i > 0; i--) {
                System.out.println(i);
                TimeUnit.SECONDS.sleep(1);
            }
            
            Random rand = new Random();
            StringBuilder generatedText = new StringBuilder();
            String[] selectedWords = new String[wordCount];
            for (int i = 0; i < wordCount; i++) {
                selectedWords[i] = words[rand.nextInt(words.length)];
                System.out.print(selectedWords[i] + " ");
                generatedText.append(selectedWords[i]).append(" ");
            }
            System.out.println();

            double start = LocalTime.now().toNanoOfDay();
            String typed = scan.nextLine().trim();
            double end = LocalTime.now().toNanoOfDay();

            double actualTime = end - start;
            double sec = actualTime / 1_000_000_000.0;
            int numc = typed.length();
            int wpm = (int) (((double) numc / 5) / sec * 60);

            if (!typed.equals(generatedText.toString().trim())) {
                System.out.println("Incorrect! Try again.");
                continue;
            }

            int accuracy = (int) (((double) getMatchingCharacters(typed, generatedText.toString().trim()) / generatedText.length()) * 100);
            System.out.println("Your WPM: " + wpm);
            System.out.println("Accuracy: " + accuracy + "%");
            System.out.println("Time taken: " + sec + " seconds");

            if (wpm > highestWPM) {
                highestWPM = wpm;
                System.out.println("New High Score!");
            } else {
                System.out.println("Highest WPM so far: " + highestWPM);
            }
            
            System.out.println("Play again? (yes/no)");
            if (!scan.nextLine().equalsIgnoreCase("yes")) {
                break;
            }
        }
    }

    public static int getMatchingCharacters(String typed, String original) {
        int count = 0;
        for (int i = 0; i < Math.min(typed.length(), original.length()); i++) {
            if (typed.charAt(i) == original.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
