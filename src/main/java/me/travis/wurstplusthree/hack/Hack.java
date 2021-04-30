package me.travis.wurstplusthree.hack;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.wurstplusthree.WurstplusThree;
import me.travis.wurstplusthree.event.events.Render2DEvent;
import me.travis.wurstplusthree.event.events.Render3DEvent;
import me.travis.wurstplusthree.hack.chat.ToggleMessages;
import me.travis.wurstplusthree.setting.Setting;
import me.travis.wurstplusthree.util.ClientMessage;
import me.travis.wurstplusthree.util.Globals;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class Hack implements Globals {

    private final String name;
    private final String description;
    private final Category category;
    private int bind;
    private boolean shown;
    private boolean isEnabled;
    /**
     * -1 = not
     * 0 = always
     * 1 = yes
     */
    private int isListening;

    public Hack(String name, String desc, Category cat, boolean shouldAlwaysListen) {
        this.name = name;
        this.description = desc;
        this.category = cat;
        this.isListening = (shouldAlwaysListen ? 0 : 1);
        this.bind = Keyboard.KEY_NONE;
        this.shown = true;
        this.isEnabled = false;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onToggle() {
    }

    public void onLoad() {
    }

    public void onTick() {
    }

    public void onLogin() {
    }

    public void onLogout() {
    }

    public void onUpdate() {
    }

    public void onRender2D(Render2DEvent event) {
    }

    public void onRender3D(Render3DEvent event) {
    }

    public void onUnload() {
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void stopListening() {
        this.isListening = -1;
    }

    public void enable() {
        this.isEnabled = true;
        this.onEnable();
        if (this.isEnabled() && this.isListening()) {
            MinecraftForge.EVENT_BUS.register(this);
        }
        if(this.shown) {ClientMessage.sendToggleMessage(this, true);}
    }

    public void disable() {
        if (this.isListening != 0) {
            MinecraftForge.EVENT_BUS.unregister(this);
        }
        this.isEnabled = false;
        this.onDisable();
        if(this.shown) {ClientMessage.sendToggleMessage(this, false);}
    }

    public void toggle() {
        if (this.isEnabled()) {
            this.disable();
        } else {
            this.enable();
        }
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    public boolean isListening() {
        return isListening >= -1;
    }


    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDisplayInfo() {
        return null;
    }

    public int getBind() {
        return this.bind;
    }

    public boolean getShown() {
        return this.shown;
    }

    public String getBindName() {
        return Keyboard.getKeyName(this.bind);
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public Category getCategory() {
        return this.category;
    }

    public List<Setting> getSettings() {
        List<Setting> settings = new ArrayList<>();
        for (Setting setting : WurstplusThree.SETTINGS.getSettings()) {
            if (setting.getParent() == this) {
                settings.add(setting);
            }
        }
        return settings;
    }

    public String getFullArrayString() {
        return this.name + (this.getDisplayInfo() != null ? ChatFormatting.GOLD + "[" + this.getDisplayInfo().toUpperCase() + "]" : "");
    }

    public Setting getSettingByName(String name) {
        for (Setting setting : this.getSettings()) {
            if (setting.getName().equalsIgnoreCase(name)) {
                return setting;
            }
        }
        return null;
    }

    public enum Category {
        CHAT("Chat"),
        COMBAT("Combat"),
        MISC("Misc"),
        RENDER("Render"),
        PLAYER("Player"),
        CLIENT("Client");

        private final String name;

        Category(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

}
