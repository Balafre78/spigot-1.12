/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataInspectorItem
/*    */   extends DataInspectorTagged
/*    */ {
/*    */   private final String[] a;
/*    */   
/*    */   public DataInspectorItem(Class<?> paramClass, String... paramVarArgs) {
/* 11 */     super(paramClass);
/* 12 */     this.a = paramVarArgs;
/*    */   }
/*    */ 
/*    */   
/*    */   NBTTagCompound b(DataConverter paramDataConverter, NBTTagCompound paramNBTTagCompound, int paramInt) {
/* 17 */     for (String str : this.a) {
/* 18 */       paramNBTTagCompound = DataConverterRegistry.a(paramDataConverter, paramNBTTagCompound, paramInt, str);
/*    */     }
/* 20 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataInspectorItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */