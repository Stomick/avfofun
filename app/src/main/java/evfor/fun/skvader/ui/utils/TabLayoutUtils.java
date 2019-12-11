package evfor.fun.skvader.ui.utils;


import android.support.design.widget.TabLayout;

public class TabLayoutUtils {
    static public void setTitles(TabLayout tabLayout, String... titles) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            if (i < titles.length && tabLayout.getTabAt(i) != null)
                tabLayout.getTabAt(i).setText(titles[i]);
        }
    }

    public static void selectTab(TabLayout tabLayout, int index) {
        TabLayout.Tab tab = tabLayout.getTabAt(index);
        if (tab != null)
            tab.select();
    }
}
