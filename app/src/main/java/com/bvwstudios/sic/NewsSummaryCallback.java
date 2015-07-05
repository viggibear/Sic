package com.bvwstudios.sic;

interface NewsSummaryCallbackInterface {
    public void returnSummary(String summary);
}

public class NewsSummaryCallback implements NewsSummaryCallbackInterface {
    @Override
    public void returnSummary(String summary) { return; }
}
