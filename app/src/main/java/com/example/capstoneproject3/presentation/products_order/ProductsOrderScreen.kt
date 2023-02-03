package com.example.capstoneproject3.presentation.products_order

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.capstoneproject3.components.AppTopBar
import com.example.capstoneproject3.presentation.products_order.components.ProductsOrderContent

@Composable
@ExperimentalMaterial3Api
fun ProductsOrderScreen(
    orderId: String,
    navigateToProductSearchScreen: () -> Unit,
    navigateToShoppingCartScreen: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = orderId,
                navigateBack = navigateBack,
                onSearchIconClick = navigateToProductSearchScreen,
                onShoppingCartIconClick = navigateToShoppingCartScreen
            )
        },
        content = { padding ->
            ProductsOrderContent(
                padding = padding,
                orderId = orderId
            )
        }
    )
}