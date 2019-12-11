package evfor.fun.skvader.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import evfor.fun.skvader.R;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.repository.user.UserReader;
import evfor.fun.skvader.ui.adapters.CommentsAdapter;
import evfor.fun.skvader.ui.utils.RecyclerUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class ReviewActivity extends BaseActivity {

    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @Inject
    MainApi mainApi;
    @Inject
    UserReader userReader;
    CommentsAdapter commentsAdapter;

    @Override
    protected int layout() {
        return R.layout.activity_review;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
//
        Injector.get().getMain().inject(this);


        commentsAdapter = new CommentsAdapter();
        RecyclerUtils.setVerticalAdapter(rvReview,commentsAdapter);
        if (getIntent().getBooleanExtra("is_my",false))
        {
            String s = getIntent().getStringExtra("user_id");
            userReader.request(Integer.valueOf(s))
                    .subscribe(t->runOnUiThread(() -> commentsAdapter.setComments(t.reviews)),throwable -> Log.e("my",throwable.getMessage()));
        }
        else
            mainApi.getReview(getIntent().getStringExtra("id"))
                .subscribe(
                        t-> runOnUiThread(() -> commentsAdapter.setComments(t.getMessage())),
                        throwable -> Log.e("my",throwable.getMessage()));
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        setBackArrow(bar);
        bar.setTitle(R.string.my_reviews);
    }
}
