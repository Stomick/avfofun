package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.mvp.views.CreateEventCommView;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.WriterRepos;
import evfor.fun.skvader.repository.data_provide.DataProvide;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class CreateEventCommPresenter extends BasePresenter<CreateEventCommView> {

    @Inject
    DataProvide<List<Category>> categories;
    @Inject
    ReaderRepos<Act, ActId> actReader;
    @Inject
    WriterRepos<Act> actWriter;

    public CreateEventCommPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void getCategory() {
        categories.provide()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::setCategories, this::onError);
    }

    public void getComm(String id) {
        actReader.request(new ActId(id, false))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(eventComm -> getViewState().loadEventComm(eventComm), this::onError);
    }

    public void getEvent(String id) {
        actReader.request(new ActId(id, true))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(eventComm -> getViewState().loadEventComm(eventComm), this::onError);
    }

    public void create(Act act) {
        actWriter.edit(act)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::onComplete,throwable -> Log.e("My",throwable.getMessage()));
    }
}
