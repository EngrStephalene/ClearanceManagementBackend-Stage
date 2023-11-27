package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.FacultyDto;
import com.clearance.management.backend.dto.StudentDto;
import com.clearance.management.backend.dto.ViolationDto;
import com.clearance.management.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    public static final String EMAIL_TITLE_NEW_USER_ACCOUNT_VERIFICATION = "USER REGISTRATION NOTIFICATION.";
    public static final String EMAIL_TITLE_STUDENT_DATA_UPDATE = "STUDENT INFORMATION UPDATED NOTIFICATION.";
    public static final String EMAIL_TITLE_FACULTY_DATE_UPDATE = "FACULTY INFORMATION UPDATED NOTIFICATION.";
    public static final String EMAIL_TITLE_NEW_STUDENT_VIOLATION = "NEW VIOLATION FOR STUDENT NOTIFICATION.";

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${spring.mail.verify.host}")
    private String host;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * STUDENT ADD API
     * @param name
     * @param email
     * @param username
     * @param password
     */
    @Override
    public void studentAccountVerifEmail(String name, String email, String username, String password) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(EMAIL_TITLE_NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(studentAccVerifMsg(name, username, password));
            javaMailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     *
     * @param name
     * @param email
     * @param username
     * @param password
     */
    @Override
    public void facultyAccountVerifEmail(String name, String email, String username, String password) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(EMAIL_TITLE_NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(facultyAccVerifMsg(name, username, password));
            javaMailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     *
     * @param name
     * @param email
     * @param studentDto
     */
    @Override
    public void studentInformationUpdatedEmail(String name, String email, StudentDto studentDto) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(EMAIL_TITLE_STUDENT_DATA_UPDATE);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(updateStudentMsg(name, email, studentDto));
            javaMailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     *
     * @param name
     * @param email
     * @param facultyDto
     */
    @Override
    public void facultyInformationUpdatedEmail(String name, String email, FacultyDto facultyDto) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(EMAIL_TITLE_FACULTY_DATE_UPDATE);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(updatedFacultyMsg(name, email, facultyDto));
            javaMailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     *
     * @param violationDto
     * @param facultyDto
     * @param studentDto
     */
    @Override
    public void newStudentViolationEmail(ViolationDto violationDto, FacultyDto facultyDto, StudentDto studentDto) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(EMAIL_TITLE_NEW_STUDENT_VIOLATION);
            message.setFrom(fromEmail);
            message.setTo(studentDto.getEmail());
            message.setText(newStudentViolationMsg(violationDto, facultyDto, studentDto));
            javaMailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     *
     * @param name
     * @param username
     * @param password
     * @return
     */
    private String studentAccVerifMsg(String name, String username, String password) {
        return "Dear " + name + "," +
                "\n\nWe are delighted to welcome you to the SAINT FRANCIS COLLEGE - GUIHULNGAN CLEARANCE MANAGEMENT SYSTEM!" +
                "\n\nYour registration has been successfully processed, and your account is now active. Below, you will find your login credentials:" +
                "\n\nSTUDENT CREDENTIALS FOR CLEARANCE MANAGEMENT SYSTEM:" +
                "\n\nUsername: " + username +
                "\nTemporary Password: " + password +
                "\n\nFor security reasons, we recommend that you change your password as soon as you log in to the system. To do so, please follow these steps:"+
                "\n1. Visit the SAINT FRANCIS COLLEGE - GUIHULNGAN CLEARANCE MANAGEMENT SYSTEM website https://clearance-management-frontend-stage.vercel.app"+
                "\n2. Click on the \"Login\" button."+
                "\n3. Enter your provided username and temporary password."+
                "\n4. Click the 'Profile' menu in the sidebar."+
                "\n5. In the 'Profile' page, click 'Change Password' tab. Please ensure that your new password is strong and confidential."+
                "\n\nIf you encounter any difficulties during the login process or need further assistance, please do not hesitate to contact our support team."+
                "\nOur dedicated staff is ready to assist you with any questions or concerns you may have."+
                "\n\nThank you for choosing SAINT FRANCIS COLLEGE - GUIHULNGAN CLEARANCE MANAGEMENT SYSTEM. We look forward to providing you with an efficient and convenient clearance management experience."+
                "\n\n\nBest regards,"+
                "\nSAINT FRANCIS COLLEGE - GUIHULNGAN CLEARANCE MANAGEMENT SYSTEM";
    }

    /**
     *
     * @param name
     * @param username
     * @param password
     * @return
     */
    private String facultyAccVerifMsg(String name, String username, String password) {
        return "Dear " + name + "," +
                "\n\nWe are delighted to welcome you to the SAINT FRANCIS COLLEGE - GUIHULNGAN CLEARANCE MANAGEMENT SYSTEM!" +
                "\n\nYour registration has been successfully processed, and your account is now active. Below, you will find your login credentials:" +
                "\n\nFACULTY CREDENTIALS FOR CLEARANCE MANAGEMENT SYSTEM:" +
                "\n\nUsername: " + username +
                "\nTemporary Password: " + password +
                "\n\nFor security reasons, we recommend that you change your password as soon as you log in to the system. To do so, please follow these steps:"+
                "\n1. Visit the SAINT FRANCIS COLLEGE - GUIHULNGAN CLEARANCE MANAGEMENT SYSTEM website https://clearance-management-frontend-stage.vercel.app"+
                "\n2. Click on the \"Login\" button."+
                "\n3. Enter your provided username and temporary password."+
                "\n4. Click the 'Profile' menu in the sidebar."+
                "\n5. In the 'Profile' page, click 'Change Password' tab. Please ensure that your new password is strong and confidential."+
                "\n\nIf you encounter any difficulties during the login process or need further assistance, please do not hesitate to contact our support team."+
                "\nOur dedicated staff is ready to assist you with any questions or concerns you may have."+
                "\n\nThank you for choosing SAINT FRANCIS COLLEGE - GUIHULNGAN CLEARANCE MANAGEMENT SYSTEM. We look forward to providing you with an efficient and convenient clearance management experience."+
                "\n\n\nBest regards,"+
                "\nSAINT FRANCIS COLLEGE - GUIHULNGAN CLEARANCE MANAGEMENT SYSTEM";
    }

    /**
     *
     * @param name
     * @param email
     * @param studentDto
     * @return
     */
    private String updateStudentMsg(String name, String email, StudentDto studentDto) {
        return "Hello " + name + "," +
                "\n\nYour student information has been successfully updated." +
                "Kindly verify if this is correct; otherwise kindly update your information on the site." +
                "\n\nStudent Firstname: " + studentDto.getFirstName() +
                "\n\nStudent Middlename: " + studentDto.getEmail() +
                "\n\nStudent Lastname: " + studentDto.getLastName() +
                "\n\nStudent Email Address: " + email +
                "\n\nStudent Address: " + studentDto.getAddress() +
                "\n\nThank you.";
    }

    /**
     *
     * @param name
     * @param email
     * @param facultyDto
     * @return
     */
    private String updatedFacultyMsg(String name, String email, FacultyDto facultyDto) {
        return "Hello " + name + "," +
                "\n\nYour student information has been successfully updated." +
                "Kindly verify if this is correct; otherwise, please update your information on the site." +
                "\nStudent Firstname: " + facultyDto.getFirstName() +
                "\nStudent Middlename: " + facultyDto.getEmail() +
                "\nStudent Lastname: " + facultyDto.getLastName() +
                "\nStudent Email Address: " + email +
                "\nStudent Address: " + facultyDto.getAddress() +
                "\n\nThank you.";
    }

    /**
     *
     * @param violationDto
     * @param facultyDto
     * @param studentDto
     * @return
     */
    private String newStudentViolationMsg(ViolationDto violationDto, FacultyDto facultyDto, StudentDto studentDto) {
        return  "Dear " + studentDto.getFirstName() + " " + studentDto.getLastName()
                + "\nI hope this message finds you well. " +
                "\nWe would like to bring to your attention a matter of concern regarding your conduct at school. " +
                "\nIt has come to our notice that you have been involved in a violation of our school's code of conduct." +
                "\nDetails of the report says that you " + violationDto.getDescription() +
                "\n\nThis behavior is not in line with our school's expectations." +
                "\nAs a result of this violation, you are required to " + violationDto.getActionItem() +
                "\nPlease take this opportunity to reflect on your actions and make better choices in the future. We believe in your ability to do so." +
                "\n\nIf you have any questions or concerns, please contact support-sfc-g@gmail.com" +
                "\n\nSincerely," +
                "\nFaculty Management " + facultyDto.getFirstName() + " " + facultyDto.getLastName();
    }
}
