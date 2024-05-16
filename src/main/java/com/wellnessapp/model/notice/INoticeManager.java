package com.wellnessapp.model.notice;

import java.time.LocalDateTime;

public interface INoticeManager {
    void scheduleNotice(LocalDateTime dateTime, NoticeBO notice);
}
