@echo off
rem Ejecuta las pruebas con entradas guardadas. Lanzar desde la raiz del proyecto:
rem     pruebas\ejecutar_pruebas.bat
rem Las salidas se guardan en pruebas\salidas para comparar con PRUEBAS.md

javac -d bin src\*.java || exit /b 1
if not exist pruebas\salidas mkdir pruebas\salidas

rem Se guarda el ranking real para no perderlo durante las pruebas
if exist ranking.xml copy /y ranking.xml ranking.xml.bak >nul

del /q ranking.xml inventario.xml ranking.html 2>nul
java -cp bin DespertardelHeroe < pruebas\entradas\03_ranking_vacio_y_ayuda.txt > pruebas\salidas\03.txt
java -cp bin DespertardelHeroe < pruebas\entradas\01_validaciones.txt > pruebas\salidas\01.txt
java -cp bin DespertardelHeroe < pruebas\entradas\02_inventario_y_abandono.txt > pruebas\salidas\02.txt

copy /y pruebas\datos\ranking_lleno.xml ranking.xml >nul
java -cp bin DespertardelHeroe < pruebas\entradas\04_ranking_lleno_no_entra.txt > pruebas\salidas\04.txt

del /q ranking.xml 2>nul
java -cp bin DespertardelHeroe < pruebas\entradas\05_partida_atacando.txt > pruebas\salidas\05.txt

copy /y pruebas\datos\ranking_lleno.xml ranking.xml >nul
java -cp bin DespertardelHeroe < pruebas\entradas\06_ranking_lleno_empate.txt > pruebas\salidas\06.txt

del /q ranking.xml 2>nul
java -cp bin DespertardelHeroe < pruebas\entradas\07_reinicio_entre_partidas.txt > pruebas\salidas\07.txt

rem Se devuelve el ranking real
del /q ranking.xml 2>nul
if exist ranking.xml.bak move /y ranking.xml.bak ranking.xml >nul

echo Pruebas terminadas. Revisa la carpeta pruebas\salidas
