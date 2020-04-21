/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterUUID
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/* 12 */     return 108;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 17 */     if (paramNBTTagCompound.hasKeyOfType("UUID", 8)) {
/* 18 */       paramNBTTagCompound.a("UUID", UUID.fromString(paramNBTTagCompound.getString("UUID")));
/*    */     }
/* 20 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterUUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */