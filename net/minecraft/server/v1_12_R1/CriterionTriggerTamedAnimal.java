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
/*     */ public class CriterionTriggerTamedAnimal
/*     */   implements CriterionTrigger<CriterionTriggerTamedAnimal.b>
/*     */ {
/*  19 */   private static final MinecraftKey a = new MinecraftKey("tame_animal");
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
/*  55 */     CriterionConditionEntity criterionConditionEntity = CriterionConditionEntity.a(paramJsonObject.get("entity"));
/*  56 */     return new b(criterionConditionEntity);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, EntityAnimal paramEntityAnimal) {
/*  60 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  61 */     if (a != null)
/*  62 */       a.a(paramEntityPlayer, paramEntityAnimal); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionEntity a;
/*     */     
/*     */     public b(CriterionConditionEntity param1CriterionConditionEntity) {
/*  70 */       super(CriterionTriggerTamedAnimal.b());
/*  71 */       this.a = param1CriterionConditionEntity;
/*     */     }
/*     */     
/*     */     public boolean a(EntityPlayer param1EntityPlayer, EntityAnimal param1EntityAnimal) {
/*  75 */       return this.a.a(param1EntityPlayer, param1EntityAnimal);
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  81 */     private final Set<CriterionTrigger.a<CriterionTriggerTamedAnimal.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  84 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*  88 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerTamedAnimal.b> param1a) {
/*  92 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerTamedAnimal.b> param1a) {
/*  96 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(EntityPlayer param1EntityPlayer, EntityAnimal param1EntityAnimal) {
/* 100 */       ArrayList<CriterionTrigger.a<CriterionTriggerTamedAnimal.b>> arrayList = null;
/* 101 */       for (CriterionTrigger.a<CriterionTriggerTamedAnimal.b> a1 : this.b) {
/* 102 */         if (((CriterionTriggerTamedAnimal.b)a1.a()).a(param1EntityPlayer, param1EntityAnimal)) {
/* 103 */           if (arrayList == null) {
/* 104 */             arrayList = Lists.newArrayList();
/*     */           }
/* 106 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 109 */       if (arrayList != null)
/* 110 */         for (CriterionTrigger.a<CriterionTriggerTamedAnimal.b> a1 : arrayList)
/* 111 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerTamedAnimal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */