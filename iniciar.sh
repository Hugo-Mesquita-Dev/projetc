#!/bin/bash
# Script para iniciar a aplicação Love Makeup BL

# Configurar JAVA_HOME
export JAVA_HOME="/c/Program Files/Java/jdk-25.0.2"

# Navegar para o diretório do projeto
cd ~/Projetos/projetc

# Limpar e compilar
echo "🔨 Compilando projeto..."
./mvnw clean compile -q

# Executar
echo "🚀 Iniciando aplicação..."
./mvnw spring-boot:run

# Mensagem de sucesso
echo "✅ Aplicação rodando em http://localhost:8080"

