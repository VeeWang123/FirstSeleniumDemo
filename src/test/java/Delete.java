import java.util.Arrays;

/**
 * Created by tianweiwang on 2019/9/27.
 */
public class Delete {

    public static String name;

      public static void main(String[] args){
          Delete d1=new Delete();
          Delete d2=new Delete();

          System.out.println(Delete.name);
          String str="abc/c-c";
          System.out.println(Arrays.toString(str.split("[\\/]")));

        }
}
