/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Iterators;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumDirection
/*     */   implements INamable
/*     */ {
/*  17 */   DOWN(0, 1, -1, "down", EnumAxisDirection.NEGATIVE, EnumAxis.Y, new BaseBlockPosition(0, -1, 0)),
/*  18 */   UP(1, 0, -1, "up", EnumAxisDirection.POSITIVE, EnumAxis.Y, new BaseBlockPosition(0, 1, 0)),
/*  19 */   NORTH(2, 3, 2, "north", EnumAxisDirection.NEGATIVE, EnumAxis.Z, new BaseBlockPosition(0, 0, -1)),
/*  20 */   SOUTH(3, 2, 0, "south", EnumAxisDirection.POSITIVE, EnumAxis.Z, new BaseBlockPosition(0, 0, 1)),
/*  21 */   WEST(4, 5, 1, "west", EnumAxisDirection.NEGATIVE, EnumAxis.X, new BaseBlockPosition(-1, 0, 0)),
/*  22 */   EAST(5, 4, 3, "east", EnumAxisDirection.POSITIVE, EnumAxis.X, new BaseBlockPosition(1, 0, 0));
/*     */   
/*     */   private final int g;
/*     */   
/*     */   private final int h;
/*     */   
/*     */   private final int i;
/*     */   private final String j;
/*     */   private final EnumAxis k;
/*     */   
/*     */   static {
/*  33 */     n = new EnumDirection[6];
/*  34 */     o = new EnumDirection[4];
/*  35 */     p = Maps.newHashMap();
/*     */ 
/*     */     
/*  38 */     for (EnumDirection enumDirection : values()) {
/*  39 */       n[enumDirection.g] = enumDirection;
/*     */       
/*  41 */       if (enumDirection.k().c()) {
/*  42 */         o[enumDirection.i] = enumDirection;
/*     */       }
/*     */       
/*  45 */       p.put(enumDirection.j().toLowerCase(Locale.ROOT), enumDirection);
/*     */     } 
/*     */   }
/*     */   private final EnumAxisDirection l; private final BaseBlockPosition m; private static final EnumDirection[] n; private static final EnumDirection[] o; private static final Map<String, EnumDirection> p;
/*     */   EnumDirection(int paramInt1, int paramInt2, int paramInt3, String paramString1, EnumAxisDirection paramEnumAxisDirection, EnumAxis paramEnumAxis, BaseBlockPosition paramBaseBlockPosition) {
/*  50 */     this.g = paramInt1;
/*  51 */     this.i = paramInt3;
/*  52 */     this.h = paramInt2;
/*  53 */     this.j = paramString1;
/*  54 */     this.k = paramEnumAxis;
/*  55 */     this.l = paramEnumAxisDirection;
/*  56 */     this.m = paramBaseBlockPosition;
/*     */   }
/*     */   
/*     */   public int a() {
/*  60 */     return this.g;
/*     */   }
/*     */   
/*     */   public int get2DRotationValue() {
/*  64 */     return this.i;
/*     */   }
/*     */   
/*     */   public EnumAxisDirection c() {
/*  68 */     return this.l;
/*     */   }
/*     */   
/*     */   public EnumDirection opposite() {
/*  72 */     return fromType1(this.h);
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
/*     */ 
/*     */   
/*     */   public EnumDirection e() {
/* 126 */     switch (null.b[ordinal()]) {
/*     */       case 1:
/* 128 */         return EAST;
/*     */       case 2:
/* 130 */         return SOUTH;
/*     */       case 3:
/* 132 */         return WEST;
/*     */       case 4:
/* 134 */         return NORTH;
/*     */     } 
/* 136 */     throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
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
/*     */   public EnumDirection f() {
/* 201 */     switch (null.b[ordinal()]) {
/*     */       case 1:
/* 203 */         return WEST;
/*     */       case 2:
/* 205 */         return NORTH;
/*     */       case 3:
/* 207 */         return EAST;
/*     */       case 4:
/* 209 */         return SOUTH;
/*     */     } 
/* 211 */     throw new IllegalStateException("Unable to get CCW facing of " + this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAdjacentX() {
/* 216 */     if (this.k == EnumAxis.X) {
/* 217 */       return this.l.a();
/*     */     }
/* 219 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAdjacentY() {
/* 224 */     if (this.k == EnumAxis.Y) {
/* 225 */       return this.l.a();
/*     */     }
/* 227 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAdjacentZ() {
/* 232 */     if (this.k == EnumAxis.Z) {
/* 233 */       return this.l.a();
/*     */     }
/* 235 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String j() {
/* 240 */     return this.j;
/*     */   }
/*     */   
/*     */   public EnumAxis k() {
/* 244 */     return this.k;
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
/*     */   public static EnumDirection fromType1(int paramInt) {
/* 256 */     return n[MathHelper.a(paramInt % n.length)];
/*     */   }
/*     */   
/*     */   public static EnumDirection fromType2(int paramInt) {
/* 260 */     return o[MathHelper.a(paramInt % o.length)];
/*     */   }
/*     */   
/*     */   public static EnumDirection fromAngle(double paramDouble) {
/* 264 */     return fromType2(MathHelper.floor(paramDouble / 90.0D + 0.5D) & 0x3);
/*     */   }
/*     */   
/*     */   public float l() {
/* 268 */     return ((this.i & 0x3) * 90);
/*     */   }
/*     */   
/*     */   public static EnumDirection a(Random paramRandom) {
/* 272 */     return values()[paramRandom.nextInt((values()).length)];
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
/*     */   public String toString() {
/* 308 */     return this.j;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 313 */     return this.j;
/*     */   }
/*     */   
/*     */   public static EnumDirection a(EnumAxisDirection paramEnumAxisDirection, EnumAxis paramEnumAxis) {
/* 317 */     for (EnumDirection enumDirection : values()) {
/* 318 */       if (enumDirection.c() == paramEnumAxisDirection && enumDirection.k() == paramEnumAxis) {
/* 319 */         return enumDirection;
/*     */       }
/*     */     } 
/* 322 */     throw new IllegalArgumentException("No such direction: " + paramEnumAxisDirection + " " + paramEnumAxis);
/*     */   }
/*     */   
/*     */   public static EnumDirection a(BlockPosition paramBlockPosition, EntityLiving paramEntityLiving) {
/* 326 */     if (Math.abs(paramEntityLiving.locX - (paramBlockPosition.getX() + 0.5F)) < 2.0D && Math.abs(paramEntityLiving.locZ - (paramBlockPosition.getZ() + 0.5F)) < 2.0D) {
/*     */       
/* 328 */       double d = paramEntityLiving.locY + paramEntityLiving.getHeadHeight();
/* 329 */       if (d - paramBlockPosition.getY() > 2.0D) {
/* 330 */         return UP;
/*     */       }
/*     */       
/* 333 */       if (paramBlockPosition.getY() - d > 0.0D) {
/* 334 */         return DOWN;
/*     */       }
/*     */     } 
/*     */     
/* 338 */     return paramEntityLiving.getDirection().opposite();
/*     */   }
/*     */   
/*     */   public enum EnumAxis implements Predicate<EnumDirection>, INamable {
/* 342 */     X("x", EnumDirection.EnumDirectionLimit.HORIZONTAL),
/* 343 */     Y("y", EnumDirection.EnumDirectionLimit.VERTICAL),
/* 344 */     Z("z", EnumDirection.EnumDirectionLimit.HORIZONTAL);
/*     */ 
/*     */     
/* 347 */     private static final Map<String, EnumAxis> d = Maps.newHashMap();
/*     */     
/*     */     private final String e;
/*     */     private final EnumDirection.EnumDirectionLimit f;
/*     */     
/*     */     static {
/* 353 */       for (EnumAxis enumAxis : values()) {
/* 354 */         d.put(enumAxis.a().toLowerCase(Locale.ROOT), enumAxis);
/*     */       }
/*     */     }
/*     */     
/*     */     EnumAxis(String param1String1, EnumDirection.EnumDirectionLimit param1EnumDirectionLimit) {
/* 359 */       this.e = param1String1;
/* 360 */       this.f = param1EnumDirectionLimit;
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
/*     */     public String a() {
/* 372 */       return this.e;
/*     */     }
/*     */     
/*     */     public boolean b() {
/* 376 */       return (this.f == EnumDirection.EnumDirectionLimit.VERTICAL);
/*     */     }
/*     */     
/*     */     public boolean c() {
/* 380 */       return (this.f == EnumDirection.EnumDirectionLimit.HORIZONTAL);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 385 */       return this.e;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(@Nullable EnumDirection param1EnumDirection) {
/* 390 */       return (param1EnumDirection != null && param1EnumDirection.k() == this);
/*     */     }
/*     */     
/*     */     public EnumDirection.EnumDirectionLimit d() {
/* 394 */       return this.f;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 399 */       return this.e;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EnumAxisDirection {
/* 404 */     POSITIVE(1, "Towards positive"),
/* 405 */     NEGATIVE(-1, "Towards negative");
/*     */     
/*     */     private final int c;
/*     */     
/*     */     private final String d;
/*     */     
/*     */     EnumAxisDirection(int param1Int1, String param1String1) {
/* 412 */       this.c = param1Int1;
/* 413 */       this.d = param1String1;
/*     */     }
/*     */     
/*     */     public int a() {
/* 417 */       return this.c;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 426 */       return this.d;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum EnumDirectionLimit
/*     */     implements Predicate<EnumDirection>, Iterable<EnumDirection>
/*     */   {
/* 435 */     HORIZONTAL,
/* 436 */     VERTICAL;
/*     */ 
/*     */     
/*     */     public EnumDirection[] a() {
/* 440 */       switch (EnumDirection.null.c[ordinal()]) {
/*     */         case 1:
/* 442 */           return new EnumDirection[] { EnumDirection.NORTH, EnumDirection.EAST, EnumDirection.SOUTH, EnumDirection.WEST };
/*     */         case 2:
/* 444 */           return new EnumDirection[] { EnumDirection.UP, EnumDirection.DOWN };
/*     */       } 
/* 446 */       throw new Error("Someone's been tampering with the universe!");
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
/*     */     public EnumDirection a(Random param1Random) {
/* 460 */       EnumDirection[] arrayOfEnumDirection = a();
/* 461 */       return arrayOfEnumDirection[param1Random.nextInt(arrayOfEnumDirection.length)];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean a(@Nullable EnumDirection param1EnumDirection) {
/* 471 */       return (param1EnumDirection != null && param1EnumDirection.k().d() == this);
/*     */     }
/*     */ 
/*     */     
/*     */     public Iterator<EnumDirection> iterator() {
/* 476 */       return (Iterator<EnumDirection>)Iterators.forArray((Object[])a());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumDirection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */