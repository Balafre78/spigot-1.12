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
/*    */ public class ItemFireworks
/*    */   extends Item
/*    */ {
/*    */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 42 */     if (!paramWorld.isClientSide) {
/* 43 */       ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 44 */       EntityFireworks entityFireworks = new EntityFireworks(paramWorld, (paramBlockPosition.getX() + paramFloat1), (paramBlockPosition.getY() + paramFloat2), (paramBlockPosition.getZ() + paramFloat3), itemStack);
/* 45 */       paramWorld.addEntity(entityFireworks);
/*    */       
/* 47 */       if (!paramEntityHuman.abilities.canInstantlyBuild) {
/* 48 */         itemStack.subtract(1);
/*    */       }
/*    */     } 
/* 51 */     return EnumInteractionResult.SUCCESS;
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 56 */     if (paramEntityHuman.cP()) {
/* 57 */       ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 58 */       if (!paramWorld.isClientSide) {
/* 59 */         EntityFireworks entityFireworks = new EntityFireworks(paramWorld, itemStack, paramEntityHuman);
/* 60 */         paramWorld.addEntity(entityFireworks);
/* 61 */         if (!paramEntityHuman.abilities.canInstantlyBuild) {
/* 62 */           itemStack.subtract(1);
/*    */         }
/*    */       } 
/*    */       
/* 66 */       return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, paramEntityHuman.b(paramEnumHand));
/*    */     } 
/* 68 */     return new InteractionResultWrapper<>(EnumInteractionResult.PASS, paramEntityHuman.b(paramEnumHand));
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemFireworks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */