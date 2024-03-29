package sk.posam.fsa.moneymate.exception;

import java.sql.Timestamp;

record Response(String message, String code, Timestamp timestamp) {
}