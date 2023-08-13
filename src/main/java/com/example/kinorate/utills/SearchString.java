package com.example.kinorate.utills;

public class SearchString {
    /**
     * Operates with string from search-user servlet, to use it in searching by name and last name
     * @param str - parameter from request (search)
     * @return array with 2 elements: name and last name. Last name could be empty
     */

    public static String[] divideString(String str) {
        str = str.trim();
        String[] arr = new String[2];
        if (!str.contains(" ")) {
            arr[0] = str;
            arr[1] = "";
        }
        else {
            String str1 = str.substring(0, str.indexOf(" "));
            String str2 = str.substring(str.indexOf(" "));

            arr[0] = str1;
            arr[1] = str2;
        }

        return arr;
    }
}
