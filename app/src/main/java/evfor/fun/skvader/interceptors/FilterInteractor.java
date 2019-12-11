package evfor.fun.skvader.interceptors;

import evfor.fun.skvader.convertors.Converter;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.models.FilterModel;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.network.models.response.RsAct;
import evfor.fun.skvader.repository.data_provide.DataProvide;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class FilterInteractor implements Interactor<Observable<Act>, FilterModel> {

    private ContentApi api;
    private DataProvide<List<Category>> provideCategories;
    private AsyncList<ActId> bannedList;
    private Converter<Act, RsAct> converter;
    private List<Category> categories;

    @Inject
    FilterInteractor(ContentApi api,
                     DataProvide<List<Category>> provideCategories,
                     AsyncList<ActId> bannedList,
                     Converter<Act, RsAct> converter) {
        this.api = api;
        this.provideCategories = provideCategories;
        this.bannedList = bannedList;
        this.converter = converter;
    }

    @Override
    public Observable<Act> call(FilterModel filterModel) {
        return loadCategories()
                .andThen(getActs(filterModel));
    }

    private Completable loadCategories() {
        return provideCategories.provide()
                .doOnSuccess(categories1 -> categories = categories1)
                .toCompletable();
    }

    private Observable<Act> getActs(FilterModel filterModel) {
        return api.filter(filterModel.toMap())
                .map(rsEventList -> rsEventList.answer)
                .flatMap(Observable::fromIterable)
                .doOnNext(this::setImage)
                .map(converter::convert)
                .flatMap(this::emptyIfBanned);
    }

    private Observable<Act> emptyIfBanned(Act act) {
        return bannedList.contain(act)
                .andThen(Observable.<Act>empty())
                .onErrorReturnItem(act);
    }

    private void setImage(RsAct popularEvent) {
        if (popularEvent.logo == null || popularEvent.logo.isEmpty())
            popularEvent.logo = getImageFromCategory(popularEvent.category_id);
    }

    private String getImageFromCategory(String id) {
        if (categories != null)
            for (Category category : categories)
                if (category.id.equals(id))
                    return category.logoUrl;
        return null;
    }
}
