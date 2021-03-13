package com.amazonaws.guru.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * RequestContext such as repoName additional context can be provided in the future.
 */
@Builder
@Value
public class RequestContext {
    @NonNull
    private final String repoName;
}
