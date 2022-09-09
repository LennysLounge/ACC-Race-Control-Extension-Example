/*
 * Copyright (c) 2021 Leonard Schüngel
 * 
 * For licensing information see the included license (LICENSE.txt)
 */
package racecontrol.example;

import racecontrol.appextension.AppExtensionModule;
import racecontrol.client.ClientExtension;
import racecontrol.gui.app.PageController;

/**
 * An Example of an extension module.
 *
 * @author Leonard
 */
public class ExampleExtensionModule implements AppExtensionModule {

    @Override
    public String getName() {
        return "Example extension";
    }

    @Override
    public ClientExtension getExtension() {
        return new ExampleExtension();
    }

    @Override
    public PageController getPageController() {
        return new ExamplePageControler();
    }

}
