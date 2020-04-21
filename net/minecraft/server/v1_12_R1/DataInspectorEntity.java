/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataInspectorEntity
/*    */   implements DataInspector
/*    */ {
/* 12 */   private static final Logger a = LogManager.getLogger();
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(DataConverter paramDataConverter, NBTTagCompound paramNBTTagCompound, int paramInt) {
/* 16 */     NBTTagCompound nBTTagCompound = paramNBTTagCompound.getCompound("tag");
/* 17 */     if (nBTTagCompound.hasKeyOfType("EntityTag", 10)) {
/* 18 */       String str2; boolean bool; NBTTagCompound nBTTagCompound1 = nBTTagCompound.getCompound("EntityTag");
/*    */ 
/*    */       
/* 21 */       String str1 = paramNBTTagCompound.getString("id");
/*    */ 
/*    */       
/* 24 */       if ("minecraft:armor_stand".equals(str1)) {
/* 25 */         str2 = (paramInt < 515) ? "ArmorStand" : "minecraft:armor_stand";
/* 26 */       } else if ("minecraft:spawn_egg".equals(str1)) {
/* 27 */         str2 = nBTTagCompound1.getString("id");
/*    */       } else {
/* 29 */         return paramNBTTagCompound;
/*    */       } 
/*    */ 
/*    */       
/* 33 */       if (str2 == null) {
/* 34 */         a.warn("Unable to resolve Entity for ItemInstance: {}", str1);
/* 35 */         bool = false;
/*    */       } else {
/* 37 */         bool = !nBTTagCompound1.hasKeyOfType("id", 8) ? true : false;
/* 38 */         nBTTagCompound1.setString("id", str2);
/*    */       } 
/*    */       
/* 41 */       paramDataConverter.a(DataConverterTypes.ENTITY, nBTTagCompound1, paramInt);
/*    */       
/* 43 */       if (bool) {
/* 44 */         nBTTagCompound1.remove("id");
/*    */       }
/*    */     } 
/* 47 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataInspectorEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */