package com.example.login.telas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.telas.telaLogin

@Composable
fun menu() {
    val navController = rememberNavController()
    var cadastroLogin by rememberSaveable { mutableStateOf("") }
    var cadastroSenha by rememberSaveable { mutableStateOf("") }

    NavHost(navController, startDestination = "login") {
        composable("login") { telaLogin(navController, cadastroLogin, cadastroSenha)}
        composable("cadastro") {
            telaCadastro(navController) { login, senha ->
                cadastroLogin = login
                cadastroSenha = senha
            }
        }
        composable("main") { telaPrincipal() }
    }
}