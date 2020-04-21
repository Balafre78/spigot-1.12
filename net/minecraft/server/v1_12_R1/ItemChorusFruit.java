/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerTeleportEvent;
/*    */ 
/*    */ public class ItemChorusFruit
/*    */   extends ItemFood {
/*    */   public ItemChorusFruit(int i, float f) {
/* 12 */     super(i, f, false);
/*    */   }
/*    */   
/*    */   public ItemStack a(ItemStack itemstack, World world, EntityLiving entityliving) {
/* 16 */     ItemStack itemstack1 = super.a(itemstack, world, entityliving);
/*    */     
/* 18 */     if (!world.isClientSide) {
/* 19 */       double d0 = entityliving.locX;
/* 20 */       double d1 = entityliving.locY;
/* 21 */       double d2 = entityliving.locZ;
/*    */       
/* 23 */       for (int i = 0; i < 16; i++) {
/* 24 */         double d3 = entityliving.locX + (entityliving.getRandom().nextDouble() - 0.5D) * 16.0D;
/* 25 */         double d4 = MathHelper.a(entityliving.locY + (entityliving.getRandom().nextInt(16) - 8), 0.0D, (world.ab() - 1));
/* 26 */         double d5 = entityliving.locZ + (entityliving.getRandom().nextDouble() - 0.5D) * 16.0D;
/*    */ 
/*    */         
/* 29 */         if (entityliving instanceof EntityPlayer) {
/* 30 */           CraftPlayer craftPlayer = ((EntityPlayer)entityliving).getBukkitEntity();
/* 31 */           PlayerTeleportEvent teleEvent = new PlayerTeleportEvent((Player)craftPlayer, craftPlayer.getLocation(), new Location(craftPlayer.getWorld(), d3, d4, d5), PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT);
/* 32 */           world.getServer().getPluginManager().callEvent((Event)teleEvent);
/* 33 */           if (teleEvent.isCancelled()) {
/*    */             break;
/*    */           }
/* 36 */           d3 = teleEvent.getTo().getX();
/* 37 */           d4 = teleEvent.getTo().getY();
/* 38 */           d5 = teleEvent.getTo().getZ();
/*    */         } 
/*    */ 
/*    */         
/* 42 */         if (entityliving.isPassenger()) {
/* 43 */           entityliving.stopRiding();
/*    */         }
/*    */         
/* 46 */         if (entityliving.j(d3, d4, d5)) {
/* 47 */           world.a((EntityHuman)null, d0, d1, d2, SoundEffects.ak, SoundCategory.PLAYERS, 1.0F, 1.0F);
/* 48 */           entityliving.a(SoundEffects.ak, 1.0F, 1.0F);
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/* 53 */       if (entityliving instanceof EntityHuman) {
/* 54 */         ((EntityHuman)entityliving).getCooldownTracker().a(this, 20);
/*    */       }
/*    */     } 
/*    */     
/* 58 */     return itemstack1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemChorusFruit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */