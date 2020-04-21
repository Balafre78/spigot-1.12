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
/*    */ 
/*    */ public class CriterionConditionLocation
/*    */ {
/* 16 */   public static CriterionConditionLocation a = new CriterionConditionLocation(CriterionConditionValue.a, CriterionConditionValue.a, CriterionConditionValue.a, null, null, null);
/*    */   
/*    */   private final CriterionConditionValue c;
/*    */   
/*    */   private final CriterionConditionValue d;
/*    */   
/*    */   private final CriterionConditionValue e;
/*    */ 
/*    */   
/*    */   public CriterionConditionLocation(CriterionConditionValue paramCriterionConditionValue1, CriterionConditionValue paramCriterionConditionValue2, CriterionConditionValue paramCriterionConditionValue3, @Nullable BiomeBase paramBiomeBase, @Nullable String paramString, @Nullable DimensionManager paramDimensionManager) {
/* 26 */     this.c = paramCriterionConditionValue1;
/* 27 */     this.d = paramCriterionConditionValue2;
/* 28 */     this.e = paramCriterionConditionValue3;
/* 29 */     this.b = paramBiomeBase;
/* 30 */     this.f = paramString;
/* 31 */     this.g = paramDimensionManager; } @Nullable
/*    */   final BiomeBase b; @Nullable
/*    */   private final String f; @Nullable
/*    */   private final DimensionManager g; public boolean a(WorldServer paramWorldServer, double paramDouble1, double paramDouble2, double paramDouble3) {
/* 35 */     return a(paramWorldServer, (float)paramDouble1, (float)paramDouble2, (float)paramDouble3);
/*    */   }
/*    */   
/*    */   public boolean a(WorldServer paramWorldServer, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 39 */     if (!this.c.a(paramFloat1)) {
/* 40 */       return false;
/*    */     }
/* 42 */     if (!this.d.a(paramFloat2)) {
/* 43 */       return false;
/*    */     }
/* 45 */     if (!this.e.a(paramFloat3)) {
/* 46 */       return false;
/*    */     }
/* 48 */     if (this.g != null && this.g != paramWorldServer.worldProvider.getDimensionManager()) {
/* 49 */       return false;
/*    */     }
/* 51 */     BlockPosition blockPosition = new BlockPosition(paramFloat1, paramFloat2, paramFloat3);
/* 52 */     if (this.b != null && this.b != paramWorldServer.getBiome(blockPosition)) {
/* 53 */       return false;
/*    */     }
/* 55 */     if (this.f != null && !paramWorldServer.getChunkProviderServer().a(paramWorldServer, this.f, blockPosition)) {
/* 56 */       return false;
/*    */     }
/* 58 */     return true;
/*    */   }
/*    */   
/*    */   public static CriterionConditionLocation a(@Nullable JsonElement paramJsonElement) {
/* 62 */     if (paramJsonElement == null || paramJsonElement.isJsonNull()) {
/* 63 */       return a;
/*    */     }
/* 65 */     JsonObject jsonObject1 = ChatDeserializer.m(paramJsonElement, "location");
/* 66 */     JsonObject jsonObject2 = ChatDeserializer.a(jsonObject1, "position", new JsonObject());
/* 67 */     CriterionConditionValue criterionConditionValue1 = CriterionConditionValue.a(jsonObject2.get("x"));
/* 68 */     CriterionConditionValue criterionConditionValue2 = CriterionConditionValue.a(jsonObject2.get("y"));
/* 69 */     CriterionConditionValue criterionConditionValue3 = CriterionConditionValue.a(jsonObject2.get("z"));
/* 70 */     DimensionManager dimensionManager = jsonObject1.has("dimension") ? DimensionManager.a(ChatDeserializer.h(jsonObject1, "dimension")) : null;
/* 71 */     String str = jsonObject1.has("feature") ? ChatDeserializer.h(jsonObject1, "feature") : null;
/* 72 */     BiomeBase biomeBase = null;
/* 73 */     if (jsonObject1.has("biome")) {
/* 74 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(jsonObject1, "biome"));
/* 75 */       biomeBase = BiomeBase.REGISTRY_ID.get(minecraftKey);
/* 76 */       if (biomeBase == null) {
/* 77 */         throw new JsonSyntaxException("Unknown biome '" + minecraftKey + "'");
/*    */       }
/*    */     } 
/* 80 */     return new CriterionConditionLocation(criterionConditionValue1, criterionConditionValue2, criterionConditionValue3, biomeBase, str, dimensionManager);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionConditionLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */