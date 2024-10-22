package com.iteris.counterapp.data.datasources

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @Provides
    fun provideAppCompatActivity(activity: Activity): AppCompatActivity {
        check(activity is AppCompatActivity)
        return activity
    }
}
