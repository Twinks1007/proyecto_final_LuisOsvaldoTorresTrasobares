package com.dog.doggallery.utils

import com.dog.doggallery.model.Dog
import com.dog.doggallery.model.TransformationDog

fun convertListToAllDogs(list: Map<String, List<String>>?):List<String> {
    val dogs : ArrayList<String> = arrayListOf()
    list?.forEach { (s, strings) ->
        dogs.add(s)
        if (strings.isNotEmpty()){
            strings.forEach {
                dogs.add(s.plus("-".plus(it)))
            }
        }
    }
    return dogs
}

fun stringImageOrStringsImages(string: String):Boolean{
    return !string.contains("-")
}

fun stringsImagesTransformation(string: String): TransformationDog {
    val transformationDog = string.split("-")
    return TransformationDog(
        breeds = transformationDog.firstOrNull()?:"",
        hound = transformationDog.lastOrNull()?:""
    )
}

fun dogToDogEntity(dog: Dog):com.dog.doggallery.entities.Dog{
    return com.dog.doggallery.entities.Dog(name = dog.name, image = dog.image)

}



fun dogsEntityToDogs(dogs: List<com.dog.doggallery.entities.Dog>):ArrayList<Dog>{
    val arrayList:ArrayList<Dog> = arrayListOf()
    dogs.forEach {
        arrayList.add(Dog(name = it.name, image = it.image))
    }
    return arrayList

}

fun dogEntityToDog(dog: com.dog.doggallery.entities.Dog):Dog{
    return Dog(name = dog.name, image = dog.image, favorite = true)
}