/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityEvokerFangs extends Entity {
/*     */   private int a;
/*     */   private boolean b;
/*     */   private int c;
/*     */   private boolean d;
/*     */   private EntityLiving e;
/*     */   private UUID f;
/*     */   
/*     */   public EntityEvokerFangs(World world) {
/*  18 */     super(world);
/*  19 */     this.c = 22;
/*  20 */     setSize(0.5F, 0.8F);
/*     */   }
/*     */   
/*     */   public EntityEvokerFangs(World world, double d0, double d1, double d2, float f, int i, EntityLiving entityliving) {
/*  24 */     this(world);
/*  25 */     this.a = i;
/*  26 */     a(entityliving);
/*  27 */     this.yaw = f * 57.295776F;
/*  28 */     setPosition(d0, d1, d2);
/*     */   }
/*     */   
/*     */   protected void i() {}
/*     */   
/*     */   public void a(@Nullable EntityLiving entityliving) {
/*  34 */     this.e = entityliving;
/*  35 */     this.f = (entityliving == null) ? null : entityliving.getUniqueID();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving getOwner() {
/*  40 */     if (this.e == null && this.f != null && this.world instanceof WorldServer) {
/*  41 */       Entity entity = ((WorldServer)this.world).getEntity(this.f);
/*     */       
/*  43 */       if (entity instanceof EntityLiving) {
/*  44 */         this.e = (EntityLiving)entity;
/*     */       }
/*     */     } 
/*     */     
/*  48 */     return this.e;
/*     */   }
/*     */   
/*     */   protected void a(NBTTagCompound nbttagcompound) {
/*  52 */     this.a = nbttagcompound.getInt("Warmup");
/*  53 */     this.f = nbttagcompound.a("OwnerUUID");
/*     */   }
/*     */   
/*     */   protected void b(NBTTagCompound nbttagcompound) {
/*  57 */     nbttagcompound.setInt("Warmup", this.a);
/*  58 */     if (this.f != null) {
/*  59 */       nbttagcompound.a("OwnerUUID", this.f);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/*  65 */     super.B_();
/*  66 */     if (this.world.isClientSide) {
/*  67 */       if (this.d) {
/*  68 */         this.c--;
/*  69 */         if (this.c == 14) {
/*  70 */           for (int i = 0; i < 12; i++) {
/*  71 */             double d0 = this.locX + (this.random.nextDouble() * 2.0D - 1.0D) * this.width * 0.5D;
/*  72 */             double d1 = this.locY + 0.05D + this.random.nextDouble() * 1.0D;
/*  73 */             double d2 = this.locZ + (this.random.nextDouble() * 2.0D - 1.0D) * this.width * 0.5D;
/*  74 */             double d3 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.3D;
/*  75 */             double d4 = 0.3D + this.random.nextDouble() * 0.3D;
/*  76 */             double d5 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.3D;
/*     */             
/*  78 */             this.world.addParticle(EnumParticle.CRIT, d0, d1 + 1.0D, d2, d3, d4, d5, new int[0]);
/*     */           } 
/*     */         }
/*     */       } 
/*  82 */     } else if (--this.a < 0) {
/*  83 */       if (this.a == -8) {
/*  84 */         List<EntityLiving> list = this.world.a(EntityLiving.class, getBoundingBox().grow(0.2D, 0.0D, 0.2D));
/*  85 */         Iterator<EntityLiving> iterator = list.iterator();
/*     */         
/*  87 */         while (iterator.hasNext()) {
/*  88 */           EntityLiving entityliving = iterator.next();
/*     */           
/*  90 */           c(entityliving);
/*     */         } 
/*     */       } 
/*     */       
/*  94 */       if (!this.b) {
/*  95 */         this.world.broadcastEntityEffect(this, (byte)4);
/*  96 */         this.b = true;
/*     */       } 
/*     */       
/*  99 */       if (--this.c < 0) {
/* 100 */         die();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void c(EntityLiving entityliving) {
/* 107 */     EntityLiving entityliving1 = getOwner();
/*     */     
/* 109 */     if (entityliving.isAlive() && !entityliving.be() && entityliving != entityliving1)
/* 110 */       if (entityliving1 == null) {
/* 111 */         CraftEventFactory.entityDamage = this;
/* 112 */         entityliving.damageEntity(DamageSource.MAGIC, 6.0F);
/* 113 */         CraftEventFactory.entityDamage = null;
/*     */       } else {
/* 115 */         if (entityliving1.r(entityliving)) {
/*     */           return;
/*     */         }
/*     */         
/* 119 */         entityliving.damageEntity(DamageSource.b(this, entityliving1), 6.0F);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityEvokerFangs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */