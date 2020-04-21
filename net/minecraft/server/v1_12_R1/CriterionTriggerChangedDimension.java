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
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionTriggerChangedDimension
/*     */   implements CriterionTrigger<CriterionTriggerChangedDimension.b>
/*     */ {
/*  21 */   private static final MinecraftKey a = new MinecraftKey("changed_dimension");
/*  22 */   private final Map<AdvancementDataPlayer, a> b = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   public MinecraftKey a() {
/*  26 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  31 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  32 */     if (a1 == null) {
/*  33 */       a1 = new a(paramAdvancementDataPlayer);
/*  34 */       this.b.put(paramAdvancementDataPlayer, a1);
/*     */     } 
/*  36 */     a1.a(parama);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  41 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  42 */     if (a1 != null) {
/*  43 */       a1.b(parama);
/*  44 */       if (a1.a()) {
/*  45 */         this.b.remove(paramAdvancementDataPlayer);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer) {
/*  52 */     this.b.remove(paramAdvancementDataPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public b b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/*  57 */     DimensionManager dimensionManager1 = paramJsonObject.has("from") ? DimensionManager.a(ChatDeserializer.h(paramJsonObject, "from")) : null;
/*  58 */     DimensionManager dimensionManager2 = paramJsonObject.has("to") ? DimensionManager.a(ChatDeserializer.h(paramJsonObject, "to")) : null;
/*  59 */     return new b(dimensionManager1, dimensionManager2);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, DimensionManager paramDimensionManager1, DimensionManager paramDimensionManager2) {
/*  63 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  64 */     if (a != null)
/*  65 */       a.a(paramDimensionManager1, paramDimensionManager2); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     @Nullable
/*     */     private final DimensionManager a;
/*     */     
/*     */     public b(@Nullable DimensionManager param1DimensionManager1, @Nullable DimensionManager param1DimensionManager2) {
/*  74 */       super(CriterionTriggerChangedDimension.b());
/*  75 */       this.a = param1DimensionManager1;
/*  76 */       this.b = param1DimensionManager2;
/*     */     } @Nullable
/*     */     private final DimensionManager b;
/*     */     public boolean a(DimensionManager param1DimensionManager1, DimensionManager param1DimensionManager2) {
/*  80 */       if (this.a != null && this.a != param1DimensionManager1) {
/*  81 */         return false;
/*     */       }
/*  83 */       if (this.b != null && this.b != param1DimensionManager2) {
/*  84 */         return false;
/*     */       }
/*  86 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  92 */     private final Set<CriterionTrigger.a<CriterionTriggerChangedDimension.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  95 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*  99 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerChangedDimension.b> param1a) {
/* 103 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerChangedDimension.b> param1a) {
/* 107 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(DimensionManager param1DimensionManager1, DimensionManager param1DimensionManager2) {
/* 111 */       ArrayList<CriterionTrigger.a<CriterionTriggerChangedDimension.b>> arrayList = null;
/* 112 */       for (CriterionTrigger.a<CriterionTriggerChangedDimension.b> a1 : this.b) {
/* 113 */         if (((CriterionTriggerChangedDimension.b)a1.a()).a(param1DimensionManager1, param1DimensionManager2)) {
/* 114 */           if (arrayList == null) {
/* 115 */             arrayList = Lists.newArrayList();
/*     */           }
/* 117 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 120 */       if (arrayList != null)
/* 121 */         for (CriterionTrigger.a<CriterionTriggerChangedDimension.b> a1 : arrayList)
/* 122 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerChangedDimension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */