package com.example.capstoneproject3.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.DESCENDING
import com.example.capstoneproject3.core.FirebaseConstants.CREATION_DATE
import com.example.capstoneproject3.core.FirebaseConstants.NAME
import com.example.capstoneproject3.core.FirebaseConstants.PAGE_SIZE
import com.example.capstoneproject3.core.FirebaseConstants.PRODUCTS
import com.example.capstoneproject3.domain.repository.ProductSearchRepository

class ProductSearchRepositoryImpl(
    private val db: FirebaseFirestore,
    private val config: PagingConfig
): ProductSearchRepository {
    override fun getProductsFromFirestore() = Pager(
        config = config
    ) {
        ProductsPagingSource(
            query = db.collection(PRODUCTS)
                .orderBy(CREATION_DATE, DESCENDING)
                .limit(PAGE_SIZE)
        )
    }.flow

    override fun getSearchProductsFromFirestore(searchText: String) = Pager(
        config = config
    ) {
        ProductsPagingSource(
            query = db.collection(PRODUCTS)
                .orderBy(NAME)
                .startAt(searchText)
                .endAt("$searchText\uf8ff")
                .limit(PAGE_SIZE)
        )
    }.flow
}