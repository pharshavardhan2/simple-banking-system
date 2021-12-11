package banking;

class Account {
  private long accountNO;
  private String password;
  private int balance;

  Account(long accountNO, String password, int balance) {
    this.accountNO = accountNO;
    this.password = password;
    this.balance = balance;
  }

  Account(Account another) {
    this.accountNO = another.accountNO;
    this.password = another.password;
  }

  long getAccountNO() {
    return this.accountNO;
  }

  void setAccountNo(long accountNO) {
    this.accountNO = accountNO;
  }

  String getPassword() {
    return this.password;
  }

  void setPassword(String password) {
    this.password = password;
  }

  int getBalance() {
    return this.balance;
  }
}
