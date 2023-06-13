package com.happymeerkat.avocado.di
import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.happymeerkat.avocado.data.CategoryDao
import com.happymeerkat.avocado.data.CategoryRepositoryImpl
import com.happymeerkat.avocado.data.ListDao
import com.happymeerkat.avocado.data.ListDatabase
import com.happymeerkat.avocado.data.ListRepositoryImpl
import com.happymeerkat.avocado.domain.repository.CategoryRepository
import com.happymeerkat.avocado.domain.repository.ListRepository
import com.happymeerkat.avocado.domain.use_case.CreateCategory
import com.happymeerkat.avocado.domain.use_case.DeleteCategory
import com.happymeerkat.avocado.domain.use_case.DeleteCompletedTasks
import com.happymeerkat.avocado.domain.use_case.GetCategories
import com.happymeerkat.avocado.domain.use_case.GetItems
import com.happymeerkat.avocado.domain.use_case.ListUseCases
import com.happymeerkat.avocado.domain.use_case.UpsertItem
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
        repo: ListRepository, catRepo: CategoryRepository
    ): ListUseCases {
        return ListUseCases(
            getItems = GetItems(repo),
            upsertItem = UpsertItem(repo),
            deleteCompletedTasks = DeleteCompletedTasks(repo),
            getAllCategories = GetCategories(catRepo),
            createCategory = CreateCategory(catRepo),
            deleteCategory = DeleteCategory(catRepo = catRepo, listRepo = repo)
        )
    }

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