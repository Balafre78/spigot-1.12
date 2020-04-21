/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class AttributeRanged
/*    */   extends AttributeBase {
/*    */   private final double a;
/*    */   public double b;
/*    */   private String c;
/*    */   
/*    */   public AttributeRanged(@Nullable IAttribute iattribute, String s, double d0, double d1, double d2) {
/* 12 */     super(iattribute, s, d0);
/* 13 */     this.a = d1;
/* 14 */     this.b = d2;
/* 15 */     if (d1 > d2)
/* 16 */       throw new IllegalArgumentException("Minimum value cannot be bigger than maximum value!"); 
/* 17 */     if (d0 < d1)
/* 18 */       throw new IllegalArgumentException("Default value cannot be lower than minimum value!"); 
/* 19 */     if (d0 > d2) {
/* 20 */       throw new IllegalArgumentException("Default value cannot be bigger than maximum value!");
/*    */     }
/*    */   }
/*    */   
/*    */   public AttributeRanged a(String s) {
/* 25 */     this.c = s;
/* 26 */     return this;
/*    */   }
/*    */   
/*    */   public String g() {
/* 30 */     return this.c;
/*    */   }
/*    */   
/*    */   public double a(double d0) {
/* 34 */     d0 = MathHelper.a(d0, this.a, this.b);
/* 35 */     return d0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AttributeRanged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */