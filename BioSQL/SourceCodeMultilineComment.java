package com.amazonaws.guru.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * Denotes the start and end of a multiline comment. For example in Java &#47;&#42; and &#42;&#47; respectively.
 */
@Getter
@Builder
public class SourceCodeMultilineComment {
    @NonNull private String start;
    @NonNull private String end;
}
