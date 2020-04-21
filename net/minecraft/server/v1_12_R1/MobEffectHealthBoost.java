/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class MobEffectHealthBoost
/*    */   extends MobEffectList
/*    */ {
/*    */   public MobEffectHealthBoost(boolean paramBoolean, int paramInt) {
/*  8 */     super(paramBoolean, paramInt);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(EntityLiving paramEntityLiving, AttributeMapBase paramAttributeMapBase, int paramInt) {
/* 13 */     super.a(paramEntityLiving, paramAttributeMapBase, paramInt);
/* 14 */     if (paramEntityLiving.getHealth() > paramEntityLiving.getMaxHealth())
/* 15 */       paramEntityLiving.setHealth(paramEntityLiving.getMaxHealth()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MobEffectHealthBoost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */