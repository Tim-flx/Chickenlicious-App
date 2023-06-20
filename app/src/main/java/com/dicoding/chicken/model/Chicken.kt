package com.dicoding.chicken.model

data class Chicken(
    val id: Int,
    val judul: String,
    val deskripsi: String,
    val gambar: String,
    val favorit: Boolean = false
)
