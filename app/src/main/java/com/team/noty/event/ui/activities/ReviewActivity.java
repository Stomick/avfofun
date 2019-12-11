package com.team.noty.event.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.team.noty.event.R;
import com.team.noty.event.app.Injector;
import com.team.noty.event.models.Comment;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.repository.user.UserReader;
import com.team.noty.event.ui.adapters.CommentsAdapter;
import com.team.noty.event.ui.utils.RecyclerUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Single;

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
