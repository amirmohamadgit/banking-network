package services;

import java.lang.classfile.ClassFile;

public class FinancialOperations {


    private static void satnaMoneyTransfer(long amount) {
        Validate.validateMoneyTransfer(amount, 50_000_000, 200_000_000, "satna transfer");

    }

    private static void payaMoneyTransfer(long amount) {
        Validate.validateMoneyTransfer(amount, 15_000_000, 50_000_000, "paya transfer");
    }

    private static void normalMoneyTransfer(long amount) {
        Validate.validateMoneyTransfer(amount, 5000, 15_000_000, "normal transfer");

    }

    private static int getNormalFee(long amount) {
        if (amount <= 10_000_000) return 720;
        else {
            amount -= 10_000_000;
            return (int) (amount / 1_000_000 * 100) + 1000;
        }
    }

    private static int getPayaFee(long amount) {
        return (int)((int) amount * 0.001);
    }

    private static int getSatnaFee(long amount){
        return (int)((int) amount * 0.002);
    }
}
