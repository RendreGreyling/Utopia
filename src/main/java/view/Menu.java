package view;

import controller.Controller;
import controller.util.GameMode;
import engine.guis.UiButton;
import engine.guis.UiTab;

import java.util.ArrayList;

public abstract class Menu {

    protected Controller controller;
    protected ArrayList<UiButton> buttons = new ArrayList<UiButton>();
    protected ArrayList<UiTab> tabs = new ArrayList<UiTab>();

    public Menu(Controller controller) {
        this.controller = controller;
    }

    public void buttonAction(UiButton button,GameMode gameMode) {
        if (!button.isEnabled()) {
            controller.switchGameModeRequest(gameMode);
        } else {
            controller.switchGameModeRequest(GameMode.SELECTION_MODE);
        }
    }

    protected abstract void loadComponents();

    public ArrayList<UiButton> getButtons() {
        return buttons;
    }

    public ArrayList<UiTab> getTabs() {
        return tabs;
    }
}