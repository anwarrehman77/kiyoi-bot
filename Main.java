import java.io.*;
import java.util.*;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;


class Main extends JFrame implements ActionListener{
    static boolean recognize = true;
    static boolean gettingChart = false;
    static boolean gettingHelp = false;
    private ArrayList<String> chart = new ArrayList<String>();
    private JButton button;
	  private JTextArea textArea;
	  private JScrollPane scrollPane;
	  private JTextField text;
	  private final String[] HELP_KEYS = {
		  "assist", "aid", "support", "back", "relieve", "lend a hand", "give a hand", 
		  "help out", "be of service", "pitch in", "come to the rescue", "be there for",
		  "boost", "uphold", "facilitate", "encourage", "succor", "help"
	};
    private ArrayList<String> helpMethodWords;
    private final String[] positiveResponses = {
          "Absolutely! I'm here to help with any coding challenges you're facing.",
          "Sure thing! Let's tackle this coding problem together.",
          "Of course! Your coding concerns are my top priority.",
          "Indeed! I'm ready to assist you in mastering this programming concept.",
          "Absolutely, my coding compatriot! ",
          "Certainly! Let's dive into the world of programming problem-solving.",
          "No problem at all! I'm here to help you understand and conquer coding obstacles.",
          "Indeed! Your journey to coding excellence starts with this question.",
          "Absolutely! Your curiosity in coding is commendable. Let's explore it further.",
          "Sure, I've got your back! ",
          "Absolutely! Your enthusiasm for learning code is truly inspiring.",
          "Of course! Let's make this coding journey a collaborative and successful one.",
          "Certainly! Your dedication to mastering programming is evident.",
          "Sure thing! I'm excited to assist you in your coding endeavors.",
          "Absolutely! Your quest for programming knowledge is commendable.",
          "Of course! I'm here to support you on your coding adventure.",
          "Sure, I'm at your service! Let's make coding concepts crystal clear.",
          "Absolutely! Your commitment to understanding code is truly commendable.",
          "No doubt! I'm here to help you navigate the intricacies of coding.",
          "Certainly! Your interest in programming is the key to success. "
      };
    public static void main(String[] args) {
      Main wowWhataGreatProgram = new Main();
      
    }

    Main(){
      textArea = new JTextArea("Hi! I'm Mr. Kiyoi! I can help you with APCS concepts and make a seating chart (given the students in your class :D)"
      + "\n" + "Btw, if you get tired of me, just say 'it's 3:22'");
      scrollPane = new JScrollPane(textArea);
      scrollPane.setBounds(25, 125, 325, 300);
      scrollPane.setVisible(true);
      scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
      scrollPane.setBackground(Color.WHITE);
      scrollPane.setOpaque(true);
  
  
  
  
      JLabel infoLabel = new JLabel();
      infoLabel.setBounds(25, 55, 300, 20);
         infoLabel.setVisible(true);
      infoLabel.setText("Put your responses here:");
      infoLabel.setForeground(Color.BLACK);
      
    button = new JButton();
    button.setBounds(250, 75, 100, 30);
    button.addActionListener(this);
    button.setHorizontalTextPosition(JButton.CENTER);
    button.setVerticalTextPosition(JButton.CENTER);
      button.setForeground(Color.black);
      button.setBackground(Color.lightGray);
        
      button.setBorder(BorderFactory.createLineBorder(Color.black));
      
      button.setText("Submit");
      
      button.setFocusable(false);
      this.setLayout(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(750,750);
      text= new JTextField();  
        text.setBounds(25, 75, 200,30);
      this.add(text);
      this.add(button);
      this.add(scrollPane);
      this.add(infoLabel);
      this.setBackground(Color.BLUE);
      this.getContentPane().setBackground(new Color(51, 204, 255));
      this.setVisible(true);
    }

    private void getHelp(String request) {
        request = removePlural(request);
        helpMethodWords = getIndex(request);
        if(helpMethodWords.size() == 0) {
          textArea.append("I am having trouble recognizing that. Could you rephrase that in a different way or use formal terminalogy?");
          gettingHelp = false;
        }
        else if (helpMethodWords.size() == 1) {
          textArea.append(positiveResponses[(int)(20 * Math.random())] + "\n" + makeBlock(helpMethodWords.get(0)).substring(4));
          gettingHelp = false;
        }
        else {
          String str = "";
          int i = 1;
          for(String word : helpMethodWords) {
              str += Integer.toString(i) + ": " + makeTitle(word).substring(4, makeTitle(word).length()-1) + "\n";
              i++;
          }
          textArea.append("I'm a bit confused, as I am not sure specifically what you need help with. Which of these would you like to learn more about? Respond with the number to the left of the desired option\n" + str + "\nFor none of these, enter \"none\"\n\n");
        }
      }

    static String removePlural(String sentence) {
        String[] words = sentence.split("\\s+");
        String news = "";

        for (String word : words) {
            if (word.endsWith("es")) {
                news += word.substring(0, word.length() - 2) + " ";
            }
            else if (word.endsWith("s")) {
                news += word.substring(0, word.length() - 1) + " ";
            }  
            else {
                news += word + " ";
            }
        }
        return news.trim();
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
          ArrayList<String> sad = new ArrayList<String>(); 
          sad.add("6969");
          return sad;
        }
      }

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

    static String makeBlock(String index) {
        //woah comments
        //this try part is the part that should always execute
        try {
          //create new buffered reader class 1
          BufferedReader input = new BufferedReader(new FileReader("apcs help with responses formatted chatbot.txt"));
//asdf
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

    private void getProblem() {
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
        String p = "Solve this: " + probs[n] + "\nI have no way of grading, so I'm gonna trust that you tried and give you and A for effort =D";
        textArea.append(p);
    }
    
    private void getIDE() {
        String[] ides = {"Replit", "Google Docs", "Google Slides", "Visual Studio (the purple one)", "Google Colab (Java)", "Notepad", "The sticky note on my desk"};

        int n = new Random().nextInt(ides.length);
        String s = "You should use " + ides[n] + " to write code";
        textArea.append(s);
    }

    private void respond(String[] helpWords, String req) {
      textArea.append("\n\nUser Response: "+ req + "\n\n");
      recognize = false;
        for (String s: helpWords){
            if (req.contains(s)) {
              gettingHelp = true;
              getHelp(req);
              recognize = true;
              break;
            }
        }
        
      
        if (req.toLowerCase().contains("seating chart")){
          recognize = true;
          gettingChart = true;
          createSeatingChart();
        }
        else if (req.toLowerCase().contains("problem") || req.toLowerCase().contains("exercise")) {
          getProblem();
          recognize = true;
        }
        else if (req.toLowerCase().contains("ide")){
          getIDE();
          recognize = true;
        }
        if (recognize == false){
          String[] unknownResponses = {
              "I'm not sure I understand. Could you provide more context?",
              "I didn't catch that. Can you please repeat or rephrase?",
              "Sorry, I'm not familiar with that. Could you clarify?",
              "I'm still learning! Can you explain that in a different way?",
              "It seems I'm not equipped to respond to that. What else can we talk about?",
              "I'm not programmed for that response. Mind sharing more details?",
              "I'm afraid I didn't get that. Can you try saying it differently?",
              "Hmm, I'm a bit confused. Can you give me more information?",
              "I'm not certain how to respond. Could you elaborate?",
              "I'm sorry, I didn't understand. Could you rephrase your statement?"
          };
          textArea.append(unknownResponses[(int)(Math.random()*10)]);
        }
    }

    private void createSeatingChart() {
      textArea.append("Hold up, I forgot who's in this class, can you remind me, just say 'stop' when you're done.");
    }

    @Override
    public void actionPerformed(ActionEvent a){
      if(text.getText().toLowerCase().replace("\n", "").equals("it's 3:22") && a.getSource()==button) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
          }
          this.setVisible(false);
          JOptionPane.showMessageDialog(this,"Remember, I dismiss you, not the bell");  
        }
      else if(a.getSource()==button){
        if(gettingHelp == false && gettingChart == false) {
      respond(this.HELP_KEYS, text.getText().toLowerCase());
      text.setText("");
      } else if (gettingChart == true) {
        String s = text.getText();
        if(!s.toLowerCase().contains("stop")) {
          chart.add(s);
        }
        else if(s.toLowerCase().contains("stop")) {
          String re= "";
          Collections.shuffle(chart);
          while(chart.size() % 5 != 0) {
            chart.add("empty");
          }
          for(int i = 0; i < chart.size()/5; i++) {
            re += "Group " + (i + 1) + ":" + "\n\n";
            for(int j = 0; j < 5; j++) {
              re += chart.get(5*i + j) + "\n";
            }
          }
          textArea.append(re);
          gettingChart = false;
          chart.clear();
        }
        text.setText("");
    } else if(gettingHelp == true) {
      String res = text.getText().replaceAll("\\s+","").replaceAll("[^A-Za-z0-9]","");
          
          if (res.equals("none")) {
              textArea.append("Alright, try rephrasing your original question in a different way. ");
          }
          else {
            try { 
                Integer.parseInt(res.replaceAll("\\s+","")); 
          textArea.append(positiveResponses[(int)(20 * Math.random())] + "\n" + makeBlock(helpMethodWords.get(Integer.valueOf(res)-1)).substring(4));
            } catch(NumberFormatException e) {
                textArea.append("That wasn't one of the options!");
                // System.out.println(e);
                // e.printStackTrace();
            }
             catch(NullPointerException e) {
                textArea.append("That wasn't one of the options!");
                // System.out.println(e);
                // e.printStackTrace();
             }
             gettingHelp = false;
             text.setText("");
    }
  }
    
  }
    }
  }