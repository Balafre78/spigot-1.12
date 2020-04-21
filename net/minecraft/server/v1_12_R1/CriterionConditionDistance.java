/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionConditionDistance
/*    */ {
/* 11 */   public static final CriterionConditionDistance a = new CriterionConditionDistance(CriterionConditionValue.a, CriterionConditionValue.a, CriterionConditionValue.a, CriterionConditionValue.a, CriterionConditionValue.a);
/*    */   
/*    */   private final CriterionConditionValue b;
/*    */   private final CriterionConditionValue c;
/*    */   private final CriterionConditionValue d;
/*    */   private final CriterionConditionValue e;
/*    */   private final CriterionConditionValue f;
/*    */   
/*    */   public CriterionConditionDistance(CriterionConditionValue paramCriterionConditionValue1, CriterionConditionValue paramCriterionConditionValue2, CriterionConditionValue paramCriterionConditionValue3, CriterionConditionValue paramCriterionConditionValue4, CriterionConditionValue paramCriterionConditionValue5) {
/* 20 */     this.b = paramCriterionConditionValue1;
/* 21 */     this.c = paramCriterionConditionValue2;
/* 22 */     this.d = paramCriterionConditionValue3;
/* 23 */     this.e = paramCriterionConditionValue4;
/* 24 */     this.f = paramCriterionConditionValue5;
/*    */   }
/*    */   
/*    */   public boolean a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6) {
/* 28 */     float f1 = (float)(paramDouble1 - paramDouble4);
/* 29 */     float f2 = (float)(paramDouble2 - paramDouble5);
/* 30 */     float f3 = (float)(paramDouble3 - paramDouble6);
/* 31 */     if (!this.b.a(MathHelper.e(f1)) || !this.c.a(MathHelper.e(f2)) || !this.d.a(MathHelper.e(f3))) {
/* 32 */       return false;
/*    */     }
/* 34 */     if (!this.e.a((f1 * f1 + f3 * f3))) {
/* 35 */       return false;
/*    */     }
/* 37 */     if (!this.f.a((f1 * f1 + f2 * f2 + f3 * f3))) {
/* 38 */       return false;
/*    */     }
/* 40 */     return true;
/*    */   }
/*    */   
/*    */   public static CriterionConditionDistance a(@Nullable JsonElement paramJsonElement) {
/* 44 */     if (paramJsonElement == null || paramJsonElement.isJsonNull()) {
/* 45 */       return a;
/*    */     }
/* 47 */     JsonObject jsonObject = ChatDeserializer.m(paramJsonElement, "distance");
/* 48 */     CriterionConditionValue criterionConditionValue1 = CriterionConditionValue.a(jsonObject.get("x"));
/* 49 */     CriterionConditionValue criterionConditionValue2 = CriterionConditionValue.a(jsonObject.get("y"));
/* 50 */     CriterionConditionValue criterionConditionValue3 = CriterionConditionValue.a(jsonObject.get("z"));
/* 51 */     CriterionConditionValue criterionConditionValue4 = CriterionConditionValue.a(jsonObject.get("horizontal"));
/* 52 */     CriterionConditionValue criterionConditionValue5 = CriterionConditionValue.a(jsonObject.get("absolute"));
/* 53 */     return new CriterionConditionDistance(criterionConditionValue1, criterionConditionValue2, criterionConditionValue3, criterionConditionValue4, criterionConditionValue5);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionConditionDistance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */