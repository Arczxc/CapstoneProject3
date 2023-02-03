package com.example.capstoneproject3.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.DESCENDING
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import com.example.capstoneproject3.core.AppConstants.NO_VALUE
import com.example.capstoneproject3.core.FirebaseConstants.BANNERS
import com.example.capstoneproject3.core.FirebaseConstants.BRANDS
import com.example.capstoneproject3.core.FirebaseConstants.DATE_OF_SUBMISSION
import com.example.capstoneproject3.core.FirebaseConstants.FAVORITES
import com.example.capstoneproject3.core.FirebaseConstants.ORDERS
import com.example.capstoneproject3.core.FirebaseConstants.PAGE_SIZE
import com.example.capstoneproject3.core.FirebaseConstants.POPULAR
import com.example.capstoneproject3.core.FirebaseConstants.PRODUCTS
import com.example.capstoneproject3.core.FirebaseConstants.SHOPPING_CARTS
import com.example.capstoneproject3.core.FirebaseConstants.USERS
import com.example.capstoneproject3.domain.model.Banner
import com.example.capstoneproject3.domain.model.Brand
import com.example.capstoneproject3.domain.model.Order
import com.example.capstoneproject3.domain.model.Response.Failure
import com.example.capstoneproject3.domain.model.Response.Success
import com.example.capstoneproject3.domain.model.User
import com.example.capstoneproject3.domain.repository.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseFirestore: FirebaseFirestore,
    private val config: PagingConfig
): MainRepository {
    override val user = User(
        uid = auth.currentUser?.uid ?: NO_VALUE,
        photoUrl = auth.currentUser?.photoUrl.toString(),      //ganto lang pag URL oh hays
        displayName = auth.currentUser?.displayName ?: NO_VALUE,
        email = auth.currentUser?.email ?: NO_VALUE
    )
    private val uidRef = firebaseDatabase.getReference(SHOPPING_CARTS).child(user.uid)
    private val ordersRef = firebaseFirestore.collection(USERS).document(user.uid).collection(ORDERS)

    override suspend fun getBannersFromRealtimeDatabase(): BannersResponse {
        return try {
            val banners = mutableListOf<Banner>()
            firebaseDatabase.getReference(BANNERS).get().await().children.forEach { brandSnapshot ->
                brandSnapshot.children.forEach { bannerSnapshot ->
                    val banner = bannerSnapshot.toBanner()
                    banners.add(banner)
                }
            }
            Success(banners)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override fun getPopularProductsFromFirestore() = Pager(
        config = config
    ) {
        ProductsPagingSource(
            query = firebaseFirestore.collection(PRODUCTS)
                .whereEqualTo(POPULAR, true)
                .limit(PAGE_SIZE)
        )
    }.flow

    override suspend fun getBrandsFromRealtimeDatabase(): BrandsResponse {
        return try {
            val brands = mutableListOf<Brand>()
            firebaseDatabase.getReference(BRANDS).get().await().children.forEach { snapshot ->    //base sa code na to be  yan bali categories yung BRANDS natin dba
                val brand = snapshot.toBrand()  // eto dko alam
                brands.add(brand) //san galing yun ilalagay sa brands lahat ng item
            }
            Success(brands)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getOrdersFromFirestore(): OrdersResponse {
        return try {
            val queryOrdersByDateOfSubmission = ordersRef.orderBy(DATE_OF_SUBMISSION, DESCENDING)
            val orders = queryOrdersByDateOfSubmission.get().await().toObjects(Order::class.java)
            Success(orders)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override fun getFavoriteProductsFromFirestore(uid: String) = Pager(
        config = config
    ) {
        ProductsPagingSource(
            query = firebaseFirestore.collection(PRODUCTS)
                .whereArrayContains(FAVORITES, uid)
                .limit(PAGE_SIZE)
        )
    }.flow

    override fun getShoppingCartSizeFromRealtimeDatabase() = callbackFlow {
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val shoppingCartSize = snapshot.getValue<Long>()
                shoppingCartSize?.let {
                    trySend(Success(it))
                } ?: run {
                    trySend(Success(0L))
                }
            }

            override fun onCancelled(e: DatabaseError) {
                Failure(e.toException())
            }
        }
        uidRef.addValueEventListener(listener)
        awaitClose {
            uidRef.removeEventListener(listener)
        }
    }

    override suspend fun signOut(): SignOutResponse {
        return try {
            oneTapClient.signOut().await()
            auth.signOut()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}

fun DataSnapshot.toBrand() = Brand(
    name = key,
    token = getValue(String::class.java),

    )

fun DataSnapshot.toBanner() = Banner(
    productId = key,
    productBrand = ref.parent?.key,
    token = getValue(String::class.java)
)