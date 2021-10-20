package com.example.proyecto2.Models


class Student(val course: SchoolCourse, val friend: Friend){

    fun beSmart(){
        course.study()
        friend.hangout()
    }
}

class SchoolCourse {
    fun study(){
        println("I'm studying")
    }
}

class Friend {
    fun hangout(){
        println("We're chillin'")
    }
}