package org.example.web.exception;

public class NotFoundException extends RuntimeException {
    // メッセージ用のコンストラクタを追加する
    public NotFoundException(String message) {
        super(message);
    }

    // メッセージ用の引数の数が異なるコンストラクタを追加する
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}