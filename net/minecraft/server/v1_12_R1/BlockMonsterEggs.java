/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class BlockMonsterEggs
/*     */   extends Block
/*     */ {
/*   9 */   public static final BlockStateEnum<EnumMonsterEggVarient> VARIANT = BlockStateEnum.of("variant", EnumMonsterEggVarient.class);
/*     */   
/*     */   public BlockMonsterEggs() {
/*  12 */     super(Material.CLAY);
/*  13 */     w(this.blockStateList.getBlockData().set(VARIANT, EnumMonsterEggVarient.STONE));
/*  14 */     c(0.0F);
/*  15 */     a(CreativeModeTab.c);
/*     */   }
/*     */   
/*     */   public int a(Random random) {
/*  19 */     return 0;
/*     */   }
/*     */   
/*     */   public static boolean x(IBlockData iblockdata) {
/*  23 */     Block block = iblockdata.getBlock();
/*     */     
/*  25 */     return !(iblockdata != Blocks.STONE.getBlockData().set(BlockStone.VARIANT, BlockStone.EnumStoneVariant.STONE) && block != Blocks.COBBLESTONE && block != Blocks.STONEBRICK);
/*     */   }
/*     */   
/*     */   protected ItemStack u(IBlockData iblockdata) {
/*  29 */     switch ((EnumMonsterEggVarient)iblockdata.get((IBlockState)VARIANT)) {
/*     */       case COBBLESTONE:
/*  31 */         return new ItemStack(Blocks.COBBLESTONE);
/*     */       
/*     */       case STONEBRICK:
/*  34 */         return new ItemStack(Blocks.STONEBRICK);
/*     */       
/*     */       case MOSSY_STONEBRICK:
/*  37 */         return new ItemStack(Blocks.STONEBRICK, 1, BlockSmoothBrick.EnumStonebrickType.MOSSY.a());
/*     */       
/*     */       case CRACKED_STONEBRICK:
/*  40 */         return new ItemStack(Blocks.STONEBRICK, 1, BlockSmoothBrick.EnumStonebrickType.CRACKED.a());
/*     */       
/*     */       case null:
/*  43 */         return new ItemStack(Blocks.STONEBRICK, 1, BlockSmoothBrick.EnumStonebrickType.CHISELED.a());
/*     */     } 
/*     */     
/*  46 */     return new ItemStack(Blocks.STONE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
/*  51 */     if (!world.isClientSide && world.getGameRules().getBoolean("doTileDrops")) {
/*  52 */       EntitySilverfish entitysilverfish = new EntitySilverfish(world);
/*     */       
/*  54 */       entitysilverfish.setPositionRotation(blockposition.getX() + 0.5D, blockposition.getY(), blockposition.getZ() + 0.5D, 0.0F, 0.0F);
/*  55 */       world.addEntity(entitysilverfish, CreatureSpawnEvent.SpawnReason.SILVERFISH_BLOCK);
/*  56 */       entitysilverfish.doSpawnEffect();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  62 */     return new ItemStack(this, 1, iblockdata.getBlock().toLegacyData(iblockdata));
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/*  66 */     return getBlockData().set(VARIANT, EnumMonsterEggVarient.a(i));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/*  70 */     return ((EnumMonsterEggVarient)iblockdata.<EnumMonsterEggVarient>get(VARIANT)).a();
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  74 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT });
/*     */   }
/*     */   
/*     */   public enum EnumMonsterEggVarient
/*     */     implements INamable {
/*  79 */     STONE(0, "stone") {
/*     */       public IBlockData d() {
/*  81 */         return Blocks.STONE.getBlockData().set(BlockStone.VARIANT, BlockStone.EnumStoneVariant.STONE);
/*     */       } },
/*  83 */     COBBLESTONE(1, "cobblestone", "cobble") {
/*     */       public IBlockData d() {
/*  85 */         return Blocks.COBBLESTONE.getBlockData();
/*     */       } },
/*  87 */     STONEBRICK(2, "stone_brick", "brick") {
/*     */       public IBlockData d() {
/*  89 */         return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.EnumStonebrickType.DEFAULT);
/*     */       } },
/*  91 */     MOSSY_STONEBRICK(3, "mossy_brick", "mossybrick") {
/*     */       public IBlockData d() {
/*  93 */         return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.EnumStonebrickType.MOSSY);
/*     */       } },
/*  95 */     CRACKED_STONEBRICK(4, "cracked_brick", "crackedbrick") {
/*     */       public IBlockData d() {
/*  97 */         return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.EnumStonebrickType.CRACKED);
/*     */       } },
/*  99 */     CHISELED_STONEBRICK(5, "chiseled_brick", "chiseledbrick") {
/*     */       public IBlockData d() {
/* 101 */         return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.EnumStonebrickType.CHISELED);
/*     */       }
/*     */     };
/*     */     
/* 105 */     private static final EnumMonsterEggVarient[] g = new EnumMonsterEggVarient[(values()).length];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final int h;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final String i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final String j;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 170 */       EnumMonsterEggVarient[] ablockmonstereggs_enummonstereggvarient = values();
/* 171 */       int i = ablockmonstereggs_enummonstereggvarient.length;
/*     */       
/* 173 */       for (int j = 0; j < i; j++) {
/* 174 */         EnumMonsterEggVarient blockmonstereggs_enummonstereggvarient = ablockmonstereggs_enummonstereggvarient[j];
/*     */         
/* 176 */         g[blockmonstereggs_enummonstereggvarient.a()] = blockmonstereggs_enummonstereggvarient;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumMonsterEggVarient(int i, String s, String s1) {
/*     */       this.h = i;
/*     */       this.i = s;
/*     */       this.j = s1;
/*     */     }
/*     */     
/*     */     public int a() {
/*     */       return this.h;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.i;
/*     */     }
/*     */     
/*     */     public static EnumMonsterEggVarient a(int i) {
/*     */       if (i < 0 || i >= g.length)
/*     */         i = 0; 
/*     */       return g[i];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.i;
/*     */     }
/*     */     
/*     */     public String c() {
/*     */       return this.j;
/*     */     }
/*     */     
/*     */     public static EnumMonsterEggVarient a(IBlockData iblockdata) {
/*     */       EnumMonsterEggVarient[] ablockmonstereggs_enummonstereggvarient = values();
/*     */       int i = ablockmonstereggs_enummonstereggvarient.length;
/*     */       for (int j = 0; j < i; j++) {
/*     */         EnumMonsterEggVarient blockmonstereggs_enummonstereggvarient = ablockmonstereggs_enummonstereggvarient[j];
/*     */         if (iblockdata == blockmonstereggs_enummonstereggvarient.d())
/*     */           return blockmonstereggs_enummonstereggvarient; 
/*     */       } 
/*     */       return STONE;
/*     */     }
/*     */     
/*     */     public abstract IBlockData d();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockMonsterEggs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */