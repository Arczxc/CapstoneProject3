package com.example.capstoneproject3.presentation.product_details.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject3.core.Utils.Companion.print
import com.example.capstoneproject3.domain.model.Response.*
import com.example.capstoneproject3.presentation.product_details.ProductDetailsViewModel

@Composable
fun AddProductToShoppingCart(
    shoppingCartViewModel: ProductDetailsViewModel = hiltViewModel()
) {
    when(val addProductToShoppingCartResponse =  shoppingCartViewModel.addProductToShoppingCartResponse) {
        is Loading -> Unit
        is Success -> Unit
        is Failure -> LaunchedEffect(Unit) {
            print(addProductToShoppingCartResponse.e)
        }
    }
}