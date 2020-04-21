/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
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
/*    */ public class ItemGlassBottle
/*    */   extends Item
/*    */ {
/*    */   public ItemGlassBottle() {
/* 25 */     b(CreativeModeTab.k);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 30 */     List<EntityAreaEffectCloud> list = paramWorld.a(EntityAreaEffectCloud.class, paramEntityHuman.getBoundingBox().g(2.0D), new Predicate<EntityAreaEffectCloud>(this)
/*    */         {
/*    */           public boolean a(@Nullable EntityAreaEffectCloud param1EntityAreaEffectCloud) {
/* 33 */             return (param1EntityAreaEffectCloud != null && param1EntityAreaEffectCloud.isAlive() && param1EntityAreaEffectCloud.y() instanceof EntityEnderDragon);
/*    */           }
/*    */         });
/*    */     
/* 37 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/*    */     
/* 39 */     if (!list.isEmpty()) {
/* 40 */       EntityAreaEffectCloud entityAreaEffectCloud = list.get(0);
/* 41 */       entityAreaEffectCloud.setRadius(entityAreaEffectCloud.getRadius() - 0.5F);
/*    */       
/* 43 */       paramWorld.a((EntityHuman)null, paramEntityHuman.locX, paramEntityHuman.locY, paramEntityHuman.locZ, SoundEffects.O, SoundCategory.NEUTRAL, 1.0F, 1.0F);
/* 44 */       return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, a(itemStack, paramEntityHuman, new ItemStack(Items.DRAGON_BREATH)));
/*    */     } 
/*    */     
/* 47 */     MovingObjectPosition movingObjectPosition = a(paramWorld, paramEntityHuman, true);
/* 48 */     if (movingObjectPosition == null) {
/* 49 */       return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemStack);
/*    */     }
/*    */     
/* 52 */     if (movingObjectPosition.type == MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/* 53 */       BlockPosition blockPosition = movingObjectPosition.a();
/*    */       
/* 55 */       if (!paramWorld.a(paramEntityHuman, blockPosition) || !paramEntityHuman.a(blockPosition.shift(movingObjectPosition.direction), movingObjectPosition.direction, itemStack)) {
/* 56 */         return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemStack);
/*    */       }
/* 58 */       if (paramWorld.getType(blockPosition).getMaterial() == Material.WATER) {
/* 59 */         paramWorld.a(paramEntityHuman, paramEntityHuman.locX, paramEntityHuman.locY, paramEntityHuman.locZ, SoundEffects.N, SoundCategory.NEUTRAL, 1.0F, 1.0F);
/* 60 */         return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, a(itemStack, paramEntityHuman, PotionUtil.a(new ItemStack(Items.POTION), Potions.b)));
/*    */       } 
/*    */     } 
/*    */     
/* 64 */     return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemStack);
/*    */   }
/*    */   
/*    */   protected ItemStack a(ItemStack paramItemStack1, EntityHuman paramEntityHuman, ItemStack paramItemStack2) {
/* 68 */     paramItemStack1.subtract(1);
/* 69 */     paramEntityHuman.b(StatisticList.b(this));
/* 70 */     if (paramItemStack1.isEmpty()) {
/* 71 */       return paramItemStack2;
/*    */     }
/* 73 */     if (!paramEntityHuman.inventory.pickup(paramItemStack2)) {
/* 74 */       paramEntityHuman.drop(paramItemStack2, false);
/*    */     }
/* 76 */     return paramItemStack1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemGlassBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */