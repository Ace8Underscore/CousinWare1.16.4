package io.ace.nordclient.hacks.player;

import io.ace.nordclient.event.RenderEvent;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.settings.SettingBase;
import io.ace.nordclient.settings.SettingMode;
import io.ace.nordclient.utilz.BlockInteractionHelper;
import io.ace.nordclient.utilz.NordTessellator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ace________/Ace_#1233
 */

public class Scaffold extends Hack {
    int playerHotbarSlot = -1;
    int lastHotbarSlot = -1;

    private static final List<SettingBase> settings = Arrays.asList(new SettingMode("PlaceModes", "Old", "New"));

    public Scaffold() {
        super("Scaffold", Category.PLAYER, 8501181, settings);
    }

    @Override
    public void onUpdate() {
        BlockPos below = new BlockPos(mc.player.getPosX(), mc.player.getPosY() - 1, mc.player.getPosZ());
        BlockPos belowSouth = new BlockPos(mc.player.getPosX(), mc.player.getPosY() - 1, mc.player.getPosZ() - 1);
        BlockPos belowNorth = new BlockPos(mc.player.getPosX(), mc.player.getPosY() - 1, mc.player.getPosZ() + 1);
        BlockPos belowEast = new BlockPos(mc.player.getPosX() - 1, mc.player.getPosY() - 1, mc.player.getPosZ());
        BlockPos belowWest = new BlockPos(mc.player.getPosX() + 1, mc.player.getPosY() - 1, mc.player.getPosZ());
        if (this.getSettings().get(0).toMode().getModeValue(getSettings().get(0).toMode().mode).equalsIgnoreCase("new")) {
            findDirectionLookingPlace();
            if (mc.player.getMotion().getX() == 0 && mc.player.getMotion().getZ() == 0) {
                if (mc.gameSettings.keyBindJump.isKeyDown()) {


                    }
                }
        } else {
            // south -z
            // north +z
            //east - x
            // west +x
            int blockSlot = this.findBlockInHotbar();
            if (blockSlot > 1) {
                if (blockSlot == -1) {

                }
                if (this.lastHotbarSlot != blockSlot) {
                    mc.player.inventory.currentItem = blockSlot;
                    this.lastHotbarSlot = blockSlot;
                }

                if (mc.world.getBlockState(below).getMaterial().isReplaceable()) {
                    BlockInteractionHelper.placeBlockScaffold(below);
                }
            }
        }

    }

    @Override
    public void onWorldRender(RenderEvent event) {
        BlockPos below = new BlockPos(mc.player.getPosX(), mc.player.getPosY() - 1, mc.player.getPosZ());
        BlockPos belowSouth = new BlockPos(mc.player.getPosX(), mc.player.getPosY() - 1, mc.player.getPosZ() - 1);
        BlockPos belowNorth = new BlockPos(mc.player.getPosX(), mc.player.getPosY() - 1, mc.player.getPosZ() + 1);
        BlockPos belowEast = new BlockPos(mc.player.getPosX() - 1, mc.player.getPosY() - 1, mc.player.getPosZ());
        BlockPos belowWest = new BlockPos(mc.player.getPosX() + 1, mc.player.getPosY() - 1, mc.player.getPosZ());

        NordTessellator.drawBoundingBoxBlockPos(below, 1, 81, 205, 159, 255);
    }

    @Override
    public void onEnable() {
        this.playerHotbarSlot = mc.player.inventory.currentItem;
        this.lastHotbarSlot = -1;
    }

    @Override
    public void onDisable() {
        if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
            mc.player.inventory.currentItem = this.playerHotbarSlot;
        }
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;


    }


    private int findBlockInHotbar() {

        int slot = -1;
        for (int i = 0; i < 9; i++) {


            ItemStack stack = mc.player.inventory.getStackInSlot(i);

            if (stack == ItemStack.EMPTY || !(stack.getItem() instanceof BlockItem)) {
                continue;
            }
            slot = i;
            break;

        }

        return slot;

    }

    private void findDirectionLookingPlace() {
        BlockPos below = new BlockPos(mc.player.getPosX(), mc.player.getPosY() - 1, mc.player.getPosZ());
        BlockPos belowSouth = new BlockPos(mc.player.getPosX(), mc.player.getPosY() - 1, mc.player.getPosZ() - 1);
        BlockPos belowNorth = new BlockPos(mc.player.getPosX(), mc.player.getPosY() - 1, mc.player.getPosZ() + 1);
        BlockPos belowEast = new BlockPos(mc.player.getPosX() - 1, mc.player.getPosY() - 1, mc.player.getPosZ());
        BlockPos belowWest = new BlockPos(mc.player.getPosX() + 1, mc.player.getPosY() - 1, mc.player.getPosZ());
        int var24 = MathHelper.floor((double) (mc.player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        //if (var24 == 2) {
            //mc.player.sendChatMessage("Your Looking North");
            //BlockInteractionHelper.placeBlockScaffold(belowNorth);
            if (mc.world.getBlockState(belowSouth).getMaterial().isReplaceable()) {
                if (mc.player.getMotion().getZ() < -.05) {
                    BlockInteractionHelper.placeBlockScaffold(belowSouth);
                }
            //}

        }

        //if (var24 == 1) {
            //mc.player.sendChatMessage("Your Looking West");
            if (mc.world.getBlockState(belowEast).getMaterial().isReplaceable()) {
                if (mc.player.getMotion().getX() < -.05) {
                    BlockInteractionHelper.placeBlockScaffold(belowEast);
               // }
            }
        }
        if (var24 == 3) {
            //mc.player.sendChatMessage("Your Looking East");
            if (mc.world.getBlockState(belowWest).getMaterial().isReplaceable()) {
                if (mc.player.getMotion().getX() > .05) {

                    BlockInteractionHelper.placeBlockScaffold(belowWest);
                }
            }
        }
       // if (var24 == 0) {
            //mc.player.sendChatMessage("Your Looking South");
            if (mc.world.getBlockState(belowNorth).getMaterial().isReplaceable()) {
                if (mc.player.getMotion().getZ() > .05) {
                    BlockInteractionHelper.placeBlockScaffold(belowNorth);

               // }//
            }
        }
        if (mc.player.getMotion().getX() == 0 && mc.player.getMotion().getZ() == 0) {
            if (mc.world.getBlockState(below).getMaterial().isReplaceable()) {
                if (mc.gameSettings.keyBindJump.isKeyDown()) {
                    //MotionUtil.setMotionY(.3600);
                    BlockInteractionHelper.placeBlockScaffold(below);
                }
            }
        }
    }
}