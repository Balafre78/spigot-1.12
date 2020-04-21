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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Navigation
/*     */   extends NavigationAbstract
/*     */ {
/*     */   private boolean i;
/*     */   
/*     */   public Navigation(EntityInsentient paramEntityInsentient, World paramWorld) {
/*  22 */     super(paramEntityInsentient, paramWorld);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Pathfinder a() {
/*  27 */     this.h = new PathfinderNormal();
/*  28 */     this.h.a(true);
/*  29 */     return new Pathfinder(this.h);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b() {
/*  34 */     return (this.a.onGround || (h() && q()) || this.a.isPassenger());
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3D c() {
/*  39 */     return new Vec3D(this.a.locX, s(), this.a.locZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public PathEntity b(BlockPosition paramBlockPosition) {
/*  44 */     if (this.b.getType(paramBlockPosition).getMaterial() == Material.AIR) {
/*  45 */       BlockPosition blockPosition = paramBlockPosition.down();
/*  46 */       while (blockPosition.getY() > 0 && this.b.getType(blockPosition).getMaterial() == Material.AIR) {
/*  47 */         blockPosition = blockPosition.down();
/*     */       }
/*     */       
/*  50 */       if (blockPosition.getY() > 0) {
/*  51 */         return super.b(blockPosition.up());
/*     */       }
/*     */       
/*  54 */       while (blockPosition.getY() < this.b.getHeight() && this.b.getType(blockPosition).getMaterial() == Material.AIR) {
/*  55 */         blockPosition = blockPosition.up();
/*     */       }
/*  57 */       paramBlockPosition = blockPosition;
/*     */     } 
/*     */     
/*  60 */     if (this.b.getType(paramBlockPosition).getMaterial().isBuildable()) {
/*  61 */       BlockPosition blockPosition = paramBlockPosition.up();
/*  62 */       while (blockPosition.getY() < this.b.getHeight() && this.b.getType(blockPosition).getMaterial().isBuildable()) {
/*  63 */         blockPosition = blockPosition.up();
/*     */       }
/*  65 */       return super.b(blockPosition);
/*     */     } 
/*     */     
/*  68 */     return super.b(paramBlockPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   public PathEntity a(Entity paramEntity) {
/*  73 */     return b(new BlockPosition(paramEntity));
/*     */   }
/*     */   
/*     */   private int s() {
/*  77 */     if (!this.a.isInWater() || !h()) {
/*  78 */       return (int)((this.a.getBoundingBox()).b + 0.5D);
/*     */     }
/*     */     
/*  81 */     int i = (int)(this.a.getBoundingBox()).b;
/*  82 */     Block block = this.b.getType(new BlockPosition(MathHelper.floor(this.a.locX), i, MathHelper.floor(this.a.locZ))).getBlock();
/*  83 */     byte b = 0;
/*  84 */     while (block == Blocks.FLOWING_WATER || block == Blocks.WATER) {
/*  85 */       i++;
/*  86 */       block = this.b.getType(new BlockPosition(MathHelper.floor(this.a.locX), i, MathHelper.floor(this.a.locZ))).getBlock();
/*  87 */       if (++b > 16) {
/*  88 */         return (int)(this.a.getBoundingBox()).b;
/*     */       }
/*     */     } 
/*  91 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void q_() {
/*  96 */     super.q_();
/*     */     
/*  98 */     if (this.i) {
/*  99 */       if (this.b.h(new BlockPosition(MathHelper.floor(this.a.locX), (int)((this.a.getBoundingBox()).b + 0.5D), MathHelper.floor(this.a.locZ)))) {
/*     */         return;
/*     */       }
/*     */       
/* 103 */       for (byte b = 0; b < this.c.d(); b++) {
/* 104 */         PathPoint pathPoint = this.c.a(b);
/* 105 */         if (this.b.h(new BlockPosition(pathPoint.a, pathPoint.b, pathPoint.c))) {
/* 106 */           this.c.b(b - 1);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(Vec3D paramVec3D1, Vec3D paramVec3D2, int paramInt1, int paramInt2, int paramInt3) {
/* 115 */     int i = MathHelper.floor(paramVec3D1.x);
/* 116 */     int j = MathHelper.floor(paramVec3D1.z);
/*     */     
/* 118 */     double d1 = paramVec3D2.x - paramVec3D1.x;
/* 119 */     double d2 = paramVec3D2.z - paramVec3D1.z;
/* 120 */     double d3 = d1 * d1 + d2 * d2;
/* 121 */     if (d3 < 1.0E-8D) {
/* 122 */       return false;
/*     */     }
/*     */     
/* 125 */     double d4 = 1.0D / Math.sqrt(d3);
/* 126 */     d1 *= d4;
/* 127 */     d2 *= d4;
/*     */     
/* 129 */     paramInt1 += 2;
/* 130 */     paramInt3 += 2;
/* 131 */     if (!a(i, (int)paramVec3D1.y, j, paramInt1, paramInt2, paramInt3, paramVec3D1, d1, d2)) {
/* 132 */       return false;
/*     */     }
/* 134 */     paramInt1 -= 2;
/* 135 */     paramInt3 -= 2;
/*     */     
/* 137 */     double d5 = 1.0D / Math.abs(d1);
/* 138 */     double d6 = 1.0D / Math.abs(d2);
/*     */     
/* 140 */     double d7 = i - paramVec3D1.x;
/* 141 */     double d8 = j - paramVec3D1.z;
/* 142 */     if (d1 >= 0.0D) {
/* 143 */       d7++;
/*     */     }
/* 145 */     if (d2 >= 0.0D) {
/* 146 */       d8++;
/*     */     }
/* 148 */     d7 /= d1;
/* 149 */     d8 /= d2;
/*     */     
/* 151 */     byte b1 = (d1 < 0.0D) ? -1 : 1;
/* 152 */     byte b2 = (d2 < 0.0D) ? -1 : 1;
/* 153 */     int k = MathHelper.floor(paramVec3D2.x);
/* 154 */     int m = MathHelper.floor(paramVec3D2.z);
/* 155 */     int n = k - i;
/* 156 */     int i1 = m - j;
/* 157 */     while (n * b1 > 0 || i1 * b2 > 0) {
/* 158 */       if (d7 < d8) {
/* 159 */         d7 += d5;
/* 160 */         i += b1;
/* 161 */         n = k - i;
/*     */       } else {
/* 163 */         d8 += d6;
/* 164 */         j += b2;
/* 165 */         i1 = m - j;
/*     */       } 
/*     */       
/* 168 */       if (!a(i, (int)paramVec3D1.y, j, paramInt1, paramInt2, paramInt3, paramVec3D1, d1, d2)) {
/* 169 */         return false;
/*     */       }
/*     */     } 
/* 172 */     return true;
/*     */   }
/*     */   
/*     */   private boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Vec3D paramVec3D, double paramDouble1, double paramDouble2) {
/* 176 */     int i = paramInt1 - paramInt4 / 2;
/* 177 */     int j = paramInt3 - paramInt6 / 2;
/*     */     
/* 179 */     if (!b(i, paramInt2, j, paramInt4, paramInt5, paramInt6, paramVec3D, paramDouble1, paramDouble2)) {
/* 180 */       return false;
/*     */     }
/*     */     
/* 183 */     for (int k = i; k < i + paramInt4; k++) {
/* 184 */       for (int m = j; m < j + paramInt6; m++) {
/* 185 */         double d1 = k + 0.5D - paramVec3D.x;
/* 186 */         double d2 = m + 0.5D - paramVec3D.z;
/* 187 */         if (d1 * paramDouble1 + d2 * paramDouble2 >= 0.0D) {
/*     */ 
/*     */ 
/*     */           
/* 191 */           PathType pathType = this.h.a(this.b, k, paramInt2 - 1, m, this.a, paramInt4, paramInt5, paramInt6, true, true);
/*     */           
/* 193 */           if (pathType == PathType.WATER) {
/* 194 */             return false;
/*     */           }
/*     */           
/* 197 */           if (pathType == PathType.LAVA) {
/* 198 */             return false;
/*     */           }
/*     */           
/* 201 */           if (pathType == PathType.OPEN) {
/* 202 */             return false;
/*     */           }
/*     */           
/* 205 */           pathType = this.h.a(this.b, k, paramInt2, m, this.a, paramInt4, paramInt5, paramInt6, true, true);
/* 206 */           float f = this.a.a(pathType);
/*     */           
/* 208 */           if (f < 0.0F || f >= 8.0F) {
/* 209 */             return false;
/*     */           }
/*     */           
/* 212 */           if (pathType == PathType.DAMAGE_FIRE || pathType == PathType.DANGER_FIRE || pathType == PathType.DAMAGE_OTHER) {
/* 213 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 218 */     return true;
/*     */   }
/*     */   
/*     */   private boolean b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Vec3D paramVec3D, double paramDouble1, double paramDouble2) {
/* 222 */     for (BlockPosition blockPosition : BlockPosition.a(new BlockPosition(paramInt1, paramInt2, paramInt3), new BlockPosition(paramInt1 + paramInt4 - 1, paramInt2 + paramInt5 - 1, paramInt3 + paramInt6 - 1))) {
/* 223 */       double d1 = blockPosition.getX() + 0.5D - paramVec3D.x;
/* 224 */       double d2 = blockPosition.getZ() + 0.5D - paramVec3D.z;
/* 225 */       if (d1 * paramDouble1 + d2 * paramDouble2 < 0.0D) {
/*     */         continue;
/*     */       }
/* 228 */       Block block = this.b.getType(blockPosition).getBlock();
/* 229 */       if (!block.b(this.b, blockPosition)) {
/* 230 */         return false;
/*     */       }
/*     */     } 
/* 233 */     return true;
/*     */   }
/*     */   
/*     */   public void a(boolean paramBoolean) {
/* 237 */     this.h.b(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(boolean paramBoolean) {
/* 245 */     this.h.a(paramBoolean);
/*     */   }
/*     */   
/*     */   public boolean g() {
/* 249 */     return this.h.c();
/*     */   }
/*     */   
/*     */   public void c(boolean paramBoolean) {
/* 253 */     this.h.c(paramBoolean);
/*     */   }
/*     */   
/*     */   public boolean h() {
/* 257 */     return this.h.e();
/*     */   }
/*     */   
/*     */   public void d(boolean paramBoolean) {
/* 261 */     this.i = paramBoolean;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Navigation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */