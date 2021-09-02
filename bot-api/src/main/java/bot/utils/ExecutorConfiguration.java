package bot.utils;

import java.util.List;

public class ExecutorConfiguration {

    private final String refreshTime;
    private final String numberOfGroupsPerThread;
    private final String timeForSwitchingPages;
    private final boolean sameTab;
    private final List<Group> fbGroups;

    public ExecutorConfiguration(List<Group> fbGroups, String refreshTime, String numberOfGroupsPerThread, String timeForSwitchingPages, boolean sameTab) {
        this.refreshTime = refreshTime;
        this.numberOfGroupsPerThread = numberOfGroupsPerThread;
        this.timeForSwitchingPages = timeForSwitchingPages;
        this.sameTab = sameTab;
        this.fbGroups = fbGroups;
    }

    public ExecutorConfiguration(List<Group> fbGroups) {
        this.fbGroups=fbGroups;
        refreshTime="5";
        numberOfGroupsPerThread="5";
        timeForSwitchingPages="5";
        sameTab=true;
    }

    public String getRefreshTime() {
        return refreshTime;
    }

    public String getNumberOfGroupsPerThread() {
        return numberOfGroupsPerThread;
    }

    public String getTimeForSwitchingPages() {
        return timeForSwitchingPages;
    }

    public boolean isSameTab() {
        return sameTab;
    }

    public List<Group> getFbGroups() {
        return fbGroups;
    }
}
