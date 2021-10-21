package com.example.proyecto2

import com.example.proyecto2.models.Friend
import com.example.proyecto2.models.MainViewModel
import com.example.proyecto2.models.SchoolCourse
import com.example.proyecto2.models.Student
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    //defines a singleton of SchoolCourse
    single { SchoolCourse()}

    //defines a factory (creates a new instance every time)
    factory { Friend() }

    //defines a factory (creates a new instance every time)
    factory { Student(get(), get()) }
}

val viewModelModule: Module = module {
    viewModel { MainViewModel(get(), get()) }
}