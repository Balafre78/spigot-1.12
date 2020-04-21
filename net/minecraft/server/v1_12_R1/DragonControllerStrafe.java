/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DragonControllerStrafe
/*     */   extends AbstractDragonController
/*     */ {
/*  18 */   private static final Logger b = LogManager.getLogger();
/*     */   
/*     */   private int c;
/*     */   
/*     */   private PathEntity d;
/*     */   private Vec3D e;
/*     */   private EntityLiving f;
/*     */   private boolean g;
/*     */   
/*     */   public DragonControllerStrafe(EntityEnderDragon paramEntityEnderDragon) {
/*  28 */     super(paramEntityEnderDragon);
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  33 */     if (this.f == null) {
/*  34 */       b.warn("Skipping player strafe phase because no player was found");
/*  35 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.a);
/*     */       
/*     */       return;
/*     */     } 
/*  39 */     if (this.d != null && this.d.b()) {
/*  40 */       double d3 = this.f.locX;
/*  41 */       double d4 = this.f.locZ;
/*     */       
/*  43 */       double d5 = d3 - this.a.locX;
/*  44 */       double d6 = d4 - this.a.locZ;
/*  45 */       double d7 = MathHelper.sqrt(d5 * d5 + d6 * d6);
/*  46 */       double d8 = Math.min(0.4000000059604645D + d7 / 80.0D - 1.0D, 10.0D);
/*     */       
/*  48 */       this.e = new Vec3D(d3, this.f.locY + d8, d4);
/*     */     } 
/*     */     
/*  51 */     double d1 = (this.e == null) ? 0.0D : this.e.c(this.a.locX, this.a.locY, this.a.locZ);
/*  52 */     if (d1 < 100.0D || d1 > 22500.0D) {
/*  53 */       j();
/*     */     }
/*     */     
/*  56 */     double d2 = 64.0D;
/*  57 */     if (this.f.h(this.a) < 4096.0D) {
/*  58 */       if (this.a.hasLineOfSight(this.f)) {
/*  59 */         this.c++;
/*  60 */         Vec3D vec3D1 = (new Vec3D(this.f.locX - this.a.locX, 0.0D, this.f.locZ - this.a.locZ)).a();
/*  61 */         Vec3D vec3D2 = (new Vec3D(MathHelper.sin(this.a.yaw * 0.017453292F), 0.0D, -MathHelper.cos(this.a.yaw * 0.017453292F))).a();
/*  62 */         float f1 = (float)vec3D2.b(vec3D1);
/*  63 */         float f2 = (float)(Math.acos(f1) * 57.2957763671875D);
/*  64 */         f2 += 0.5F;
/*     */         
/*  66 */         if (this.c >= 5 && f2 >= 0.0F && f2 < 10.0F) {
/*  67 */           double d3 = 1.0D;
/*  68 */           Vec3D vec3D = this.a.e(1.0F);
/*  69 */           double d4 = this.a.bw.locX - vec3D.x * 1.0D;
/*  70 */           double d5 = this.a.bw.locY + (this.a.bw.length / 2.0F) + 0.5D;
/*  71 */           double d6 = this.a.bw.locZ - vec3D.z * 1.0D;
/*     */           
/*  73 */           double d7 = this.f.locX - d4;
/*  74 */           double d8 = this.f.locY + (this.f.length / 2.0F) - d5 + (this.a.bw.length / 2.0F);
/*  75 */           double d9 = this.f.locZ - d6;
/*     */           
/*  77 */           this.a.world.a((EntityHuman)null, 1017, new BlockPosition(this.a), 0);
/*  78 */           EntityDragonFireball entityDragonFireball = new EntityDragonFireball(this.a.world, this.a, d7, d8, d9);
/*  79 */           entityDragonFireball.setPositionRotation(d4, d5, d6, 0.0F, 0.0F);
/*  80 */           this.a.world.addEntity(entityDragonFireball);
/*  81 */           this.c = 0;
/*     */           
/*  83 */           if (this.d != null) {
/*  84 */             while (!this.d.b()) {
/*  85 */               this.d.a();
/*     */             }
/*     */           }
/*     */           
/*  89 */           this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.a);
/*     */         }
/*     */       
/*  92 */       } else if (this.c > 0) {
/*  93 */         this.c--;
/*     */       }
/*     */     
/*     */     }
/*  97 */     else if (this.c > 0) {
/*  98 */       this.c--;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void j() {
/* 104 */     if (this.d == null || this.d.b()) {
/* 105 */       int i = this.a.p();
/* 106 */       int j = i;
/*     */       
/* 108 */       if (this.a.getRandom().nextInt(8) == 0) {
/* 109 */         this.g = !this.g;
/* 110 */         j += 6;
/*     */       } 
/*     */       
/* 113 */       if (this.g) {
/* 114 */         j++;
/*     */       } else {
/* 116 */         j--;
/*     */       } 
/*     */       
/* 119 */       if (this.a.df() == null || this.a.df().c() <= 0) {
/*     */         
/* 121 */         j -= 12;
/* 122 */         j &= 0x7;
/* 123 */         j += 12;
/*     */       } else {
/*     */         
/* 126 */         j %= 12;
/* 127 */         if (j < 0) {
/* 128 */           j += 12;
/*     */         }
/*     */       } 
/*     */       
/* 132 */       this.d = this.a.a(i, j, (PathPoint)null);
/*     */       
/* 134 */       if (this.d != null) {
/* 135 */         this.d.a();
/*     */       }
/*     */     } 
/*     */     
/* 139 */     k();
/*     */   }
/*     */   
/*     */   private void k() {
/* 143 */     if (this.d != null && !this.d.b()) {
/* 144 */       double d3; Vec3D vec3D = this.d.f();
/*     */       
/* 146 */       this.d.a();
/* 147 */       double d1 = vec3D.x;
/*     */       
/* 149 */       double d2 = vec3D.z;
/*     */       
/*     */       do {
/* 152 */         d3 = vec3D.y + (this.a.getRandom().nextFloat() * 20.0F);
/* 153 */       } while (d3 < vec3D.y);
/*     */       
/* 155 */       this.e = new Vec3D(d1, d3, d2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/* 161 */     this.c = 0;
/* 162 */     this.e = null;
/* 163 */     this.d = null;
/* 164 */     this.f = null;
/*     */   }
/*     */   
/*     */   public void a(EntityLiving paramEntityLiving) {
/* 168 */     this.f = paramEntityLiving;
/*     */     
/* 170 */     int i = this.a.p();
/* 171 */     int j = this.a.k(this.f.locX, this.f.locY, this.f.locZ);
/*     */     
/* 173 */     int k = MathHelper.floor(this.f.locX);
/* 174 */     int m = MathHelper.floor(this.f.locZ);
/*     */     
/* 176 */     double d1 = k - this.a.locX;
/* 177 */     double d2 = m - this.a.locZ;
/* 178 */     double d3 = MathHelper.sqrt(d1 * d1 + d2 * d2);
/* 179 */     double d4 = Math.min(0.4000000059604645D + d3 / 80.0D - 1.0D, 10.0D);
/* 180 */     int n = MathHelper.floor(this.f.locY + d4);
/*     */     
/* 182 */     PathPoint pathPoint = new PathPoint(k, n, m);
/*     */     
/* 184 */     this.d = this.a.a(i, j, pathPoint);
/*     */     
/* 186 */     if (this.d != null) {
/* 187 */       this.d.a();
/*     */       
/* 189 */       k();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Vec3D g() {
/* 196 */     return this.e;
/*     */   }
/*     */ 
/*     */   
/*     */   public DragonControllerPhase<DragonControllerStrafe> getControllerPhase() {
/* 201 */     return DragonControllerPhase.b;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DragonControllerStrafe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */