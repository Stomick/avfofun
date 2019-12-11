package evfor.fun.skvader.ui.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import evfor.fun.skvader.R;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.ui.activities.DialogActivity;
import evfor.fun.skvader.ui.activities.PhotoViewActivity;
import evfor.fun.skvader.models.Message;
import evfor.fun.skvader.utils.DateFormatter;
import evfor.fun.skvader.utils.ImageLoader;
import evfor.fun.skvader.utils.callbacks.CallBack;
import evfor.fun.skvader.utils.callbacks.CallBack1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MessageAdapter extends BaseAdapter<MessageAdapter.BaseViewHolder> {

    private List<Message> messages;
    private CallBack1<String> onPlay;
    private CallBack onPause;
    private Message isPlaingView;
    private Message write;
    private Disposable writing;
    private boolean general = false;

    public MessageAdapter() {
        messages = new ArrayList<>();
        write = Message.wrtite();
    }

    public void isGeneral() {
        this.general = true;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    public void write(String userId) {
        if (!messages.contains(write)) {
            messages.add(write);
            notifyItemInserted(messages.indexOf(write));
        }
        if (writing != null && !writing.isDisposed())
            writing.dispose();
        writing = Completable.complete()
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::stopWriting);
    }

    private void stopWriting() {
        notifyItemRemoved(messages.indexOf(write));
        messages.remove(write);
    }

    public void setProgressPlay(int current, int max) {
        if (isPlaingView != null) {
            isPlaingView.playProgress = current;
            isPlaingView.maxProgress = max;
            notifyItemChanged(messages.indexOf(isPlaingView));
            if (current < 0) {
                isPlaingView.isPlayng = false;
                isPlaingView = null;

            }
        }
    }

    public void setOnPlay(CallBack1<String> onPlay) {
        this.onPlay = onPlay;
    }

    public void setOnPause(CallBack onPause) {
        this.onPause = onPause;
    }

    public void newMessage(Message message) {
        messages.add(message);
        notifyItemInserted(
                messages.indexOf(message)
        );
    }

    public void update(Message message) {
        for (Message m : messages) {
            if (m.equals(message)) {
                m.update(message);
                notifyItemChanged(messages.indexOf(m));
                m.updateId = -1;
                return;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        switch (message.type) {
            case TEXT:
                return R.layout.message_text;
            case IMAGE:
                return R.layout.message_image;
            case VOICE:
                return R.layout.message_voice;
            case WRITE:
                return R.layout.message_writing;
        }
        return 0;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflate(parent, R.layout.message_container);
        inputView(view, viewType);
        switch (viewType) {
            case R.layout.message_text:
                return new TextViewHolder(view);
            case R.layout.message_image:
                return new ImageViewHolder(view);
            case R.layout.message_voice:
                return new VoiceViewHolder(view);
            case R.layout.message_writing:
                return new WritingViewHolder(view);
        }
        return new BaseViewHolder(view);
    }

    private void inputView(View parent, @LayoutRes int layout) {
        FrameLayout container = parent.findViewById(R.id.message_container);
        container.addView(inflate(container, layout));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class WritingViewHolder extends BaseViewHolder {

        WritingViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(int pos) {
            separator.setVisibility(View.GONE);
            status.setVisibility(View.GONE);
        }
    }

    class BaseViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.message_container)
        View container;
        @BindView(R.id.message_separstor)
        TextView separator;
        @BindView(R.id.message_status)
        TextView status;
        @BindView(R.id.message_name)
        TextView nameView;
        @BindView(R.id.message_bg)
        View bg;
        int maxW = -1;

        BaseViewHolder(View itemView) {
            super(itemView);
            if (maxW < 0)
                maxW = (int) ((float) itemView.getContext().getResources().getDisplayMetrics().widthPixels * 0.7f);
        }

        @Override
        void bind(int pos) {
            if (AuthData.equalId(messages.get(pos).sender))
                setFromMe();
            else setFromHis();
            setName(messages.get(pos));
            if (general && !messages.get(pos).fromMe())
                nameView.setVisibility(View.VISIBLE);
            else nameView.setVisibility(View.GONE);
            setStatus(messages.get(pos));
            separatorOption();
        }

        private void setName(Message message) {
            if (message.notHasUser()) {
                if (DialogActivity.creator_name != null)
                    nameView.setText(DialogActivity.creator_name);
                else
                    nameView.setText(R.string.user_deleted);
            }
            else
                nameView.setText(message.name);
            nameView.setTextColor(ContextCompat.getColor(nameView.getContext(), message.notHasUser() ? R.color.red_button_text : R.color.small_title_text));
        }

        private void separatorOption() {
            int i = getAdapterPosition();
            if (i > 0
                    && !(messages.get(i - 1).date != null
                    && DateFormatter.differenceLessMinute(messages.get(i).date, messages.get(i - 1).date))
                    || i == 0) {
                separator.setVisibility(View.VISIBLE);
                separator.setText(DateFormatter.toString(messages.get(i).date));
            } else separator.setVisibility(View.GONE);
        }

        private void setStatus(Message message) {
            if (message.status != null && message.fromMe()) {
                switch (message.status) {
                    case DELIVER:
                        status.setText(itemView.getContext().getResources().getString(R.string.delivered));
                        bg.setBackgroundResource(R.drawable.message_bg_fromme);
                        break;
                    case READ:
                        status.setText(itemView.getContext().getResources().getString(R.string.read));
                        bg.setBackgroundResource(R.drawable.message_bg_read);
                        break;
                    default:
                        status.setText(itemView.getContext().getResources().getString(R.string.sending));
                        bg.setBackgroundResource(R.drawable.message_bg_fromme);
                        break;
                }
                if(message.type.equals(Message.Type.VOICE))
                    bg.setBackgroundResource(R.drawable.message_bg);
                status.setVisibility(View.VISIBLE);
            } else {
                status.setVisibility(View.GONE);
                bg.setBackgroundResource(R.drawable.message_bg);
            }
        }

        protected void setFromMe() {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) bg.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            bg.setLayoutParams(layoutParams);
        }

        protected void setFromHis() {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) bg.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            bg.setLayoutParams(layoutParams);
        }
    }

    class TextViewHolder extends BaseViewHolder {
        @BindView(R.id.message_text)
        TextView textView;

        TextViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(int pos) {
            super.bind(pos);
            textView.setText(htmlShow(messages.get(pos).body));
        }

        private Spanned htmlShow(String htmlAsString) {
            return Html.fromHtml(htmlAsString);
        }

        @Override
        protected void setFromMe() {
            super.setFromMe();
            textView.setTextColor(Color.WHITE);
        }

        @Override
        protected void setFromHis() {
            super.setFromHis();
            textView.setTextColor(Color.BLACK);
        }
    }

    class ImageViewHolder extends BaseViewHolder {
        @BindView(R.id.message_image)
        ImageView imageView;

        ImageViewHolder(View itemView) {
            super(itemView);
            if (maxW > 0)
                imageView.setMaxWidth(maxW);
            imageView.setOnClickListener(
                    view ->
                            PhotoViewActivity.openImage(view.getContext(), messages.get(getAdapterPosition()).body));
        }

        @Override
        void bind(int pos) {
            super.bind(pos);
            ImageLoader.clearCashUrl(messages.get(pos).body);
            ImageLoader.loadImage(messages.get(pos).body, imageView);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    class VoiceViewHolder extends BaseViewHolder {
        @BindView(R.id.voice_play_pause)
        CheckBox playBox;
        @BindView(R.id.voice_progress)
        SeekBar progressBar;
        @BindView(R.id.voice_time)
        TextView timeView;

        VoiceViewHolder(View itemView) {
            super(itemView);
            itemView.setMinimumWidth(maxW);
            itemView.setOnClickListener(this::onClick);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) container.getLayoutParams();
            layoutParams.width = maxW;
            container.setLayoutParams(layoutParams);
            playBox.setOnClickListener(this::onClick);
            progressBar.setOnTouchListener(this::onTouchUp);
            bg.setBackgroundResource(R.drawable.message_bg);
        }

        private boolean onTouchUp(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                onClick(view);
            return true;
        }

        private void onClick(View ignore) {
            if (isPlaingView != null && !isPlaingView.equals(messages.get(getAdapterPosition())))
                resetAudioView(isPlaingView);
            isPlaingView = messages.get(getAdapterPosition());
            if (!isPlaingView.isPlayng) {
                if (onPlay != null) {
                    onPlay.call(messages.get(getAdapterPosition()).body);
                    isPlaingView.isPlayng = true;
                }
            } else if (onPause != null) {
                onPause.call();
                isPlaingView.isPlayng = false;
            }
            playBox.setChecked(isPlaingView.isPlayng);
        }

        @Override
        void bind(int pos) {
            super.bind(pos);
            if (messages.get(getAdapterPosition()).playProgress > 0) {
                progressBar.setMax(messages.get(getAdapterPosition()).maxProgress);
                progressBar.setProgress(messages.get(getAdapterPosition()).playProgress);
                timeView.setText(itemView.getContext().getString(R.string.voice_time_template,
                        messages.get(getAdapterPosition()).playProgress / 60,
                        messages.get(getAdapterPosition()).playProgress % 60
                ));
                playBox.setChecked(true);
            } else {
                timeView.setText(itemView.getContext().getString(R.string.voice_time_template, 0, 0));
                progressBar.setProgress(0);
                playBox.setChecked(false);
            }
        }
    }

    private void resetAudioView(Message message) {
        message.maxProgress = -1;
        message.playProgress = -1;
        if (message.isPlayng)
            if (onPause != null)
                onPause.call();
        message.isPlayng = false;
        notifyItemChanged(messages.indexOf(message));
    }
}
