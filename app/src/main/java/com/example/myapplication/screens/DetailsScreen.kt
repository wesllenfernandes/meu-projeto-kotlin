package com.example.myapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProjectDetailScreen(projectId: Int?) {
    val project = projects.find { it.first == projectId }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                if (project != null) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "ID: ",
                            color = Color(0xFF1976D2),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = project.first.toString(),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Nome: ",
                            color = Color(0xFF1976D2),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = project.second,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Descrição: ",
                            color = Color(0xFF1976D2),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = project.third,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    Text("Projeto não encontrado")
                }
            }
        }
    }
}