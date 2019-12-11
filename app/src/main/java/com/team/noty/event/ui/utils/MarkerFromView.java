package com.team.noty.event.ui.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team.noty.event.R;
import com.team.noty.event.models.Act;
import com.team.noty.event.utils.ImageLoader;
import com.team.noty.event.utils.Pair;
import com.team.noty.event.utils.callbacks.CallBack;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

public class MarkerFromView {

    private Act act;
    private View v;

    public MarkerFromView(Act act) {
        this.act = act;
    }

    public Maybe<Pair<MarkerOptions, Act>> generate(Context context) {

        return Maybe.create(e -> {
            v = LayoutInflater.from(context).inflate(R.layout.act_on_map, null);
            TextView textView = v.findViewById(R.id.event_map_count);
            textView.setText(String.valueOf(act.count_peoples));
            ImageView imageView = v.findViewById(R.id.event_map_image);
            if (act.imageUrl != null)
                ImageLoader.loadImageOnMap(act.imageUrl, imageView, new Callback(e) {
                    @Override
                    public void call() {
                        if (!emitter.isDisposed())
                            emitter.onSuccess(new Pair<>(new MarkerOptions()
                                    .position(new LatLng(act.latitude, act.longitude))
                                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(v))),
                                    act)
                            );
                    }
                });
        });
    }

    private Bitmap getMarkerBitmapFromView(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = view.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        view.draw(canvas);
        return returnedBitmap;
    }

    private abstract class Callback implements CallBack {

        MaybeEmitter<Pair<MarkerOptions, Act>> emitter;

        Callback(MaybeEmitter<Pair<MarkerOptions, Act>> emitter) {
            this.emitter = emitter;
        }
    }
}
