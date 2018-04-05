package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.app;


import android.content.Context;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.DaoMaster;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.DaoSession;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GreenDaoModule {

    @Provides
    @Singleton
    public DaoSession provideDaoSession(Context context) {

        DaoMaster.DevOpenHelper helper =
                new DaoMaster.DevOpenHelper(context, context.getString(R.string.db_name));
        Database db = helper.getWritableDb();

        return new DaoMaster(db).newSession(IdentityScopeType.None);
    }
}
