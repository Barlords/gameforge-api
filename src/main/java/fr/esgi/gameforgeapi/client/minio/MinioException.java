package fr.esgi.gameforgeapi.client.minio;

public class MinioException extends Exception {
    public MinioException(String message, Throwable cause) {
        super(message, cause);
    }
}