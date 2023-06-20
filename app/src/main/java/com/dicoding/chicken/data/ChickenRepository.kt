package com.dicoding.chicken.data

import com.dicoding.chicken.model.Chicken
import kotlinx.coroutines.flow.Flow

interface ChickenRepository {
    fun searchChicken(query: String): Flow<List<Chicken>>

    fun getChickenById(id: Int): Flow<Chicken>

    fun getFavoriteChicken(): Flow<List<Chicken>>

    fun updateChicken(id: Int, newState: Boolean): Flow<Boolean>
}