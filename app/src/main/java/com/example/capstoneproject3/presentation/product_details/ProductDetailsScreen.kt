package com.example.capstoneproject3.presentation.product_details

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.capstoneproject3.components.AppTopBar
import com.example.capstoneproject3.core.AppConstants.NO_VALUE
import com.example.capstoneproject3.presentation.product_details.components.ProductDetailsContent

@Composable
@ExperimentalMaterial3Api
fun ProductDetailsScreen(
    productId: String,
    navigateToProductSearchScreen: () -> Unit,
    navigateToShoppingCartScreen: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = NO_VALUE,
                navigateBack = navigateBack,
                onSearchIconClick = navigateToProductSearchScreen,
                onShoppingCartIconClick = navigateToShoppingCartScreen
            )
        },
        content = { padding ->
            ProductDetailsContent(
                padding = padding,
                productId = productId
            )
        }
    )
}