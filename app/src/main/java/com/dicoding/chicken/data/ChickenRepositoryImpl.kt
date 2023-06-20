package com.dicoding.chicken.data

import com.dicoding.chicken.model.ChickenData
import com.dicoding.chicken.model.Chicken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChickenRepositoryImpl @Inject constructor() : ChickenRepository {

    private val chickenList = mutableListOf<Chicken>()

    init {
        if (chickenList.isEmpty()) {
            chickenList.addAll(ChickenData.listChicken)
        }
    }

    override fun searchChicken(query: String) = flow {
        val data = chickenList.filter {
            it.judul.contains(query, ignoreCase = true)
        }
        emit(data)
    }

    override fun getChickenById(id: Int): Flow<Chicken> {
        return flowOf(chickenList.first { it.id == id })
    }

    override fun getFavoriteChicken(): Flow<List<Chicken>> {
        return flowOf(chickenList.filter { it.favorit })
    }

    override fun updateChicken(id: Int, newState: Boolean): Flow<Boolean> {
        val index = chickenList.indexOfFirst { it.id == id }
        val result = if (index >= 0) {
            val chickenIndex = chickenList[index]
            chickenList[index] = chickenIndex.copy(favorit = newState)
            true
        } else {
            false
        }
        return flowOf(result)
    }
}