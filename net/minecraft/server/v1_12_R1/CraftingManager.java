/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.nio.file.FileSystem;
/*     */ import java.nio.file.FileSystems;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CraftingManager
/*     */ {
/*  29 */   private static final Logger b = LogManager.getLogger();
/*     */   private static int c;
/*  31 */   public static RegistryMaterials<MinecraftKey, IRecipe> recipes = new RegistryMaterials<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean init() {
/*     */     try {
/*  37 */       c = 0;
/*  38 */       a("armordye", new RecipeArmorDye());
/*  39 */       a("bookcloning", new RecipeBookClone());
/*  40 */       a("mapcloning", new RecipeMapClone());
/*  41 */       a("mapextending", new RecipeMapExtend());
/*  42 */       a("fireworks", new RecipeFireworks());
/*  43 */       a("repairitem", new RecipeRepair());
/*  44 */       a("tippedarrow", new RecipeTippedArrow());
/*  45 */       a("bannerduplicate", new RecipesBanner.DuplicateRecipe());
/*  46 */       a("banneraddpattern", new RecipesBanner.AddRecipe());
/*  47 */       a("shielddecoration", new RecipiesShield.Decoration());
/*  48 */       a("shulkerboxcoloring", new RecipeShulkerBox.Dye());
/*  49 */       return b();
/*  50 */     } catch (Throwable throwable) {
/*  51 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean b() {
/*  56 */     FileSystem filesystem = null;
/*  57 */     Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
/*     */ 
/*     */ 
/*     */     
/*     */     try { Path java_nio_file_path;
/*     */       
/*  63 */       URL url = CraftingManager.class.getResource("/assets/.mcassetsroot");
/*     */       
/*  65 */       if (url == null) {
/*  66 */         b.error("Couldn't find .mcassetsroot");
/*  67 */         boolean flag = false;
/*  68 */         return flag;
/*     */       } 
/*     */       
/*  71 */       URI uri = url.toURI();
/*     */ 
/*     */       
/*  74 */       if ("file".equals(uri.getScheme())) {
/*  75 */         java_nio_file_path = Paths.get(CraftingManager.class.getResource("/assets/minecraft/recipes").toURI());
/*     */       } else {
/*  77 */         if (!"jar".equals(uri.getScheme())) {
/*  78 */           b.error("Unsupported scheme " + uri + " trying to list all recipes");
/*  79 */           boolean flag1 = false;
/*     */           
/*  81 */           return flag1;
/*     */         } 
/*     */         
/*  84 */         filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
/*  85 */         java_nio_file_path = filesystem.getPath("/assets/minecraft/recipes", new String[0]);
/*     */       } 
/*     */       
/*  88 */       Iterator<Path> iterator = Files.walk(java_nio_file_path, new java.nio.file.FileVisitOption[0]).iterator();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */        }
/*     */     
/* 119 */     catch (IOException|java.net.URISyntaxException urisyntaxexception)
/* 120 */     { b.error("Couldn't get a list of all recipe files", urisyntaxexception);
/* 121 */       boolean flag = false;
/* 122 */       return flag; }
/*     */     finally
/*     */     
/* 125 */     { IOUtils.closeQuietly(filesystem); }  IOUtils.closeQuietly(filesystem);
/*     */ 
/*     */     
/* 128 */     return true;
/*     */   }
/*     */   
/*     */   private static IRecipe a(JsonObject jsonobject) {
/* 132 */     String s = ChatDeserializer.h(jsonobject, "type");
/*     */     
/* 134 */     if ("crafting_shaped".equals(s))
/* 135 */       return ShapedRecipes.a(jsonobject); 
/* 136 */     if ("crafting_shapeless".equals(s)) {
/* 137 */       return ShapelessRecipes.a(jsonobject);
/*     */     }
/* 139 */     throw new JsonSyntaxException("Invalid or unsupported recipe type '" + s + "'");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(String s, IRecipe irecipe) {
/* 144 */     a(new MinecraftKey(s), irecipe);
/*     */   }
/*     */   
/*     */   public static void a(MinecraftKey minecraftkey, IRecipe irecipe) {
/* 148 */     if (recipes.d(minecraftkey)) {
/* 149 */       throw new IllegalStateException("Duplicate recipe ignored with ID " + minecraftkey);
/*     */     }
/* 151 */     irecipe.setKey(minecraftkey);
/* 152 */     recipes.a(c++, minecraftkey, irecipe);
/*     */   }
/*     */   
/*     */   public static ItemStack craft(InventoryCrafting inventorycrafting, World world) {
/*     */     IRecipe irecipe;
/* 157 */     Iterator<IRecipe> iterator = recipes.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 162 */       if (!iterator.hasNext()) {
/* 163 */         inventorycrafting.currentRecipe = null;
/* 164 */         return ItemStack.a;
/*     */       } 
/*     */       
/* 167 */       irecipe = iterator.next();
/* 168 */     } while (!irecipe.a(inventorycrafting, world));
/*     */     
/* 170 */     inventorycrafting.currentRecipe = irecipe;
/* 171 */     return irecipe.craftItem(inventorycrafting);
/*     */   }
/*     */   @Nullable
/*     */   public static IRecipe b(InventoryCrafting inventorycrafting, World world) {
/*     */     IRecipe irecipe;
/* 176 */     Iterator<IRecipe> iterator = recipes.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 181 */       if (!iterator.hasNext()) {
/* 182 */         inventorycrafting.currentRecipe = null;
/* 183 */         return null;
/*     */       } 
/*     */       
/* 186 */       irecipe = iterator.next();
/* 187 */     } while (!irecipe.a(inventorycrafting, world));
/*     */     
/* 189 */     inventorycrafting.currentRecipe = irecipe;
/* 190 */     return irecipe;
/*     */   }
/*     */   
/*     */   public static NonNullList<ItemStack> c(InventoryCrafting inventorycrafting, World world) {
/* 194 */     Iterator<IRecipe> iterator = recipes.iterator();
/*     */     
/* 196 */     while (iterator.hasNext()) {
/* 197 */       IRecipe irecipe = iterator.next();
/*     */       
/* 199 */       if (irecipe.a(inventorycrafting, world)) {
/* 200 */         return irecipe.b(inventorycrafting);
/*     */       }
/*     */     } 
/*     */     
/* 204 */     NonNullList<ItemStack> nonnulllist = NonNullList.a(inventorycrafting.getSize(), ItemStack.a);
/*     */     
/* 206 */     for (int i = 0; i < nonnulllist.size(); i++) {
/* 207 */       nonnulllist.set(i, inventorycrafting.getItem(i));
/*     */     }
/*     */     
/* 210 */     return nonnulllist;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static IRecipe a(MinecraftKey minecraftkey) {
/* 215 */     return recipes.get(minecraftkey);
/*     */   }
/*     */   
/*     */   public static int a(IRecipe irecipe) {
/* 219 */     return recipes.a(irecipe);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static IRecipe a(int i) {
/* 224 */     return recipes.getId(i);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CraftingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */