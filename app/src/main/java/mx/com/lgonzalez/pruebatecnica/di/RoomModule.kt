package mx.com.lgonzalez.pruebatecnica.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.lgonzalez.pruebatecnica.data.local.TechnicalTestDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideTechnicalTestDatabase(
        app: Application
    ): TechnicalTestDatabase {
        return Room.databaseBuilder(
            app,
            TechnicalTestDatabase::class.java,
            "technical_test_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}