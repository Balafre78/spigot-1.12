/*     */ package net.minecraft.server.v1_12_R1;
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
/*     */ public class ControllerMove
/*     */ {
/*     */   protected final EntityInsentient a;
/*     */   protected double b;
/*     */   protected double c;
/*     */   protected double d;
/*     */   protected double e;
/*     */   protected float f;
/*     */   protected float g;
/*  23 */   public Operation h = Operation.WAIT;
/*     */   
/*     */   public ControllerMove(EntityInsentient paramEntityInsentient) {
/*  26 */     this.a = paramEntityInsentient;
/*     */   }
/*     */   
/*     */   public boolean b() {
/*  30 */     return (this.h == Operation.MOVE_TO);
/*     */   }
/*     */   
/*     */   public double c() {
/*  34 */     return this.e;
/*     */   }
/*     */   
/*     */   public void a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/*  38 */     this.b = paramDouble1;
/*  39 */     this.c = paramDouble2;
/*  40 */     this.d = paramDouble3;
/*  41 */     this.e = paramDouble4;
/*  42 */     this.h = Operation.MOVE_TO;
/*     */   }
/*     */   
/*     */   public void a(float paramFloat1, float paramFloat2) {
/*  46 */     this.h = Operation.STRAFE;
/*  47 */     this.f = paramFloat1;
/*  48 */     this.g = paramFloat2;
/*  49 */     this.e = 0.25D;
/*     */   }
/*     */   
/*     */   public void a(ControllerMove paramControllerMove) {
/*  53 */     this.h = paramControllerMove.h;
/*  54 */     this.b = paramControllerMove.b;
/*  55 */     this.c = paramControllerMove.c;
/*  56 */     this.d = paramControllerMove.d;
/*  57 */     this.e = Math.max(paramControllerMove.e, 1.0D);
/*  58 */     this.f = paramControllerMove.f;
/*  59 */     this.g = paramControllerMove.g;
/*     */   }
/*     */   
/*     */   public void a() {
/*  63 */     if (this.h == Operation.STRAFE) {
/*     */       
/*  65 */       float f1 = (float)this.a.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue();
/*  66 */       float f2 = (float)this.e * f1;
/*     */       
/*  68 */       float f3 = this.f;
/*  69 */       float f4 = this.g;
/*  70 */       float f5 = MathHelper.c(f3 * f3 + f4 * f4);
/*  71 */       if (f5 < 1.0F) {
/*  72 */         f5 = 1.0F;
/*     */       }
/*  74 */       f5 = f2 / f5;
/*  75 */       f3 *= f5;
/*  76 */       f4 *= f5;
/*     */       
/*  78 */       float f6 = MathHelper.sin(this.a.yaw * 0.017453292F);
/*  79 */       float f7 = MathHelper.cos(this.a.yaw * 0.017453292F);
/*  80 */       float f8 = f3 * f7 - f4 * f6;
/*  81 */       float f9 = f4 * f7 + f3 * f6;
/*     */       
/*  83 */       NavigationAbstract navigationAbstract = this.a.getNavigation();
/*  84 */       if (navigationAbstract != null) {
/*  85 */         PathfinderAbstract pathfinderAbstract = navigationAbstract.r();
/*  86 */         if (pathfinderAbstract != null && pathfinderAbstract.a(this.a.world, MathHelper.floor(this.a.locX + f8), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ + f9)) != PathType.WALKABLE) {
/*  87 */           this.f = 1.0F;
/*  88 */           this.g = 0.0F;
/*  89 */           f2 = f1;
/*     */         } 
/*     */       } 
/*     */       
/*  93 */       this.a.k(f2);
/*  94 */       this.a.n(this.f);
/*  95 */       this.a.p(this.g);
/*     */       
/*  97 */       this.h = Operation.WAIT;
/*  98 */     } else if (this.h == Operation.MOVE_TO) {
/*  99 */       this.h = Operation.WAIT;
/*     */       
/* 101 */       double d1 = this.b - this.a.locX;
/* 102 */       double d2 = this.d - this.a.locZ;
/* 103 */       double d3 = this.c - this.a.locY;
/* 104 */       double d4 = d1 * d1 + d3 * d3 + d2 * d2;
/* 105 */       if (d4 < 2.500000277905201E-7D) {
/* 106 */         this.a.n(0.0F);
/*     */         
/*     */         return;
/*     */       } 
/* 110 */       float f = (float)(MathHelper.c(d2, d1) * 57.2957763671875D) - 90.0F;
/*     */       
/* 112 */       this.a.yaw = a(this.a.yaw, f, 90.0F);
/* 113 */       this.a.k((float)(this.e * this.a.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue()));
/*     */       
/* 115 */       if (d3 > this.a.P && d1 * d1 + d2 * d2 < Math.max(1.0F, this.a.width)) {
/* 116 */         this.a.getControllerJump().a();
/* 117 */         this.h = Operation.JUMPING;
/*     */       } 
/* 119 */     } else if (this.h == Operation.JUMPING) {
/* 120 */       this.a.k((float)(this.e * this.a.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue()));
/* 121 */       if (this.a.onGround) {
/* 122 */         this.h = Operation.WAIT;
/*     */       }
/*     */     } else {
/* 125 */       this.a.n(0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected float a(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 130 */     float f1 = MathHelper.g(paramFloat2 - paramFloat1);
/* 131 */     if (f1 > paramFloat3) {
/* 132 */       f1 = paramFloat3;
/*     */     }
/* 134 */     if (f1 < -paramFloat3) {
/* 135 */       f1 = -paramFloat3;
/*     */     }
/* 137 */     float f2 = paramFloat1 + f1;
/* 138 */     if (f2 < 0.0F) {
/* 139 */       f2 += 360.0F;
/* 140 */     } else if (f2 > 360.0F) {
/* 141 */       f2 -= 360.0F;
/*     */     } 
/* 143 */     return f2;
/*     */   }
/*     */   
/*     */   public double d() {
/* 147 */     return this.b;
/*     */   }
/*     */   
/*     */   public double e() {
/* 151 */     return this.c;
/*     */   }
/*     */   
/*     */   public double f() {
/* 155 */     return this.d;
/*     */   }
/*     */   
/*     */   public enum Operation {
/* 159 */     WAIT,
/* 160 */     MOVE_TO,
/* 161 */     STRAFE,
/* 162 */     JUMPING;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ControllerMove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */