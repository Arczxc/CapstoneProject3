package com.example.capstoneproject3.presentation.main.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject3.components.ProgressBar
import com.example.capstoneproject3.core.Utils.Companion.print
import com.example.capstoneproject3.domain.model.Response.*
import com.example.capstoneproject3.domain.repository.Orders
import com.example.capstoneproject3.presentation.main.MainViewModel

@Composable
fun Orders(
    viewModel: MainViewModel = hiltViewModel(),
    ordersContent: @Composable (orders: Orders) -> Unit
) {
    when(val ordersResponse = viewModel.ordersResponse) {
        is Loading -> ProgressBar()
        is Success -> ordersResponse.data?.let { orders ->
            ordersContent(orders)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(ordersResponse.e)
        }
    }
}