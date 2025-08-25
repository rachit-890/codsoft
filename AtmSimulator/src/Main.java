//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00);

        ATM atm = new ATM(account);

        atm.run();
    }
}
