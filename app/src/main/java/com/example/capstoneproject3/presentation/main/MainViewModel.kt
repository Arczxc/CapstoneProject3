package com.example.capstoneproject3.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.capstoneproject3.core.Utils.Companion.items
import com.example.capstoneproject3.domain.model.Response
import com.example.capstoneproject3.domain.model.Response.Loading
import com.example.capstoneproject3.domain.model.Response.Success
import com.example.capstoneproject3.domain.repository.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MainRepository
): ViewModel() {
    val user = repo.user

    var bannersResponse by mutableStateOf<BannersResponse>(Loading)
        private set
    var brandsResponse by mutableStateOf<BrandsResponse>(Loading)
        private set
    var ordersResponse by mutableStateOf<OrdersResponse>(Loading)
        private set
    var shoppingCartSizeResponse by mutableStateOf<Response<Long>>(Loading)
        private set
    var signOutResponse by mutableStateOf<SignOutResponse>(Success(false))
        private set

    var selectedItem by mutableStateOf(items[0])

    init {
        getBanners()
        getBrands()
        getOrders()
    }

    private fun getBanners() = viewModelScope.launch {
        bannersResponse = repo.getBannersFromRealtimeDatabase()
    }

    fun getPopularProducts() = repo.getPopularProductsFromFirestore().cachedIn(viewModelScope)

    private fun getBrands() = viewModelScope.launch {
        brandsResponse = repo.getBrandsFromRealtimeDatabase()
    }

    private fun getOrders() = viewModelScope.launch {
        ordersResponse = repo.getOrdersFromFirestore()
    }

    fun getFavoriteProducts() = repo.getFavoriteProductsFromFirestore(user.uid).cachedIn(viewModelScope)

    fun getShoppingCartSize() = viewModelScope.launch {
        repo.getShoppingCartSizeFromRealtimeDatabase().collect { response ->
            shoppingCartSizeResponse = response
        }
    }

    fun signOut() = viewModelScope.launch {
        signOutResponse = Loading
        signOutResponse = repo.signOut()
    }
}