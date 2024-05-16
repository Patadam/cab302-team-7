package com.wellnessapp.workers;

import com.wellnessapp.model.hydration.HydrationManager;
import com.wellnessapp.model.notice.NoticeBO;
import com.wellnessapp.model.notice.NoticeManager;
import javafx.concurrent.Worker;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HydrationWorker {
    private static ScheduledExecutorService executorService;
    public HydrationWorker() {}
    public void startBackgroundExecutor() {
        final long MINUTES = 60;
        final long NoticeThreshold = getSecondsFromMinutes(MINUTES);
        if (executorService == null) {
            executorService = Executors.newScheduledThreadPool(1);
            HydrationManager manager = new HydrationManager();
            NoticeManager noticeManager = new NoticeManager();
            executorService.scheduleAtFixedRate(() -> {
                if (manager.timeSinceLastHydration() >= NoticeThreshold ) {
                    NoticeBO notice = NoticeBO.builder().title("Remember to have some water!").text(String.format("You haven't had water in the last %s minutes", 60)).build();
                    noticeManager.scheduleNotice(LocalDateTime.now(), notice);
                }
            }, 1, 5, TimeUnit.MINUTES);
        }
    }

    private long getSecondsFromMinutes(long minutes) {
        return minutes * 60;
    }
}
