import java.io.*;
import java.util.*;

class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String[] help_keys = {
            "assist", "aid", "support", "back", "relieve", "lend a hand", "give a hand", 
            "help out", "be of service", "pitch in", "come to the rescue", "be there for",
            "boost", "uphold", "facilitate", "encourage", "succor", "help"
        };

        System.out.println("Hi! I'm Mr. Kiyoi! I can help you with APCS concepts and make a seating (given the students in your class :D)");
        String req = sc.nextLine();
        int c = 0;

        while (!req.toLowerCase().equals("it's 3:22")) {
            if (c % 3 == 0) System.out.println(getProblem());
            else if (c % 5 == 0) System.out.println(getIDE());

            respond(help_keys, req.toLowerCase());
            req = sc.nextLine();
            c++;
        }
        System.out.println("Remember, I dismiss you, not the bell.");
    }

    static void getHelp(String request) {
        request = removePlural(request);


        ArrayList<String> words = new ArrayList<String>();
        words = getIndex(request);
        if(words.size() == 0) {
          System.out.println("I am having trouble recognizing that. Could you rephrase that in a different way or use formal terminalogy?");
        }
        else if (words.size() == 1) {
          System.out.println("Sure! I can help with that! \n" + makeBlock(words.get(0)).substring(4));
        }
        else if (words.size() == 2) {
          String str = "";
          for(String word : words) {
            str += makeBlock(word).substring(4) + "\n";
          }
          System.out.println("Sure! I can help with that! \n" + str);
        }
    
        else {
          String str = "";
          int i = 1;
          for(String word : words) {
              str += Integer.toString(i) + ": " + makeTitle(word).substring(4, makeTitle(word).length()-1) + "\n";
              i++;
          }
          System.out.println("I'm a bit confused, as I am not sure specifically what you need help with. Which of these would you like to learn more about? Response with the number to the left of each option\n" + str + "\nFor none of these, enter \"none\"");
    
          String res = sc.nextLine().replaceAll("\\s+","").replaceAll("[^A-Za-z0-9]","");  // Read user input
          if (res.equals("none")) {
              System.out.println("Alright, try rephrasing your original question in a different way. ");
          }
          else {
          System.out.println(makeBlock(words.get(Integer.valueOf(res)-1)).substring(4));
          }
    
        }
      }
    
    static String removePlural(String sentence) {
      if(!sentence.substring(sentence.length()).equals(" ")) {
        sentence += " ";
      }
      String filtered = "";
      for(int i = 0; i < sentence.length()-2; i++) {
        if(sentence.substring(i, i+3).equals("es ")) {
          filtered += " ";
          i++;
        }
        else {
          filtered += sentence.substring(i, i+1);
        }
      }
      if(!sentence.substring(sentence.length()-2).equals("es")) {
        filtered += sentence.substring(sentence.length()-1);
      }

      sentence = filtered;
      filtered = "";
      for(int i = 0; i < sentence.length()-1; i++) {
        if(sentence.substring(i, i+2).equals("s ")) {
          filtered += " ";
          i++;
        }
        else {
          filtered += sentence.substring(i, i+1);
        }
      }
      if(!sentence.substring(sentence.length()-1).equals("s")) {
        filtered += sentence.substring(sentence.length()-2);
      }

      

      
      return filtered;
    }
      
      static ArrayList<String> getIndex(String response) {
        try {
          ArrayList<String> indexes = new ArrayList<String>();
    
          ArrayList<String> words = splitString(removePlural(response.toLowerCase().replaceAll("[^a-zA-Z ]", "")));
          for(String word : words) {
            BufferedReader input = new BufferedReader(new FileReader("apcs help with responses formatted chatbot.txt"));
            String pastLine = "";
            String line = "";
            while (input.ready()) {
              pastLine = line; 
              line = input.readLine();
    
    
              if(pastLine.equals("k&s2Uald9")) {
                ArrayList<String> titleWords = splitString(removePlural(line.toLowerCase().replaceAll("[^a-zA-Z ]", "")));
                for(String titleWord : titleWords) {
                    if(titleWord.equals(word)) indexes.add(line.substring(0, 4));
                }
              }
    
            }
            input.close();
          }
    
          //return the chunk as a string without the numbers at
          return indexes;
        }
    
        //do this if we can't find the file
        catch(Exception e) {
          // e.getStackTrace();
          // System.out.println("StackTrace: ");
          //    e.printStackTrace();
          ArrayList<String> sad = new ArrayList<String>(); 
          sad.add("6969");
          return sad;
        }
      }
    
      //this method splits a string into its individual words
      static ArrayList<String> splitString(String original) {
        String[] results = original.split(" ");
        return new ArrayList<String>(Arrays.asList(results));
    }
    
    
      static String makeTitle(String index) {
        String chunk = makeBlock(index);
        for(int i = 0; i < chunk.length(); i++) {
          if (chunk.substring(i, i+1).equals("\n")) {
            return chunk.substring(0, i);
        }
      }
    
    return "Something went wrong, please reprase and try again";
      }
    
      //this method should return indexth  "chunk"" or concept information starting from zero
      static String makeBlock(String index) {
        //woah comments
        //this try part is the part that should always execute
        try {
          //create new buffered reader class 1
          BufferedReader input = new BufferedReader(new FileReader("apcs help with responses formatted chatbot.txt"));
    
          //iterate through the chunks of the text file 
          
          String pastLine = "";
          String line = "";
          while (input.ready()) { 
            pastLine = line; 
            line = input.readLine();
            if(pastLine.equals("k&s2Uald9")) {
    
            }
            //stop when we get to the chunk we want to return
            if(line.length() > 3){
            if(index.equals(line.substring(0, 4))) {
              break;
            }
            }
          }
    
          //add the chunk to a string
          String str = "";
          str += line + "\n";
          while(input.ready()) {
            String newLine = input.readLine();
            if (!newLine.equals("k&s2Uald9")){
            str += newLine;
            str += "\n";
            } else {
              break;
            }
          }
          input.close();
          //return the chunk as a string without the numbers at
          return str;
        }
    
        //do this if we can't find the file
        catch(Exception e) {
          // e.getStackTrace();
          // System.out.println("StackTrace: ");
          //    e.printStackTrace();
          return "Something went wrong, please try again";
        }
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
        return "You should use " + ides[n] + " to write code";
    }

    static void respond(String[] helpWords, String req) {
        for (String s: helpWords){
            if (req.contains(s)) getHelp(req);
        }
        if (req.toLowerCase().contains("seating chart"))
          createSeatingChart();
    }

    static void createSeatingChart() {
      ArrayList<ArrayList<String>> chart = new ArrayList<ArrayList<String>>();
      chart.add(new ArrayList<String>());

      System.out.println("Who's in your class. Say 'stop' to stop :o");
      String s = sc.nextLine();
      int i = 0;
      int j = 0;
      while (!s.toLowerCase().contains("stop")) {
        if (j % 5 == 0) {
          chart.add(new ArrayList<String>());
          i++;
        }
        s = sc.nextLine();
        chart.get(i).add(s);
        j++;
      }

      System.out.println("Here ya go!");

      for (int k = 1; k < chart.size(); k++) {
        System.out.println("Group " + k + ":");
        for (String st : chart.get(k))
          System.out.println(st + "\n");
      }
    }
}
