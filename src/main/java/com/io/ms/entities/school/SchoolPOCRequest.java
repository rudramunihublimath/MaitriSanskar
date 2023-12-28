package com.io.ms.entities.school;


import com.io.ms.entities.login.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "school_poc_02")
public class SchoolPOCRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacherfirstname", nullable = false, length = 30)
    private String teacherfirstname;

    @Column(name = "teacherlastname", nullable = false, length = 30)
    private String teacherlastname;

    @Column(name = "designation", nullable = false, length = 40)
    private String designation;

    @Column(name = "contactNum1", nullable = true, length = 20)
    private String contactNum1;

    @Column(name = "contactNum2", nullable = true, length = 20)
    private String contactNum2;

    @Column(name = "linkdinid", length = 100)
    private String linkdinID;

    @Email
    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @Column(name = "firstContact", length = 10)
    private String firstContact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolNameRequest_id")
    public SchoolNameRequest schoolNameRequest;

}
