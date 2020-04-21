/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityPortalEnterEvent;
/*    */ 
/*    */ public class BlockEnderPortal extends BlockTileEntity {
/* 11 */   protected static final AxisAlignedBB a = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
/*    */   
/*    */   protected BlockEnderPortal(Material material) {
/* 14 */     super(material);
/* 15 */     a(1.0F);
/*    */   }
/*    */   
/*    */   public TileEntity a(World world, int i) {
/* 19 */     return new TileEntityEnderPortal();
/*    */   }
/*    */   
/*    */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 23 */     return a;
/*    */   }
/*    */   
/*    */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, AxisAlignedBB axisalignedbb, List<AxisAlignedBB> list, @Nullable Entity entity, boolean flag) {}
/*    */   
/*    */   public boolean b(IBlockData iblockdata) {
/* 29 */     return false;
/*    */   }
/*    */   
/*    */   public boolean c(IBlockData iblockdata) {
/* 33 */     return false;
/*    */   }
/*    */   
/*    */   public int a(Random random) {
/* 37 */     return 0;
/*    */   }
/*    */   
/*    */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
/* 41 */     if (!entity.isPassenger() && !entity.isVehicle() && entity.bf() && !world.isClientSide && entity.getBoundingBox().c(iblockdata.e(world, blockposition).a(blockposition))) {
/*    */       
/* 43 */       EntityPortalEnterEvent event = new EntityPortalEnterEvent((Entity)entity.getBukkitEntity(), new Location((World)world.getWorld(), blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 44 */       world.getServer().getPluginManager().callEvent((Event)event);
/*    */       
/* 46 */       entity.b(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 52 */     return ItemStack.a;
/*    */   }
/*    */   
/*    */   public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 56 */     return MaterialMapColor.F;
/*    */   }
/*    */   
/*    */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 60 */     return EnumBlockFaceShape.UNDEFINED;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockEnderPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */