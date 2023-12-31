package com.example.galleryapp.data.repositories.authorization

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(
        email: String,
        password: String
    ): AuthResult? {
        return firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun signup(
        email: String,
        password: String
    ): AuthResult? {

        return firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}