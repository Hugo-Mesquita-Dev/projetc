@echo off
REM Script para iniciar a aplicação Love Makeup BL no Windows

echo.
echo ========================================
echo  Love Makeup BL - E-commerce
echo ========================================
echo.

REM Configurar JAVA_HOME
set JAVA_HOME=C:\Program Files\Java\jdk-25.0.2

REM Navegar para o diretório do projeto
cd /d %USERPROFILE%\Projetos\projetc

REM Limpar e compilar
echo.
echo [*] Compilando projeto...
call mvnw clean compile -q

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [!] ERRO na compilacao!
    echo Verifique se Java 17+ esta instalado
    pause
    exit /b 1
)

REM Executar
echo.
echo [*] Iniciando aplicacao...
echo.
call mvnw spring-boot:run

REM Mensagem de conclusão
echo.
echo ========================================
echo [+] Aplicacao rodando em http://localhost:8080
echo [+] Pressione Ctrl+C para parar
echo ========================================
echo.
pause

