package util;

public class Sever {
    public static String localhost = "192.168.101.38";
//    public static String localhost = "10.20.184.43";
    public static String producttypelink = "http://"+localhost+ ":8080/Sever/getdevicetype.php";
    public static String newproduct = "http://"+localhost+":8080/Sever/getnewproduct.php";
    public static String product = "http://"+localhost+":8080/Sever/getproduct.php";
    public static String billlink = "http://"+localhost+":8080/Sever/customerinformation.php";
    public static String orderdetaillink = "http://"+localhost+":8080/Sever/orderdetail.php";
    public static String signup = "http://"+localhost+":8080/Sever/signup.php";
    public static String login = "http://"+localhost+":8080/Sever/login.php";
    public static String checkuser = "http://"+localhost+":8080/Sever/checkuser.php";
    public static String getcart = "http://"+localhost+":8080/Sever/getcart.php";
}
