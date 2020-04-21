/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DragonControllerHold
/*     */   extends AbstractDragonController
/*     */ {
/*     */   private PathEntity b;
/*     */   private Vec3D c;
/*     */   private boolean d;
/*     */   
/*     */   public DragonControllerHold(EntityEnderDragon paramEntityEnderDragon) {
/*  21 */     super(paramEntityEnderDragon);
/*     */   }
/*     */ 
/*     */   
/*     */   public DragonControllerPhase<DragonControllerHold> getControllerPhase() {
/*  26 */     return DragonControllerPhase.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  31 */     double d = (this.c == null) ? 0.0D : this.c.c(this.a.locX, this.a.locY, this.a.locZ);
/*  32 */     if (d < 100.0D || d > 22500.0D || this.a.positionChanged || this.a.B) {
/*  33 */       j();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  39 */     this.b = null;
/*  40 */     this.c = null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Vec3D g() {
/*  46 */     return this.c;
/*     */   }
/*     */   
/*     */   private void j() {
/*  50 */     if (this.b != null && this.b.b()) {
/*  51 */       BlockPosition blockPosition = this.a.world.q(new BlockPosition(WorldGenEndTrophy.a));
/*     */ 
/*     */ 
/*     */       
/*  55 */       byte b = (this.a.df() == null) ? 0 : this.a.df().c();
/*     */       
/*  57 */       if (this.a.getRandom().nextInt(b + 3) == 0) {
/*  58 */         this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.c);
/*     */         return;
/*     */       } 
/*  61 */       double d = 64.0D;
/*  62 */       EntityHuman entityHuman = this.a.world.a(blockPosition, d, d);
/*  63 */       if (entityHuman != null) {
/*  64 */         d = entityHuman.d(blockPosition) / 512.0D;
/*     */       }
/*  66 */       if (entityHuman != null && (this.a.getRandom().nextInt(MathHelper.a((int)d) + 2) == 0 || this.a.getRandom().nextInt(b + 2) == 0)) {
/*     */         
/*  68 */         a(entityHuman);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/*  74 */     if (this.b == null || this.b.b()) {
/*  75 */       int i = this.a.p();
/*  76 */       int j = i;
/*     */       
/*  78 */       if (this.a.getRandom().nextInt(8) == 0) {
/*  79 */         this.d = !this.d;
/*  80 */         j += 6;
/*     */       } 
/*     */       
/*  83 */       if (this.d) {
/*  84 */         j++;
/*     */       } else {
/*  86 */         j--;
/*     */       } 
/*     */       
/*  89 */       if (this.a.df() == null || this.a.df().c() < 0) {
/*     */         
/*  91 */         j -= 12;
/*  92 */         j &= 0x7;
/*  93 */         j += 12;
/*     */       } else {
/*     */         
/*  96 */         j %= 12;
/*  97 */         if (j < 0) {
/*  98 */           j += 12;
/*     */         }
/*     */       } 
/*     */       
/* 102 */       this.b = this.a.a(i, j, (PathPoint)null);
/* 103 */       if (this.b != null) {
/* 104 */         this.b.a();
/*     */       }
/*     */     } 
/*     */     
/* 108 */     k();
/*     */   }
/*     */   
/*     */   private void a(EntityHuman paramEntityHuman) {
/* 112 */     this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.b);
/* 113 */     ((DragonControllerStrafe)this.a.getDragonControllerManager().<DragonControllerStrafe>b(DragonControllerPhase.b)).a(paramEntityHuman);
/*     */   }
/*     */   
/*     */   private void k() {
/* 117 */     if (this.b != null && !this.b.b()) {
/* 118 */       double d3; Vec3D vec3D = this.b.f();
/*     */       
/* 120 */       this.b.a();
/* 121 */       double d1 = vec3D.x;
/* 122 */       double d2 = vec3D.z;
/*     */ 
/*     */       
/*     */       do {
/* 126 */         d3 = vec3D.y + (this.a.getRandom().nextFloat() * 20.0F);
/* 127 */       } while (d3 < vec3D.y);
/*     */       
/* 129 */       this.c = new Vec3D(d1, d3, d2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityEnderCrystal paramEntityEnderCrystal, BlockPosition paramBlockPosition, DamageSource paramDamageSource, @Nullable EntityHuman paramEntityHuman) {
/* 135 */     if (paramEntityHuman != null && !paramEntityHuman.abilities.isInvulnerable)
/* 136 */       a(paramEntityHuman); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DragonControllerHold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */