package com.example.capstoneproject3.presentation.main.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject3.components.ProgressBar
import com.example.capstoneproject3.core.Utils.Companion.print
import com.example.capstoneproject3.domain.model.Response.*
import com.example.capstoneproject3.domain.repository.Brands //wait
import com.example.capstoneproject3.presentation.main.MainViewModel

@Composable
@ExperimentalMaterial3Api
fun Brands(
    viewModel: MainViewModel = hiltViewModel(),
    brandsContent: @Composable (brands: Brands) -> Unit
) {
    when(val brandsResponse = viewModel.brandsResponse) {
        is Loading -> ProgressBar()
        is Success -> brandsResponse.data?.let { brands ->
            brandsContent(brands)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(brandsResponse.e)
        }
    }
}