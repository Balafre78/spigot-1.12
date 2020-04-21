/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ public abstract class BossBattle
/*     */ {
/*     */   private final UUID h;
/*     */   public IChatBaseComponent title;
/*     */   protected float b;
/*     */   public BarColor color;
/*     */   public BarStyle style;
/*     */   protected boolean e;
/*     */   protected boolean f;
/*     */   protected boolean g;
/*     */   
/*     */   public BossBattle(UUID paramUUID, IChatBaseComponent paramIChatBaseComponent, BarColor paramBarColor, BarStyle paramBarStyle) {
/*  18 */     this.h = paramUUID;
/*  19 */     this.title = paramIChatBaseComponent;
/*  20 */     this.color = paramBarColor;
/*  21 */     this.style = paramBarStyle;
/*  22 */     this.b = 1.0F;
/*     */   }
/*     */   
/*     */   public UUID d() {
/*  26 */     return this.h;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent e() {
/*  30 */     return this.title;
/*     */   }
/*     */   
/*     */   public void a(IChatBaseComponent paramIChatBaseComponent) {
/*  34 */     this.title = paramIChatBaseComponent;
/*     */   }
/*     */   
/*     */   public float getProgress() {
/*  38 */     return this.b;
/*     */   }
/*     */   
/*     */   public void a(float paramFloat) {
/*  42 */     this.b = paramFloat;
/*     */   }
/*     */   
/*     */   public BarColor g() {
/*  46 */     return this.color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BarStyle h() {
/*  54 */     return this.style;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean i() {
/*  62 */     return this.e;
/*     */   }
/*     */   
/*     */   public BossBattle a(boolean paramBoolean) {
/*  66 */     this.e = paramBoolean;
/*  67 */     return this;
/*     */   }
/*     */   
/*     */   public boolean j() {
/*  71 */     return this.f;
/*     */   }
/*     */   
/*     */   public BossBattle b(boolean paramBoolean) {
/*  75 */     this.f = paramBoolean;
/*  76 */     return this;
/*     */   }
/*     */   
/*     */   public BossBattle c(boolean paramBoolean) {
/*  80 */     this.g = paramBoolean;
/*  81 */     return this;
/*     */   }
/*     */   
/*     */   public boolean k() {
/*  85 */     return this.g;
/*     */   }
/*     */   
/*     */   public enum BarColor {
/*  89 */     PINK,
/*  90 */     BLUE,
/*  91 */     RED,
/*  92 */     GREEN,
/*  93 */     YELLOW,
/*  94 */     PURPLE,
/*  95 */     WHITE;
/*     */   }
/*     */   
/*     */   public enum BarStyle
/*     */   {
/* 100 */     PROGRESS,
/* 101 */     NOTCHED_6,
/* 102 */     NOTCHED_10,
/* 103 */     NOTCHED_12,
/* 104 */     NOTCHED_20;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BossBattle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */