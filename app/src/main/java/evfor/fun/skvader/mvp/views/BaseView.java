package evfor.fun.skvader.mvp.views;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView{
    void showInfo(@StringRes int messageId, Object...params);
}
