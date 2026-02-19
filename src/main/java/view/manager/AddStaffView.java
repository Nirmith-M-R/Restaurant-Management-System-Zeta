package view.manager;

import enums.IdProofType;
import enums.UserType;
import enums.WorkingStatus;
import env.Env;
import model.User;
import services.ManagerService;
import util.ScannerUtil;
import util.ValidatorUtil;

import java.io.Console;
import java.util.Scanner;

public class AddStaffView {
    public static void addStaffView() {

        Scanner scanner = ScannerUtil.getScanner();

        System.out.println(String.format("Welcome to %s !!", Env.RESTAURANTNAME));

        try {
            System.out.print("Enter Name: ");
            String name = scanner.next();

            System.out.print("\nEnter phone number: ");
            int phno = scanner.nextInt();

            boolean validatePhoneNumber = ValidatorUtil.validatePhoneNumber(phno);
            if (!validatePhoneNumber){
                throw new Exception("Invalid phone Number");
            }

            System.out.println("Enter User Type:\n1.Waiter\n2.Chef\n3.Manager\n4.Customer\n5.Receptionist");

            int userTypeChoice = scanner.nextInt();
            UserType userType;

            switch (userTypeChoice) {
                case 1:
                    userType = UserType.WAITER;
                    break;
                case 2:
                    userType = UserType.CHEF;
                    break;
                case 3:
                    userType = UserType.MANAGER;
                    break;
                case 4:
                    userType = UserType.CUSTOMER;
                    break;
                case 5:
                    userType = UserType.RECEPTIONIST;
                    break;
                default:
                    System.out.println("Wrong choice entered");
                    return;
            }

            System.out.print("Enter User Id: ");
            int userId = scanner.nextInt();

            System.out.print("Enter Government ID proof number: ");
            String govtIdProof = scanner.next();

            System.out.println("Enter ID proof type:\n1.Aadhar\n2.PAN\n3.VoterID");
            int idProofTypeChoice = scanner.nextInt();

            IdProofType idProofType;
            boolean validateGovtId;

            switch (idProofTypeChoice) {
                case 1:
                    idProofType = IdProofType.AADHAR;
                    validateGovtId = ValidatorUtil.validateAadharNumber(govtIdProof);
                    break;
                case 2:
                    idProofType = IdProofType.PAN;
                    validateGovtId = ValidatorUtil.validatePanNumber(govtIdProof);
                    break;
                case 3:
                    idProofType = IdProofType.VOTERID;
                    validateGovtId = ValidatorUtil.validateVoterIDNumber(govtIdProof);
                    break;
                default:
                    System.out.println("Wrong choice entered");
                    return;
            }

            if (!validateGovtId){
                throw new Exception("Invalid Govt ID");
            }
            System.out.println("Enter Password: ");
            String password = scanner.next();

            User user = new User(
                    name,
                    phno,
                    userId,
                    govtIdProof,
                    idProofType,
                    password,
                    userType,
                    WorkingStatus.ACTIVE
            );

            boolean isAdded = ManagerService.addStaff(user);

            if (isAdded)
                System.out.println("User has been added successfully.");
            else
                System.out.println("Failed to add user.");
        } catch (Exception e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
        }
    }
}
