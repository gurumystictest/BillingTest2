package com.amazonaws.guru.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.nio.file.Path;

/**
 * File level result of SourceCodeLocCalculator.
 */
@Builder
@Value
public class SourceCodeFileResult {
    // relative path
    @NonNull
    private final Path fileRelativePath;
    @NonNull
    private final SourceCodeType sourceCodeType;
    @NonNull private final Long linesOfCode;
    @NonNull private final Long logicalLinesOfCode;
    private final Long blankLinesOfCode;
    private final Long commentLinesOfCode;
    private final Long excludingCommentBlankLinesOfCode;
    private final String sourceCodeFileExtension;
    private final Long insertedLogicalLoc;
    private final Long replacedLogicalLoc;
    private final Long insertedLoc;
    private final Long replacedLoc;
    private final Long deletedLoc;
}
