package ra.webmovieapplication.exception;

import lombok.Getter;

@Getter
public class CustomException extends Exception{
    public CustomException(String message) {
        super(message);
    }
}
