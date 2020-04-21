/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
/*    */ import org.bukkit.entity.Explosive;
/*    */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*    */ 
/*    */ public class EntityLargeFireball extends EntityFireball {
/*  7 */   public int yield = 1;
/*    */   
/*    */   public EntityLargeFireball(World world) {
/* 10 */     super(world);
/* 11 */     this.isIncendiary = this.world.getGameRules().getBoolean("mobGriefing");
/*    */   }
/*    */   
/*    */   public EntityLargeFireball(World world, EntityLiving entityliving, double d0, double d1, double d2) {
/* 15 */     super(world, entityliving, d0, d1, d2);
/* 16 */     this.isIncendiary = this.world.getGameRules().getBoolean("mobGriefing");
/*    */   }
/*    */   
/*    */   protected void a(MovingObjectPosition movingobjectposition) {
/* 20 */     if (!this.world.isClientSide) {
/* 21 */       if (movingobjectposition.entity != null) {
/* 22 */         movingobjectposition.entity.damageEntity(DamageSource.fireball(this, this.shooter), 6.0F);
/* 23 */         a(this.shooter, movingobjectposition.entity);
/*    */       } 
/*    */       
/* 26 */       this.world.getGameRules().getBoolean("mobGriefing");
/*    */ 
/*    */       
/* 29 */       ExplosionPrimeEvent event = new ExplosionPrimeEvent((Explosive)CraftEntity.getEntity(this.world.getServer(), this));
/* 30 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*    */       
/* 32 */       if (!event.isCancelled())
/*    */       {
/* 34 */         this.world.createExplosion(this, this.locX, this.locY, this.locZ, event.getRadius(), event.getFire(), this.isIncendiary);
/*    */       }
/*    */       
/* 37 */       die();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void a(DataConverterManager dataconvertermanager) {
/* 43 */     EntityFireball.a(dataconvertermanager, "Fireball");
/*    */   }
/*    */   
/*    */   public void b(NBTTagCompound nbttagcompound) {
/* 47 */     super.b(nbttagcompound);
/* 48 */     nbttagcompound.setInt("ExplosionPower", this.yield);
/*    */   }
/*    */   
/*    */   public void a(NBTTagCompound nbttagcompound) {
/* 52 */     super.a(nbttagcompound);
/* 53 */     if (nbttagcompound.hasKeyOfType("ExplosionPower", 99))
/*    */     {
/* 55 */       this.bukkitYield = (this.yield = nbttagcompound.getInt("ExplosionPower"));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityLargeFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */