package com.amazonaws.guru.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * POJO for SourceCodeLocCalculator result.
 */
@Builder
@Value
public class SourceCodeLocCalculatorResult {
    private final List<SourceCodeFileResult> sourceCodeFileResults;
}
