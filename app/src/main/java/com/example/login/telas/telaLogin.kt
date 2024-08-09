package com.example.login.telas

import android.graphics.Color
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun telaLogin(navController: NavController, cadstroLogin: String, cadstroSenha: String){
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(value = login, onValueChange = {login = it}, label = {Text("Login")})

        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = senha, onValueChange = {senha = it}, label = {Text("Senha")})

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (login == cadstroLogin && senha == cadstroSenha){
                navController.navigate("main")
            } else{
                error = "Error login ou senha, tente novamente."
            }
        }) {
            Text("Login")
        }
        Text(text = error, modifier = Modifier.padding(top = 8.dp))

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = {navController.navigate("cadastro")}) {
            Text("Fazer se cadastra.")
        }
    }
}