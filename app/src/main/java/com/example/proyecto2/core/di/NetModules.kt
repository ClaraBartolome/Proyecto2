package com.example.proyecto2

import android.app.Application
import com.example.proyecto2.core.Common.BASE_URL
import com.example.proyecto2.data.MovieAPI
import com.example.proyecto2.data.MovieRepository
import com.example.proyecto2.models.Friend
import com.example.proyecto2.models.MainViewModel
import com.example.proyecto2.models.SchoolCourse
import com.example.proyecto2.models.Student
import com.example.proyecto2.presentation.BaseViewModel
import com.example.proyecto2.presentation.StartScreen.StartScrenViewModel
import com.example.proyecto2.utils.AppDispatcherFactory
import com.example.proyecto2.utils.DispatcherFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule: Module = module {

    //defines a singleton of SchoolCourse
    single { SchoolCourse() }

    //defines a factory (creates a new instance every time)
    factory { Friend() }

    //defines a factory (creates a new instance every time)
    factory { Student(get(), get()) }
}

val viewModelModule: Module = module {
    viewModel { StartScrenViewModel(
        get(),
        getMoviesUseCase = get(named("get_movies"))
    )}
}

val dispatcherFactoryModule = module {
    single<DispatcherFactory> {
        AppDispatcherFactory()
    }
}


val netModule: Module = module {

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)

        return okHttpClientBuilder.build()
    }

        fun provideRetrofit(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build()
        }


        single{
            provideHttpClient(get())
        }

        single { provideCache(androidApplication()) }

        single {
            provideRetrofit(get())
        }

    }


val movieAPIModule: Module = module {
    fun provideMovieApi(retrofit: Retrofit): MovieAPI {
        return retrofit.create(MovieAPI::class.java)
    }

    single {
        provideMovieApi(get())
    }

}

val repositoryModule = module {
    single {
        MovieRepository(get())
    }
}