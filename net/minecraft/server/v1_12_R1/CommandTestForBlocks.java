/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
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
/*     */ public class CommandTestForBlocks
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  27 */     return "testforblocks";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  32 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  37 */     return "commands.compare.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*  42 */     if (paramArrayOfString.length < 9) {
/*  43 */       throw new ExceptionUsage("commands.compare.usage", new Object[0]);
/*     */     }
/*  45 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 0);
/*     */     
/*  47 */     BlockPosition blockPosition1 = a(paramICommandListener, paramArrayOfString, 0, false);
/*  48 */     BlockPosition blockPosition2 = a(paramICommandListener, paramArrayOfString, 3, false);
/*  49 */     BlockPosition blockPosition3 = a(paramICommandListener, paramArrayOfString, 6, false);
/*     */     
/*  51 */     StructureBoundingBox structureBoundingBox1 = new StructureBoundingBox(blockPosition1, blockPosition2);
/*  52 */     StructureBoundingBox structureBoundingBox2 = new StructureBoundingBox(blockPosition3, blockPosition3.a(structureBoundingBox1.b()));
/*     */     
/*  54 */     int i = structureBoundingBox1.c() * structureBoundingBox1.d() * structureBoundingBox1.e();
/*  55 */     if (i > 524288) {
/*  56 */       throw new CommandException("commands.compare.tooManyBlocks", new Object[] { Integer.valueOf(i), Integer.valueOf(524288) });
/*     */     }
/*  58 */     if (structureBoundingBox1.b < 0 || structureBoundingBox1.e >= 256 || structureBoundingBox2.b < 0 || structureBoundingBox2.e >= 256) {
/*  59 */       throw new CommandException("commands.compare.outOfWorld", new Object[0]);
/*     */     }
/*  61 */     World world = paramICommandListener.getWorld();
/*  62 */     if (!world.a(structureBoundingBox1) || !world.a(structureBoundingBox2)) {
/*  63 */       throw new CommandException("commands.compare.outOfWorld", new Object[0]);
/*     */     }
/*     */     
/*  66 */     boolean bool = false;
/*  67 */     if (paramArrayOfString.length > 9 && 
/*  68 */       "masked".equals(paramArrayOfString[9])) {
/*  69 */       bool = true;
/*     */     }
/*     */ 
/*     */     
/*  73 */     i = 0;
/*  74 */     BlockPosition blockPosition4 = new BlockPosition(structureBoundingBox2.a - structureBoundingBox1.a, structureBoundingBox2.b - structureBoundingBox1.b, structureBoundingBox2.c - structureBoundingBox1.c);
/*  75 */     BlockPosition.MutableBlockPosition mutableBlockPosition1 = new BlockPosition.MutableBlockPosition();
/*  76 */     BlockPosition.MutableBlockPosition mutableBlockPosition2 = new BlockPosition.MutableBlockPosition();
/*     */     
/*  78 */     for (int j = structureBoundingBox1.c; j <= structureBoundingBox1.f; j++) {
/*  79 */       for (int k = structureBoundingBox1.b; k <= structureBoundingBox1.e; k++) {
/*  80 */         for (int m = structureBoundingBox1.a; m <= structureBoundingBox1.d; m++) {
/*  81 */           mutableBlockPosition1.c(m, k, j);
/*  82 */           mutableBlockPosition2.c(m + blockPosition4.getX(), k + blockPosition4.getY(), j + blockPosition4.getZ());
/*     */           
/*  84 */           boolean bool1 = false;
/*  85 */           IBlockData iBlockData = world.getType(mutableBlockPosition1);
/*  86 */           if (!bool || iBlockData.getBlock() != Blocks.AIR) {
/*     */ 
/*     */             
/*  89 */             if (iBlockData == world.getType(mutableBlockPosition2)) {
/*  90 */               TileEntity tileEntity1 = world.getTileEntity(mutableBlockPosition1);
/*  91 */               TileEntity tileEntity2 = world.getTileEntity(mutableBlockPosition2);
/*  92 */               if (tileEntity1 != null && tileEntity2 != null) {
/*  93 */                 NBTTagCompound nBTTagCompound1 = tileEntity1.save(new NBTTagCompound());
/*  94 */                 nBTTagCompound1.remove("x");
/*  95 */                 nBTTagCompound1.remove("y");
/*  96 */                 nBTTagCompound1.remove("z");
/*     */                 
/*  98 */                 NBTTagCompound nBTTagCompound2 = tileEntity2.save(new NBTTagCompound());
/*  99 */                 nBTTagCompound2.remove("x");
/* 100 */                 nBTTagCompound2.remove("y");
/* 101 */                 nBTTagCompound2.remove("z");
/*     */                 
/* 103 */                 if (!nBTTagCompound1.equals(nBTTagCompound2)) {
/* 104 */                   bool1 = true;
/*     */                 }
/* 106 */               } else if (tileEntity1 != null) {
/* 107 */                 bool1 = true;
/*     */               } 
/*     */             } else {
/* 110 */               bool1 = true;
/*     */             } 
/*     */             
/* 113 */             i++;
/* 114 */             if (bool1) {
/* 115 */               throw new CommandException("commands.compare.failed", new Object[0]);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 121 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, i);
/* 122 */     a(paramICommandListener, this, "commands.compare.success", new Object[] { Integer.valueOf(i) });
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 127 */     if (paramArrayOfString.length > 0 && paramArrayOfString.length <= 3)
/* 128 */       return a(paramArrayOfString, 0, paramBlockPosition); 
/* 129 */     if (paramArrayOfString.length > 3 && paramArrayOfString.length <= 6)
/* 130 */       return a(paramArrayOfString, 3, paramBlockPosition); 
/* 131 */     if (paramArrayOfString.length > 6 && paramArrayOfString.length <= 9)
/* 132 */       return a(paramArrayOfString, 6, paramBlockPosition); 
/* 133 */     if (paramArrayOfString.length == 10) {
/* 134 */       return a(paramArrayOfString, new String[] { "masked", "all" });
/*     */     }
/*     */     
/* 137 */     return Collections.emptyList();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandTestForBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */