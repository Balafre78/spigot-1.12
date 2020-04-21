/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.util.BlockStateListPopulator;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ 
/*     */ public class BlockPumpkin
/*     */   extends BlockFacingHorizontal
/*     */ {
/*     */   private ShapeDetector snowGolemPart;
/*     */   private ShapeDetector snowGolem;
/*     */   private ShapeDetector ironGolemPart;
/*     */   private ShapeDetector ironGolem;
/*     */   
/*  19 */   private static final Predicate<IBlockData> e = new Predicate() {
/*     */       public boolean a(@Nullable IBlockData iblockdata) {
/*  21 */         return (iblockdata != null && (iblockdata.getBlock() == Blocks.PUMPKIN || iblockdata.getBlock() == Blocks.LIT_PUMPKIN));
/*     */       }
/*     */       
/*     */       public boolean apply(@Nullable Object object) {
/*  25 */         return a((IBlockData)object);
/*     */       }
/*     */     };
/*     */   
/*     */   protected BlockPumpkin() {
/*  30 */     super(Material.PUMPKIN, MaterialMapColor.r);
/*  31 */     w(this.blockStateList.getBlockData().set(FACING, EnumDirection.NORTH));
/*  32 */     a(true);
/*  33 */     a(CreativeModeTab.b);
/*     */   }
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  37 */     super.onPlace(world, blockposition, iblockdata);
/*  38 */     c(world, blockposition);
/*     */   }
/*     */   
/*     */   public boolean b(World world, BlockPosition blockposition) {
/*  42 */     return !(getDetectorSnowGolemPart().a(world, blockposition) == null && getDetectorIronGolemPart().a(world, blockposition) == null);
/*     */   }
/*     */   
/*     */   private void c(World world, BlockPosition blockposition) {
/*  46 */     ShapeDetector.ShapeDetectorCollection shapedetector_shapedetectorcollection = getDetectorSnowGolem().a(world, blockposition);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     BlockStateListPopulator blockList = new BlockStateListPopulator((World)world.getWorld());
/*  53 */     if (shapedetector_shapedetectorcollection != null) {
/*  54 */       for (int i = 0; i < getDetectorSnowGolem().b(); i++) {
/*  55 */         ShapeDetectorBlock shapedetectorblock = shapedetector_shapedetectorcollection.a(0, i, 0);
/*     */ 
/*     */ 
/*     */         
/*  59 */         BlockPosition pos = shapedetectorblock.getPosition();
/*  60 */         blockList.setTypeId(pos.getX(), pos.getY(), pos.getZ(), 0);
/*     */       } 
/*     */ 
/*     */       
/*  64 */       EntitySnowman entitysnowman = new EntitySnowman(world);
/*  65 */       BlockPosition blockposition1 = shapedetector_shapedetectorcollection.a(0, 2, 0).getPosition();
/*     */       
/*  67 */       entitysnowman.setPositionRotation(blockposition1.getX() + 0.5D, blockposition1.getY() + 0.05D, blockposition1.getZ() + 0.5D, 0.0F, 0.0F);
/*     */       
/*  69 */       if (world.addEntity(entitysnowman, CreatureSpawnEvent.SpawnReason.BUILD_SNOWMAN)) {
/*  70 */         blockList.updateList();
/*  71 */         Iterator<EntityPlayer> iterator = world.<EntityPlayer>a(EntityPlayer.class, entitysnowman.getBoundingBox().g(5.0D)).iterator();
/*     */         
/*  73 */         while (iterator.hasNext()) {
/*  74 */           EntityPlayer entityplayer = iterator.next();
/*  75 */           CriterionTriggers.m.a(entityplayer, entitysnowman);
/*     */         } 
/*     */         int j;
/*  78 */         for (j = 0; j < 120; j++) {
/*  79 */           world.addParticle(EnumParticle.SNOW_SHOVEL, blockposition1.getX() + world.random.nextDouble(), blockposition1.getY() + world.random.nextDouble() * 2.5D, blockposition1.getZ() + world.random.nextDouble(), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         }
/*     */         
/*  82 */         for (j = 0; j < getDetectorSnowGolem().b(); j++) {
/*  83 */           ShapeDetectorBlock shapedetectorblock1 = shapedetector_shapedetectorcollection.a(0, j, 0);
/*     */           
/*  85 */           world.update(shapedetectorblock1.getPosition(), Blocks.AIR, false);
/*     */         } 
/*     */       } 
/*     */     } else {
/*  89 */       shapedetector_shapedetectorcollection = getDetectorIronGolem().a(world, blockposition);
/*  90 */       if (shapedetector_shapedetectorcollection != null) {
/*  91 */         for (int i = 0; i < getDetectorIronGolem().c(); i++) {
/*  92 */           for (int k = 0; k < getDetectorIronGolem().b(); k++) {
/*     */ 
/*     */             
/*  95 */             BlockPosition pos = shapedetector_shapedetectorcollection.a(i, k, 0).getPosition();
/*  96 */             blockList.setTypeId(pos.getX(), pos.getY(), pos.getZ(), 0);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 101 */         BlockPosition blockposition2 = shapedetector_shapedetectorcollection.a(1, 2, 0).getPosition();
/* 102 */         EntityIronGolem entityirongolem = new EntityIronGolem(world);
/*     */         
/* 104 */         entityirongolem.setPlayerCreated(true);
/* 105 */         entityirongolem.setPositionRotation(blockposition2.getX() + 0.5D, blockposition2.getY() + 0.05D, blockposition2.getZ() + 0.5D, 0.0F, 0.0F);
/*     */         
/* 107 */         if (world.addEntity(entityirongolem, CreatureSpawnEvent.SpawnReason.BUILD_IRONGOLEM)) {
/* 108 */           blockList.updateList();
/* 109 */           Iterator<EntityPlayer> iterator = world.<EntityPlayer>a(EntityPlayer.class, entityirongolem.getBoundingBox().g(5.0D)).iterator();
/*     */           
/* 111 */           while (iterator.hasNext()) {
/* 112 */             EntityPlayer entityplayer = iterator.next();
/* 113 */             CriterionTriggers.m.a(entityplayer, entityirongolem);
/*     */           } 
/*     */           int j;
/* 116 */           for (j = 0; j < 120; j++) {
/* 117 */             world.addParticle(EnumParticle.SNOWBALL, blockposition2.getX() + world.random.nextDouble(), blockposition2.getY() + world.random.nextDouble() * 3.9D, blockposition2.getZ() + world.random.nextDouble(), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */           }
/*     */           
/* 120 */           for (j = 0; j < getDetectorIronGolem().c(); j++) {
/* 121 */             for (int l = 0; l < getDetectorIronGolem().b(); l++) {
/* 122 */               ShapeDetectorBlock shapedetectorblock2 = shapedetector_shapedetectorcollection.a(j, l, 0);
/*     */               
/* 124 */               world.update(shapedetectorblock2.getPosition(), Blocks.AIR, false);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/* 134 */     return ((world.getType(blockposition).getBlock()).material.isReplaceable() && world.getType(blockposition.down()).q());
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 138 */     return iblockdata.set(FACING, enumblockrotation.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 142 */     return iblockdata.a(enumblockmirror.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/* 146 */     return getBlockData().set(FACING, entityliving.getDirection().opposite());
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 150 */     return getBlockData().set(FACING, EnumDirection.fromType2(i));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 154 */     return ((EnumDirection)iblockdata.<EnumDirection>get(FACING)).get2DRotationValue();
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 158 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING });
/*     */   }
/*     */   
/*     */   protected ShapeDetector getDetectorSnowGolemPart() {
/* 162 */     if (this.snowGolemPart == null) {
/* 163 */       this.snowGolemPart = ShapeDetectorBuilder.a().a(new String[] { " ", "#", "#" }).a('#', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.SNOW))).b();
/*     */     }
/*     */     
/* 166 */     return this.snowGolemPart;
/*     */   }
/*     */   
/*     */   protected ShapeDetector getDetectorSnowGolem() {
/* 170 */     if (this.snowGolem == null) {
/* 171 */       this.snowGolem = ShapeDetectorBuilder.a().a(new String[] { "^", "#", "#" }).a('^', ShapeDetectorBlock.a(e)).a('#', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.SNOW))).b();
/*     */     }
/*     */     
/* 174 */     return this.snowGolem;
/*     */   }
/*     */   
/*     */   protected ShapeDetector getDetectorIronGolemPart() {
/* 178 */     if (this.ironGolemPart == null) {
/* 179 */       this.ironGolemPart = ShapeDetectorBuilder.a().a(new String[] { "~ ~", "###", "~#~" }).a('#', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.IRON_BLOCK))).a('~', ShapeDetectorBlock.a(MaterialPredicate.a(Material.AIR))).b();
/*     */     }
/*     */     
/* 182 */     return this.ironGolemPart;
/*     */   }
/*     */   
/*     */   protected ShapeDetector getDetectorIronGolem() {
/* 186 */     if (this.ironGolem == null) {
/* 187 */       this.ironGolem = ShapeDetectorBuilder.a().a(new String[] { "~^~", "###", "~#~" }).a('^', ShapeDetectorBlock.a(e)).a('#', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.IRON_BLOCK))).a('~', ShapeDetectorBlock.a(MaterialPredicate.a(Material.AIR))).b();
/*     */     }
/*     */     
/* 190 */     return this.ironGolem;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPumpkin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */