package com.bvwstudios.sic;

import java.util.ArrayList;
import java.util.List;

class NewsSources {
    public static final int NEW_YORK_TIMES = 1 << 0;
    public static final int THE_GUARDIAN = 1 << 1;
    public static final int USA_TODAY = 1 << 2;
    
    private static List<String> mNames = new ArrayList<String>();
    private static List<String> mUrls = new ArrayList<String>();
    
    static public CharSequence getName(int id) {
        if (id == 1) return "New York Times";
        if (id == 2) return "The Guardian";
        if (id == 4) return "USA Today";
        return String.valueOf(id);
    }
    
    public String getUrl(int id) {
        for (int i = 0; i < mUrls.size(); i++) {
            if (id == (1 << i)) return mUrls.get(i);
        }
        return null;
    }
}