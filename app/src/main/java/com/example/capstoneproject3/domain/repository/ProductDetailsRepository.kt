package com.example.capstoneproject3.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.capstoneproject3.domain.model.Product
import com.example.capstoneproject3.domain.model.Response
import com.example.capstoneproject3.domain.model.ShoppingCartItem

typealias ProductResponse = Response<Product>
typealias AddProductToFavoriteResponse = Response<Boolean>
typealias DeleteProductFromFavoritesResponse = Response<Boolean>
typealias AddProductToShoppingCartResponse = Response<Boolean>

interface ProductDetailsRepository {
    fun getProductFromFirestore(
        productId: String
    ): Flow<ProductResponse>

    suspend fun addProductToFavoritesInFirestore(
        productId: String,
        uid: String
    ): AddProductToFavoriteResponse

    suspend fun deleteProductFromFavoritesInFirestore(
        productId: String,
        uid: String
    ): DeleteProductFromFavoritesResponse

    suspend fun addProductToShoppingCartInFirestore(
        item: ShoppingCartItem
    ): AddProductToShoppingCartResponse
}