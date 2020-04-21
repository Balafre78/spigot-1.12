/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class MobEffectAbsorption
/*    */   extends MobEffectList
/*    */ {
/*    */   protected MobEffectAbsorption(boolean paramBoolean, int paramInt) {
/*  8 */     super(paramBoolean, paramInt);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(EntityLiving paramEntityLiving, AttributeMapBase paramAttributeMapBase, int paramInt) {
/* 13 */     paramEntityLiving.setAbsorptionHearts(paramEntityLiving.getAbsorptionHearts() - (4 * (paramInt + 1)));
/* 14 */     super.a(paramEntityLiving, paramAttributeMapBase, paramInt);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(EntityLiving paramEntityLiving, AttributeMapBase paramAttributeMapBase, int paramInt) {
/* 19 */     paramEntityLiving.setAbsorptionHearts(paramEntityLiving.getAbsorptionHearts() + (4 * (paramInt + 1)));
/* 20 */     super.b(paramEntityLiving, paramAttributeMapBase, paramInt);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MobEffectAbsorption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */