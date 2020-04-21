/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public enum EnumItemRarity
/*    */ {
/*  6 */   COMMON(EnumChatFormat.WHITE, "Common"),
/*  7 */   UNCOMMON(EnumChatFormat.YELLOW, "Uncommon"),
/*  8 */   RARE(EnumChatFormat.AQUA, "Rare"),
/*  9 */   EPIC(EnumChatFormat.LIGHT_PURPLE, "Epic");
/*    */   
/*    */   public final EnumChatFormat e;
/*    */   public final String f;
/*    */   
/*    */   EnumItemRarity(EnumChatFormat paramEnumChatFormat, String paramString1) {
/* 15 */     this.e = paramEnumChatFormat;
/* 16 */     this.f = paramString1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumItemRarity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */