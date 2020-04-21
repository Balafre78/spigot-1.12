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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityMinecartMobSpawner
/*    */   extends EntityMinecartAbstract
/*    */ {
/* 19 */   private final MobSpawnerAbstract a = new MobSpawnerAbstract(this)
/*    */     {
/*    */       public void a(int param1Int) {
/* 22 */         this.a.world.broadcastEntityEffect(this.a, (byte)param1Int);
/*    */       }
/*    */ 
/*    */       
/*    */       public World a() {
/* 27 */         return this.a.world;
/*    */       }
/*    */ 
/*    */       
/*    */       public BlockPosition b() {
/* 32 */         return new BlockPosition(this.a);
/*    */       }
/*    */     };
/*    */   
/*    */   public EntityMinecartMobSpawner(World paramWorld) {
/* 37 */     super(paramWorld);
/*    */   }
/*    */   
/*    */   public EntityMinecartMobSpawner(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
/* 41 */     super(paramWorld, paramDouble1, paramDouble2, paramDouble3);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager paramDataConverterManager) {
/* 45 */     a(paramDataConverterManager, EntityMinecartMobSpawner.class);
/* 46 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataInspector()
/*    */         {
/*    */           public NBTTagCompound a(DataConverter param1DataConverter, NBTTagCompound param1NBTTagCompound, int param1Int) {
/* 49 */             String str = param1NBTTagCompound.getString("id");
/* 50 */             if (EntityTypes.getName((Class)EntityMinecartMobSpawner.class).equals(new MinecraftKey(str))) {
/*    */               
/* 52 */               param1NBTTagCompound.setString("id", TileEntity.a((Class)TileEntityMobSpawner.class).toString());
/* 53 */               param1DataConverter.a(DataConverterTypes.BLOCK_ENTITY, param1NBTTagCompound, param1Int);
/* 54 */               param1NBTTagCompound.setString("id", str);
/*    */             } 
/* 56 */             return param1NBTTagCompound;
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecartAbstract.EnumMinecartType v() {
/* 63 */     return EntityMinecartAbstract.EnumMinecartType.SPAWNER;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData x() {
/* 68 */     return Blocks.MOB_SPAWNER.getBlockData();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(NBTTagCompound paramNBTTagCompound) {
/* 73 */     super.a(paramNBTTagCompound);
/* 74 */     this.a.a(paramNBTTagCompound);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void b(NBTTagCompound paramNBTTagCompound) {
/* 79 */     super.b(paramNBTTagCompound);
/* 80 */     this.a.b(paramNBTTagCompound);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void B_() {
/* 90 */     super.B_();
/* 91 */     this.a.c();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityMinecartMobSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */