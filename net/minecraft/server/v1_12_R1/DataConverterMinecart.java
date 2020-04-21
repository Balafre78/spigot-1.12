/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class DataConverterMinecart
/*    */   implements IDataConverter
/*    */ {
/* 10 */   private static final List<String> a = Lists.newArrayList((Object[])new String[] { "MinecartRideable", "MinecartChest", "MinecartFurnace", "MinecartTNT", "MinecartSpawner", "MinecartHopper", "MinecartCommandBlock" });
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
/*    */   public int a() {
/* 22 */     return 106;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 27 */     if ("Minecart".equals(paramNBTTagCompound.getString("id"))) {
/* 28 */       String str = "MinecartRideable";
/* 29 */       int i = paramNBTTagCompound.getInt("Type");
/* 30 */       if (i > 0 && i < a.size()) {
/* 31 */         str = a.get(i);
/*    */       }
/* 33 */       paramNBTTagCompound.setString("id", str);
/* 34 */       paramNBTTagCompound.remove("Type");
/*    */     } 
/*    */     
/* 37 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */