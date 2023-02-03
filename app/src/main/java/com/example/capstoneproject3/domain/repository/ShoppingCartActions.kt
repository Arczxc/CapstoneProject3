package com.example.capstoneproject3.domain.repository

import com.example.capstoneproject3.domain.model.ShoppingCartItem

interface ShoppingCartActions {
    suspend fun incrementShoppingCartQuantityInRealtimeDatabase()

    suspend fun incrementShoppingCartItemQuantityInFirestore(itemId: String)

    suspend fun addShoppingCartItemToFirestore(item: ShoppingCartItem)

    fun decrementShoppingCartQuantityInRealtimeDatabase()

    suspend fun decrementShoppingCartItemQuantityInFirestore(itemId: String)
}