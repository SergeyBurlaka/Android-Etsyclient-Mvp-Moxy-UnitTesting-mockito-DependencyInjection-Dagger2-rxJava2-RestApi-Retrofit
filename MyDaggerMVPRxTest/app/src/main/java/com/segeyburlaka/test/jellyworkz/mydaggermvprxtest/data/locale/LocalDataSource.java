package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.locale;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.interfaces.Model;

import java.util.List;

import rx.Observable;

public interface LocalDataSource<T, Entity extends Model<T>> {

    Observable<Entity> save(Entity model);

    Observable.Transformer<Entity, Entity> saveTransformer();

    Observable<List<Entity>> saveList(List<Entity> models);

    Observable.Transformer<List<Entity>, List<Entity>> saveListTransformer();

    Observable<Entity> remove(Entity model);

    Observable.Transformer<Entity, Entity> removeTransformer();

    Observable<List<Entity>> removeList(List<Entity> models);

    Observable.Transformer<List<Entity>, List<Entity>> removeListTransformer();

    Observable<List<Entity>> getAll();

    Observable<Entity> getById(T id);

    Observable.Transformer<T, Entity> getByIdTransformer();

    Observable<Entity> removeById(T id);

    Observable.Transformer<T, Entity> removeByIdTransformer();

    Observable<Void> deleteAll();
}
