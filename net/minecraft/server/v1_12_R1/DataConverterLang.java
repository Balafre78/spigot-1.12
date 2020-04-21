/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Locale;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterLang
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/* 12 */     return 816;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 17 */     if (paramNBTTagCompound.hasKeyOfType("lang", 8)) {
/* 18 */       paramNBTTagCompound.setString("lang", paramNBTTagCompound.getString("lang").toLowerCase(Locale.ROOT));
/*    */     }
/* 20 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterLang.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */