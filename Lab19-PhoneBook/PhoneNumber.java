import java.util.*;

public class PhoneNumber {

   private String num;
   
   public PhoneNumber(String num) {
      this.num = num;
   }
   
   public String getNum() {
      return num;
   }
   
   public boolean equals (PhoneNumber pnum) {
      if (this.num.equals(pnum.num)) {
         return true;
      }
      return false;
   }
   
   public String toString() {
      return num;
   }
   
   public void setNum(String number) {
      num = number;
   }

}