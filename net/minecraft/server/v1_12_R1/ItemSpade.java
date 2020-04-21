/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSpade
/*    */   extends ItemTool
/*    */ {
/* 20 */   private static final Set<Block> e = Sets.newHashSet((Object[])new Block[] { Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.dS });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemSpade(Item.EnumToolMaterial paramEnumToolMaterial) {
/* 36 */     super(1.5F, -3.0F, paramEnumToolMaterial, e);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canDestroySpecialBlock(IBlockData paramIBlockData) {
/* 41 */     Block block = paramIBlockData.getBlock();
/* 42 */     if (block == Blocks.SNOW_LAYER) {
/* 43 */       return true;
/*    */     }
/* 45 */     if (block == Blocks.SNOW) {
/* 46 */       return true;
/*    */     }
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 53 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 54 */     if (!paramEntityHuman.a(paramBlockPosition.shift(paramEnumDirection), paramEnumDirection, itemStack)) {
/* 55 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 58 */     IBlockData iBlockData1 = paramWorld.getType(paramBlockPosition);
/* 59 */     Block block = iBlockData1.getBlock();
/*    */     
/* 61 */     if (paramEnumDirection == EnumDirection.DOWN || paramWorld.getType(paramBlockPosition.up()).getMaterial() != Material.AIR || block != Blocks.GRASS) {
/* 62 */       return EnumInteractionResult.PASS;
/*    */     }
/*    */     
/* 65 */     IBlockData iBlockData2 = Blocks.GRASS_PATH.getBlockData();
/* 66 */     paramWorld.a(paramEntityHuman, paramBlockPosition, SoundEffects.gz, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*    */     
/* 68 */     if (!paramWorld.isClientSide) {
/* 69 */       paramWorld.setTypeAndData(paramBlockPosition, iBlockData2, 11);
/* 70 */       itemStack.damage(1, paramEntityHuman);
/*    */     } 
/* 72 */     return EnumInteractionResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemSpade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */