/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionTriggerLocation
/*     */   implements CriterionTrigger<CriterionTriggerLocation.b>
/*     */ {
/*     */   private final MinecraftKey a;
/*  20 */   private final Map<AdvancementDataPlayer, a> b = Maps.newHashMap();
/*     */   
/*     */   public CriterionTriggerLocation(MinecraftKey paramMinecraftKey) {
/*  23 */     this.a = paramMinecraftKey;
/*     */   }
/*     */ 
/*     */   
/*     */   public MinecraftKey a() {
/*  28 */     return this.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  33 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  34 */     if (a1 == null) {
/*  35 */       a1 = new a(paramAdvancementDataPlayer);
/*  36 */       this.b.put(paramAdvancementDataPlayer, a1);
/*     */     } 
/*  38 */     a1.a(parama);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  43 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  44 */     if (a1 != null) {
/*  45 */       a1.b(parama);
/*  46 */       if (a1.a()) {
/*  47 */         this.b.remove(paramAdvancementDataPlayer);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer) {
/*  54 */     this.b.remove(paramAdvancementDataPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public b b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/*  59 */     CriterionConditionLocation criterionConditionLocation = CriterionConditionLocation.a((JsonElement)paramJsonObject);
/*  60 */     return new b(this.a, criterionConditionLocation);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer) {
/*  64 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  65 */     if (a != null)
/*  66 */       a.a(paramEntityPlayer.x(), paramEntityPlayer.locX, paramEntityPlayer.locY, paramEntityPlayer.locZ); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionLocation a;
/*     */     
/*     */     public b(MinecraftKey param1MinecraftKey, CriterionConditionLocation param1CriterionConditionLocation) {
/*  74 */       super(param1MinecraftKey);
/*  75 */       this.a = param1CriterionConditionLocation;
/*     */     }
/*     */     
/*     */     public boolean a(WorldServer param1WorldServer, double param1Double1, double param1Double2, double param1Double3) {
/*  79 */       return this.a.a(param1WorldServer, param1Double1, param1Double2, param1Double3);
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  85 */     private final Set<CriterionTrigger.a<CriterionTriggerLocation.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  88 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*  92 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerLocation.b> param1a) {
/*  96 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerLocation.b> param1a) {
/* 100 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(WorldServer param1WorldServer, double param1Double1, double param1Double2, double param1Double3) {
/* 104 */       ArrayList<CriterionTrigger.a<CriterionTriggerLocation.b>> arrayList = null;
/* 105 */       for (CriterionTrigger.a<CriterionTriggerLocation.b> a1 : this.b) {
/* 106 */         if (((CriterionTriggerLocation.b)a1.a()).a(param1WorldServer, param1Double1, param1Double2, param1Double3)) {
/* 107 */           if (arrayList == null) {
/* 108 */             arrayList = Lists.newArrayList();
/*     */           }
/* 110 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 113 */       if (arrayList != null)
/* 114 */         for (CriterionTrigger.a<CriterionTriggerLocation.b> a1 : arrayList)
/* 115 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */