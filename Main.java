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

            req = sc.nextLine();

            c++;
        }
        sc.close();
        System.out.println("Remember, I dismiss you, not the bell.");
    }

    static String getProblem() {
        String[] probs = {
            "Given an array of scores, return true if each score is equal or greater than the one before. The array will be length 2 or more.\r\n" + //
                "\r\n" + //
                "\r\n" + //
                "scoresIncreasing([1, 3, 4]) → true\r\n" + //
                "scoresIncreasing([1, 3, 2]) → false\r\n" + //
                "scoresIncreasing([1, 1, 4]) → true",
            "Given an array of scores, compute the int average of the first half and the second half, and return whichever is larger. We'll say that the second half begins at index length/2. The array length will be at least 2. To practice decomposition, write a separate helper method\r\n" + //
                "int average(int[] scores, int start, int end) { which computes the average of the elements between indexes start..end. Call your helper method twice to implement scoresAverage(). Write your helper method after your scoresAverage() method in the JavaBat text area. Normally you would compute averages with doubles, but here we use ints so the expected results are exact.\r\n" + //
                "\r\n" + //
                "\r\n" + //
                "scoresAverage([2, 2, 4, 4]) → 4\r\n" + //
                "scoresAverage([4, 4, 4, 2, 2, 2]) → 4\r\n" + //
                "scoresAverage([3, 4, 5, 1, 2, 3]) → 4",
            "Given an array of scores, return true if there are scores of 100 next to each other in the array. The array length will be at least 2.\r\n" + //
                "\r\n" + //
                "\r\n" + //
                "scores100([1, 100, 100]) → true\r\n" + //
                "scores100([1, 100, 99, 100]) → false\r\n" + //
                "scores100([100, 1, 100, 100]) → true",
            "Given an array of scores sorted in increasing order, return true if the array contains 3 adjacent scores that differ from each other by at most 2, such as with {3, 4, 5} or {3, 5, 5}.\r\n" + //
                "\r\n" + //
                "\r\n" + //
                "scoresClump([3, 4, 5]) → true\r\n" + //
                "scoresClump([3, 4, 6]) → false\r\n" + //
                "scoresClump([1, 3, 5, 5]) → true",
            "Given an array of positive ints, return a new array of length \"count\" containing the first even numbers from the original array. The original array will contain at least \"count\" even numbers.\r\n" + //
                "\r\n" + //
                "\r\n" + //
                "copyEvens([3, 2, 4, 5, 8], 2) → [2, 4]\r\n" + //
                "copyEvens([3, 2, 4, 5, 8], 3) → [2, 4, 8]\r\n" + //
                "copyEvens([6, 1, 2, 4, 5, 8], 3) → [6, 2, 4]"
            };

        int n = new Random().nextInt(probs.length);
        return "Solve this: " + probs[n];
    }
    
    static String getIDE() {
        String[] ides = {"Replit", "Google Docs", "Google Slides", "Visual Studio (the purple one)", "Google Colab (Java)", "Notepad", "The sticky note on my desk"};

        int n = new Random().nextInt(ides.length);
        return "You should use " + ides[n];
    }

    static String getResponse() {


        return null;
    }
}