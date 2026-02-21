@echo off
REM Script para fazer push automático para o GitHub
REM Uso: push.bat "Mensagem do commit"

setlocal enabledelayedexpansion

set mensagem=%1
if "!mensagem!"=="" (
    set mensagem=update: Atualização geral do projeto
)

echo ========================================
echo   Script de Push para GitHub
echo ========================================
echo.

echo Verificando status...
git status

echo.
echo Adicionando arquivos...
git add .

echo.
echo Fazendo commit com mensagem: "!mensagem!"
git commit -m "!mensagem!"

echo.
echo Fazendo push para GitHub...
git push origin main

echo.
echo Push concluído com sucesso!
echo ========================================
pause

