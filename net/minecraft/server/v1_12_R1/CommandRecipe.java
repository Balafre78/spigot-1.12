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
/*     */ public class CommandRecipe
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  22 */     return "recipe";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  27 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  32 */     return "commands.recipe.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*  37 */     if (paramArrayOfString.length < 2) {
/*  38 */       throw new ExceptionUsage("commands.recipe.usage", new Object[0]);
/*     */     }
/*     */     
/*  41 */     boolean bool1 = "give".equalsIgnoreCase(paramArrayOfString[0]);
/*  42 */     boolean bool2 = "take".equalsIgnoreCase(paramArrayOfString[0]);
/*  43 */     if (!bool1 && !bool2) {
/*  44 */       throw new ExceptionUsage("commands.recipe.usage", new Object[0]);
/*     */     }
/*     */     
/*  47 */     List<EntityPlayer> list = a(paramMinecraftServer, paramICommandListener, paramArrayOfString[1]);
/*     */     
/*  49 */     for (EntityPlayer entityPlayer : list) {
/*  50 */       if ("*".equals(paramArrayOfString[2])) {
/*  51 */         if (bool1) {
/*  52 */           entityPlayer.a(d());
/*  53 */           a(paramICommandListener, this, "commands.recipe.give.success.all", new Object[] { entityPlayer.getName() }); continue;
/*     */         } 
/*  55 */         entityPlayer.b(d());
/*  56 */         a(paramICommandListener, this, "commands.recipe.take.success.all", new Object[] { entityPlayer.getName() });
/*     */         continue;
/*     */       } 
/*  59 */       IRecipe iRecipe = CraftingManager.a(new MinecraftKey(paramArrayOfString[2]));
/*  60 */       if (iRecipe == null)
/*  61 */         throw new CommandException("commands.recipe.unknownrecipe", new Object[] { paramArrayOfString[2] }); 
/*  62 */       if (iRecipe.c()) {
/*  63 */         throw new CommandException("commands.recipe.unsupported", new Object[] { paramArrayOfString[2] });
/*     */       }
/*     */       
/*  66 */       ArrayList<IRecipe> arrayList = Lists.newArrayList((Object[])new IRecipe[] { iRecipe });
/*     */       
/*  68 */       if (bool1 == entityPlayer.F().b(iRecipe)) {
/*  69 */         String str = bool1 ? "commands.recipe.alreadyHave" : "commands.recipe.dontHave";
/*  70 */         throw new CommandException(str, new Object[] { entityPlayer.getName(), iRecipe.b().getName() });
/*     */       } 
/*     */       
/*  73 */       if (bool1) {
/*  74 */         entityPlayer.a(arrayList);
/*  75 */         a(paramICommandListener, this, "commands.recipe.give.success.one", new Object[] { entityPlayer.getName(), iRecipe.b().getName() }); continue;
/*     */       } 
/*  77 */       entityPlayer.b(arrayList);
/*  78 */       a(paramICommandListener, this, "commands.recipe.take.success.one", new Object[] { iRecipe.b().getName(), entityPlayer.getName() });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private List<IRecipe> d() {
/*  85 */     return Lists.newArrayList(CraftingManager.recipes);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/*  90 */     if (paramArrayOfString.length == 1) {
/*  91 */       return a(paramArrayOfString, new String[] { "give", "take" });
/*     */     }
/*     */     
/*  94 */     if (paramArrayOfString.length == 2) {
/*  95 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*     */     }
/*     */     
/*  98 */     if (paramArrayOfString.length == 3) {
/*  99 */       return a(paramArrayOfString, CraftingManager.recipes.keySet());
/*     */     }
/*     */     
/* 102 */     return Collections.emptyList();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */