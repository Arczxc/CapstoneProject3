package com.example.capstoneproject3.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import com.example.capstoneproject3.components.DrawerTopBar
import com.example.capstoneproject3.core.Utils.Companion.items
import com.example.capstoneproject3.presentation.main.components.SignOut
import com.example.capstoneproject3.presentation.main.components.drawer.DrawerContent
import com.example.capstoneproject3.presentation.main.components.drawer.items.ItemFavorites
import com.example.capstoneproject3.presentation.main.components.drawer.items.ItemHome
import com.example.capstoneproject3.presentation.main.components.drawer.items.ItemOrders
import com.example.capstoneproject3.presentation.main.components.drawer.items.ItemProfile

@Composable
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navigateToProductSearchScreen: () -> Unit,
    navigateToShoppingCartScreen: () -> Unit,
    navigateToProductDetailsScreen: (productId: String) -> Unit,
    navigateToBrandProductsScreen: (productBrand: String) -> Unit,
    navigateToProductsOrderScreen: (orderId: String) -> Unit,
    navigateToAuthScreen: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                user = viewModel.user,
                drawerState = drawerState,
                coroutineScope = coroutineScope
            )
        },
        content = {
            Scaffold(
                topBar = {
                    DrawerTopBar(
                        openNavigationDrawer = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        },
                        onSearchIconClick = navigateToProductSearchScreen,
                        onShoppingCartIconClick = navigateToShoppingCartScreen
                    )
                },
                content = { padding ->
                    Box(
                        modifier = Modifier.fillMaxSize().padding(padding)
                    ) {
                        when (viewModel.selectedItem) { //tas eto yung sa left? oo
                            items[0] -> ItemHome(
                                navigateToProductDetailsScreen = navigateToProductDetailsScreen,
                                navigateToBrandProductsScreen = navigateToBrandProductsScreen
                            )
                            items[1] -> ItemOrders(
                                navigateToProductsOrderScreen = navigateToProductsOrderScreen
                            )
                            items[2] -> ItemFavorites(
                                navigateToProductDetailsScreen = navigateToProductDetailsScreen
                            )
                            items[3] -> ItemProfile()
                            items[4] -> viewModel.signOut()
                        }
                    }
                }
            )
        }
    )

    SignOut { signedOut ->
        if (signedOut) {
            navigateToAuthScreen()
        }
    }
}