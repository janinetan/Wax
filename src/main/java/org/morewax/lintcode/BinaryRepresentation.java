package org.morewax.lintcode;

/**
 * Problem
 * Binary Representation (http://www.lintcode.com/en/problem/binary-representation/)
 *  Binary Representation
 *  Given a (decimal - e.g. 3.72) number that is passed in as a string, return the binary representation that is passed in
 *  as a string. If the fractional part of the number can not be represented accurately in binary with at most 32
 *  characters, return ERROR.
 *  Example
 *   For n = "3.72", return "ERROR".
 *   For n = "3.5", return "11.1".
 * Created by Bing on 12/28/2015.
 */
public class BinaryRepresentation {
    /**
     *@param str: Given a decimal number that is passed in as a string
     *@return: A string
     */
    public String binaryRepresentation(String str) {
        int intPart = Integer.parseInt(str.substring(0, str.indexOf(".")));
        double decPart = Double.parseDouble(str.substring(str.indexOf(".")));

        String intString = "";

        if (intPart == 0) {
            intString = "0";
        } else {
            while (intPart > 0) {
                intString = intPart%2 + intString;
                intPart = intPart/2;
            }
        }

        StringBuffer decString = new StringBuffer();
        while (decPart > 0) {
            if (decString.length() > 32) {
                return "ERROR";
            }

            double r = decPart*2;
            if (r >= 1) {
                decString.append("1");
                decPart = r-1;
            } else {
                decString.append("0");
                decPart = r;
            }
        }

        if (decString.length() == 0) {
            return intString;
        } else {
            return intString + "." + decString.toString();
        }
    }

    public static void main(String[] args) {
        final BinaryRepresentation s = new BinaryRepresentation();

        System.out.println(s.binaryRepresentation("3.72"));
        System.out.println(s.binaryRepresentation("3.5"));
        System.out.println(s.binaryRepresentation("1.0"));
    }
}
