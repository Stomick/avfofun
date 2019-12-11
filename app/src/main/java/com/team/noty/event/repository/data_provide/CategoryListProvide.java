package com.team.noty.event.repository.data_provide;

import com.team.noty.event.models.Category;
import com.team.noty.event.network.api.ContentApi;
import com.team.noty.event.network.models.response.RsCategory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class CategoryListProvide implements DataProvide<List<Category>> {

    private ContentApi api;

    @Inject
    CategoryListProvide(ContentApi api) {
        this.api = api;
    }

    @Override
    public Single<List<Category>> provide() {
        return api.category()
                .flatMap(rsCategory -> Observable.fromIterable(rsCategory.answer))
                .map(this::map)
                .toList();
    }

    private Category map(RsCategory.Category rsCategory){
        return new Category(rsCategory.category_id, rsCategory.urlimg, rsCategory.name);
    }
}
