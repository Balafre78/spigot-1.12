/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public abstract class BlockFluids extends Block {
/*   9 */   public static final BlockStateInteger LEVEL = BlockStateInteger.of("level", 0, 15);
/*     */   
/*     */   protected BlockFluids(Material material) {
/*  12 */     super(material);
/*  13 */     w(this.blockStateList.getBlockData().set(LEVEL, Integer.valueOf(0)));
/*  14 */     a(true);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  18 */     return j;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  23 */     return k;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  27 */     return (this.material != Material.LAVA);
/*     */   }
/*     */   
/*     */   public static float b(int i) {
/*  31 */     if (i >= 8) {
/*  32 */       i = 0;
/*     */     }
/*     */     
/*  35 */     return (i + 1) / 9.0F;
/*     */   }
/*     */   
/*     */   protected int x(IBlockData iblockdata) {
/*  39 */     return (iblockdata.getMaterial() == this.material) ? ((Integer)iblockdata.<Integer>get(LEVEL)).intValue() : -1;
/*     */   }
/*     */   
/*     */   protected int y(IBlockData iblockdata) {
/*  43 */     int i = x(iblockdata);
/*     */     
/*  45 */     return (i >= 8) ? 0 : i;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  49 */     return false;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  53 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a(IBlockData iblockdata, boolean flag) {
/*  57 */     return (flag && ((Integer)iblockdata.<Integer>get(LEVEL)).intValue() == 0);
/*     */   }
/*     */   
/*     */   private boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  61 */     IBlockData iblockdata = iblockaccess.getType(blockposition);
/*  62 */     Block block = iblockdata.getBlock();
/*  63 */     Material material = iblockdata.getMaterial();
/*     */     
/*  65 */     if (material == this.material)
/*  66 */       return false; 
/*  67 */     if (enumdirection == EnumDirection.UP)
/*  68 */       return true; 
/*  69 */     if (material == Material.ICE) {
/*  70 */       return false;
/*     */     }
/*  72 */     boolean flag = !(!c(block) && !(block instanceof BlockStairs));
/*     */     
/*  74 */     return (!flag && iblockdata.d(iblockaccess, blockposition, enumdirection) == EnumBlockFaceShape.SOLID);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType a(IBlockData iblockdata) {
/*  79 */     return EnumRenderType.LIQUID;
/*     */   }
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/*  83 */     return Items.a;
/*     */   }
/*     */   
/*     */   public int a(Random random) {
/*  87 */     return 0;
/*     */   }
/*     */   
/*     */   protected Vec3D a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata) {
/*  91 */     double d0 = 0.0D;
/*  92 */     double d1 = 0.0D;
/*  93 */     double d2 = 0.0D;
/*  94 */     int i = y(iblockdata);
/*  95 */     BlockPosition.PooledBlockPosition blockposition_pooledblockposition = BlockPosition.PooledBlockPosition.s();
/*  96 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/*  98 */     while (iterator.hasNext()) {
/*  99 */       EnumDirection enumdirection = iterator.next();
/*     */       
/* 101 */       blockposition_pooledblockposition.j(blockposition).d(enumdirection);
/* 102 */       int j = y(iblockaccess.getType(blockposition_pooledblockposition));
/*     */ 
/*     */       
/* 105 */       if (j < 0) {
/* 106 */         if (!iblockaccess.getType(blockposition_pooledblockposition).getMaterial().isSolid()) {
/* 107 */           j = y(iblockaccess.getType(blockposition_pooledblockposition.down()));
/* 108 */           if (j >= 0) {
/* 109 */             int k = j - i - 8;
/* 110 */             d0 += (enumdirection.getAdjacentX() * k);
/* 111 */             d1 += (enumdirection.getAdjacentY() * k);
/* 112 */             d2 += (enumdirection.getAdjacentZ() * k);
/*     */           } 
/*     */         }  continue;
/* 115 */       }  if (j >= 0) {
/* 116 */         int k = j - i;
/* 117 */         d0 += (enumdirection.getAdjacentX() * k);
/* 118 */         d1 += (enumdirection.getAdjacentY() * k);
/* 119 */         d2 += (enumdirection.getAdjacentZ() * k);
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     Vec3D vec3d = new Vec3D(d0, d1, d2);
/*     */     
/* 125 */     if (((Integer)iblockdata.get(LEVEL)).intValue() >= 8) {
/* 126 */       Iterator<EnumDirection> iterator1 = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */       
/* 128 */       while (iterator1.hasNext()) {
/* 129 */         EnumDirection enumdirection1 = iterator1.next();
/*     */         
/* 131 */         blockposition_pooledblockposition.j(blockposition).d(enumdirection1);
/* 132 */         if (a(iblockaccess, blockposition_pooledblockposition, enumdirection1) || a(iblockaccess, blockposition_pooledblockposition.up(), enumdirection1)) {
/* 133 */           vec3d = vec3d.a().add(0.0D, -6.0D, 0.0D);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 139 */     blockposition_pooledblockposition.t();
/* 140 */     return vec3d.a();
/*     */   }
/*     */   
/*     */   public Vec3D a(World world, BlockPosition blockposition, Entity entity, Vec3D vec3d) {
/* 144 */     return vec3d.e(a(world, blockposition, world.getType(blockposition)));
/*     */   }
/*     */   
/*     */   public int a(World world) {
/* 148 */     return (this.material == Material.WATER) ? 5 : ((this.material == Material.LAVA) ? (world.worldProvider.n() ? 10 : 30) : 0);
/*     */   }
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 152 */     e(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/* 156 */     e(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public boolean e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 160 */     if (this.material == Material.LAVA) {
/* 161 */       boolean flag = false;
/* 162 */       EnumDirection[] aenumdirection = EnumDirection.values();
/* 163 */       int i = aenumdirection.length;
/*     */       
/* 165 */       for (int j = 0; j < i; j++) {
/* 166 */         EnumDirection enumdirection = aenumdirection[j];
/*     */         
/* 168 */         if (enumdirection != EnumDirection.DOWN && world.getType(blockposition.shift(enumdirection)).getMaterial() == Material.WATER) {
/* 169 */           flag = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 174 */       if (flag) {
/* 175 */         Integer integer = iblockdata.<Integer>get(LEVEL);
/*     */         
/* 177 */         if (integer.intValue() == 0) {
/*     */           
/* 179 */           if (CraftEventFactory.handleBlockFormEvent(world, blockposition, Blocks.OBSIDIAN.getBlockData(), null)) {
/* 180 */             fizz(world, blockposition);
/*     */           }
/*     */           
/* 183 */           return true;
/*     */         } 
/*     */         
/* 186 */         if (integer.intValue() <= 4) {
/*     */           
/* 188 */           if (CraftEventFactory.handleBlockFormEvent(world, blockposition, Blocks.COBBLESTONE.getBlockData(), null)) {
/* 189 */             fizz(world, blockposition);
/*     */           }
/*     */           
/* 192 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 197 */     return false;
/*     */   }
/*     */   
/*     */   protected void fizz(World world, BlockPosition blockposition) {
/* 201 */     double d0 = blockposition.getX();
/* 202 */     double d1 = blockposition.getY();
/* 203 */     double d2 = blockposition.getZ();
/*     */     
/* 205 */     world.a(null, blockposition, SoundEffects.dE, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
/*     */     
/* 207 */     for (int i = 0; i < 8; i++) {
/* 208 */       world.addParticle(EnumParticle.SMOKE_LARGE, d0 + Math.random(), d1 + 1.2D, d2 + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 214 */     return getBlockData().set(LEVEL, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 218 */     return ((Integer)iblockdata.<Integer>get(LEVEL)).intValue();
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 222 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { LEVEL });
/*     */   }
/*     */   
/*     */   public static BlockFlowing a(Material material) {
/* 226 */     if (material == Material.WATER)
/* 227 */       return Blocks.FLOWING_WATER; 
/* 228 */     if (material == Material.LAVA) {
/* 229 */       return Blocks.FLOWING_LAVA;
/*     */     }
/* 231 */     throw new IllegalArgumentException("Invalid material");
/*     */   }
/*     */ 
/*     */   
/*     */   public static BlockStationary b(Material material) {
/* 236 */     if (material == Material.WATER)
/* 237 */       return Blocks.WATER; 
/* 238 */     if (material == Material.LAVA) {
/* 239 */       return Blocks.LAVA;
/*     */     }
/* 241 */     throw new IllegalArgumentException("Invalid material");
/*     */   }
/*     */ 
/*     */   
/*     */   public static float g(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 246 */     int i = ((Integer)iblockdata.<Integer>get(LEVEL)).intValue();
/*     */     
/* 248 */     return ((i & 0x7) == 0 && iblockaccess.getType(blockposition.up()).getMaterial() == Material.WATER) ? 1.0F : (1.0F - b(i));
/*     */   }
/*     */   
/*     */   public static float h(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 252 */     return blockposition.getY() + g(iblockdata, iblockaccess, blockposition);
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 256 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockFluids.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */