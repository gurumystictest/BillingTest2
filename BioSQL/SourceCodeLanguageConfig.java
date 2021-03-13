package com.amazonaws.guru.model;

import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.regex.Pattern;

/**
 * Config class for SourceCodeFileLocCalculator, which provides support for different comment, quote and import patterns
 * to allow easy support for multiple languages.
 */
@Getter
@Builder
public class SourceCodeLanguageConfig {
    @NonNull private ImmutableSet<String> fileExtensions;
    @NonNull private ImmutableSet<String> lineComments;
    @NonNull private ImmutableSet<Pattern> importStatementPatterns;
    @NonNull private ImmutableSet<Pattern> moduleDeclarationPatterns;
    @NonNull private ImmutableSet<SourceCodeQuote> quotes;
    @NonNull private ImmutableSet<SourceCodeMultilineComment> multilineComments;
}