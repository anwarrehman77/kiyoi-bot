import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hi! I'm Mr. Kiyoi!");
        String req = sc.nextLine();
        int c = 0;

        while (!req.toLowerCase().equals("it's 3:22")) {
            if (c % 3 == 0) System.out.println(getProblem());
            else if (c % 5 == 0) System.out.println(getIDE());

            c++;
        }
        sc.close();
        System.out.println("Remember, I dismiss you, not the bell.");
    }

    static String getProblem() {
        String[] probs = {};

        int n = new Random().nextInt(probs.length);
        return probs[n];
    }
    
    static String getIDE() {
        String[] ides = {"Replit", "Google Docs", "Google Slides", "Visual Studio (the purple one)", "Google Colab (Java)", "Notepad", "The sticky note on my desk"};

        int n = new Random().nextInt(ides.length);
        return ides[n];
    }

    static String getResponse() {


        return null;
    }
}