/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootValueBounds
/*    */ {
/*    */   private final float a;
/*    */   private final float b;
/*    */   
/*    */   public LootValueBounds(float paramFloat1, float paramFloat2) {
/* 22 */     this.a = paramFloat1;
/* 23 */     this.b = paramFloat2;
/*    */   }
/*    */   
/*    */   public LootValueBounds(float paramFloat) {
/* 27 */     this.a = paramFloat;
/* 28 */     this.b = paramFloat;
/*    */   }
/*    */   
/*    */   public float a() {
/* 32 */     return this.a;
/*    */   }
/*    */   
/*    */   public float b() {
/* 36 */     return this.b;
/*    */   }
/*    */   
/*    */   public int a(Random paramRandom) {
/* 40 */     return MathHelper.nextInt(paramRandom, MathHelper.d(this.a), MathHelper.d(this.b));
/*    */   }
/*    */   
/*    */   public float b(Random paramRandom) {
/* 44 */     return MathHelper.a(paramRandom, this.a, this.b);
/*    */   }
/*    */   
/*    */   public boolean a(int paramInt) {
/* 48 */     return (paramInt <= this.b && paramInt >= this.a);
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements JsonDeserializer<LootValueBounds>, JsonSerializer<LootValueBounds> {
/*    */     public LootValueBounds a(JsonElement param1JsonElement, Type param1Type, JsonDeserializationContext param1JsonDeserializationContext) throws JsonParseException {
/* 54 */       if (ChatDeserializer.b(param1JsonElement)) {
/* 55 */         return new LootValueBounds(ChatDeserializer.e(param1JsonElement, "value"));
/*    */       }
/* 57 */       JsonObject jsonObject = ChatDeserializer.m(param1JsonElement, "value");
/* 58 */       float f1 = ChatDeserializer.l(jsonObject, "min");
/* 59 */       float f2 = ChatDeserializer.l(jsonObject, "max");
/* 60 */       return new LootValueBounds(f1, f2);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonElement a(LootValueBounds param1LootValueBounds, Type param1Type, JsonSerializationContext param1JsonSerializationContext) {
/* 66 */       if (LootValueBounds.a(param1LootValueBounds) == LootValueBounds.b(param1LootValueBounds)) {
/* 67 */         return (JsonElement)new JsonPrimitive(Float.valueOf(LootValueBounds.a(param1LootValueBounds)));
/*    */       }
/* 69 */       JsonObject jsonObject = new JsonObject();
/* 70 */       jsonObject.addProperty("min", Float.valueOf(LootValueBounds.a(param1LootValueBounds)));
/* 71 */       jsonObject.addProperty("max", Float.valueOf(LootValueBounds.b(param1LootValueBounds)));
/* 72 */       return (JsonElement)jsonObject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootValueBounds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */