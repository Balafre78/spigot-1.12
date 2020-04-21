/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumHorseArmor
/*    */ {
/* 10 */   NONE(0),
/* 11 */   IRON(5, "iron", "meo"),
/* 12 */   GOLD(7, "gold", "goo"),
/* 13 */   DIAMOND(11, "diamond", "dio");
/*    */   
/*    */   private final String e;
/*    */   
/*    */   private final String f;
/*    */   
/*    */   private final int g;
/*    */   
/*    */   EnumHorseArmor(int paramInt1) {
/* 22 */     this.g = paramInt1;
/* 23 */     this.e = null;
/* 24 */     this.f = "";
/*    */   }
/*    */   
/*    */   EnumHorseArmor(int paramInt1, String paramString1, String paramString2) {
/* 28 */     this.g = paramInt1;
/* 29 */     this.e = "textures/entity/horse/armor/horse_armor_" + paramString1 + ".png";
/* 30 */     this.f = paramString2;
/*    */   }
/*    */   
/*    */   public int a() {
/* 34 */     return ordinal();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int c() {
/* 42 */     return this.g;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static EnumHorseArmor a(int paramInt) {
/* 51 */     return values()[paramInt];
/*    */   }
/*    */   
/*    */   public static EnumHorseArmor a(ItemStack paramItemStack) {
/* 55 */     return paramItemStack.isEmpty() ? NONE : a(paramItemStack.getItem());
/*    */   }
/*    */   
/*    */   public static EnumHorseArmor a(Item paramItem) {
/* 59 */     if (paramItem == Items.IRON_HORSE_ARMOR)
/* 60 */       return IRON; 
/* 61 */     if (paramItem == Items.GOLDEN_HORSE_ARMOR)
/* 62 */       return GOLD; 
/* 63 */     if (paramItem == Items.DIAMOND_HORSE_ARMOR) {
/* 64 */       return DIAMOND;
/*    */     }
/* 66 */     return NONE;
/*    */   }
/*    */   
/*    */   public static boolean b(Item paramItem) {
/* 70 */     return (a(paramItem) != NONE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumHorseArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */