import java.util.*;

class ConvertBinary {

      // user validation. only accepts binary number 1 and 0
      private static boolean isValidBinary(String binary) {
            for (char c : binary.toCharArray()) {
                  if (c != '0' && c != '1' && c != '.') {
                        return false;
                  }
            }
            return true;
      }

      public static void performBinary() {
            String input;
            do {
                  String binary = getBinaryInput();
                  if (isValidBinary(binary)) {
                        printAllBinary(binary);
                  } else {
                        System.out.println("Invalid input: binary number must only contain 0 and 1");
                        performBinary();
                        // Note: This will result in nested calls to performBinary() if the user
                        // repeatedly inputs an invalid binary number.
                  }
                  do {
                        System.out.println("Do you want to perform the conversion again? (Y/N)");
                        input = NumberSystem.sc.nextLine();
                        if (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")) {
                              System.out.println("Invalid input. Please enter Y or N.");
                        }
                  } while (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N"));
            } while (!input.equalsIgnoreCase("N"));
            NumberSystem.MAIN_MENU();
      }

      private static String getBinaryInput() {

            System.out.print("Enter Binary Number: \n");
            String binary = NumberSystem.sc.nextLine();
            return binary;
      }

      private static int binaryToDecimal(String binary) {

            // BINARY TO OCTAL, BINARY TO DECIMAL, BINARY TO HEXADECIMAL=
            int decimal = 0;
            double fraction = 0.0;
            boolean hasFraction = false;
            // Check if the input string contains a decimal point
            int pointIndex = binary.indexOf('.');
            if (pointIndex != -1) {
                  // Extract the integer and fractional parts
                  String intPart = binary.substring(0, pointIndex);
                  String fracPart = binary.substring(pointIndex + 1);
                  binary = intPart;
                  hasFraction = true;

                  // Convert the fractional part to decimal
                  for (int i = 0; i < fracPart.length(); i++) {
                        char c = fracPart.charAt(i);
                        int digit = Character.digit(c, 2);
                        fraction += digit / Math.pow(2, i + 1);
                  }
            }

            // Convert the integer part to decimal
            for (int i = 0; i < binary.length(); i++) {
                  char c = binary.charAt(i);
                  int digit = Character.digit(c, 2);
                  decimal = 2 * decimal + digit;
            }

            double result = decimal + fraction;
            System.out.println("Decimal number: " + result);
            return pointIndex;
      }

      private static String binaryToOctal(String binary) {

            int decimal = 0;
            double fraction = 0.0;
            boolean hasFraction = false;

            // Check if the input string contains a decimal point
            int pointIndex = binary.indexOf('.');
            if (pointIndex != -1) {
                  // Extract the integer and fractional parts
                  String intPart = binary.substring(0, pointIndex);
                  String fracPart = binary.substring(pointIndex + 1);
                  binary = intPart;
                  hasFraction = true;

                  // Convert the fractional part to decimal
                  for (int i = 0; i < fracPart.length(); i++) {
                        char c = fracPart.charAt(i);
                        int digit = Character.digit(c, 2);
                        fraction += digit / Math.pow(2, i + 1);
                  }
            }

            // Convert the integer part to decimal
            for (int i = 0; i < binary.length(); i++) {
                  char c = binary.charAt(i);
                  int digit = Character.digit(c, 2);
                  decimal = 2 * decimal + digit;
            }

            // Convert the decimal to octal
            String octalIntegral = "";
            while (decimal > 0) {
                  int rem = decimal % 8;
                  octalIntegral = rem + octalIntegral;
                  decimal = decimal / 8;
            }

            // Convert the fractional part to octal
            String octalFractional = "";
            if (hasFraction) {
                  fraction *= 8;
                  for (int i = 0; i < 5; i++) {
                        int digit = (int) fraction;
                        octalFractional += digit;
                        fraction -= digit;
                        fraction *= 8;
                  }
            }

            // Combine the integral and fractional parts in octal
            String octal = octalIntegral;
            if (hasFraction) {
                  octal += "." + octalFractional;
            }

            return octal;
      }

      private static String binaryToHexadecimal(String binary) {

            String digits = "0123456789ABCDEF";
            int decimal = 0;
            double fraction = 0.0;
            boolean hasFraction = false;

            // Check if the input string contains a decimal point
            int pointIndex = binary.indexOf('.');
            if (pointIndex != -1) {
                  // Extract the integer and fractional parts
                  String intPart = binary.substring(0, pointIndex);
                  String fracPart = binary.substring(pointIndex + 1);
                  binary = intPart;
                  hasFraction = true;

                  // Convert the fractional part to decimal
                  for (int i = 0; i < fracPart.length(); i++) {
                        char c = fracPart.charAt(i);
                        int digit = Character.digit(c, 2);
                        fraction += digit / Math.pow(2, i + 1);
                  }
            }

            // Convert the integer part to decimal
            for (int i = 0; i < binary.length(); i++) {
                  char c = binary.charAt(i);
                  int digit = Character.digit(c, 2);
                  decimal = 2 * decimal + digit;
            }

            // Convert the decimal to hexadecimal
            String hexIntegral = "";
            while (decimal > 0) {
                  int rem = decimal % 16;
                  hexIntegral = digits.charAt(rem) + hexIntegral;
                  decimal = decimal / 16;
            }

            // Convert the fractional part to hexadecimal
            String hexFractional = "";
            if (hasFraction) {
                  for (int i = 0; i < 5; i++) {
                        fraction *= 16;
                        int digit = (int) fraction;
                        hexFractional += digits.charAt(digit);
                        fraction -= digit;
                        if (digit == 0) {
                              break;
                        }
                  }
                  // temporary fix for trailing zeros
                  // this only fix the hexadecimal trailing zeros
                  if (hexFractional.isEmpty()) {
                        hexFractional = "0";
                  }
                  hexFractional = hexFractional.replaceAll("0*$", "");
                  if (hexFractional.isEmpty()) {
                        hexFractional = "0";
                  }
            }

            // Combine the integral and fractional parts in hexadecimal
            String hex = hexIntegral;
            if (hasFraction) {
                  hex += "." + hexFractional;
            }

            return hex;
      }

      private static void printAllBinary(String binary) {

            System.out.println("================| Binary |================\n");
            System.out.println("Octal number: " + binaryToOctal(binary) + "");
            binaryToOctal(binary);
            binaryToDecimal(binary);
            System.out.println("Hexadecimal number: " + binaryToHexadecimal(binary) + "");
            binaryToHexadecimal(binary);
      }

}

class ConvertOctal {

      public static void performOctal() {
            boolean done = false;

            while (!done) {
                  String octal = getOctalInput();
                  printAllOctal(octal);

                  while (true) {
                        System.out.print("Do you want to convert another octal number? (Y/N): ");
                        String choice = NumberSystem.sc.nextLine().toUpperCase();
                        if (choice.equals("N")) {
                              done = true;
                              NumberSystem.MAIN_MENU();
                              break;
                        } else if (choice.equals("Y")) {
                              break;
                        } else {
                              System.out.println("Invalid input. Please enter Y or N.");
                        }
                  }
            }
      }

      private static String getOctalInput() {
            while (true) {
                  System.out.print("Enter Octal Number: \n");
                  String octal = NumberSystem.sc.nextLine();

                  // Check if the input is a valid octal number
                  boolean isValidOctal = true;
                  boolean hasDecimalPoint = false;
                  for (int i = 0; i < octal.length(); i++) {
                        char c = octal.charAt(i);
                        if (c == '.') {
                              if (hasDecimalPoint) {
                                    isValidOctal = false;
                                    break;
                              }
                              hasDecimalPoint = true;
                        } else if (c < '0' || c > '7') {
                              isValidOctal = false;
                              break;
                        }
                  }

                  if (isValidOctal) {
                        return octal;
                  } else {
                        System.out.println("Invalid octal number. Please try again.");
                  }
            }
      }

      private static String octalToBinary(String octal) {

            int integralPart = 0;
            double fractionalPart = 0.0;
            boolean hasFraction = false;

            // Check if the input string contains a decimal point
            int pointIndex = octal.indexOf('.');
            if (pointIndex != -1) {
                  // Extract the integer and fractional parts
                  String intPart = octal.substring(0, pointIndex);
                  String fracPart = octal.substring(pointIndex + 1);
                  octal = intPart;
                  hasFraction = true;

                  // Convert the fractional part to decimal
                  for (int i = 0; i < fracPart.length(); i++) {
                        char c = fracPart.charAt(i);
                        int digit = Character.digit(c, 8);
                        fractionalPart += digit / Math.pow(8, i + 1);
                  }
            }

            // Convert the integral part to decimal
            for (int i = 0; i < octal.length(); i++) {
                  char c = octal.charAt(i);
                  int digit = Character.digit(c, 8);
                  integralPart = 8 * integralPart + digit;
            }

            // Convert the integral part to binary
            String binaryIntegral = Integer.toBinaryString(integralPart);

            // Convert the fractional part to binary
            String binaryFractional = "";
            if (hasFraction) {
                  fractionalPart *= 2;
                  while (fractionalPart != 0) {
                        if (fractionalPart >= 1) {
                              binaryFractional += "1";
                              fractionalPart -= 1;
                        } else {
                              binaryFractional += "0";
                        }
                        fractionalPart *= 2;
                  }
            }

            // Combine the integral and fractional parts in binary
            String binary = binaryIntegral;
            if (hasFraction) {
                  binary += "." + binaryFractional;
            }

            return binary;
      }

      private static double octalToDecimal(String octal) {
            int pointIndex = octal.indexOf('.');
            int integralPart = 0;
            double fractionalPart = 0.0;
            boolean hasFraction = false;

            // Check if the input string contains a decimal point
            if (pointIndex != -1) {
                  // Extract the integer and fractional parts
                  String intPart = octal.substring(0, pointIndex);
                  String fracPart = octal.substring(pointIndex + 1);
                  octal = intPart;
                  hasFraction = true;

                  // Convert the fractional part to decimal
                  for (int i = 0; i < fracPart.length(); i++) {
                        char c = fracPart.charAt(i);
                        int digit = Character.digit(c, 8);
                        fractionalPart += digit / Math.pow(8, i + 1);
                  }
            }

            // Convert the integral part to decimal
            for (int i = 0; i < octal.length(); i++) {
                  char c = octal.charAt(i);
                  int digit = Character.digit(c, 8);
                  integralPart = 8 * integralPart + digit;
            }

            double decimal = integralPart + fractionalPart;

            return decimal;
      }

      private static String octalToHexadecimal(String octal) {

            String digits = "0123456789ABCDEF";
            int integralPart = 0;
            double fractionalPart = 0.0;
            boolean hasFraction = false;

            // Check if the input string contains a decimal point
            int pointIndex = octal.indexOf('.');
            if (pointIndex != -1) {
                  // Extract the integer and fractional parts
                  String intPart = octal.substring(0, pointIndex);
                  String fracPart = octal.substring(pointIndex + 1);
                  octal = intPart;
                  hasFraction = true;

                  // Convert the fractional part to decimal
                  for (int i = 0; i < fracPart.length(); i++) {
                        char c = fracPart.charAt(i);
                        int digit = Character.digit(c, 8);
                        fractionalPart += digit / Math.pow(8, i + 1);
                  }
            }

            // Convert the integral part to decimal
            for (int i = 0; i < octal.length(); i++) {
                  char c = octal.charAt(i);
                  int digit = Character.digit(c, 8);
                  integralPart = 8 * integralPart + digit;
            }

            // Convert the integral part to hexadecimal
            String hexIntegral = Integer.toHexString(integralPart);

            // Convert the fractional part to hexadecimal
            String hexFractional = "";
            if (hasFraction) {
                  fractionalPart *= 16;
                  while (fractionalPart != 0) {
                        int digit = (int) fractionalPart;
                        hexFractional += digits.charAt(digit);
                        fractionalPart -= digit;
                        fractionalPart *= 16;
                  }
            }

            // Combine the integral and fractional parts in hexadecimal
            String hex = hexIntegral;
            if (hasFraction) {
                  hex += "." + hexFractional;
            }

            return hex;
      }

      static void printAllOctal(String octal) {
            System.out.println("\n================| Octal |================\n");
            System.out.println("Binary number: " + octalToBinary(octal) + "");
            octalToBinary(octal);
            System.out.println("Decimal number: " + octalToDecimal(octal) + "");
            octalToDecimal(octal);
            System.out.println("Hexadecimal number: " + octalToHexadecimal(octal.toUpperCase()) + "");
            octalToHexadecimal(octal);

      }

}

class ConvertDecimal {

      public static void performDecimal() {
            while (true) {
                  double decimal = getDecimalInput();
                  printAllBinary(decimal);

                  while (true) {
                        System.out.println("Do you want to convert again? (Y/N)");
                        String choice = NumberSystem.sc.nextLine();
                        if (choice.equalsIgnoreCase("N")) {
                              NumberSystem.MAIN_MENU();
                        } else if (choice.equalsIgnoreCase("Y")) {
                              break;
                        } else {
                              System.out.println("Invalid input. Please enter Y or N.");
                        }
                  }
            }
      }

      private static double getDecimalInput() {
            while (true) {
                  System.out.println("Enter Decimal Number: ");
                  String input = NumberSystem.sc.nextLine();
                  if (input.matches("-?[0-9]+(\\.[0-9]+)?")) {
                        try {
                              double decimal = Double.parseDouble(input);
                              return decimal;
                        } catch (NumberFormatException e) {
                              System.out.println("Invalid input. Please enter a valid decimal number.");
                        }
                  } else {
                        System.out.println("Invalid input. Please enter a valid decimal number.");
                  }
            }
      }

      private static String decimalToBinary(double decimal) {

            // Split the decimal into the integral and fractional parts
            int integralPart = (int) decimal;
            double fractionalPart = decimal - integralPart;

            // Convert the integral part to binary
            String integralBinary = "";
            while (integralPart > 0) {
                  int remainder = integralPart % 2;
                  integralBinary = remainder + integralBinary;
                  integralPart /= 2;
            }
            if (integralBinary.isEmpty()) {
                  integralBinary = "0";
            }

            // Convert the fractional part to binary
            String fractionalBinary = "";
            while (fractionalPart > 0) {
                  if (fractionalBinary.length() >= 32) {
                        // Maximum number of fractional digits exceeded
                        break;
                  }
                  fractionalPart *= 2;
                  int digit = (int) fractionalPart;
                  fractionalPart -= digit;
                  fractionalBinary += digit;
            }
            if (!fractionalBinary.isEmpty()) {
                  fractionalBinary = "." + fractionalBinary;
            }

            return integralBinary + fractionalBinary;
      }

      private static String decimalToOctal(double decimal) {
            // Split the decimal into the integral and fractional parts
            int integralPart = (int) decimal;
            double fractionalPart = decimal - integralPart;

            // Convert the integral part to octal
            String integralOctal = "";
            while (integralPart > 0) {
                  int remainder = integralPart % 8;
                  integralOctal = remainder + integralOctal;
                  integralPart /= 8;
            }
            if (integralOctal.isEmpty()) {
                  integralOctal = "0";
            }

            // Convert the fractional part to octal
            String fractionalOctal = "";
            while (fractionalPart > 0) {
                  if (fractionalOctal.length() >= 32) {
                        // Maximum number of fractional digits exceeded
                        break;
                  }
                  fractionalPart *= 8;
                  int digit = (int) fractionalPart;
                  fractionalPart -= digit;
                  fractionalOctal += digit;
            }
            if (!fractionalOctal.isEmpty()) {
                  fractionalOctal = "." + fractionalOctal;
            }

            return integralOctal + fractionalOctal;

      }

      private static String decimalToHexadecimal(double decimal) {
            // Split the decimal into the integral and fractional parts
            int integralPart = (int) decimal;
            double fractionalPart = decimal - integralPart;

            // Convert the integral part to hexadecimal
            String integralHex = "";
            while (integralPart > 0) {
                  int remainder = integralPart % 16;
                  integralHex = getHexDigit(remainder) + integralHex;
                  integralPart /= 16;
            }
            if (integralHex.isEmpty()) {
                  integralHex = "0";
            }

            // Convert the fractional part to hexadecimal
            String fractionalHex = "";
            while (fractionalPart > 0) {
                  if (fractionalHex.length() >= 32) {
                        // Maximum number of fractional digits exceeded
                        break;
                  }
                  fractionalPart *= 16;
                  int digit = (int) fractionalPart;
                  fractionalPart -= digit;
                  fractionalHex += getHexDigit(digit);
            }
            if (!fractionalHex.isEmpty()) {
                  fractionalHex = "." + fractionalHex;
            }

            return integralHex + fractionalHex;
      }

      private static String getHexDigit(int digit) {
            String digits = "0123456789ABCDEF";
            if (digit >= 0 && digit < 16) {
                  return String.valueOf(digits.charAt(digit));
            }
            return "0";
      }

      private static void printAllBinary(double decimal) {
            System.out.println("================| Binary |================\n");
            String binary = decimalToBinary(decimal);
            // System.out.println("Decimal number: " + decimal + "");
            System.out.println("Binary number: " + binary);
            System.out.println("Octal number: " + decimalToOctal(decimal));
            System.out.println("Hexadecimal number: " + decimalToHexadecimal(decimal).toUpperCase());
      }

}

class ConvertHexadecimal {

      private static String digits = "0123456789ABCDEF";

      public static void performHexadecimal() {
            boolean tryAgain = true;
            do {
                  String hex = getHexadecimalInput();
                  printAllHexadecimal(hex);

                  while (true) {
                        System.out.print("Do you want to try again? (Y/N): ");
                        String choice = NumberSystem.sc.nextLine().toUpperCase();
                        if (choice.equals("N")) {
                              tryAgain = false;
                              NumberSystem.MAIN_MENU();
                              break;
                        } else if (choice.equals("Y")) {
                              break;
                        } else {
                              System.out.println("Invalid input. Please enter Y or N.");
                        }
                  }
            } while (tryAgain);
      }

      private static String getHexadecimalInput() {
            System.out.print("Enter Hexadecimal Number: \n");
            String hex = NumberSystem.sc.nextLine().toUpperCase();

            // Validate the input
            while (!hex.matches("[0-9A-F]+(\\.[0-9A-F]+)?")) {
                  System.out.println(
                              "Invalid input. Please enter a hexadecimal number with an optional fractional part.");
                  hex = NumberSystem.sc.nextLine().toUpperCase();
            }
            return hex;
      }

      // * hexa to decimal *//
      private static int hexadecimalToDecimal(String hex) {

            // HEXADECIMAL TO BINARY, HEXADECIMAL TO OCTAL, HEXADECIMAL TO DECIMAL
            int decimal = 0;
            double fraction = 0.0;
            boolean hasFraction = false;
            // Check if the input string contains a decimal point
            int pointIndex = hex.indexOf('.');
            if (pointIndex != -1) {
                  // Extract the integer and fractional parts
                  String intPart = hex.substring(0, pointIndex);
                  String fracPart = hex.substring(pointIndex + 1);
                  hex = intPart;
                  hasFraction = true;

                  // Convert the fractional part to decimal
                  for (int i = 0; i < fracPart.length(); i++) {
                        char c = fracPart.charAt(i);
                        int digit = Character.digit(c, 16);
                        fraction += digit / Math.pow(16, i + 1);
                  }
            }

            // Convert the integer part to decimal
            hex = hex.toUpperCase();
            for (int i = 0; i < hex.length(); i++) {
                  char c = hex.charAt(i);
                  int digit = digits.indexOf(c);
                  decimal = 16 * decimal + digit;
            }

            // Add the fractional part to the decimal result
            double result = decimal + fraction;

            // Print the result
            if (hasFraction) {
                  // when the input has fraction part
                  System.out.println("Decimal number: " + result);
            } else {
                  // if the input is only integral part
                  System.out.println("Decimal number: " + decimal);
            }
            return pointIndex;
      }

      // * hexa to binary *//
      private static String hexadecimalToBinary(String hex) {

            int decimal = 0;
            double fractional = 0.0;

            // Check if the input string contains a decimal point
            int pointIndex = hex.indexOf('.');
            if (pointIndex != -1) {
                  // Extract the integer and fractional parts
                  String intPart = hex.substring(0, pointIndex);
                  String fracPart = hex.substring(pointIndex + 1);
                  hex = intPart;
                  // fract = fracPart;

                  // Convert the fractional part to decimal
                  for (int i = 0; i < fracPart.length(); i++) {
                        char c = fracPart.charAt(i);
                        int digit = digits.indexOf(c);
                        fractional += digit / Math.pow(16, i + 1);
                  }
            }

            // convert the integer part to decimal
            for (int i = 0; i < hex.length(); i++) {
                  char c = hex.charAt(i);
                  int digit = digits.indexOf(c);
                  decimal = 16 * decimal + digit;
            }

            // Combine the integral and fractional binary parts with a decimal point
            String integralBinary = Integer.toBinaryString(decimal);
            String fractionalBinary = convertFractionalToBinary(fractional);
            return integralBinary + (fractionalBinary.isEmpty() ? "" : "." + fractionalBinary);

      }

      private static String convertFractionalToBinary(double fractional) {
            StringBuilder binaryBuilder = new StringBuilder();
            while (fractional > 0) {
                  fractional *= 2;
                  if (fractional >= 1) {
                        binaryBuilder.append("1");
                        fractional -= 1;
                  } else {
                        binaryBuilder.append("0");
                  }

            }
            return binaryBuilder.toString();
      }

      // * hexa to octal *//
      private static String hexadecimalToOctal(String hex) {
            int decimal = 0;
            double fractional = 0.0;

            // Check if the input string contains a decimal point
            int pointIndex = hex.indexOf('.');
            if (pointIndex != -1) {
                  // Extract the integer and fractional parts
                  String intPart = hex.substring(0, pointIndex);
                  String fracPart = hex.substring(pointIndex + 1);
                  hex = intPart;

                  // Convert the fractional part to decimal
                  for (int i = 0; i < fracPart.length(); i++) {
                        char c = fracPart.charAt(i);
                        int digit = digits.indexOf(c);
                        fractional += digit / Math.pow(16, i + 1);
                  }
            }

            // convert the integer part to decimal
            for (int i = 0; i < hex.length(); i++) {
                  char c = hex.charAt(i);
                  int digit = digits.indexOf(c);
                  decimal = 16 * decimal + digit;
            }

            // Convert decimal to octal
            String octal = Integer.toOctalString(decimal);

            // Combine the integral and fractional octal parts with a decimal point
            if (fractional > 0.0) {
                  octal += ".";
                  for (int i = 0; i < 6; i++) {
                        fractional *= 8.0;
                        int digit = (int) fractional;
                        octal += digits.charAt(digit);
                        fractional -= digit;
                        if (fractional == 0.0) {
                              break;
                        }
                  }
            }
            return octal;
      }

      private static void printAllHexadecimal(String hex) {

            System.out.println("================| Hexadecimal |================\n");
            String binary = hexadecimalToBinary(hex);
            // System.out.println("Hexadecimal number: " + hex);
            System.out.println("Binary number: " + binary);
            hexadecimalToDecimal(hex);
            String octal = hexadecimalToOctal(hex);
            System.out.println("Octal number: " + octal);

      }
}

public class NumberSystem {

      static Scanner sc = new Scanner(System.in);

      public static void MAIN_MENU() {

            System.out.println("================| Select from the choices |================\n");
            System.out.println("\t\t[A] Binary to other number system");
            System.out.println("\t\t[B] Octal to other number system");
            System.out.println("\t\t[C] Decimal to other number system");
            System.out.println("\t\t[D] Hexadecimal to other number system");
            System.out.println("\t\t[E] Exit");
            System.out.print("ENTER INPUT: ");
            String userChoices = sc.nextLine().toUpperCase();

            if (userChoices.equals("A")) {
                  ConvertBinary objBinary = new ConvertBinary();
                  objBinary.performBinary();
            } else if (userChoices.equals("B")) {
                  ConvertOctal objOctal = new ConvertOctal();
                  objOctal.performOctal();
            } else if (userChoices.equals("C")) {
                  ConvertDecimal objDecimal = new ConvertDecimal();
                  objDecimal.performDecimal();
            } else if (userChoices.equals("D")) {
                  ConvertHexadecimal objHexadecimal = new ConvertHexadecimal();
                  objHexadecimal.performHexadecimal();
            } else if (userChoices.equals("E")) {
                  END_PROGRAM();
            } else {
                  System.out.println("Invalid Input!");
                  MAIN_MENU();
            }

      }

      static void END_PROGRAM() {

            System.out.println("\n\tThank you for using the program!\n");
            sc.close();

      }

      public static void main(String[] args) {
            MAIN_MENU();
      }
}
