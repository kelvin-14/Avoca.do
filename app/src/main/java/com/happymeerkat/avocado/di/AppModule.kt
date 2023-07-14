package com.happymeerkat.avocado.di
import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.happymeerkat.avocado.data.CategoryRepositoryImpl
import com.happymeerkat.avocado.data.ListDatabase
import com.happymeerkat.avocado.data.ListRepositoryImpl
import com.happymeerkat.avocado.data.preferences.ListFocusPreferenceRepository
import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.repository.CategoryRepository
import com.happymeerkat.avocado.domain.repository.ListRepository
import com.happymeerkat.avocado.domain.use_case.CategoryDelete
import com.happymeerkat.avocado.domain.use_case.DeleteCompletedTasks
import com.happymeerkat.avocado.domain.use_case.CategoryGetAll
import com.happymeerkat.avocado.domain.use_case.CategoryUpsert
import com.happymeerkat.avocado.domain.use_case.GetItemById
import com.happymeerkat.avocado.domain.use_case.GetItems
import com.happymeerkat.avocado.domain.use_case.ListUseCases
import com.happymeerkat.avocado.domain.use_case.ReadActiveCategoryIndex
import com.happymeerkat.avocado.domain.use_case.SaveActiveCategoryIndex
import com.happymeerkat.avocado.domain.use_case.UpsertItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

    @Provides
    @Singleton
    fun provideFocusPreferenceRepository(dataStore: DataStore<Preferences>): ListFocusPreferenceRepository {
        return ListFocusPreferenceRepository(dataStore)
    }

    @Provides
    @Singleton
    fun provideListRepository(db: ListDatabase): ListRepository {
        return ListRepositoryImpl(db.getListDao())
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(db: ListDatabase): CategoryRepository {
        return CategoryRepositoryImpl(db.getCategoryDao())
    }
    @Provides
    @Singleton
    fun provideUseCases(
        repo: ListRepository, catRepo: CategoryRepository, listFocusPreferenceRepository: ListFocusPreferenceRepository
    ): ListUseCases {
        return ListUseCases(
            getItems = GetItems(repo),
            getItemById = GetItemById(repo),
            upsertItem = UpsertItem(repo),
            deleteCompletedTasks = DeleteCompletedTasks(repo),
            categoryGetAll = CategoryGetAll(catRepo),
            categoryUpsert = CategoryUpsert(catRepo),
            categoryDelete = CategoryDelete(catRepo = catRepo, listRepo = repo),
            readActiveCategoryIndex = ReadActiveCategoryIndex(listFocusPreferenceRepository),
            saveActiveCategoryIndex = SaveActiveCategoryIndex(listFocusPreferenceRepository)
        )
    }

    @Provides
    @Singleton
    fun provideListDatabase(app: Application): ListDatabase {
        return Room.databaseBuilder(
            app,
            ListDatabase::class.java,
            ListDatabase.DATABASE_NAME,
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val contentValues = ContentValues()
                    contentValues.put ("id",1)
                    contentValues.put ("name","All")
                    db.insert("Category", OnConflictStrategy.REPLACE, contentValues)
                }
            })
            .build()
    }
}