package org.morewax.meetqun;

/**
 * Created by Bing on 12/6/2015.
 */
public class PatternMatch {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();

        if (p.length() >=2 && p.charAt(1) == '*') {
            for (int i = 0; i < s.length() && p.charAt(0) == s.charAt(i); ++i) {
                if (isMatch(s.substring(i+1), p.substring(2))) {
                    return true;
                }
            }

            return isMatch(s, p.substring(2));
        }

        return !s.isEmpty() && s.charAt(0) == p.charAt(0) && isMatch(s.substring(1), p.substring(1));
    }

    public static void main(String[] args) {
        PatternMatch pm = new PatternMatch();

        System.out.println(pm.isMatch("aa", "a"));
        System.out.println(pm.isMatch("aa", "a*"));
        System.out.println(pm.isMatch("ab", "a*"));
        System.out.println(pm.isMatch("aab", "c*a*b"));
        System.out.println(pm.isMatch("ab", "a*b"));
        System.out.println(pm.isMatch("b", "a*b"));
    }
}
