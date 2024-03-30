package sk.posam.fsa.moneymate.exception;

import java.sql.Timestamp;

public record ErrorResponse(int code, String message, Timestamp timestamp) {
}
