package com.cby.survey.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by baiyanfang on 2017/12/15.
 */

public class DataBaseManager {

    private DaoSession mDaoSession = null;
    private UserProfileDao mUserProfileDao = null;

    private DataBaseManager(){

    }

    public DataBaseManager init(Context context){
        initDao(context);
        return this;
    }

    private static final class Holder{
        private final static DataBaseManager INSTANCE = new DataBaseManager();
    }

    public static DataBaseManager getInstance(){
        return Holder.INSTANCE;
    }

    private void initDao(Context context){
        final ReleaseOpenHelper releaseOpenHelper = new ReleaseOpenHelper(context,"survey.db");
        final Database db = releaseOpenHelper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mUserProfileDao = mDaoSession.getUserProfileDao();
    }

    public UserProfileDao getDao(){
        return mUserProfileDao;
    }
}
