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
/*    */ public class EntityMinecartChest
/*    */   extends EntityMinecartContainer
/*    */ {
/*    */   public EntityMinecartChest(World paramWorld) {
/* 20 */     super(paramWorld);
/*    */   }
/*    */   
/*    */   public EntityMinecartChest(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
/* 24 */     super(paramWorld, paramDouble1, paramDouble2, paramDouble3);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager paramDataConverterManager) {
/* 28 */     EntityMinecartContainer.b(paramDataConverterManager, EntityMinecartChest.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(DamageSource paramDamageSource) {
/* 33 */     super.a(paramDamageSource);
/*    */     
/* 35 */     if (this.world.getGameRules().getBoolean("doEntityDrops")) {
/* 36 */       a(Item.getItemOf(Blocks.CHEST), 1, 0.0F);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSize() {
/* 42 */     return 27;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecartAbstract.EnumMinecartType v() {
/* 47 */     return EntityMinecartAbstract.EnumMinecartType.CHEST;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData x() {
/* 52 */     return Blocks.CHEST.getBlockData().set(BlockChest.FACING, EnumDirection.NORTH);
/*    */   }
/*    */ 
/*    */   
/*    */   public int z() {
/* 57 */     return 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getContainerName() {
/* 62 */     return "minecraft:chest";
/*    */   }
/*    */ 
/*    */   
/*    */   public Container createContainer(PlayerInventory paramPlayerInventory, EntityHuman paramEntityHuman) {
/* 67 */     f(paramEntityHuman);
/* 68 */     return new ContainerChest(paramPlayerInventory, this, paramEntityHuman);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityMinecartChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */