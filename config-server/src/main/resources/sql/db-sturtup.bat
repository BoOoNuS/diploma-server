@ECHO OFF
SET HOST=localhost
SET PORT=5432
SET USER=postgres
SET DB_NAME=heating_system

psql -h %HOST% -p %PORT% -U %USER% -c "DROP DATABASE IF EXISTS %DB_NAME%"
psql -h %HOST% -p %PORT% -U %USER% -c "CREATE DATABASE %DB_NAME%"
psql -h %HOST% -p %PORT% -U %USER% -d %DB_NAME% -f "schema.sql"
psql -h %HOST% -p %PORT% -U %USER% -d %DB_NAME% -f "data.sql"

