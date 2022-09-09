/*
 * Copyright (c) 2021 Leonard Schüngel
 * 
 * For licensing information see the included license (LICENSE.txt)
 */
package racecontrol.example;

import processing.core.PApplet;
import static racecontrol.gui.LookAndFeel.COLOR_DARK_GRAY;
import racecontrol.gui.RaceControlApplet;
import racecontrol.gui.app.Menu;
import racecontrol.gui.app.PageController;
import racecontrol.gui.lpui.LPContainer;

/**
 *
 * @author Leonard
 */
public class ExamplePageControler
        implements PageController {

    /**
     * The menu item for the main menu.
     */
    private final Menu.MenuItem menuItem;
    /**
     * The GUI panel for this page.
     */
    private final LPContainer panel;

    public ExamplePageControler() {
        this.menuItem = new Menu.MenuItem("Example",
                RaceControlApplet.getApplet()
                        .loadResourceAsPImage("/images/RC_Menu_Debugging.png"));
        this.panel = new ExamplePanel();
    }

    @Override
    public LPContainer getPanel() {
        return panel;
    }

    @Override
    public Menu.MenuItem getMenuItem() {
        return menuItem;
    }

}
