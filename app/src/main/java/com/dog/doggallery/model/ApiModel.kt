package com.dog.doggallery.model

data class ApiModelBreedsAll(
    val message: Map<String, List<String>>,
    val status: String,
)


data class ApiModelBreedImage(
    val message: String,
    val status: String,
)

data class ApiModelBreedImages(
    val message: List<String>,
    val status: String,
)


