package evfor.fun.skvader.ui.utils.factory;

import android.os.Bundle;

import evfor.fun.skvader.models.Community;
import evfor.fun.skvader.models.Event;
import evfor.fun.skvader.ui.fragments.ActListFragment;
import evfor.fun.skvader.ui.fragments.BaseFragment;
import evfor.fun.skvader.ui.fragments.main.CabinetTabFragment;
import evfor.fun.skvader.ui.fragments.main.SearchTabFragment;
import evfor.fun.skvader.ui.fragments.main.create.CreateCommunityFragment;
import evfor.fun.skvader.ui.fragments.main.create.CreateEventFragment;

public class FragmentFactory {

    private static FragmentFactory instance;

    private FragmentFactory() {
    }

    public static FragmentFactory get() {
        if (instance == null)
            instance = new FragmentFactory();
        return instance;
    }

    public static BaseFragment create(String tag, Bundle bundle) {
        return get().createWithTag(tag, bundle);
    }

    private BaseFragment createWithTag(String tag, Bundle bundle) {
        if (tag.equals(SearchTabFragment.TAG))
            return new SearchTabFragment();
        if (tag.equals(CabinetTabFragment.TAG)) {
            String id = null;
            if (bundle != null && bundle.containsKey(CabinetTabFragment.TAG))
                id = bundle.getString(CabinetTabFragment.TAG);
            return CabinetTabFragment.createVisitor(id);
        }
        if (tag.equals(CreateCommunityFragment.TAG)) {
            String id = null;
            if (bundle != null && bundle.containsKey(CreateCommunityFragment.TAG))
                id = bundle.getString(CreateCommunityFragment.TAG);
            return CreateCommunityFragment.create(new Community(id));
        }
        if (tag.equals(CreateEventFragment.TAG)) {
            String id = null;
            if (bundle != null && bundle.containsKey(CreateEventFragment.TAG))
                id = bundle.getString(CreateEventFragment.TAG);
            return CreateEventFragment.create(new Event(id));
        }
        if (tag.equals(ActListFragment.TAG)) {
            if (bundle != null
                    && bundle.containsKey(ActListFragment.TAG)
                    && bundle.containsKey(ActListFragment.ID))
                return ActListFragment.create(
                        bundle.getBoolean(ActListFragment.TAG),
                        bundle.getString(ActListFragment.ID));
        }
        return null;
    }
}
