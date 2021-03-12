package com.amazonaws.guru.model;


import lombok.Builder;
import lombok.Value;

/**
 * Represents one changed section of file between source and destination.
 * start line is exclusive of the change, but end line is inclusive
 *
 *      66 64       inferenceRequests.add(RepositoryInferenceRequest.builder()
 *      67 65      .source(codeRepository)
 *      68     -   .filesWithDiff(processEntirePackage ? null : getFilesWithDiff(change))
 *         66  +   .sourceCommitId(change.getTipCommitId())
 *         67  +   .destinationCommitId(change.getBaseCommitId())
 *      69 68      .build());
 *
 * Edit for the above request
 * BeginDestination : 67
 * EndDestination : 68
 * BeginSource : 65
 * EndSource : 67
 *
 * Destination maps to base i.e. destination
 *      Typically (but not always) for a CR destination is mainline
 *      We call it "destination" because it is the DESTINATION of the move (when the developer applies the diff)
 *      You can also view this as the the code BEFORE the developer makes their change.  I.e., the ORIGINAL code.
 * Source maps to head i.e. source
 *      Typically (but not always) for a CR source is the developer's branch in which they made the changes.
 *      We call it "source" because it is the SOURCE of the move (when the developer applies the diff)
 *      You can also view this as the the code AFTER the developer makes their change.  I.e., the CHANGED code.
 */
@Builder
@Value
public class FileEdit {
    private int startingLineDestination;
    private int endingLineDestination;
    private int startingLineSource;
    private int endingLineSource;
    private final int sourceLength;
    private final int destinationLength;
    private FileEditType type;
}
