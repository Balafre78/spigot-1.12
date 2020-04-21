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
/*     */ public class CriterionTriggerBredAnimals
/*     */   implements CriterionTrigger<CriterionTriggerBredAnimals.b>
/*     */ {
/*  20 */   private static final MinecraftKey a = new MinecraftKey("bred_animals");
/*  21 */   private final Map<AdvancementDataPlayer, a> b = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   public MinecraftKey a() {
/*  25 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  30 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  31 */     if (a1 == null) {
/*  32 */       a1 = new a(paramAdvancementDataPlayer);
/*  33 */       this.b.put(paramAdvancementDataPlayer, a1);
/*     */     } 
/*  35 */     a1.a(parama);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  40 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  41 */     if (a1 != null) {
/*  42 */       a1.b(parama);
/*  43 */       if (a1.a()) {
/*  44 */         this.b.remove(paramAdvancementDataPlayer);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer) {
/*  51 */     this.b.remove(paramAdvancementDataPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public b b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/*  56 */     CriterionConditionEntity criterionConditionEntity1 = CriterionConditionEntity.a(paramJsonObject.get("parent"));
/*  57 */     CriterionConditionEntity criterionConditionEntity2 = CriterionConditionEntity.a(paramJsonObject.get("partner"));
/*  58 */     CriterionConditionEntity criterionConditionEntity3 = CriterionConditionEntity.a(paramJsonObject.get("child"));
/*  59 */     return new b(criterionConditionEntity1, criterionConditionEntity2, criterionConditionEntity3);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, EntityAnimal paramEntityAnimal1, EntityAnimal paramEntityAnimal2, EntityAgeable paramEntityAgeable) {
/*  63 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  64 */     if (a != null)
/*  65 */       a.a(paramEntityPlayer, paramEntityAnimal1, paramEntityAnimal2, paramEntityAgeable); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionEntity a;
/*     */     private final CriterionConditionEntity b;
/*     */     private final CriterionConditionEntity c;
/*     */     
/*     */     public b(CriterionConditionEntity param1CriterionConditionEntity1, CriterionConditionEntity param1CriterionConditionEntity2, CriterionConditionEntity param1CriterionConditionEntity3) {
/*  75 */       super(CriterionTriggerBredAnimals.b());
/*  76 */       this.a = param1CriterionConditionEntity1;
/*  77 */       this.b = param1CriterionConditionEntity2;
/*  78 */       this.c = param1CriterionConditionEntity3;
/*     */     }
/*     */     
/*     */     public boolean a(EntityPlayer param1EntityPlayer, EntityAnimal param1EntityAnimal1, EntityAnimal param1EntityAnimal2, EntityAgeable param1EntityAgeable) {
/*  82 */       if (!this.c.a(param1EntityPlayer, param1EntityAgeable)) {
/*  83 */         return false;
/*     */       }
/*     */       
/*  86 */       return ((this.a.a(param1EntityPlayer, param1EntityAnimal1) && this.b.a(param1EntityPlayer, param1EntityAnimal2)) || (this.a.a(param1EntityPlayer, param1EntityAnimal2) && this.b.a(param1EntityPlayer, param1EntityAnimal1)));
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  92 */     private final Set<CriterionTrigger.a<CriterionTriggerBredAnimals.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  95 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*  99 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerBredAnimals.b> param1a) {
/* 103 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerBredAnimals.b> param1a) {
/* 107 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(EntityPlayer param1EntityPlayer, EntityAnimal param1EntityAnimal1, EntityAnimal param1EntityAnimal2, EntityAgeable param1EntityAgeable) {
/* 111 */       ArrayList<CriterionTrigger.a<CriterionTriggerBredAnimals.b>> arrayList = null;
/* 112 */       for (CriterionTrigger.a<CriterionTriggerBredAnimals.b> a1 : this.b) {
/* 113 */         if (((CriterionTriggerBredAnimals.b)a1.a()).a(param1EntityPlayer, param1EntityAnimal1, param1EntityAnimal2, param1EntityAgeable)) {
/* 114 */           if (arrayList == null) {
/* 115 */             arrayList = Lists.newArrayList();
/*     */           }
/* 117 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 120 */       if (arrayList != null)
/* 121 */         for (CriterionTrigger.a<CriterionTriggerBredAnimals.b> a1 : arrayList)
/* 122 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerBredAnimals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */