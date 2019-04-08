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

import java.time.Duration;
import java.time.Instant;

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
        this.logger.info("Replacing UserStorageService");
        Sponge.getServiceManager()
                .setProvider(this, UserStorageService.class, this.service);
    }

    @Listener
    public void onServerStart(GameStartingServerEvent event) {
        this.logger.warn("-------------------------------------------------------------------------------------------");
        this.logger.warn("You are running a testing plugin for the UserStorageService");
        this.logger.warn("DO NOT OPEN AN ISSUE ON SPONGE IF YOU ENCOUNTER ISSUES WITH USERS WHILE THIS PLUGIN IS INSTALLED");
        this.logger.warn("See https://github.com/SpongePowered/SpongeCommon/pull/2124");
        this.logger.warn("-------------------------------------------------------------------------------------------");
        this.logger.info("Starting init of user discoverer async.");
        SpongeImpl.getScheduler().createAsyncExecutor(SpongeImpl.getSpongePlugin()).execute(() -> {
            Instant thisInstant = Instant.now();
            this.service.init();
            Duration duration = Duration.between(thisInstant, Instant.now());
            this.logger.info("User discoverer init complete - time taken: {} s.", ((double) duration.toMillis()) / 1000d);
        });
    }
}
