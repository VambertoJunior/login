package com.example.navegacao1.model.dados

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class CadastroDAO {

    private val db = FirebaseFirestore.getInstance()

    // Adiciona um novo usuário à coleção "usuarios"
    fun adicionar(usuario: Usuario, callback: (Boolean) -> Unit) {
        db.collection("usuarios").add(usuario)
            .addOnSuccessListener { documentReference ->
                val id = documentReference.id
                db.collection("usuarios").document(id).update("id", id)
                    .addOnSuccessListener {
                        callback(true)
                    }
                    .addOnFailureListener {
                        callback(false)
                    }
            }
            .addOnFailureListener {
                callback(false)
            }
    }

    // Busca um usuário pelo nome
    fun buscarPorNome(nome: String, callback: (Usuario?) -> Unit) {
        db.collection("usuarios").whereEqualTo("nome", nome).get()
            .addOnSuccessListener { document ->
                if (!document.isEmpty) {
                    val usuario = document.documents[0].toObject<Usuario>()
                    callback(usuario)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener {
                callback(null)
            }
    }

    // Busca um usuário pelo ID
    fun buscarPorId(id: String, callback: (Usuario?) -> Unit) {
        db.collection("usuarios").document(id).get()
            .addOnSuccessListener { document ->
                val usuario = document.toObject<Usuario>()
                callback(usuario)
            }
            .addOnFailureListener {
                callback(null)
            }
    }

    // Atualiza os dados de um usuário
    fun atualizar(usuario: Usuario, callback: (Boolean) -> Unit) {
        db.collection("usuarios").document(usuario.id).set(usuario)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }

    // Remove um usuário
    fun remover(id: String, callback: (Boolean) -> Unit) {
        db.collection("usuarios").document(id).delete()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }
}
