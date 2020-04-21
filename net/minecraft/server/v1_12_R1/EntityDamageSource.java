/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityDamageSource
/*    */   extends DamageSource
/*    */ {
/*    */   @Nullable
/*    */   protected Entity v;
/*    */   private boolean w;
/*    */   
/*    */   public EntityDamageSource(String paramString, @Nullable Entity paramEntity) {
/* 20 */     super(paramString);
/* 21 */     this.v = paramEntity;
/*    */   }
/*    */   
/*    */   public EntityDamageSource w() {
/* 25 */     this.w = true;
/* 26 */     return this;
/*    */   }
/*    */   
/*    */   public boolean x() {
/* 30 */     return this.w;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity getEntity() {
/* 36 */     return this.v;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent getLocalizedDeathMessage(EntityLiving paramEntityLiving) {
/* 41 */     ItemStack itemStack = (this.v instanceof EntityLiving) ? ((EntityLiving)this.v).getItemInMainHand() : ItemStack.a;
/* 42 */     String str1 = "death.attack." + this.translationIndex;
/* 43 */     String str2 = str1 + ".item";
/*    */     
/* 45 */     if (!itemStack.isEmpty() && itemStack.hasName() && LocaleI18n.c(str2)) {
/* 46 */       return new ChatMessage(str2, new Object[] { paramEntityLiving.getScoreboardDisplayName(), this.v.getScoreboardDisplayName(), itemStack.C() });
/*    */     }
/* 48 */     return new ChatMessage(str1, new Object[] { paramEntityLiving.getScoreboardDisplayName(), this.v.getScoreboardDisplayName() });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean r() {
/* 54 */     return (this.v != null && this.v instanceof EntityLiving && !(this.v instanceof EntityHuman));
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D v() {
/* 60 */     return new Vec3D(this.v.locX, this.v.locY, this.v.locZ);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */