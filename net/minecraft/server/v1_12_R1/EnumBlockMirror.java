/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public enum EnumBlockMirror
/*    */ {
/*  6 */   NONE("no_mirror"),
/*  7 */   LEFT_RIGHT("mirror_left_right"),
/*  8 */   FRONT_BACK("mirror_front_back");
/*    */   private final String d;
/*    */   private static final String[] e;
/*    */   
/*    */   static {
/* 13 */     e = new String[(values()).length];
/*    */ 
/*    */     
/* 16 */     byte b = 0;
/* 17 */     for (EnumBlockMirror enumBlockMirror : values()) {
/* 18 */       e[b++] = enumBlockMirror.d;
/*    */     }
/*    */   }
/*    */   
/*    */   EnumBlockMirror(String paramString1) {
/* 23 */     this.d = paramString1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(int paramInt1, int paramInt2) {
/* 40 */     int i = paramInt2 / 2;
/* 41 */     int j = (paramInt1 > i) ? (paramInt1 - paramInt2) : paramInt1;
/* 42 */     switch (null.a[ordinal()]) {
/*    */       case 1:
/* 44 */         return (paramInt2 - j) % paramInt2;
/*    */       case 2:
/* 46 */         return (i - j + paramInt2) % paramInt2;
/*    */     } 
/* 48 */     return paramInt1;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumBlockRotation a(EnumDirection paramEnumDirection) {
/* 53 */     EnumDirection.EnumAxis enumAxis = paramEnumDirection.k();
/* 54 */     return ((this == LEFT_RIGHT && enumAxis == EnumDirection.EnumAxis.Z) || (this == FRONT_BACK && enumAxis == EnumDirection.EnumAxis.X)) ? EnumBlockRotation.CLOCKWISE_180 : EnumBlockRotation.NONE;
/*    */   }
/*    */   
/*    */   public EnumDirection b(EnumDirection paramEnumDirection) {
/* 58 */     switch (null.a[ordinal()]) {
/*    */       case 1:
/* 60 */         if (paramEnumDirection == EnumDirection.WEST) {
/* 61 */           return EnumDirection.EAST;
/*    */         }
/* 63 */         if (paramEnumDirection == EnumDirection.EAST) {
/* 64 */           return EnumDirection.WEST;
/*    */         }
/* 66 */         return paramEnumDirection;
/*    */       case 2:
/* 68 */         if (paramEnumDirection == EnumDirection.NORTH) {
/* 69 */           return EnumDirection.SOUTH;
/*    */         }
/* 71 */         if (paramEnumDirection == EnumDirection.SOUTH) {
/* 72 */           return EnumDirection.NORTH;
/*    */         }
/* 74 */         return paramEnumDirection;
/*    */     } 
/* 76 */     return paramEnumDirection;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumBlockMirror.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */