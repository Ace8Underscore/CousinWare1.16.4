package io.ace.nordclient.hacks.misc;

import io.ace.nordclient.event.PlayerJoinEvent;
import io.ace.nordclient.event.PlayerLeaveEvent;
import io.ace.nordclient.event.PlayerMoveEvent;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.managers.FriendManager;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingSlider;
import io.ace.nordclient.settings.SettingToggle;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.item.BlockItem;
import net.minecraft.item.EnchantedGoldenAppleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Ace________/Ace_#1233
 */

public class Spammer extends Hack {

    double updatedX = 0;
    double updatedZ = 0;
    double finalMovement;
    int msgDelay = 0;
    int placeDelay = 0;
    int inventoryDelay = 0;
    int chatDelay = 0;
    String statementBlock;
    boolean firstPlace = true;
    int timesPlaced = 0;
    String placedMessage;
    int joinLeaveMsg = 0;
    int eaten = 0;
    int eattingDelay;

    Item checkBlock;
    ItemStack checkBlock2;

    private static final List<SettingBase> settings = Arrays.asList(
            new SettingToggle(true, "Movement"),
            new SettingSlider(1, 750, 100, 1, "Delay"));


    public Spammer() {
        super("Spammer", Category.MISC, 664558, settings);
    }

    @Override
    public void onUpdate() {
        joinLeaveMsg++;
        msgDelay++;
        placeDelay++;
        eattingDelay++;
        inventoryDelay++;
        chatDelay++;
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        if (msgDelay >= settings.get(1).toSlider().getValue()) {
            if (finalMovement >= randomNum) {
                mc.player.sendChatMessage("> I Just Hopped " + Math.round(finalMovement * 10) / 10.00 + " Meters Like A Kangaroo Thanks To CousinWare!");
            }
            if (timesPlaced >= randomNum) {
                mc.player.sendChatMessage(placedMessage);
                firstPlace = true;
                timesPlaced = 0;
            }
//
            firstPlace = true;
            timesPlaced = 0;
            finalMovement = 0;
            msgDelay = 0;
        }
        if (mc.gameSettings.keyBindUseItem.isKeyDown()) {
            Item usedItem = mc.player.getHeldItemMainhand().getItem();
            ItemStack heldBlock = new ItemStack(mc.player.getHeldItemMainhand().getItem());
            if (usedItem instanceof BlockItem) {
                if (mc.player.isSwingInProgress) {
                    if (placeDelay > 5) {
                        if (firstPlace) {
                            statementBlock = usedItem.getDisplayName(heldBlock).getString();
                            checkBlock = usedItem;
                            checkBlock2 = heldBlock;
                            firstPlace = false;
                        } else {
                            if (usedItem.getDisplayName(heldBlock).toString().equals(checkBlock.getDisplayName(checkBlock2).getString())) {
                                timesPlaced += 1;
                                //mc.player.sendChatMessage(usedItem.getItemStackDisplayName(heldBlock) + "Placed " + timesPlaced);
                                //placedMessage = usedItem.getItemStackDisplayName(heldBlock) + "Placed " + timesPlaced;
                                placedMessage = "> I Just Placed " + timesPlaced + " " + usedItem.getDisplayName(heldBlock).toString() + " Thanks To Cousin Ware!";
                                //}
                            }
                            placeDelay = 0;

                        }
                    }
                }
            }

        }
        if (mc.currentScreen instanceof InventoryScreen && inventoryDelay >= 40) {
            mc.player.sendChatMessage("> I Just Opened Up My Inventory Thanks To CousinWare!");
            inventoryDelay = 0;
        }
        if (mc.currentScreen instanceof ChatScreen && chatDelay >= 40) {
            mc.player.sendChatMessage("> I Just Opened Up The Chat Thanks To CousinWare!");
            chatDelay = 0;
        }
        if (mc.currentScreen instanceof InventoryScreen || mc.currentScreen instanceof ChatScreen) {
            chatDelay = 0;
            inventoryDelay = 0;
        }
    }

    @EventHandler
    private final Listener<PlayerMoveEvent> listener = new Listener<>(event -> {
        getMovement();

    });

    @EventHandler
    private final Listener<PlayerJoinEvent> listener2 = new Listener<>(event -> {
        if (joinLeaveMsg > 30) {
            if (!FriendManager.isFriend(event.getName())) {
                mc.player.sendChatMessage("> Welcome " + event.getName() + " To " + mc.getCurrentServerData().serverIP);
            } else {
                mc.player.sendChatMessage("> My Friend " + event.getName() + " Has Just Joined!");
            }
            joinLeaveMsg = 0;
        }
    });

    @EventHandler
    private final Listener<PlayerLeaveEvent> listener3 = new Listener<>(event -> {
        if (joinLeaveMsg > 30) {
            if (!FriendManager.isFriend(event.getName())) {
                mc.player.sendChatMessage("> Fuck You " + event.getName() + " Im Glad you Left!");
            } else {
                mc.player.sendChatMessage("> My Friend " + event.getName() + " Just left ;(");
            }
            joinLeaveMsg = 0;
        }
    });

    @SubscribeEvent
    public void finishEating(LivingEntityUseItemEvent.Finish event) {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        if (event.getEntity() == mc.player) {
            if (event.getItem().getItem() instanceof EnchantedGoldenAppleItem) {
                eaten++;
                if (eaten >= randomNum) {
                    mc.player.sendChatMessage("> I Just Ate " + eaten + " GoldenApples Thanks To CousinWare!");

                    eaten = 0;
                    eattingDelay = 0;
                }
            }
        }
    }

    public double getMovement() {
        if (settings.get(0).toToggle().state) {
            double movementX = mc.player.getMotion().getX();
            double movementZ = mc.player.getMotion().getZ();

            if (movementX < 0) {
                updatedX = movementX * -1;
            } else {
                updatedX = movementX;
            }

            if (movementZ < 0) {
                updatedZ = movementZ * -1;
            } else {
                updatedZ = movementZ;
            }


            double updatedMovement = updatedX + updatedZ;
            finalMovement += updatedMovement;

        }
        return finalMovement;


    }

    public void onEnable() {
        firstPlace = true;
    }
}
