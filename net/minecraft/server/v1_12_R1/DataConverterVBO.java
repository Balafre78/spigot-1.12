/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterVBO
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/*  9 */     return 505;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 14 */     paramNBTTagCompound.setString("useVbo", "true");
/* 15 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterVBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */