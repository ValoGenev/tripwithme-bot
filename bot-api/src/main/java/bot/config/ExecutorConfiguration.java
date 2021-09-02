package bot.config;

import bot.model.Group;

import java.util.List;

public class ExecutorConfiguration {

    private final String timeForSwitchingPages;
    private final boolean sameTab;
    private final List<Group> fbGroups;

    public ExecutorConfiguration( List<Group> fbGroups, String timeForSwitchingPages, boolean sameTab) {
        this.timeForSwitchingPages = timeForSwitchingPages;
        this.sameTab = sameTab;
        this.fbGroups = fbGroups;
    }

    public ExecutorConfiguration(List<Group> fbGroups) {
        this.fbGroups=fbGroups;
        timeForSwitchingPages="5";
        sameTab=true;
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
