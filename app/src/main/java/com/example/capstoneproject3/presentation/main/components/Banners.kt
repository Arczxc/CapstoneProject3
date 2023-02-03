package com.example.capstoneproject3.presentation.main.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject3.components.ProgressBar
import com.example.capstoneproject3.core.Utils.Companion.print
import com.example.capstoneproject3.domain.model.Response.*
import com.example.capstoneproject3.domain.repository.Banners
import com.example.capstoneproject3.presentation.main.MainViewModel

@Composable
@ExperimentalMaterial3Api
fun Banners(
    viewModel: MainViewModel = hiltViewModel(),
    bannersContent: @Composable (banners: Banners) -> Unit
) {
    when(val bannersResponse = viewModel.bannersResponse) {
        is Loading -> ProgressBar()
        is Success -> bannersResponse.data?.let { banners ->
            bannersContent(banners)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(bannersResponse.e)
        }
    }
}