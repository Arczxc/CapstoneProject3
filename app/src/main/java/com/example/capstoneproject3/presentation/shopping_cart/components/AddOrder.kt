package com.example.capstoneproject3.presentation.shopping_cart.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject3.core.Utils.Companion.print
import com.example.capstoneproject3.domain.model.Response.*
import com.example.capstoneproject3.presentation.shopping_cart.ShoppingCartViewModel

@Composable
fun AddOrder(
    viewModel: ShoppingCartViewModel = hiltViewModel()
) {
    when(val addOrderResponse = viewModel.addOrderResponse) {
        is Loading -> Unit
        is Success -> Unit
        is Failure -> LaunchedEffect(Unit) {
            print(addOrderResponse.e)
        }
    }
}