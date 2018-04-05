package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.locale;


import android.support.annotation.NonNull;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.base_mapper.DataMapper;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.interfaces.Model;

import org.greenrobot.greendao.rx.RxDao;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * @param <T>       primary key type (Long, String)
 * @param <Entity>  base model
 * @param <DBModel> db model
 */
abstract class BaseLocalDataSource<T, Entity extends Model<T>, DBModel> implements LocalDataSource<T, Entity> {

    private DataMapper<Entity, DBModel> mDataMapper;
    private RxDao<DBModel, T> mRxDao;

    protected BaseLocalDataSource(@NonNull DataMapper<Entity, DBModel> dataMapper,
                                  @NonNull RxDao<DBModel, T> rxDao) {
        mDataMapper = checkNotNull(dataMapper);
        mRxDao = checkNotNull(rxDao);
    }

    protected DataMapper<Entity, DBModel> getDataMapper() {
        return mDataMapper;
    }

    protected RxDao<DBModel, T> getRxDao() {
        return mRxDao;
    }

    @Override
    public Observable<Entity> save(final @NonNull Entity model) {
        return mDataMapper
                .transformToRx(model)
                .filter(result -> result != null)
                .flatMap(toSave -> mRxDao
                        .load(model.getId())
                        .filter(result -> result != null)
                        .flatMap(result -> mRxDao.update(toSave))
                        .flatMap(result -> mRxDao.load(model.getId()))
                        .switchIfEmpty(mRxDao.insertOrReplace(toSave)))
                .compose(mDataMapper.singleFrom());
    }

    @Override
    public Observable.Transformer<Entity, Entity> saveTransformer() {
        return observable -> observable.flatMap(BaseLocalDataSource.this::save);
    }

    @Override
    public Observable<List<Entity>> saveList(@NonNull List<Entity> models) {
        return Observable.from(models)
                //concatMap() instead flatMap() for saving correct order items in list
                .concatMap(this::save)
                .collect(ArrayList::new, List::add);
    }

    @Override
    public Observable.Transformer<List<Entity>, List<Entity>> saveListTransformer() {
        return listObservable -> listObservable.flatMap(BaseLocalDataSource.this::saveList);
    }

    @Override
    public Observable<Entity> remove(Entity model) {
        return mDataMapper
                .transformToRx(model)
                .flatMap(mRxDao::delete)
                .map(ignored -> model);
    }

    @Override
    public Observable.Transformer<Entity, Entity> removeTransformer() {
        return modelObservable -> modelObservable.flatMap(BaseLocalDataSource.this::remove);
    }

    @Override
    public Observable<List<Entity>> removeList(List<Entity> models) {
        return mDataMapper
                .transformToRx(models)
                .flatMap(mRxDao::deleteInTx)
                .map(ignored -> models);
    }

    @Override
    public Observable.Transformer<List<Entity>, List<Entity>> removeListTransformer() {
        return modelObservable -> modelObservable.flatMap(BaseLocalDataSource.this::removeList);
    }

    @Override
    public Observable<List<Entity>> getAll() {
        return mRxDao
                .loadAll()
                .compose(mDataMapper.listFrom());
    }

    @Override
    public Observable<Entity> getById(T id) {
        return mRxDao
                .load(id)
                .compose(mDataMapper.singleFrom());
    }

    @Override
    public Observable.Transformer<T, Entity> getByIdTransformer() {
        return modelObservable -> modelObservable.flatMap(BaseLocalDataSource.this::getById);
    }

    @Override
    public Observable<Entity> removeById(T id) {
        return mRxDao.load(id)
                .filter(dbModel -> dbModel != null)
                .flatMap(model -> mRxDao.delete(model)
                        .map(ignored -> model))
                .defaultIfEmpty(null)
                .compose(mDataMapper.singleFrom());
    }

    @Override
    public Observable.Transformer<T, Entity> removeByIdTransformer() {
        return modelObservable -> modelObservable.flatMap(BaseLocalDataSource.this::removeById);
    }

    @Override
    public Observable<Void> deleteAll() {
        return getRxDao().deleteAll();
    }
}
