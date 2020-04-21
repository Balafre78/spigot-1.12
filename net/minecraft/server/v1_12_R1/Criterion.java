/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Criterion
/*    */ {
/*    */   private final CriterionInstance a;
/*    */   
/*    */   public Criterion(CriterionInstance paramCriterionInstance) {
/* 19 */     this.a = paramCriterionInstance;
/*    */   }
/*    */   
/*    */   public Criterion() {
/* 23 */     this.a = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) {}
/*    */   
/*    */   public static Criterion a(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/* 30 */     MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(paramJsonObject, "trigger"));
/* 31 */     CriterionTrigger<CriterionInstance> criterionTrigger = CriterionTriggers.a(minecraftKey);
/* 32 */     if (criterionTrigger == null) {
/* 33 */       throw new JsonSyntaxException("Invalid criterion trigger: " + minecraftKey);
/*    */     }
/* 35 */     CriterionInstance criterionInstance = (CriterionInstance)criterionTrigger.a(ChatDeserializer.a(paramJsonObject, "conditions", new JsonObject()), paramJsonDeserializationContext);
/* 36 */     return new Criterion(criterionInstance);
/*    */   }
/*    */   
/*    */   public static Criterion b(PacketDataSerializer paramPacketDataSerializer) {
/* 40 */     return new Criterion();
/*    */   }
/*    */   
/*    */   public static Map<String, Criterion> b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/* 44 */     HashMap<String, Criterion> hashMap = Maps.newHashMap();
/* 45 */     for (Map.Entry entry : paramJsonObject.entrySet()) {
/* 46 */       hashMap.put(entry.getKey(), a(ChatDeserializer.m((JsonElement)entry.getValue(), "criterion"), paramJsonDeserializationContext));
/*    */     }
/* 48 */     return hashMap;
/*    */   }
/*    */   
/*    */   public static Map<String, Criterion> c(PacketDataSerializer paramPacketDataSerializer) {
/* 52 */     HashMap<String, Criterion> hashMap = Maps.newHashMap();
/* 53 */     int i = paramPacketDataSerializer.g();
/* 54 */     for (byte b = 0; b < i; b++) {
/* 55 */       hashMap.put(paramPacketDataSerializer.e(32767), b(paramPacketDataSerializer));
/*    */     }
/* 57 */     return hashMap;
/*    */   }
/*    */   
/*    */   public static void a(Map<String, Criterion> paramMap, PacketDataSerializer paramPacketDataSerializer) {
/* 61 */     paramPacketDataSerializer.d(paramMap.size());
/* 62 */     for (Map.Entry<String, Criterion> entry : paramMap.entrySet()) {
/* 63 */       paramPacketDataSerializer.a((String)entry.getKey());
/* 64 */       ((Criterion)entry.getValue()).a(paramPacketDataSerializer);
/*    */     } 
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public CriterionInstance a() {
/* 70 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Criterion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */