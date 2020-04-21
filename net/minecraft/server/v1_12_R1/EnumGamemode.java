/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public enum EnumGamemode
/*    */ {
/*  6 */   NOT_SET(-1, "", ""),
/*  7 */   SURVIVAL(0, "survival", "s"),
/*  8 */   CREATIVE(1, "creative", "c"),
/*  9 */   ADVENTURE(2, "adventure", "a"),
/* 10 */   SPECTATOR(3, "spectator", "sp");
/*    */   
/*    */   int f;
/*    */   
/*    */   String g;
/*    */   String h;
/*    */   
/*    */   EnumGamemode(int paramInt1, String paramString1, String paramString2) {
/* 18 */     this.f = paramInt1;
/* 19 */     this.g = paramString1;
/* 20 */     this.h = paramString2;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 24 */     return this.f;
/*    */   }
/*    */   
/*    */   public String b() {
/* 28 */     return this.g;
/*    */   }
/*    */   
/*    */   public void a(PlayerAbilities paramPlayerAbilities) {
/* 32 */     if (this == CREATIVE) {
/* 33 */       paramPlayerAbilities.canFly = true;
/* 34 */       paramPlayerAbilities.canInstantlyBuild = true;
/* 35 */       paramPlayerAbilities.isInvulnerable = true;
/* 36 */     } else if (this == SPECTATOR) {
/* 37 */       paramPlayerAbilities.canFly = true;
/* 38 */       paramPlayerAbilities.canInstantlyBuild = false;
/* 39 */       paramPlayerAbilities.isInvulnerable = true;
/* 40 */       paramPlayerAbilities.isFlying = true;
/*    */     } else {
/* 42 */       paramPlayerAbilities.canFly = false;
/* 43 */       paramPlayerAbilities.canInstantlyBuild = false;
/* 44 */       paramPlayerAbilities.isInvulnerable = false;
/* 45 */       paramPlayerAbilities.isFlying = false;
/*    */     } 
/* 47 */     paramPlayerAbilities.mayBuild = !c();
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 51 */     return (this == ADVENTURE || this == SPECTATOR);
/*    */   }
/*    */   
/*    */   public boolean isCreative() {
/* 55 */     return (this == CREATIVE);
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 59 */     return (this == SURVIVAL || this == ADVENTURE);
/*    */   }
/*    */   
/*    */   public static EnumGamemode getById(int paramInt) {
/* 63 */     return a(paramInt, SURVIVAL);
/*    */   }
/*    */   
/*    */   public static EnumGamemode a(int paramInt, EnumGamemode paramEnumGamemode) {
/* 67 */     for (EnumGamemode enumGamemode : values()) {
/* 68 */       if (enumGamemode.f == paramInt) {
/* 69 */         return enumGamemode;
/*    */       }
/*    */     } 
/* 72 */     return paramEnumGamemode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static EnumGamemode a(String paramString, EnumGamemode paramEnumGamemode) {
/* 80 */     for (EnumGamemode enumGamemode : values()) {
/* 81 */       if (enumGamemode.g.equals(paramString) || enumGamemode.h.equals(paramString)) {
/* 82 */         return enumGamemode;
/*    */       }
/*    */     } 
/* 85 */     return paramEnumGamemode;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumGamemode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */