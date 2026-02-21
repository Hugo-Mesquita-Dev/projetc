#!/usr/bin/env pwsh

# Script para executar Maven no Windows

param(
    [Parameter(ValueFromRemainingArguments=$true)]
    [string[]]$MavenArgs
)

$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $scriptDir

Write-Host ""
Write-Host "=============================================" -ForegroundColor Green
Write-Host "    Compilando Projeto - Loja de Maquiagem" -ForegroundColor Green
Write-Host "=============================================" -ForegroundColor Green
Write-Host ""

# Verificar Java
$javaVersion = java -version 2>&1
if ($LASTEXITCODE -ne 0) {
    Write-Host "[ERRO] Java nao encontrado!" -ForegroundColor Red
    Write-Host "Instale Java 17 ou superior" -ForegroundColor Red
    exit 1
}

Write-Host "[OK] Java encontrado" -ForegroundColor Green
Write-Host ""

# Se nenhum argumento foi passado, compilar por padrão
if ($MavenArgs.Count -eq 0) {
    $MavenArgs = @("clean", "compile")
}

Write-Host "Executando: mvnw $($MavenArgs -join ' ')" -ForegroundColor Cyan
Write-Host ""

# Executar Maven Wrapper
& cmd /c "mvnw.cmd $($MavenArgs -join ' ')"

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "=============================================" -ForegroundColor Green
    Write-Host "[SUCESSO] Comando executado com sucesso!" -ForegroundColor Green
    Write-Host "=============================================" -ForegroundColor Green
    Write-Host ""
} else {
    Write-Host ""
    Write-Host "=============================================" -ForegroundColor Red
    Write-Host "[ERRO] Falha na execucao!" -ForegroundColor Red
    Write-Host "=============================================" -ForegroundColor Red
    Write-Host ""
    exit 1
}

