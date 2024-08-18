package com.example.navegacao1.model.dados

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.tasks.await

class UsuarioDAO {

    private val db = FirebaseFirestore.getInstance()

    // Retorna um Flow com a lista de usuários
    fun buscar(): Flow<List<Usuario>> = flow {
        val listener = db.collection("usuarios").addSnapshotListener { snapshot, e ->
        }
    }

    suspend fun buscarPorNome(nome: String): Usuario? {
        return try {
            val snapshot = db.collection("usuarios")
                .whereEqualTo("nome", nome)
                .get()
                .await()

            if (!snapshot.isEmpty) {
                snapshot.documents[0].toObject<Usuario>()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun buscarPorId(id: String): Usuario? {
        return try {
            val snapshot = db.collection("usuarios").document(id).get().await()
            snapshot.toObject<Usuario>()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun adicionar(usuario: Usuario): Boolean {
        return try {
            val documentReference = db.collection("usuarios").add(usuario).await()
            documentReference.update("id", documentReference.id).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
