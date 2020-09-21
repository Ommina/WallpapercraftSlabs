package net.ommina.wallpapercraftslabs.blocks;

import net.minecraft.block.SlabBlock;

public class WallpaperSlabBlock extends SlabBlock implements IDecorativeBlock {

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
    public String getName() {
        return this.pattern + this.colour + this.suffix;
    }

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
//endregion Overrides

}
