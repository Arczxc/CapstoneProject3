package com.example.capstoneproject3.presentation.product_details.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.capstoneproject3.core.Utils.Companion.getImageUrl
import com.example.capstoneproject3.domain.model.Image.ProductImageImage

@Composable
fun ProductImage(
    productId: String,
    token: String
) {
    val url = getImageUrl(
        image = ProductImageImage,
        name = productId,
        token = token
    )
    AsyncImage(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null
    )
}