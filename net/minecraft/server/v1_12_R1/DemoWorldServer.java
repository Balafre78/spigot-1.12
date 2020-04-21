/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DemoWorldServer
/*    */   extends WorldServer
/*    */ {
/* 14 */   private static final long K = "North Carolina".hashCode();
/*    */   
/* 16 */   public static final WorldSettings a = (new WorldSettings(K, EnumGamemode.SURVIVAL, true, false, WorldType.NORMAL)).a();
/*    */   
/*    */   public DemoWorldServer(MinecraftServer paramMinecraftServer, IDataManager paramIDataManager, WorldData paramWorldData, int paramInt, MethodProfiler paramMethodProfiler) {
/* 19 */     super(paramMinecraftServer, paramIDataManager, paramWorldData, paramInt, paramMethodProfiler);
/* 20 */     this.worldData.a(a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DemoWorldServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */