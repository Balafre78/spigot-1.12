/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.AbstractIterator;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.concurrent.Immutable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Immutable
/*     */ public class BlockPosition
/*     */   extends BaseBlockPosition
/*     */ {
/*  18 */   private static final Logger b = LogManager.getLogger();
/*     */   
/*  20 */   public static final BlockPosition ZERO = new BlockPosition(0, 0, 0);
/*     */   
/*  22 */   private static final int c = 1 + MathHelper.e(MathHelper.c(30000000));
/*  23 */   private static final int d = c;
/*  24 */   private static final int f = 64 - c - d;
/*     */   
/*  26 */   private static final int g = 0 + d;
/*  27 */   private static final int h = g + f;
/*  28 */   private static final long i = (1L << c) - 1L;
/*  29 */   private static final long j = (1L << f) - 1L;
/*  30 */   private static final long k = (1L << d) - 1L;
/*     */   
/*     */   public BlockPosition(int paramInt1, int paramInt2, int paramInt3) {
/*  33 */     super(paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */   
/*     */   public BlockPosition(double paramDouble1, double paramDouble2, double paramDouble3) {
/*  37 */     super(paramDouble1, paramDouble2, paramDouble3);
/*     */   }
/*     */   
/*     */   public BlockPosition(Entity paramEntity) {
/*  41 */     this(paramEntity.locX, paramEntity.locY, paramEntity.locZ);
/*     */   }
/*     */   
/*     */   public BlockPosition(Vec3D paramVec3D) {
/*  45 */     this(paramVec3D.x, paramVec3D.y, paramVec3D.z);
/*     */   }
/*     */   
/*     */   public BlockPosition(BaseBlockPosition paramBaseBlockPosition) {
/*  49 */     this(paramBaseBlockPosition.getX(), paramBaseBlockPosition.getY(), paramBaseBlockPosition.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition a(double paramDouble1, double paramDouble2, double paramDouble3) {
/*  54 */     if (paramDouble1 == 0.0D && paramDouble2 == 0.0D && paramDouble3 == 0.0D) {
/*  55 */       return this;
/*     */     }
/*  57 */     return new BlockPosition(getX() + paramDouble1, getY() + paramDouble2, getZ() + paramDouble3);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition a(int paramInt1, int paramInt2, int paramInt3) {
/*  62 */     if (paramInt1 == 0 && paramInt2 == 0 && paramInt3 == 0) {
/*  63 */       return this;
/*     */     }
/*  65 */     return new BlockPosition(getX() + paramInt1, getY() + paramInt2, getZ() + paramInt3);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition a(BaseBlockPosition paramBaseBlockPosition) {
/*  70 */     return a(paramBaseBlockPosition.getX(), paramBaseBlockPosition.getY(), paramBaseBlockPosition.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition b(BaseBlockPosition paramBaseBlockPosition) {
/*  75 */     return a(-paramBaseBlockPosition.getX(), -paramBaseBlockPosition.getY(), -paramBaseBlockPosition.getZ());
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
/*     */   public BlockPosition up() {
/*  90 */     return up(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition up(int paramInt) {
/*  95 */     return shift(EnumDirection.UP, paramInt);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition down() {
/* 100 */     return down(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition down(int paramInt) {
/* 105 */     return shift(EnumDirection.DOWN, paramInt);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition north() {
/* 110 */     return north(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition north(int paramInt) {
/* 115 */     return shift(EnumDirection.NORTH, paramInt);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition south() {
/* 120 */     return south(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition south(int paramInt) {
/* 125 */     return shift(EnumDirection.SOUTH, paramInt);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition west() {
/* 130 */     return west(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition west(int paramInt) {
/* 135 */     return shift(EnumDirection.WEST, paramInt);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition east() {
/* 140 */     return east(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition east(int paramInt) {
/* 145 */     return shift(EnumDirection.EAST, paramInt);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition shift(EnumDirection paramEnumDirection) {
/* 150 */     return shift(paramEnumDirection, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition shift(EnumDirection paramEnumDirection, int paramInt) {
/* 155 */     if (paramInt == 0) {
/* 156 */       return this;
/*     */     }
/* 158 */     return new BlockPosition(getX() + paramEnumDirection.getAdjacentX() * paramInt, getY() + paramEnumDirection.getAdjacentY() * paramInt, getZ() + paramEnumDirection.getAdjacentZ() * paramInt);
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
/*     */   public BlockPosition a(EnumBlockRotation paramEnumBlockRotation) {
/* 173 */     switch (null.a[paramEnumBlockRotation.ordinal()])
/*     */     
/*     */     { default:
/* 176 */         return this;
/*     */       case 2:
/* 178 */         return new BlockPosition(-getZ(), getY(), getX());
/*     */       case 3:
/* 180 */         return new BlockPosition(-getX(), getY(), -getZ());
/*     */       case 4:
/* 182 */         break; }  return new BlockPosition(getZ(), getY(), -getX());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPosition c(BaseBlockPosition paramBaseBlockPosition) {
/* 188 */     return new BlockPosition(getY() * paramBaseBlockPosition.getZ() - getZ() * paramBaseBlockPosition.getY(), getZ() * paramBaseBlockPosition.getX() - getX() * paramBaseBlockPosition.getZ(), getX() * paramBaseBlockPosition.getY() - getY() * paramBaseBlockPosition.getX());
/*     */   }
/*     */ 
/*     */   
/*     */   public long asLong() {
/* 193 */     return (getX() & i) << h | (getY() & j) << g | (getZ() & k) << 0L;
/*     */   }
/*     */   
/*     */   public static BlockPosition fromLong(long paramLong) {
/* 197 */     int i = (int)(paramLong << 64 - h - c >> 64 - c);
/* 198 */     int j = (int)(paramLong << 64 - g - f >> 64 - f);
/* 199 */     int k = (int)(paramLong << 64 - d >> 64 - d);
/*     */     
/* 201 */     return new BlockPosition(i, j, k);
/*     */   }
/*     */   
/*     */   public static Iterable<BlockPosition> a(BlockPosition paramBlockPosition1, BlockPosition paramBlockPosition2) {
/* 205 */     return a(
/* 206 */         Math.min(paramBlockPosition1.getX(), paramBlockPosition2.getX()), 
/* 207 */         Math.min(paramBlockPosition1.getY(), paramBlockPosition2.getY()), 
/* 208 */         Math.min(paramBlockPosition1.getZ(), paramBlockPosition2.getZ()), 
/* 209 */         Math.max(paramBlockPosition1.getX(), paramBlockPosition2.getX()), 
/* 210 */         Math.max(paramBlockPosition1.getY(), paramBlockPosition2.getY()), 
/* 211 */         Math.max(paramBlockPosition1.getZ(), paramBlockPosition2.getZ()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static Iterable<BlockPosition> a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/* 216 */     return new Iterable<BlockPosition>(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6)
/*     */       {
/*     */         public Iterator<BlockPosition> iterator() {
/* 219 */           return (Iterator<BlockPosition>)new AbstractIterator<BlockPosition>(this)
/*     */             {
/*     */               private boolean b = true;
/*     */               private int c;
/*     */               private int d;
/*     */               private int e;
/*     */               
/*     */               protected BlockPosition a() {
/* 227 */                 if (this.b) {
/* 228 */                   this.b = false;
/*     */                   
/* 230 */                   this.c = this.a.a;
/* 231 */                   this.d = this.a.b;
/* 232 */                   this.e = this.a.c;
/* 233 */                   return new BlockPosition(this.a.a, this.a.b, this.a.c);
/* 234 */                 }  if (this.c == this.a.d && this.d == this.a.e && this.e == this.a.f)
/*     */                 {
/* 236 */                   return (BlockPosition)endOfData();
/*     */                 }
/*     */                 
/* 239 */                 if (this.c < this.a.d) {
/* 240 */                   this.c++;
/* 241 */                 } else if (this.d < this.a.e) {
/* 242 */                   this.c = this.a.a;
/* 243 */                   this.d++;
/* 244 */                 } else if (this.e < this.a.f) {
/* 245 */                   this.c = this.a.a;
/* 246 */                   this.d = this.a.b;
/* 247 */                   this.e++;
/*     */                 } 
/* 249 */                 return new BlockPosition(this.c, this.d, this.e);
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition h() {
/* 258 */     return this;
/*     */   }
/*     */   
/*     */   public static class MutableBlockPosition extends BlockPosition {
/*     */     protected int b;
/*     */     protected int c;
/*     */     protected int d;
/*     */     
/*     */     public MutableBlockPosition() {
/* 267 */       this(0, 0, 0);
/*     */     }
/*     */     
/*     */     public MutableBlockPosition(BlockPosition param1BlockPosition) {
/* 271 */       this(param1BlockPosition.getX(), param1BlockPosition.getY(), param1BlockPosition.getZ());
/*     */     }
/*     */     
/*     */     public MutableBlockPosition(int param1Int1, int param1Int2, int param1Int3) {
/* 275 */       super(0, 0, 0);
/* 276 */       this.b = param1Int1;
/* 277 */       this.c = param1Int2;
/* 278 */       this.d = param1Int3;
/*     */     }
/*     */ 
/*     */     
/*     */     public BlockPosition a(double param1Double1, double param1Double2, double param1Double3) {
/* 283 */       return super.a(param1Double1, param1Double2, param1Double3).h();
/*     */     }
/*     */ 
/*     */     
/*     */     public BlockPosition a(int param1Int1, int param1Int2, int param1Int3) {
/* 288 */       return super.a(param1Int1, param1Int2, param1Int3).h();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public BlockPosition shift(EnumDirection param1EnumDirection, int param1Int) {
/* 298 */       return super.shift(param1EnumDirection, param1Int).h();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public BlockPosition a(EnumBlockRotation param1EnumBlockRotation) {
/* 308 */       return super.a(param1EnumBlockRotation).h();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getX() {
/* 313 */       return this.b;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getY() {
/* 318 */       return this.c;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getZ() {
/* 323 */       return this.d;
/*     */     }
/*     */     
/*     */     public MutableBlockPosition c(int param1Int1, int param1Int2, int param1Int3) {
/* 327 */       this.b = param1Int1;
/* 328 */       this.c = param1Int2;
/* 329 */       this.d = param1Int3;
/* 330 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MutableBlockPosition c(double param1Double1, double param1Double2, double param1Double3) {
/* 338 */       return c(MathHelper.floor(param1Double1), MathHelper.floor(param1Double2), MathHelper.floor(param1Double3));
/*     */     }
/*     */     
/*     */     public MutableBlockPosition g(BaseBlockPosition param1BaseBlockPosition) {
/* 342 */       return c(param1BaseBlockPosition.getX(), param1BaseBlockPosition.getY(), param1BaseBlockPosition.getZ());
/*     */     }
/*     */     
/*     */     public MutableBlockPosition c(EnumDirection param1EnumDirection) {
/* 346 */       return c(param1EnumDirection, 1);
/*     */     }
/*     */     
/*     */     public MutableBlockPosition c(EnumDirection param1EnumDirection, int param1Int) {
/* 350 */       return c(this.b + param1EnumDirection.getAdjacentX() * param1Int, this.c + param1EnumDirection.getAdjacentY() * param1Int, this.d + param1EnumDirection.getAdjacentZ() * param1Int);
/*     */     }
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
/*     */     public void p(int param1Int) {
/* 366 */       this.c = param1Int;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public BlockPosition h() {
/* 375 */       return new BlockPosition(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static Iterable<MutableBlockPosition> b(BlockPosition paramBlockPosition1, BlockPosition paramBlockPosition2) {
/* 380 */     return b(
/* 381 */         Math.min(paramBlockPosition1.getX(), paramBlockPosition2.getX()), 
/* 382 */         Math.min(paramBlockPosition1.getY(), paramBlockPosition2.getY()), 
/* 383 */         Math.min(paramBlockPosition1.getZ(), paramBlockPosition2.getZ()), 
/* 384 */         Math.max(paramBlockPosition1.getX(), paramBlockPosition2.getX()), 
/* 385 */         Math.max(paramBlockPosition1.getY(), paramBlockPosition2.getY()), 
/* 386 */         Math.max(paramBlockPosition1.getZ(), paramBlockPosition2.getZ()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static Iterable<MutableBlockPosition> b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/* 391 */     return new Iterable<MutableBlockPosition>(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6)
/*     */       {
/*     */         public Iterator<BlockPosition.MutableBlockPosition> iterator() {
/* 394 */           return (Iterator<BlockPosition.MutableBlockPosition>)new AbstractIterator<BlockPosition.MutableBlockPosition>(this)
/*     */             {
/*     */               private BlockPosition.MutableBlockPosition b;
/*     */               
/*     */               protected BlockPosition.MutableBlockPosition a() {
/* 399 */                 if (this.b == null) {
/*     */                   
/* 401 */                   this.b = new BlockPosition.MutableBlockPosition(this.a.a, this.a.b, this.a.c);
/* 402 */                   return this.b;
/* 403 */                 }  if (this.b.b == this.a.d && this.b.c == this.a.e && this.b.d == this.a.f)
/*     */                 {
/* 405 */                   return (BlockPosition.MutableBlockPosition)endOfData();
/*     */                 }
/*     */                 
/* 408 */                 if (this.b.b < this.a.d) {
/* 409 */                   this.b.b++;
/* 410 */                 } else if (this.b.c < this.a.e) {
/* 411 */                   this.b.b = this.a.a;
/* 412 */                   this.b.c++;
/* 413 */                 } else if (this.b.d < this.a.f) {
/* 414 */                   this.b.b = this.a.a;
/* 415 */                   this.b.c = this.a.b;
/* 416 */                   this.b.d++;
/*     */                 } 
/* 418 */                 return this.b;
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class PooledBlockPosition
/*     */     extends MutableBlockPosition
/*     */   {
/*     */     private boolean f;
/* 430 */     private static final List<PooledBlockPosition> g = Lists.newArrayList();
/*     */     
/*     */     private PooledBlockPosition(int param1Int1, int param1Int2, int param1Int3) {
/* 433 */       super(param1Int1, param1Int2, param1Int3);
/*     */     }
/*     */     
/*     */     public static PooledBlockPosition s() {
/* 437 */       return e(0, 0, 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static PooledBlockPosition d(double param1Double1, double param1Double2, double param1Double3) {
/* 445 */       return e(MathHelper.floor(param1Double1), MathHelper.floor(param1Double2), MathHelper.floor(param1Double3));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static PooledBlockPosition e(int param1Int1, int param1Int2, int param1Int3) {
/* 453 */       synchronized (g) {
/* 454 */         if (!g.isEmpty()) {
/* 455 */           PooledBlockPosition pooledBlockPosition = g.remove(g.size() - 1);
/* 456 */           if (pooledBlockPosition != null && pooledBlockPosition.f) {
/* 457 */             pooledBlockPosition.f = false;
/* 458 */             pooledBlockPosition.f(param1Int1, param1Int2, param1Int3);
/* 459 */             return pooledBlockPosition;
/*     */           } 
/*     */         } 
/*     */       } 
/* 463 */       return new PooledBlockPosition(param1Int1, param1Int2, param1Int3);
/*     */     }
/*     */     
/*     */     public void t() {
/* 467 */       synchronized (g) {
/* 468 */         if (g.size() < 100) {
/* 469 */           g.add(this);
/*     */         }
/* 471 */         this.f = true;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public PooledBlockPosition f(int param1Int1, int param1Int2, int param1Int3) {
/* 477 */       if (this.f) {
/* 478 */         BlockPosition.o().error("PooledMutableBlockPosition modified after it was released.", new Throwable());
/* 479 */         this.f = false;
/*     */       } 
/*     */       
/* 482 */       return (PooledBlockPosition)super.c(param1Int1, param1Int2, param1Int3);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public PooledBlockPosition e(double param1Double1, double param1Double2, double param1Double3) {
/* 492 */       return (PooledBlockPosition)super.c(param1Double1, param1Double2, param1Double3);
/*     */     }
/*     */ 
/*     */     
/*     */     public PooledBlockPosition j(BaseBlockPosition param1BaseBlockPosition) {
/* 497 */       return (PooledBlockPosition)super.g(param1BaseBlockPosition);
/*     */     }
/*     */ 
/*     */     
/*     */     public PooledBlockPosition d(EnumDirection param1EnumDirection) {
/* 502 */       return (PooledBlockPosition)super.c(param1EnumDirection);
/*     */     }
/*     */ 
/*     */     
/*     */     public PooledBlockPosition d(EnumDirection param1EnumDirection, int param1Int) {
/* 507 */       return (PooledBlockPosition)super.c(param1EnumDirection, param1Int);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */