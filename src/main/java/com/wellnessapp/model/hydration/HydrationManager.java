package com.wellnessapp.model.hydration;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class HydrationManager {
    private HydrationDAO hydrationDAO;

    public HydrationManager(){
        this.hydrationDAO = new HydrationDAO();
    }

    public void add(HydrationEntry entry) {
        hydrationDAO.insert(entry);
    }

    /**
     *
     * @return Time in seconds since the last hydration entry.
     */
    public long timeSinceLastHydration() {
        HydrationEntry entry = hydrationDAO.getLatestEntry();
        if (entry != null) {
            long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
            long datum = entry.getDateTime().toEpochSecond(ZoneOffset.UTC);
            return now - datum;
        } else {
            System.err.println("Could not get the latest entry in the Hydration table via hydrationDAO");
            return 0;
        }
    }
}
