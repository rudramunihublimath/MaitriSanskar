package com.io.ms.entities.school;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.io.ms.entities.login.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "school_01")
public class SchoolNameRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "code", unique = true, nullable = false, length = 40)
    private String code;

    @Column(name = "password", nullable = true, length = 60)
    private String password;

    @Email
    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "country", nullable = false, length = 15)
    private String country;

    @Column(name = "state", nullable = false, length = 20)
    private String state;

    @Column(name = "city", nullable = false, length = 20)
    private String city;

    @Column(name = "board", nullable = false, length = 200)
    private List<String> board;

    @Column(name = "contactNum1", nullable = false, length = 100)
    private String contactNum1;

    @Column(name = "contactNum2", nullable = false, length = 100)
    private String contactNum2;

    @Column(name = " chainofID", nullable = true, length = 300)
    private List<Long> chainofID;

    @Column(name = "address1", nullable = false, length = 150)
    private String address1;

    //@Column(name = "address2", length = 60)
    //private String address2;

    @Column(name = "pincode", nullable = false,length = 12)
    private String pincode;

    @Column(name = "websiteURL", length = 120)
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "USER_SCHOOL_T",
            joinColumns = {
                    @JoinColumn(name = "school_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            }
    )
    private Set<User> userName= new HashSet<>();

    @OneToMany(mappedBy = "schoolNameRequest")
    private List<SchoolPOCRequest> SchoolPOCRequest;

    @OneToMany(mappedBy = "schoolNmReq")
    private List<SchoolMBPMeetingRequest> schoolMBPMeetingRequest;

    //@OneToOne(mappedBy = "nameRequest", cascade = CascadeType.ALL, optional = true)
    //private OutReachRequest outReachRequest;

    @OneToOne(mappedBy = "trainRequest", cascade = CascadeType.ALL, optional = true)
    private TrainingRequest trainingRequest;

    @OneToOne(mappedBy = "mbpFlagsReq", cascade = CascadeType.ALL, optional = true)
    private MBPFlagsRequest mbpFlagsRequest;

    @OneToOne(mappedBy = "agreementReq", cascade = CascadeType.ALL, optional = true)
    private AgreementRequest agreementRequest;

    @OneToMany(mappedBy = "schoolNmReq2")
    private List<SchoolGradeRequest> schoolGradesRequest;

}
