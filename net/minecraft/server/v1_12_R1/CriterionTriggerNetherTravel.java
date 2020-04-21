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
/*     */ public class CriterionTriggerNetherTravel
/*     */   implements CriterionTrigger<CriterionTriggerNetherTravel.b>
/*     */ {
/*  20 */   private static final MinecraftKey a = new MinecraftKey("nether_travel");
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
/*  56 */     CriterionConditionLocation criterionConditionLocation1 = CriterionConditionLocation.a(paramJsonObject.get("entered"));
/*  57 */     CriterionConditionLocation criterionConditionLocation2 = CriterionConditionLocation.a(paramJsonObject.get("exited"));
/*  58 */     CriterionConditionDistance criterionConditionDistance = CriterionConditionDistance.a(paramJsonObject.get("distance"));
/*  59 */     return new b(criterionConditionLocation1, criterionConditionLocation2, criterionConditionDistance);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, Vec3D paramVec3D) {
/*  63 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  64 */     if (a != null)
/*  65 */       a.a(paramEntityPlayer.x(), paramVec3D, paramEntityPlayer.locX, paramEntityPlayer.locY, paramEntityPlayer.locZ); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionLocation a;
/*     */     private final CriterionConditionLocation b;
/*     */     private final CriterionConditionDistance c;
/*     */     
/*     */     public b(CriterionConditionLocation param1CriterionConditionLocation1, CriterionConditionLocation param1CriterionConditionLocation2, CriterionConditionDistance param1CriterionConditionDistance) {
/*  75 */       super(CriterionTriggerNetherTravel.b());
/*  76 */       this.a = param1CriterionConditionLocation1;
/*  77 */       this.b = param1CriterionConditionLocation2;
/*  78 */       this.c = param1CriterionConditionDistance;
/*     */     }
/*     */     
/*     */     public boolean a(WorldServer param1WorldServer, Vec3D param1Vec3D, double param1Double1, double param1Double2, double param1Double3) {
/*  82 */       if (!this.a.a(param1WorldServer, param1Vec3D.x, param1Vec3D.y, param1Vec3D.z)) {
/*  83 */         return false;
/*     */       }
/*  85 */       if (!this.b.a(param1WorldServer, param1Double1, param1Double2, param1Double3)) {
/*  86 */         return false;
/*     */       }
/*  88 */       if (!this.c.a(param1Vec3D.x, param1Vec3D.y, param1Vec3D.z, param1Double1, param1Double2, param1Double3)) {
/*  89 */         return false;
/*     */       }
/*  91 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  97 */     private final Set<CriterionTrigger.a<CriterionTriggerNetherTravel.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/* 100 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 104 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerNetherTravel.b> param1a) {
/* 108 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerNetherTravel.b> param1a) {
/* 112 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(WorldServer param1WorldServer, Vec3D param1Vec3D, double param1Double1, double param1Double2, double param1Double3) {
/* 116 */       ArrayList<CriterionTrigger.a<CriterionTriggerNetherTravel.b>> arrayList = null;
/* 117 */       for (CriterionTrigger.a<CriterionTriggerNetherTravel.b> a1 : this.b) {
/* 118 */         if (((CriterionTriggerNetherTravel.b)a1.a()).a(param1WorldServer, param1Vec3D, param1Double1, param1Double2, param1Double3)) {
/* 119 */           if (arrayList == null) {
/* 120 */             arrayList = Lists.newArrayList();
/*     */           }
/* 122 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 125 */       if (arrayList != null)
/* 126 */         for (CriterionTrigger.a<CriterionTriggerNetherTravel.b> a1 : arrayList)
/* 127 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerNetherTravel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */