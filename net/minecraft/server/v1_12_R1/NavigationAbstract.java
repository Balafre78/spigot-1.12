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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class NavigationAbstract
/*     */ {
/*     */   protected EntityInsentient a;
/*     */   protected World b;
/*     */   @Nullable
/*     */   protected PathEntity c;
/*     */   protected double d;
/*     */   private final AttributeInstance i;
/*     */   protected int e;
/*     */   private int j;
/*  42 */   private Vec3D k = Vec3D.a;
/*  43 */   private Vec3D l = Vec3D.a;
/*     */   private long m;
/*     */   private long n;
/*     */   private double o;
/*  47 */   protected float f = 0.5F;
/*     */   
/*     */   protected boolean g;
/*     */   
/*     */   private long p;
/*     */   
/*     */   protected PathfinderAbstract h;
/*     */   private BlockPosition q;
/*     */   private final Pathfinder r;
/*     */   
/*     */   public NavigationAbstract(EntityInsentient paramEntityInsentient, World paramWorld) {
/*  58 */     this.a = paramEntityInsentient;
/*  59 */     this.b = paramWorld;
/*  60 */     this.i = paramEntityInsentient.getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
/*  61 */     this.r = a();
/*     */   }
/*     */   
/*     */   protected abstract Pathfinder a();
/*     */   
/*     */   public void a(double paramDouble) {
/*  67 */     this.d = paramDouble;
/*     */   }
/*     */   
/*     */   public float i() {
/*  71 */     return (float)this.i.getValue();
/*     */   }
/*     */   
/*     */   public boolean j() {
/*  75 */     return this.g;
/*     */   }
/*     */   
/*     */   public void k() {
/*  79 */     if (this.b.getTime() - this.p > 20L) {
/*  80 */       if (this.q != null) {
/*  81 */         this.c = null;
/*  82 */         this.c = b(this.q);
/*  83 */         this.p = this.b.getTime();
/*  84 */         this.g = false;
/*     */       } 
/*     */     } else {
/*  87 */       this.g = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public final PathEntity a(double paramDouble1, double paramDouble2, double paramDouble3) {
/*  93 */     return b(new BlockPosition(paramDouble1, paramDouble2, paramDouble3));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PathEntity b(BlockPosition paramBlockPosition) {
/*  98 */     if (!b()) {
/*  99 */       return null;
/*     */     }
/*     */     
/* 102 */     if (this.c != null && !this.c.b() && paramBlockPosition.equals(this.q)) {
/* 103 */       return this.c;
/*     */     }
/*     */     
/* 106 */     this.q = paramBlockPosition;
/*     */     
/* 108 */     float f = i();
/* 109 */     this.b.methodProfiler.a("pathfind");
/* 110 */     BlockPosition blockPosition = new BlockPosition(this.a);
/* 111 */     int i = (int)(f + 8.0F);
/*     */     
/* 113 */     ChunkCache chunkCache = new ChunkCache(this.b, blockPosition.a(-i, -i, -i), blockPosition.a(i, i, i), 0);
/* 114 */     PathEntity pathEntity = this.r.a(chunkCache, this.a, this.q, f);
/* 115 */     this.b.methodProfiler.b();
/* 116 */     return pathEntity;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PathEntity a(Entity paramEntity) {
/* 121 */     if (!b()) {
/* 122 */       return null;
/*     */     }
/*     */     
/* 125 */     BlockPosition blockPosition1 = new BlockPosition(paramEntity);
/* 126 */     if (this.c != null && !this.c.b() && blockPosition1.equals(this.q)) {
/* 127 */       return this.c;
/*     */     }
/* 129 */     this.q = blockPosition1;
/*     */     
/* 131 */     float f = i();
/* 132 */     this.b.methodProfiler.a("pathfind");
/* 133 */     BlockPosition blockPosition2 = (new BlockPosition(this.a)).up();
/* 134 */     int i = (int)(f + 16.0F);
/*     */     
/* 136 */     ChunkCache chunkCache = new ChunkCache(this.b, blockPosition2.a(-i, -i, -i), blockPosition2.a(i, i, i), 0);
/* 137 */     PathEntity pathEntity = this.r.a(chunkCache, this.a, paramEntity, f);
/* 138 */     this.b.methodProfiler.b();
/* 139 */     return pathEntity;
/*     */   }
/*     */   
/*     */   public boolean a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/* 143 */     return a(a(paramDouble1, paramDouble2, paramDouble3), paramDouble4);
/*     */   }
/*     */   
/*     */   public boolean a(Entity paramEntity, double paramDouble) {
/* 147 */     PathEntity pathEntity = a(paramEntity);
/* 148 */     return (pathEntity != null && a(pathEntity, paramDouble));
/*     */   }
/*     */   
/*     */   public boolean a(@Nullable PathEntity paramPathEntity, double paramDouble) {
/* 152 */     if (paramPathEntity == null) {
/* 153 */       this.c = null;
/* 154 */       return false;
/*     */     } 
/* 156 */     if (!paramPathEntity.a(this.c)) {
/* 157 */       this.c = paramPathEntity;
/*     */     }
/* 159 */     q_();
/* 160 */     if (this.c.d() <= 0) {
/* 161 */       return false;
/*     */     }
/*     */     
/* 164 */     this.d = paramDouble;
/* 165 */     Vec3D vec3D = c();
/* 166 */     this.j = this.e;
/* 167 */     this.k = vec3D;
/* 168 */     return true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PathEntity l() {
/* 173 */     return this.c;
/*     */   }
/*     */   
/*     */   public void d() {
/* 177 */     this.e++;
/*     */     
/* 179 */     if (this.g) {
/* 180 */       k();
/*     */     }
/*     */     
/* 183 */     if (o()) {
/*     */       return;
/*     */     }
/*     */     
/* 187 */     if (b()) {
/* 188 */       n();
/* 189 */     } else if (this.c != null && this.c.e() < this.c.d()) {
/* 190 */       Vec3D vec3D1 = c();
/* 191 */       Vec3D vec3D2 = this.c.a(this.a, this.c.e());
/* 192 */       if (vec3D1.y > vec3D2.y && !this.a.onGround && MathHelper.floor(vec3D1.x) == MathHelper.floor(vec3D2.x) && MathHelper.floor(vec3D1.z) == MathHelper.floor(vec3D2.z)) {
/* 193 */         this.c.c(this.c.e() + 1);
/*     */       }
/*     */     } 
/*     */     
/* 197 */     m();
/*     */     
/* 199 */     if (o()) {
/*     */       return;
/*     */     }
/*     */     
/* 203 */     Vec3D vec3D = this.c.a(this.a);
/*     */     
/* 205 */     BlockPosition blockPosition = (new BlockPosition(vec3D)).down();
/* 206 */     AxisAlignedBB axisAlignedBB = this.b.getType(blockPosition).e(this.b, blockPosition);
/* 207 */     vec3D = vec3D.a(0.0D, 1.0D - axisAlignedBB.e, 0.0D);
/*     */     
/* 209 */     this.a.getControllerMove().a(vec3D.x, vec3D.y, vec3D.z, this.d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void n() {
/* 231 */     Vec3D vec3D1 = c();
/*     */ 
/*     */     
/* 234 */     int i = this.c.d();
/* 235 */     for (int j = this.c.e(); j < this.c.d(); j++) {
/* 236 */       if ((this.c.a(j)).b != Math.floor(vec3D1.y)) {
/* 237 */         i = j;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 243 */     this.f = (this.a.width > 0.75F) ? (this.a.width / 2.0F) : (0.75F - this.a.width / 2.0F);
/* 244 */     Vec3D vec3D2 = this.c.f();
/* 245 */     if (MathHelper.e((float)(this.a.locX - vec3D2.x + 0.5D)) < this.f && MathHelper.e((float)(this.a.locZ - vec3D2.z + 0.5D)) < this.f && Math.abs(this.a.locY - vec3D2.y) < 1.0D) {
/* 246 */       this.c.c(this.c.e() + 1);
/*     */     }
/*     */ 
/*     */     
/* 250 */     int k = MathHelper.f(this.a.width);
/* 251 */     int m = MathHelper.f(this.a.length);
/* 252 */     int n = k;
/* 253 */     for (int i1 = i - 1; i1 >= this.c.e(); i1--) {
/* 254 */       if (a(vec3D1, this.c.a(this.a, i1), k, m, n)) {
/* 255 */         this.c.c(i1);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 260 */     a(vec3D1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(Vec3D paramVec3D) {
/* 265 */     if (this.e - this.j > 100) {
/* 266 */       if (paramVec3D.distanceSquared(this.k) < 2.25D) {
/* 267 */         p();
/*     */       }
/* 269 */       this.j = this.e;
/* 270 */       this.k = paramVec3D;
/*     */     } 
/*     */     
/* 273 */     if (this.c != null && !this.c.b()) {
/* 274 */       Vec3D vec3D = this.c.f();
/*     */       
/* 276 */       if (vec3D.equals(this.l)) {
/* 277 */         this.m += System.currentTimeMillis() - this.n;
/*     */       } else {
/* 279 */         this.l = vec3D;
/* 280 */         double d = paramVec3D.f(this.l);
/* 281 */         this.o = (this.a.cy() > 0.0F) ? (d / this.a.cy() * 1000.0D) : 0.0D;
/*     */       } 
/*     */       
/* 284 */       if (this.o > 0.0D && this.m > this.o * 3.0D) {
/* 285 */         this.l = Vec3D.a;
/* 286 */         this.m = 0L;
/* 287 */         this.o = 0.0D;
/* 288 */         p();
/*     */       } 
/* 290 */       this.n = System.currentTimeMillis();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean o() {
/* 295 */     return (this.c == null || this.c.b());
/*     */   }
/*     */   
/*     */   public void p() {
/* 299 */     this.c = null;
/*     */   }
/*     */   
/*     */   protected abstract Vec3D c();
/*     */   
/*     */   protected abstract boolean b();
/*     */   
/*     */   protected boolean q() {
/* 307 */     return (this.a.isInWater() || this.a.au());
/*     */   }
/*     */   
/*     */   protected void q_() {
/* 311 */     if (this.c == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 316 */     for (byte b = 0; b < this.c.d(); b++) {
/* 317 */       PathPoint pathPoint1 = this.c.a(b);
/* 318 */       PathPoint pathPoint2 = (b + 1 < this.c.d()) ? this.c.a(b + 1) : null;
/*     */       
/* 320 */       IBlockData iBlockData = this.b.getType(new BlockPosition(pathPoint1.a, pathPoint1.b, pathPoint1.c));
/* 321 */       Block block = iBlockData.getBlock();
/*     */       
/* 323 */       if (block == Blocks.cauldron) {
/* 324 */         this.c.a(b, pathPoint1.a(pathPoint1.a, pathPoint1.b + 1, pathPoint1.c));
/* 325 */         if (pathPoint2 != null && pathPoint1.b >= pathPoint2.b) {
/* 326 */           this.c.a(b + 1, pathPoint2.a(pathPoint2.a, pathPoint1.b + 1, pathPoint2.c));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract boolean a(Vec3D paramVec3D1, Vec3D paramVec3D2, int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   public boolean a(BlockPosition paramBlockPosition) {
/* 335 */     return this.b.getType(paramBlockPosition.down()).b();
/*     */   }
/*     */   
/*     */   public PathfinderAbstract r() {
/* 339 */     return this.h;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NavigationAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */