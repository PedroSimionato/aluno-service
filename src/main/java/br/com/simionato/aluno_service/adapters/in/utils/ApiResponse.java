package br.com.simionato.aluno_service.adapters.in.utils;

public record ApiResponse<T> (
        String status,
        String message,
        T data){

    private static final String SUCCESS = "SUCCESS";
    private static final String ERROR = "ERROR";

    public static <T> ApiResponse<T> success(String message, T data){
        return new ApiResponse<>(SUCCESS, message, data);
    }

    public static <T> ApiResponse<T> error(String message, T data){
        return new ApiResponse<>(ERROR, message, data);
    }
}
