package net.ommina.wallpapercraftslabs.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.ommina.wallpapercraft.blocks.IDecorativeBlock;
import net.ommina.wallpapercraft.blocks.InWorldHelper;
import net.ommina.wallpapercraft.items.ModItems;
import net.ommina.wallpapercraft.items.PressColour;
import net.ommina.wallpapercraft.items.PressVariant;
import net.ommina.wallpapercraft.sounds.ModSoundType;

import javax.annotation.Nullable;

public class WallpaperSlabBlock extends SlabBlock implements IDecorativeBlock {

    private static final String POSTFIX = "";
    private final String pattern;
    private final String colour;
    private final String suffix;

    public WallpaperSlabBlock( final String pattern, final String colour, final int suffix, Properties properties ) {
        super( properties );

        this.pattern = pattern;
        this.colour = colour;
        this.suffix = "-" + suffix;

    }

    //region Overrides
    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public String getColour() {
        return this.colour;
    }

    @Override
    public String getSuffix() {
        return this.suffix;
    }

    @Override
    public SoundType getSoundType( final BlockState state, final IWorldReader world, final BlockPos pos, @Nullable final Entity entity ) {

        if ( !(entity instanceof PlayerEntity) )
            return SoundType.STONE;

        final PlayerEntity player = (PlayerEntity) entity;

        if ( player.getHeldItemMainhand().isEmpty() )
            return SoundType.STONE;

        if ( player.getHeldItemMainhand().getItem() == ModItems.PAINTBRUSH || player.getHeldItemMainhand().getItem() instanceof PressColour || player.getHeldItemMainhand().getItem() instanceof PressVariant )
            return ModSoundType.BLOCK_CHANGE;

        return SoundType.STONE;

    }

    @Override
    public void onBlockClicked( final BlockState state, final World world, final BlockPos pos, final PlayerEntity player ) {

        Block block = Blocks.AIR;

        if ( player.getHeldItemMainhand().getItem() == ModItems.PAINTBRUSH )
            block = InWorldHelper.getIncrementedBlockColour( this );
        else if ( player.getHeldItemMainhand().getItem() instanceof PressColour )
            block = InWorldHelper.getBlockFromColourPress( this, (PressColour) player.getHeldItemMainhand().getItem() );
        else if ( player.getHeldItemMainhand().getItem() instanceof PressVariant )
            block = InWorldHelper.getBlockFromVariantPress( this, (PressVariant) player.getHeldItemMainhand().getItem() );

        if ( block == Blocks.AIR )
            return;

        BlockState newState = block.getStateContainer().getBaseState()
             .with( SlabBlock.TYPE, state.get( SlabBlock.TYPE ) )
             .with( SlabBlock.WATERLOGGED, state.get( SlabBlock.WATERLOGGED ) );

        if ( !world.isRemote )
            world.setBlockState( pos, newState, 3 );

    }
//endregion Overrides

    @Override
    public String getPostfix() {
        return POSTFIX;
    }

    public String getNameForRegistry() {
        return this.pattern + this.colour + this.suffix;
    }

}
