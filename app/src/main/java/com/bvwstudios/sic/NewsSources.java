package com.bvwstudios.sic;

import java.util.ArrayList;
import java.util.List;

class NewsSources {
    public static final int NEW_YORK_TIMES = 1 << 0;
    public static final int THE_GUARDIAN = 1 << 1;
    public static final int USA_TODAY = 1 << 2;
    
    private List<String> mNames = new ArrayList<String>();
    private List<String> mUrls = new ArrayList<String>();
    
    public String getName(int id) {
        for (int i = 0; i < mNames.size(); i++) {
            if (id == (1 << i)) return mNames.get(i);
        }
        return null;
    }
    
    public String getUrl(int id) {
        for (int i = 0; i < mUrls.size(); i++) {
            if (id == (1 << i)) return mUrls.get(i);
        }
        return null;
    }
}