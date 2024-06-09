package activities;

import java.util.Scanner;

/**
 *
 * @author david
 */
public class ConvertNumber {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            do { // to run at least one 

                System.out.print("Enter a decimal value (0 to exit): "); 
                int num = scanner.nextInt();                         
                
                if (num != 0) {
                    System.out.printf("%s\n", "***** Result *****");
                    System.out.printf("Number: %s\n", getBaseValue(10, num));
                    System.out.printf("To Bin: %s\n", getBaseValue( 2, num));
                    System.out.printf("To Oct: %s\n", getBaseValue( 8, num));
                    System.out.printf("To Hex: %s\n", getBaseValue(16, num));
                } else {
                    System.out.println("Tapos na po!");
                    break;  // break the infinite loop
                }

            } while (true); // always true

        }

    }
    
    /* static String getBaseValue(int base, int value) {

        String alphaDigits = "0123456789ABCDEF";
        int idxRem, idxCap = 32; // idx: remainder, capacity
        char[] sb = new char[idxCap];

        while (value != 0) {
            idxRem = value % base; // remainder    
            sb[--idxCap] = alphaDigits.charAt(idxRem);
            value /= base;
        }                              

        return String.valueOf(sb);                
    } */

    static String getBaseValue(int base, int value) {

        StringBuilder sb = new StringBuilder();
        int remainder, toHexDigit;

        while (value != 0) {
            remainder = value % base; // get remainder    
            if (remainder > 9) {
                toHexDigit = 'A' + (remainder - 10); // alternative to switch-case
                sb.append((char) toHexDigit);
            } else {
                sb.append(remainder);
            }
            value /= base; // get quotient
        }                              

        return sb.reverse().toString();
    }
}
