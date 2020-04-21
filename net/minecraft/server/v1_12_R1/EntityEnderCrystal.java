/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*     */ 
/*     */ public class EntityEnderCrystal
/*     */   extends Entity
/*     */ {
/*  13 */   private static final DataWatcherObject<Optional<BlockPosition>> b = DataWatcher.a((Class)EntityEnderCrystal.class, DataWatcherRegistry.k);
/*  14 */   private static final DataWatcherObject<Boolean> c = DataWatcher.a((Class)EntityEnderCrystal.class, DataWatcherRegistry.h);
/*     */   public int a;
/*     */   
/*     */   public EntityEnderCrystal(World world) {
/*  18 */     super(world);
/*  19 */     this.i = true;
/*  20 */     setSize(2.0F, 2.0F);
/*  21 */     this.a = this.random.nextInt(100000);
/*     */   }
/*     */   
/*     */   public EntityEnderCrystal(World world, double d0, double d1, double d2) {
/*  25 */     this(world);
/*  26 */     setPosition(d0, d1, d2);
/*     */   }
/*     */   
/*     */   protected boolean playStepSound() {
/*  30 */     return false;
/*     */   }
/*     */   
/*     */   protected void i() {
/*  34 */     getDataWatcher().register(b, Optional.absent());
/*  35 */     getDataWatcher().register(c, Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   public void B_() {
/*  39 */     this.lastX = this.locX;
/*  40 */     this.lastY = this.locY;
/*  41 */     this.lastZ = this.locZ;
/*  42 */     this.a++;
/*  43 */     if (!this.world.isClientSide) {
/*  44 */       BlockPosition blockposition = new BlockPosition(this);
/*     */       
/*  46 */       if (this.world.worldProvider instanceof WorldProviderTheEnd && this.world.getType(blockposition).getBlock() != Blocks.FIRE)
/*     */       {
/*  48 */         if (!CraftEventFactory.callBlockIgniteEvent(this.world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this).isCancelled()) {
/*  49 */           this.world.setTypeUpdate(blockposition, Blocks.FIRE.getBlockData());
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void b(NBTTagCompound nbttagcompound) {
/*  58 */     if (getBeamTarget() != null) {
/*  59 */       nbttagcompound.set("BeamTarget", GameProfileSerializer.a(getBeamTarget()));
/*     */     }
/*     */     
/*  62 */     nbttagcompound.setBoolean("ShowBottom", isShowingBottom());
/*     */   }
/*     */   
/*     */   protected void a(NBTTagCompound nbttagcompound) {
/*  66 */     if (nbttagcompound.hasKeyOfType("BeamTarget", 10)) {
/*  67 */       setBeamTarget(GameProfileSerializer.c(nbttagcompound.getCompound("BeamTarget")));
/*     */     }
/*     */     
/*  70 */     if (nbttagcompound.hasKeyOfType("ShowBottom", 1)) {
/*  71 */       setShowingBottom(nbttagcompound.getBoolean("ShowBottom"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/*  77 */     return true;
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  81 */     if (isInvulnerable(damagesource))
/*  82 */       return false; 
/*  83 */     if (damagesource.getEntity() instanceof EntityEnderDragon) {
/*  84 */       return false;
/*     */     }
/*  86 */     if (!this.dead && !this.world.isClientSide) {
/*     */       
/*  88 */       if (CraftEventFactory.handleNonLivingEntityDamageEvent(this, damagesource, f)) {
/*  89 */         return false;
/*     */       }
/*     */       
/*  92 */       die();
/*  93 */       if (!this.world.isClientSide) {
/*  94 */         if (!damagesource.isExplosion()) {
/*     */           
/*  96 */           ExplosionPrimeEvent event = new ExplosionPrimeEvent((Entity)getBukkitEntity(), 6.0F, true);
/*  97 */           this.world.getServer().getPluginManager().callEvent((Event)event);
/*  98 */           if (event.isCancelled()) {
/*  99 */             this.dead = false;
/* 100 */             return false;
/*     */           } 
/* 102 */           this.world.explode(this, this.locX, this.locY, this.locZ, event.getRadius(), event.getFire());
/*     */         } 
/*     */ 
/*     */         
/* 106 */         a(damagesource);
/*     */       } 
/*     */     } 
/*     */     
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void killEntity() {
/* 115 */     a(DamageSource.GENERIC);
/* 116 */     super.killEntity();
/*     */   }
/*     */   
/*     */   private void a(DamageSource damagesource) {
/* 120 */     if (this.world.worldProvider instanceof WorldProviderTheEnd) {
/* 121 */       WorldProviderTheEnd worldprovidertheend = (WorldProviderTheEnd)this.world.worldProvider;
/* 122 */       EnderDragonBattle enderdragonbattle = worldprovidertheend.t();
/*     */       
/* 124 */       if (enderdragonbattle != null) {
/* 125 */         enderdragonbattle.a(this, damagesource);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBeamTarget(@Nullable BlockPosition blockposition) {
/* 132 */     getDataWatcher().set(b, Optional.fromNullable(blockposition));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition getBeamTarget() {
/* 137 */     return (BlockPosition)((Optional)getDataWatcher().<Optional>get((DataWatcherObject)b)).orNull();
/*     */   }
/*     */   
/*     */   public void setShowingBottom(boolean flag) {
/* 141 */     getDataWatcher().set(c, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean isShowingBottom() {
/* 145 */     return ((Boolean)getDataWatcher().<Boolean>get(c)).booleanValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityEnderCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */