package evfor.fun.skvader.app;

import evfor.fun.skvader.dagger2.components.AppComponent;
import evfor.fun.skvader.dagger2.components.ChatSubComponent;
import evfor.fun.skvader.dagger2.components.DaggerAppComponent;
import evfor.fun.skvader.dagger2.components.DaggerMainComponent;
import evfor.fun.skvader.dagger2.components.MainComponent;
import evfor.fun.skvader.dagger2.modules.AppModule;
import evfor.fun.skvader.dagger2.modules.ChatModule;
import evfor.fun.skvader.utils.socket.models.ModelConnect;

public class Injector {

    private static Injector INSTANCE;
    private AppComponent appComponent;
    private ChatSubComponent chatSubComponent;
    private MainComponent mainComponent;

    static void createInstance(App app) {
        INSTANCE = new Injector(app);
    }

    public static Injector get() {
        return INSTANCE;
    }

    private Injector(App app) {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(app))
                .build();
    }

    public AppComponent getApp() {
        return appComponent;
    }

    public MainComponent getMain() {
        if (mainComponent == null)
            mainComponent = DaggerMainComponent
                    .builder()
                    .appComponent(appComponent)
                    .build();
        return mainComponent;
    }

    public void closeMain() {
        mainComponent = null;
    }

    public ChatSubComponent getChatComponent(ModelConnect modelConnect) {
        if (chatSubComponent == null)
            chatSubComponent = mainComponent.chatSubComponent(new ChatModule(modelConnect));
        return chatSubComponent;
    }

    public void closeChatComponent() {
        chatSubComponent = null;
    }
}
