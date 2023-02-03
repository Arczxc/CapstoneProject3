package com.example.capstoneproject3.domain.repository

interface ProductsOrderRepository {
    suspend fun getOrderShoppingCartItemsFromFirestore(orderId: String): ShoppingCartItemsResponse
}