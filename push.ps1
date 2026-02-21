# Script para fazer push automático para o GitHub
# Uso: .\push.ps1 "Mensagem do commit"

param(
    [string]$mensagem = "update: Atualização geral do projeto"
)

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Script de Push para GitHub" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Verifica status
Write-Host "📋 Verificando status..." -ForegroundColor Yellow
git status

Write-Host ""
Write-Host "➕ Adicionando arquivos..." -ForegroundColor Yellow
git add .

Write-Host ""
Write-Host "📝 Fazendo commit com mensagem: '$mensagem'" -ForegroundColor Yellow
git commit -m "$mensagem"

Write-Host ""
Write-Host "🚀 Fazendo push para GitHub..." -ForegroundColor Yellow
git push origin main

Write-Host ""
Write-Host "✅ Push concluído com sucesso!" -ForegroundColor Green
Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan

