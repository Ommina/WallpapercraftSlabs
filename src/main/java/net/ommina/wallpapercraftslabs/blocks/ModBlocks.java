package net.ommina.wallpapercraftslabs.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.ommina.wallpapercraft.Wallpapercraft;
import net.ommina.wallpapercraftslabs.WallpapercraftSlabs;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD )
public class ModBlocks {

    public static final String[] COLOURS = { "blue", "brown", "cyan", "gray", "green", "purple", "red", "yellow" };
    public static final String[] SLABS = { "solid", "brick", "checkeredwool", "clay", "colouredbrick",
         "damask", "diagonallydotted", "dotted", "fancytiles", "floral", "rippled", "stonebrick", "striped", "woodplank", "wool" };

    public static final Map<String, WallpaperSlabBlock> SLABS_BLOCKS = new HashMap<String, WallpaperSlabBlock>();

    @SubscribeEvent
    public static void registerSlabs( final RegistryEvent.Register<Block> event ) {

        for ( String pattern : SLABS ) {

            for ( String colour : COLOURS ) {

                final int suffixCount = colour.equals( "cyan" ) ? 9 : 14;

                for ( int suffix = 0; suffix <= suffixCount; suffix++ ) {

                    String name = getName( pattern, colour, suffix );

                    Block sourceBlock = ForgeRegistries.BLOCKS.getValue( Wallpapercraft.getId( name ) );

                    if ( sourceBlock == null )
                        WallpapercraftSlabs.LOGGER.warn( "Source block not found: " + name + " -- unable to create slabs for it.  Panic!  This shouldn't happen!" );
                    else {

                        WallpaperSlabBlock block = new WallpaperSlabBlock( pattern, colour, suffix, Block.Properties.from( sourceBlock ) );
                        block.setRegistryName( WallpapercraftSlabs.getId( name ) );

                        event.getRegistry().register( block );
                        SLABS_BLOCKS.put( name, block );

                    }

                }
            }

        }

    }

    private static String getName( String pattern, String colour, int suffix ) {
        return pattern + colour + "-" + suffix;
    }

}
