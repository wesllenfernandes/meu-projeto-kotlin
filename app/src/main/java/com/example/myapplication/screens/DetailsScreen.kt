package com.example.myapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.Project
import com.example.myapplication.viewmodel.ProjectViewModel

@Composable
fun ProjectDetailScreen(projectId: Int?, viewModel: ProjectViewModel) {
    // Corrigido: usar collectAsState com tipagem explícita
    val projects by viewModel.projects.collectAsState(initial = emptyList<Project>())
    val project = projects.find { it.id == projectId }

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
                    InfoRow("ID", project.id.toString())
                    InfoRow("Nome", project.name)
                    InfoRow("Descrição", project.description ?: "Sem descrição")
                    // Corrigido: usar htmlUrl em vez de url
                    project.htmlUrl?.let { url ->
                        InfoRow("Link", url)
                    }
                } else {
                    Text("Projeto não encontrado")
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "$label: ",
            color = Color(0xFF1976D2),
            fontWeight = FontWeight.Bold
        )
        Text(value, fontWeight = FontWeight.Bold)
    }
    Spacer(modifier = Modifier.height(8.dp))
}