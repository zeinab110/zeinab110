import model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Helper {
    static BufferedReader reader;
    static String path="C:\\Users\\Asus\\IdeaProjects\\FarmFrenzy\\src\\json\\";
    static String         FILE_PATH   = path+"missions.json";
    static String         FILE_CONFIG = path+"users.txt";
    static String         FILE_USERS  = path+"users.txt";
    static String         FILE_LOGS   = path+"log.txt";
    static String         line;

    public static String readFile() throws Exception {
        line = "";
        try {
            reader = new BufferedReader(new FileReader(FILE_PATH));
            StringBuilder stringBuilder = new StringBuilder();
            String        ls            = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

    public static List<User> getUsers() throws Exception {
        List<User> users       = new ArrayList<>();
        if (!readUsersFile().isEmpty()) {
            String[] usersString = readUsersFile().split(":");
            for (String userString : usersString) {
                users.add(new User(userString.split(";")));
            }
        }
        return users;
    }

    public static String readUsersFile() throws Exception {
        line = "";
        try {
            reader = new BufferedReader(new FileReader(FILE_USERS));
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

    public static void updateScore(int point, boolean isDecrease) throws IOException {
        try {
            int score = getUserScore();
            if (isDecrease)
                score -= point;
            else
                score += point;
            writeLog("updateScore : " + getUserScore() + " to " + score);
            FileWriter fw = new FileWriter(FILE_CONFIG);
            fw.write(String.valueOf(score));
            fw.close();
        } catch (Exception e) {
            writeLog("updateScore Exception : " + e.getMessage());
            System.err.println(e.getMessage());
        }
    }

    public static void insertNewUser(User user) throws Exception {
        boolean isExists = false;
        for (User user1 : getUsers()) {
            if (user.userName.equals(user1.userName)) {
                isExists = true;
                break;
            }
        }
        if (isExists) {
            System.err.println(user.userName + " is exists");
            writeLog(user.userName + " is exists ");
        } else {
            String oldUsers = readUsersFile();
            try {
                FileWriter fw    = new FileWriter(FILE_USERS,true);
                String     users = (oldUsers.isEmpty() ? "" : ":") + user.name + ";" + user.userName + ";" + user.password + ";" + String.valueOf(user.score);
                fw.write(users);
                writeLog(user + " insert ");
                fw.close();
            } catch (Exception e) {
                writeLog("insert New User Exception : " + user + "\t" + e.getMessage());
                System.err.println(e.getMessage());
            }
        }
    }

    public static int getUserScore() throws Exception {
        line = "";
        try {
            reader = new BufferedReader(new FileReader(FILE_CONFIG));
            StringBuilder stringBuilder = new StringBuilder();
            line = reader.readLine();
            stringBuilder.append(line);
            reader.close();

            writeLog("getUserScore : " + stringBuilder);
           // StringBuilder.toString();
            return Integer.parseInt(stringBuilder.toString());
        } finally {
            writeLog("getUserScore finally ");
            reader.close();
        }
    }

    public static void writeLog(String msg) {
        try {
            FileWriter fw = new FileWriter(FILE_LOGS,true);
            fw.write("\n" + new Date() + " : " + msg);
            fw.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
