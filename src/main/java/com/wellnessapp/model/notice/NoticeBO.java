package com.wellnessapp.model.notice;

public class NoticeBO {
    private final String title;
    private final String text;

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

        @Override
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        @Override
        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public NoticeBO build() {
            return new NoticeBO(this);
        }
    }

}
