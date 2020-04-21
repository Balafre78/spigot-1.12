/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public enum EnumItemSlot {
/*  4 */   MAINHAND(Function.HAND, 0, 0, "mainhand"),
/*  5 */   OFFHAND(Function.HAND, 1, 5, "offhand"),
/*  6 */   FEET(Function.ARMOR, 0, 1, "feet"),
/*  7 */   LEGS(Function.ARMOR, 1, 2, "legs"),
/*  8 */   CHEST(Function.ARMOR, 2, 3, "chest"),
/*  9 */   HEAD(Function.ARMOR, 3, 4, "head");
/*    */   
/*    */   private final Function g;
/*    */   private final int h;
/*    */   private final int i;
/*    */   private final String j;
/*    */   
/*    */   EnumItemSlot(Function paramFunction, int paramInt1, int paramInt2, String paramString1) {
/* 17 */     this.g = paramFunction;
/* 18 */     this.h = paramInt1;
/* 19 */     this.i = paramInt2;
/* 20 */     this.j = paramString1;
/*    */   }
/*    */   
/*    */   public Function a() {
/* 24 */     return this.g;
/*    */   }
/*    */   
/*    */   public int b() {
/* 28 */     return this.h;
/*    */   }
/*    */   
/*    */   public int c() {
/* 32 */     return this.i;
/*    */   }
/*    */   
/*    */   public String d() {
/* 36 */     return this.j;
/*    */   }
/*    */   
/*    */   public enum Function {
/* 40 */     HAND,
/* 41 */     ARMOR;
/*    */   }
/*    */   
/*    */   public static EnumItemSlot a(String paramString) {
/* 45 */     for (EnumItemSlot enumItemSlot : values()) {
/* 46 */       if (enumItemSlot.d().equals(paramString)) {
/* 47 */         return enumItemSlot;
/*    */       }
/*    */     } 
/*    */     
/* 51 */     throw new IllegalArgumentException("Invalid slot '" + paramString + "'");
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumItemSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */