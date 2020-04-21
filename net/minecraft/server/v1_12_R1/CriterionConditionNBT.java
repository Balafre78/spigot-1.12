/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionConditionNBT
/*    */ {
/* 18 */   public static final CriterionConditionNBT a = new CriterionConditionNBT(null);
/*    */   @Nullable
/*    */   private final NBTTagCompound b;
/*    */   
/*    */   public CriterionConditionNBT(@Nullable NBTTagCompound paramNBTTagCompound) {
/* 23 */     this.b = paramNBTTagCompound;
/*    */   }
/*    */   
/*    */   public boolean a(ItemStack paramItemStack) {
/* 27 */     if (this == a) {
/* 28 */       return true;
/*    */     }
/* 30 */     return a(paramItemStack.getTag());
/*    */   }
/*    */   
/*    */   public boolean a(Entity paramEntity) {
/* 34 */     if (this == a) {
/* 35 */       return true;
/*    */     }
/* 37 */     return a(CommandAbstract.a(paramEntity));
/*    */   }
/*    */   
/*    */   public boolean a(@Nullable NBTBase paramNBTBase) {
/* 41 */     if (paramNBTBase == null) {
/* 42 */       return (this == a);
/*    */     }
/*    */     
/* 45 */     if (this.b != null && !GameProfileSerializer.a(this.b, paramNBTBase, true)) {
/* 46 */       return false;
/*    */     }
/*    */     
/* 49 */     return true;
/*    */   }
/*    */   public static CriterionConditionNBT a(@Nullable JsonElement paramJsonElement) {
/*    */     NBTTagCompound nBTTagCompound;
/* 53 */     if (paramJsonElement == null || paramJsonElement.isJsonNull()) {
/* 54 */       return a;
/*    */     }
/*    */     
/*    */     try {
/* 58 */       nBTTagCompound = MojangsonParser.parse(ChatDeserializer.a(paramJsonElement, "nbt"));
/* 59 */     } catch (MojangsonParseException mojangsonParseException) {
/* 60 */       throw new JsonSyntaxException("Invalid nbt tag: " + mojangsonParseException.getMessage());
/*    */     } 
/* 62 */     return new CriterionConditionNBT(nBTTagCompound);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionConditionNBT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */