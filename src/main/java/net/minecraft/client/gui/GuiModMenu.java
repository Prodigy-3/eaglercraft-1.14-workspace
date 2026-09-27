package net.minecraft.client.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

public class GuiModMenu extends Screen {

    private String currentCategory = "All";
    private boolean fullbrightEnabled = true;
    private boolean toggleSprintEnabled = false;
    private boolean toggleCrouchEnabled = false;
    private boolean armorHudEnabled = false;

    public GuiModMenu() {
        super(new StringTextComponent("Client Mod Menu"));
    }

    @Override
    protected void init() {
        this.buttons.clear();
        this.children.clear();

        int topY = 30;
        int tabWidth = 80;
        int startX = (this.width - (tabWidth * 4 + 15)) / 2;

        // Category Tabs
        this.addButton(new Button(startX, topY, tabWidth, 20, "All", (btn) -> {
            this.currentCategory = "All";
            this.init();
        }));
        this.addButton(new Button(startX + tabWidth + 5, topY, tabWidth, 20, "PvP", (btn) -> {
            this.currentCategory = "PvP";
            this.init();
        }));
        this.addButton(new Button(startX + (tabWidth + 5) * 2, topY, tabWidth, 20, "Optimization", (btn) -> {
            this.currentCategory = "Optimization";
            this.init();
        }));
        this.addButton(new Button(startX + (tabWidth + 5) * 3, topY, tabWidth, 20, "Utility", (btn) -> {
            this.currentCategory = "Utility";
            this.init();
        }));

        // Render Mod Toggle Buttons based on Category
        int modY = topY + 40;
        int modWidth = 200;
        int modX = (this.width - modWidth) / 2;

        if (this.currentCategory.equals("All") || this.currentCategory.equals("Utility")) {
            this.addButton(new Button(modX, modY, modWidth, 20, "Fullbright: " + (fullbrightEnabled ? "ON" : "OFF"), (btn) -> {
                this.fullbrightEnabled = !this.fullbrightEnabled;
                this.minecraft.gameSettings.gamma = this.fullbrightEnabled ? 10000.0F : 1.0F;
                btn.setMessage("Fullbright: " + (fullbrightEnabled ? "ON" : "OFF"));
            }));
            modY += 25;
        }

        if (this.currentCategory.equals("All") || this.currentCategory.equals("PvP")) {
            this.addButton(new Button(modX, modY, modWidth, 20, "Toggle Sprint: " + (toggleSprintEnabled ? "ON" : "OFF"), (btn) -> {
                this.toggleSprintEnabled = !this.toggleSprintEnabled;
                btn.setMessage("Toggle Sprint: " + (toggleSprintEnabled ? "ON" : "OFF"));
            }));
            modY += 25;

            this.addButton(new Button(modX, modY, modWidth, 20, "Toggle Crouch: " + (toggleCrouchEnabled ? "ON" : "OFF"), (btn) -> {
                this.toggleCrouchEnabled = !this.toggleCrouchEnabled;
                btn.setMessage("Toggle Crouch: " + (toggleCrouchEnabled ? "ON" : "OFF"));
            }));
            modY += 25;
        }

        if (this.currentCategory.equals("All") || this.currentCategory.equals("Optimization")) {
            this.addButton(new Button(modX, modY, modWidth, 20, "Armor HUD: " + (armorHudEnabled ? "ON" : "OFF"), (btn) -> {
                this.armorHudEnabled = !this.armorHudEnabled;
                btn.setMessage("Armor HUD: " + (armorHudEnabled ? "ON" : "OFF"));
            }));
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        drawCenteredString(this.font, "Modern Client Settings", this.width / 2, 12, 16777215);
        super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
