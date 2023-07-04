package com.dog.doggallery.repository

import androidx.annotation.WorkerThread
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dog.doggallery.dao.DogDao
import com.dog.doggallery.database.DogRoomDatabase
import com.dog.doggallery.entities.Dog
import com.dog.doggallery.model.ApiModelBreedImage
import com.dog.doggallery.model.ApiModelBreedImages
import com.dog.doggallery.model.ApiModelBreedsAll
import com.dog.doggallery.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class DogRepository {
    private val dogBreedsAllService = RetrofitInstance.dogBreedsAllService
    private val dogBreedImageService = RetrofitInstance.dogBreedImageService
    private val dogBreedImagesService = RetrofitInstance.dogBreedImagesService
    private val dogBreedsImagesService = RetrofitInstance.dogBreedsImagesService
    private val dogBreedsImageService = RetrofitInstance.dogBreedsImageService


    suspend fun getBreedsAll(): ApiModelBreedsAll {
        return dogBreedsAllService.getBreedsAll()
    }

    suspend fun getdogBreedImageService(breeds:String): ApiModelBreedImage {
        return dogBreedImageService.getBreedImage(breeds = breeds)
    }

    suspend fun getdogBreedsImageService(breeds:String, hound:String): ApiModelBreedImage {
        return dogBreedsImageService.getBreedsImage(breeds = breeds, hound=hound)
    }

    suspend fun getdogBreedsImagesService(breeds:String, hound:String): ApiModelBreedImages {
        return dogBreedsImagesService.getBreedsImages(breeds = breeds, hound=hound)
    }
    suspend fun getdogBreedImagesService(breeds:String): ApiModelBreedImages {
        return dogBreedImagesService.getBreedImages(breeds = breeds)
    }
}
class DogRoomRepository(private val dogDao: DogDao) {

    val allDogs: Flow<List<Dog>> = dogDao.getAll()

    @WorkerThread
    fun insert(dog: Dog){
        dogDao.insert(dog)
    }
    @WorkerThread
    fun findByName(name: String):Dog?{
        return dogDao.findByName(name)
    }

    suspend fun delete(name: String){
        dogDao.delete(name)
    }
}

