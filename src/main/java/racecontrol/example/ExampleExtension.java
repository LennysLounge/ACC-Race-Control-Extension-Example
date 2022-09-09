/*
 * Copyright (c) 2021 Leonard Schüngel
 * 
 * For licensing information see the included license (LICENSE.txt)
 */

package racecontrol.example;

import java.util.logging.Logger;
import racecontrol.client.ClientExtension;
import racecontrol.client.events.SessionPhaseChangedEvent;
import racecontrol.client.protocol.SessionInfo;
import racecontrol.client.protocol.enums.SessionPhase;
import racecontrol.eventbus.Event;

/**
 * An example client Extension. Logs all active drivers when a session starts.
 *
 * @author Leonard
 */
public class ExampleExtension
        extends ClientExtension {

    /**
     * This class's logger.
     */
    private static final Logger LOG
            = Logger.getLogger(ExampleExtension.class.getName());

    @Override
    public void onEvent(Event event) {
        if (event instanceof SessionPhaseChangedEvent) {
            onSessionPhaseChanged(((SessionPhaseChangedEvent) event).getSessionInfo());
        }
    }

    private void onSessionPhaseChanged(SessionInfo info) {
        if (info.getPhase() == SessionPhase.SESSION) {
            LOG.info("Session has started\nThese are the drivers");
            getWritableModel().cars.values().forEach(car -> {
                LOG.info(car.getDriver().truncatedName()
                        + " "
                        + car.carNumberString());
            });
        }
    }

}
