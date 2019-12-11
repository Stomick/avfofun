// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.adapters;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageAdapter$VoiceViewHolder_ViewBinding extends MessageAdapter$BaseViewHolder_ViewBinding {
  private MessageAdapter.VoiceViewHolder target;

  @UiThread
  public MessageAdapter$VoiceViewHolder_ViewBinding(MessageAdapter.VoiceViewHolder target,
      View source) {
    super(target, source);

    this.target = target;

    target.playBox = Utils.findRequiredViewAsType(source, R.id.voice_play_pause, "field 'playBox'", CheckBox.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.voice_progress, "field 'progressBar'", SeekBar.class);
    target.timeView = Utils.findRequiredViewAsType(source, R.id.voice_time, "field 'timeView'", TextView.class);
  }

  @Override
  public void unbind() {
    MessageAdapter.VoiceViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.playBox = null;
    target.progressBar = null;
    target.timeView = null;

    super.unbind();
  }
}
