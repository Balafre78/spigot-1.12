/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSeeds
/*    */   extends Item
/*    */ {
/*    */   private final Block a;
/*    */   private final Block b;
/*    */   
/*    */   public ItemSeeds(Block paramBlock1, Block paramBlock2) {
/* 18 */     this.a = paramBlock1;
/* 19 */     this.b = paramBlock2;
/* 20 */     b(CreativeModeTab.l);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 25 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 26 */     if (paramEnumDirection != EnumDirection.UP || !paramEntityHuman.a(paramBlockPosition.shift(paramEnumDirection), paramEnumDirection, itemStack) || paramWorld.getType(paramBlockPosition).getBlock() != this.b || !paramWorld.isEmpty(paramBlockPosition.up())) {
/* 27 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 30 */     paramWorld.setTypeUpdate(paramBlockPosition.up(), this.a.getBlockData());
/* 31 */     if (paramEntityHuman instanceof EntityPlayer) {
/* 32 */       CriterionTriggers.x.a((EntityPlayer)paramEntityHuman, paramBlockPosition.up(), itemStack);
/*    */     }
/* 34 */     itemStack.subtract(1);
/* 35 */     return EnumInteractionResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemSeeds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */