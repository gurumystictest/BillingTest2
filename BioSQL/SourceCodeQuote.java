package com.amazonaws.guru.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * Denotes a quoted string. In Python, it could optionally be a docstring.
 */
@Getter
@Builder
public class SourceCodeQuote {
    @NonNull private String start;
    @NonNull private String end;
    @NonNull @Builder.Default private Boolean docString = false;
}
