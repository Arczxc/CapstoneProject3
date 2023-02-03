package com.example.capstoneproject3.components.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.capstoneproject3.R

@Composable
fun FavoriteBorderIcon(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            tint = colorResource(id = R.color.accent),
            contentDescription = null
        )
    }
}