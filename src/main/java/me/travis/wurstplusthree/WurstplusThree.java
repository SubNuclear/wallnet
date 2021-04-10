package me.travis.wurstplusthree;

import me.travis.wurstplusthree.command.Commands;
import me.travis.wurstplusthree.event.Events;
import me.travis.wurstplusthree.gui.WurstplusGui;
import me.travis.wurstplusthree.hack.Hacks;
import me.travis.wurstplusthree.manager.fonts.GuiFont;
import me.travis.wurstplusthree.manager.fonts.MenuFont;
import me.travis.wurstplusthree.setting.Settings;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

/**
 * @author travis - began work on 8th april 2021
 */

@Mod(modid = "wurstplusthree", name = "Wurst+3", version = "0.0.1")
public class WurstplusThree {

    public static final String MODID = "wurstplusthree";
    public static final String MODNAME = "Wurst+3";
    public static final String MODVER = "0.0.1";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    // events
    public static Events EVENTS;

    // commands
    public static Commands COMMANDS;

    // hacks
    public static Hacks HACKS;

    // settings
    public static Settings SETTINGS;

    // gui
    public static WurstplusGui GUI;

    // managers
    // fonts
    public static MenuFont MENU_FONT_MANAGER;
    public static GuiFont GUI_FONT_MANAGER;

    @Mod.Instance
    public static WurstplusThree INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("loading " + MODNAME);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        this.load();
        LOGGER.info(MODNAME + " : " + MODVER + " has been loaded");
        Display.setTitle(MODNAME + " v" + MODVER);
    }

    public void load() {
        EVENTS = new Events();
        SETTINGS = new Settings();
        COMMANDS = new Commands();
        HACKS = new Hacks();
        GUI = new WurstplusGui();
        this.loadManagers();
    }

    public void loadManagers() {
        MENU_FONT_MANAGER = new MenuFont();
        GUI_FONT_MANAGER = new GuiFont();
    }

}

