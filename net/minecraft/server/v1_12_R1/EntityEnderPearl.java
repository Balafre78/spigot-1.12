/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.player.PlayerTeleportEvent;
/*     */ 
/*     */ public class EntityEnderPearl
/*     */   extends EntityProjectile {
/*     */   public EntityEnderPearl(World world) {
/*  15 */     super(world);
/*     */   }
/*     */   private EntityLiving d;
/*     */   public EntityEnderPearl(World world, EntityLiving entityliving) {
/*  19 */     super(world, entityliving);
/*  20 */     this.d = entityliving;
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*  24 */     EntityProjectile.a(dataconvertermanager, "ThrownEnderpearl");
/*     */   }
/*     */   
/*     */   protected void a(MovingObjectPosition movingobjectposition) {
/*  28 */     EntityLiving entityliving = getShooter();
/*     */     
/*  30 */     if (movingobjectposition.entity != null) {
/*  31 */       if (movingobjectposition.entity == this.d) {
/*     */         return;
/*     */       }
/*     */       
/*  35 */       movingobjectposition.entity.damageEntity(DamageSource.projectile(this, entityliving), 0.0F);
/*     */     } 
/*     */     
/*  38 */     if (movingobjectposition.type == MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/*  39 */       BlockPosition blockposition = movingobjectposition.a();
/*  40 */       TileEntity tileentity = this.world.getTileEntity(blockposition);
/*     */       
/*  42 */       if (tileentity instanceof TileEntityEndGateway) {
/*  43 */         TileEntityEndGateway tileentityendgateway = (TileEntityEndGateway)tileentity;
/*     */         
/*  45 */         if (entityliving != null) {
/*  46 */           if (entityliving instanceof EntityPlayer) {
/*  47 */             CriterionTriggers.d.a((EntityPlayer)entityliving, this.world.getType(blockposition));
/*     */           }
/*     */           
/*  50 */           tileentityendgateway.a(entityliving);
/*  51 */           die();
/*     */           
/*     */           return;
/*     */         } 
/*  55 */         tileentityendgateway.a(this);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  60 */     for (int i = 0; i < 32; i++) {
/*  61 */       this.world.addParticle(EnumParticle.PORTAL, this.locX, this.locY + this.random.nextDouble() * 2.0D, this.locZ, this.random.nextGaussian(), 0.0D, this.random.nextGaussian(), new int[0]);
/*     */     }
/*     */     
/*  64 */     if (!this.world.isClientSide) {
/*  65 */       if (entityliving instanceof EntityPlayer) {
/*  66 */         EntityPlayer entityplayer = (EntityPlayer)entityliving;
/*     */         
/*  68 */         if (entityplayer.playerConnection.a().isConnected() && entityplayer.world == this.world && !entityplayer.isSleeping()) {
/*     */           
/*  70 */           CraftPlayer player = entityplayer.getBukkitEntity();
/*  71 */           Location location = getBukkitEntity().getLocation();
/*  72 */           location.setPitch(player.getLocation().getPitch());
/*  73 */           location.setYaw(player.getLocation().getYaw());
/*     */           
/*  75 */           PlayerTeleportEvent teleEvent = new PlayerTeleportEvent((Player)player, player.getLocation(), location, PlayerTeleportEvent.TeleportCause.ENDER_PEARL);
/*  76 */           Bukkit.getPluginManager().callEvent((Event)teleEvent);
/*     */           
/*  78 */           if (!teleEvent.isCancelled() && !entityplayer.playerConnection.isDisconnected()) {
/*  79 */             if (this.random.nextFloat() < 0.05F && this.world.getGameRules().getBoolean("doMobSpawning")) {
/*  80 */               EntityEndermite entityendermite = new EntityEndermite(this.world);
/*     */               
/*  82 */               entityendermite.a(true);
/*  83 */               entityendermite.setPositionRotation(entityliving.locX, entityliving.locY, entityliving.locZ, entityliving.yaw, entityliving.pitch);
/*  84 */               this.world.addEntity(entityendermite, CreatureSpawnEvent.SpawnReason.ENDER_PEARL);
/*     */             } 
/*     */             
/*  87 */             if (entityliving.isPassenger()) {
/*  88 */               entityliving.stopRiding();
/*     */             }
/*     */             
/*  91 */             entityplayer.playerConnection.teleport(teleEvent.getTo());
/*  92 */             entityliving.fallDistance = 0.0F;
/*  93 */             CraftEventFactory.entityDamage = this;
/*  94 */             entityliving.damageEntity(DamageSource.FALL, 5.0F);
/*  95 */             CraftEventFactory.entityDamage = null;
/*     */           }
/*     */         
/*     */         } 
/*  99 */       } else if (entityliving != null) {
/* 100 */         entityliving.enderTeleportTo(this.locX, this.locY, this.locZ);
/* 101 */         entityliving.fallDistance = 0.0F;
/*     */       } 
/*     */       
/* 104 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/* 110 */     EntityLiving entityliving = getShooter();
/*     */     
/* 112 */     if (entityliving != null && entityliving instanceof EntityHuman && !entityliving.isAlive()) {
/* 113 */       die();
/*     */     } else {
/* 115 */       super.B_();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityEnderPearl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */