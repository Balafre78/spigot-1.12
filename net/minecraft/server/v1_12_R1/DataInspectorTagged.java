/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class DataInspectorTagged
/*    */   implements DataInspector
/*    */ {
/*    */   private final MinecraftKey a;
/*    */   
/*    */   public DataInspectorTagged(Class<?> paramClass) {
/* 16 */     if (Entity.class.isAssignableFrom(paramClass)) {
/* 17 */       this.a = EntityTypes.getName((Class)paramClass);
/* 18 */     } else if (TileEntity.class.isAssignableFrom(paramClass)) {
/* 19 */       this.a = TileEntity.a((Class)paramClass);
/*    */     } else {
/* 21 */       this.a = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(DataConverter paramDataConverter, NBTTagCompound paramNBTTagCompound, int paramInt) {
/* 27 */     if ((new MinecraftKey(paramNBTTagCompound.getString("id"))).equals(this.a)) {
/* 28 */       paramNBTTagCompound = b(paramDataConverter, paramNBTTagCompound, paramInt);
/*    */     }
/* 30 */     return paramNBTTagCompound;
/*    */   }
/*    */   
/*    */   abstract NBTTagCompound b(DataConverter paramDataConverter, NBTTagCompound paramNBTTagCompound, int paramInt);
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataInspectorTagged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */