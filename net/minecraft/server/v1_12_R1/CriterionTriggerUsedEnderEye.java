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
/*     */ public class CriterionTriggerUsedEnderEye
/*     */   implements CriterionTrigger<CriterionTriggerUsedEnderEye.b>
/*     */ {
/*  19 */   private static final MinecraftKey a = new MinecraftKey("used_ender_eye");
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
/*  55 */     CriterionConditionValue criterionConditionValue = CriterionConditionValue.a(paramJsonObject.get("distance"));
/*  56 */     return new b(criterionConditionValue);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, BlockPosition paramBlockPosition) {
/*  60 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  61 */     if (a != null) {
/*  62 */       double d1 = paramEntityPlayer.locX - paramBlockPosition.getX();
/*  63 */       double d2 = paramEntityPlayer.locZ - paramBlockPosition.getZ();
/*  64 */       a.a(d1 * d1 + d2 * d2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class b extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionValue a;
/*     */     
/*     */     public b(CriterionConditionValue param1CriterionConditionValue) {
/*  72 */       super(CriterionTriggerUsedEnderEye.b());
/*  73 */       this.a = param1CriterionConditionValue;
/*     */     }
/*     */     
/*     */     public boolean a(double param1Double) {
/*  77 */       return this.a.a(param1Double);
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  83 */     private final Set<CriterionTrigger.a<CriterionTriggerUsedEnderEye.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  86 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*  90 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerUsedEnderEye.b> param1a) {
/*  94 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerUsedEnderEye.b> param1a) {
/*  98 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(double param1Double) {
/* 102 */       ArrayList<CriterionTrigger.a<CriterionTriggerUsedEnderEye.b>> arrayList = null;
/* 103 */       for (CriterionTrigger.a<CriterionTriggerUsedEnderEye.b> a1 : this.b) {
/* 104 */         if (((CriterionTriggerUsedEnderEye.b)a1.a()).a(param1Double)) {
/* 105 */           if (arrayList == null) {
/* 106 */             arrayList = Lists.newArrayList();
/*     */           }
/* 108 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 111 */       if (arrayList != null)
/* 112 */         for (CriterionTrigger.a<CriterionTriggerUsedEnderEye.b> a1 : arrayList)
/* 113 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerUsedEnderEye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */