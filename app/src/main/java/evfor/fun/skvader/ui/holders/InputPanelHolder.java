package evfor.fun.skvader.ui.holders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import evfor.fun.skvader.R;
import evfor.fun.skvader.ui.utils.listeners.VoiceMotionListener;
import evfor.fun.skvader.utils.callbacks.CallBack;
import evfor.fun.skvader.utils.callbacks.CallBack1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class InputPanelHolder {

    @BindView(R.id.dialog_editText)
    EditText dialogEditText;
    @BindView(R.id.dialog_send_button)
    ImageView dialogSendButton;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.voice_cancel)
    LinearLayout voiceCancel;
    private View root;
    private CallBack startVoice, endVoice, cancelVoice;
    private CallBack1<String> sendMessage;
    private CallBack focusMessage = () -> {
    };
    private ObservableEmitter<Object> writing;
    private boolean recording = false;
    private AtomicBoolean isTick = new AtomicBoolean(false);

    public InputPanelHolder(View root) {
        this.root = root;
        ButterKnife.bind(this, root);
        dialogEditText.setOnFocusChangeListener(this::onFocus);
    }

    private void onFocus(View v, boolean b) {
        if (b) focusMessage.call();
    }

    public void setFocusMessage(CallBack focusMessage) {
        this.focusMessage = focusMessage;
    }

    public void setSendMessage(CallBack1<String> sendMessage) {
        this.sendMessage = sendMessage;
    }
    public String getMessage(){
        return dialogEditText.getText().toString();
    }

    public void claerMessage(){
         dialogEditText.setText("");
    }

    public boolean isFocusedET() {
        return dialogEditText.isFocused();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setVoiceCallBack(CallBack startVoice, CallBack endVoice, CallBack cancelVoice) {
        this.startVoice = startVoice;
        this.cancelVoice = cancelVoice;
        this.endVoice = endVoice;
        dialogSendButton.setOnTouchListener(new VoiceMotionListener(
                this::start, this::end, this::cancel
        ));
    }

    private void start() {
        if (dialogEditText.getText().length() > 0) {
            sendMessage.call(dialogEditText.getText().toString());
            dialogEditText.setText("");
        } else {
            recording = true;
            startAnimVoice();
            this.startVoice.call();
        }
    }

    private void end() {
        if (recording) {
            endAnimVoice();
            this.endVoice.call();
            recording = false;
        }
    }

    private void cancel() {
        endAnimVoice();
        this.cancelVoice.call();
        recording = false;
    }

    public Observable<Object> setWriting() {
        return Observable.create(e -> writing = e)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @OnTextChanged(R.id.dialog_editText)
    void onTextChange(CharSequence charSequence) {
        if (charSequence.length() > 0) {
            dialogSendButton.setImageResource(R.drawable.ic_send_message);
            onNext();
        } else dialogSendButton.setImageResource(R.drawable.dialog_voice);
    }

    private void onNext() {
        if (!isTick.get() && writing != null && !writing.isDisposed()) {
            Single.timer(1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(Schedulers.computation())
                    .map(aLong -> false)
                    .subscribe(isTick::set);
            isTick.set(true);
            writing.onNext(new Object());
        }
    }

    private void startAnimVoice() {
        dialogSendButton.animate().setDuration(500).scaleY(2).scaleX(2)
                .start();
        linearLayout2.setVisibility(View.INVISIBLE);
        voiceCancel.setVisibility(View.VISIBLE);
    }

    private void endAnimVoice() {
        dialogSendButton.animate().cancel();
        dialogSendButton.animate().setDuration(500).scaleY(1).scaleX(1)
                .start();
        linearLayout2.setVisibility(View.VISIBLE);
        voiceCancel.setVisibility(View.GONE);
    }
}
