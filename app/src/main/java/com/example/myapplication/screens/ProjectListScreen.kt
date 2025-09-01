package com.example.myapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Lista mock
val projects = listOf(
    Triple(1, "App meu pedido", "Aplicativo de pedidos online."),
    Triple(2, "Barbiaria camilo", "marque seu corte de cabelo."),
    Triple(3, "Minha agenda", "Controle seus horarios"),
    Triple(4, "Estoque supermercado", "Gerenciador de estoque."),
    Triple(5, "Biblioteca", "gerencie sua biblioteca."),
)

@Composable
fun ProjectListScreen(onProjectClick: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(projects) { project ->
            ProjectCard(
                id = project.first,
                name = project.second,
                description = project.third,
                onClick = { onProjectClick(project.first) }
            )
        }
    }
}

@Composable
fun ProjectCard(id: Int, name: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF1976D2),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}