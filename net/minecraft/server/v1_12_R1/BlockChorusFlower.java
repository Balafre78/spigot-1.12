/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockChorusFlower
/*     */   extends Block
/*     */ {
/*  11 */   public static final BlockStateInteger AGE = BlockStateInteger.of("age", 0, 5);
/*     */   
/*     */   protected BlockChorusFlower() {
/*  14 */     super(Material.PLANT, MaterialMapColor.A);
/*  15 */     w(this.blockStateList.getBlockData().set(AGE, Integer.valueOf(0)));
/*  16 */     a(CreativeModeTab.c);
/*  17 */     a(true);
/*     */   }
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/*  21 */     return Items.a;
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  25 */     if (!b(world, blockposition)) {
/*  26 */       world.setAir(blockposition, true);
/*     */     } else {
/*  28 */       BlockPosition blockposition1 = blockposition.up();
/*     */       
/*  30 */       if (world.isEmpty(blockposition1) && blockposition1.getY() < 256) {
/*  31 */         int i = ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */         
/*  33 */         if (i < 5 && random.nextInt(1) == 0) {
/*  34 */           boolean flag = false;
/*  35 */           boolean flag1 = false;
/*  36 */           IBlockData iblockdata1 = world.getType(blockposition.down());
/*  37 */           Block block = iblockdata1.getBlock();
/*     */ 
/*     */           
/*  40 */           if (block == Blocks.END_STONE) {
/*  41 */             flag = true;
/*  42 */           } else if (block == Blocks.CHORUS_PLANT) {
/*  43 */             int j = 1;
/*     */             
/*     */             int k;
/*     */             
/*  47 */             for (k = 0; k < 4; k++) {
/*  48 */               Block block1 = world.getType(blockposition.down(j + 1)).getBlock();
/*     */               
/*  50 */               if (block1 != Blocks.CHORUS_PLANT) {
/*  51 */                 if (block1 == Blocks.END_STONE) {
/*  52 */                   flag1 = true;
/*     */                 }
/*     */                 
/*     */                 break;
/*     */               } 
/*  57 */               j++;
/*     */             } 
/*     */             
/*  60 */             k = 4;
/*  61 */             if (flag1) {
/*  62 */               k++;
/*     */             }
/*     */             
/*  65 */             if (j < 2 || random.nextInt(k) >= j) {
/*  66 */               flag = true;
/*     */             }
/*  68 */           } else if (iblockdata1.getMaterial() == Material.AIR) {
/*  69 */             flag = true;
/*     */           } 
/*     */           
/*  72 */           if (flag && a(world, blockposition1, (EnumDirection)null) && world.isEmpty(blockposition.up(2))) {
/*     */ 
/*     */ 
/*     */             
/*  76 */             BlockPosition target = blockposition1;
/*  77 */             if (CraftEventFactory.handleBlockSpreadEvent(
/*  78 */                 world.getWorld().getBlockAt(target.getX(), target.getY(), target.getZ()), 
/*  79 */                 world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), 
/*  80 */                 this, 
/*  81 */                 toLegacyData(getBlockData().set(AGE, Integer.valueOf(i)))))
/*     */             {
/*  83 */               world.setTypeAndData(blockposition, Blocks.CHORUS_PLANT.getBlockData(), 2);
/*  84 */               world.triggerEffect(1033, blockposition, 0);
/*     */             }
/*     */           
/*  87 */           } else if (i < 4) {
/*  88 */             int j = random.nextInt(4);
/*  89 */             boolean flag2 = false;
/*     */             
/*  91 */             if (flag1) {
/*  92 */               j++;
/*     */             }
/*     */             
/*  95 */             for (int l = 0; l < j; l++) {
/*  96 */               EnumDirection enumdirection = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(random);
/*  97 */               BlockPosition blockposition2 = blockposition.shift(enumdirection);
/*     */               
/*  99 */               if (world.isEmpty(blockposition2) && world.isEmpty(blockposition2.down()) && a(world, blockposition2, enumdirection.opposite())) {
/*     */ 
/*     */                 
/* 102 */                 BlockPosition target = blockposition2;
/* 103 */                 if (CraftEventFactory.handleBlockSpreadEvent(
/* 104 */                     world.getWorld().getBlockAt(target.getX(), target.getY(), target.getZ()), 
/* 105 */                     world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), 
/* 106 */                     this, 
/* 107 */                     toLegacyData(getBlockData().set(AGE, Integer.valueOf(i + 1))))) {
/*     */                   
/* 109 */                   world.triggerEffect(1033, blockposition, 0);
/* 110 */                   flag2 = true;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 116 */             if (flag2) {
/* 117 */               world.setTypeAndData(blockposition, Blocks.CHORUS_PLANT.getBlockData(), 2);
/*     */             
/*     */             }
/* 120 */             else if (CraftEventFactory.handleBlockGrowEvent(
/* 121 */                 world, 
/* 122 */                 blockposition.getX(), 
/* 123 */                 blockposition.getY(), 
/* 124 */                 blockposition.getZ(), 
/* 125 */                 this, 
/* 126 */                 toLegacyData(iblockdata.set(AGE, Integer.valueOf(5))))) {
/*     */               
/* 128 */               world.triggerEffect(1034, blockposition, 0);
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 133 */           else if (i == 4) {
/*     */             
/* 135 */             if (CraftEventFactory.handleBlockGrowEvent(
/* 136 */                 world, 
/* 137 */                 blockposition.getX(), 
/* 138 */                 blockposition.getY(), 
/* 139 */                 blockposition.getZ(), 
/* 140 */                 this, 
/* 141 */                 toLegacyData(iblockdata.set(AGE, Integer.valueOf(5)))))
/*     */             {
/* 143 */               world.triggerEffect(1034, blockposition, 0);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, int i) {
/* 155 */     world.setTypeAndData(blockposition, getBlockData().set(AGE, Integer.valueOf(i)), 2);
/* 156 */     world.triggerEffect(1033, blockposition, 0);
/*     */   }
/*     */   
/*     */   private void c(World world, BlockPosition blockposition) {
/* 160 */     world.setTypeAndData(blockposition, getBlockData().set(AGE, Integer.valueOf(5)), 2);
/* 161 */     world.triggerEffect(1034, blockposition, 0);
/*     */   }
/*     */   private static boolean a(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*     */     EnumDirection enumdirection1;
/* 165 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 170 */       if (!iterator.hasNext()) {
/* 171 */         return true;
/*     */       }
/*     */       
/* 174 */       enumdirection1 = iterator.next();
/* 175 */     } while (enumdirection1 == enumdirection || world.isEmpty(blockposition.shift(enumdirection1)));
/*     */     
/* 177 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/* 181 */     return false;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/* 185 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/* 189 */     return (super.canPlace(world, blockposition) && b(world, blockposition));
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/* 193 */     if (!b(world, blockposition)) {
/* 194 */       world.a(blockposition, this, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(World world, BlockPosition blockposition) {
/* 200 */     IBlockData iblockdata = world.getType(blockposition.down());
/* 201 */     Block block = iblockdata.getBlock();
/*     */     
/* 203 */     if (block != Blocks.CHORUS_PLANT && block != Blocks.END_STONE) {
/* 204 */       if (iblockdata.getMaterial() == Material.AIR) {
/* 205 */         int i = 0;
/* 206 */         Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */         
/* 208 */         while (iterator.hasNext()) {
/* 209 */           EnumDirection enumdirection = iterator.next();
/* 210 */           IBlockData iblockdata1 = world.getType(blockposition.shift(enumdirection));
/* 211 */           Block block1 = iblockdata1.getBlock();
/*     */           
/* 213 */           if (block1 == Blocks.CHORUS_PLANT) {
/* 214 */             i++; continue;
/* 215 */           }  if (iblockdata1.getMaterial() != Material.AIR) {
/* 216 */             return false;
/*     */           }
/*     */         } 
/*     */         
/* 220 */         return (i == 1);
/*     */       } 
/* 222 */       return false;
/*     */     } 
/*     */     
/* 225 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, @Nullable TileEntity tileentity, ItemStack itemstack) {
/* 230 */     super.a(world, entityhuman, blockposition, iblockdata, tileentity, itemstack);
/* 231 */     a(world, blockposition, new ItemStack(Item.getItemOf(this)));
/*     */   }
/*     */   
/*     */   protected ItemStack u(IBlockData iblockdata) {
/* 235 */     return ItemStack.a;
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 239 */     return getBlockData().set(AGE, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 243 */     return ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 247 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { AGE });
/*     */   }
/*     */   
/*     */   public static void a(World world, BlockPosition blockposition, Random random, int i) {
/* 251 */     world.setTypeAndData(blockposition, Blocks.CHORUS_PLANT.getBlockData(), 2);
/* 252 */     a(world, blockposition, random, blockposition, i, 0);
/*     */   }
/*     */   
/*     */   private static void a(World world, BlockPosition blockposition, Random random, BlockPosition blockposition1, int i, int j) {
/* 256 */     int k = random.nextInt(4) + 1;
/*     */     
/* 258 */     if (j == 0) {
/* 259 */       k++;
/*     */     }
/*     */     
/* 262 */     for (int l = 0; l < k; l++) {
/* 263 */       BlockPosition blockposition2 = blockposition.up(l + 1);
/*     */       
/* 265 */       if (!a(world, blockposition2, (EnumDirection)null)) {
/*     */         return;
/*     */       }
/*     */       
/* 269 */       world.setTypeAndData(blockposition2, Blocks.CHORUS_PLANT.getBlockData(), 2);
/*     */     } 
/*     */     
/* 272 */     boolean flag = false;
/*     */     
/* 274 */     if (j < 4) {
/* 275 */       int i1 = random.nextInt(4);
/*     */       
/* 277 */       if (j == 0) {
/* 278 */         i1++;
/*     */       }
/*     */       
/* 281 */       for (int j1 = 0; j1 < i1; j1++) {
/* 282 */         EnumDirection enumdirection = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(random);
/* 283 */         BlockPosition blockposition3 = blockposition.up(k).shift(enumdirection);
/*     */         
/* 285 */         if (Math.abs(blockposition3.getX() - blockposition1.getX()) < i && Math.abs(blockposition3.getZ() - blockposition1.getZ()) < i && world.isEmpty(blockposition3) && world.isEmpty(blockposition3.down()) && a(world, blockposition3, enumdirection.opposite())) {
/* 286 */           flag = true;
/* 287 */           world.setTypeAndData(blockposition3, Blocks.CHORUS_PLANT.getBlockData(), 2);
/* 288 */           a(world, blockposition3, random, blockposition1, i, j + 1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 293 */     if (!flag) {
/* 294 */       world.setTypeAndData(blockposition.up(k), Blocks.CHORUS_FLOWER.getBlockData().set(AGE, Integer.valueOf(5)), 2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 300 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockChorusFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */