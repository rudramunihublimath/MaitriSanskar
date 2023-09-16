package com.io.ms.entities.school;


import com.io.ms.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "school")
public class SchoolNameRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Email
    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "city", nullable = false, length = 20)
    private String city;

    @Column(name = "board", nullable = false, length = 200)
    private List<String> board;

    @Column(name = "contactNum1", nullable = false, length = 20)
    private String contactNum1;

    @Column(name = "contactNum2", nullable = false, length = 20)
    private String contactNum2;

    @Column(name = " chainofID", nullable = false, length = 300)
    private List<String> chainofID;

    @Column(name = "address1", nullable = false, length = 60)
    private String address1;

    @Column(name = "address2", length = 60)
    private String address2;

    @Column(name = "pincode", nullable = false,length = 12)
    private String pincode;

    @Column(name = "websiteURL", length = 100)
    private String websiteURL;

    @Column(name = "linkdinid", length = 100)
    private String linkdinID;

    @Column(name = "facebookid", length = 100)
    private String facebookID;

    @Column(name = "instaid", length = 100)
    private String instaID;

    @Column(name = "targetPhase", length = 50)
    private String targetPhase;

    @Column(name = "mbpPersonName", length = 100)
    private String mbpPersonName;

    @Column(name = "mbpPersonContactNum", length = 20)
    private String mbpPersonContactNum;

    @Column(name = "mbpPersonEmail", length = 20)
    private String mbpPersonEmail;

    @Column(name = "refPersonName", length = 100)
    private String refPersonName;

    @Column(name = "refPersonContactNum", length = 20)
    private String refPersonContactNum;

    @CreationTimestamp
    @Column(name = "createdAt",updatable = false)
    private LocalDate createdDate;

    @UpdateTimestamp
    @Column(name = "updatedAt",insertable = false)
    private LocalDate updatedDate;

    @OneToMany(mappedBy = "schoolNameRequest")
    private List<SchoolPOCRequest> SchoolPOCRequest;

}
