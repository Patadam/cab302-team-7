package com.wellnessapp.model.hydration;

import java.util.List;

public interface IHydrationDAO {
    void insert(HydrationEntry entry);

    void get();

    void delete();

    List<HydrationEntry> getAll();

}

