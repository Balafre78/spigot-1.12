/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
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
/*     */ public class CommandClone
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  33 */     return "clone";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  38 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  43 */     return "commands.clone.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*  48 */     if (paramArrayOfString.length < 9) {
/*  49 */       throw new ExceptionUsage("commands.clone.usage", new Object[0]);
/*     */     }
/*  51 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 0);
/*     */     
/*  53 */     BlockPosition blockPosition1 = a(paramICommandListener, paramArrayOfString, 0, false);
/*  54 */     BlockPosition blockPosition2 = a(paramICommandListener, paramArrayOfString, 3, false);
/*  55 */     BlockPosition blockPosition3 = a(paramICommandListener, paramArrayOfString, 6, false);
/*     */     
/*  57 */     StructureBoundingBox structureBoundingBox1 = new StructureBoundingBox(blockPosition1, blockPosition2);
/*  58 */     StructureBoundingBox structureBoundingBox2 = new StructureBoundingBox(blockPosition3, blockPosition3.a(structureBoundingBox1.b()));
/*     */     
/*  60 */     int i = structureBoundingBox1.c() * structureBoundingBox1.d() * structureBoundingBox1.e();
/*  61 */     if (i > 32768) {
/*  62 */       throw new CommandException("commands.clone.tooManyBlocks", new Object[] { Integer.valueOf(i), Integer.valueOf(32768) });
/*     */     }
/*  64 */     boolean bool1 = false;
/*  65 */     Block block = null;
/*  66 */     Predicate<IBlockData> predicate = null;
/*  67 */     if ((paramArrayOfString.length < 11 || (!"force".equals(paramArrayOfString[10]) && !"move".equals(paramArrayOfString[10]))) && structureBoundingBox1.a(structureBoundingBox2))
/*  68 */       throw new CommandException("commands.clone.noOverlap", new Object[0]); 
/*  69 */     if (paramArrayOfString.length >= 11 && "move".equals(paramArrayOfString[10])) {
/*  70 */       bool1 = true;
/*     */     }
/*     */     
/*  73 */     if (structureBoundingBox1.b < 0 || structureBoundingBox1.e >= 256 || structureBoundingBox2.b < 0 || structureBoundingBox2.e >= 256) {
/*  74 */       throw new CommandException("commands.clone.outOfWorld", new Object[0]);
/*     */     }
/*  76 */     World world = paramICommandListener.getWorld();
/*  77 */     if (!world.a(structureBoundingBox1) || !world.a(structureBoundingBox2)) {
/*  78 */       throw new CommandException("commands.clone.outOfWorld", new Object[0]);
/*     */     }
/*     */     
/*  81 */     boolean bool2 = false;
/*  82 */     if (paramArrayOfString.length >= 10) {
/*  83 */       if ("masked".equals(paramArrayOfString[9])) {
/*  84 */         bool2 = true;
/*  85 */       } else if ("filtered".equals(paramArrayOfString[9])) {
/*  86 */         if (paramArrayOfString.length >= 12) {
/*  87 */           block = b(paramICommandListener, paramArrayOfString[11]);
/*     */         } else {
/*  89 */           throw new ExceptionUsage("commands.clone.usage", new Object[0]);
/*     */         } 
/*  91 */         if (paramArrayOfString.length >= 13) {
/*  92 */           predicate = b(block, paramArrayOfString[12]);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  97 */     ArrayList<CommandCloneStoredTileEntity> arrayList1 = Lists.newArrayList();
/*  98 */     ArrayList<CommandCloneStoredTileEntity> arrayList2 = Lists.newArrayList();
/*  99 */     ArrayList<CommandCloneStoredTileEntity> arrayList3 = Lists.newArrayList();
/* 100 */     LinkedList<BlockPosition> linkedList = Lists.newLinkedList();
/*     */     
/* 102 */     BlockPosition blockPosition4 = new BlockPosition(structureBoundingBox2.a - structureBoundingBox1.a, structureBoundingBox2.b - structureBoundingBox1.b, structureBoundingBox2.c - structureBoundingBox1.c);
/* 103 */     for (int j = structureBoundingBox1.c; j <= structureBoundingBox1.f; j++) {
/* 104 */       for (int k = structureBoundingBox1.b; k <= structureBoundingBox1.e; k++) {
/* 105 */         for (int m = structureBoundingBox1.a; m <= structureBoundingBox1.d; m++) {
/* 106 */           BlockPosition blockPosition5 = new BlockPosition(m, k, j);
/* 107 */           BlockPosition blockPosition6 = blockPosition5.a(blockPosition4);
/*     */           
/* 109 */           IBlockData iBlockData = world.getType(blockPosition5);
/* 110 */           if (bool2 && iBlockData.getBlock() == Blocks.AIR)
/*     */             continue; 
/* 112 */           if (block != null) {
/* 113 */             if (iBlockData.getBlock() != block) {
/*     */               continue;
/*     */             }
/* 116 */             if (predicate != null && !predicate.apply(iBlockData)) {
/*     */               continue;
/*     */             }
/*     */           } 
/*     */           
/* 121 */           TileEntity tileEntity = world.getTileEntity(blockPosition5);
/* 122 */           if (tileEntity != null) {
/* 123 */             NBTTagCompound nBTTagCompound = tileEntity.save(new NBTTagCompound());
/* 124 */             arrayList2.add(new CommandCloneStoredTileEntity(blockPosition6, iBlockData, nBTTagCompound));
/* 125 */             linkedList.addLast(blockPosition5);
/* 126 */           } else if (iBlockData.b() || iBlockData.g()) {
/* 127 */             arrayList1.add(new CommandCloneStoredTileEntity(blockPosition6, iBlockData, null));
/* 128 */             linkedList.addLast(blockPosition5);
/*     */           } else {
/* 130 */             arrayList3.add(new CommandCloneStoredTileEntity(blockPosition6, iBlockData, null));
/* 131 */             linkedList.addFirst(blockPosition5);
/*     */           } 
/*     */           continue;
/*     */         } 
/*     */       } 
/*     */     } 
/* 137 */     if (bool1) {
/* 138 */       for (BlockPosition blockPosition : linkedList) {
/* 139 */         TileEntity tileEntity = world.getTileEntity(blockPosition);
/* 140 */         if (tileEntity instanceof IInventory) {
/* 141 */           ((IInventory)tileEntity).clear();
/*     */         }
/* 143 */         world.setTypeAndData(blockPosition, Blocks.BARRIER.getBlockData(), 2);
/*     */       } 
/* 145 */       for (BlockPosition blockPosition : linkedList) {
/* 146 */         world.setTypeAndData(blockPosition, Blocks.AIR.getBlockData(), 3);
/*     */       }
/*     */     } 
/*     */     
/* 150 */     ArrayList<CommandCloneStoredTileEntity> arrayList4 = Lists.newArrayList();
/* 151 */     arrayList4.addAll(arrayList1);
/* 152 */     arrayList4.addAll(arrayList2);
/* 153 */     arrayList4.addAll(arrayList3);
/*     */     
/* 155 */     List list = Lists.reverse(arrayList4);
/* 156 */     for (CommandCloneStoredTileEntity commandCloneStoredTileEntity : list) {
/* 157 */       TileEntity tileEntity = world.getTileEntity(commandCloneStoredTileEntity.a);
/* 158 */       if (tileEntity instanceof IInventory) {
/* 159 */         ((IInventory)tileEntity).clear();
/*     */       }
/* 161 */       world.setTypeAndData(commandCloneStoredTileEntity.a, Blocks.BARRIER.getBlockData(), 2);
/*     */     } 
/*     */     
/* 164 */     i = 0;
/* 165 */     for (CommandCloneStoredTileEntity commandCloneStoredTileEntity : arrayList4) {
/* 166 */       if (world.setTypeAndData(commandCloneStoredTileEntity.a, commandCloneStoredTileEntity.b, 2)) {
/* 167 */         i++;
/*     */       }
/*     */     } 
/* 170 */     for (CommandCloneStoredTileEntity commandCloneStoredTileEntity : arrayList2) {
/* 171 */       TileEntity tileEntity = world.getTileEntity(commandCloneStoredTileEntity.a);
/* 172 */       if (commandCloneStoredTileEntity.c != null && tileEntity != null) {
/* 173 */         commandCloneStoredTileEntity.c.setInt("x", commandCloneStoredTileEntity.a.getX());
/* 174 */         commandCloneStoredTileEntity.c.setInt("y", commandCloneStoredTileEntity.a.getY());
/* 175 */         commandCloneStoredTileEntity.c.setInt("z", commandCloneStoredTileEntity.a.getZ());
/* 176 */         tileEntity.a(commandCloneStoredTileEntity.c);
/* 177 */         tileEntity.update();
/*     */       } 
/* 179 */       world.setTypeAndData(commandCloneStoredTileEntity.a, commandCloneStoredTileEntity.b, 2);
/*     */     } 
/*     */     
/* 182 */     for (CommandCloneStoredTileEntity commandCloneStoredTileEntity : list) {
/* 183 */       world.update(commandCloneStoredTileEntity.a, commandCloneStoredTileEntity.b.getBlock(), false);
/*     */     }
/*     */     
/* 186 */     List<NextTickListEntry> list1 = world.a(structureBoundingBox1, false);
/* 187 */     if (list1 != null) {
/* 188 */       for (NextTickListEntry nextTickListEntry : list1) {
/* 189 */         if (structureBoundingBox1.b(nextTickListEntry.a)) {
/* 190 */           BlockPosition blockPosition = nextTickListEntry.a.a(blockPosition4);
/* 191 */           world.b(blockPosition, nextTickListEntry.a(), (int)(nextTickListEntry.b - world.getWorldData().getTime()), nextTickListEntry.c);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 196 */     if (i <= 0) {
/* 197 */       throw new CommandException("commands.clone.failed", new Object[0]);
/*     */     }
/* 199 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, i);
/* 200 */     a(paramICommandListener, this, "commands.clone.success", new Object[] { Integer.valueOf(i) });
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 205 */     if (paramArrayOfString.length > 0 && paramArrayOfString.length <= 3)
/* 206 */       return a(paramArrayOfString, 0, paramBlockPosition); 
/* 207 */     if (paramArrayOfString.length > 3 && paramArrayOfString.length <= 6)
/* 208 */       return a(paramArrayOfString, 3, paramBlockPosition); 
/* 209 */     if (paramArrayOfString.length > 6 && paramArrayOfString.length <= 9)
/* 210 */       return a(paramArrayOfString, 6, paramBlockPosition); 
/* 211 */     if (paramArrayOfString.length == 10)
/* 212 */       return a(paramArrayOfString, new String[] { "replace", "masked", "filtered" }); 
/* 213 */     if (paramArrayOfString.length == 11)
/* 214 */       return a(paramArrayOfString, new String[] { "normal", "force", "move" }); 
/* 215 */     if (paramArrayOfString.length == 12 && "filtered".equals(paramArrayOfString[9])) {
/* 216 */       return a(paramArrayOfString, Block.REGISTRY.keySet());
/*     */     }
/* 218 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   static class CommandCloneStoredTileEntity {
/*     */     public final BlockPosition a;
/*     */     public final IBlockData b;
/*     */     public final NBTTagCompound c;
/*     */     
/*     */     public CommandCloneStoredTileEntity(BlockPosition param1BlockPosition, IBlockData param1IBlockData, NBTTagCompound param1NBTTagCompound) {
/* 227 */       this.a = param1BlockPosition;
/* 228 */       this.b = param1IBlockData;
/* 229 */       this.c = param1NBTTagCompound;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandClone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */