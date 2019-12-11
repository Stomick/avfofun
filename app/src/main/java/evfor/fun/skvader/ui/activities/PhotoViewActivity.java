package evfor.fun.skvader.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import evfor.fun.skvader.R;
import evfor.fun.skvader.utils.ImageLoader;

import butterknife.BindView;

public class PhotoViewActivity extends BaseActivity {

    private static final String C = "content";

    public static void openImage(Context context, String content) {
        context.startActivity(new Intent(context, PhotoViewActivity.class)
                .putExtra(C, content)
        );
    }

    @BindView(R.id.image_view)
    ImageView imageView;

    @Override
    protected int layout() {
        return R.layout.activity_photo_view;
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    protected void initViews(@Nullable Bundle savedInstanceState) {
        loadImage(getIntent().getExtras());
        imageView.setOnTouchListener(new ImageMatrixTouchHandler(this));
    }

    private void loadImage(Bundle bundle) {
        if (bundle != null)
            if (bundle.containsKey(C))
                ImageLoader.loadImage(bundle.getString(C),
                        imageView);
    }

}
