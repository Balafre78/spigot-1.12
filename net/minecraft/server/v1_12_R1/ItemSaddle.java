/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSaddle
/*    */   extends Item
/*    */ {
/*    */   public ItemSaddle() {
/* 12 */     this.maxStackSize = 1;
/* 13 */     b(CreativeModeTab.e);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack paramItemStack, EntityHuman paramEntityHuman, EntityLiving paramEntityLiving, EnumHand paramEnumHand) {
/* 18 */     if (paramEntityLiving instanceof EntityPig) {
/* 19 */       EntityPig entityPig = (EntityPig)paramEntityLiving;
/* 20 */       if (!entityPig.hasSaddle() && !entityPig.isBaby()) {
/* 21 */         entityPig.setSaddle(true);
/* 22 */         entityPig.world.a(paramEntityHuman, entityPig.locX, entityPig.locY, entityPig.locZ, SoundEffects.fr, SoundCategory.NEUTRAL, 0.5F, 1.0F);
/* 23 */         paramItemStack.subtract(1);
/*    */       } 
/*    */       
/* 26 */       return true;
/*    */     } 
/* 28 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemSaddle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */