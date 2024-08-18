package com.example.login.telas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.navegacao1.model.dados.CadastroDAO
import com.example.navegacao1.model.dados.Usuario

@Composable
fun telaCadastro(navController: NavController, param: (Any, Any) -> Unit) {
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    val cadastroDAO = CadastroDAO()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Login") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (login.isNotBlank() && senha.isNotBlank()) {
                val usuario = Usuario(nome = login, senha = senha)
                cadastroDAO.adicionar(usuario) { success ->
                    if (success) {
                        navController.navigate("login")
                    } else {
                        error = "Falha ao criar usuário"
                    }
                }
            } else {
                error = "Login e senha são obrigatórios"
            }
        }) {
            Text("Inscrever-se")
        }

        if (error.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = error, color = androidx.compose.ui.graphics.Color.Red)
        }
    }
}
