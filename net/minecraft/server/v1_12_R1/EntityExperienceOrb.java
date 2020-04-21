/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
/*     */ 
/*     */ public class EntityExperienceOrb
/*     */   extends Entity
/*     */ {
/*     */   public int a;
/*     */   public int b;
/*     */   public int c;
/*  14 */   private int d = 5;
/*     */   public int value;
/*     */   private EntityHuman targetPlayer;
/*     */   private int targetTime;
/*     */   
/*     */   public EntityExperienceOrb(World world, double d0, double d1, double d2, int i) {
/*  20 */     super(world);
/*  21 */     setSize(0.5F, 0.5F);
/*  22 */     setPosition(d0, d1, d2);
/*  23 */     this.yaw = (float)(Math.random() * 360.0D);
/*  24 */     this.motX = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
/*  25 */     this.motY = ((float)(Math.random() * 0.2D) * 2.0F);
/*  26 */     this.motZ = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
/*  27 */     this.value = i;
/*     */   }
/*     */   
/*     */   protected boolean playStepSound() {
/*  31 */     return false;
/*     */   }
/*     */   
/*     */   public EntityExperienceOrb(World world) {
/*  35 */     super(world);
/*  36 */     setSize(0.25F, 0.25F);
/*     */   }
/*     */   
/*     */   protected void i() {}
/*     */   
/*     */   public void B_() {
/*  42 */     super.B_();
/*  43 */     EntityHuman prevTarget = this.targetPlayer;
/*  44 */     if (this.c > 0) {
/*  45 */       this.c--;
/*     */     }
/*     */     
/*  48 */     this.lastX = this.locX;
/*  49 */     this.lastY = this.locY;
/*  50 */     this.lastZ = this.locZ;
/*  51 */     if (!isNoGravity()) {
/*  52 */       this.motY -= 0.029999999329447746D;
/*     */     }
/*     */     
/*  55 */     if (this.world.getType(new BlockPosition(this)).getMaterial() == Material.LAVA) {
/*  56 */       this.motY = 0.20000000298023224D;
/*  57 */       this.motX = ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*  58 */       this.motZ = ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*  59 */       a(SoundEffects.bR, 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
/*     */     } 
/*     */     
/*  62 */     i(this.locX, ((getBoundingBox()).b + (getBoundingBox()).e) / 2.0D, this.locZ);
/*     */ 
/*     */     
/*  65 */     if (this.targetTime < this.a - 20 + getId() % 100) {
/*  66 */       if (this.targetPlayer == null || this.targetPlayer.h(this) > 64.0D) {
/*  67 */         this.targetPlayer = this.world.findNearbyPlayer(this, 8.0D);
/*     */       }
/*     */       
/*  70 */       this.targetTime = this.a;
/*     */     } 
/*     */     
/*  73 */     if (this.targetPlayer != null && this.targetPlayer.isSpectator()) {
/*  74 */       this.targetPlayer = null;
/*     */     }
/*     */     
/*  77 */     if (this.targetPlayer != null) {
/*     */       
/*  79 */       boolean cancelled = false;
/*  80 */       if (this.targetPlayer != prevTarget) {
/*  81 */         EntityTargetLivingEntityEvent event = CraftEventFactory.callEntityTargetLivingEvent(this, this.targetPlayer, EntityTargetEvent.TargetReason.CLOSEST_PLAYER);
/*  82 */         EntityLiving target = (event.getTarget() == null) ? null : ((CraftLivingEntity)event.getTarget()).getHandle();
/*  83 */         this.targetPlayer = (target instanceof EntityHuman) ? (EntityHuman)target : null;
/*  84 */         cancelled = event.isCancelled();
/*     */       } 
/*     */       
/*  87 */       if (!cancelled && this.targetPlayer != null) {
/*  88 */         double d1 = (this.targetPlayer.locX - this.locX) / 8.0D;
/*  89 */         double d2 = (this.targetPlayer.locY + this.targetPlayer.getHeadHeight() / 2.0D - this.locY) / 8.0D;
/*  90 */         double d3 = (this.targetPlayer.locZ - this.locZ) / 8.0D;
/*  91 */         double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
/*  92 */         double d5 = 1.0D - d4;
/*     */         
/*  94 */         if (d5 > 0.0D) {
/*  95 */           d5 *= d5;
/*  96 */           this.motX += d1 / d4 * d5 * 0.1D;
/*  97 */           this.motY += d2 / d4 * d5 * 0.1D;
/*  98 */           this.motZ += d3 / d4 * d5 * 0.1D;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 104 */     move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/* 105 */     float f = 0.98F;
/*     */     
/* 107 */     if (this.onGround) {
/* 108 */       f = (this.world.getType(new BlockPosition(MathHelper.floor(this.locX), MathHelper.floor((getBoundingBox()).b) - 1, MathHelper.floor(this.locZ))).getBlock()).frictionFactor * 0.98F;
/*     */     }
/*     */     
/* 111 */     this.motX *= f;
/* 112 */     this.motY *= 0.9800000190734863D;
/* 113 */     this.motZ *= f;
/* 114 */     if (this.onGround) {
/* 115 */       this.motY *= -0.8999999761581421D;
/*     */     }
/*     */     
/* 118 */     this.a++;
/* 119 */     this.b++;
/* 120 */     if (this.b >= 6000) {
/* 121 */       die();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean aq() {
/* 127 */     return this.world.a(getBoundingBox(), Material.WATER, this);
/*     */   }
/*     */   
/*     */   protected void burn(int i) {
/* 131 */     damageEntity(DamageSource.FIRE, i);
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 135 */     if (isInvulnerable(damagesource)) {
/* 136 */       return false;
/*     */     }
/* 138 */     ax();
/* 139 */     this.d = (int)(this.d - f);
/* 140 */     if (this.d <= 0) {
/* 141 */       die();
/*     */     }
/*     */     
/* 144 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 149 */     nbttagcompound.setShort("Health", (short)this.d);
/* 150 */     nbttagcompound.setShort("Age", (short)this.b);
/* 151 */     nbttagcompound.setShort("Value", (short)this.value);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 155 */     this.d = nbttagcompound.getShort("Health");
/* 156 */     this.b = nbttagcompound.getShort("Age");
/* 157 */     this.value = nbttagcompound.getShort("Value");
/*     */   }
/*     */   
/*     */   public void d(EntityHuman entityhuman) {
/* 161 */     if (!this.world.isClientSide && 
/* 162 */       this.c == 0 && entityhuman.bD == 0) {
/* 163 */       entityhuman.bD = 2;
/* 164 */       entityhuman.receive(this, 1);
/* 165 */       ItemStack itemstack = EnchantmentManager.b(Enchantments.C, entityhuman);
/*     */       
/* 167 */       if (!itemstack.isEmpty() && itemstack.h()) {
/* 168 */         int i = Math.min(d(this.value), itemstack.i());
/*     */         
/* 170 */         this.value -= c(i);
/* 171 */         itemstack.setData(itemstack.i() - i);
/*     */       } 
/*     */       
/* 174 */       if (this.value > 0) {
/* 175 */         entityhuman.giveExp(CraftEventFactory.callPlayerExpChangeEvent(entityhuman, this.value).getAmount());
/*     */       }
/*     */       
/* 178 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int c(int i) {
/* 185 */     return i / 2;
/*     */   }
/*     */   
/*     */   private int d(int i) {
/* 189 */     return i * 2;
/*     */   }
/*     */   
/*     */   public int j() {
/* 193 */     return this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getOrbValue(int i) {
/* 198 */     if (i > 162670129) return i - 100000; 
/* 199 */     if (i > 81335063) return 81335063; 
/* 200 */     if (i > 40667527) return 40667527; 
/* 201 */     if (i > 20333759) return 20333759; 
/* 202 */     if (i > 10166857) return 10166857; 
/* 203 */     if (i > 5083423) return 5083423; 
/* 204 */     if (i > 2541701) return 2541701; 
/* 205 */     if (i > 1270849) return 1270849; 
/* 206 */     if (i > 635413) return 635413; 
/* 207 */     if (i > 317701) return 317701; 
/* 208 */     if (i > 158849) return 158849; 
/* 209 */     if (i > 79423) return 79423; 
/* 210 */     if (i > 39709) return 39709; 
/* 211 */     if (i > 19853) return 19853; 
/* 212 */     if (i > 9923) return 9923; 
/* 213 */     if (i > 4957) return 4957;
/*     */     
/* 215 */     return (i >= 2477) ? 2477 : ((i >= 1237) ? 1237 : ((i >= 617) ? 617 : ((i >= 307) ? 307 : ((i >= 149) ? 149 : ((i >= 73) ? 73 : ((i >= 37) ? 37 : ((i >= 17) ? 17 : ((i >= 7) ? 7 : ((i >= 3) ? 3 : 1)))))))));
/*     */   }
/*     */   
/*     */   public boolean bd() {
/* 219 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityExperienceOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */