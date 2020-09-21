package net.ommina.wallpapercraftslabs.items;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.ommina.wallpapercraft.Wallpapercraft;
import net.ommina.wallpapercraftslabs.WallpapercraftSlabs;
import net.ommina.wallpapercraftslabs.blocks.ModBlocks;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD )
@ObjectHolder( WallpapercraftSlabs.MODID )
public class ModItems {

    @SubscribeEvent
    public static void registerItems( final RegistryEvent.Register<Item> event ) {

        ModBlocks.SLABS_BLOCKS.keySet().stream().sorted().forEachOrdered( s ->
             event.getRegistry().register( new SlabItem( ModBlocks.SLABS_BLOCKS.get( s ), new Item.Properties().group( Wallpapercraft.TAB ) ).setRegistryName( s ) )
        );

    }

    @Nullable
    public static SlabItem get( final String pattern, final String colour, final int suffix ) {
        return (SlabItem) ForgeRegistries.ITEMS.getValue( WallpapercraftSlabs.getId( pattern + colour + "-" + suffix ) );
    }

}
