package com.example.myapplication.data.repository

import com.example.myapplication.data.local.ProjectDao
import com.example.myapplication.data.remote.ApiService
import com.example.myapplication.data.remote.GitHubRepo
import com.example.myapplication.model.Project
import kotlinx.coroutines.flow.Flow

class ProjectRepository(
    private val api: ApiService,
    private val dao: ProjectDao
) {
    val projects: Flow<List<Project>> = dao.getAll()

    suspend fun refreshProjects(user: String) {
        try {
            // Buscar dados da API
            val remoteProjects = api.getUserRepos(user)

            // Converter para nossa entidade Project
            val projects = remoteProjects.map { repo ->
                Project(
                    id = repo.id.toInt(),  // Converter Long para Int
                    name = repo.name,
                    description = repo.description ?: "Sem descrição",
                    htmlUrl = repo.htmlUrl  // Usar o campo correto
                )
            }

            // Limpar banco e inserir novos dados
            dao.clear()
            dao.insertAll(projects)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}