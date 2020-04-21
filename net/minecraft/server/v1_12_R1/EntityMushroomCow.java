/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerShearEntityEvent;
/*    */ 
/*    */ public class EntityMushroomCow extends EntityCow {
/*    */   public EntityMushroomCow(World world) {
/*  9 */     super(world);
/* 10 */     setSize(0.9F, 1.4F);
/* 11 */     this.bA = Blocks.MYCELIUM;
/*    */   }
/*    */   
/*    */   public static void c(DataConverterManager dataconvertermanager) {
/* 15 */     EntityInsentient.a(dataconvertermanager, EntityMushroomCow.class);
/*    */   }
/*    */   
/*    */   public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/* 19 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */     
/* 21 */     if (itemstack.getItem() == Items.BOWL && getAge() >= 0 && !entityhuman.abilities.canInstantlyBuild) {
/* 22 */       itemstack.subtract(1);
/* 23 */       if (itemstack.isEmpty()) {
/* 24 */         entityhuman.a(enumhand, new ItemStack(Items.MUSHROOM_STEW));
/* 25 */       } else if (!entityhuman.inventory.pickup(new ItemStack(Items.MUSHROOM_STEW))) {
/* 26 */         entityhuman.drop(new ItemStack(Items.MUSHROOM_STEW), false);
/*    */       } 
/*    */       
/* 29 */       return true;
/* 30 */     }  if (itemstack.getItem() == Items.SHEARS && getAge() >= 0) {
/*    */       
/* 32 */       PlayerShearEntityEvent event = new PlayerShearEntityEvent((Player)entityhuman.getBukkitEntity(), (Entity)getBukkitEntity());
/* 33 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*    */       
/* 35 */       if (event.isCancelled()) {
/* 36 */         return false;
/*    */       }
/*    */       
/* 39 */       die();
/* 40 */       this.world.addParticle(EnumParticle.EXPLOSION_LARGE, this.locX, this.locY + (this.length / 2.0F), this.locZ, 0.0D, 0.0D, 0.0D, new int[0]);
/* 41 */       if (!this.world.isClientSide) {
/* 42 */         EntityCow entitycow = new EntityCow(this.world);
/*    */         
/* 44 */         entitycow.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
/* 45 */         entitycow.setHealth(getHealth());
/* 46 */         entitycow.aN = this.aN;
/* 47 */         if (hasCustomName()) {
/* 48 */           entitycow.setCustomName(getCustomName());
/*    */         }
/*    */         
/* 51 */         this.world.addEntity(entitycow);
/*    */         
/* 53 */         for (int i = 0; i < 5; i++) {
/* 54 */           this.world.addEntity(new EntityItem(this.world, this.locX, this.locY + this.length, this.locZ, new ItemStack(Blocks.RED_MUSHROOM)));
/*    */         }
/*    */         
/* 57 */         itemstack.damage(1, entityhuman);
/* 58 */         a(SoundEffects.ei, 1.0F, 1.0F);
/*    */       } 
/*    */       
/* 61 */       return true;
/*    */     } 
/* 63 */     return super.a(entityhuman, enumhand);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMushroomCow c(EntityAgeable entityageable) {
/* 68 */     return new EntityMushroomCow(this.world);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   protected MinecraftKey J() {
/* 73 */     return LootTables.M;
/*    */   }
/*    */   
/*    */   public EntityCow b(EntityAgeable entityageable) {
/* 77 */     return c(entityageable);
/*    */   }
/*    */   
/*    */   public EntityAgeable createChild(EntityAgeable entityageable) {
/* 81 */     return c(entityageable);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityMushroomCow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */