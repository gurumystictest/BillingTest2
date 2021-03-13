package com.amazonaws.guru.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

/**
 * The current state of a SourceCodeFileLocCalculator. This includes the current character index in the file, as well
 * as the status of the parser (are we in a comment, code, etc.). It also includes optionals for the current quote and
 * current multiline comment, which are empty when not in the corresponding state
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public final class State {
    @Getter
    private Integer characterIndexInFile = 0;
    @Getter @Setter
    private Status status = Status.BLANK;
    @Getter @Setter
    private Optional<SourceCodeQuote> currentQuote;
    @Getter @Setter
    private Optional<SourceCodeMultilineComment> currentMultilineComment;

    public void incrementCharacterIndexInFile() {
        addToCharacterIndexInFile(1);
    }

    public void decrementCharacterIndexInFile() {
        addToCharacterIndexInFile(-1);
    }

    public void addToCharacterIndexInFile(final int n) {
        characterIndexInFile += n;
    }

    /**
     * An enum for the current internal state of the calculator.
     */
    public enum Status {
        /**
         * The parser begins non-multiline comment and non-multiline string lines in the blank or empty state. If the
         * parser does not exit the blank state by the end of the line, the line must be empty or only whitespace.
         */
        BLANK,
        /**
         * The parser will transition from blank state to code state when it encounters non-whitespace that's not a
         * comment or string.
         */
        CODE,
        /**
         * The parser reaches comment state when there is only a single line comment on the current line with nothing
         * preceding it.
         */
        COMMENT,
        /**
         * If the parser is in the code state, and encounters a single line comment before the end of the line, we
         * transition to comment code state, to distinguish that although we've encountered a comment, we should count
         * the line as code.
         */
        COMMENT_CODE,
        /**
         * If the parser encounters a multiline comment start, it will transition into the multiline comment state,
         * remaining in that state even across newlines until it reaches the multiline comment end sequence.
         */
        MULTILINE_COMMENT,
        /**
         * Similarly to the transition to the comment code state, if the parser is in the code state, and encounters a
         * multiline comment before the end of the line, we transition to multiline comment code state, to distinguish
         * that although we've encountered a multiline comment, we should count the line as code.
         */
        MULTILINE_COMMENT_CODE,
        /**
         * If the parser encounters a string, we must set to string state to ignore any comments in the string. The
         * string state will switch to code state when we see the end of the string.
         */
        STRING,
        /**
         * Similar to multiline comment, state the docstring state indicates we are in a docstring. However we can only
         * enter the docstring state from the blank state and therefore there is not a docstring code state because any
         * docstring we enter from code is in fact just a string.
         */
        DOC_STRING,
    }
}
