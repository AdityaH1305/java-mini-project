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

    public static void main(String[] args) throws InterruptedException {
        System.out.println("3");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("2");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1");

        Random rand = new Random();
        StringBuilder generatedText = new StringBuilder();

        // Generate 10 random words
        String[] selectedWords = new String[10];
        for (int i = 0; i < 10; i++) {
            selectedWords[i] = words[rand.nextInt(words.length)];
            System.out.print(selectedWords[i] + " ");
            generatedText.append(selectedWords[i]).append(" ");
        }
        System.out.println();

        double start = LocalTime.now().toNanoOfDay();

        Scanner scan = new Scanner(System.in);
        String typed = scan.nextLine().trim(); // Read input and trim extra spaces

        double end = LocalTime.now().toNanoOfDay();

        // Check if the typed text matches the generated text
        if (!typed.equals(generatedText.toString().trim())) {
            System.out.println("You've printed the wrong word!");
            return; // Exit without calculating WPM
        }

        // Calculate WPM
        double actualTime = end - start;
        double sec = actualTime / 1_000_000_000.0;
        int numc = typed.length();
        int wpm = (int) (((double) numc / 5) / sec * 60);

        System.out.println("Your words per minute is " + wpm + "!");
        System.out.println(sec + " seconds");
    }
}
