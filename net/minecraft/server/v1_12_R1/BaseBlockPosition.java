/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.MoreObjects;
/*     */ import javax.annotation.concurrent.Immutable;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Immutable
/*     */ public class BaseBlockPosition
/*     */   implements Comparable<BaseBlockPosition>
/*     */ {
/*  12 */   public static final BaseBlockPosition ZERO = new BaseBlockPosition(0, 0, 0);
/*     */   
/*     */   private final int a;
/*     */   private final int b;
/*     */   private final int c;
/*     */   
/*     */   public BaseBlockPosition(int paramInt1, int paramInt2, int paramInt3) {
/*  19 */     this.a = paramInt1;
/*  20 */     this.b = paramInt2;
/*  21 */     this.c = paramInt3;
/*     */   }
/*     */   
/*     */   public BaseBlockPosition(double paramDouble1, double paramDouble2, double paramDouble3) {
/*  25 */     this(MathHelper.floor(paramDouble1), MathHelper.floor(paramDouble2), MathHelper.floor(paramDouble3));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/*  30 */     if (this == paramObject) {
/*  31 */       return true;
/*     */     }
/*  33 */     if (!(paramObject instanceof BaseBlockPosition)) {
/*  34 */       return false;
/*     */     }
/*     */     
/*  37 */     BaseBlockPosition baseBlockPosition = (BaseBlockPosition)paramObject;
/*     */     
/*  39 */     if (getX() != baseBlockPosition.getX()) {
/*  40 */       return false;
/*     */     }
/*  42 */     if (getY() != baseBlockPosition.getY()) {
/*  43 */       return false;
/*     */     }
/*  45 */     if (getZ() != baseBlockPosition.getZ()) {
/*  46 */       return false;
/*     */     }
/*     */     
/*  49 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  54 */     return (getY() + getZ() * 31) * 31 + getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int l(BaseBlockPosition paramBaseBlockPosition) {
/*  59 */     if (getY() == paramBaseBlockPosition.getY()) {
/*  60 */       if (getZ() == paramBaseBlockPosition.getZ()) {
/*  61 */         return getX() - paramBaseBlockPosition.getX();
/*     */       }
/*  63 */       return getZ() - paramBaseBlockPosition.getZ();
/*     */     } 
/*  65 */     return getY() - paramBaseBlockPosition.getY();
/*     */   }
/*     */   
/*     */   public int getX() {
/*  69 */     return this.a;
/*     */   }
/*     */   
/*     */   public int getY() {
/*  73 */     return this.b;
/*     */   }
/*     */   
/*     */   public int getZ() {
/*  77 */     return this.c;
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
/*     */   public BaseBlockPosition d(BaseBlockPosition paramBaseBlockPosition) {
/* 181 */     return new BaseBlockPosition(getY() * paramBaseBlockPosition.getZ() - getZ() * paramBaseBlockPosition.getY(), getZ() * paramBaseBlockPosition.getX() - getX() * paramBaseBlockPosition.getZ(), getX() * paramBaseBlockPosition.getY() - getY() * paramBaseBlockPosition.getX());
/*     */   }
/*     */   
/*     */   public double h(int paramInt1, int paramInt2, int paramInt3) {
/* 185 */     double d1 = (getX() - paramInt1);
/* 186 */     double d2 = (getY() - paramInt2);
/* 187 */     double d3 = (getZ() - paramInt3);
/*     */     
/* 189 */     return Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double distanceSquared(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 197 */     double d1 = getX() - paramDouble1;
/* 198 */     double d2 = getY() - paramDouble2;
/* 199 */     double d3 = getZ() - paramDouble3;
/* 200 */     return d1 * d1 + d2 * d2 + d3 * d3;
/*     */   }
/*     */   
/*     */   public double g(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 204 */     double d1 = getX() + 0.5D - paramDouble1;
/* 205 */     double d2 = getY() + 0.5D - paramDouble2;
/* 206 */     double d3 = getZ() + 0.5D - paramDouble3;
/* 207 */     return d1 * d1 + d2 * d2 + d3 * d3;
/*     */   }
/*     */   
/*     */   public double n(BaseBlockPosition paramBaseBlockPosition) {
/* 211 */     return distanceSquared(paramBaseBlockPosition.getX(), paramBaseBlockPosition.getY(), paramBaseBlockPosition.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 216 */     return MoreObjects.toStringHelper(this)
/* 217 */       .add("x", getX())
/* 218 */       .add("y", getY())
/* 219 */       .add("z", getZ())
/* 220 */       .toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BaseBlockPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */