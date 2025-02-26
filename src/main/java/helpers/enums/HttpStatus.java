package helpers.enums;

import lombok.Getter;

@Getter
public enum HttpStatus {
    OK(200),
    Created(201),
    Accepted(202),
    NoContent(204),
    BadRequest(400),
    Forbidden(403),
    NotFound(404),
    Conflict(409),
    InternalServerError(500);

    private final int code;

    HttpStatus(int code) {
        this.code = code;
    }
}
