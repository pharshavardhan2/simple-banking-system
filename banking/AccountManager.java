package banking;

import java.util.Random;

class AccountManager {

  enum Status {
    MAIN, GET_CARD, MEMBER
  }

  private final Account[] accounts;
  private final long BIN;
  private int presentAccountNo;
  Status presentStatus;
  private int sessionUser;

  AccountManager(int noAccounts, long bin) {
    this.accounts = new Account[noAccounts];
    this.BIN = bin;
    this.presentAccountNo = 0;
    this.presentStatus = Status.MAIN;
    this.sessionUser = -1;
  }

  void generateAccount() {
    Random random = new Random();
    String password = "";
    for (int i = 0; i < 4; i++) {
      password += random.nextInt(10);
    }
    Account account = new Account(this.BIN + this.presentAccountNo, password, 0);
    accounts[this.presentAccountNo] = new Account(account);
    presentAccountNo++;
    System.out.println("\nYour card has been created");
    System.out.println("Your card number:\n" + account.getAccountNO()
            + "\nYour card PIN:\n" + account.getPassword());
    this.presentStatus = Status.MAIN;
  }

  boolean validateAccount(String no, String pin) {
    for (int i = 0; i < presentAccountNo; i++) {
      if (accounts[i].getAccountNO() == Long.parseLong(no) && accounts[i].getPassword().equals(pin)) {
        this.sessionUser = i;
        return true;
      }
    }
    return false;
  }

  void showMemberMenu() {
    System.out.println("\n1. Balance\n2. Log out\n0. Exit");
  }

  void showMainMenu() {
    System.out.println("\n1. Create an account\n2. Log into account\n0. Exit");
  }

  boolean userCommand(String userInput) {
    switch (this.presentStatus) {
      case MAIN:
        switch (userInput) {
          case "1":
            generateAccount();
            showMainMenu();
            break;

          case "2":
            this.presentStatus = Status.GET_CARD;
            System.out.println("\nEnter your card number:");
            break;

          case "0":
            return true;

          default:
            System.out.println("Invalid option");
            break;
        }
        break;

      case GET_CARD:
        String[] accountData = userInput.split(" ");
        boolean isValid = validateAccount(accountData[0], accountData[1]);
        if(!isValid) {
          System.out.println("Wrong card number or PIN!");
          this.presentStatus = Status.MAIN;
          showMainMenu();
        } else {
          this.presentStatus = Status.MEMBER;
          System.out.println("\nYou have successfully logged in!");
          showMemberMenu();
        }
        break;

      case MEMBER:
        switch (userInput) {
          case "1":
            System.out.println("\nBalance: " + this.accounts[sessionUser].getBalance());
            showMemberMenu();
            break;

          case "2":
            this.sessionUser = -1;
            System.out.println("\nYou have successfully logged out!");
            this.presentStatus = Status.MAIN;
            showMainMenu();
            break;

          case "0":
            return true;

          default:
            System.out.println("Invalid option");
            showMemberMenu();
            break;
        }
    }
    return false;
  }
}
