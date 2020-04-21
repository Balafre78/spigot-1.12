/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandReplaceItem
/*     */   extends CommandAbstract
/*     */ {
/*  34 */   private static final Map<String, Integer> a = Maps.newHashMap();
/*     */   static {
/*     */     byte b;
/*  37 */     for (b = 0; b < 54; b++) {
/*  38 */       a.put("slot.container." + b, Integer.valueOf(b));
/*     */     }
/*  40 */     for (b = 0; b < 9; b++) {
/*  41 */       a.put("slot.hotbar." + b, Integer.valueOf(b));
/*     */     }
/*  43 */     for (b = 0; b < 27; b++) {
/*  44 */       a.put("slot.inventory." + b, Integer.valueOf(9 + b));
/*     */     }
/*  46 */     for (b = 0; b < 27; b++) {
/*  47 */       a.put("slot.enderchest." + b, Integer.valueOf(200 + b));
/*     */     }
/*  49 */     for (b = 0; b < 8; b++) {
/*  50 */       a.put("slot.villager." + b, Integer.valueOf(300 + b));
/*     */     }
/*  52 */     for (b = 0; b < 15; b++) {
/*  53 */       a.put("slot.horse." + b, Integer.valueOf(500 + b));
/*     */     }
/*  55 */     a.put("slot.weapon", Integer.valueOf(98));
/*  56 */     a.put("slot.weapon.mainhand", Integer.valueOf(98));
/*  57 */     a.put("slot.weapon.offhand", Integer.valueOf(99));
/*  58 */     a.put("slot.armor.head", Integer.valueOf(100 + EnumItemSlot.HEAD.b()));
/*  59 */     a.put("slot.armor.chest", Integer.valueOf(100 + EnumItemSlot.CHEST.b()));
/*  60 */     a.put("slot.armor.legs", Integer.valueOf(100 + EnumItemSlot.LEGS.b()));
/*  61 */     a.put("slot.armor.feet", Integer.valueOf(100 + EnumItemSlot.FEET.b()));
/*  62 */     a.put("slot.horse.saddle", Integer.valueOf(400));
/*  63 */     a.put("slot.horse.armor", Integer.valueOf(401));
/*  64 */     a.put("slot.horse.chest", Integer.valueOf(499));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommand() {
/*  69 */     return "replaceitem";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  74 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  79 */     return "commands.replaceitem.usage";
/*     */   } public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*     */     boolean bool1;
/*     */     byte b;
/*     */     Item item;
/*  84 */     if (paramArrayOfString.length < 1) {
/*  85 */       throw new ExceptionUsage("commands.replaceitem.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  89 */     if ("entity".equals(paramArrayOfString[0])) {
/*  90 */       bool1 = false;
/*  91 */     } else if ("block".equals(paramArrayOfString[0])) {
/*  92 */       bool1 = true;
/*     */     } else {
/*  94 */       throw new ExceptionUsage("commands.replaceitem.usage", new Object[0]);
/*     */     } 
/*     */ 
/*     */     
/*  98 */     if (bool1) {
/*  99 */       if (paramArrayOfString.length < 6) {
/* 100 */         throw new ExceptionUsage("commands.replaceitem.block.usage", new Object[0]);
/*     */       }
/* 102 */       b = 4;
/*     */     } else {
/* 104 */       if (paramArrayOfString.length < 4) {
/* 105 */         throw new ExceptionUsage("commands.replaceitem.entity.usage", new Object[0]);
/*     */       }
/* 107 */       b = 2;
/*     */     } 
/*     */     
/* 110 */     String str = paramArrayOfString[b];
/* 111 */     int i = e(paramArrayOfString[b++]);
/*     */     
/*     */     try {
/* 114 */       item = a(paramICommandListener, paramArrayOfString[b]);
/* 115 */     } catch (ExceptionInvalidNumber exceptionInvalidNumber) {
/* 116 */       if (Block.getByName(paramArrayOfString[b]) == Blocks.AIR) {
/* 117 */         item = null;
/*     */       } else {
/* 119 */         throw exceptionInvalidNumber;
/*     */       } 
/*     */     } 
/* 122 */     b++;
/*     */     
/* 124 */     boolean bool2 = (paramArrayOfString.length > b) ? a(paramArrayOfString[b++], 1, item.getMaxStackSize()) : true;
/* 125 */     boolean bool3 = (paramArrayOfString.length > b) ? a(paramArrayOfString[b++]) : false;
/* 126 */     ItemStack itemStack = new ItemStack(item, bool2, bool3);
/* 127 */     if (paramArrayOfString.length > b) {
/* 128 */       String str1 = a(paramArrayOfString, b);
/*     */       try {
/* 130 */         itemStack.setTag(MojangsonParser.parse(str1));
/* 131 */       } catch (MojangsonParseException mojangsonParseException) {
/* 132 */         throw new CommandException("commands.replaceitem.tagError", new Object[] { mojangsonParseException.getMessage() });
/*     */       } 
/*     */     } 
/*     */     
/* 136 */     if (bool1) {
/* 137 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ITEMS, 0);
/* 138 */       BlockPosition blockPosition = a(paramICommandListener, paramArrayOfString, 1, false);
/* 139 */       World world = paramICommandListener.getWorld();
/* 140 */       TileEntity tileEntity = world.getTileEntity(blockPosition);
/* 141 */       if (tileEntity == null || !(tileEntity instanceof IInventory)) {
/* 142 */         throw new CommandException("commands.replaceitem.noContainer", new Object[] { Integer.valueOf(blockPosition.getX()), Integer.valueOf(blockPosition.getY()), Integer.valueOf(blockPosition.getZ()) });
/*     */       }
/* 144 */       IInventory iInventory = (IInventory)tileEntity;
/* 145 */       if (i >= 0 && i < iInventory.getSize()) {
/* 146 */         iInventory.setItem(i, itemStack);
/*     */       }
/*     */     } else {
/* 149 */       Entity entity = c(paramMinecraftServer, paramICommandListener, paramArrayOfString[1]);
/* 150 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ITEMS, 0);
/*     */ 
/*     */ 
/*     */       
/* 154 */       if (entity instanceof EntityHuman) {
/* 155 */         ((EntityHuman)entity).defaultContainer.b();
/*     */       }
/*     */       
/* 158 */       if (!entity.c(i, itemStack)) {
/* 159 */         throw new CommandException("commands.replaceitem.failed", new Object[] { str, Integer.valueOf(bool2), itemStack.isEmpty() ? "Air" : itemStack.C() });
/*     */       }
/*     */ 
/*     */       
/* 163 */       if (entity instanceof EntityHuman) {
/* 164 */         ((EntityHuman)entity).defaultContainer.b();
/*     */       }
/*     */     } 
/*     */     
/* 168 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ITEMS, bool2);
/* 169 */     a(paramICommandListener, this, "commands.replaceitem.success", new Object[] { str, Integer.valueOf(bool2), itemStack.isEmpty() ? "Air" : itemStack.C() });
/*     */   }
/*     */   
/*     */   private int e(String paramString) throws CommandException {
/* 173 */     if (!a.containsKey(paramString)) {
/* 174 */       throw new CommandException("commands.generic.parameter.invalid", new Object[] { paramString });
/*     */     }
/* 176 */     return ((Integer)a.get(paramString)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 181 */     if (paramArrayOfString.length == 1) {
/* 182 */       return a(paramArrayOfString, new String[] { "entity", "block" });
/*     */     }
/* 184 */     if (paramArrayOfString.length == 2 && "entity".equals(paramArrayOfString[0])) {
/* 185 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*     */     }
/* 187 */     if (paramArrayOfString.length >= 2 && paramArrayOfString.length <= 4 && "block".equals(paramArrayOfString[0])) {
/* 188 */       return a(paramArrayOfString, 1, paramBlockPosition);
/*     */     }
/* 190 */     if ((paramArrayOfString.length == 3 && "entity".equals(paramArrayOfString[0])) || (paramArrayOfString.length == 5 && "block".equals(paramArrayOfString[0]))) {
/* 191 */       return a(paramArrayOfString, a.keySet());
/*     */     }
/* 193 */     if ((paramArrayOfString.length == 4 && "entity".equals(paramArrayOfString[0])) || (paramArrayOfString.length == 6 && "block".equals(paramArrayOfString[0]))) {
/* 194 */       return a(paramArrayOfString, Item.REGISTRY.keySet());
/*     */     }
/*     */     
/* 197 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 202 */     return (paramArrayOfString.length > 0 && "entity".equals(paramArrayOfString[0]) && paramInt == 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandReplaceItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */