screen -dmS gameforge-db docker compose up
sleep 20
screen -dmS gameforge-api java -jar target/gameforge-api-0.0.1-SNAPSHOT.jar