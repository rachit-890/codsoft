import java.util.Scanner;
import java.util.InputMismatchException;

class ATM {
    private BankAccount userAccount;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.userAccount = account;
        this.scanner = new Scanner(System.in);
    }

    private void displayMenu() {
        System.out.println("\n===== ATM Menu =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.println("====================");
    }

    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Please choose an option (1-4): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    checkBalance();
                    break;
                case "2":
                    deposit();
                    break;
                case "3":
                    withdraw();
                    break;
                case "4":
                    System.out.println("\nThank you for using the ATM. Goodbye!");
                    scanner.close(); // Close the scanner before exiting
                    return; // Exit the run method and the program
                default:
                    System.out.println("\nInvalid choice. Please select a valid option from the menu.");
                    break;
            }
        }
    }

    private void checkBalance() {
        System.out.printf("%nYour current balance is: ₹%.2f%n", userAccount.getBalance());
    }

    private void deposit() {
        try {
            System.out.print("Enter the amount to deposit: ₹");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the leftover newline character
            userAccount.deposit(amount);
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid input. Please enter a numerical value.");
            scanner.nextLine(); // Clear the invalid input from the scanner
        }
    }

    private void withdraw() {
        try {
            System.out.print("Enter the amount to withdraw: ₹");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the leftover newline character
            userAccount.withdraw(amount);
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid input. Please enter a numerical value.");
            scanner.nextLine(); // Clear the invalid input from the scanner
        }
    }
}
