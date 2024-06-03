package activities;

import java.util.Scanner;

/**
 *
 * @author david
 */
public class ConvertNumber {    

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            do {

                System.out.print("Enter a decimal value (0 to exit): ");
                int num = scanner.nextInt();
                if (num != 0) {
                    System.out.printf("%s\n", "***** Result *****");
                    System.out.printf("Number: %s\n", getBaseValue(num, 10));
                    System.out.printf("To Bin: %s\n", getBaseValue(num,  2));
                    System.out.printf("To Oct: %s\n", getBaseValue(num,  8));
                    System.out.printf("To Hex: %s\n", getBaseValue(num, 16));
                } else {
                    System.out.println("Tapos na po!");
                    break;
                }

            } while (true);

        }

    }
    
    static String getBaseValue(int number, int base) {

        StringBuilder sb = new StringBuilder();      
        int quotient, toHexDigit;
        
        while (number != 0) {
            quotient = number % base;
            if (quotient > 9) {
                toHexDigit = 'A' + (quotient - 10);
                sb.append((char) toHexDigit);
            } else {
                sb.append(quotient);
            }
            number /= base;                                   
        }
        
        return sb.reverse().toString();
    }
   
}
