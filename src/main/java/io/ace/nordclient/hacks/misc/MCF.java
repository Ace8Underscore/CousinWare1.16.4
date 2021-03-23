package io.ace.nordclient.hacks.misc;

import io.ace.nordclient.command.Command;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.managers.FriendManager;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class MCF extends Hack {


    public MCF() {
        super("MCF", Category.MISC, 15756204, null);

    }

    @SubscribeEvent
    public void middleClick(InputEvent.MouseInputEvent event) {
        if (event.getButton() == 2 && event.getAction() == 1) {
            RayTraceResult object = mc.objectMouseOver;
            if (object == null) {
                return;
            }
            if (object.getType() == RayTraceResult.Type.ENTITY) {
                Object entity = mc.objectMouseOver.hitInfo;
                if (entity instanceof PlayerEntity && !(entity instanceof ArmorStandEntity) && !mc.player.isShowDeathScreen() && mc.player.canEntityBeSeen(((PlayerEntity) entity).getEntity())) {
                    PlayerEntity player = (PlayerEntity) entity;
                    String ID = ((PlayerEntity) entity).getEntity().getScoreboardName();

                    if (mc.currentScreen == null && !FriendManager.isFriend(ID)) {
                        FriendManager.addFriend(ID);
                        Command.sendClientSideMessage(TextFormatting.GREEN + ID + TextFormatting.WHITE + " Has Been Added To The Friends List ");

                    }
                    if (mc.currentScreen == null && FriendManager.isFriend(ID)) {
                        FriendManager.removeFriend(ID);
                        Command.sendClientSideMessage(TextFormatting.DARK_RED + ID + " Has Been UnFriended");
                    }
                }
            }
        }

    }

}


