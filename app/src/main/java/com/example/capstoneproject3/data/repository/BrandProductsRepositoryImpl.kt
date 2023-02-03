package com.example.capstoneproject3.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.google.firebase.firestore.FirebaseFirestore
import com.example.capstoneproject3.core.FirebaseConstants.BRAND
import com.example.capstoneproject3.core.FirebaseConstants.PAGE_SIZE
import com.example.capstoneproject3.core.FirebaseConstants.PRODUCTS
import com.example.capstoneproject3.domain.repository.BrandProductsRepository

class BrandProductsRepositoryImpl(                       // eto yung kukuwa ng brand sa firestore
    private val db: FirebaseFirestore,
    private val config: PagingConfig
): BrandProductsRepository {
    override fun getBrandProductsFromFirestore(brand: String) = Pager(
        config = config
    ) {
        ProductsPagingSource(
            query = db.collection(PRODUCTS).whereEqualTo(BRAND, brand).limit(PAGE_SIZE) //pag yung product brand is equal sa brand. hihahanap mong ifelse. pero nakaquery
        )
    }.flow
}