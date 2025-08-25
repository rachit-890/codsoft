class BankAccount {
    private double balance;


    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.printf("Successfully deposited ₹%.2f%n", amount);
        } else {
            System.out.println("Invalid amount. Deposit must be a positive number.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Withdrawal must be a positive number.");
            return false;
        }
        if (amount > this.balance) {
            System.out.println("Withdrawal failed. Insufficient funds.");
            System.out.printf("You only have ₹%.2f available.%n", this.balance);
            return false;
        }

        this.balance -= amount;
        System.out.printf("Successfully withdrew ₹%.2f%n", amount);
        return true;
    }
}
