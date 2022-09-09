/*
 * Copyright (c) 2021 Leonard Schüngel
 * 
 * For licensing information see the included license (LICENSE.txt)
 */
package racecontrol.example;

import java.util.logging.Logger;
import java.util.stream.Collectors;
import processing.core.PApplet;
import static racecontrol.client.AccBroadcastingClient.getClient;
import racecontrol.client.events.RealtimeUpdateEvent;
import racecontrol.client.protocol.SessionInfo;
import racecontrol.eventbus.Event;
import racecontrol.eventbus.EventBus;
import racecontrol.eventbus.EventListener;
import static racecontrol.gui.LookAndFeel.COLOR_DARK_GRAY;
import static racecontrol.gui.LookAndFeel.LINE_HEIGHT;
import racecontrol.gui.RaceControlApplet;
import racecontrol.gui.lpui.LPButton;
import racecontrol.gui.lpui.LPContainer;
import racecontrol.gui.lpui.LPLabel;
import racecontrol.utility.TimeUtils;

/**
 * An example panel.
 *
 * @author Leonard
 */
public class ExamplePanel
        extends LPContainer
        implements EventListener {

    /**
     * This class's logger
     */
    private static final Logger LOG = Logger.getLogger(ExamplePanel.class.getName());

    /**
     * GUI elements
     */
    protected final LPLabel label = new LPLabel("This is a label");
    protected final LPLabel sessionTimeLabel = new LPLabel("");
    protected final LPButton testButton = new LPButton("Button");

    public ExamplePanel() {
        EventBus.register(this);

        setName("Example panel");

        // add gui elements to this container and set their size and position
        addComponent(label);
        label.setPosition(20, LINE_HEIGHT);
        addComponent(sessionTimeLabel);
        sessionTimeLabel.setPosition(20, LINE_HEIGHT * 2);
        addComponent(testButton);
        testButton.setPosition(20, LINE_HEIGHT * 3);
        testButton.setSize(100, LINE_HEIGHT);
        testButton.setAction(this::testButtonPressed);
    }

    /**
     * Overriding the draw method on the container allows to draw the background
     * of the panel.
     *
     * @param applet The applet used for drawing.
     */
    @Override
    public void draw(PApplet applet) {
        applet.fill(COLOR_DARK_GRAY);
        applet.rect(0, 0, getWidth(), getHeight());
    }

    /**
     * Gui class can also react to event from the client. Any changes to GUI
     * elements need to be executed in the animation thread to avoid concurrency
     * issues.
     *
     * @param event The event.
     */
    @Override
    public void onEvent(Event event) {
        if (event instanceof RealtimeUpdateEvent) {
            SessionInfo info = ((RealtimeUpdateEvent) event).getSessionInfo();
            RaceControlApplet.runLater(() -> {
                sessionTimeLabel.setText("session time: "
                        + TimeUtils.asDuration(info.getSessionTime()));
            });
        }
    }

    private void testButtonPressed() {
        // You can access the client data with the getClient method
        String drivers = getClient().getModel().cars.values().stream()
                .map(carInfo
                        -> carInfo.getDriver() + " " + carInfo.carNumberString()
                )
                .collect(Collectors.joining("\n"));
        LOG.info(drivers);
    }

}
