package evfor.fun.skvader.services;

import com.google.firebase.iid.FirebaseInstanceIdService;
import evfor.fun.skvader.app.App;

public class TokenRefresh extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        if (App.getINSTANSE() != null)
            App.getINSTANSE().sendFBToken();
    }
}
