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
/*     */ public class CriterionTriggerEffectsChanged
/*     */   implements CriterionTrigger<CriterionTriggerEffectsChanged.b>
/*     */ {
/*  18 */   private static final MinecraftKey a = new MinecraftKey("effects_changed");
/*  19 */   private final Map<AdvancementDataPlayer, a> b = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   public MinecraftKey a() {
/*  23 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  28 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  29 */     if (a1 == null) {
/*  30 */       a1 = new a(paramAdvancementDataPlayer);
/*  31 */       this.b.put(paramAdvancementDataPlayer, a1);
/*     */     } 
/*  33 */     a1.a(parama);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  38 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  39 */     if (a1 != null) {
/*  40 */       a1.b(parama);
/*  41 */       if (a1.a()) {
/*  42 */         this.b.remove(paramAdvancementDataPlayer);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer) {
/*  49 */     this.b.remove(paramAdvancementDataPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public b b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/*  54 */     CriterionConditionMobEffect criterionConditionMobEffect = CriterionConditionMobEffect.a(paramJsonObject.get("effects"));
/*  55 */     return new b(criterionConditionMobEffect);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer) {
/*  59 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  60 */     if (a != null)
/*  61 */       a.a(paramEntityPlayer); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionMobEffect a;
/*     */     
/*     */     public b(CriterionConditionMobEffect param1CriterionConditionMobEffect) {
/*  69 */       super(CriterionTriggerEffectsChanged.b());
/*  70 */       this.a = param1CriterionConditionMobEffect;
/*     */     }
/*     */     
/*     */     public boolean a(EntityPlayer param1EntityPlayer) {
/*  74 */       return this.a.a(param1EntityPlayer);
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  80 */     private final Set<CriterionTrigger.a<CriterionTriggerEffectsChanged.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  83 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*  87 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerEffectsChanged.b> param1a) {
/*  91 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerEffectsChanged.b> param1a) {
/*  95 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(EntityPlayer param1EntityPlayer) {
/*  99 */       ArrayList<CriterionTrigger.a<CriterionTriggerEffectsChanged.b>> arrayList = null;
/* 100 */       for (CriterionTrigger.a<CriterionTriggerEffectsChanged.b> a1 : this.b) {
/* 101 */         if (((CriterionTriggerEffectsChanged.b)a1.a()).a(param1EntityPlayer)) {
/* 102 */           if (arrayList == null) {
/* 103 */             arrayList = Lists.newArrayList();
/*     */           }
/* 105 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 108 */       if (arrayList != null)
/* 109 */         for (CriterionTrigger.a<CriterionTriggerEffectsChanged.b> a1 : arrayList)
/* 110 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerEffectsChanged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */