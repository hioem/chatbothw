import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class main {
    private static ArrayList<String> responses;
    private static String[] keywords = {"home", "ball", "cat", "dog", "mother", "father", "sister", "brother", "bed", };

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Magpie mag = new Magpie();
        String[] greeting = {"ahoy", "allo", "aloha", "bonjour", "buenos dias", "buenas noches", "ciao", "good morning", "good afternoon", "good evening", "g’day mate", "hallo", "hello", "hello sunshine", "hey", "hey man", "hey there", "heeey, baaaaaby", "hi", "hidyho", "hiya", "hola", "how’s it going", "how are you doing", "how's it hanging", "how goes it", "howdy", "howdy", "howdy partner", "konnichiwa", "morning", "mornin", "namaste", "ni hao", "que pasa", "salaam", "sup?", "whaddup", "whassap", "whazzup", "whats crackin", "whats up", "whats up buttercup", "whats up doc", "whats new", "what's going on", "yo", "yoo hoo"};
        responses = new ArrayList<String>();
        mag.greeting();

        switch (input.nextInt()) {
            case 1 -> {
                System.out.println(greeting[(int) (Math.random() * greeting.length)] + ", let's chat!");
                input.nextLine();

                while(!Objects.equals(input.nextLine(), "bye")) {
                    String statement = input.nextLine();
                    mag.emptyResponse(statement);
                    System.out.println(mag.getResponse(statement));
                }
            }
            case 2 -> System.out.println("Goodbye!");
            default -> System.out.println("Invalid input!");
        }


    }
}
