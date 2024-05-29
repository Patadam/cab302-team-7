package com.wellnessapp.model.notice;

/**
 * NoticeBO represents a notice object containing a title and text.
 * This class provides a builder pattern for creating NoticeBO instances.
 * @see NoticeBO#builder()
 */
public class NoticeBO {
    private final String title;
    private final String text;

    /**
     * Constructs a NoticeBO object with the provided title and text.
     * This constructor is private to enforce the use of the builder pattern.
     * @param builder The builder containing title and text.
     */
    private NoticeBO(Builder builder) {
        this.title = builder.title;
        this.text = builder.text;
    }

    public static TitleSetter builder() {
        return new Builder();
    }

    public String getTitle() {
        return this.title;
    }
    public String getText() {
        return this.text;
    }
    public interface TitleSetter {
        TextSetter title(String title);
    }
    public interface TextSetter {
        TextSetter text(String text);
        NoticeBO build();
    }

    private static class Builder implements TitleSetter, TextSetter {
        private String title;
        private String text;

        /**
         * Sets the title of the notice.
         * @param title The title of the notice.
         * @return The builder instance to set the text.
         */
        @Override
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets the text content of the notice.
         * @param text The text content of the notice.
         * @return The builder instance to build the notice.
         */
        @Override
        public Builder text(String text) {
            this.text = text;
            return this;
        }

        /**
         * Builds and returns the NoticeBO object.
         * @return The constructed NoticeBO object.
         */
        public NoticeBO build() {
            return new NoticeBO(this);
        }
    }

}
