package com.miusicmaker.musmk.validation;

public class AuthValidator {

    public static boolean isValidEmail(String email){

        char[] specialChrs = new char[]{'@', '.'};

        char[] emailCh = email.toCharArray();

        int countOf2 = 0;
        int countOfdot = 0;

        for (int i = 0; i < emailCh.length; i++) {

            if (emailCh[i] == specialChrs[0]){

                countOf2++;

            }
            if (emailCh[i] == specialChrs[1]){

                countOfdot++;

            }

        }

        return countOf2 > 0 && countOfdot > 0;

    }

}
