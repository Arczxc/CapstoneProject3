package com.example.capstoneproject3.domain.repository

import kotlinx.coroutines.flow.Flow

interface BrandProductsRepository {
    fun getBrandProductsFromFirestore(brand: String): Flow<ProductPagingData>
}