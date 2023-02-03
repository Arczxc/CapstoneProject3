package com.example.capstoneproject3.core

import android.net.Uri
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import com.example.capstoneproject3.R
import com.example.capstoneproject3.core.AppConstants.FAVORITES
import com.example.capstoneproject3.core.AppConstants.HOME
import com.example.capstoneproject3.core.AppConstants.ORDERS
import com.example.capstoneproject3.core.AppConstants.PROFILE
import com.example.capstoneproject3.core.AppConstants.SIGN_OUT
import com.example.capstoneproject3.core.AppConstants.TAG
import com.example.capstoneproject3.core.AppConstants.PNG
import com.example.capstoneproject3.core.FirebaseConstants.STORAGE_BASE_URL
import com.example.capstoneproject3.domain.model.Image
import com.example.capstoneproject3.domain.model.Item
import com.example.capstoneproject3.domain.repository.ShoppingCartItems

class Utils {
    companion object {
        fun print(e: Exception?) {
            Log.e(TAG, e?.message ?: e.toString())
        }

        fun print(errorState: LoadState.Error) {
            val error = errorState.error
            Log.d(TAG, error.message ?: error.toString())
        }

        val items = listOf(
            Item(Icons.Default.Home, HOME),
            Item(Icons.Default.List, ORDERS),
            Item(Icons.Default.Favorite, FAVORITES),
            Item(Icons.Default.Person, PROFILE),
            Item(Icons.Default.ExitToApp, SIGN_OUT)
        )

        @Composable
        fun getImageUrl(image: Image, name: String, token: String): String {
            val projectId = LocalContext.current.getString(R.string.firebase_project_id)
            val slash = Uri.encode("/")
            return "$STORAGE_BASE_URL/$projectId/o/${image.folder}$slash$name.$PNG?alt=media&token=$token"

        }

        fun calculateShoppingCartTotal(items: ShoppingCartItems) = items.sumOf { item ->
            val price = item.price ?: 0
            val quantity = item.quantity ?: 0
            price * quantity
        }
    }
}