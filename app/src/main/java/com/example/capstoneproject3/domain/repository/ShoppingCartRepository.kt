package com.example.capstoneproject3.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.capstoneproject3.domain.model.Response
import com.example.capstoneproject3.domain.model.ShoppingCartItem

typealias ShoppingCartItems = List<ShoppingCartItem>
typealias ShoppingCartItemsResponse = Response<ShoppingCartItems>
typealias IncrementQuantityResponse = Response<Boolean>
typealias DecrementQuantityResponse = Response<Boolean>
typealias AddOrderResponse = Response<Boolean>

interface ShoppingCartRepository {
    fun getShoppingCartItemsFromFirestore(): Flow<ShoppingCartItemsResponse>

    suspend fun incrementQuantity(itemId: String): IncrementQuantityResponse

    suspend fun decrementQuantity(itemId: String): DecrementQuantityResponse

    suspend fun addOrderInFirestore(items: ShoppingCartItems): AddOrderResponse
}