package com.wellnessapp.model.notice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Timer;

public class NoticeManager implements INoticeManager {
    private final Timer timer;

    /**
     * Constructs a new NoticeManager with a timer.
     */
    public NoticeManager() {
        this.timer = new Timer();
    }

    /**
     * Schedules a notice to be executed at the specified date and time.
     *
     * @param dateTime the date and time when the notice should be scheduled
     * @param notice   the notice to be scheduled
     */
    public void scheduleNotice(LocalDateTime dateTime, NoticeBO notice) {
        ZonedDateTime zdt = dateTime.atZone(ZoneId.systemDefault());
        Date when = Date.from(zdt.toInstant());

        NoticeTask task = new NoticeTask(notice);
        timer.schedule(task, when);
    }
}
