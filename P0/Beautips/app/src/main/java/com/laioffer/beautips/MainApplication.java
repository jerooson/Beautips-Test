package com.laioffer.beautips;

import android.app.Application;



import com.ashokvarma.gander.Gander;
import com.ashokvarma.gander.imdb.GanderIMDB;
import com.ashokvarma.gander.persistence.GanderPersistence;

import java.util.Calendar;

public class MainApplication extends Application {

      @Override
      public void onCreate() {
        super.onCreate();
        Gander.setGanderStorage(GanderPersistence.getInstance(this));

        Gander.setGanderStorage(GanderIMDB.getInstance());
    }


//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        Gander.setGanderStorage(GanderIMDB.getInstance());
//    }
}
