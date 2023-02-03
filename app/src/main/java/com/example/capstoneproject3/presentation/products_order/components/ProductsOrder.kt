package com.example.capstoneproject3.presentation.products_order.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject3.components.ProgressBar
import com.example.capstoneproject3.core.Utils.Companion.print
import com.example.capstoneproject3.domain.model.Response.*
import com.example.capstoneproject3.domain.repository.ShoppingCartItems
import com.example.capstoneproject3.presentation.products_order.ProductsOrderViewModel

@Composable
fun ProductsOrder(
    viewModel: ProductsOrderViewModel = hiltViewModel(),
    shoppingCartItemsContent: @Composable (items: ShoppingCartItems) -> Unit
) {
    when(val productsOrderResponse = viewModel.productsOrderResponse) {
        is Loading -> ProgressBar()
        is Success -> productsOrderResponse.data?.let { items ->
            shoppingCartItemsContent(items)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(productsOrderResponse.e)
        }
    }
}