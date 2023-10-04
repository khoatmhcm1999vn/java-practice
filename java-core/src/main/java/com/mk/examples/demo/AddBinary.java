package com.mk.examples.demo;

public class AddBinary {

    public static void main(String[] args) {
        String a = "11", b = "1";
        System.out.println(addBinary(a, b));
    }

    /**
     * Given two binary strings a and b, return their sum as a binary string.
     * a and b consist only of '0' or '1' characters.
     *
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {
        StringBuilder sumResultInBinary = new StringBuilder();
        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;
        int carry = 0;

        while (aIndex >= 0 || bIndex >= 0) {
            int sum = carry;

            if (aIndex >= 0) {
                // because a and b consist only of '0' or '1' characters
                // so we will always get 0 or 1 in the system 10  (with - '0');
                sum += a.charAt(aIndex) - '0';
            }

            if (bIndex >= 0) {
                sum += b.charAt(bIndex) - '0';
            }

            // sum always from 0 -> 3
            // 0= 00, 1 = 01, 2 = 10,  3 = 11,... so we realize that we could % 2 to get the tail
            // if sum == 2, it means 1 + 1 = 11, carry 1 (sum / 2) for the next move
            sumResultInBinary.append(sum % 2);
            carry = sum / 2;
            aIndex--;
            bIndex--;
        }

        if (carry == 1) {
            sumResultInBinary.append(carry);
        }

        return sumResultInBinary.reverse().toString();
    }
}
