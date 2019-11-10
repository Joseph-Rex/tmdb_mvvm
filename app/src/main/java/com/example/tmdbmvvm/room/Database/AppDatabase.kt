package com.example.tmdbmvvm
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomsample.MovieDao


@Database(entities = [(MovieTable::class)/*, (Provider::class), (Bill::class)*/], version = 1/*, exportSchema = false*/)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao



    /*companion object {

        *
         * The only instance

        private var sInstance: AppDatabase? = null

        *
         * Gets the singleton instance of SampleDatabase.
         *
         * @param context The context.
         * @return The singleton instance of SampleDatabase.

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, "example")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return sInstance!!
        }
    }*/

}
