package com.example.login.telas

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.navegacao1.model.dados.UsuarioDAO
import kotlinx.coroutines.launch

@Composable
fun telaLogin(navController: NavController, usuarioDAO: UsuarioDAO) {
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    val context = LocalContext.current

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
            // A chamada é suspensa e deve ser feita dentro de uma coroutine
            kotlinx.coroutines.GlobalScope.launch {
                val usuario = usuarioDAO.buscarPorNome(login)
                if (usuario != null && usuario.senha == senha) {
                    navController.navigate("main")
                } else {
                    error = "Login ou senha incorretos"
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                }
            }
        }) {
            Text("Login")
        }
        Text(text = error, modifier = Modifier.padding(top = 8.dp))
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("cadastro") }) {
            Text("Fazer cadastro")
        }
    }
}
