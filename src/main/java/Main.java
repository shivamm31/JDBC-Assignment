import javax.xml.transform.Result;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        try
        {
            // connecting to database

            final String JdbcDriver = "com.mysql.cj.jdbc.Driver";
            final String DbUrl = "jdbc:mysql://localhost:3306/MyDB";
            final String user = "root";
            final String password = "Knoldus@2012";

            Class.forName(JdbcDriver);
            Connection con=DriverManager.getConnection(DbUrl,user,password);

            System.out.println("1.Find the total amount to be paid at the time of checkout for a particular cart. As shown in above table. e.g. Query should return a single integer as total amount.");
            Statement statement1=con.createStatement();
            ResultSet r1=statement1.executeQuery("select sum(amount) as total from cart");
            while(r1.next())
                System.out.println("Total = "+r1.getString(1));

            System.out.println("\n\n");
            System.out.println("2. Find the product name which as sold the most.");
            Statement statement2=con.createStatement();
            ResultSet r2=statement2.executeQuery("select pname from product where pid=(select pid from cart where qty=(select Max(qty) from cart));");
            while(r2.next())
                System.out.println("Name with max qty = "+r2.getString(1));


            System.out.println("\n\n");
            System.out.println("3.Find all the products which are not yet sold.");
            Statement statement3=con.createStatement();
            ResultSet r3=statement3.executeQuery("select pname from product where pid NOT IN(Select pid from cart)");
            while(r3.next())
                System.out.println(r3.getString(1));

            System.out.println("\n\n");
            con.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

