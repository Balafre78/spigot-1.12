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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPotion
/*    */   extends Item
/*    */ {
/*    */   public ItemPotion() {
/* 27 */     d(1);
/* 28 */     b(CreativeModeTab.k);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityLiving paramEntityLiving) {
/* 38 */     EntityHuman entityHuman = (paramEntityLiving instanceof EntityHuman) ? (EntityHuman)paramEntityLiving : null;
/*    */     
/* 40 */     if (entityHuman == null || !entityHuman.abilities.canInstantlyBuild) {
/* 41 */       paramItemStack.subtract(1);
/*    */     }
/*    */     
/* 44 */     if (entityHuman instanceof EntityPlayer) {
/* 45 */       CriterionTriggers.y.a((EntityPlayer)entityHuman, paramItemStack);
/*    */     }
/* 47 */     if (!paramWorld.isClientSide) {
/* 48 */       List<MobEffect> list = PotionUtil.getEffects(paramItemStack);
/* 49 */       for (MobEffect mobEffect : list) {
/* 50 */         if (mobEffect.getMobEffect().isInstant()) {
/* 51 */           mobEffect.getMobEffect().applyInstantEffect(entityHuman, entityHuman, paramEntityLiving, mobEffect.getAmplifier(), 1.0D); continue;
/*    */         } 
/* 53 */         paramEntityLiving.addEffect(new MobEffect(mobEffect));
/*    */       } 
/*    */     } 
/*    */     
/* 57 */     if (entityHuman != null) {
/* 58 */       entityHuman.b(StatisticList.b(this));
/*    */     }
/* 60 */     if (entityHuman == null || !entityHuman.abilities.canInstantlyBuild) {
/* 61 */       if (paramItemStack.isEmpty())
/* 62 */         return new ItemStack(Items.GLASS_BOTTLE); 
/* 63 */       if (entityHuman != null) {
/* 64 */         entityHuman.inventory.pickup(new ItemStack(Items.GLASS_BOTTLE));
/*    */       }
/*    */     } 
/*    */     
/* 68 */     return paramItemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public int e(ItemStack paramItemStack) {
/* 73 */     return 32;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumAnimation f(ItemStack paramItemStack) {
/* 78 */     return EnumAnimation.DRINK;
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 83 */     paramEntityHuman.c(paramEnumHand);
/* 84 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, paramEntityHuman.b(paramEnumHand));
/*    */   }
/*    */ 
/*    */   
/*    */   public String b(ItemStack paramItemStack) {
/* 89 */     return LocaleI18n.get(PotionUtil.d(paramItemStack).b("potion.effect."));
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */