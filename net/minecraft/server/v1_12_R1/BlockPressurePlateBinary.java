/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.entity.EntityInteractEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class BlockPressurePlateBinary extends BlockPressurePlateAbstract {
/*  10 */   public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");
/*     */   private final EnumMobType e;
/*     */   
/*     */   protected BlockPressurePlateBinary(Material material, EnumMobType blockpressureplatebinary_enummobtype) {
/*  14 */     super(material);
/*  15 */     w(this.blockStateList.getBlockData().set(POWERED, Boolean.valueOf(false)));
/*  16 */     this.e = blockpressureplatebinary_enummobtype;
/*     */   }
/*     */   
/*     */   protected int getPower(IBlockData iblockdata) {
/*  20 */     return ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue() ? 15 : 0;
/*     */   }
/*     */   
/*     */   protected IBlockData a(IBlockData iblockdata, int i) {
/*  24 */     return iblockdata.set(POWERED, Boolean.valueOf((i > 0)));
/*     */   }
/*     */   
/*     */   protected void b(World world, BlockPosition blockposition) {
/*  28 */     if (this.material == Material.WOOD) {
/*  29 */       world.a(null, blockposition, SoundEffects.jg, SoundCategory.BLOCKS, 0.3F, 0.8F);
/*     */     } else {
/*  31 */       world.a(null, blockposition, SoundEffects.hP, SoundCategory.BLOCKS, 0.3F, 0.6F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(World world, BlockPosition blockposition) {
/*  37 */     if (this.material == Material.WOOD) {
/*  38 */       world.a(null, blockposition, SoundEffects.jf, SoundCategory.BLOCKS, 0.3F, 0.7F);
/*     */     } else {
/*  40 */       world.a(null, blockposition, SoundEffects.hO, SoundCategory.BLOCKS, 0.3F, 0.5F);
/*     */     } 
/*     */   }
/*     */   protected int e(World world, BlockPosition blockposition) {
/*     */     List<Entity> list1;
/*     */     List<EntityLiving> list;
/*  46 */     AxisAlignedBB axisalignedbb = c.a(blockposition);
/*     */ 
/*     */     
/*  49 */     switch (this.e) {
/*     */       case null:
/*  51 */         list1 = world.getEntities(null, axisalignedbb);
/*     */         break;
/*     */       
/*     */       case MOBS:
/*  55 */         list = world.a(EntityLiving.class, axisalignedbb);
/*     */         break;
/*     */       
/*     */       default:
/*  59 */         return 0;
/*     */     } 
/*     */     
/*  62 */     if (!list.isEmpty()) {
/*  63 */       Iterator<EntityLiving> iterator = list.iterator();
/*     */       
/*  65 */       while (iterator.hasNext()) {
/*  66 */         Entity entity = iterator.next();
/*     */ 
/*     */         
/*  69 */         if (getPower(world.getType(blockposition)) == 0) {
/*  70 */           EntityInteractEvent entityInteractEvent; CraftWorld craftWorld = world.getWorld();
/*  71 */           PluginManager manager = world.getServer().getPluginManager();
/*     */ 
/*     */           
/*  74 */           if (entity instanceof EntityHuman) {
/*  75 */             PlayerInteractEvent playerInteractEvent = CraftEventFactory.callPlayerInteractEvent((EntityHuman)entity, Action.PHYSICAL, blockposition, null, null, null);
/*     */           } else {
/*  77 */             entityInteractEvent = new EntityInteractEvent((Entity)entity.getBukkitEntity(), craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/*  78 */             manager.callEvent((Event)entityInteractEvent);
/*     */           } 
/*     */ 
/*     */           
/*  82 */           if (entityInteractEvent.isCancelled()) {
/*     */             continue;
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/*  88 */         if (!entity.isIgnoreBlockTrigger()) {
/*  89 */           return 15;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  94 */     return 0;
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/*  98 */     return getBlockData().set(POWERED, Boolean.valueOf((i == 1)));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 102 */     return ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue() ? 1 : 0;
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 106 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { POWERED });
/*     */   }
/*     */   
/*     */   public enum EnumMobType
/*     */   {
/* 111 */     EVERYTHING, MOBS;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPressurePlateBinary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */