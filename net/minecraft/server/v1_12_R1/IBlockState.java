package net.minecraft.server.v1_12_R1;

import com.google.common.base.Optional;
import java.util.Collection;

public interface IBlockState<T extends Comparable<T>> {
  String a();
  
  Collection<T> c();
  
  Class<T> b();
  
  Optional<T> b(String paramString);
  
  String a(T paramT);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IBlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */