package banking;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    AccountManager manager = new AccountManager(100, 4_000_000_000_000_000L);
    Scanner sc = new Scanner(System.in);
    boolean exit = false;

    manager.showMainMenu();
    while (!exit && sc.hasNext()){
      String userInput;
      if (manager.presentStatus == AccountManager.Status.GET_CARD) {
        userInput = sc.nextLine().trim();
        System.out.println("Enter your PIN:");
        userInput += " " + sc.nextLine().trim();
      } else {
        userInput = sc.nextLine().trim();
      }
      exit = manager.userCommand(userInput);
    }

    System.out.println("\nBye!");
  }
}