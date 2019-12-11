package com.team.noty.event.interceptors;

import com.team.noty.event.convertors.Converter;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.Category;
import com.team.noty.event.models.FilterModel;
import com.team.noty.event.network.api.ContentApi;
import com.team.noty.event.network.models.response.RsAct;
import com.team.noty.event.repository.data_provide.DataProvide;

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
