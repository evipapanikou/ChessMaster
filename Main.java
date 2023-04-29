import java.util.ArrayList;

class Main {
  public static void clear() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static String line(int dim) {
    String s = "";
    for (int i = 0; i < dim; i++) {
      s += "----";
    }
    return s;
  }

  public static void print(char[][] arr, String word, int size) {
    String lineStr = line(size), alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int pos = 0;

    System.out.println("\t " + lineStr);
    for (int i = 0; i < size; i++) {
      System.out.print((size - i) + "\t");
      for (int j = 0; j < size; j++) {
        System.out.print(" | " + arr[i][j]);
        if (word.charAt(i) == (arr[i][j])) {
          pos = j;
        }
      }
      System.out.println(" |\t " + word.charAt(i)
          + ": (" + alphabet.charAt(pos) + ", " + (size - i)
          + ")\n\t " + lineStr);
    }

    System.out.print("\t  ");
    for (int i = 0; i < size; i++) {
      System.out.print(" " + alphabet.charAt(i) + "  ");
    }
  }

  public static void fillBasic(char[][] arr, int size) {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (j == i) {
          arr[i][j] = 'X';
        } else {
          arr[i][j] = ' ';
        }
      }
    }
  }

  public static int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }

  public static String fillRandomeLetters(char[][] arr, String word, Boolean rnd) {

    int size = word.length();
    ArrayList<Character> letters = new ArrayList<Character>();
    String result = "";

    for (char ch : word.toCharArray()) {
      letters.add(ch);
    } // ['C', 'H', 'E', ...]

    for (int i = 0, j = 0; i < size; i++) {

      do {
        j = getRandomNumber(0, size);
      } while (j == i);
      
      if(rnd){
        arr[i][j] = letters.remove(getRandomNumber(0, letters.size()));
        result += arr[i][j];
      }
      else{
        arr[i][j] = letters.get(i);
      } 

    }

    return result;
  }

  // -------------------------------------------------------- main
  public static void main(String[] args) {
    clear();

    // part A - word = CHESSMASTER
    String word = "CHESSMASTER";
    int size = word.length();
    char[][] arr = new char[word.length()][word.length()];

    System.out.println("η λέξη είναι: " + word);
    System.out.println("");

    fillBasic(arr, size);
    fillRandomeLetters(arr, word, false);
    print(arr, word, size);

    // part B - word = MINDBLOWING (reverse printing)
    word = "MINDBLOWING";
    size = word.length();
    arr = new char[word.length()][word.length()];

    System.out.println("\n\nη λέξη είναι: " + word);
    System.out.println("");

    String word2 = "";
    for (int i = size - 1; i >= 0; i--) {
      word2 += word.charAt(i);
    }

    fillBasic(arr, size);
    fillRandomeLetters(arr, word2, false);
    print(arr, word2, size);

    // part c - word = EXTRAVAGANT (random printing)
    word = "EXTRAVAGANT";
    size = word.length();
    arr = new char[word.length()][word.length()];

    System.out.println("\n\nη λέξη είναι: " + word);
    System.out.println("");

    fillBasic(arr, size);
    print(arr,  fillRandomeLetters(arr, word, true), size);

  } // end of main method
} // end of class Main
