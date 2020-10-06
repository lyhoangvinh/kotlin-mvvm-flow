package com.lyhoangvinh.simple.di.module

import androidx.room.Room
import com.lyhoangvinh.simple.MyApplication
import com.lyhoangvinh.simple.data.DatabaseManager
import com.lyhoangvinh.simple.data.SharedPrefs
import com.lyhoangvinh.simple.data.dao.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: MyApplication): DatabaseManager {
        return Room.databaseBuilder(context, DatabaseManager::class.java, "my-database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    internal fun providesSharedPrefs(context: MyApplication): SharedPrefs = SharedPrefs.getInstance(context)

    @Provides
    @Singleton
    fun provideIssuesDao(databaseManager: DatabaseManager): IssuesDao = databaseManager.issuesDao()

    @Provides
    @Singleton
    fun provideCategoriesDao(databaseManager: DatabaseManager): CategoriesDao = databaseManager.categoriesDao()

    @Provides
    @Singleton
    fun provideCollectionDao(databaseManager: DatabaseManager): CollectionDao = databaseManager.collectionDao()

    @Provides
    @Singleton
    fun provideVideosDao(databaseManager: DatabaseManager): VideosDao = databaseManager.videosDao()

    @Provides
    @Singleton
    fun provideSearchHistoryDao(databaseManager: DatabaseManager): SearchHistoryDao = databaseManager.searchHistoryDao()
}