import java.util.*;

public class Person {

   private String name;
   
   public Person(String s) {
      name = s;
   }
   
   public String getName() {
      return name;
   }
   
   public boolean equals (Person key) {
      boolean answer = false;
      if (this.name.equals(key.name)) {
         answer = true;
      }
      return answer;
   }
   
   public int hashCode() {
      int code = 0;
      for (int i = 0; i < name.length(); i++) {
         code += name.charAt(i);
      }
      return code;
   }

}