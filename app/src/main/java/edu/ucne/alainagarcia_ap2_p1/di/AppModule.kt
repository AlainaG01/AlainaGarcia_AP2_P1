package edu.ucne.alainagarcia_ap2_p1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.alainagarcia_ap2_p1.data.local.database.TareaDb
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun providesTareaDb(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            TareaDb::class.java,
            "Tarea.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesTareaDao(tareaDb: TareaDb) = tareaDb.TareaDao()
}