package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.ProjectRepository
import com.example.myapplication.model.Project
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProjectViewModel(private val repository: ProjectRepository) : ViewModel() {

    private val _projects = MutableStateFlow<List<Project>>(emptyList())
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        // Observa os projetos do repositório
        viewModelScope.launch {
            repository.projects.collect { _projects.value = it }
        }

        // Carrega os projetos do usuário padrão
        refresh("wesllenfernandes")
    }

    fun refresh(user: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                // Aguarda um pequeno delay para mostrar o loading visivelmente
                delay(1500)

                repository.refreshProjects(user)
            } catch (e: Exception) {
                _error.value = e.message ?: "Erro ao carregar projetos"
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }
}