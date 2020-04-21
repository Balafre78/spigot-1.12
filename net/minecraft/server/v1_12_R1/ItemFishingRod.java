/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import org.bukkit.entity.Fish;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerFishEvent;
/*    */ 
/*    */ public class ItemFishingRod extends Item {
/*    */   public ItemFishingRod() {
/*  8 */     setMaxDurability(64);
/*  9 */     d(1);
/* 10 */     b(CreativeModeTab.i);
/* 11 */     a(new MinecraftKey("cast"), new IDynamicTexture() {  }
/*    */       );
/*    */   }
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 16 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */     
/* 18 */     if (entityhuman.hookedFish != null) {
/* 19 */       int i = entityhuman.hookedFish.j();
/*    */       
/* 21 */       itemstack.damage(i, entityhuman);
/* 22 */       entityhuman.a(enumhand);
/* 23 */       world.a((EntityHuman)null, entityhuman.locX, entityhuman.locY, entityhuman.locZ, SoundEffects.J, SoundCategory.NEUTRAL, 1.0F, 0.4F / (ItemFishingRod.j.nextFloat() * 0.4F + 0.8F));
/*    */     } else {
/*    */       
/* 26 */       if (!world.isClientSide) {
/* 27 */         EntityFishingHook entityfishinghook = new EntityFishingHook(world, entityhuman);
/* 28 */         int j = EnchantmentManager.c(itemstack);
/*    */         
/* 30 */         if (j > 0) {
/* 31 */           entityfishinghook.a(j);
/*    */         }
/*    */         
/* 34 */         int k = EnchantmentManager.b(itemstack);
/*    */         
/* 36 */         if (k > 0) {
/* 37 */           entityfishinghook.c(k);
/*    */         }
/*    */ 
/*    */         
/* 41 */         PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)entityhuman.getBukkitEntity(), null, (Fish)entityfishinghook.getBukkitEntity(), PlayerFishEvent.State.FISHING);
/* 42 */         world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/*    */         
/* 44 */         if (playerFishEvent.isCancelled()) {
/* 45 */           entityhuman.hookedFish = null;
/* 46 */           return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*    */         } 
/* 48 */         world.a((EntityHuman)null, entityhuman.locX, entityhuman.locY, entityhuman.locZ, SoundEffects.L, SoundCategory.NEUTRAL, 0.5F, 0.4F / (ItemFishingRod.j.nextFloat() * 0.4F + 0.8F));
/*    */ 
/*    */         
/* 51 */         world.addEntity(entityfishinghook);
/*    */       } 
/*    */       
/* 54 */       entityhuman.a(enumhand);
/* 55 */       entityhuman.b(StatisticList.b(this));
/*    */     } 
/*    */     
/* 58 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
/*    */   }
/*    */   
/*    */   public int c() {
/* 62 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemFishingRod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */