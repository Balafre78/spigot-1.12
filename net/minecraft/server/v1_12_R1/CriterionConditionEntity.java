/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionConditionEntity
/*    */ {
/* 15 */   public static final CriterionConditionEntity a = new CriterionConditionEntity(null, CriterionConditionDistance.a, CriterionConditionLocation.a, CriterionConditionMobEffect.a, CriterionConditionNBT.a);
/*    */   
/*    */   private final MinecraftKey b;
/*    */   private final CriterionConditionDistance c;
/*    */   private final CriterionConditionLocation d;
/*    */   private final CriterionConditionMobEffect e;
/*    */   private final CriterionConditionNBT f;
/*    */   
/*    */   public CriterionConditionEntity(@Nullable MinecraftKey paramMinecraftKey, CriterionConditionDistance paramCriterionConditionDistance, CriterionConditionLocation paramCriterionConditionLocation, CriterionConditionMobEffect paramCriterionConditionMobEffect, CriterionConditionNBT paramCriterionConditionNBT) {
/* 24 */     this.b = paramMinecraftKey;
/* 25 */     this.c = paramCriterionConditionDistance;
/* 26 */     this.d = paramCriterionConditionLocation;
/* 27 */     this.e = paramCriterionConditionMobEffect;
/* 28 */     this.f = paramCriterionConditionNBT;
/*    */   }
/*    */   
/*    */   public boolean a(EntityPlayer paramEntityPlayer, @Nullable Entity paramEntity) {
/* 32 */     if (this == a) {
/* 33 */       return true;
/*    */     }
/* 35 */     if (paramEntity == null) {
/* 36 */       return false;
/*    */     }
/* 38 */     if (this.b != null && !EntityTypes.a(paramEntity, this.b)) {
/* 39 */       return false;
/*    */     }
/* 41 */     if (!this.c.a(paramEntityPlayer.locX, paramEntityPlayer.locY, paramEntityPlayer.locZ, paramEntity.locX, paramEntity.locY, paramEntity.locZ)) {
/* 42 */       return false;
/*    */     }
/* 44 */     if (!this.d.a(paramEntityPlayer.x(), paramEntity.locX, paramEntity.locY, paramEntity.locZ)) {
/* 45 */       return false;
/*    */     }
/* 47 */     if (!this.e.a(paramEntity)) {
/* 48 */       return false;
/*    */     }
/* 50 */     if (!this.f.a(paramEntity)) {
/* 51 */       return false;
/*    */     }
/* 53 */     return true;
/*    */   }
/*    */   
/*    */   public static CriterionConditionEntity a(@Nullable JsonElement paramJsonElement) {
/* 57 */     if (paramJsonElement == null || paramJsonElement.isJsonNull()) {
/* 58 */       return a;
/*    */     }
/* 60 */     JsonObject jsonObject = ChatDeserializer.m(paramJsonElement, "entity");
/* 61 */     MinecraftKey minecraftKey = null;
/* 62 */     if (jsonObject.has("type")) {
/* 63 */       minecraftKey = new MinecraftKey(ChatDeserializer.h(jsonObject, "type"));
/* 64 */       if (!EntityTypes.b(minecraftKey)) {
/* 65 */         throw new JsonSyntaxException("Unknown entity type '" + minecraftKey + "', valid types are: " + EntityTypes.b());
/*    */       }
/*    */     } 
/* 68 */     CriterionConditionDistance criterionConditionDistance = CriterionConditionDistance.a(jsonObject.get("distance"));
/* 69 */     CriterionConditionLocation criterionConditionLocation = CriterionConditionLocation.a(jsonObject.get("location"));
/* 70 */     CriterionConditionMobEffect criterionConditionMobEffect = CriterionConditionMobEffect.a(jsonObject.get("effects"));
/* 71 */     CriterionConditionNBT criterionConditionNBT = CriterionConditionNBT.a(jsonObject.get("nbt"));
/* 72 */     return new CriterionConditionEntity(minecraftKey, criterionConditionDistance, criterionConditionLocation, criterionConditionMobEffect, criterionConditionNBT);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionConditionEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */