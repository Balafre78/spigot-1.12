/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class CommandFill
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  31 */     return "fill";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  36 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  41 */     return "commands.fill.usage";
/*     */   }
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*     */     IBlockData iBlockData;
/*  46 */     if (paramArrayOfString.length < 7) {
/*  47 */       throw new ExceptionUsage("commands.fill.usage", new Object[0]);
/*     */     }
/*  49 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 0);
/*     */     
/*  51 */     BlockPosition blockPosition1 = a(paramICommandListener, paramArrayOfString, 0, false);
/*  52 */     BlockPosition blockPosition2 = a(paramICommandListener, paramArrayOfString, 3, false);
/*  53 */     Block block = CommandAbstract.b(paramICommandListener, paramArrayOfString[6]);
/*     */ 
/*     */     
/*  56 */     if (paramArrayOfString.length >= 8) {
/*  57 */       iBlockData = a(block, paramArrayOfString[7]);
/*     */     } else {
/*  59 */       iBlockData = block.getBlockData();
/*     */     } 
/*     */     
/*  62 */     BlockPosition blockPosition3 = new BlockPosition(Math.min(blockPosition1.getX(), blockPosition2.getX()), Math.min(blockPosition1.getY(), blockPosition2.getY()), Math.min(blockPosition1.getZ(), blockPosition2.getZ()));
/*  63 */     BlockPosition blockPosition4 = new BlockPosition(Math.max(blockPosition1.getX(), blockPosition2.getX()), Math.max(blockPosition1.getY(), blockPosition2.getY()), Math.max(blockPosition1.getZ(), blockPosition2.getZ()));
/*     */     
/*  65 */     int i = (blockPosition4.getX() - blockPosition3.getX() + 1) * (blockPosition4.getY() - blockPosition3.getY() + 1) * (blockPosition4.getZ() - blockPosition3.getZ() + 1);
/*  66 */     if (i > 32768) {
/*  67 */       throw new CommandException("commands.fill.tooManyBlocks", new Object[] { Integer.valueOf(i), Integer.valueOf(32768) });
/*     */     }
/*     */     
/*  70 */     if (blockPosition3.getY() < 0 || blockPosition4.getY() >= 256) {
/*  71 */       throw new CommandException("commands.fill.outOfWorld", new Object[0]);
/*     */     }
/*     */     
/*  74 */     World world = paramICommandListener.getWorld();
/*  75 */     for (int j = blockPosition3.getZ(); j <= blockPosition4.getZ(); j += 16) {
/*  76 */       for (int m = blockPosition3.getX(); m <= blockPosition4.getX(); m += 16) {
/*  77 */         if (!world.isLoaded(new BlockPosition(m, blockPosition4.getY() - blockPosition3.getY(), j))) {
/*  78 */           throw new CommandException("commands.fill.outOfWorld", new Object[0]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  83 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*  84 */     boolean bool = false;
/*  85 */     if (paramArrayOfString.length >= 10 && block.isTileEntity()) {
/*  86 */       String str = a(paramArrayOfString, 9);
/*     */       try {
/*  88 */         nBTTagCompound = MojangsonParser.parse(str);
/*  89 */         bool = true;
/*  90 */       } catch (MojangsonParseException mojangsonParseException) {
/*  91 */         throw new CommandException("commands.fill.tagError", new Object[] { mojangsonParseException.getMessage() });
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     ArrayList<BlockPosition> arrayList = Lists.newArrayList();
/*     */     
/*  97 */     i = 0;
/*  98 */     for (int k = blockPosition3.getZ(); k <= blockPosition4.getZ(); k++) {
/*  99 */       for (int m = blockPosition3.getY(); m <= blockPosition4.getY(); m++) {
/* 100 */         for (int n = blockPosition3.getX(); n <= blockPosition4.getX(); n++) {
/* 101 */           BlockPosition blockPosition = new BlockPosition(n, m, k);
/*     */           
/* 103 */           if (paramArrayOfString.length >= 9) {
/* 104 */             if ("outline".equals(paramArrayOfString[8]) || "hollow".equals(paramArrayOfString[8])) {
/* 105 */               if (n != blockPosition3.getX() && n != blockPosition4.getX() && m != blockPosition3.getY() && m != blockPosition4.getY() && k != blockPosition3.getZ() && k != blockPosition4.getZ()) {
/* 106 */                 if ("hollow".equals(paramArrayOfString[8])) {
/* 107 */                   world.setTypeAndData(blockPosition, Blocks.AIR.getBlockData(), 2);
/* 108 */                   arrayList.add(blockPosition);
/*     */                 } 
/*     */                 continue;
/*     */               } 
/* 112 */             } else if ("destroy".equals(paramArrayOfString[8])) {
/* 113 */               world.setAir(blockPosition, true);
/* 114 */             } else if ("keep".equals(paramArrayOfString[8])) {
/* 115 */               if (!world.isEmpty(blockPosition)) {
/*     */                 continue;
/*     */               }
/* 118 */             } else if ("replace".equals(paramArrayOfString[8]) && !block.isTileEntity() && 
/* 119 */               paramArrayOfString.length > 9) {
/* 120 */               Block block1 = CommandAbstract.b(paramICommandListener, paramArrayOfString[9]);
/* 121 */               if (world.getType(blockPosition).getBlock() != block1) {
/*     */                 continue;
/*     */               }
/* 124 */               if (paramArrayOfString.length > 10 && !"-1".equals(paramArrayOfString[10]) && !"*".equals(paramArrayOfString[10]) && !CommandAbstract.b(block1, paramArrayOfString[10]).apply(world.getType(blockPosition))) {
/*     */                 continue;
/*     */               }
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/* 131 */           TileEntity tileEntity = world.getTileEntity(blockPosition);
/* 132 */           if (tileEntity != null && 
/* 133 */             tileEntity instanceof IInventory) {
/* 134 */             ((IInventory)tileEntity).clear();
/*     */           }
/*     */ 
/*     */           
/* 138 */           if (world.setTypeAndData(blockPosition, iBlockData, 2)) {
/*     */ 
/*     */             
/* 141 */             arrayList.add(blockPosition);
/* 142 */             i++;
/*     */             
/* 144 */             if (bool) {
/* 145 */               TileEntity tileEntity1 = world.getTileEntity(blockPosition);
/* 146 */               if (tileEntity1 != null) {
/* 147 */                 nBTTagCompound.setInt("x", blockPosition.getX());
/* 148 */                 nBTTagCompound.setInt("y", blockPosition.getY());
/* 149 */                 nBTTagCompound.setInt("z", blockPosition.getZ());
/*     */                 
/* 151 */                 tileEntity1.a(nBTTagCompound);
/*     */               } 
/*     */             } 
/*     */           }  continue;
/*     */         } 
/*     */       } 
/*     */     } 
/* 158 */     for (BlockPosition blockPosition : arrayList) {
/* 159 */       Block block1 = world.getType(blockPosition).getBlock();
/* 160 */       world.update(blockPosition, block1, false);
/*     */     } 
/*     */     
/* 163 */     if (i <= 0) {
/* 164 */       throw new CommandException("commands.fill.failed", new Object[0]);
/*     */     }
/* 166 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, i);
/* 167 */     a(paramICommandListener, this, "commands.fill.success", new Object[] { Integer.valueOf(i) });
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 172 */     if (paramArrayOfString.length > 0 && paramArrayOfString.length <= 3)
/* 173 */       return a(paramArrayOfString, 0, paramBlockPosition); 
/* 174 */     if (paramArrayOfString.length > 3 && paramArrayOfString.length <= 6)
/* 175 */       return a(paramArrayOfString, 3, paramBlockPosition); 
/* 176 */     if (paramArrayOfString.length == 7)
/* 177 */       return a(paramArrayOfString, Block.REGISTRY.keySet()); 
/* 178 */     if (paramArrayOfString.length == 9)
/* 179 */       return a(paramArrayOfString, new String[] { "replace", "destroy", "keep", "hollow", "outline" }); 
/* 180 */     if (paramArrayOfString.length == 10 && "replace".equals(paramArrayOfString[8])) {
/* 181 */       return a(paramArrayOfString, Block.REGISTRY.keySet());
/*     */     }
/*     */     
/* 184 */     return Collections.emptyList();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandFill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */