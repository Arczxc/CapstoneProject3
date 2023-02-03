package com.example.capstoneproject3.components.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.capstoneproject3.components.icons.ThumbImage
import com.example.capstoneproject3.core.AppConstants.NO_VALUE
import com.example.capstoneproject3.core.Utils.Companion.getImageUrl
import com.example.capstoneproject3.domain.model.Brand
import com.example.capstoneproject3.domain.model.Image.BrandImage

@Composable
@ExperimentalMaterial3Api
fun BrandCard(
    brand: Brand,               //brand extend Brand
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(
            start = 4.dp,
            end = 4.dp
        ),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onClick
    ) {
        val url = getImageUrl(         // eto ni call yung funtion nayan tas need mo lagyan ng tatlong value
            image = BrandImage,
            name = brand.name ?: NO_VALUE,       //dalawa lang tinawag niya oh  name at token lang so bat kaya sa model niya tatlo
            token = brand.token ?: NO_VALUE
        )
        ThumbImage(
            url = url,
            width = 64.dp,
            height = 64.dp
        )
    }
}