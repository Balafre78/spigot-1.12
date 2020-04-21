/*     */ package net.minecraft.server.v1_12_R1;public class MapIcon { private final Type type;
/*     */   private byte x;
/*     */   private byte y;
/*     */   private byte rotation;
/*     */   
/*     */   public enum Type {
/*   7 */     PLAYER(false),
/*   8 */     FRAME(true),
/*   9 */     RED_MARKER(false),
/*  10 */     BLUE_MARKER(false),
/*  11 */     TARGET_X(true),
/*  12 */     TARGET_POINT(true),
/*  13 */     PLAYER_OFF_MAP(false),
/*  14 */     PLAYER_OFF_LIMITS(false),
/*  15 */     MANSION(true, 5393476),
/*  16 */     MONUMENT(true, 3830373);
/*     */ 
/*     */     
/*     */     private final byte k;
/*     */ 
/*     */     
/*     */     private final boolean l;
/*     */     
/*     */     private final int m;
/*     */ 
/*     */     
/*     */     Type(boolean param1Boolean, int param1Int1) {
/*  28 */       this.k = (byte)ordinal();
/*  29 */       this.l = param1Boolean;
/*  30 */       this.m = param1Int1;
/*     */     }
/*     */     
/*     */     public byte a() {
/*  34 */       return this.k;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean c() {
/*  42 */       return (this.m >= 0);
/*     */     }
/*     */     
/*     */     public int d() {
/*  46 */       return this.m;
/*     */     }
/*     */     
/*     */     public static Type a(byte param1Byte) {
/*  50 */       return values()[MathHelper.clamp(param1Byte, 0, (values()).length - 1)];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MapIcon(Type paramType, byte paramByte1, byte paramByte2, byte paramByte3) {
/*  60 */     this.type = paramType;
/*  61 */     this.x = paramByte1;
/*  62 */     this.y = paramByte2;
/*  63 */     this.rotation = paramByte3;
/*     */   }
/*     */   
/*     */   public byte getType() {
/*  67 */     return this.type.a();
/*     */   }
/*     */   
/*     */   public Type b() {
/*  71 */     return this.type;
/*     */   }
/*     */   
/*     */   public byte getX() {
/*  75 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getY() {
/*  83 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getRotation() {
/*  91 */     return this.rotation;
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
/*     */   public boolean equals(Object paramObject) {
/* 104 */     if (this == paramObject) {
/* 105 */       return true;
/*     */     }
/* 107 */     if (!(paramObject instanceof MapIcon)) {
/* 108 */       return false;
/*     */     }
/*     */     
/* 111 */     MapIcon mapIcon = (MapIcon)paramObject;
/*     */     
/* 113 */     if (this.type != mapIcon.type) {
/* 114 */       return false;
/*     */     }
/* 116 */     if (this.rotation != mapIcon.rotation) {
/* 117 */       return false;
/*     */     }
/* 119 */     if (this.x != mapIcon.x) {
/* 120 */       return false;
/*     */     }
/* 122 */     if (this.y != mapIcon.y) {
/* 123 */       return false;
/*     */     }
/*     */     
/* 126 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 131 */     byte b = this.type.a();
/* 132 */     int i = 31 * b + this.x;
/* 133 */     i = 31 * i + this.y;
/* 134 */     i = 31 * i + this.rotation;
/* 135 */     return i;
/*     */   } }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MapIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */