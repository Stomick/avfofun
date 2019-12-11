package com.team.noty.event.interceptors;

import com.team.noty.event.exceptions.NotFoundException;
import com.team.noty.event.models.Category;
import com.team.noty.event.network.api.ContentApi;
import com.team.noty.event.network.models.response.RsCategory;

import javax.inject.Inject;

import io.reactivex.Single;

public class CategoryById implements Interactor<Single<Category>, String> {

    public ContentApi api;

    @Inject
    CategoryById(ContentApi api) {
        this.api = api;
    }

    @Override
    public Single<Category> call(String id) {
        return api.category()
                .singleOrError()
                .zipWith(Single.just(id), this::find);
    }

    private Category find(RsCategory category, String id) throws NotFoundException {
        for (RsCategory.Category c : category.answer)
            if (c.category_id.equals(id))
                return convert(c);
        throw new NotFoundException(id);
    }

    private Category convert(RsCategory.Category category) {
        return new Category(category.category_id, category.name, category.urlimg);
    }
}
