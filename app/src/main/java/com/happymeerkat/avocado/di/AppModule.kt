package com.happymeerkat.avocado.di
import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.happymeerkat.avocado.data.ListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    // just my preference of naming including the package name
    name = "com.happymeerkat.preferences"
)

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
        return applicationContext.userDataStore
    }

//    @Provides
//    @Singleton
//    fun provideUseCases(
//    ) {
//
//    }

    @Provides
    @Singleton
    fun provideListDatabase(app: Application): ListDatabase {
        return Room.databaseBuilder(
            app,
            ListDatabase::class.java,
            ListDatabase.DATABASE_NAME,
        ).fallbackToDestructiveMigration()
            .build()
    }
}