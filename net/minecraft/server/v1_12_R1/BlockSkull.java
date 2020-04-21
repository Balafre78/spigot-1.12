/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.util.BlockStateListPopulator;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ 
/*     */ public class BlockSkull
/*     */   extends BlockTileEntity
/*     */ {
/*  15 */   public static final BlockStateDirection FACING = BlockDirectional.FACING;
/*  16 */   public static final BlockStateBoolean NODROP = BlockStateBoolean.of("nodrop");
/*  17 */   private static final Predicate<ShapeDetectorBlock> B = new Predicate() {
/*     */       public boolean a(@Nullable ShapeDetectorBlock shapedetectorblock) {
/*  19 */         return (shapedetectorblock.a() != null && shapedetectorblock.a().getBlock() == Blocks.SKULL && shapedetectorblock.b() instanceof TileEntitySkull && ((TileEntitySkull)shapedetectorblock.b()).getSkullType() == 1);
/*     */       }
/*     */       
/*     */       public boolean apply(@Nullable Object object) {
/*  23 */         return a((ShapeDetectorBlock)object);
/*     */       }
/*     */     };
/*  26 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
/*  27 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D);
/*  28 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D);
/*  29 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
/*  30 */   protected static final AxisAlignedBB g = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D);
/*     */   private ShapeDetector C;
/*     */   private ShapeDetector D;
/*     */   
/*     */   protected BlockSkull() {
/*  35 */     super(Material.ORIENTABLE);
/*  36 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).set(NODROP, Boolean.valueOf(false)));
/*     */   }
/*     */   
/*     */   public String getName() {
/*  40 */     return LocaleI18n.get("tile.skull.skeleton.name");
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  44 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  48 */     return false;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  52 */     switch ((EnumDirection)iblockdata.get(FACING)) {
/*     */       
/*     */       default:
/*  55 */         return c;
/*     */       
/*     */       case NORTH:
/*  58 */         return d;
/*     */       
/*     */       case SOUTH:
/*  61 */         return e;
/*     */       
/*     */       case WEST:
/*  64 */         return f;
/*     */       case EAST:
/*     */         break;
/*  67 */     }  return g;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/*  72 */     return getBlockData().<Comparable, EnumDirection>set(FACING, entityliving.getDirection()).set(NODROP, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public TileEntity a(World world, int i) {
/*  76 */     return new TileEntitySkull();
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  80 */     int i = 0;
/*  81 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/*  83 */     if (tileentity instanceof TileEntitySkull) {
/*  84 */       i = ((TileEntitySkull)tileentity).getSkullType();
/*     */     }
/*     */     
/*  87 */     return new ItemStack(Items.SKULL, 1, i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
/*  93 */     if (world.random.nextFloat() < f) {
/*  94 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/*  96 */       if (tileentity instanceof TileEntitySkull) {
/*  97 */         TileEntitySkull tileentityskull = (TileEntitySkull)tileentity;
/*  98 */         ItemStack itemstack = a(world, blockposition, iblockdata);
/*     */         
/* 100 */         if (tileentityskull.getSkullType() == 3 && tileentityskull.getGameProfile() != null) {
/* 101 */           itemstack.setTag(new NBTTagCompound());
/* 102 */           NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */           
/* 104 */           GameProfileSerializer.serialize(nbttagcompound, tileentityskull.getGameProfile());
/* 105 */           itemstack.getTag().set("SkullOwner", nbttagcompound);
/*     */         } 
/*     */         
/* 108 */         a(world, blockposition, itemstack);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/* 115 */     if (entityhuman.abilities.canInstantlyBuild) {
/* 116 */       iblockdata = iblockdata.set(NODROP, Boolean.valueOf(true));
/* 117 */       world.setTypeAndData(blockposition, iblockdata, 4);
/*     */     } 
/*     */     
/* 120 */     super.a(world, blockposition, iblockdata, entityhuman);
/*     */   }
/*     */   
/*     */   public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 124 */     if (!world.isClientSide)
/*     */     {
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 147 */       super.remove(world, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 152 */     return Items.SKULL;
/*     */   }
/*     */   
/*     */   public boolean b(World world, BlockPosition blockposition, ItemStack itemstack) {
/* 156 */     return (itemstack.getData() == 1 && blockposition.getY() >= 2 && world.getDifficulty() != EnumDifficulty.PEACEFUL && !world.isClientSide) ? ((e().a(world, blockposition) != null)) : false;
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, TileEntitySkull tileentityskull) {
/* 160 */     if (world.captureBlockStates)
/* 161 */       return;  if (tileentityskull.getSkullType() == 1 && blockposition.getY() >= 2 && world.getDifficulty() != EnumDifficulty.PEACEFUL && !world.isClientSide) {
/* 162 */       ShapeDetector shapedetector = g();
/* 163 */       ShapeDetector.ShapeDetectorCollection shapedetector_shapedetectorcollection = shapedetector.a(world, blockposition);
/*     */       
/* 165 */       if (shapedetector_shapedetectorcollection != null) {
/*     */         
/* 167 */         BlockStateListPopulator blockList = new BlockStateListPopulator((World)world.getWorld());
/*     */         
/*     */         int i;
/* 170 */         for (i = 0; i < 3; i++) {
/* 171 */           ShapeDetectorBlock shapedetectorblock = shapedetector_shapedetectorcollection.a(i, 0, 0);
/*     */ 
/*     */ 
/*     */           
/* 175 */           BlockPosition pos = shapedetectorblock.getPosition();
/* 176 */           IBlockData data = shapedetectorblock.a().set(NODROP, Boolean.valueOf(true));
/* 177 */           blockList.setTypeAndData(pos.getX(), pos.getY(), pos.getZ(), data.getBlock(), data.getBlock().toLegacyData(data), 2);
/*     */         } 
/*     */ 
/*     */         
/* 181 */         for (i = 0; i < shapedetector.c(); i++) {
/* 182 */           for (int j = 0; j < shapedetector.b(); j++) {
/* 183 */             ShapeDetectorBlock shapedetectorblock1 = shapedetector_shapedetectorcollection.a(i, j, 0);
/*     */ 
/*     */ 
/*     */             
/* 187 */             BlockPosition pos = shapedetectorblock1.getPosition();
/* 188 */             blockList.setTypeAndData(pos.getX(), pos.getY(), pos.getZ(), Blocks.AIR, 0, 2);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 193 */         BlockPosition blockposition1 = shapedetector_shapedetectorcollection.a(1, 0, 0).getPosition();
/* 194 */         EntityWither entitywither = new EntityWither(world);
/* 195 */         BlockPosition blockposition2 = shapedetector_shapedetectorcollection.a(1, 2, 0).getPosition();
/*     */         
/* 197 */         entitywither.setPositionRotation(blockposition2.getX() + 0.5D, blockposition2.getY() + 0.55D, blockposition2.getZ() + 0.5D, (shapedetector_shapedetectorcollection.getFacing().k() == EnumDirection.EnumAxis.X) ? 0.0F : 90.0F, 0.0F);
/* 198 */         entitywither.aN = (shapedetector_shapedetectorcollection.getFacing().k() == EnumDirection.EnumAxis.X) ? 0.0F : 90.0F;
/* 199 */         entitywither.p();
/* 200 */         Iterator<EntityPlayer> iterator = world.<EntityPlayer>a(EntityPlayer.class, entitywither.getBoundingBox().g(50.0D)).iterator();
/*     */ 
/*     */         
/* 203 */         if (world.addEntity(entitywither, CreatureSpawnEvent.SpawnReason.BUILD_WITHER)) {
/* 204 */           blockList.updateList();
/*     */           
/* 206 */           while (iterator.hasNext()) {
/* 207 */             EntityPlayer entityplayer = iterator.next();
/*     */             
/* 209 */             CriterionTriggers.m.a(entityplayer, entitywither);
/*     */           } 
/*     */           
/*     */           int k;
/*     */           
/* 214 */           for (k = 0; k < 120; k++) {
/* 215 */             world.addParticle(EnumParticle.SNOWBALL, blockposition1.getX() + world.random.nextDouble(), (blockposition1.getY() - 2) + world.random.nextDouble() * 3.9D, blockposition1.getZ() + world.random.nextDouble(), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */           }
/*     */           
/* 218 */           for (k = 0; k < shapedetector.c(); k++) {
/* 219 */             for (int l = 0; l < shapedetector.b(); l++) {
/* 220 */               ShapeDetectorBlock shapedetectorblock2 = shapedetector_shapedetectorcollection.a(k, l, 0);
/*     */               
/* 222 */               world.update(shapedetectorblock2.getPosition(), Blocks.AIR, false);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 232 */     return getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.fromType1(i & 0x7)).set(NODROP, Boolean.valueOf(((i & 0x8) > 0)));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 236 */     byte b0 = 0;
/* 237 */     int i = b0 | ((EnumDirection)iblockdata.<EnumDirection>get(FACING)).a();
/*     */     
/* 239 */     if (((Boolean)iblockdata.<Boolean>get(NODROP)).booleanValue()) {
/* 240 */       i |= 0x8;
/*     */     }
/*     */     
/* 243 */     return i;
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 247 */     return iblockdata.set(FACING, enumblockrotation.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 251 */     return iblockdata.a(enumblockmirror.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 255 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, NODROP });
/*     */   }
/*     */   
/*     */   protected ShapeDetector e() {
/* 259 */     if (this.C == null) {
/* 260 */       this.C = ShapeDetectorBuilder.a().a(new String[] { "   ", "###", "~#~" }).a('#', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.SOUL_SAND))).a('~', ShapeDetectorBlock.a(MaterialPredicate.a(Material.AIR))).b();
/*     */     }
/*     */     
/* 263 */     return this.C;
/*     */   }
/*     */   
/*     */   protected ShapeDetector g() {
/* 267 */     if (this.D == null) {
/* 268 */       this.D = ShapeDetectorBuilder.a().a(new String[] { "^^^", "###", "~#~" }).a('#', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.SOUL_SAND))).a('^', B).a('~', ShapeDetectorBlock.a(MaterialPredicate.a(Material.AIR))).b();
/*     */     }
/*     */     
/* 271 */     return this.D;
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 275 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */