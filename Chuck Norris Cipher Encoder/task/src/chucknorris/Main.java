package chucknorris;

import java.util.Scanner;

public class Main {

    public static int convertBinIntToDecInt(int binNum) {
        int result = 0;
        int bin = 1;
        while (binNum > 0) {
            result += (binNum % 10) * bin;
            bin *= 2;
            binNum /= 10;
        }
        return result;
    }

    public static void encoder() {
        System.out.println("Input string:");
        Scanner s = new Scanner(System.in);

        String str = s.nextLine();
        char[] arrayChars = str.toCharArray();

        System.out.println("Encoded string:");
        StringBuilder binaryDigit = new StringBuilder();

        for (char ch: arrayChars) {
            String digit = Integer.toBinaryString(ch);
            int score = Integer.parseInt(digit);
            binaryDigit.append(String.format("%07d", score));
        }
        String temp = binaryDigit.toString();
        char[] binArrCh = temp.toCharArray();
        char print = '0';
        boolean flag = false;
        for (int i = 0; i < binArrCh.length; i++) {
            if (!flag && binArrCh[0] == '0') {
                System.out.print("00 ");
                flag = true;
            } else if (!flag && binArrCh[0] == '1') {
                System.out.print("0 ");
                flag = true;
            }
            if ((print == '1') && (binArrCh[i] == '0')) {
                print = '0';
                System.out.print(" 00 ");
            } else if (print == '0' && binArrCh[i] == '1') {
                print = '1';
                if (i != 0) System.out.print(" 0 ");
            }
            if (binArrCh[i] == print) System.out.print("0");
        }
        System.out.println();
    }


    public static void decoder() {
        System.out.println("Input encoded string:");
        Scanner s = new Scanner(System.in);

        String str = s.nextLine();
        char[] arrayChars = str.toCharArray();
        StringBuilder tempNum = new StringBuilder();
        int countZero = 0;
        int countEmpty = 0;
        boolean errFlag = false;
        for (char ch: arrayChars) {
            if (ch == ' ' || ch == '0') {
                if (ch == ' ') {
                    countEmpty++;
                    if (countEmpty % 2 == 0) countZero = 0;
                }
                if (countEmpty % 2 == 0) {
                    if (ch == '0') countZero++;
                }
                if (countZero < 3) {
                    if (countEmpty % 2 != 0) {
                        if (countZero == 1 && ch == '0') {
                            tempNum.append('1');
                        } else if (countZero == 2 && ch == '0') {
                            tempNum.append('0');
                        }
                    }
                } else {
                    errFlag = true;
//                    System.out.println("The first block of each sequence is not 0 or 00");
                    break;
                }
            } else {
                errFlag = true;
//                System.out.println("The encoded message includes characters other than 0 or spaces.");
                break;
            }
        }
        if (tempNum.length() % 7 == 0) {
            if (!errFlag) {
                StringBuilder encryptBinaryDigit = new StringBuilder();
                for (int i = 0; i < tempNum.length() / 7; i++) {
                    int tempInt = Integer.parseInt(tempNum, i * 7, i * 7 + 7, 10);
                    char temp = (char) convertBinIntToDecInt(tempInt);
                    encryptBinaryDigit.append(temp);
                }
                System.out.println("Decoded string:");
                System.out.println(encryptBinaryDigit);
            }
        } else {
            errFlag = true;
//            System.out.println("The length of the decoded binary string is not a multiple of 7");
        }
        if (errFlag) System.out.println("Encoded string is not valid.");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String itemMenu = "run";
        while (!"exit".equals(itemMenu)) {
            System.out.println("Please input operation (encode/decode/exit):");
            itemMenu = scanner.nextLine();
            if ("encode".equals(itemMenu)) {
                encoder();
            } else if ("decode".equals(itemMenu)) {
                decoder();
            } else if ("exit".equals(itemMenu)) {
                System.out.println("Bye!");
            } else {
                System.out.printf("There is no '%s' operation\n", itemMenu);
            }
        }
    }
}