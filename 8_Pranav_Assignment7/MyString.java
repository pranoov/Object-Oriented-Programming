public class MyString {
    public MyString() {
    String inputString = "Hello, World!";
    System.out.println(inputString);
    //from oracle docs
    System.out.println(inputString.toUpperCase());
    System.out.println(inputString.toLowerCase());
    
    System.out.println("Length: " + inputString.length() + ", String: " + inputString);
    //replace from oracle docs
    System.out.println(inputString.replace('o','O'));
    //index of from oracle docs
    System.out.println(inputString.indexOf('h') == 0);
    //using substring from oracle docs
    System.out.println(inputString.substring(inputString.length() - 6) == "World");
    System.out.println(inputString.substring(7,11));
    //replace all for strings from oracle docs
    System.out.println(inputString.replaceAll(" ",""));
    System.out.println(inputString.substring(5,6));
    System.out.println(inputString.indexOf(','));
    findPassword("pranav20");

    }
/**
* this method determines a password for a given user name
* it prints out the user name and the password
* @param String – username
* @return none
* */

    public void findPassword(String username)
    {
    String password = "";
    //https://www.w3schools.com/java/java_arrays.asp
    String[] letters = {"a","b","c","d","e","f"};
    if(username.length() > 4){
        password = "1" + username.substring(2,3).toUpperCase() + "2" + username.substring(4,6) + "10";
    }
    System.out.println("Username: " + username + ", Password: " + password);

    }
}
