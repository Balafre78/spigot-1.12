/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockTNT extends Block {
/*   5 */   public static final BlockStateBoolean EXPLODE = BlockStateBoolean.of("explode");
/*     */   
/*     */   public BlockTNT() {
/*   8 */     super(Material.TNT);
/*   9 */     w(this.blockStateList.getBlockData().set(EXPLODE, Boolean.valueOf(false)));
/*  10 */     a(CreativeModeTab.d);
/*     */   }
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  14 */     super.onPlace(world, blockposition, iblockdata);
/*  15 */     if (world.isBlockIndirectlyPowered(blockposition)) {
/*  16 */       postBreak(world, blockposition, iblockdata.set(EXPLODE, Boolean.valueOf(true)));
/*  17 */       world.setAir(blockposition);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  23 */     if (world.isBlockIndirectlyPowered(blockposition)) {
/*  24 */       postBreak(world, blockposition, iblockdata.set(EXPLODE, Boolean.valueOf(true)));
/*  25 */       world.setAir(blockposition);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void wasExploded(World world, BlockPosition blockposition, Explosion explosion) {
/*  31 */     if (!world.isClientSide) {
/*  32 */       EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (blockposition.getX() + 0.5F), blockposition.getY(), (blockposition.getZ() + 0.5F), explosion.getSource());
/*     */       
/*  34 */       entitytntprimed.setFuseTicks((short)(world.random.nextInt(entitytntprimed.getFuseTicks() / 4) + entitytntprimed.getFuseTicks() / 8));
/*  35 */       world.addEntity(entitytntprimed);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void postBreak(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  40 */     a(world, blockposition, iblockdata, (EntityLiving)null);
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving) {
/*  44 */     if (!world.isClientSide && (
/*  45 */       (Boolean)iblockdata.<Boolean>get(EXPLODE)).booleanValue()) {
/*  46 */       EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (blockposition.getX() + 0.5F), blockposition.getY(), (blockposition.getZ() + 0.5F), entityliving);
/*     */       
/*  48 */       world.addEntity(entitytntprimed);
/*  49 */       world.a((EntityHuman)null, entitytntprimed.locX, entitytntprimed.locY, entitytntprimed.locZ, SoundEffects.hW, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  56 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/*  58 */     if (!itemstack.isEmpty() && (itemstack.getItem() == Items.FLINT_AND_STEEL || itemstack.getItem() == Items.FIRE_CHARGE)) {
/*  59 */       a(world, blockposition, iblockdata.set(EXPLODE, Boolean.valueOf(true)), entityhuman);
/*  60 */       world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 11);
/*  61 */       if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
/*  62 */         itemstack.damage(1, entityhuman);
/*  63 */       } else if (!entityhuman.abilities.canInstantlyBuild) {
/*  64 */         itemstack.subtract(1);
/*     */       } 
/*     */       
/*  67 */       return true;
/*     */     } 
/*  69 */     return super.interact(world, blockposition, iblockdata, entityhuman, enumhand, enumdirection, f, f1, f2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
/*  74 */     if (!world.isClientSide && entity instanceof EntityArrow) {
/*  75 */       EntityArrow entityarrow = (EntityArrow)entity;
/*     */       
/*  77 */       if (entityarrow.isBurning()) {
/*     */         
/*  79 */         if (CraftEventFactory.callEntityChangeBlockEvent(entityarrow, blockposition, Blocks.AIR, 0).isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/*  83 */         a(world, blockposition, world.getType(blockposition).set(EXPLODE, Boolean.valueOf(true)), (entityarrow.shooter instanceof EntityLiving) ? (EntityLiving)entityarrow.shooter : null);
/*  84 */         world.setAir(blockposition);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(Explosion explosion) {
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/*  95 */     return getBlockData().set(EXPLODE, Boolean.valueOf(((i & 0x1) > 0)));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/*  99 */     return ((Boolean)iblockdata.<Boolean>get(EXPLODE)).booleanValue() ? 1 : 0;
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 103 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { EXPLODE });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */