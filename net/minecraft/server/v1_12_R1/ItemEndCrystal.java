/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.List;
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
/*    */ public class ItemEndCrystal
/*    */   extends Item
/*    */ {
/*    */   public ItemEndCrystal() {
/* 21 */     c("end_crystal");
/* 22 */     b(CreativeModeTab.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 27 */     IBlockData iBlockData = paramWorld.getType(paramBlockPosition);
/* 28 */     if (iBlockData.getBlock() != Blocks.OBSIDIAN && iBlockData.getBlock() != Blocks.BEDROCK) {
/* 29 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 32 */     BlockPosition blockPosition1 = paramBlockPosition.up();
/* 33 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 34 */     if (!paramEntityHuman.a(blockPosition1, paramEnumDirection, itemStack)) {
/* 35 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 38 */     BlockPosition blockPosition2 = blockPosition1.up();
/* 39 */     int i = (!paramWorld.isEmpty(blockPosition1) && !paramWorld.getType(blockPosition1).getBlock().a(paramWorld, blockPosition1)) ? 1 : 0;
/* 40 */     i |= (!paramWorld.isEmpty(blockPosition2) && !paramWorld.getType(blockPosition2).getBlock().a(paramWorld, blockPosition2)) ? 1 : 0;
/* 41 */     if (i != 0) {
/* 42 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 45 */     double d1 = blockPosition1.getX();
/* 46 */     double d2 = blockPosition1.getY();
/* 47 */     double d3 = blockPosition1.getZ();
/*    */     
/* 49 */     List<Entity> list = paramWorld.getEntities(null, new AxisAlignedBB(d1, d2, d3, d1 + 1.0D, d2 + 2.0D, d3 + 1.0D));
/* 50 */     if (!list.isEmpty()) {
/* 51 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 54 */     if (!paramWorld.isClientSide) {
/* 55 */       EntityEnderCrystal entityEnderCrystal = new EntityEnderCrystal(paramWorld, (paramBlockPosition.getX() + 0.5F), (paramBlockPosition.getY() + 1), (paramBlockPosition.getZ() + 0.5F));
/* 56 */       entityEnderCrystal.setShowingBottom(false);
/* 57 */       paramWorld.addEntity(entityEnderCrystal);
/*    */       
/* 59 */       if (paramWorld.worldProvider instanceof WorldProviderTheEnd) {
/* 60 */         EnderDragonBattle enderDragonBattle = ((WorldProviderTheEnd)paramWorld.worldProvider).t();
/* 61 */         enderDragonBattle.e();
/*    */       } 
/*    */     } 
/* 64 */     itemStack.subtract(1);
/* 65 */     return EnumInteractionResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemEndCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */