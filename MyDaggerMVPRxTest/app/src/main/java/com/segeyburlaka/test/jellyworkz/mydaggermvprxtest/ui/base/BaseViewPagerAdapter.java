package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import java.util.List;


public class BaseViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<FragmentInfoContainer> mFragmentInfoContainers;
    private Context mContext;
    private SparseArray<Fragment> mFragments = new SparseArray<>();

    public BaseViewPagerAdapter(
            @NonNull Context context,
            FragmentManager fm,
            @NonNull List<FragmentInfoContainer> fragmentInfoContainers) {
        super(fm);
        mContext = context.getApplicationContext();
        mFragmentInfoContainers = fragmentInfoContainers;
    }

    public static FragmentInfoContainer newFragmentInfoContainer(@NonNull Class<? extends MvpAppCompatFragment> fragmentClass,
                                                                 @NonNull String title,
                                                                 @Nullable Bundle args) {
        return new FragmentInfoContainer(fragmentClass, title, args);
    }

    public static FragmentInfoContainer newFragmentInfoContainer(@NonNull Class<? extends MvpAppCompatFragment> fragmentClass,
                                                                 @NonNull String title) {
        return new FragmentInfoContainer(fragmentClass, title, null);
    }

    @Override
    public Fragment getItem(int position) {
        FragmentInfoContainer fragmentInfoContainer = mFragmentInfoContainers.get(position);
        return Fragment.instantiate(mContext, fragmentInfoContainer.getFragmentClass().getName(), fragmentInfoContainer.getArgs());
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Nullable
    public Fragment getFragment(int position) {
        return mFragments.get(position);
    }

    @Nullable
    public int getFragmentsCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentInfoContainers.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return mFragmentInfoContainers.size();
    }

    public static class FragmentInfoContainer {
        private Class<? extends MvpAppCompatFragment> mFragmentClass;
        private String mTitle;
        private Bundle mArgs;

        private FragmentInfoContainer(@NonNull Class<? extends MvpAppCompatFragment> fragmentClass, @NonNull String title, @Nullable Bundle args) {
            mFragmentClass = fragmentClass;
            mTitle = title;
            mArgs = args;
        }

        private Class<? extends MvpAppCompatFragment> getFragmentClass() {
            return mFragmentClass;
        }

        public FragmentInfoContainer setFragmentClass(Class<? extends MvpAppCompatFragment> fragmentClass) {
            mFragmentClass = fragmentClass;
            return this;
        }

        private String getTitle() {
            return mTitle;
        }

        public FragmentInfoContainer setTitle(String title) {
            mTitle = title;
            return this;
        }

        private Bundle getArgs() {
            return mArgs;
        }

        public FragmentInfoContainer setArgs(Bundle args) {
            mArgs = args;
            return this;
        }
    }
}
