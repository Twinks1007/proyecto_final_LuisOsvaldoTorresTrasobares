package com.dog.doggallery.retrofit


import com.dog.doggallery.model.ApiModelBreedImage
import com.dog.doggallery.model.ApiModelBreedImages
import com.dog.doggallery.model.ApiModelBreedsAll
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedsAllService {
    @GET("api/breeds/list/all")
    suspend fun getBreedsAll(): ApiModelBreedsAll
}

interface BreedImageService {
    @GET("api/breed/{breeds}/images/random")
    suspend fun getBreedImage(
        @Path("breeds") breeds:String
    ): ApiModelBreedImage
}

interface BreedsImagesService {
    @GET("api/breed/{breeds}/{hound}/images/random/3")
    suspend fun getBreedsImages(
        @Path("breeds") breeds:String,
        @Path("hound") hound:String
    ): ApiModelBreedImages
}

interface BreedsImageService {
    @GET("api/breed/{breeds}/{hound}/images/random")
    suspend fun getBreedsImage(
        @Path("breeds") breeds:String,
        @Path("hound") hound:String
    ): ApiModelBreedImage
}

interface BreedImagesService {
    @GET("api/breed/{breeds}/images/random/3")
    suspend fun getBreedImages(
        @Path("breeds") breeds:String
    ): ApiModelBreedImages
}

object RetrofitInstance {
    private const val BASE_URL = "https://dog.ceo"
    private val retrofit: Retrofit by lazy {

        val levelType = HttpLoggingInterceptor.Level.BODY
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(levelType)
                ).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val dogBreedsAllService: BreedsAllService by lazy {
        retrofit.create(BreedsAllService::class.java)
    }

    val dogBreedImageService: BreedImageService by lazy {
        retrofit.create(BreedImageService::class.java)
    }

    val dogBreedsImagesService: BreedsImagesService by lazy {
        retrofit.create(BreedsImagesService::class.java)
    }

    val dogBreedsImageService: BreedsImageService by lazy {
        retrofit.create(BreedsImageService::class.java)
    }



    val dogBreedImagesService: BreedImagesService by lazy {
        retrofit.create(BreedImagesService::class.java)
    }

}
