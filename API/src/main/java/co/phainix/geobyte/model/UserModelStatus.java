package co.phainix.geobyte.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum UserModelStatus {
    ACTIVE, INACTIVE
}
