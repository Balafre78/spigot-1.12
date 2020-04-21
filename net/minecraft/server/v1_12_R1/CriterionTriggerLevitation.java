/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionTriggerLevitation
/*     */   implements CriterionTrigger<CriterionTriggerLevitation.b>
/*     */ {
/*  19 */   private static final MinecraftKey a = new MinecraftKey("levitation");
/*  20 */   private final Map<AdvancementDataPlayer, a> b = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   public MinecraftKey a() {
/*  24 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  29 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  30 */     if (a1 == null) {
/*  31 */       a1 = new a(paramAdvancementDataPlayer);
/*  32 */       this.b.put(paramAdvancementDataPlayer, a1);
/*     */     } 
/*  34 */     a1.a(parama);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  39 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  40 */     if (a1 != null) {
/*  41 */       a1.b(parama);
/*  42 */       if (a1.a()) {
/*  43 */         this.b.remove(paramAdvancementDataPlayer);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer) {
/*  50 */     this.b.remove(paramAdvancementDataPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public b b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/*  55 */     CriterionConditionDistance criterionConditionDistance = CriterionConditionDistance.a(paramJsonObject.get("distance"));
/*  56 */     CriterionConditionValue criterionConditionValue = CriterionConditionValue.a(paramJsonObject.get("duration"));
/*  57 */     return new b(criterionConditionDistance, criterionConditionValue);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, Vec3D paramVec3D, int paramInt) {
/*  61 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  62 */     if (a != null)
/*  63 */       a.a(paramEntityPlayer, paramVec3D, paramInt); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionDistance a;
/*     */     private final CriterionConditionValue b;
/*     */     
/*     */     public b(CriterionConditionDistance param1CriterionConditionDistance, CriterionConditionValue param1CriterionConditionValue) {
/*  72 */       super(CriterionTriggerLevitation.b());
/*  73 */       this.a = param1CriterionConditionDistance;
/*  74 */       this.b = param1CriterionConditionValue;
/*     */     }
/*     */     
/*     */     public boolean a(EntityPlayer param1EntityPlayer, Vec3D param1Vec3D, int param1Int) {
/*  78 */       if (!this.a.a(param1Vec3D.x, param1Vec3D.y, param1Vec3D.z, param1EntityPlayer.locX, param1EntityPlayer.locY, param1EntityPlayer.locZ)) {
/*  79 */         return false;
/*     */       }
/*  81 */       if (!this.b.a(param1Int)) {
/*  82 */         return false;
/*     */       }
/*  84 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  90 */     private final Set<CriterionTrigger.a<CriterionTriggerLevitation.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  93 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*  97 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerLevitation.b> param1a) {
/* 101 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerLevitation.b> param1a) {
/* 105 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(EntityPlayer param1EntityPlayer, Vec3D param1Vec3D, int param1Int) {
/* 109 */       ArrayList<CriterionTrigger.a<CriterionTriggerLevitation.b>> arrayList = null;
/* 110 */       for (CriterionTrigger.a<CriterionTriggerLevitation.b> a1 : this.b) {
/* 111 */         if (((CriterionTriggerLevitation.b)a1.a()).a(param1EntityPlayer, param1Vec3D, param1Int)) {
/* 112 */           if (arrayList == null) {
/* 113 */             arrayList = Lists.newArrayList();
/*     */           }
/* 115 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 118 */       if (arrayList != null)
/* 119 */         for (CriterionTrigger.a<CriterionTriggerLevitation.b> a1 : arrayList)
/* 120 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerLevitation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */