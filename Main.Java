import java.util.Scanner;

// Custom exception classes
class InvalidPasswordException extends Exception {
    InvalidPasswordException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) {
        super(message);
    }
}

class InvalidAccountException extends Exception {
    InvalidAccountException(String message) {
        super(message);
    }
}

class WithdrawalException extends Exception {
    WithdrawalException(String message) {
        super(message);
    }
}

class DepositException extends Exception {
    DepositException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String account;
        int balance = 10000;
        String pin;

        System.out.println("Welcome to the Bank Account Manager");

        // Get account number
        System.out.println("Please enter your account number:");
        account = input.nextLine();

        // Get PIN
        System.out.println("Please enter your 4-digit PIN:");
        pin = input.nextLine();

        // Validate PIN and account number
        try {
            pinAccountChecker(pin, account);
        } catch (InvalidPasswordException | InvalidAccountException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }

        // Main loop for multiple operations
        while (true) {
            System.out.println("\nWhich service would you like to use?");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("WITHDRAW");
                    System.out.print("Please enter the amount to withdraw: ");
                    int withdraw = input.nextInt();

                    try {
                        withdrawChecker(withdraw, balance);
                        balance -= withdraw;
                        System.out.println("Withdrawal successful. New balance: " + balance);
                    } catch (WithdrawalException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("DEPOSIT");
                    System.out.print("Please enter the amount to deposit: ");
                    int deposit = input.nextInt();

                    try {
                        depositChecker(deposit);
                        balance += deposit;
                        System.out.println("Deposit of " + deposit + " was successful.");
                        System.out.println("New balance: " + balance);
                    } catch (DepositException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("BALANCE");
                    System.out.println("Current balance: " + balance);
                    break;

                case 4:
                    System.out.println("Thank you for using the Bank Account Manager. Goodbye!");
                    input.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    // Validation methods
    static void pinAccountChecker(String pin, String account) throws InvalidPasswordException, InvalidAccountException {
        if (pin.length() != 4) {
            throw new InvalidPasswordException("Invalid PIN. Please enter a 4-digit PIN.");
        }
        if (account.length() != 10) {
            throw new InvalidAccountException("Invalid account number. Please enter a 10-digit account number.");
        }
    }

    static void withdrawChecker(int withdraw, int balance) throws WithdrawalException {
        if (withdraw <= 0) {
            throw new WithdrawalException("Withdrawal amount must be positive.");
        }
        if (withdraw > balance) {
            throw new WithdrawalException("Insufficient funds. You do not have enough money in your account.");
        }
    }

    static void depositChecker(int deposit) throws DepositException {
        if (deposit <= 0) {
            throw new DepositException("Invalid deposit amount. Please enter a positive number.");
        }
    }
}
