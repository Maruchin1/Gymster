package com.maruchin.gymster

import android.content.Context
import androidx.room.Room
import com.maruchin.repository.training.local.TrainingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Singleton
    @Provides
    fun gymsterDatabase(@ApplicationContext context: Context): GymsterDatabase =
        Room.databaseBuilder(context, GymsterDatabase::class.java, "gymster-databsae")
            .build()

    @Provides
    fun trainingDao(database: GymsterDatabase): TrainingDao =
        database.trainingDao()
}
