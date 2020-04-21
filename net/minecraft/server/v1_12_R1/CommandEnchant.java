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
/*     */ public class CommandEnchant
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  23 */     return "enchant";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  28 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  33 */     return "commands.enchant.usage";
/*     */   }
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*     */     Enchantment enchantment;
/*  38 */     if (paramArrayOfString.length < 2) {
/*  39 */       throw new ExceptionUsage("commands.enchant.usage", new Object[0]);
/*     */     }
/*     */     
/*  42 */     EntityLiving entityLiving = a(paramMinecraftServer, paramICommandListener, paramArrayOfString[0], EntityLiving.class);
/*  43 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ITEMS, 0);
/*     */ 
/*     */     
/*     */     try {
/*  47 */       enchantment = Enchantment.c(a(paramArrayOfString[1], 0));
/*  48 */     } catch (ExceptionInvalidNumber exceptionInvalidNumber) {
/*  49 */       enchantment = Enchantment.b(paramArrayOfString[1]);
/*     */     } 
/*  51 */     if (enchantment == null) {
/*  52 */       throw new ExceptionInvalidNumber("commands.enchant.notFound", new Object[] { paramArrayOfString[1] });
/*     */     }
/*     */     
/*  55 */     int i = 1;
/*     */     
/*  57 */     ItemStack itemStack = entityLiving.getItemInMainHand();
/*  58 */     if (itemStack.isEmpty()) {
/*  59 */       throw new CommandException("commands.enchant.noItem", new Object[0]);
/*     */     }
/*     */     
/*  62 */     if (!enchantment.canEnchant(itemStack)) {
/*  63 */       throw new CommandException("commands.enchant.cantEnchant", new Object[0]);
/*     */     }
/*     */     
/*  66 */     if (paramArrayOfString.length >= 3) {
/*  67 */       i = a(paramArrayOfString[2], enchantment.getStartLevel(), enchantment.getMaxLevel());
/*     */     }
/*     */     
/*  70 */     if (itemStack.hasTag()) {
/*  71 */       NBTTagList nBTTagList = itemStack.getEnchantments();
/*  72 */       for (byte b = 0; b < nBTTagList.size(); b++) {
/*  73 */         short s = nBTTagList.get(b).getShort("id");
/*     */         
/*  75 */         if (Enchantment.c(s) != null) {
/*  76 */           Enchantment enchantment1 = Enchantment.c(s);
/*  77 */           if (!enchantment.c(enchantment1)) {
/*  78 */             throw new CommandException("commands.enchant.cantCombine", new Object[] { enchantment.d(i), enchantment1.d(nBTTagList.get(b).getShort("lvl")) });
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  84 */     itemStack.addEnchantment(enchantment, i);
/*  85 */     a(paramICommandListener, this, "commands.enchant.success", new Object[0]);
/*  86 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ITEMS, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/*  91 */     if (paramArrayOfString.length == 1) {
/*  92 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*     */     }
/*  94 */     if (paramArrayOfString.length == 2) {
/*  95 */       return a(paramArrayOfString, Enchantment.enchantments.keySet());
/*     */     }
/*     */     
/*  98 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 103 */     return (paramInt == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandEnchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */