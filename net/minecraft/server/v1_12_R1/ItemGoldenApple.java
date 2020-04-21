/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemGoldenApple
/*    */   extends ItemFood
/*    */ {
/*    */   public ItemGoldenApple(int paramInt, float paramFloat, boolean paramBoolean) {
/* 12 */     super(paramInt, paramFloat, paramBoolean);
/*    */     
/* 14 */     a(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumItemRarity g(ItemStack paramItemStack) {
/* 24 */     if (paramItemStack.getData() == 0) {
/* 25 */       return EnumItemRarity.RARE;
/*    */     }
/* 27 */     return EnumItemRarity.EPIC;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman) {
/* 32 */     if (!paramWorld.isClientSide)
/* 33 */       if (paramItemStack.getData() > 0) {
/* 34 */         paramEntityHuman.addEffect(new MobEffect(MobEffects.REGENERATION, 400, 1));
/* 35 */         paramEntityHuman.addEffect(new MobEffect(MobEffects.RESISTANCE, 6000, 0));
/* 36 */         paramEntityHuman.addEffect(new MobEffect(MobEffects.FIRE_RESISTANCE, 6000, 0));
/* 37 */         paramEntityHuman.addEffect(new MobEffect(MobEffects.ABSORBTION, 2400, 3));
/*    */       } else {
/* 39 */         paramEntityHuman.addEffect(new MobEffect(MobEffects.REGENERATION, 100, 1));
/* 40 */         paramEntityHuman.addEffect(new MobEffect(MobEffects.ABSORBTION, 2400, 0));
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemGoldenApple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */