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
/*     */ 
/*     */ public class CriterionTriggerKilled
/*     */   implements CriterionTrigger<CriterionTriggerKilled.b>
/*     */ {
/*  20 */   private final Map<AdvancementDataPlayer, a> a = Maps.newHashMap();
/*     */   private final MinecraftKey b;
/*     */   
/*     */   public CriterionTriggerKilled(MinecraftKey paramMinecraftKey) {
/*  24 */     this.b = paramMinecraftKey;
/*     */   }
/*     */ 
/*     */   
/*     */   public MinecraftKey a() {
/*  29 */     return this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  34 */     a a1 = this.a.get(paramAdvancementDataPlayer);
/*  35 */     if (a1 == null) {
/*  36 */       a1 = new a(paramAdvancementDataPlayer);
/*  37 */       this.a.put(paramAdvancementDataPlayer, a1);
/*     */     } 
/*  39 */     a1.a(parama);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  44 */     a a1 = this.a.get(paramAdvancementDataPlayer);
/*  45 */     if (a1 != null) {
/*  46 */       a1.b(parama);
/*  47 */       if (a1.a()) {
/*  48 */         this.a.remove(paramAdvancementDataPlayer);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer) {
/*  55 */     this.a.remove(paramAdvancementDataPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public b b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/*  60 */     return new b(this.b, CriterionConditionEntity.a(paramJsonObject.get("entity")), CriterionConditionDamageSource.a(paramJsonObject.get("killing_blow")));
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, Entity paramEntity, DamageSource paramDamageSource) {
/*  64 */     a a = this.a.get(paramEntityPlayer.getAdvancementData());
/*  65 */     if (a != null)
/*  66 */       a.a(paramEntityPlayer, paramEntity, paramDamageSource); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionEntity a;
/*     */     private final CriterionConditionDamageSource b;
/*     */     
/*     */     public b(MinecraftKey param1MinecraftKey, CriterionConditionEntity param1CriterionConditionEntity, CriterionConditionDamageSource param1CriterionConditionDamageSource) {
/*  75 */       super(param1MinecraftKey);
/*  76 */       this.a = param1CriterionConditionEntity;
/*  77 */       this.b = param1CriterionConditionDamageSource;
/*     */     }
/*     */     
/*     */     public boolean a(EntityPlayer param1EntityPlayer, Entity param1Entity, DamageSource param1DamageSource) {
/*  81 */       if (!this.b.a(param1EntityPlayer, param1DamageSource)) {
/*  82 */         return false;
/*     */       }
/*  84 */       return this.a.a(param1EntityPlayer, param1Entity);
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  90 */     private final Set<CriterionTrigger.a<CriterionTriggerKilled.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  93 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*  97 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerKilled.b> param1a) {
/* 101 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerKilled.b> param1a) {
/* 105 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(EntityPlayer param1EntityPlayer, Entity param1Entity, DamageSource param1DamageSource) {
/* 109 */       ArrayList<CriterionTrigger.a<CriterionTriggerKilled.b>> arrayList = null;
/* 110 */       for (CriterionTrigger.a<CriterionTriggerKilled.b> a1 : this.b) {
/* 111 */         if (((CriterionTriggerKilled.b)a1.a()).a(param1EntityPlayer, param1Entity, param1DamageSource)) {
/* 112 */           if (arrayList == null) {
/* 113 */             arrayList = Lists.newArrayList();
/*     */           }
/* 115 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 118 */       if (arrayList != null)
/* 119 */         for (CriterionTrigger.a<CriterionTriggerKilled.b> a1 : arrayList)
/* 120 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerKilled.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */