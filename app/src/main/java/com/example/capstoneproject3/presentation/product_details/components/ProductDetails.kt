package com.example.capstoneproject3.presentation.product_details.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject3.components.ProgressBar
import com.example.capstoneproject3.core.Utils.Companion.print
import com.example.capstoneproject3.domain.model.Product
import com.example.capstoneproject3.domain.model.Response.*
import com.example.capstoneproject3.presentation.product_details.ProductDetailsViewModel

@Composable
fun ProductDetails(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    productDetailsContent: @Composable (product: Product) -> Unit
) {
    when(val productDetailsResponse = viewModel.productDetailsResponse) {
        is Loading -> ProgressBar()
        is Success -> productDetailsResponse.data?.let { product ->
            productDetailsContent(product)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(productDetailsResponse.e)
        }
    }
}