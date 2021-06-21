import com.google.gson.Gson;
import model.Direction;
import model.Mission;
import model.Missions;
import model.User;

import java.util.*;

public class Main extends TimerTask {
    static Scanner    scanner    = new Scanner(System.in);
    static String[][] board      = new String[6][6];
    static Missions   missions;
    static Random     random     = new Random();
    static User       userGlobal = new User();

    public static void main(String[] args) throws Exception {
        Helper.writeLog("Start Game");

        Gson gson = new Gson();
        missions = gson.fromJson(Helper.readFile(), Missions.class);
        System.out.println("1)LOGIN");
        System.out.println("2)SIGNUP");
        System.out.print("0)Exit \nSelect one : ");
        int n = scanner.nextInt();
        if (n == 1) {

            Helper.writeLog("Select Login");
            System.out.print("Enter UserName : ");
            String userName = scanner.next();
            if (!Helper.readUsersFile().isEmpty()) {

                Helper.writeLog("Insert user name : " + userName);
                List<User> users    = Helper.getUsers();
                boolean    isExists = false;
                for (User user : users) {
                    if (user.userName.equals(userName)) {
                        isExists = true;
                        Helper.writeLog("User name " + userName + " found");
                        userGlobal = user;
                        break;
                    }
                }
                if (isExists) {
                    System.out.print("Enter Password: ");
                    String password = scanner.next();
                    if (!userGlobal.userName.isEmpty() && userGlobal.password.equals(password)) {
                        Helper.writeLog(userName + " Login");
                        startGame();
                    } else {
                        System.err.println("Password not valid.");
                        System.out.print("Enter Password: ");
                        password = scanner.next();
                        if (!userGlobal.userName.isEmpty() && userGlobal.password.equals(password)) {
                            Helper.writeLog(password + " re enter " + userName + " login");
                            startGame();
                        } else {
                            Helper.writeLog(password + " Password not valid.\tExiting Program...");
                            System.err.println("Password not valid.");
                            System.err.println("Exiting Program...");
                            System.exit(0);
                        }
                    }

                } else {
                    Helper.writeLog(userName + " User name not found.\tExiting Program...");
                    System.err.println("User name not found.");
                    System.err.println("Exiting Program...");
                    System.exit(0);
                }
            } else {
                System.err.println("User Not found please sign up");
            }
        } else if (n == 2) {
            Helper.writeLog("Select Register");
            System.out.print("Enter your name: ");
            String name = scanner.next();
            System.out.print("Enter your user name: ");
            String userName = scanner.next();
            System.out.print("Enter your password: ");
            String password = scanner.next();
            User   user     = new User(name, userName, password, missions.getFirstScore());
            userGlobal = user;
            Helper.insertNewUser(user);
            startGame();
        } else {
            Helper.writeLog("select invalid menu");
            System.err.println("Please select valid menu");
        }

    }

    private static void startGame() throws Exception {
        Helper.writeLog("start Game");
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                 board[i][j] = "-";

        System.out.println("Developer : " + missions.getName());
        System.out.println("LEVEL Count : " + missions.getMissions().size() + " - Score " + Helper.getUserScore());
        for (Mission mission : missions.getMissions()) {
            System.out.println(mission);
        }
        System.out.print("0)log out 1)Select level to start game : ");
        int n = scanner.nextInt();
        if (n == 0) {
            Helper.writeLog("log out");
            userGlobal = null;
            System.err.println("Exiting Program...");
            System.exit(0);
        } else if (userGlobal != null && Helper.getUserScore() > 100) {
            Helper.writeLog(userGlobal.userName + "\tStart Game\t");
            if (n > 0 && n < missions.getMissions().size()) {
                Mission mission = missions.getMissions().get(n - 1);
                if (mission.getIsOpen()) {
                    TimerTask timerTask = new Main();
                    Timer     timer     = new Timer(true);
                    timer.scheduleAtFixedRate(timerTask, 0, 800);
                    System.out.println("TimerTask started");
                    try {
                        Thread.sleep(mission.getTime());//set Time fir mission
                        Thread.sleep(mission.getTime() * 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timer.cancel();
                    System.out.println("TimerTask cancelled");
                }
            } else {
                Helper.writeLog("\tinvalid level");
                System.err.println("Select correct level");
            }
        } else {
            Helper.writeLog("score not enough");
            System.err.println("Your score not enough");
        }
    }


    @Override
    public void run() {
        completeTask();
    }

    static int getRandom() {
        int low  = 0;
        int high = 5;
        return random.nextInt(high - low) + low;
    }


    private void completeTask() {
        try {
            System.err.println("Your Score " + Helper.getUserScore());
            System.out.println("Please select:0)Exit 1)Insert Hen 2)Insert Turkey 3)Insert Buffalo");
            System.out.print("Please select:4)Fill with water 5)Planting grass 3)Insert Buffalo :");
            int n = scanner.nextInt();
            int i = getRandom();
            int j = getRandom();
            switch (n) {
                case 0 -> {
                    System.err.println("Exiting Program...");
                    System.exit(0);
                }
                case 1 -> {
                    if (Helper.getUserScore() >= 100) {
                        board[i][j] = "H";
                        Helper.updateScore(100, true);
                    } else
                        System.err.println("Your score not enough");
                }
                case 2 -> {
                    if (Helper.getUserScore() >= 200) {
                        board[i][j] = "T";
                        Helper.updateScore(200, true);
                    } else
                        System.err.println("Your score not enough");
                }
                case 3 -> {
                    if (Helper.getUserScore() >= 400) {
                        board[i][j] = "B";
                        Helper.updateScore(400, true);
                    } else
                        System.err.println("Your score not enough");
                }
                case 4 -> board[i][j] = "F";
                case 5 -> {
                    System.out.print("Enter i:");
                    i = scanner.nextInt();
                    System.out.print("Enter j:");
                    j           = scanner.nextInt();
                    board[i][j] = "P";
                }
            }
            showBoard();
            Thread.sleep(800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void updateBoard() {
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 6; j++) {
//                direction = move(i, j);
//                switch (board[i][j]) {
//                    case "H" -> changeBoard(i, j, direction, "H");
//                    case "T" -> changeBoard(i, j, direction, "T");
//                    case "B" -> changeBoard(i, j, direction, "B");
//                }
//            }
//        }
    }

    private void showBoard() {
        updateBoard();
        System.out.println("---------------------------------");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------");
    }

    private static void changeBoard(int i, int j, Direction direction, String ch) {
        switch (direction) {
            case LEFT -> {
                board[i][j]     = "-";
                board[i][j - 1] = ch;
            }
            case RIGHT -> {
                board[i][j]     = "-";
                board[i][j + 1] = ch;
            }
            case TOP -> {
                board[i][j]     = "-";
                board[i - 1][j] = ch;
            }
            case BOTTOM -> {
                board[i][j]     = "-";
                board[i + 1][j] = ch;
            }
        }
    }

    static Direction move(int i, int j) {
        if (canGoLeftOrTop(i)) {
            return Direction.LEFT;
        } else if (canGoLeftOrTop(j)) {
            return Direction.TOP;
        } else if (canGoRightOrBottom(i)) {
            return Direction.BOTTOM;
        } else if (canGoRightOrBottom(j)) {
            return Direction.RIGHT;
        }
        return Direction.LEFT;
    }

    static boolean canGoLeftOrTop(int i) {
        return i > 1;
    }

    static boolean canGoRightOrBottom(int i) {
        return i < 4;
    }

}
