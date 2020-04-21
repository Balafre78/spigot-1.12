/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AxisAlignedBB
/*     */ {
/*     */   public final double a;
/*     */   public final double b;
/*     */   public final double c;
/*     */   public final double d;
/*     */   public final double e;
/*     */   public final double f;
/*     */   
/*     */   public AxisAlignedBB(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6) {
/*  18 */     this.a = Math.min(paramDouble1, paramDouble4);
/*  19 */     this.b = Math.min(paramDouble2, paramDouble5);
/*  20 */     this.c = Math.min(paramDouble3, paramDouble6);
/*  21 */     this.d = Math.max(paramDouble1, paramDouble4);
/*  22 */     this.e = Math.max(paramDouble2, paramDouble5);
/*  23 */     this.f = Math.max(paramDouble3, paramDouble6);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB(BlockPosition paramBlockPosition) {
/*  27 */     this(paramBlockPosition.getX(), paramBlockPosition.getY(), paramBlockPosition.getZ(), (paramBlockPosition.getX() + 1), (paramBlockPosition.getY() + 1), (paramBlockPosition.getZ() + 1));
/*     */   }
/*     */   
/*     */   public AxisAlignedBB(BlockPosition paramBlockPosition1, BlockPosition paramBlockPosition2) {
/*  31 */     this(paramBlockPosition1.getX(), paramBlockPosition1.getY(), paramBlockPosition1.getZ(), paramBlockPosition2.getX(), paramBlockPosition2.getY(), paramBlockPosition2.getZ());
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
/*     */   public AxisAlignedBB e(double paramDouble) {
/*  55 */     return new AxisAlignedBB(this.a, this.b, this.c, this.d, paramDouble, this.f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/*  64 */     if (this == paramObject) {
/*  65 */       return true;
/*     */     }
/*  67 */     if (!(paramObject instanceof AxisAlignedBB)) {
/*  68 */       return false;
/*     */     }
/*     */     
/*  71 */     AxisAlignedBB axisAlignedBB = (AxisAlignedBB)paramObject;
/*     */     
/*  73 */     if (Double.compare(axisAlignedBB.a, this.a) != 0) {
/*  74 */       return false;
/*     */     }
/*  76 */     if (Double.compare(axisAlignedBB.b, this.b) != 0) {
/*  77 */       return false;
/*     */     }
/*  79 */     if (Double.compare(axisAlignedBB.c, this.c) != 0) {
/*  80 */       return false;
/*     */     }
/*  82 */     if (Double.compare(axisAlignedBB.d, this.d) != 0) {
/*  83 */       return false;
/*     */     }
/*  85 */     if (Double.compare(axisAlignedBB.e, this.e) != 0) {
/*  86 */       return false;
/*     */     }
/*  88 */     return (Double.compare(axisAlignedBB.f, this.f) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  93 */     long l = Double.doubleToLongBits(this.a);
/*  94 */     int i = (int)(l ^ l >>> 32L);
/*  95 */     l = Double.doubleToLongBits(this.b);
/*  96 */     i = 31 * i + (int)(l ^ l >>> 32L);
/*  97 */     l = Double.doubleToLongBits(this.c);
/*  98 */     i = 31 * i + (int)(l ^ l >>> 32L);
/*  99 */     l = Double.doubleToLongBits(this.d);
/* 100 */     i = 31 * i + (int)(l ^ l >>> 32L);
/* 101 */     l = Double.doubleToLongBits(this.e);
/* 102 */     i = 31 * i + (int)(l ^ l >>> 32L);
/* 103 */     l = Double.doubleToLongBits(this.f);
/* 104 */     i = 31 * i + (int)(l ^ l >>> 32L);
/* 105 */     return i;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB a(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 109 */     double d1 = this.a;
/* 110 */     double d2 = this.b;
/* 111 */     double d3 = this.c;
/* 112 */     double d4 = this.d;
/* 113 */     double d5 = this.e;
/* 114 */     double d6 = this.f;
/*     */     
/* 116 */     if (paramDouble1 < 0.0D) {
/* 117 */       d1 -= paramDouble1;
/* 118 */     } else if (paramDouble1 > 0.0D) {
/* 119 */       d4 -= paramDouble1;
/*     */     } 
/*     */     
/* 122 */     if (paramDouble2 < 0.0D) {
/* 123 */       d2 -= paramDouble2;
/* 124 */     } else if (paramDouble2 > 0.0D) {
/* 125 */       d5 -= paramDouble2;
/*     */     } 
/*     */     
/* 128 */     if (paramDouble3 < 0.0D) {
/* 129 */       d3 -= paramDouble3;
/* 130 */     } else if (paramDouble3 > 0.0D) {
/* 131 */       d6 -= paramDouble3;
/*     */     } 
/*     */     
/* 134 */     return new AxisAlignedBB(d1, d2, d3, d4, d5, d6);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 138 */     double d1 = this.a;
/* 139 */     double d2 = this.b;
/* 140 */     double d3 = this.c;
/* 141 */     double d4 = this.d;
/* 142 */     double d5 = this.e;
/* 143 */     double d6 = this.f;
/*     */     
/* 145 */     if (paramDouble1 < 0.0D) {
/* 146 */       d1 += paramDouble1;
/* 147 */     } else if (paramDouble1 > 0.0D) {
/* 148 */       d4 += paramDouble1;
/*     */     } 
/*     */     
/* 151 */     if (paramDouble2 < 0.0D) {
/* 152 */       d2 += paramDouble2;
/* 153 */     } else if (paramDouble2 > 0.0D) {
/* 154 */       d5 += paramDouble2;
/*     */     } 
/*     */     
/* 157 */     if (paramDouble3 < 0.0D) {
/* 158 */       d3 += paramDouble3;
/* 159 */     } else if (paramDouble3 > 0.0D) {
/* 160 */       d6 += paramDouble3;
/*     */     } 
/*     */     
/* 163 */     return new AxisAlignedBB(d1, d2, d3, d4, d5, d6);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB grow(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 167 */     double d1 = this.a - paramDouble1;
/* 168 */     double d2 = this.b - paramDouble2;
/* 169 */     double d3 = this.c - paramDouble3;
/* 170 */     double d4 = this.d + paramDouble1;
/* 171 */     double d5 = this.e + paramDouble2;
/* 172 */     double d6 = this.f + paramDouble3;
/*     */     
/* 174 */     return new AxisAlignedBB(d1, d2, d3, d4, d5, d6);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB g(double paramDouble) {
/* 178 */     return grow(paramDouble, paramDouble, paramDouble);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB a(AxisAlignedBB paramAxisAlignedBB) {
/* 182 */     double d1 = Math.max(this.a, paramAxisAlignedBB.a);
/* 183 */     double d2 = Math.max(this.b, paramAxisAlignedBB.b);
/* 184 */     double d3 = Math.max(this.c, paramAxisAlignedBB.c);
/* 185 */     double d4 = Math.min(this.d, paramAxisAlignedBB.d);
/* 186 */     double d5 = Math.min(this.e, paramAxisAlignedBB.e);
/* 187 */     double d6 = Math.min(this.f, paramAxisAlignedBB.f);
/*     */     
/* 189 */     return new AxisAlignedBB(d1, d2, d3, d4, d5, d6);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(AxisAlignedBB paramAxisAlignedBB) {
/* 193 */     double d1 = Math.min(this.a, paramAxisAlignedBB.a);
/* 194 */     double d2 = Math.min(this.b, paramAxisAlignedBB.b);
/* 195 */     double d3 = Math.min(this.c, paramAxisAlignedBB.c);
/* 196 */     double d4 = Math.max(this.d, paramAxisAlignedBB.d);
/* 197 */     double d5 = Math.max(this.e, paramAxisAlignedBB.e);
/* 198 */     double d6 = Math.max(this.f, paramAxisAlignedBB.f);
/*     */     
/* 200 */     return new AxisAlignedBB(d1, d2, d3, d4, d5, d6);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB d(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 204 */     return new AxisAlignedBB(this.a + paramDouble1, this.b + paramDouble2, this.c + paramDouble3, this.d + paramDouble1, this.e + paramDouble2, this.f + paramDouble3);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB a(BlockPosition paramBlockPosition) {
/* 208 */     return new AxisAlignedBB(this.a + paramBlockPosition.getX(), this.b + paramBlockPosition.getY(), this.c + paramBlockPosition.getZ(), this.d + paramBlockPosition.getX(), this.e + paramBlockPosition.getY(), this.f + paramBlockPosition.getZ());
/*     */   }
/*     */   
/*     */   public AxisAlignedBB a(Vec3D paramVec3D) {
/* 212 */     return d(paramVec3D.x, paramVec3D.y, paramVec3D.z);
/*     */   }
/*     */ 
/*     */   
/*     */   public double a(AxisAlignedBB paramAxisAlignedBB, double paramDouble) {
/* 217 */     if (paramAxisAlignedBB.e <= this.b || paramAxisAlignedBB.b >= this.e || paramAxisAlignedBB.f <= this.c || paramAxisAlignedBB.c >= this.f) {
/* 218 */       return paramDouble;
/*     */     }
/*     */ 
/*     */     
/* 222 */     if (paramDouble > 0.0D && paramAxisAlignedBB.d <= this.a) {
/* 223 */       double d = this.a - paramAxisAlignedBB.d;
/* 224 */       if (d < paramDouble) {
/* 225 */         paramDouble = d;
/*     */       }
/* 227 */     } else if (paramDouble < 0.0D && paramAxisAlignedBB.a >= this.d) {
/* 228 */       double d = this.d - paramAxisAlignedBB.a;
/* 229 */       if (d > paramDouble) {
/* 230 */         paramDouble = d;
/*     */       }
/*     */     } 
/*     */     
/* 234 */     return paramDouble;
/*     */   }
/*     */ 
/*     */   
/*     */   public double b(AxisAlignedBB paramAxisAlignedBB, double paramDouble) {
/* 239 */     if (paramAxisAlignedBB.d <= this.a || paramAxisAlignedBB.a >= this.d || paramAxisAlignedBB.f <= this.c || paramAxisAlignedBB.c >= this.f) {
/* 240 */       return paramDouble;
/*     */     }
/*     */ 
/*     */     
/* 244 */     if (paramDouble > 0.0D && paramAxisAlignedBB.e <= this.b) {
/* 245 */       double d = this.b - paramAxisAlignedBB.e;
/* 246 */       if (d < paramDouble) {
/* 247 */         paramDouble = d;
/*     */       }
/* 249 */     } else if (paramDouble < 0.0D && paramAxisAlignedBB.b >= this.e) {
/* 250 */       double d = this.e - paramAxisAlignedBB.b;
/* 251 */       if (d > paramDouble) {
/* 252 */         paramDouble = d;
/*     */       }
/*     */     } 
/*     */     
/* 256 */     return paramDouble;
/*     */   }
/*     */ 
/*     */   
/*     */   public double c(AxisAlignedBB paramAxisAlignedBB, double paramDouble) {
/* 261 */     if (paramAxisAlignedBB.d <= this.a || paramAxisAlignedBB.a >= this.d || paramAxisAlignedBB.e <= this.b || paramAxisAlignedBB.b >= this.e) {
/* 262 */       return paramDouble;
/*     */     }
/*     */ 
/*     */     
/* 266 */     if (paramDouble > 0.0D && paramAxisAlignedBB.f <= this.c) {
/* 267 */       double d = this.c - paramAxisAlignedBB.f;
/* 268 */       if (d < paramDouble) {
/* 269 */         paramDouble = d;
/*     */       }
/* 271 */     } else if (paramDouble < 0.0D && paramAxisAlignedBB.c >= this.f) {
/* 272 */       double d = this.f - paramAxisAlignedBB.c;
/* 273 */       if (d > paramDouble) {
/* 274 */         paramDouble = d;
/*     */       }
/*     */     } 
/*     */     
/* 278 */     return paramDouble;
/*     */   }
/*     */   
/*     */   public boolean c(AxisAlignedBB paramAxisAlignedBB) {
/* 282 */     return a(paramAxisAlignedBB.a, paramAxisAlignedBB.b, paramAxisAlignedBB.c, paramAxisAlignedBB.d, paramAxisAlignedBB.e, paramAxisAlignedBB.f);
/*     */   }
/*     */   
/*     */   public boolean a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6) {
/* 286 */     return (this.a < paramDouble4 && this.d > paramDouble1 && this.b < paramDouble5 && this.e > paramDouble2 && this.c < paramDouble6 && this.f > paramDouble3);
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
/*     */   
/*     */   public boolean b(Vec3D paramVec3D) {
/* 299 */     if (paramVec3D.x <= this.a || paramVec3D.x >= this.d) {
/* 300 */       return false;
/*     */     }
/* 302 */     if (paramVec3D.y <= this.b || paramVec3D.y >= this.e) {
/* 303 */       return false;
/*     */     }
/* 305 */     if (paramVec3D.z <= this.c || paramVec3D.z >= this.f) {
/* 306 */       return false;
/*     */     }
/* 308 */     return true;
/*     */   }
/*     */   
/*     */   public double a() {
/* 312 */     double d1 = this.d - this.a;
/* 313 */     double d2 = this.e - this.b;
/* 314 */     double d3 = this.f - this.c;
/* 315 */     return (d1 + d2 + d3) / 3.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB shrink(double paramDouble) {
/* 323 */     return g(-paramDouble);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public MovingObjectPosition b(Vec3D paramVec3D1, Vec3D paramVec3D2) {
/* 328 */     Vec3D vec3D1 = a(this.a, paramVec3D1, paramVec3D2);
/* 329 */     EnumDirection enumDirection = EnumDirection.WEST;
/*     */     
/* 331 */     Vec3D vec3D2 = a(this.d, paramVec3D1, paramVec3D2);
/* 332 */     if (vec3D2 != null && a(paramVec3D1, vec3D1, vec3D2)) {
/* 333 */       vec3D1 = vec3D2;
/* 334 */       enumDirection = EnumDirection.EAST;
/*     */     } 
/*     */     
/* 337 */     vec3D2 = b(this.b, paramVec3D1, paramVec3D2);
/* 338 */     if (vec3D2 != null && a(paramVec3D1, vec3D1, vec3D2)) {
/* 339 */       vec3D1 = vec3D2;
/* 340 */       enumDirection = EnumDirection.DOWN;
/*     */     } 
/*     */     
/* 343 */     vec3D2 = b(this.e, paramVec3D1, paramVec3D2);
/* 344 */     if (vec3D2 != null && a(paramVec3D1, vec3D1, vec3D2)) {
/* 345 */       vec3D1 = vec3D2;
/* 346 */       enumDirection = EnumDirection.UP;
/*     */     } 
/*     */     
/* 349 */     vec3D2 = c(this.c, paramVec3D1, paramVec3D2);
/* 350 */     if (vec3D2 != null && a(paramVec3D1, vec3D1, vec3D2)) {
/* 351 */       vec3D1 = vec3D2;
/* 352 */       enumDirection = EnumDirection.NORTH;
/*     */     } 
/*     */     
/* 355 */     vec3D2 = c(this.f, paramVec3D1, paramVec3D2);
/* 356 */     if (vec3D2 != null && a(paramVec3D1, vec3D1, vec3D2)) {
/* 357 */       vec3D1 = vec3D2;
/* 358 */       enumDirection = EnumDirection.SOUTH;
/*     */     } 
/*     */     
/* 361 */     return (vec3D1 == null) ? null : new MovingObjectPosition(vec3D1, enumDirection);
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   boolean a(Vec3D paramVec3D1, @Nullable Vec3D paramVec3D2, Vec3D paramVec3D3) {
/* 366 */     return (paramVec3D2 == null || paramVec3D1.distanceSquared(paramVec3D3) < paramVec3D1.distanceSquared(paramVec3D2));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   @VisibleForTesting
/*     */   Vec3D a(double paramDouble, Vec3D paramVec3D1, Vec3D paramVec3D2) {
/* 372 */     Vec3D vec3D = paramVec3D1.a(paramVec3D2, paramDouble);
/* 373 */     return (vec3D == null || !c(vec3D)) ? null : vec3D;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   @VisibleForTesting
/*     */   Vec3D b(double paramDouble, Vec3D paramVec3D1, Vec3D paramVec3D2) {
/* 379 */     Vec3D vec3D = paramVec3D1.b(paramVec3D2, paramDouble);
/* 380 */     return (vec3D == null || !d(vec3D)) ? null : vec3D;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   @VisibleForTesting
/*     */   Vec3D c(double paramDouble, Vec3D paramVec3D1, Vec3D paramVec3D2) {
/* 386 */     Vec3D vec3D = paramVec3D1.c(paramVec3D2, paramDouble);
/* 387 */     return (vec3D == null || !e(vec3D)) ? null : vec3D;
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   public boolean c(Vec3D paramVec3D) {
/* 392 */     return (paramVec3D.y >= this.b && paramVec3D.y <= this.e && paramVec3D.z >= this.c && paramVec3D.z <= this.f);
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   public boolean d(Vec3D paramVec3D) {
/* 397 */     return (paramVec3D.x >= this.a && paramVec3D.x <= this.d && paramVec3D.z >= this.c && paramVec3D.z <= this.f);
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   public boolean e(Vec3D paramVec3D) {
/* 402 */     return (paramVec3D.x >= this.a && paramVec3D.x <= this.d && paramVec3D.y >= this.b && paramVec3D.y <= this.e);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 406 */     return "box[" + this.a + ", " + this.b + ", " + this.c + " -> " + this.d + ", " + this.e + ", " + this.f + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AxisAlignedBB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */