package net.whynotjava.lynel.Util;

import java.util.ArrayList;

public class ArrayHelp {
    public static boolean inArray(String[] arr, String str) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(str))
                return true;
        }
        return false;
    }
    public static <T> ArrayList<T> deepCopy(ArrayList<T> arr){
        ArrayList<T> copy = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++){
            copy.add(arr.get(i));
        }
        return copy;
    }
}
