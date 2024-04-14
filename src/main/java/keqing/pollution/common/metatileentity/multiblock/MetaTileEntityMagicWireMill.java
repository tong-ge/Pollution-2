package keqing.pollution.common.metatileentity.multiblock;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Material;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;

import keqing.pollution.api.metatileentity.POMultiblockAbility;
import keqing.pollution.api.metatileentity.PORecipeMapMultiblockController;
import keqing.pollution.client.textures.POTextures;
import keqing.pollution.common.block.PollutionMetaBlock.POGlass;
import keqing.pollution.common.block.PollutionMetaBlock.POMBeamCore;
import keqing.pollution.common.block.PollutionMetaBlock.POMagicBlock;
import keqing.pollution.common.block.PollutionMetaBlock.POTurbine;
import keqing.pollution.common.block.PollutionMetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import static keqing.pollution.api.unification.PollutionMaterials.*;

public class MetaTileEntityMagicWireMill extends PORecipeMapMultiblockController{
    public MetaTileEntityMagicWireMill(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, new RecipeMap[] {RecipeMaps.WIREMILL_RECIPES});
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityMagicWireMill(this.metaTileEntityId);
    }

    @Override
    protected  BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "XXGGG", "XXXXX")
                .aisle("XXXXX", "XACCG", "XXXXX")
                .aisle("XXXXX", "XSGGG", "XFXXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(25).or(autoAbilities()))
                .where('C', states(getCasingState2()))
                .where('G', states(getCasingState3()))
                .where('F', abilities(POMultiblockAbility.VIS_HATCH).setMaxGlobalLimited(1).setPreviewCount(1))
                .where('A', air())
                .where('#', any())
                .build();
    }

    @Override
    public Material getMaterial() {return infused_instrument; }

    private static IBlockState getCasingState() {
        return PollutionMetaBlocks.MAGIC_BLOCK.getState(POMagicBlock.MagicBlockType.SPELL_PRISM_ORDER);
    }
    private static IBlockState getCasingState2(){
        return PollutionMetaBlocks.BEAM_CORE.getState(POMBeamCore.MagicBlockType.BEAM_CORE_2);
    }

    private static IBlockState getCasingState3() {
        return PollutionMetaBlocks.GLASS.getState(POGlass.MagicBlockType.AAMINATED_GLASS);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return POTextures.SPELL_PRISM_ORDER;
    }

    @Override
    protected  OrientedOverlayRenderer getFrontOverlay() {
        return Textures.HPCA_OVERLAY;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }
}
