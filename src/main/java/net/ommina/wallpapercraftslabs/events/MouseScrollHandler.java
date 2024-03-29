package net.ommina.wallpapercraftslabs.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.ommina.wallpapercraft.util.MathUtil;
import net.ommina.wallpapercraftslabs.WallpapercraftSlabs;
import net.ommina.wallpapercraftslabs.blocks.ModBlocks;
import net.ommina.wallpapercraftslabs.blocks.WallpaperSlabBlock;
import net.ommina.wallpapercraftslabs.items.SlabItem;
import net.ommina.wallpapercraftslabs.network.Network;
import net.ommina.wallpapercraftslabs.network.VariantScrollRequest;

@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT )
public class MouseScrollHandler {

    @SubscribeEvent
    public static void onScroll( InputEvent.MouseScrollEvent event ) {

        final LocalPlayer player = Minecraft.getInstance().player;
        final ItemStack held = player.getMainHandItem();

        if ( !held.isEmpty() && held.getItem() instanceof SlabItem && player.isCrouching() && held.getItem().getRegistryName().getNamespace().equals( WallpapercraftSlabs.MODID ) ) {

            final int delta = MathUtil.clamp( (int) Math.round( event.getScrollDelta() ), -1, 1 );

            cycleVariant( held, delta );

            event.setCanceled( true );

        }

    }

    private static void cycleVariant( ItemStack stack, int delta ) {

        final WallpaperSlabBlock block = ModBlocks.SLABS_BLOCKS.get( stack.getItem().getRegistryName().getPath() );

        if ( block != null )
            Network.channel.sendToServer( new VariantScrollRequest( delta ) );

    }

}