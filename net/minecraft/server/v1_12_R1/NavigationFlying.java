/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NavigationFlying
/*     */   extends NavigationAbstract
/*     */ {
/*     */   public NavigationFlying(EntityInsentient paramEntityInsentient, World paramWorld) {
/*  15 */     super(paramEntityInsentient, paramWorld);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Pathfinder a() {
/*  20 */     this.h = new PathfinderFlying();
/*  21 */     this.h.a(true);
/*  22 */     return new Pathfinder(this.h);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b() {
/*  27 */     return ((g() && q()) || !this.a.isPassenger());
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3D c() {
/*  32 */     return new Vec3D(this.a.locX, this.a.locY, this.a.locZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public PathEntity a(Entity paramEntity) {
/*  37 */     return b(new BlockPosition(paramEntity));
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  42 */     this.e++;
/*     */     
/*  44 */     if (this.g) {
/*  45 */       k();
/*     */     }
/*     */     
/*  48 */     if (o()) {
/*     */       return;
/*     */     }
/*     */     
/*  52 */     if (b()) {
/*  53 */       n();
/*  54 */     } else if (this.c != null && this.c.e() < this.c.d()) {
/*  55 */       Vec3D vec3D1 = this.c.a(this.a, this.c.e());
/*  56 */       if (MathHelper.floor(this.a.locX) == MathHelper.floor(vec3D1.x) && MathHelper.floor(this.a.locY) == MathHelper.floor(vec3D1.y) && MathHelper.floor(this.a.locZ) == MathHelper.floor(vec3D1.z)) {
/*  57 */         this.c.c(this.c.e() + 1);
/*     */       }
/*     */     } 
/*     */     
/*  61 */     m();
/*     */     
/*  63 */     if (o()) {
/*     */       return;
/*     */     }
/*  66 */     Vec3D vec3D = this.c.a(this.a);
/*     */     
/*  68 */     this.a.getControllerMove().a(vec3D.x, vec3D.y, vec3D.z, this.d);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(Vec3D paramVec3D1, Vec3D paramVec3D2, int paramInt1, int paramInt2, int paramInt3) {
/*  73 */     int i = MathHelper.floor(paramVec3D1.x);
/*  74 */     int j = MathHelper.floor(paramVec3D1.y);
/*  75 */     int k = MathHelper.floor(paramVec3D1.z);
/*     */     
/*  77 */     double d1 = paramVec3D2.x - paramVec3D1.x;
/*  78 */     double d2 = paramVec3D2.y - paramVec3D1.y;
/*  79 */     double d3 = paramVec3D2.z - paramVec3D1.z;
/*  80 */     double d4 = d1 * d1 + d2 * d2 + d3 * d3;
/*  81 */     if (d4 < 1.0E-8D) {
/*  82 */       return false;
/*     */     }
/*     */     
/*  85 */     double d5 = 1.0D / Math.sqrt(d4);
/*  86 */     d1 *= d5;
/*  87 */     d2 *= d5;
/*  88 */     d3 *= d5;
/*     */     
/*  90 */     double d6 = 1.0D / Math.abs(d1);
/*  91 */     double d7 = 1.0D / Math.abs(d2);
/*  92 */     double d8 = 1.0D / Math.abs(d3);
/*     */     
/*  94 */     double d9 = i - paramVec3D1.x;
/*  95 */     double d10 = j - paramVec3D1.y;
/*  96 */     double d11 = k - paramVec3D1.z;
/*  97 */     if (d1 >= 0.0D) {
/*  98 */       d9++;
/*     */     }
/* 100 */     if (d2 >= 0.0D) {
/* 101 */       d10++;
/*     */     }
/* 103 */     if (d3 >= 0.0D) {
/* 104 */       d11++;
/*     */     }
/* 106 */     d9 /= d1;
/* 107 */     d10 /= d2;
/* 108 */     d11 /= d3;
/*     */     
/* 110 */     byte b1 = (d1 < 0.0D) ? -1 : 1;
/* 111 */     byte b2 = (d2 < 0.0D) ? -1 : 1;
/* 112 */     byte b3 = (d3 < 0.0D) ? -1 : 1;
/* 113 */     int m = MathHelper.floor(paramVec3D2.x);
/* 114 */     int n = MathHelper.floor(paramVec3D2.y);
/* 115 */     int i1 = MathHelper.floor(paramVec3D2.z);
/* 116 */     int i2 = m - i;
/* 117 */     int i3 = n - j;
/* 118 */     int i4 = i1 - k;
/*     */     
/* 120 */     while (i2 * b1 > 0 || i3 * b2 > 0 || i4 * b3 > 0) {
/* 121 */       if (d9 < d11 && d9 <= d10) {
/* 122 */         d9 += d6;
/* 123 */         i += b1;
/* 124 */         i2 = m - i; continue;
/* 125 */       }  if (d10 < d9 && d10 <= d11) {
/* 126 */         d10 += d7;
/* 127 */         j += b2;
/* 128 */         i3 = n - j; continue;
/*     */       } 
/* 130 */       d11 += d8;
/* 131 */       k += b3;
/* 132 */       i4 = i1 - k;
/*     */     } 
/*     */     
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   public void a(boolean paramBoolean) {
/* 139 */     this.h.b(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(boolean paramBoolean) {
/* 147 */     this.h.a(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(boolean paramBoolean) {
/* 155 */     this.h.c(paramBoolean);
/*     */   }
/*     */   
/*     */   public boolean g() {
/* 159 */     return this.h.e();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(BlockPosition paramBlockPosition) {
/* 164 */     return this.b.getType(paramBlockPosition).q();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NavigationFlying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */