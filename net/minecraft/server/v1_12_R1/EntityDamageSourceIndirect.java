/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class EntityDamageSourceIndirect
/*    */   extends EntityDamageSource {
/*    */   private final Entity owner;
/*    */   
/*    */   public EntityDamageSourceIndirect(String s, Entity entity, @Nullable Entity entity1) {
/* 10 */     super(s, entity);
/* 11 */     this.owner = entity1;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Entity i() {
/* 16 */     return this.v;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Entity getEntity() {
/* 21 */     return this.owner;
/*    */   }
/*    */   
/*    */   public IChatBaseComponent getLocalizedDeathMessage(EntityLiving entityliving) {
/* 25 */     IChatBaseComponent ichatbasecomponent = (this.owner == null) ? this.v.getScoreboardDisplayName() : this.owner.getScoreboardDisplayName();
/* 26 */     ItemStack itemstack = (this.owner instanceof EntityLiving) ? ((EntityLiving)this.owner).getItemInMainHand() : ItemStack.a;
/* 27 */     String s = "death.attack." + this.translationIndex;
/* 28 */     String s1 = String.valueOf(s) + ".item";
/*    */     
/* 30 */     return (!itemstack.isEmpty() && itemstack.hasName() && LocaleI18n.c(s1)) ? new ChatMessage(s1, new Object[] { entityliving.getScoreboardDisplayName(), ichatbasecomponent, itemstack.C() }) : new ChatMessage(s, new Object[] { entityliving.getScoreboardDisplayName(), ichatbasecomponent });
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity getProximateDamageSource() {
/* 35 */     return super.getEntity();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityDamageSourceIndirect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */