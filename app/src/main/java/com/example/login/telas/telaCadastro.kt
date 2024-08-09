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

@Composable
fun telaCadastro(navController: NavController, MCadastro:(String, String) -> Unit){
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(value = login, onValueChange = {login = it}, label = { Text("Login")})

        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = senha, onValueChange = {senha = it}, label = {Text("Senha")})

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            MCadastro(login, senha)
            navController.navigate("login")
        }) {
            Text("Inscrever-se")
        }

    }
}