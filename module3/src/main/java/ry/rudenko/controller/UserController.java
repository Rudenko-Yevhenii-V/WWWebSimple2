package ry.rudenko.controller;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UserController {

//  private final UserFacade userFacade = new UserFacadeImpl();
//  private final SecurityFacade securityFacade = new SecurityFacadeImpl();
//
//  public void start() {
//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    System.out.println("select your option");
//    String position;
//    try {
//      runNavigation();
//      while ((position = reader.readLine()) != null) {
//        crud(position, reader);
//        position = reader.readLine();
//        if (position.equals("0")) {
//          System.exit(0);
//        }
//        crud(position, reader);
//      }
//    } catch (IOException e) {
//      System.out.println("problem: = " + e.getMessage());
//    }
//  }
//
//  private void runNavigation() {
//    System.out.println();
//    System.out.println("if you want create user, please enter 1");
//    System.out.println("if you want login, please enter 2");
//    System.out.println("if you want update user, please enter 3");
//    System.out.println("if you want delete user, please enter 4");
//    System.out.println("if you want findById user, please enter 5");
//    System.out.println("if you want findAll user, please enter 6");
//    System.out.println("if you want exit, please enter 0");
//    System.out.println();
//  }
//
//  private void crud(String position, BufferedReader reader) {
//    switch (position) {
//      case "1":
//        create(reader);
//        break;
//      case "2":
//        login(reader);
//        break;
//      case "3":
//        update(reader);
//        break;
//      case "4":
//        delete(reader);
//        break;
//      case "5":
//        findById(reader);
//        break;
//      case "6":
//        findAll(reader);
//        break;
//      case "0":
//        System.exit(0);
//    }
//    runNavigation();
//  }
//
//  public void createForCRUD(String email, String password) {
//    userFacade.register(email, password);
//  }
//
//  private void create(BufferedReader reader) {
//    System.out.println("UserController.create");
//    try {
//      System.out.println("Please, enter your email");
//      String email = reader.readLine();
//      System.out.println("Please, enter your password");
//      String password = reader.readLine();
//      userFacade.register(email, password);
//    } catch (IOException e) {
//      System.out.println("problem: = " + e.getMessage());
//    }
//  }
//
//  public String loginForCRUD(String email, String password) {
//    String token = null;
//    System.out.println("UserController.login");
//    try {
//      token = securityFacade.login(email, password);
//    }catch (Exception ex){
//      return "NullPointerException";
//    }
//    return token;
//  }
//
//  private void login(BufferedReader reader) {
//    System.out.println("UserController.login");
//    try {
//      System.out.println("Please, enter your email");
//      String email = reader.readLine();
//      System.out.println("Please, enter your password");
//      String password = reader.readLine();
//      String token = securityFacade.login(email, password);
//      System.out.println("token = " + token);
//    } catch (IOException e) {
//      System.out.println("problem: = " + e.getMessage());
//    }
//  }
//
//  private void update(BufferedReader reader) {
//    System.out.println("UserController.update");
//    try {
//      System.out.println("Please, enter token");
//      String token = reader.readLine();
//      System.out.println("Please, enter your first name");
//      String firstName = reader.readLine();
//      System.out.println("Please, enter your last name");
//      String lastName = reader.readLine();
//      UserDto userDto = new UserDto();
//      userDto.setFirstName(firstName);
//      userDto.setLastName(lastName);
//      userFacade.updateByToken(userDto, token);
//    } catch (IOException e) {
//      System.out.println("problem: = " + e.getMessage());
//    }
//  }
//
//  private void delete(BufferedReader reader) {
//    System.out.println("UserController.delete");
//    try {
//      System.out.println("Please, enter token");
//      String token = reader.readLine();
//      userFacade.deleteByToken(token);
//    } catch (IOException e) {
//      System.out.println("problem: = " + e.getMessage());
//    }
//  }
//
//  private void findById(BufferedReader reader) {
//    System.out.println("UserController.findById");
//    try {
//      System.out.println("Please, enter token");
//      String token = reader.readLine();
//      UserDto userDto = userFacade.findByToken(token);
//      System.out.println("user = " + userDto);
//    } catch (IOException e) {
//      System.out.println("problem: = " + e.getMessage());
//    }
//  }
//
//  private void findAll(BufferedReader reader) {
//    System.out.println("UserController.findAll");
//    try {
//      System.out.println("Please, enter token");
//      String token = reader.readLine();
//      List<UserDto> users = userFacade.findAllByToken(token);
//      for (UserDto user : users) {
//        System.out.println("user = " + user);
//      }
//    } catch (IOException e) {
//      System.out.println("problem: = " + e.getMessage());
//    }
//  }
}
