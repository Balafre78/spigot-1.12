/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterEquipment
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/* 12 */     return 100;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 18 */     NBTTagList nBTTagList = paramNBTTagCompound.getList("Equipment", 10);
/* 19 */     if (!nBTTagList.isEmpty() && !paramNBTTagCompound.hasKeyOfType("HandItems", 10)) {
/* 20 */       NBTTagList nBTTagList1 = new NBTTagList();
/*    */       
/* 22 */       nBTTagList1.add(nBTTagList.i(0));
/* 23 */       nBTTagList1.add(new NBTTagCompound());
/* 24 */       paramNBTTagCompound.set("HandItems", nBTTagList1);
/*    */     } 
/*    */     
/* 27 */     if (nBTTagList.size() > 1 && !paramNBTTagCompound.hasKeyOfType("ArmorItem", 10)) {
/* 28 */       NBTTagList nBTTagList1 = new NBTTagList();
/*    */       
/* 30 */       nBTTagList1.add(nBTTagList.get(1));
/* 31 */       nBTTagList1.add(nBTTagList.get(2));
/* 32 */       nBTTagList1.add(nBTTagList.get(3));
/* 33 */       nBTTagList1.add(nBTTagList.get(4));
/* 34 */       paramNBTTagCompound.set("ArmorItems", nBTTagList1);
/*    */     } 
/* 36 */     paramNBTTagCompound.remove("Equipment");
/*    */ 
/*    */     
/* 39 */     if (paramNBTTagCompound.hasKeyOfType("DropChances", 9)) {
/* 40 */       NBTTagList nBTTagList1 = paramNBTTagCompound.getList("DropChances", 5);
/* 41 */       if (!paramNBTTagCompound.hasKeyOfType("HandDropChances", 10)) {
/* 42 */         NBTTagList nBTTagList2 = new NBTTagList();
/*    */         
/* 44 */         nBTTagList2.add(new NBTTagFloat(nBTTagList1.g(0)));
/* 45 */         nBTTagList2.add(new NBTTagFloat(0.0F));
/* 46 */         paramNBTTagCompound.set("HandDropChances", nBTTagList2);
/*    */       } 
/*    */       
/* 49 */       if (!paramNBTTagCompound.hasKeyOfType("ArmorDropChances", 10)) {
/* 50 */         NBTTagList nBTTagList2 = new NBTTagList();
/*    */         
/* 52 */         nBTTagList2.add(new NBTTagFloat(nBTTagList1.g(1)));
/* 53 */         nBTTagList2.add(new NBTTagFloat(nBTTagList1.g(2)));
/* 54 */         nBTTagList2.add(new NBTTagFloat(nBTTagList1.g(3)));
/* 55 */         nBTTagList2.add(new NBTTagFloat(nBTTagList1.g(4)));
/* 56 */         paramNBTTagCompound.set("ArmorDropChances", nBTTagList2);
/*    */       } 
/* 58 */       paramNBTTagCompound.remove("DropChances");
/*    */     } 
/*    */     
/* 61 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterEquipment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */