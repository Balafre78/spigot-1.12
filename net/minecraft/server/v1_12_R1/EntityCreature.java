/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityUnleashEvent;
/*     */ 
/*     */ public abstract class EntityCreature
/*     */   extends EntityInsentient
/*     */ {
/*  11 */   public static final UUID bv = UUID.fromString("E199AD21-BA8A-4C53-8D13-6182D5C69D3A");
/*  12 */   public static final AttributeModifier bw = (new AttributeModifier(bv, "Fleeing speed bonus", 2.0D, 2)).a(false);
/*     */   private BlockPosition a;
/*     */   private float b;
/*     */   private final float c;
/*     */   
/*     */   public EntityCreature(World world) {
/*  18 */     super(world);
/*  19 */     this.a = BlockPosition.ZERO;
/*  20 */     this.b = -1.0F;
/*  21 */     this.c = PathType.WATER.a();
/*     */   }
/*     */   
/*     */   public float a(BlockPosition blockposition) {
/*  25 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public boolean P() {
/*  29 */     return (super.P() && a(new BlockPosition(this.locX, (getBoundingBox()).b, this.locZ)) >= 0.0F);
/*     */   }
/*     */   
/*     */   public boolean de() {
/*  33 */     return !this.navigation.o();
/*     */   }
/*     */   
/*     */   public boolean df() {
/*  37 */     return f(new BlockPosition(this));
/*     */   }
/*     */   
/*     */   public boolean f(BlockPosition blockposition) {
/*  41 */     return (this.b == -1.0F) ? true : ((this.a.n(blockposition) < (this.b * this.b)));
/*     */   }
/*     */   
/*     */   public void a(BlockPosition blockposition, int i) {
/*  45 */     this.a = blockposition;
/*  46 */     this.b = i;
/*     */   }
/*     */   
/*     */   public BlockPosition dg() {
/*  50 */     return this.a;
/*     */   }
/*     */   
/*     */   public float dh() {
/*  54 */     return this.b;
/*     */   }
/*     */   
/*     */   public void di() {
/*  58 */     this.b = -1.0F;
/*     */   }
/*     */   
/*     */   public boolean dj() {
/*  62 */     return (this.b != -1.0F);
/*     */   }
/*     */   
/*     */   protected void cZ() {
/*  66 */     super.cZ();
/*  67 */     if (isLeashed() && getLeashHolder() != null && (getLeashHolder()).world == this.world) {
/*  68 */       Entity entity = getLeashHolder();
/*     */       
/*  70 */       a(new BlockPosition((int)entity.locX, (int)entity.locY, (int)entity.locZ), 5);
/*  71 */       float f = g(entity);
/*     */       
/*  73 */       if (this instanceof EntityTameableAnimal && ((EntityTameableAnimal)this).isSitting()) {
/*  74 */         if (f > 10.0F) {
/*  75 */           this.world.getServer().getPluginManager().callEvent((Event)new EntityUnleashEvent((Entity)getBukkitEntity(), EntityUnleashEvent.UnleashReason.DISTANCE));
/*  76 */           unleash(true, true);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  82 */       q(f);
/*  83 */       if (f > 10.0F) {
/*  84 */         this.world.getServer().getPluginManager().callEvent((Event)new EntityUnleashEvent((Entity)getBukkitEntity(), EntityUnleashEvent.UnleashReason.DISTANCE));
/*  85 */         unleash(true, true);
/*  86 */         this.goalSelector.c(1);
/*  87 */       } else if (f > 6.0F) {
/*  88 */         double d0 = (entity.locX - this.locX) / f;
/*  89 */         double d1 = (entity.locY - this.locY) / f;
/*  90 */         double d2 = (entity.locZ - this.locZ) / f;
/*     */         
/*  92 */         this.motX += d0 * Math.abs(d0) * 0.4D;
/*  93 */         this.motY += d1 * Math.abs(d1) * 0.4D;
/*  94 */         this.motZ += d2 * Math.abs(d2) * 0.4D;
/*     */       } else {
/*  96 */         this.goalSelector.d(1);
/*     */         
/*  98 */         Vec3D vec3d = (new Vec3D(entity.locX - this.locX, entity.locY - this.locY, entity.locZ - this.locZ)).a().a(Math.max(f - 2.0F, 0.0F));
/*     */         
/* 100 */         getNavigation().a(this.locX + vec3d.x, this.locY + vec3d.y, this.locZ + vec3d.z, dk());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected double dk() {
/* 107 */     return 1.0D;
/*     */   }
/*     */   
/*     */   protected void q(float f) {}
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityCreature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */