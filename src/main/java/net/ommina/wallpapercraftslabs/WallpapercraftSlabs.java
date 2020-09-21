package net.ommina.wallpapercraftslabs;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.ommina.wallpapercraftslabs.network.Network;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.ommina.wallpapercraftslabs.WallpapercraftSlabs.MODID;

@Mod( MODID )
public class WallpapercraftSlabs {

    public static final String MODID = "wallpapercraftslabs";
    public static final Logger LOGGER = LogManager.getLogger();

    public WallpapercraftSlabs() {

        MinecraftForge.EVENT_BUS.register( this );

        FMLJavaModLoadingContext.get().getModEventBus().addListener( this::setup );

    }

    public static ResourceLocation getId( String path ) {
        return new ResourceLocation( MODID, path );
    }

    private void setup( final FMLCommonSetupEvent event ) {
        Network.init();
    }

}
