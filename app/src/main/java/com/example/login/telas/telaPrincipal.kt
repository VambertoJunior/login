package com.example.login.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.navegacao1.model.dados.Usuario
import com.example.navegacao1.model.dados.UsuarioDAO

@Composable
fun telaPrincipal(navController: NavController) {
    val usuarioDAO = UsuarioDAO()
    val usuarios by usuarioDAO.buscar().collectAsState(initial = emptyList())

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        items(usuarios) { usuario ->
            UserCard(usuario = usuario)
        }
    }
}

@Composable
fun UserCard(usuario: Usuario) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        elevation = 4.dp, // Corrigido para usar CardElevation
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Nome: ${usuario.nome}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Senha: ${usuario.senha}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

fun Card(
    modifier: Modifier,
    elevation: Dp,
    shape: CornerBasedShape,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    TODO("Not yet implemented")
}
