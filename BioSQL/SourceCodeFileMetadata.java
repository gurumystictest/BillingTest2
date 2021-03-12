package com.amazonaws.guru.model;

import com.amazonaws.guru.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Source code files metadata.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
    "hashedFileRelativePath",
    "totalLoc",
    "insertedLoc",
    "replacedLoc",
    "deletedLoc",
    "sourceCodeLoc",
    "insertedLogicalLoc",
    "replacedLogicalLoc",
    "fileExtension",
    "createdTimeStamp"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class SourceCodeFileMetadata {

    @JsonProperty("hashedFileRelativePath")
    private String hashedFileRelativePath;

    @JsonProperty("totalLoc")
    private Long totalLoc;

    @JsonProperty("insertedLoc")
    private Long insertedLoc;

    @JsonProperty("replacedLoc")
    private Long replacedLoc;

    @JsonProperty("deletedLoc")
    private Long deletedLoc;

    @JsonProperty("sourceCodeLoc")
    private Long sourceCodeLoc;

    @JsonProperty("insertedLogicalLoc")
    private Long insertedLogicalLoc;

    @JsonProperty("replacedLogicalLoc")
    private Long replacedLogicalLoc;

    @JsonProperty("fileExtension")
    private String fileExtension;

    @JsonProperty("createdTimeStamp")
    private String createdTimeStamp;

    /**
     * Customized Lombok Builder for SourceCodeFileMetadata to use a consistent formatting for createdTimeStamp field.
     */
    public static class SourceCodeFileMetadataBuilder {

        // Instant from the system UTC clock. Please note that conversion from instant to date or time uses the
        // {@linkplain ZoneOffset#UTC UTC time-zone}.
        public SourceCodeFileMetadataBuilder createdTimeStamp(final Instant instant) {
            this.createdTimeStamp = DateTimeUtils.DATE_TIME_FORMATTER.format(instant);
            return this;
        }
    }
}