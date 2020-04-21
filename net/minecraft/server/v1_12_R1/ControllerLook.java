/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ public class ControllerLook
/*     */ {
/*     */   private final EntityInsentient a;
/*     */   private float b;
/*     */   private float c;
/*     */   private boolean d;
/*     */   private double e;
/*     */   private double f;
/*     */   private double g;
/*     */   
/*     */   public ControllerLook(EntityInsentient paramEntityInsentient) {
/*  15 */     this.a = paramEntityInsentient;
/*     */   }
/*     */   
/*     */   public void a(Entity paramEntity, float paramFloat1, float paramFloat2) {
/*  19 */     this.e = paramEntity.locX;
/*  20 */     if (paramEntity instanceof EntityLiving) {
/*  21 */       this.f = paramEntity.locY + paramEntity.getHeadHeight();
/*     */     } else {
/*  23 */       this.f = ((paramEntity.getBoundingBox()).b + (paramEntity.getBoundingBox()).e) / 2.0D;
/*     */     } 
/*  25 */     this.g = paramEntity.locZ;
/*  26 */     this.b = paramFloat1;
/*  27 */     this.c = paramFloat2;
/*  28 */     this.d = true;
/*     */   }
/*     */   
/*     */   public void a(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
/*  32 */     this.e = paramDouble1;
/*  33 */     this.f = paramDouble2;
/*  34 */     this.g = paramDouble3;
/*  35 */     this.b = paramFloat1;
/*  36 */     this.c = paramFloat2;
/*  37 */     this.d = true;
/*     */   }
/*     */   
/*     */   public void a() {
/*  41 */     this.a.pitch = 0.0F;
/*     */     
/*  43 */     if (this.d) {
/*  44 */       this.d = false;
/*     */       
/*  46 */       double d1 = this.e - this.a.locX;
/*  47 */       double d2 = this.f - this.a.locY + this.a.getHeadHeight();
/*  48 */       double d3 = this.g - this.a.locZ;
/*  49 */       double d4 = MathHelper.sqrt(d1 * d1 + d3 * d3);
/*     */       
/*  51 */       float f1 = (float)(MathHelper.c(d3, d1) * 57.2957763671875D) - 90.0F;
/*  52 */       float f2 = (float)-(MathHelper.c(d2, d4) * 57.2957763671875D);
/*  53 */       this.a.pitch = a(this.a.pitch, f2, this.c);
/*  54 */       this.a.aP = a(this.a.aP, f1, this.b);
/*     */     } else {
/*  56 */       this.a.aP = a(this.a.aP, this.a.aN, 10.0F);
/*     */     } 
/*     */     
/*  59 */     float f = MathHelper.g(this.a.aP - this.a.aN);
/*     */     
/*  61 */     if (!this.a.getNavigation().o()) {
/*     */       
/*  63 */       if (f < -75.0F) {
/*  64 */         this.a.aP = this.a.aN - 75.0F;
/*     */       }
/*  66 */       if (f > 75.0F) {
/*  67 */         this.a.aP = this.a.aN + 75.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private float a(float paramFloat1, float paramFloat2, float paramFloat3) {
/*  73 */     float f = MathHelper.g(paramFloat2 - paramFloat1);
/*  74 */     if (f > paramFloat3) {
/*  75 */       f = paramFloat3;
/*     */     }
/*  77 */     if (f < -paramFloat3) {
/*  78 */       f = -paramFloat3;
/*     */     }
/*  80 */     return paramFloat1 + f;
/*     */   }
/*     */   
/*     */   public boolean b() {
/*  84 */     return this.d;
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
/*     */   public double e() {
/*  96 */     return this.e;
/*     */   }
/*     */   
/*     */   public double f() {
/* 100 */     return this.f;
/*     */   }
/*     */   
/*     */   public double g() {
/* 104 */     return this.g;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ControllerLook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */