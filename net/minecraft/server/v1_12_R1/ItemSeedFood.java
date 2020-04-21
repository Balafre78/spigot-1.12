/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSeedFood
/*    */   extends ItemFood
/*    */ {
/*    */   private final Block b;
/*    */   private final Block c;
/*    */   
/*    */   public ItemSeedFood(int paramInt, float paramFloat, Block paramBlock1, Block paramBlock2) {
/* 16 */     super(paramInt, paramFloat, false);
/*    */     
/* 18 */     this.b = paramBlock1;
/* 19 */     this.c = paramBlock2;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 24 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 25 */     if (paramEnumDirection != EnumDirection.UP || !paramEntityHuman.a(paramBlockPosition.shift(paramEnumDirection), paramEnumDirection, itemStack) || paramWorld.getType(paramBlockPosition).getBlock() != this.c || !paramWorld.isEmpty(paramBlockPosition.up())) {
/* 26 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 29 */     paramWorld.setTypeAndData(paramBlockPosition.up(), this.b.getBlockData(), 11);
/* 30 */     itemStack.subtract(1);
/* 31 */     return EnumInteractionResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemSeedFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */