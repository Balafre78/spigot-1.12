/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import org.bukkit.event.entity.ExpBottleEvent;
/*    */ 
/*    */ public class EntityThrownExpBottle extends EntityProjectile {
/*    */   public EntityThrownExpBottle(World world) {
/*  6 */     super(world);
/*    */   }
/*    */   
/*    */   public EntityThrownExpBottle(World world, EntityLiving entityliving) {
/* 10 */     super(world, entityliving);
/*    */   }
/*    */   
/*    */   public EntityThrownExpBottle(World world, double d0, double d1, double d2) {
/* 14 */     super(world, d0, d1, d2);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager dataconvertermanager) {
/* 18 */     EntityProjectile.a(dataconvertermanager, "ThrowableExpBottle");
/*    */   }
/*    */   
/*    */   protected float j() {
/* 22 */     return 0.07F;
/*    */   }
/*    */   
/*    */   protected void a(MovingObjectPosition movingobjectposition) {
/* 26 */     if (!this.world.isClientSide) {
/*    */ 
/*    */       
/* 29 */       int i = 3 + this.world.random.nextInt(5) + this.world.random.nextInt(5);
/*    */ 
/*    */       
/* 32 */       ExpBottleEvent event = CraftEventFactory.callExpBottleEvent(this, i);
/* 33 */       i = event.getExperience();
/* 34 */       if (event.getShowEffect()) {
/* 35 */         this.world.triggerEffect(2002, new BlockPosition(this), PotionUtil.a(Potions.b));
/*    */       }
/*    */ 
/*    */       
/* 39 */       while (i > 0) {
/* 40 */         int j = EntityExperienceOrb.getOrbValue(i);
/*    */         
/* 42 */         i -= j;
/* 43 */         this.world.addEntity(new EntityExperienceOrb(this.world, this.locX, this.locY, this.locZ, j));
/*    */       } 
/*    */       
/* 46 */       die();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityThrownExpBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */