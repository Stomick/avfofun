package evfor.fun.skvader.ui.utils.listeners;

import android.content.DialogInterface;

import evfor.fun.skvader.utils.callbacks.CallBack;

public class SimpleDismissListener implements DialogInterface.OnDismissListener {
    private CallBack callBack;

    public SimpleDismissListener(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        callBack.call();
    }
}
