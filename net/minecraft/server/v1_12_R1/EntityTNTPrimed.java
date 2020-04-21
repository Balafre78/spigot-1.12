/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
/*     */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*     */ 
/*     */ public class EntityTNTPrimed extends Entity {
/*   8 */   private static final DataWatcherObject<Integer> FUSE_TICKS = DataWatcher.a((Class)EntityTNTPrimed.class, DataWatcherRegistry.b);
/*     */   @Nullable
/*     */   private EntityLiving source;
/*     */   private int c;
/*  12 */   public float yield = 4.0F;
/*     */   public boolean isIncendiary = false;
/*     */   
/*     */   public EntityTNTPrimed(World world) {
/*  16 */     super(world);
/*  17 */     this.c = 80;
/*  18 */     this.i = true;
/*  19 */     this.fireProof = true;
/*  20 */     setSize(0.98F, 0.98F);
/*     */   }
/*     */   
/*     */   public EntityTNTPrimed(World world, double d0, double d1, double d2, EntityLiving entityliving) {
/*  24 */     this(world);
/*  25 */     setPosition(d0, d1, d2);
/*  26 */     float f = (float)(Math.random() * 6.2831854820251465D);
/*     */     
/*  28 */     this.motX = (-((float)Math.sin(f)) * 0.02F);
/*  29 */     this.motY = 0.20000000298023224D;
/*  30 */     this.motZ = (-((float)Math.cos(f)) * 0.02F);
/*  31 */     setFuseTicks(80);
/*  32 */     this.lastX = d0;
/*  33 */     this.lastY = d1;
/*  34 */     this.lastZ = d2;
/*  35 */     this.source = entityliving;
/*     */   }
/*     */   
/*     */   protected void i() {
/*  39 */     this.datawatcher.register(FUSE_TICKS, Integer.valueOf(80));
/*     */   }
/*     */   
/*     */   protected boolean playStepSound() {
/*  43 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isInteractable() {
/*  47 */     return !this.dead;
/*     */   }
/*     */   
/*     */   public void B_() {
/*  51 */     if (this.world.spigotConfig.currentPrimedTnt++ > this.world.spigotConfig.maxTntTicksPerTick)
/*  52 */       return;  this.lastX = this.locX;
/*  53 */     this.lastY = this.locY;
/*  54 */     this.lastZ = this.locZ;
/*  55 */     if (!isNoGravity()) {
/*  56 */       this.motY -= 0.03999999910593033D;
/*     */     }
/*     */     
/*  59 */     move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/*  60 */     this.motX *= 0.9800000190734863D;
/*  61 */     this.motY *= 0.9800000190734863D;
/*  62 */     this.motZ *= 0.9800000190734863D;
/*  63 */     if (this.onGround) {
/*  64 */       this.motX *= 0.699999988079071D;
/*  65 */       this.motZ *= 0.699999988079071D;
/*  66 */       this.motY *= -0.5D;
/*     */     } 
/*     */     
/*  69 */     this.c--;
/*  70 */     if (this.c <= 0) {
/*     */ 
/*     */       
/*  73 */       if (!this.world.isClientSide) {
/*  74 */         explode();
/*     */       }
/*  76 */       die();
/*     */     } else {
/*     */       
/*  79 */       aq();
/*  80 */       this.world.addParticle(EnumParticle.SMOKE_NORMAL, this.locX, this.locY + 0.5D, this.locZ, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void explode() {
/*  89 */     CraftServer server = this.world.getServer();
/*  90 */     ExplosionPrimeEvent event = new ExplosionPrimeEvent((Explosive)CraftEntity.getEntity(server, this));
/*  91 */     server.getPluginManager().callEvent((Event)event);
/*     */     
/*  93 */     if (!event.isCancelled()) {
/*  94 */       this.world.createExplosion(this, this.locX, this.locY + (this.length / 16.0F), this.locZ, event.getRadius(), event.getFire(), true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(NBTTagCompound nbttagcompound) {
/* 100 */     nbttagcompound.setShort("Fuse", (short)getFuseTicks());
/*     */   }
/*     */   
/*     */   protected void a(NBTTagCompound nbttagcompound) {
/* 104 */     setFuseTicks(nbttagcompound.getShort("Fuse"));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving getSource() {
/* 109 */     return this.source;
/*     */   }
/*     */   
/*     */   public float getHeadHeight() {
/* 113 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void setFuseTicks(int i) {
/* 117 */     this.datawatcher.set(FUSE_TICKS, Integer.valueOf(i));
/* 118 */     this.c = i;
/*     */   }
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 122 */     if (FUSE_TICKS.equals(datawatcherobject)) {
/* 123 */       this.c = k();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int k() {
/* 129 */     return ((Integer)this.datawatcher.<Integer>get(FUSE_TICKS)).intValue();
/*     */   }
/*     */   
/*     */   public int getFuseTicks() {
/* 133 */     return this.c;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityTNTPrimed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */