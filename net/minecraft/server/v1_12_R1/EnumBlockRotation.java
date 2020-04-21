/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumBlockRotation
/*     */ {
/*   8 */   NONE("rotate_0"),
/*   9 */   CLOCKWISE_90("rotate_90"),
/*  10 */   CLOCKWISE_180("rotate_180"),
/*  11 */   COUNTERCLOCKWISE_90("rotate_270"); private final String e;
/*     */   
/*     */   static {
/*  14 */     f = new String[(values()).length];
/*     */ 
/*     */     
/*  17 */     byte b = 0;
/*  18 */     for (EnumBlockRotation enumBlockRotation : values())
/*  19 */       f[b++] = enumBlockRotation.e; 
/*     */   }
/*     */   private static final String[] f;
/*     */   
/*     */   EnumBlockRotation(String paramString1) {
/*  24 */     this.e = paramString1;
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
/*     */   public EnumBlockRotation a(EnumBlockRotation paramEnumBlockRotation) {
/*  41 */     switch (null.a[paramEnumBlockRotation.ordinal()]) {
/*     */       case 3:
/*  43 */         switch (null.a[ordinal()]) {
/*     */           case 1:
/*  45 */             return CLOCKWISE_180;
/*     */           case 2:
/*  47 */             return COUNTERCLOCKWISE_90;
/*     */           case 3:
/*  49 */             return NONE;
/*     */           case 4:
/*  51 */             return CLOCKWISE_90;
/*     */         } 
/*     */       case 4:
/*  54 */         switch (null.a[ordinal()]) {
/*     */           case 1:
/*  56 */             return COUNTERCLOCKWISE_90;
/*     */           case 2:
/*  58 */             return NONE;
/*     */           case 3:
/*  60 */             return CLOCKWISE_90;
/*     */           case 4:
/*  62 */             return CLOCKWISE_180;
/*     */         } 
/*     */       case 2:
/*  65 */         switch (null.a[ordinal()]) {
/*     */           case 1:
/*  67 */             return CLOCKWISE_90;
/*     */           case 2:
/*  69 */             return CLOCKWISE_180;
/*     */           case 3:
/*  71 */             return COUNTERCLOCKWISE_90;
/*     */           case 4:
/*  73 */             return NONE;
/*     */         }  break;
/*     */     } 
/*  76 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDirection a(EnumDirection paramEnumDirection) {
/*  81 */     if (paramEnumDirection.k() == EnumDirection.EnumAxis.Y) {
/*  82 */       return paramEnumDirection;
/*     */     }
/*  84 */     switch (null.a[ordinal()]) {
/*     */       case 3:
/*  86 */         return paramEnumDirection.opposite();
/*     */       case 4:
/*  88 */         return paramEnumDirection.f();
/*     */       case 2:
/*  90 */         return paramEnumDirection.e();
/*     */     } 
/*  92 */     return paramEnumDirection;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(int paramInt1, int paramInt2) {
/*  97 */     switch (null.a[ordinal()]) {
/*     */       case 3:
/*  99 */         return (paramInt1 + paramInt2 / 2) % paramInt2;
/*     */       case 4:
/* 101 */         return (paramInt1 + paramInt2 * 3 / 4) % paramInt2;
/*     */       case 2:
/* 103 */         return (paramInt1 + paramInt2 / 4) % paramInt2;
/*     */     } 
/* 105 */     return paramInt1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumBlockRotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */