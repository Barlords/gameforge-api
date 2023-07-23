screen -dmS gameforge-db docker compose up
sleep 10
screen -dmS gameforge-api java -jar target/gameforge-api-0.0.1-SNAPSHOT.jar