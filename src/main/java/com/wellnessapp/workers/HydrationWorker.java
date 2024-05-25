package com.wellnessapp.workers;

import com.wellnessapp.model.hydration.HydrationManager;
import com.wellnessapp.model.notice.NoticeBO;
import com.wellnessapp.model.notice.NoticeManager;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Worker class responsible for managing the background hydration reminder task.
 */
public class HydrationWorker {
    private static ScheduledExecutorService executorService;
    public HydrationWorker() {}
    /**
     * Starts the background executor that periodically checks hydration status
     * and sends a notification if the user has not had water in the specified duration.
     * @see NoticeManager
     */
    public void startBackgroundExecutor() {
        final long MINUTES = 60;
        final long NoticeThreshold = getSecondsFromMinutes(MINUTES);
        if (executorService == null) {
            executorService = Executors.newScheduledThreadPool(1);
            HydrationManager manager = new HydrationManager();
            NoticeManager noticeManager = new NoticeManager();
            executorService.scheduleAtFixedRate(() -> {
                if (manager.timeSinceLastHydration() >= NoticeThreshold ) {
                    NoticeBO notice = NoticeBO.builder()
                            .title("Remember to have some water!")
                            .text(String.format("You haven't had water in the last %s minutes, " +
                                    "remember to record your water entry in the WellTrack app.", 60))
                            .build();
                    noticeManager.scheduleNotice(LocalDateTime.now(), notice);
                }
            }, 0, 4, TimeUnit.MINUTES);
        }
    }

    private long getSecondsFromMinutes(long minutes) {
        return minutes * 60;
    }
}
