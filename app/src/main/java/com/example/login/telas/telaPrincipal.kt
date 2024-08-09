package com.example.login.telas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun telaPrincipal(){
    val  items = listOf("bem vindo!", "o que voce quer ?", "tem novidade? hehe")

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        items(items){ item ->

            Card (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp)
            ){
                Text(text = item, modifier = Modifier.padding(16.dp))
            }
        }
    }
}