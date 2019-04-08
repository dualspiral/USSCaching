package uk.co.drnaylor.sponge.usscachingsponge;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.user.UserStorageService;
import org.spongepowered.common.SpongeImpl;

@Plugin(
        id = "uss-caching-sponge",
        name = "USS Caching Sponge"
)
public class USSCachingSponge {

    private final CachingUserStorageService service = new CachingUserStorageService();

    @Inject
    private Logger logger;

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        Sponge.getServiceManager()
                .setProvider(this, UserStorageService.class, this.service);
    }

    @Listener
    public void onServerStart(GameStartingServerEvent event) {
        this.logger.info("Starting init of user discoverer async");
        SpongeImpl.getScheduler().createAsyncExecutor(SpongeImpl.getSpongePlugin()).execute(() -> {
            this.service.init();
            this.logger.info("User discoverer init complete.");
        });
    }
}
