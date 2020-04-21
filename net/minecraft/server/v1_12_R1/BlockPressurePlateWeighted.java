/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import java.util.Iterator;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.entity.EntityInteractEvent;
/*    */ 
/*    */ public class BlockPressurePlateWeighted extends BlockPressurePlateAbstract {
/*  7 */   public static final BlockStateInteger POWER = BlockStateInteger.of("power", 0, 15);
/*    */   private final int weight;
/*    */   
/*    */   protected BlockPressurePlateWeighted(Material material, int i) {
/* 11 */     this(material, i, material.r());
/*    */   }
/*    */   
/*    */   protected BlockPressurePlateWeighted(Material material, int i, MaterialMapColor materialmapcolor) {
/* 15 */     super(material, materialmapcolor);
/* 16 */     w(this.blockStateList.getBlockData().set(POWER, Integer.valueOf(0)));
/* 17 */     this.weight = i;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int e(World world, BlockPosition blockposition) {
/* 23 */     int i = 0;
/* 24 */     Iterator<Entity> iterator = world.<Entity>a(Entity.class, c.a(blockposition)).iterator();
/*    */     
/* 26 */     while (iterator.hasNext()) {
/* 27 */       EntityInteractEvent entityInteractEvent; Entity entity = iterator.next();
/*    */ 
/*    */ 
/*    */       
/* 31 */       if (entity instanceof EntityHuman) {
/* 32 */         PlayerInteractEvent playerInteractEvent = CraftEventFactory.callPlayerInteractEvent((EntityHuman)entity, Action.PHYSICAL, blockposition, null, null, null);
/*    */       } else {
/* 34 */         entityInteractEvent = new EntityInteractEvent((Entity)entity.getBukkitEntity(), world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 35 */         world.getServer().getPluginManager().callEvent((Event)entityInteractEvent);
/*    */       } 
/*    */ 
/*    */       
/* 39 */       if (!entityInteractEvent.isCancelled()) {
/* 40 */         i++;
/*    */       }
/*    */     } 
/*    */     
/* 44 */     i = Math.min(i, this.weight);
/*    */ 
/*    */     
/* 47 */     if (i > 0) {
/* 48 */       float f = Math.min(this.weight, i) / this.weight;
/*    */       
/* 50 */       return MathHelper.f(f * 15.0F);
/*    */     } 
/* 52 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void b(World world, BlockPosition blockposition) {
/* 57 */     world.a(null, blockposition, SoundEffects.ee, SoundCategory.BLOCKS, 0.3F, 0.90000004F);
/*    */   }
/*    */   
/*    */   protected void c(World world, BlockPosition blockposition) {
/* 61 */     world.a(null, blockposition, SoundEffects.ed, SoundCategory.BLOCKS, 0.3F, 0.75F);
/*    */   }
/*    */   
/*    */   protected int getPower(IBlockData iblockdata) {
/* 65 */     return ((Integer)iblockdata.<Integer>get(POWER)).intValue();
/*    */   }
/*    */   
/*    */   protected IBlockData a(IBlockData iblockdata, int i) {
/* 69 */     return iblockdata.set(POWER, Integer.valueOf(i));
/*    */   }
/*    */   
/*    */   public int a(World world) {
/* 73 */     return 10;
/*    */   }
/*    */   
/*    */   public IBlockData fromLegacyData(int i) {
/* 77 */     return getBlockData().set(POWER, Integer.valueOf(i));
/*    */   }
/*    */   
/*    */   public int toLegacyData(IBlockData iblockdata) {
/* 81 */     return ((Integer)iblockdata.<Integer>get(POWER)).intValue();
/*    */   }
/*    */   
/*    */   protected BlockStateList getStateList() {
/* 85 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { POWER });
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPressurePlateWeighted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */