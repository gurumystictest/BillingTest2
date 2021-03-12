package com.amazonaws.guru.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * File for which LOC should be calculated.
 */
@Builder
@Value
@Slf4j
public class SourceCodeFile {
    // file content in lines
    @NonNull
    private final List<String> fileContent;

    // relative path
    @NonNull
    private final Path fileRelativePath;

    @NonNull
    private final SourceCodeType sourceCodeType;

    private final List<FileEdit> edits;

    /*
     JGit Lib logic to get type based on the indexes:
        public final Edit.Type getType() {
            if (this.beginA < this.endA) {
                return this.beginB < this.endB ? Edit.Type.REPLACE : Edit.Type.DELETE;
            } else {
                return this.beginB < this.endB ? Edit.Type.INSERT : Edit.Type.EMPTY;
            }
        }
     */
    public Optional<List<List<String>>> getEditedContentForInsertOrReplace(final FileEditType editType) {
        if (edits == null || editType == null) {
            if (editType == null) {
                log.error("Edit type is null for getEditedContentForInsertOrReplace operation");
            }
            return Optional.empty();
        }

        if (!(editType.equals(FileEditType.INSERT) || editType.equals(FileEditType.REPLACE))) {
            log.warn("Only insert and replace edit types are supported for "
                    + "getEditedContentForInsertOrReplace operation. Given type: {} not supported", editType);
            return Optional.empty();
        }
        List<List<String>> editedContent = new ArrayList<>();
        edits.stream()
                .filter(edit -> edit.getType() != null && edit.getType().equals(editType))
                .forEach(edit -> {
                    try {
                        editedContent.add(fileContent.subList(edit.getStartingLineSource(),
                                edit.getEndingLineSource()));
                    } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                        log.error("Exception occurred when getting edited content for file: {}",
                                fileRelativePath.toString());
                        // TODO: publish metrics for this error. This requires wiring the metrics factory in
                        //  BillingUtil library, will send another CR today. Since we are under free trial,
                        //  no impact on revenue in case we miss any file
                    }
                });
        return Optional.of(editedContent);
    }

    public Optional<Long> getLocForType(final FileEditType editType) {
        if (edits == null || editType == null) {
            return Optional.empty();
        }

        long locForType = edits.stream()
                .filter(edit -> edit.getType() != null && edit.getType().equals(editType))
                .mapToLong(edit -> {
                    if (editType.equals(FileEditType.INSERT) || editType.equals(FileEditType.REPLACE)) {
                        return edit.getSourceLength();
                    } else if (editType.equals(FileEditType.DELETE)) {
                        return edit.getDestinationLength();
                    } else {
                        log.warn("Returning 0 LOC for type: {}", edit.getType());
                        return 0L;
                    }
                }).sum();
        return Optional.of(locForType);
    }
}
