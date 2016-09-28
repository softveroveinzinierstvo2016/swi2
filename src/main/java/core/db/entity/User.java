package core.db.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The User class represent object with instances(Long id, String role, String
 * name, String userName, String surname, String password, String email, Date
 * contractStart, Date contractEnd, Long idProject). Object contains
 * toString,getter and setters. Object defines one of users(admin, manager,
 * student).
 *
 * @author Rastislav Vocko
 * @version 1.0
 * @since 2016-04-25
 */
@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 1, max = 24)
    @Column(name = "role")
    private String role;

    @NotNull
    @Column(name = "name")
    @Size(min = 1, max = 24)
    private String name;

    @NotNull
    @Column(name = "user_name")
    @Size(min = 1, max = 24)
    private String userName;

    @NotNull
    @Column(name = "surname")
    @Size(min = 1, max = 44)
    private String surname;

    @NotNull
    @Column(name = "password")
    @Size(min = 1, max = 255)
    private String password;

    @NotNull
    @Pattern(regexp = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")
    @Column(name = "email")
    @Size(min = 1, max = 44)
    private String email;

    @NotNull
    @Size(min = 1, max = 19)
    @Column(name = "contract_type")
    private String contractType;

    @Column(name = "contract_start")
    private Date contractStart;

    @Column(name = "contract_end")
    private Date contractEnd;

    @Column(name = "id_project")
    private Long projectId;

    /**
     * The object contructor.
     *
     */
    public User() {
    }

    /**
     * @return Date in format (yyyy-MM-dd) when user started working.
     */
    public Date getContractStart() {
        return contractStart;
    }

    /**
     * @param contractStart is a date when user started working in format
     * (yyyy-MM-dd).
     */
    public void setContractStart(Date contractStart) {
        this.contractStart = contractStart;
    }

    /**
     * @return Long id of user. Id is setted on DB level.
     */
    public Long getId() {
        return id;
    }

    /**
     * @return String which defines one of user roles(admin, manager, user).
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role defines one of user roles(admin, manager, user).
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return String with user`s name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name with user`s name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return user`s userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName is a user`s userName. UserName must be unique.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return String user`s surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname is a user`s surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return String user`s password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password is a user`s password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return String of user`s mail.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email is a user`s email. Mail must be valid.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String type of user contract(Part-Time, Full-Time,
     * Bussines-Licence).
     */
    public String getContractType() {
        return contractType;
    }

    /**
     * @param contractType is a user`s type of contract.
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    /**
     * @return Date where user end work.Format of date is(yyyy-MM-dd).
     */
    public Date getContractEnd() {
        return contractEnd;
    }

    /**
     * @param contractEnd is a date when user end work
     */
    public void setContract_end(Date contractEnd) {
        this.contractEnd = contractEnd;
    }

    /**
     * @return Long projectId
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * @param projectId is a id in which user work.
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id + '\''
                + ", role=" + role + '\''
                + ", name='" + name + '\''
                + ", user name='" + userName + '\''
                + ", surname='" + surname + '\''
                + ", password='" + password + '\''
                + ", email='" + email + '\''
                + ", contractType='" + contractType + '\''
                + ", contractStart=" + contractStart + '\''
                + ", contract_end=" + contractEnd + '\''
                + ", projectId=" + projectId + '\''
                + '}';
    }
}
