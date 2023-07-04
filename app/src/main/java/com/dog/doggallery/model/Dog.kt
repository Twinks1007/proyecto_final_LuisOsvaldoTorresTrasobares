package com.dog.doggallery.model

data class Dog(
    val name: String="",
    val image: String ="",
    var favorite:Boolean=false
)

data class TransformationDog(
    val breeds :String = "",
    val hound:String = ""
)