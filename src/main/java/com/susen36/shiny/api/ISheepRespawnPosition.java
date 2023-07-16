package com.susen36.shiny.api;

import javax.annotation.Nullable;
import java.util.UUID;

public interface ISheepRespawnPosition {
    @Nullable
    UUID getBedEntityUUID();
    void setBedEntityUUID(@Nullable UUID uuid);
}
