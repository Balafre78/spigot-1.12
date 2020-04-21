/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.ArrayList;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemKnowledgeBook
/*    */   extends Item
/*    */ {
/* 23 */   private static final Logger a = LogManager.getLogger();
/*    */   
/*    */   public ItemKnowledgeBook() {
/* 26 */     d(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 31 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 32 */     NBTTagCompound nBTTagCompound = itemStack.getTag();
/*    */     
/* 34 */     if (!paramEntityHuman.abilities.canInstantlyBuild) {
/* 35 */       paramEntityHuman.a(paramEnumHand, ItemStack.a);
/*    */     }
/*    */     
/* 38 */     if (nBTTagCompound == null || !nBTTagCompound.hasKeyOfType("Recipes", 9)) {
/* 39 */       a.error("Tag not valid: " + nBTTagCompound);
/* 40 */       return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemStack);
/*    */     } 
/*    */     
/* 43 */     if (!paramWorld.isClientSide) {
/* 44 */       NBTTagList nBTTagList = nBTTagCompound.getList("Recipes", 8);
/* 45 */       ArrayList<IRecipe> arrayList = Lists.newArrayList();
/* 46 */       for (byte b = 0; b < nBTTagList.size(); b++) {
/* 47 */         String str = nBTTagList.getString(b);
/* 48 */         IRecipe iRecipe = CraftingManager.a(new MinecraftKey(str));
/* 49 */         if (iRecipe != null) {
/* 50 */           arrayList.add(iRecipe);
/*    */         } else {
/* 52 */           a.error("Invalid recipe: " + str);
/* 53 */           return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemStack);
/*    */         } 
/*    */       } 
/*    */       
/* 57 */       paramEntityHuman.a(arrayList);
/* 58 */       paramEntityHuman.b(StatisticList.b(this));
/*    */     } 
/*    */     
/* 61 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemKnowledgeBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */