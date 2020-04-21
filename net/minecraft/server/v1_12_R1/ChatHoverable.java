/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ChatHoverable
/*    */ {
/*    */   private final EnumHoverAction a;
/*    */   private final IChatBaseComponent b;
/*    */   
/*    */   public ChatHoverable(EnumHoverAction paramEnumHoverAction, IChatBaseComponent paramIChatBaseComponent) {
/* 12 */     this.a = paramEnumHoverAction;
/* 13 */     this.b = paramIChatBaseComponent;
/*    */   }
/*    */   
/*    */   public EnumHoverAction a() {
/* 17 */     return this.a;
/*    */   }
/*    */   
/*    */   public IChatBaseComponent b() {
/* 21 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 26 */     if (this == paramObject) {
/* 27 */       return true;
/*    */     }
/* 29 */     if (paramObject == null || getClass() != paramObject.getClass()) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     ChatHoverable chatHoverable = (ChatHoverable)paramObject;
/*    */     
/* 35 */     if (this.a != chatHoverable.a) {
/* 36 */       return false;
/*    */     }
/* 38 */     if ((this.b != null) ? !this.b.equals(chatHoverable.b) : (chatHoverable.b != null)) {
/* 39 */       return false;
/*    */     }
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     return "HoverEvent{action=" + this.a + ", value='" + this.b + '\'' + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 55 */     int i = this.a.hashCode();
/* 56 */     i = 31 * i + ((this.b != null) ? this.b.hashCode() : 0);
/* 57 */     return i;
/*    */   }
/*    */   
/*    */   public enum EnumHoverAction {
/* 61 */     SHOW_TEXT("show_text", true),
/* 62 */     SHOW_ITEM("show_item", true),
/* 63 */     SHOW_ENTITY("show_entity", true);
/*    */ 
/*    */     
/* 66 */     private static final Map<String, EnumHoverAction> d = Maps.newHashMap();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     private final boolean e;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     private final String f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     static {
/* 84 */       for (EnumHoverAction enumHoverAction : values()) {
/* 85 */         d.put(enumHoverAction.b(), enumHoverAction);
/*    */       }
/*    */     }
/*    */     
/*    */     public static EnumHoverAction a(String param1String) {
/* 90 */       return d.get(param1String);
/*    */     }
/*    */     
/*    */     EnumHoverAction(String param1String1, boolean param1Boolean) {
/*    */       this.f = param1String1;
/*    */       this.e = param1Boolean;
/*    */     }
/*    */     
/*    */     public boolean a() {
/*    */       return this.e;
/*    */     }
/*    */     
/*    */     public String b() {
/*    */       return this.f;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatHoverable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */